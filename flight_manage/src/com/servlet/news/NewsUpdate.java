package com.servlet.news;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Admin;
import com.entity.News;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

/**
 * Servlet implementation class NewsUpdate
 */
//WebServlet("/NewsUpdate")
public class NewsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processrequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processrequest(request, response);
	}
	
	void processrequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("news-title");
		String classified=request.getParameter("type");
		String context=request.getParameter("news-context");
		String id=request.getParameter("id");
		HttpSession session=request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(title+classified+id+context);
		News news=new News(title, df.format(new Date()), context, classified, null,admin.getEmpno() , id);
		int re=admin.modifyNews(news);
		if(re==-1){
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else if(re==1){
			request.setAttribute("result", re);
			if(classified.equals("机场介绍")){
				request.getRequestDispatcher("/Public/News/Intro.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("/Public/News/NewsList.jsp").forward(request, response);
		}else {
			session.setAttribute("result", re);
			if(classified.equals("机场介绍")){
				request.getRequestDispatcher("/Public/News/Intro.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("/Public/News/NewsList.jsp").forward(request, response);
		}
	}

}
