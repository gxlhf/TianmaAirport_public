package com.servlet.flight;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.entity.Admin;
import com.entity.ArrivalFlightInfo;
import com.entity.DepartureFlightInfo;
import com.entity.FlightCourse;
import com.entity.User;

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
		request.setCharacterEncoding("UTF-8");
		User user = new User();
		DepartureFlightInfo[] departureFlightInfos = null;
		int ret = 1; //0:通过验证   >0:验证未通过
		if(request.getParameter("id") != null){  //当get传入参数有id时，查询在同一时间有无相同的航班号
			departureFlightInfos = user.searchDepartureFlightInfo("", request.getParameter("id"), "", request.getParameter("time"));
			ret = departureFlightInfos.length;
		}
		else{  //当get传入参数无id时，查询同一时间同一登机门是否被占用，登机门以参数gate传入
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long oneMin = 1000 * 60 * 1;
			ret = 0;
			int temp;
			try {
				Date inDate = format.parse(request.getParameter("time"));
				inDate.setTime(inDate.getTime() - 6 * oneMin);
				for(int i = 0; i < 11; i++){  //使用同一个登机门的两个航班之间至少间隔5分钟
					inDate.setTime(inDate.getTime() + oneMin);
					temp = user.searchDepartureFlightInfo(request.getParameter("gate"), format.format(inDate));
					if(temp == -1){
						ret = -10;
						break;
					}
					ret += temp;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = 1;
			}
		}
		
		response.getWriter().print(ret); //departureFlightInfos.length
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		User user = new User();
  	  	DepartureFlightInfo[] departureFlightValidateInfo = user.searchDepartureFlightInfo("", flightNumber, "", time);
  	  	int result;
  	  	if(departureFlightValidateInfo!=null&&departureFlightValidateInfo.length!=0)
  	  		result = 0;
  	  	else	
  	  		result = admin.addDepartureFlightInfo(departureFlightAddInfo);
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
