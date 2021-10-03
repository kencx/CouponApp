package com.fdm.coupon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/coupons"})
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		boolean authenticated = false;
		HttpSession session = req.getSession(false);
		Long currentUserName = (Long) session.getAttribute("userId");

		if (currentUserName != null && session != null) {
			authenticated = true;
		}
		
		if (authenticated) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/index");
		}	
	}

	@Override
	public void destroy() {
		
	}

}