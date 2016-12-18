package com.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.dao.loggingDao;
import com.entity.Admin;

import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class logFilter
 */
@WebFilter("/logFilter")
public class logFilter implements Filter {

    /**
     * Default constructor. 
     */
    public logFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public String getRemortIP(HttpServletRequest request) {

		if (request.getHeader("x-forwarded-for") == null) {

		return request.getRemoteAddr();

		}

		return request.getHeader("x-forwarded-for");

		}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		String ip=getRemortIP(req);
		String action=req.getServletPath();
		
		if(action.endsWith("js")||action.endsWith("css")||action.endsWith("jpg")||action.endsWith("png")||action.endsWith("woff")||action.endsWith("gif")||action.endsWith("ico")){

		}else{
			String userId="";
			if(session.getAttribute("admin")==null){
				userId="visitor";
			}else{
				Admin admin=(Admin)session.getAttribute("admin");
				userId=admin.getEmpno();
			}
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time=format.format(date);
			loggingDao loggingDao=new loggingDao();
			loggingDao.LogAdd(ip, userId, action,time);
		}
		chain.doFilter(request, response);
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
