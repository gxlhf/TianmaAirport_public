package com.servlet.flight;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.DepartureFlightInfo;
import com.entity.FlightCourse;

/**
 * Servlet implementation class AddDepartureFlightInfo
 */
//@WebServlet("/AddDepartureFlightInfo")
public class AddDepartureFlightInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDepartureFlightInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		boolean area = false;
		if(request.getParameter("flight-area").equals("true"))
			area= true;
		if(request.getParameter("flight-area").equals("false"))
			area = false;
		String flightNumber = request.getParameter("flight-id");
		String time = request.getParameter("flight-dep-time");
		String from = request.getParameter("flight-from");
		String stop = request.getParameter("flight-via");
		String to = request.getParameter("flight-to");
		String airline = request.getParameter("flight-airline");
		String checkinCounter = request.getParameter("flight-counter");
		String boardingGate = request.getParameter("flight-gate");
		FlightCourse departureFlightCourse = new FlightCourse(area, false, flightNumber, airline, from, to, stop);
		String[] checkinCounterArray = checkinCounter.split(", ");
		DepartureFlightInfo departureFlightAddInfo = new DepartureFlightInfo(departureFlightCourse, checkinCounterArray, boardingGate, time);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int result = admin.addDepartureFlightInfo(departureFlightAddInfo);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else {
			request.setAttribute("addResult", result);
			if(area==true)	
				request.getRequestDispatcher("/Public/Flight/DepartureFlightInfoCheck.jsp?area=international").forward(request,response);
			if(area==false)
				request.getRequestDispatcher("/Public/Flight/DepartureFlightInfoCheck.jsp?area=local").forward(request,response);
		}
	}

}
