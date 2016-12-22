package com.servlet.user;

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
		request.setCharacterEncoding("UTF-8");
		String empno = request.getParameter("search-empno");
		String name = request.getParameter("search-name");
		int sex;
		if(request.getParameter("search-sex")==null||request.getParameter("search-sex").equals(""))
			sex = -1;
		else if(request.getParameter("search-sex").equals("1"))
			sex = 1;
		else
			sex = 0;
		String position = request.getParameter("search-position");
		String role = request.getParameter("search-role");
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		Admin[] adminsInfo = admin.searchAdmin(empno, name, sex, position, role);
		/*System.out.println(empno);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(position);
		System.out.println(role);
		for(Admin output:adminsInfo)
			System.out.println(output.getEmpno());*/
		/*if(adminsInfo.length==0){
			request.setAttribute("adminsInfo", adminsInfo);
			request.getRequestDispatcher("User/UserAdmin.jsp").forward(request, response);
		}*/	
		if(adminsInfo.length!=0&&adminsInfo[0].getEmpno().equals("-1"))
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		else{
			request.setAttribute("adminsInfo", adminsInfo);
			request.getRequestDispatcher("User/UserAdmin.jsp").forward(request, response);
		}
	}
}
