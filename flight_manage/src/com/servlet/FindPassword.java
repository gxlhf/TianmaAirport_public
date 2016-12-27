package com.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.dao.AdminDao;
import com.entity.Admin;
import com.servlet.mail.Mail;
import com.servlet.mail.MailUtil;

/**
 * Servlet implementation class FindPassword
 */
//WebServlet("/FindPassword")
public class FindPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processrequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processrequest(request,response);
	}
	
	private void processrequest(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String userid=request.getParameter("userid");
		String email=request.getParameter("email");
		AdminDao adminDao=new AdminDao();
		Admin[] admin=adminDao.searchAdmin0(userid);
		if(admin.length==0){
			String message="未输入员工号";
			request.setAttribute("result", message);
			request.getRequestDispatcher("forget.jsp").forward(request, response);
			return;
		}else if(!admin[0].getEmail().equals(email)){
			String message="邮箱与用户不匹配！";
			request.setAttribute("result", message);
			request.getRequestDispatcher("forget.jsp").forward(request, response);
			return;
		}else{
			Random random=new Random();
			String temp="";
			for(int i=0;i<6;i++){
				temp+=random.nextInt(10);
			}
//			admin[0].setPassword(temp);
			admin[0].setPassword(GetMD5.getMd5(temp));
			adminDao.modifyAdmin(admin[0]);
			Mail mail=new Mail();
			mail.setHost("smtp.163.com");
			mail.setPortNumber("465");
			mail.setSender("tianmaairport@163.com");
			mail.setName("天马机场");
			mail.setReceiver(email);
			mail.setUsername("tianmaairport@163.com");
			mail.setPassword("team0303");
			mail.setSubject("天马机场管理员密码找回服务");
			mail.setMessage(admin[0].getName()+"，您的新密码是:"+temp+"，请登陆http://123.207.57.24/更改密码！");
			new MailUtil().send(mail);
//			System.out.println(temp);
			/*if(new MailUtil().send(mail)){
				System.out.println("发送成功");
			}else{
				System.out.println("发送失败");
			}*/
			String message="发送成功";
			request.setAttribute("result", message);
			request.getRequestDispatcher("forget.jsp").forward(request, response);
/*				String to=email;
				String from="1510825910@qq.com";
				String host="smtp.qq.com";
				Properties properties=System.getProperties();
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				properties.put("mail.smtp.socketFactory.fallback", "false");

				
				Session session=Session.getDefaultInstance(properties, new Authenticator(){
					public PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication("1510825910@qq.com", "eshsskxsncvzfedi");
					}
				});
				try{
					MimeMessage message=new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("找回密码");
					message.setText("找回密码");
					Transport.send(message);
					System.out.println("ok");
				}catch (MessagingException e) {
					e.printStackTrace();
				}*/
				
		}
	}

	

	


}
