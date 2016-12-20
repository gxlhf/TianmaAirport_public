package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.*;
/**
 * Servlet implementation class ModifyFacility
 */
//@WebServlet("/ModifyFacility")
public class ModifyFacility extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyFacility() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("facility-name");
		String site =  request.getParameter("facility-site");
		String phone =  request.getParameter("facility-phone");
		String extra =  request.getParameter("facility-extra");
		String type = request.getParameter("facility-type");
		/*System.out.println(name);
		System.out.println(site);
		System.out.println(phone);
		System.out.println(extra);
		System.out.println(type);*/
		
		PropertyFacility facilityModifyInfo  = new PropertyFacility(name, site, extra, type, phone);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		
		int result = admin.modifyPropertyFacility(facilityModifyInfo);
//		System.out.println(result);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("modifyResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request, response);
		}else {
			request.setAttribute("modifyResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request,response);
		}
		
	}

}
