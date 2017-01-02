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
 * Servlet implementation class AddAdmin
 */
//@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		Admin[] result = admin.searchAdmin(request.getParameter("user-id"), "", -1, "", "");
		if(result.length == 0)
			response.setStatus(200);
		else 
			response.setStatus(400);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String password = GetMD5.getMd5(request.getParameter("user-password"));
		Role roleAddInfo = new Role(roleName);
		Admin adminAddInfo = new Admin(empno, name, sex, email, roleAddInfo, mobile, phone, department, position, password);
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
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		Admin[] adminValidateInfo = admin.searchAdmin(empno, "", -1, "", "");
		int result;
		if(adminValidateInfo!=null&&adminValidateInfo.length!=0)
			result = 0;
		else
			result = admin.addAdmin(adminAddInfo);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request, response);
		}else {
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request,response);
		}
	}

}
