package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import com.entity.Admin;
import com.dao.*;


/**
 * Servlet implementation class login
 */
//@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		LoginDao loginDao = new LoginDao();
		Admin admin = loginDao.loginCheck(username, password);
		System.out.println("aa");
		int result;
		if(username.equals("aaa") && password.equals("123")){
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			result = -1;
			request.setAttribute("result", result);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}
		/*if(admin!=null){
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}else{
			result = -1;
			request.setAttribute("result", result);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}*/

		
	}

}
