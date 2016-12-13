package com.servlet;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.entity.Admin;


/**
 * Servlet Filter implementation class PrivFilter
 */
public class PrivFilter implements Filter {
	private FilterConfig filterConfig = null;
	private static final String[] dir = { "/Facility",//
		"/Flight",//
		"/News",//
		"/Role",
		"/User"
	};
    /**
     * Default constructor. 
     */
    public PrivFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//System.out.println("start");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = (req).getSession();
		if(session.getAttribute("admin")==null){
//			System.out.println("admin is null in privfilter");
			session.removeAttribute("empno");
			session.removeAttribute("priv0");
			session.removeAttribute("priv1");
			session.removeAttribute("priv2");
			session.removeAttribute("priv3");
			session.removeAttribute("priv4");
		}
		String user=(String) session.getAttribute("empno");
		Boolean[] priv={(Boolean) session.getAttribute("priv0"),(Boolean) session.getAttribute("priv1"),(Boolean) session.getAttribute("priv2"),(Boolean) session.getAttribute("priv3"),(Boolean) session.getAttribute("priv4")};
		String url = req.getRequestURI().substring(
				req.getContextPath().length());
		for(int i=0;i<5;i++){
		if(url.startsWith(dir[i])){
			if(user==null||session.getAttribute("admin")==null){
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else if(priv[i]==null||!priv[i]){
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}

}
