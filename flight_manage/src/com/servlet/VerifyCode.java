package com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class VerifyCode
 */
//@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int width=60;
	private int height=20;
	private int codeCount=4;
	private int x=0;
    private int fontHeight;
    private int codeY;
    char[] codeSequence={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void initxuan()throws ServletException{
    	String strWidth="100";
    	String strHeight="40";
    	String strCodeCount="4";
    	try{
    		if(strWidth!=null&&strWidth.length()!=0){
    			width=Integer.parseInt(strWidth);
    		}
    		if(strHeight!=null&&strHeight.length()!=0){
    			height=Integer.parseInt(strHeight);
    		}
    		if(strCodeCount!=null&&strCodeCount.length()!=0){
    			codeCount=Integer.parseInt(strCodeCount);
    		}
    	}catch (NumberFormatException e) {
				// TODO: handle exception
		}
    	x=width/(codeCount+1);
    	fontHeight=height-8;
    	codeY=height-10;
    }
    
    public VerifyCode() {
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
		initxuan();
		BufferedImage buffImg=new BufferedImage(width, fontHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=buffImg.createGraphics();
		Random random=new Random();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		Font font=new Font("Fixedsys", Font.PLAIN, fontHeight);
		g.setFont(font);

		g.setColor(Color.BLACK);
		for(int i=0;i<30;i++){
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int xl=random.nextInt(12);
			int yl=random.nextInt(12);
			g.drawLine(x, y, x+xl, y+yl);
		}
		StringBuffer randomCode=new StringBuffer();
		int red=0,green=0,blue=0;
		for(int i=0;i<codeCount;i++){
			String strRand=String.valueOf(codeSequence[random.nextInt(36)]);
			red=random.nextInt(120) + 10;
			green=random.nextInt(120) + 10;
			blue=random.nextInt(120) + 10;
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i)*x, codeY);
			randomCode.append(strRand);
		}
		HttpSession session=request.getSession();
		session.setAttribute("validateCode", randomCode.toString());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache" );
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream sos=response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

}
