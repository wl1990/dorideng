package com.bxs.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;







import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.RightMenuMagImpl;
import com.bxs.server.model.RightMenu;
import com.bxs.server.model.User;
import com.bxs.server.util.HibernateUtil;

@Controller
@RequestMapping("/rightMenu")
public class RightMenuControl extends BaseControl{
	@Autowired
	private RightMenuMagImpl rightmag;
	
	@RequestMapping("/edit")
	public ModelAndView menuEdit(){
		List<RightMenu> rightlist=rightmag.getByParentId("");  //根节点
		JSONArray  spec=new JSONArray();
		createTree(rightlist,spec);
//		createRootTree(rightlist,spec);
		RightMenu rmenu=new RightMenu();
		ModelAndView mv=new ModelAndView();
		mv.addObject("spec", spec);
		mv.addObject("rmenu", rmenu);
		mv.setViewName("rightmenu/menuEdit");
		return mv;
	}
	
	/**
	 * 创建根节点的树
	 */
	public void createRootTree(List<RightMenu> rightList,JSONArray spec){
		if(rightList!=null){
			for(RightMenu r:rightList){
				JSONObject node=new JSONObject();
				node.put("id", r.getSelfId());
				node.put("pId", r.getParentId());
				node.put("name", r.getMenuName());
				node.put("edit", "编辑");
				spec.add(node);
			}
			}
	}
	 
	/**
	 * 创建完整树
	 */
	public void createTree(List<RightMenu> rightList,JSONArray spec){
		if(rightList!=null){
		for(RightMenu r:rightList){
			JSONObject node=new JSONObject();
			node.put("id", r.getSelfId());
			node.put("pId", r.getParentId());
			node.put("name", r.getMenuName());
			node.put("edit", "编辑");
			JSONArray childNode=createChildTree(r.getSelfId());
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
		List<RightMenu> childList=rightmag.getByParentId(id);
		if(childList==null || childList.size()==0)
		return null;
		else{
			for(RightMenu rm:childList){
				JSONObject node=new JSONObject();
				node.put("id", rm.getSelfId());
				node.put("name",rm.getMenuName());
				node.put("pId",rm.getParentId());
				node.put("edit", "编辑");
				JSONArray childNodes=createChildTree(rm.getSelfId());
				node.put("children", childNodes);
				childNode.add(node);
			}
		}
		return childNode;
	}

	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute RightMenu rmenu,@RequestParam String newParentId){
		if(newParentId==null || newParentId.isEmpty())
			newParentId="";
		if(rmenu.getSelfId()==null||rmenu.getSelfId().isEmpty()){ //selfId is null so add
			rmenu.setParentId(newParentId);
			rmenu.setSelfId(createCurrentMenuId(newParentId));
		}else{//update 
//		if(rmenu.getParentId()==null || rmenu.getParentId().isEmpty())
//			rmenu.setParentId("");
		if(!newParentId.equals(rmenu.getParentId()))//parentid changed,selfid have to be changed too
//			if("".equals(newParentId)){
//				newParentId=null;
//				rmenu.setParentId(null);
//			}
			rmenu.setParentId(newParentId);
			rmenu.setSelfId(createCurrentMenuId(newParentId));
	    }
		rightmag.dbSave(rmenu);
		return menuEdit();
	}
	
	/**
	 * 生成菜单id
	 * @param parentid  父id
	 * @param pageflag  前后台标识
	 * @return
	 */
	public  String createCurrentMenuId(String parentid){
		List<RightMenu> list=rightmag.getByParentId(parentid);
		int subid=0;
		String selfid="";
		if(list==null ||list.isEmpty()){
			subid=1;
		}else{
			int max=0;
			for(RightMenu rmenu:list){
				int tmp=Integer.parseInt(rmenu.getSelfId().substring((rmenu.getSelfId().lastIndexOf("s")+1)));
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
	
	//异步删除
	@RequestMapping("/delete")
	public void delete(@RequestParam String nodeid){
		if(!stringIsEmpty(nodeid)){
			rightmag.deleteBySelfId(nodeid);
			List<RightMenu> srlist=rightmag.getByParentId(nodeid);
			if(srlist!=null){
				for(RightMenu rmenu:srlist){
					delete(rmenu.getSelfId());
					
				}
			}
		}
	}
	
	/**
	 * 异步修改菜单
	 * @param nodeid
	 * @return
	 */
	@RequestMapping("/getMenu")
	@ResponseBody
	public Object getMenu(@RequestParam String nodeid){
		JSONObject json=new JSONObject();
		RightMenu rmenu=rightmag.getBySelfId(nodeid);
		if(rmenu!=null){
			json.put("id", rmenu.getId());
			json.put("imageUrl", rmenu.getImageUrl());
			json.put("menuMark", rmenu.getMenuMark());
			json.put("menuType", rmenu.getMenuType());
			json.put("menuUrl", rmenu.getMenuUrl());
			json.put("menuName", rmenu.getMenuName());
			json.put("parentId", rmenu.getParentId());
			json.put("selfId", rmenu.getSelfId());
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
		RightMenu dropRoot=rightmag.getBySelfId(nodeid);
		dropRoot.setParentId(parentid);
		String dropRootSelfid=createCurrentMenuId(parentid);
		dropRoot.setSelfId(dropRootSelfid);
		rightmag.dbSave(dropRoot);  //移动子树的根节点
		List<RightMenu> droplist=rightmag.getByParentId(nodeid);
		if(droplist!=null){
			for(RightMenu menu:droplist){
				//递归移动所有的节点
				dragUpdate(menu.getSelfId(),dropRootSelfid);
			}
		}
		return new JSONObject();
	}
	
}
