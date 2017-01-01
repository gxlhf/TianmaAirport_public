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
import com.servlet.Regex;

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
		String result;
		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		String password = GetMD5.getMd5(request.getParameter("password"));
		String validateC=(String)request.getSession().getAttribute("validateCode");
		String veryCode=request.getParameter("veryCode");
		if(veryCode==null||"".equals(veryCode)){
			result="验证码为空";
			request.setAttribute("result", result);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else{
			if(!validateC.equalsIgnoreCase(veryCode)){
				result="验证码错误";
				request.setAttribute("result", result);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
		Regex regex=new Regex();
		if(!regex.isValid(username)){
			System.out.println("username not valid");
			response.sendRedirect("error.jsp");
		}
		else if(!regex.isValid(password)){
			System.out.println("password not valid");
			response.sendRedirect("error.jsp");
		}else{
			HttpSession session = request.getSession();
			LoginDao loginDao = new LoginDao();
			Admin admin = loginDao.loginCheck(username, password);
			if(admin!=null){
				session.setAttribute("admin", admin);
				session.setAttribute("empno", admin.getEmpno());
				session.setAttribute("priv0", admin.getRole().getAuthorityMap().get("机场设施管理"));
				session.setAttribute("priv1", admin.getRole().getAuthorityMap().get("航班信息管理"));
				session.setAttribute("priv2", admin.getRole().getAuthorityMap().get("新闻管理"));
				session.setAttribute("priv3", admin.getRole().getAuthorityMap().get("角色管理"));
				session.setAttribute("priv4", admin.getRole().getAuthorityMap().get("用户管理"));
				request.getRequestDispatcher("index.jsp").forward(request, response);		
			}else{
				result ="员工号或密码不正确";
				request.setAttribute("result", result);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
	}

}
