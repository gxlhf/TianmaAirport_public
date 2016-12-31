package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.entity.Admin;
import com.entity.Role;

/**
 * Servlet implementation class EditMyInfo
 */
//@WebServlet("/EditMyInfo")
public class EditMyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMyInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String empno = request.getParameter("user-id");
		String empnoValidate = (String)request.getSession().getAttribute("empnoValidate");
		String name = request.getParameter("user-name");
		int sex;
		if(request.getParameter("user-sex").equals("男"))
			sex = 1;
		else
			sex = 0;
		String department = request.getParameter("user-dep");
		String position = request.getParameter("user-pos");
		String roleName = request.getParameter("user-role");
		String roleValidate = (String)request.getSession().getAttribute("roleValidate");
		String phone = request.getParameter("user-phone");
		String mobile = request.getParameter("user-tel");
		String email = request.getParameter("user-email");
		String password = "";
		if(!empno.equals(empnoValidate)||!roleName.equals(roleValidate))
			response.sendRedirect("error.jsp");
		else
		{
			AdminDao adminDao = new AdminDao();
			if(request.getParameter("user-password").equals(""))
			{
				Admin[] adminOriginInfo = adminDao.searchAdmin0(empno);
				password = adminOriginInfo[0].getPassword();
			}
			else
				password = GetMD5.getMd5(request.getParameter("user-password"));
			Role roleModifyInfo = new Role(roleName);
			Admin adminModifyInfo = new Admin(empno, name, sex, email, roleModifyInfo, mobile, phone, department, position, password);
			boolean result = adminDao.modifyAdmin(adminModifyInfo);
			if(result==true)
				request.getSession().setAttribute("admin", adminModifyInfo);
			request.setAttribute("modifyResult", result);
			request.getRequestDispatcher("/EditMyInfo.jsp").forward(request,response);
			
		}
		
	}

}
