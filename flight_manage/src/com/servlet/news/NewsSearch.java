package com.servlet.news;

import java.awt.List;
import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.ArrivalFlightInfo;
import com.entity.News;

/**
 * Servlet implementation class NewsSearch
 */
//@WebServlet("/NewsSearch")
public class NewsSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsSearch() {
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
		String title = request.getParameter("news-title");
		String time = request.getParameter("news-outtime");
		String type=request.getParameter("type");
		String name=request.getParameter("news-name");
		com.entity.User user = new com.entity.User();
		News[] news=user.searchNews(title, time);
		java.util.List<News> newslist=new LinkedList<News>();
		int n=0,t=0;
		if(name!=null&&!name.equals("")){
			n=1;
			for(int i=0;i<news.length;i++){
				if(news[i].getPublisher_name().equals(name)){
					newslist.add(news[i]);
				}
			}
			/*for(ArrivalFlightInfo out:arrivalFlightInfos)
			{
			System.out.println(out.getFlightCourse().getAirline());;
			}*/
		}		
		if(time!=null&&!time.equals("")){
			t=1;
			if(n==0){
				for(int i=0;i<news.length;i++){
					if(news[i].getTime().equals(name)){
						newslist.add(news[i]);
					}
				}
			}else{
				for(int i=0;i<newslist.size();i++){
					if(!newslist.get(i).getTime().equals(name)){
						newslist.remove(i--);
					}
				}
			}

		}
		if(t==0&&n==0){
			for(int i=0;i<news.length;i++){
				if(news[i].getKind().equals("航班信息")&&type.equals("flightInformation")||news[i].getKind().equals("机场资源")&&type.equals("airportResource")||news[i].getKind().equals("物业资源")&&type.equals("facilityResource")){
					newslist.add(news[i]);
				}
			}
		}else{
			for(int i=0;i<newslist.size();i++){
				if(!(newslist.get(i).getKind().equals("航班信息")&&type.equals("flightInformation"))||!(newslist.get(i).getKind().equals("机场资源")&&type.equals("airportResource"))||!(newslist.get(i).getKind().equals("物业资源")&&type.equals("facilityResource"))){
					newslist.remove(i--);
				}
			}
		}
		int size=newslist.size();
		News[] news2=(News[])newslist.toArray(new News[size]);
		request.setAttribute("news", news2);
		request.getRequestDispatcher("NewsList.jsp?type="+type).forward(request, response);
	}
}
