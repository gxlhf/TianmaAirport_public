package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.*;

/**
 * Servlet implementation class FacilityDelete
 */
//@WebServlet("/FacilityDelete")
public class FacilityDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacilityDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("fname");
//		System.out.println(name);
		
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		User user = new User();
		PropertyFacility[] facilityModifyInfo = user.searchPropertyFacility(name,"",1);
		int result;
		if(facilityModifyInfo!=null&&facilityModifyInfo.length!=0)
			result = admin.deletePropertyFacility(facilityModifyInfo[0]);
		else
			result = 0;
		
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request, response);
		}else {
			request.setAttribute("deleteResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
