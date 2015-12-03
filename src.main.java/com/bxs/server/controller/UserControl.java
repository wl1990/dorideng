package com.bxs.server.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.UserManagerImpl;
import com.bxs.server.model.User;
import com.bxs.server.util.ShortUrlGenerator;
import com.bxs.server.util.UploadFile;

@Controller
@RequestMapping("/user")
public class UserControl extends BaseControl{
	@Autowired
	private UserManagerImpl usermag;
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(required=false) String id){
		User user=null;
		if(id!=null && !id.isEmpty())
			user=usermag.getById(id);
		else
			user=new User();
		ModelAndView mv=new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("rootPath", getRootPath());
		mv.addObject("basepath",getBasePath());
		mv.setViewName("user/userEdit");
		return mv;
	}
	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user,@RequestParam(required=false) MultipartFile[] myfiles,HttpServletRequest request,@RequestParam String newpassword){
		String password="";
		if((user.getId()!=null && !newpassword.equals(user.getPassword()))||user.getId()==null){
			password=ShortUrlGenerator.getMD5OfStr(newpassword);//密码加密
			user.setPassword(password);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setRegistrationTime(sdf.format(new Date()));
		String weibo=user.getWeibo();
		for(MultipartFile myfile:myfiles){
			if(!myfile.isEmpty()){
				//判断有没有新上传
				try {
	            	String imageurl=UploadFile.uploadFiles(myfile,request.getSession().getServletContext());
					user.setImageUrl(imageurl);
				} catch (Exception e) {
					ModelAndView mv=new ModelAndView();
					mv.setViewName("error");
					return mv;
				} 
			}
		}
		usermag.dbSave(user);
		return list("1");
	}
	
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam String id){
		User user=usermag.getById(id);
		if(user!=null)
		usermag.dbDelete(user);
		return list("1");
	}
	@RequestMapping("/deleteall")
	public ModelAndView deleteAll(){
		String[] ids=request.getParameterValues("checkedid");
		List<User> userlist=new ArrayList<User>();
		if(ids!=null)
			for(String id:ids){
				userlist.add(usermag.getById(id));
			}
		usermag.dbDelete(userlist);
		return list("1");
		
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false) String page){
		int pages;
		pages=page==null ? 1:Integer.parseInt(page);
		int maxpage=usermag.getMaxPage(size, User.class);
		pages=pages<1 ? 1:(pages>maxpage?maxpage:pages);
		ModelAndView mv=new ModelAndView();
		List<User> userlist=(List<User>) usermag.getMerPerPage(User.class, pages, size);
		int amount=usermag.getAmount(User.class);
		mv.addObject("maxpage", maxpage);
		mv.addObject("amount", amount);
		mv.addObject("currentpage", pages);
		mv.addObject("rootPath", getRootPath());
		mv.addObject("userlist", userlist);
		mv.setViewName("user/userList");
		return mv;
	}																						
	
	
}
