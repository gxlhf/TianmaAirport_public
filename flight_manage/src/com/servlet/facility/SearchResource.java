package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.entity.*;

/**
 * Servlet implementation class SearchResource
 */
//@WebServlet("/SearchResource")
public class SearchResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	//	response.setContentType("text/html");
		String name = request.getParameter("resource-name");
		String type = request.getParameter("resource-type");
		//System.out.println(name);
		String t="";
		if(type.equals("1"))
			t = "登机门";
		if(type.equals("2"))
			t = "值机柜台";
		if(type.equals("3"))
			t = "行李转盘";
		User user = new User();
		
		AirportResource[] resourceInfo = user.searchAirportResource(name, t);
	  
		
			request.setAttribute("resourceInfo", resourceInfo);
			
			/*if(resourceInfo==null)
			{
				System.out.println("空指针！！！");
			}*/
			   
			request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request, response);
		
	}
}
