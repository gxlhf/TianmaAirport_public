package com.servlet.facility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.*;
/**
 * Servlet implementation class ModifyResource
 */
//@WebServlet("/ModifyResource")
public class ModifyResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyResource() {
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
	//	doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("resource-name");
		String type = request.getParameter("resource-type");
		String site = request.getParameter("resource-site");
		String extra = request.getParameter("resource-extra");
		
		/*System.out.println(name);
		System.out.println(type);
		System.out.println(site);
		System.out.println(extra);*/
		
		AirportResource resourceModifyInfo = new AirportResource(name, site, extra, type);
		 Admin admin=(Admin)request.getSession().getAttribute("admin");
		    int result = admin.modifyAirportResource(resourceModifyInfo);
			
			if(result==-1){
				response.sendRedirect("error.jsp");
				return;
			}else if(result==1){
				request.setAttribute("modifyResult", result);
				request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request, response);
			}else {
				request.setAttribute("modifyResult", result);
				request.getRequestDispatcher("Public/Facility/Resource.jsp").forward(request,response);
			}
	}

}
