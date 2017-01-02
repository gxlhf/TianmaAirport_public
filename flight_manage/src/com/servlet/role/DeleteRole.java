package com.servlet.role;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.Role;

/**
 * Servlet implementation class DeleteRole
 */
//@WebServlet("/DeleteRole")
public class DeleteRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String roleName = request.getParameter("roleName");
		Role roleDeleteInfo = new Role(roleName);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int result;
		if(admin.searchRole(roleName)!=null)
			result = admin.deleteRole(roleDeleteInfo);
		else
			result = 0;
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("/Role/RoleAdmin.jsp").forward(request, response);
		}else {
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("/Role/RoleAdmin.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
