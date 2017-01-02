package com.servlet.flight;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Admin;
import com.entity.ArrivalFlightInfo;
import com.entity.FlightCourse;
import com.entity.User;

/**
 * Servlet implementation class AddArrivalFlightInfo
 */
//@WebServlet("/AddArrivalFlightInfo")
public class AddArrivalFlightInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArrivalFlightInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		User user = new User();
		ArrivalFlightInfo[] arrivalFlightInfos = user.searchArrivalFlightInfo("", request.getParameter("id"), "", request.getParameter("time"));

		response.getWriter().print(arrivalFlightInfos.length);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		boolean area = false;
//		String area = request.getParameter("flight-area");
		if(request.getParameter("flight-area").equals("true"))
			area= true;
		if(request.getParameter("flight-area").equals("false"))
			area = false;
		String flightNumber = request.getParameter("flight-id");
		String time = request.getParameter("flight-arr-time");
		String from = request.getParameter("flight-from");
		String stop = request.getParameter("flight-via");
		String to = request.getParameter("flight-to");
		String airline = request.getParameter("flight-airline");
		/*String checkinCounter = request.getParameter("flight-counter");
		String boardingGate = request.getParameter("flight-gate");*/
		String luggageCarousel = request.getParameter("flight-baggage");
		FlightCourse arrivalFlightCourse = new FlightCourse(area, true, flightNumber, airline, from, to, stop);
		ArrivalFlightInfo arrivalFlightAddInfo = new ArrivalFlightInfo(arrivalFlightCourse, luggageCarousel, time);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		User user = new User();
  	  	ArrivalFlightInfo[] arrivalFlightValidateInfo = user.searchArrivalFlightInfo("", flightNumber, "", time);
  	  	int result;
  	  	if(arrivalFlightValidateInfo!=null&&arrivalFlightValidateInfo.length!=0)
  	  		result = 0;
  	  	else
  	  		result = admin.addArrivalFlightInfo(arrivalFlightAddInfo);
		if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else {
			request.setAttribute("addResult", result);
			if(area==true)	
				request.getRequestDispatcher("/Public/Flight/ArrivalFlightInfoCheck.jsp?area=international").forward(request,response);
			if(area==false)
				request.getRequestDispatcher("/Public/Flight/ArrivalFlightInfoCheck.jsp?area=local").forward(request,response);
		}
	}

}
