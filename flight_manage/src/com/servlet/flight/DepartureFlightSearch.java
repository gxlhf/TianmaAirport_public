package com.servlet.flight;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.*;
/**
 * Servlet implementation class DepartureFlightSearch
 */
//@WebServlet("/DepartureFlightSearch")
public class DepartureFlightSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartureFlightSearch() {
        super();
        // TODO Auto-generated constructor stub   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String flightNumber = request.getParameter("flight-id");
		String to = request.getParameter("to-site");
		String airline = request.getParameter("airCompany-name");
		String area = request.getParameter("area");
		com.entity.User user = new com.entity.User();
		DepartureFlightInfo[] departureFlightInfos = user.searchDepartureFlightInfo(to, flightNumber, airline, "");
		request.setAttribute("departureFlightInfos", departureFlightInfos);
		/*for(DepartureFlightInfo info:departureFlightInfos)
		{
			if(info!=null)
			{
				for(String output:info.getCheckinCounter())
				{
					if(output.equals(""))
						System.out.println(output);
				}
			}
			
				
		}*/
		request.getRequestDispatcher("Public/Flight/DepartureFlightInfoCheck.jsp"+"?area="+area).forward(request, response);
	}

}
