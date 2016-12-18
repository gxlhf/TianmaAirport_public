package com.servlet.news;

import java.io.IOException;
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
		String title = request.getParameter("news-title");
		String time = request.getParameter("news-outtime");
		String type=request.getParameter("type");
		String name=request.getParameter("news-name");
		com.entity.User user = new com.entity.User();
		News[] news=user.searchNews(title, time);
		News[] news2=new News[30];
		if(name!=null&&!name.equals("")){
			int a=0;
			for(int i=0;i<news.length;i++){
				if(news[i].getPublisher_name().equals(name)){
					news2[a++]=news[i];
				}
			}
			news=news2;
			/*for(ArrivalFlightInfo out:arrivalFlightInfos)
			{
			System.out.println(out.getFlightCourse().getAirline());;
			}*/
		}		
		News[] news3=new News[30];
		if(time!=null&&!time.equals("")){
			int a=0;
			for(int i=0;i<news.length;i++){
				if(news[i]!=null){
				if(news[i].getTime().equals(time)){
					news3[a++]=news[i];
				}
			}
			}
			news=news3;
		}
		request.setAttribute("news", news);
		request.getRequestDispatcher("NewsList.jsp?type="+type).forward(request, response);
	}
}
