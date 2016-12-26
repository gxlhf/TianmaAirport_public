package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import com.entity.*;

/**
 * Servlet implementation class PassengerGuide
 */
//@WebServlet("/PassengerGuide")
public class PassengerGuide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengerGuide() {
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
		String key = request.getParameter("key");
		String is_flightNo =request.getParameter("is_flightNo");
		String flight_type = request.getParameter("flight_type");
		/*System.out.println(key);
		System.out.println(is_flightNo);
		System.out.println(flight_type);*/
		User user = new User();
		DepartureFlightInfo[] departureFlightInfos = null;
		ArrivalFlightInfo[] arrivalFlightInfos = null;
		if(flight_type.equals("departure"))
		{
			if(is_flightNo.equals("true"))
				departureFlightInfos = user.searchDepartureFlightInfo("", key, "", "");
			else
				departureFlightInfos = user.searchDepartureFlightInfo(key, "", "", "");
			/*for(DepartureFlightInfo output:departureFlightInfos)
			{
				if(output!=null)
				{
					System.out.println(output.getTime());
				}
			}*/
			request.setAttribute("departureFlightInfos", departureFlightInfos);
			request.getRequestDispatcher("Public/PassengerGuide.jsp").forward(request, response);
		}
		else if(flight_type.equals("arrival"))
		{
			if(key.equals(""))
			{
				ArrivalFlightInfo[] allLocalArrivalInfos = user.returnAllLocalArrivalFlightInfo();
				ArrivalFlightInfo[] allInternationalArrivalInfos = user.returnAllInternationalArrivalFlightInfo();
				arrivalFlightInfos = new ArrivalFlightInfo[allLocalArrivalInfos.length+allInternationalArrivalInfos.length];
				System.arraycopy(allLocalArrivalInfos, 0, arrivalFlightInfos, 0, allLocalArrivalInfos.length);
				System.arraycopy(allInternationalArrivalInfos, 0, arrivalFlightInfos, allLocalArrivalInfos.length, allInternationalArrivalInfos.length);
				request.setAttribute("arrivalFlightInfos", arrivalFlightInfos);
				request.getRequestDispatcher("Public/PassengerGuide.jsp").forward(request, response);
			}
			else
			{
				if(is_flightNo.equals("true"))
					arrivalFlightInfos = user.searchArrivalFlightInfo("", key, "", "");
				else
					arrivalFlightInfos = user.searchArrivalFlightInfo(key, "", "", "");
				request.setAttribute("arrivalFlightInfos", arrivalFlightInfos);
				request.getRequestDispatcher("Public/PassengerGuide.jsp").forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
