package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.*;
/**
 * Servlet implementation class AddFacility
 */
//@WebServlet("/AddFacility")
public class AddFacility extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFacility() {
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
		
		PropertyFacility facilityAddInfo = new PropertyFacility(name, site, extra, type, phone);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		
		int result = admin.addPropertyFacility(facilityAddInfo);
		if(result==-1){
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}else if(result==1){
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request, response);
		}else {
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("Public/Facility/Facility.jsp").forward(request,response);
		}
	}

}
