package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import com.entity.Admin;
import com.entity.Role;
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
		response.sendRedirect("/flight_manage/login.jsp");
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
		String result;
		/*if(username.equals("aaa") && password.equals("123")){
		//	request.setAttribute("admin", admin);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			if(admin.getRole().getAuthorityMap().get("用户管理")){
				request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("角色管理")){
				request.getRequestDispatcher("/Role/RoleAdmin.jsp").forward(request,response );
			}else if(admin.getRole().getAuthorityMap().get("航班信息管理")){
				request.getRequestDispatcher("/Flight/FlightInfoCheck.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("机场设施管理")){
				request.getRequestDispatcher("/Facility/Resouce.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("新闻管理")){
				request.getRequestDispatcher("/News/Intro.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}else{
			result ="登陆失败，请重新登陆";
			request.setAttribute("result", result);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}*/
		if(admin!=null){
			request.setAttribute("admin", admin);
//			System.out.println(admin.getRole().getName());
			if(admin.getRole().getAuthorityMap().get("用户管理")!=null&&admin.getRole().getAuthorityMap().get("用户管理") == true){
				request.getRequestDispatcher("User/UserAdmin.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("角色管理")!=null&&admin.getRole().getAuthorityMap().get("角色管理") == true){
				request.getRequestDispatcher("Role/RoleAdmin.jsp").forward(request,response );
			}else if(admin.getRole().getAuthorityMap().get("航班信息管理")!=null&&admin.getRole().getAuthorityMap().get("航班信息管理") == true){
				request.getRequestDispatcher("Flight/FlightInfoCheck.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("机场设施管理")!=null&&admin.getRole().getAuthorityMap().get("机场设施管理") == true){
				request.getRequestDispatcher("Facility/Resouce.jsp").forward(request, response);
			}else if(admin.getRole().getAuthorityMap().get("新闻管理")!=null&&admin.getRole().getAuthorityMap().get("新闻管理") == true){
				request.getRequestDispatcher("News/Intro.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		}else{
			result ="登陆失败，请重新登陆";
			request.setAttribute("result", result);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}

		
	}

}
