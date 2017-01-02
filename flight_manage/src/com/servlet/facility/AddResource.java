package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.*;
/**
 * Servlet implementation class AddResource
 */
//@WebServlet("/AddResource")
public class AddResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddResource() {
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
		User user = new User();
		AirportResource[] airportResources = user.searchAirportResource(request.getParameter("resource-name"), "");
		if(airportResources.length == 0)
			response.setStatus(200);
		else 
			response.setStatus(400);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("resource-name");
		String type = request.getParameter("resource-type");
		String site = request.getParameter("resource-site");
		String extra = request.getParameter("resource-extra");
		
		AirportResource resourceAddInfo = new AirportResource(name, site, extra, type);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		User user = new User();
	    AirportResource[] resourceValidateInfo = user.searchAirportResource(name, "");
	    int result;
	    if(resourceValidateInfo!=null&&resourceValidateInfo.length!=0)
	    	result = 0;
	    else
	    	result = admin.addAirportResource(resourceAddInfo);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else if(result==1){
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request, response);
		}else {
			request.setAttribute("addResult", result);
			request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request,response);
		}
		
	}

}
