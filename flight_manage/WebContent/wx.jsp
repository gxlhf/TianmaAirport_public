<%@ page language="java" import="java.util.*,java.io.*,javax.servlet.*,org.dom4j.*,com.entity.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	System.out.println("");
	String respstr = request.getParameter("echostr");
	int strStart = 0;
	int strEnd = 0;

	/* 读取接收到的xml消息 */
	StringBuffer sb = new StringBuffer();
	InputStream is = request.getInputStream();
	InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	BufferedReader br = new BufferedReader(isr);
	String s = "";
	while ((s = br.readLine()) != null) {
		sb.append(s);
	}
	String xml = sb.toString();	//接收到微信端发送过来的xml数据
	System.out.println(xml);
	
	String[] toName = xml.split("<ToUserName>")[1].split("CDATA")[1].split("]></ToUserName>");//[0]
	String[] fromName = xml.split("<FromUserName>")[1].split("CDATA")[1].split("]></FromUserName>");//[0]
	String[] content = xml.split("<Content>")[1].split("CDATA")[1].split("]></Content>");//[0]
	String time = new Date().getTime();

	System.out.println(toName[0]);
	System.out.println(fromName[0]);
	System.out.println(content[0]);
	System.out.println(new Date().getTime());

	respstr="<xml>" + 
				"<ToUserName><![CDATA" + fromName[0] + "]></ToUserName>" + 
				"<FromUserName><![CDATA[" + toName[0] + "]></FromUserName>" +
				"<CreateTime>" + new Date().getTime() + "</CreateTime>" +
				"<MsgType><![CDATA[news]]></MsgType>" +
				"<ArticleCount>1</ArticleCount>" + 
				"<Articles>" + 
					"<item>" + 
						"<Title><![CDATA[arttitle]]></Title>" + 
						"<Description><![CDATA[artdescription]]></Description>" +
					"</item>" + 
				"</Articles>" +
			"</xml>";

	respstr = "<xml><ToUserName><![CDATA" + fromName[0] + "]></ToUserName><FromUserName><![CDATA[" + toName[0] + "]]></FromUserName><CreateTime>" + new Date().getTime() +"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
	
	System.out.println(respstr);
	
	response.getWriter().print(respstr);
	response.getWriter().flush();
	response.getWriter().close();
%>