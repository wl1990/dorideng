package com.bxs.server.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.RightMenuMagImpl;
import com.bxs.server.manager.impl.UserManagerImpl;
import com.bxs.server.model.RightMenu;
import com.bxs.server.model.User;
import com.bxs.server.util.CookieTool;

@Controller
@RequestMapping("/")
public class DefaultControl extends BaseControl{
	@Autowired
	private UserManagerImpl usermag;
	@Autowired
	private RightMenuMagImpl emnumag;
	
	@RequestMapping("/")
	public ModelAndView defaultPath(HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		isSaved(mv,response);
		return mv;
	}
	
	/**
	 * 判断是否已经记住了密码
	 */
	public void isSaved(ModelAndView mv,HttpServletResponse response){
		Cookie loginName=CookieTool.getCookieByName(request, "loginName");
		Cookie loginPassword=CookieTool.getCookieByName(request, "loginPwd");
		if(loginName!=null && loginPassword!=null && !stringIsEmpty(loginName.getValue()) && !stringIsEmpty(loginPassword.getValue())){
			String name=loginName.getValue();
			String pwd=loginPassword.getValue();
			List<User> list=usermag.getByUsernameAndPassword(name, pwd);
			if(list!=null && list.size()==1){
				List<RightMenu> menulist=emnumag.getAll();
				if(menulist!=null)
				mv.addObject("menulist", menulist);
				mv.setViewName("frame/main");
				HttpSession session=request.getSession();
				session.setAttribute(LOGIN_ID, list.get(0).getId());
			}else{
				mv.addObject("errmassage", "用户名和密码不匹配");
				mv.setViewName("login/Login");
			}
		}else{
			//清除过时的cookie
			CookieTool.addCookie(response, "loginName", null, 0); 
			CookieTool.addCookie(response, "loginPwd",null, 0);
			mv.setViewName("login/Login");
		}
	}
	
}
