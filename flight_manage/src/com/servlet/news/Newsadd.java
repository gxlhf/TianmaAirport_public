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

/**
 * Servlet implementation class Newsadd
 */
//WebServlet("/Newsadd")
public class Newsadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newsadd() {
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
		request.setCharacterEncoding("UTF-8");
		String title=request.getParameter("news-title");
		String classified=request.getParameter("type");
		String context=request.getParameter("news-context");
//		String id=request.getParameter("id");
		HttpSession session=request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		News news=new News(title, df.format(new Date()), context, classified, null,admin.getEmpno());
		int re=admin.addNews(news);
		if(re==-1){
			response.sendRedirect("error.jsp");
			return;
		}else {
			request.setAttribute("result", re);
			request.setAttribute("forward", "add");
			if(classified.equals("航班信息"))
				request.setAttribute("type", "flightInformation");
			if(classified.equals("机场资源"))
				request.setAttribute("type","airportResource");
			if(classified.equals("物业资源"))
				request.setAttribute("type","facilityResource");
			request.getRequestDispatcher("/Public/News/result.jsp").forward(request,response);
		}
	}



	
}
