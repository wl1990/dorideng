package com.bxs.server.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.ProductionManagerImpl;
import com.bxs.server.manager.impl.ProductionPicManagerImpl;
import com.bxs.server.model.Production;
import com.bxs.server.model.ProductionPictures;
import com.bxs.server.model.RightMenu;
import com.bxs.server.util.DateFormatUtil;
import com.bxs.server.util.UploadFile;

@Controller
@RequestMapping("/production")
public class ProductionControl extends BaseControl{
	@Autowired
	private ProductionManagerImpl productionMag;
	@Autowired
	private ProductionPicManagerImpl productionPicMag;
	
	@RequestMapping("/edit")
	public ModelAndView productionEdit(@RequestParam(required=false) String procid){
		List<Production> rightlist=productionMag.getByParentId("");  //根节点
		String status="0";
		JSONArray  spec=new JSONArray();
		createTree(rightlist,spec);
		Production pd=null;
		ModelAndView mv=new ModelAndView();
		if(!stringIsEmpty(procid)&& !"0".equals(procid)){
			pd=productionMag.getById(Integer.parseInt(procid));
			List<ProductionPictures> pp=productionPicMag.getByProId(Integer.parseInt(procid));
			if(pp!=null && pp.size()>0){
				status=String.valueOf(pp.get(0).getStatus());
			}
		}
		if(pd==null)
			pd=new Production();
		mv.addObject("spec", spec);
		mv.addObject("proc", pd);
		mv.addObject("status",status);
		mv.setViewName("production/ProductionEdit");
		return mv;
	}
	
	
	
	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute Production proc,@RequestParam String newParentId,HttpServletRequest request){
		if(stringIsEmpty(newParentId))
			newParentId="";
		if(stringIsEmpty(proc.getProselfId())){ //selfId is null so add
			proc.setProparentId(newParentId);
			proc.setProselfId(createCurrentMenuId(newParentId));
		}else{//update 
		if(!newParentId.equals(proc.getProparentId()))//parentid changed,selfid have to be changed too
			proc.setProparentId(newParentId);
			proc.setProselfId(createCurrentMenuId(newParentId));
	    }
		
		String imageurl=mulfileUpload(request);
		proc.setProPic(imageurl);
		Integer proid=0;
		if(productionMag.dbSave(proc)){
			List<Object[]> idlist=productionMag.getByProcname(proc.getProName());
			if(idlist!=null)
				proid=(Integer) idlist.get(0)[0];
		}
		return productionEdit(String.valueOf(proid));
	}
	
	/**
	 * 上传修改产品图片
	 * @param request
	 * @param productionid
	 * @return
	 */
	@RequestMapping("/uploadimg")
	@ResponseBody
	public  Object uploadImg(HttpServletRequest request,@RequestParam(required=false) String productionid,@RequestParam String status){
		JSONObject json=new JSONObject();
		if(stringIsEmpty(productionid)) //添加产品
			productionid="0";
		if(stringIsEmpty(status))
			status="0";
		else{//修改产品
				List<ProductionPictures> pp=productionPicMag.getByProId(Integer.parseInt(productionid),Integer.parseInt(status));
				if(pp!=null && pp.size()>0){
					productionPicMag.dbDelete(pp);
				}
			
		}
		ProductionPictures propic=new ProductionPictures();
		propic.setProductionId(Integer.parseInt(productionid));
		String imageurl=mulfileUpload(request);
		propic.setPicUrl(imageurl);
		int ver=Integer.parseInt(status);
		propic.setStatus(++ver);
		propic.setCreateTime(DateFormatUtil.dateToString());
		productionPicMag.dbSave(propic);
		int time=1;
		return json;
	}
	/**
	 * 生成菜单id
	 * @param parentid  父id
	 * @param pageflag  前后台标识
	 * @return
	 */
	public  String createCurrentMenuId(String parentid){
		List<Production> list=productionMag.getByParentId(parentid);
		int subid=0;
		String selfid="";
		if(list==null ||list.isEmpty()){
			subid=1;
		}else{
			int max=0;
			for(Production proc:list){
				int tmp=Integer.parseInt(proc.getProselfId().substring((proc.getProselfId().lastIndexOf("s")+1)));
				max=(max>=tmp)?max:tmp;
			}
			subid=max+1;
		}
		if(stringIsEmpty(parentid))
			selfid="s"+subid;
		else
			selfid=parentid+"s"+subid;
		return selfid;
	}
	/**
	 * 异步加载节点
	 * @param nodeid
	 */
	@RequestMapping("/asyncLoad")
	@ResponseBody
	public Object asyncLoad(@RequestParam String id){
		return createChildTree(id);
	}
	
	/**
	 * 异步删除
	 * @param nodeid
	 */
		@RequestMapping("/delete")
		public void delete(@RequestParam String nodeid){
			if(!stringIsEmpty(nodeid)){
				if(productionMag.deleteBySelfId(nodeid)){
					List<Production> srlist=productionMag.getByParentId(nodeid);
					if(srlist!=null){
						for(Production proc:srlist){
							delete(proc.getProselfId());
						}
					}
				}
			}
		}
		
		/**
		 * 异步修改
		 * @param nodeid
		 * @return
		 */
		@RequestMapping("/getProduction")
		@ResponseBody
		public Object getMenu(@RequestParam String nodeid){
			JSONObject json=new JSONObject();
			JSONArray jary=new JSONArray();
			Production rmenu=productionMag.getBySelfId(nodeid);
			if(rmenu!=null){
				List<ProductionPictures> pp=productionPicMag.getByProId(rmenu.getId());
				String status="0";
				if(pp!=null && pp.size()>0){
					status=String.valueOf(pp.get(0).getStatus());
					for(ProductionPictures pic:pp){
						jary.add(pic.getPicUrl());
					}
				}
				json.put("id", rmenu.getId());
				json.put("imageUrl", rmenu.getProPic());
				json.put("proname", rmenu.getProName());
				json.put("proType", rmenu.getProType());
				json.put("detail", rmenu.getDetail());
				json.put("parentId", rmenu.getProparentId());
				json.put("selfId", rmenu.getProselfId());
				json.put("status", status);
				json.put("ppic", jary);
			}
			return json;
		}
		
		/**
		 * 拖拽子树
		 * @param nodeid
		 * @param parentid
		 */
		@RequestMapping("/dragUpdate")
		@ResponseBody
		public Object dragUpdate(@RequestParam String nodeid,@RequestParam String parentid){
			if(stringIsEmpty(parentid))
				parentid="";
			Production dropRoot=productionMag.getBySelfId(nodeid);
			dropRoot.setProparentId(parentid);
			String dropRootSelfid=createCurrentMenuId(parentid);
			dropRoot.setProselfId(dropRootSelfid);
			productionMag.dbSave(dropRoot);  //移动子树的根节点
			List<Production> droplist=productionMag.getByParentId(nodeid);
			if(droplist!=null){
				for(Production proc:droplist){
					//递归移动所有的节点
					dragUpdate(proc.getProselfId(),dropRootSelfid);
				}
			}
			return new JSONObject();
		}
	/**
	 * 创建完整树
	 */
	public void createTree(List<Production> pdList,JSONArray spec){
		if(pdList!=null){
		for(Production r:pdList){
			JSONObject node=new JSONObject();
			node.put("id", r.getProselfId());
			node.put("pId", r.getProparentId());
			node.put("name", r.getProName());
			node.put("edit", "编辑");
			JSONArray childNode=createChildTree(r.getProselfId());
			node.put("children", childNode);
			spec.add(node);
		}
		}
	}
	
	/**
	 * 创建节点的子树
	 */
	public JSONArray createChildTree(String id){
		JSONArray childNode=new JSONArray();
		List<Production> childList=productionMag.getByParentId(id);
		if(childList==null || childList.size()==0)
		return null;
		else{
			for(Production rm:childList){
				JSONObject node=new JSONObject();
				node.put("id", rm.getProselfId());
				node.put("name",rm.getProName());
				node.put("pId",rm.getProparentId());
				node.put("edit", "编辑");
				JSONArray childNodes=createChildTree(rm.getProselfId());
				node.put("children", childNodes);
				childNode.add(node);
			}
		}
		return childNode;
	}
	
}
