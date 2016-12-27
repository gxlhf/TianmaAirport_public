package com.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.Role;
import com.servlet.GetMD5;

/**
 * Servlet implementation class ModifyAdmin
 */
//@WebServlet("/ModifyAdmin")
public class ModifyAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAdmin() {
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
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String empno = request.getParameter("user-id");
		String name = request.getParameter("user-name");
		int sex;
		if(request.getParameter("user-sex").equals("男"))
			sex = 1;
		else
			sex = 0;
		String department = request.getParameter("user-dep");
		String position = request.getParameter("user-pos");
		String roleName = request.getParameter("user-role");
		String phone = request.getParameter("user-phone");
		String mobile = request.getParameter("user-tel");
		String email = request.getParameter("user-email");
		String password = "";
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		if(request.getParameter("user-password").equals(""))
		{
			Admin[] adminOriginInfo = admin.searchAdmin(empno, "", -1, "", "");
			password = adminOriginInfo[0].getPassword();
		}
		else
			password = GetMD5.getMd5(request.getParameter("user-password"));
//			password = request.getParameter("user-password");
		Role roleModifyInfo = new Role(roleName);
		Admin adminModifyInfo = new Admin(empno, name, sex, email, roleModifyInfo, mobile, phone, department, position, password);
		/*System.out.println(empno);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(department);
		System.out.println(position);
		System.out.println(roleName);
		System.out.println(mobile);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(password);*/
		int result = admin.modifyAdmin(adminModifyInfo);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("modifyResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request, response);
		}else {
			request.setAttribute("modifyResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request,response);
		}
	}

}
