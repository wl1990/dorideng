package com.bxs.server.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class LoginFilter implements Filter{
	private String sessionKey;
	private Pattern excepUrlPattern;
	private String forwardUrl;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// 如果 sessionKey 为空，则直接放行
        if (StringUtils.isBlank(sessionKey)) {
            chain.doFilter(req, res);
            return;
        }
		 // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) res;
        HttpSession session = servletRequest.getSession();
		String servletPath=servletRequest.getServletPath();
		
		// 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
		if(servletPath.equals(forwardUrl) || excepUrlPattern.matcher(servletPath).matches()){
			chain.doFilter(req, res);
			return;
		}
		Object sessionObj = servletRequest.getSession().getAttribute(sessionKey);
		if(sessionObj==null){
			RequestDispatcher dispacher=servletRequest.getRequestDispatcher(forwardUrl);
			dispacher.forward(req, res);
			return;
		}else{
			chain.doFilter(req, res);
			return;
		}
		
		
		
		
        
		
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		
		sessionKey=cfg.getInitParameter("sessionKey");
		String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if(!StringUtils.isBlank(excepUrlRegex)){
			excepUrlPattern=Pattern.compile(excepUrlRegex);
		}
		forwardUrl = cfg.getInitParameter("redirectUrl");
	}
	

}
