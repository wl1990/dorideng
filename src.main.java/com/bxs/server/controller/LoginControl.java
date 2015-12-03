package com.bxs.server.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.RightMenuMagImpl;
import com.bxs.server.manager.impl.UserManagerImpl;
import com.bxs.server.model.RightMenu;
import com.bxs.server.model.User;
import com.bxs.server.util.CookieTool;
import com.bxs.server.util.ShortUrlGenerator;

@Controller
@RequestMapping("/login")
public class LoginControl extends BaseControl{
	@Autowired
	private UserManagerImpl usermag;
	@Autowired
	private RightMenuMagImpl emnumag;
	private static final int MAXAGE=30*24*60*60;
	
	@RequestMapping("/check")
	public ModelAndView check(@RequestParam String username,@RequestParam String passWord,@RequestParam(required=false) String rempwd,HttpServletResponse response,HttpServletRequest request){
		String password=ShortUrlGenerator.getMD5OfStr(passWord.trim());
		ModelAndView mv=new ModelAndView();
		List<User> list=usermag.getByUsernameAndPassword(username, password);
		if(list!=null && list.size()==1){
			List<RightMenu> menulist=emnumag.getAll();
			if(menulist!=null)
			mv.addObject("menulist", menulist);
			mv.setViewName("frame/main");
			HttpSession session=request.getSession();
			session.setAttribute(LOGIN_ID, list.get(0).getId());
			if(rempwd!=null){ 
				CookieTool.addCookie(response , "loginName" , username , MAXAGE); 
			    CookieTool.addCookie(response , "loginPwd" , password , MAXAGE);        
			}
		}else{
			mv.addObject("errmassage", "用户名和密码不匹配");
			mv.setViewName("login/Login");
		}
		
		return mv;
	}
	
	/**
	 * 注销
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		//清除session
		session.removeAttribute(LOGIN_ID);
		//清空cookie
		CookieTool.addCookie(response, "loginName", null, 0); 
		CookieTool.addCookie(response, "loginPwd",null, 0);
		mv.setViewName("login/Login");
		return mv;
		
	}
	
	
	
	
}
