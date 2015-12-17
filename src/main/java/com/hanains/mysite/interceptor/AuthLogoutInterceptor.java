package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if( session != null ){
			session.removeAttribute("authUser");
			session.invalidate();
		}	
		response.getWriter().println("<script> alert('로그아웃 되었습니다'); </script>");
		response.sendRedirect(request.getContextPath());
		return false;
	}
	
}
