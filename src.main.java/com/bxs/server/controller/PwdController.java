package com.bxs.server.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bxs.server.manager.impl.RecoverPwdManagerImpl;
import com.bxs.server.manager.impl.UserManagerImpl;
import com.bxs.server.model.RecoverPwd;
import com.bxs.server.model.User;
import com.bxs.server.util.DateFormatUtil;
import com.bxs.server.util.SendEmailUtil;
import com.bxs.server.util.ShortUrlGenerator;

@Controller
@RequestMapping("/pwd")
public class PwdController extends BaseControl{
	
	@Autowired
	private UserManagerImpl usermag;
	@Autowired
	private RecoverPwdManagerImpl recoverPwdmag;
	/**
	 * 找回密码
	 */
	@RequestMapping("/recover")
	public ModelAndView recoverPwd(HttpServletRequest request,@RequestParam String username){
		ModelAndView mv=new ModelAndView();
		if(!stringIsEmpty(username)){
			User user=usermag.getMemberByEmail(username);
			if(user!=null){
				String identity=SendEmailUtil.sendEmail4ChangePassword(username,getBasePath()+"/pwd/modifypwd.do");
				RecoverPwd recoverpwd=new RecoverPwd();
				List<RecoverPwd>recoverpwds=recoverPwdmag.getByUsernameAndStatus(username,0);
				if(recoverpwds!=null){
					for(RecoverPwd rpwd:recoverpwds){
						rpwd.setIsvalid(1);
						recoverPwdmag.dbSave(rpwd);
					}
				}
					
				recoverpwd.setEmail(username);
				recoverpwd.setIdentifycode(identity);
				recoverpwd.setIsvalid(0);
				recoverpwd.setRegisterDate(DateFormatUtil.dateToString());
				recoverPwdmag.dbAdd(recoverpwd);
				mv.addObject("errmassage", "发送成功");
			}else{
				mv.addObject("errmassage", "用户不存在");
			}
			
		}
		mv.setViewName("login/Login");
		return mv;
	}
	
	/**
	 * 找回密码
	 */
	@RequestMapping("/modifypwd")
	public ModelAndView modifypwd(HttpServletRequest request,@RequestParam String identity){
		ModelAndView mv=new ModelAndView();
		if(!stringIsEmpty(identity)){
			RecoverPwd recoverpwd=recoverPwdmag.getByIdentityStatus(identity,0);
			if(recoverpwd!=null){
				recoverpwd.setIsvalid(1);
				recoverPwdmag.dbSave(recoverpwd);
				mv.addObject("email", recoverpwd.getEmail());
				mv.setViewName("login/findbackPwd");
			}else{
				mv.setViewName("error");
			}
		}else{
			mv.setViewName("error");
		}
		return mv;
	}
	
	/**
	 * 设置新密吗
	 */
	@RequestMapping("/setnewpwd")
	public ModelAndView setnewpwd(HttpServletRequest request,@RequestParam String email,@RequestParam String newpassword){
		ModelAndView mv=new ModelAndView();
		if(!stringIsEmpty(email) && !stringIsEmpty(newpassword)){
			User user=usermag.getMemberByUserName(email);
			if(user!=null){
				String password=ShortUrlGenerator.getMD5OfStr(newpassword.trim());
				user.setPassword(password);
				usermag.dbSave(user);
				mv.setViewName("login/Login");
			}else{
				mv.setViewName("error");
			}
		}else{
			mv.setViewName("error");
		}
		return mv;
		
	}
	
}
