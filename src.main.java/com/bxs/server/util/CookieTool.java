package com.bxs.server.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTool {
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name cookie 名字
	 * @param value cookie 值
	 * @param maxAge cookie 生命周期 单位秒
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		Cookie cookie=new Cookie(name,value);
		cookie.setPath("/");
		if(maxAge>0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap=readCookieMap(request);
		if(cookieMap.containsKey(name)){
			Cookie cookie=cookieMap.get(name);
			return cookie;
		}else
			return null;
	}
	
	private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
		Map<String,Cookie> cookieMap=new HashMap<String,Cookie>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
}
