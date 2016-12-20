package com.servlet.role;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.Role;

/**
 * Servlet implementation class AddRole
 */
//@WebServlet("/AddRole")
public class AddRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String rolePriv = request.getParameter("rolePriv");
		String[] rolePrivInfo = rolePriv.split(",");
		Map<String, Boolean> authorityMap = new HashMap();
		for(String Privoutput:rolePrivInfo)
		{
			authorityMap.put(Privoutput, true);
		}
		Role roleAddInfo = new Role(roleName, roleDesc, authorityMap);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int result = admin.addRole(roleAddInfo);
		/*System.out.println(roleName);
		System.out.println(roleDesc);
		System.out.println(rolePriv);*/
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("/Role/RoleAdmin.jsp").forward(request, response);
		}else {
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("/Role/RoleAdmin.jsp").forward(request,response);
		}
	}

}
