package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;

/**
 * Servlet implementation class SearchAdmin
 */
//@WebServlet("/SearchAdmin")
public class SearchAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAdmin() {
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
		String empno = request.getParameter("search-empno");
		String name = request.getParameter("search-name");
		int sex;
		if(request.getParameter("sex")==null||request.getParameter("sex").equals(""))
			sex = -1;
		else if(request.getParameter("sex").equals("1"))
			sex = 1;
		else
			sex = 0;
//		String sex = request.getParameter("sex");
		String position = request.getParameter("search-position");
		String role = request.getParameter("search-role");
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		Admin[] adminsInfo = admin.searchAdmin(empno, name, sex, position, role);
		if(adminsInfo!=null&&adminsInfo[0].getEmpno().equals("-1"))
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		else{
			request.setAttribute("adminsInfo", adminsInfo);
			request.getRequestDispatcher("User/UserAdmin.jsp").forward(request, response);
		}
	}
}
