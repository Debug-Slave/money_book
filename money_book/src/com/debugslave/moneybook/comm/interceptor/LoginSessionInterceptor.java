package com.debugslave.moneybook.comm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginSessionInterceptor extends HandlerInterceptorAdapter {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userSession");
        
        if ( obj == null ){
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<script>");
			out.println("alert('로그인이 필요한 페이지입니다.');");
			out.println("location.href='/intro';");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
        	
            return false; 
        }
        
        return true;
    }

	
}
