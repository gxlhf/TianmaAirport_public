package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.AirportResource;
import com.entity.User;

/**
 * Servlet implementation class ResourceDelete
 */
//@WebServlet("/ResourceDelete")
public class ResourceDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("rname");
		//String type = request.getParameter("resource-type");
		//System.out.println(name);
		
		User user = new User();
	    AirportResource[] resourceModifyInfo = user.searchAirportResource(name, "");
	   
	    Admin admin=(Admin)request.getSession().getAttribute("admin");
	    int result;
		if(resourceModifyInfo!=null&&resourceModifyInfo.length!=0)
			result = admin.deleteAirportResource(resourceModifyInfo[0]);
		else
			result = 0;
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request, response);
		}else {
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
	}

}
