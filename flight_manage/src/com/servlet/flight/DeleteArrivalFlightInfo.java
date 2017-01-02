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
 * Servlet implementation class DeleteArrivalFlightInfo
 */
//@WebServlet("/DeleteArrivalFlightInfo")
public class DeleteArrivalFlightInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArrivalFlightInfo() {
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
		String[] t1 = request.getParameter("time").split(":");
  	  	String time = t1[0]+":"+t1[1];
  	  	User user = new User();
//  	ArrivalFlightInfo[] arrivalFlightModifyInfo = null;
  	  	ArrivalFlightInfo[] arrivalFlightDeleteInfo = user.searchArrivalFlightInfo("", request.getParameter("flightNumber"), "", time);
  	  	Admin admin=(Admin)request.getSession().getAttribute("admin");
  	  	int result;
  	  	if(arrivalFlightDeleteInfo!=null&&arrivalFlightDeleteInfo.length!=0)
  	  		result = admin.deleteArrivalFlightInfo(arrivalFlightDeleteInfo[0]);
  	  	else
  	  		result = 0;
  	  	if(result==-1){
			response.sendRedirect("error.jsp");
			return;
		}else {
			request.setAttribute("deleteResult", result);
			if(arrivalFlightDeleteInfo!=null&&arrivalFlightDeleteInfo.length!=0&&arrivalFlightDeleteInfo[0].getFlightCourse().isInternationalOrLocal()==true)	
				request.getRequestDispatcher("/Public/Flight/ArrivalFlightInfoCheck.jsp?area=international").forward(request,response);
			if(arrivalFlightDeleteInfo!=null&&arrivalFlightDeleteInfo.length!=0&&arrivalFlightDeleteInfo[0].getFlightCourse().isInternationalOrLocal()==false)
				request.getRequestDispatcher("/Public/Flight/ArrivalFlightInfoCheck.jsp?area=local").forward(request,response);
			else
				request.getRequestDispatcher("/Public/Flight/ArrivalFlightInfoCheck.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
	}

}
