package com.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.OverlayLayout;

import com.entity.Admin;

/**
 * Servlet implementation class DeleteAdmin
 */
//@WebServlet("/DeleteAdmin")
public class DeleteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println("ss");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("empno"));
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		Admin[] adminModifyInfo = admin.searchAdmin(request.getParameter("empno"), "", -1, "", "");
//		System.out.println(adminModifyInfo[0].getEmail());
		int result;
		if(adminModifyInfo!=null&&adminModifyInfo.length!=0)
			result = admin.deleteAdmin(adminModifyInfo[0]);
		else
			result = 0;
//		System.out.println(result);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request, response);
		}else {
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("/User/UserAdmin.jsp").forward(request,response);
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
