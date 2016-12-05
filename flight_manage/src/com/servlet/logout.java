package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logout
 */
//@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		//防止创建Session  
        if(request.getSession(false) == null){  
            response.sendRedirect("index.jsp");  
            return;  
        }
        /*request.removeAttribute("admin");
        request.removeAttribute("priv0");
        request.removeAttribute("priv1");
        request.removeAttribute("priv2");
        request.removeAttribute("priv3");
        request.removeAttribute("priv4");*/
        request.getSession().removeAttribute("admin");
        request.getSession().removeAttribute("priv0");
        request.getSession().removeAttribute("priv1");
        request.getSession().removeAttribute("priv2");
        request.getSession().removeAttribute("priv3");
        request.getSession().removeAttribute("priv4");
        response.sendRedirect("login.jsp"); 
	}
}
