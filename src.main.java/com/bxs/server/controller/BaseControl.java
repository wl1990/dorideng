package com.bxs.server.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bxs.server.model.User;
import com.bxs.server.util.UploadFile;

public class BaseControl {
	protected static final ResourceBundle rb = ResourceBundle.getBundle("system");
	protected static final String LOGIN_ID="userInfo"; 
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected ServletContext context;
	protected static final User user=null;
	protected int size=8;
	
	
	public User getLoginUser(){
		HttpSession session=request.getSession(false);
		return (User) session.getAttribute(LOGIN_ID);
	}
	public String getRootPath(){
		return request.getContextPath();  
	}
	
	public String getBasePath(){
		return "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}
	
	/**
	 * 判断手机号是否合法
	 * 
	 * @param telNum
	 * @return
	 */
	public  boolean isMobiPhoneNum(String telNum) {
		String regex = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(telNum);
		return m.matches();
	}
	
	/**
	 * 判断邮箱是否合法
	 */
	public boolean isEmail(String email){
		String regex="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-]+)(.[a-zA-Z0-9_-])+";
		Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher m=p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 判断string是否为空
	 * @param s
	 * @return
	 */
	public boolean stringIsEmpty(String s){
		if(s==null || s.trim().isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * 图片上传
	 */
	protected String mulfileUpload(HttpServletRequest request){
		String imageurl="";
		 //创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		  //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
        	 //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile mulfile = multiRequest.getFile(iter.next());
					if(mulfile!=null && !stringIsEmpty(mulfile.getOriginalFilename())){
						try {
							 imageurl=UploadFile.uploadFiles(mulfile,request.getSession().getServletContext());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
            }
        }
        return imageurl;
	}
	
}
