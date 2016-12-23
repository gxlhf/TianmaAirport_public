package com.servlet.flight;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.*;
/**
 * Servlet implementation class ArrivalFlightSearch
 */
//@WebServlet("/ArrivalFlightSearch")
public class ArrivalFlightSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArrivalFlightSearch() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		String flightNumber = request.getParameter("flight-id");
		String from = request.getParameter("from-site");
		String airline = request.getParameter("airCompany-name");
		String area = request.getParameter("area");
		com.entity.User user = new com.entity.User();
		ArrivalFlightInfo[] arrivalFlightInfos = user.searchArrivalFlightInfo(from,flightNumber,airline,"");
		request.setAttribute("arrivalFlightInfos", arrivalFlightInfos);
		/*for(ArrivalFlightInfo out:arrivalFlightInfos)
		{
			System.out.println(out.getFlightCourse().getAirline());;
		}*/
		request.getRequestDispatcher("Public/Flight/ArrivalFlightInfoCheck.jsp"+"?area="+area).forward(request, response);
	}

}
