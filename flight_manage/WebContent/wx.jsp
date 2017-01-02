<%@ page language="java" import="java.util.*,java.io.*,javax.servlet.*,com.entity.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	System.out.println("");
	String respStr = request.getParameter("echostr");
	int respType = 1;//0 text, 1 news
	String respTitle = new String();
	String respDesc = new String();
	
	User port = new User();

	if(!(respStr != null && respStr.length() > 1)){

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
		
		String toName = xml.split("<ToUserName>")[1].split("CDATA")[1].split("]></ToUserName>")[0];//[0]
		String fromName = xml.split("<FromUserName>")[1].split("CDATA")[1].split("]></FromUserName>")[0];//[0]
		String content = xml.split("<Content>")[1].split("CDATA")[1].split("></Content>")[0].split("\\[")[1].split("\\]")[0];//[0]
		String time = xml.split("<CreateTime>")[1].split("</CreateTime>")[0];//[0]

		if(content.matches("[a-zA-Z]{2}[0-9]{4}|[a-zA-Z][0-9]{5}|[0-9][a-zA-Z][0-9]{4}")){
			respTitle = "航班信息查询";
			
			content = content.toUpperCase();

			ArrivalFlightInfo[] arrResult = port.searchArrivalFlightInfo("", content, "", "");	
			if(arrResult.length != 0){
				respDesc = content + " " + arrResult[0].getFlightCourse().getAirline() + "\n" + 
							arrResult[0].getFlightCourse().getFrom() + " -> " + arrResult[0].getFlightCourse().getTo() + "\n" +
							"降落时间：" + arrResult[0].getTime() + "\n" +
							"行李转盘：" + arrResult[0].getLuggageCarousel() + "\n\n";
			}			

			DepartureFlightInfo[] depResult = port.searchDepartureFlightInfo("", content, "", "");
			if(depResult.length != 0){
				String[] counterList = depResult[0].getCheckinCounter();
				String counterStr = new String();
				counterStr = counterList[0];
				for(int i = 1; i < counterList.length; i++){
					if(counterList[i] != null)
						counterStr += " " + counterList[i];
				}
				respDesc += content + " " + depResult[0].getFlightCourse().getAirline() + "\n" + 
							depResult[0].getFlightCourse().getFrom() + " -> " + depResult[0].getFlightCourse().getTo()+ "\n" +
							"起飞时间：" + depResult[0].getTime() + "\n" +
							"值机柜台：" + counterStr + "\n" +
							"登机门：" + depResult[0].getBoardingGate();
			}

			if(depResult.length + arrResult.length == 0)
				respType = 0;
		}else if(content.matches("登机门[0-9]{1,2}")){
			respTitle = "登机门位置查询";
			
			AirportResource[] gateList = port.searchAirportResource(content, "登机门");
			if(gateList.length != 0){		
				respDesc = content + "位置：" + gateList[0].getLocation();
			}else{
				respType = 0;
			}
		}else if(content.matches("值机柜台[0-9]{1,2}")){
			respTitle = "值机柜台位置查询";
			
			if(content.length() == 5){
				StringBuilder stringBuilder =new StringBuilder(content);
				stringBuilder.insert(4,"0");
				content = stringBuilder.toString();
			}
			
			AirportResource[] gateList = port.searchAirportResource(content, "值机柜台");
			if(gateList.length != 0){		
				respDesc = content + "位置：" + gateList[0].getLocation();
			}else{
				respType = 0;
			}
		}else if(content.matches("行李转盘[0-9]{1,2}")){
			respTitle = "行李转盘位置查询";
			
			if(content.length() == 5){
				StringBuilder stringBuilder =new StringBuilder(content);
				stringBuilder.insert(4,"0");
				content = stringBuilder.toString();
			}
			
			AirportResource[] gateList = port.searchAirportResource(content, "行李转盘");
			if(gateList.length != 0){		
				respDesc = content + "位置：" + gateList[0].getLocation();
			}else{
				respType = 0;
			}
		}else{
			respType = 0;
		}
		
		if(respType == 1){
			respStr="<xml>" + 
						"<ToUserName><![CDATA" + fromName + "]></ToUserName>" + 
						"<FromUserName><![CDATA" + toName + "]></FromUserName>" +
						"<CreateTime>" + new Date().getTime() + "</CreateTime>" +
						"<MsgType><![CDATA[news]]></MsgType>" +
						"<ArticleCount>1</ArticleCount>" + 
						"<Articles>" + 
							"<item>" + 
								"<Title><![CDATA[" + respTitle + "]]></Title>" + 
								"<Description><![CDATA[" + respDesc + "]]></Description>" +
							"</item>" + 
						"</Articles>" +
					"</xml>";
		}else if(respType == 0){
			respStr = "<xml><ToUserName><![CDATA" + fromName + "]></ToUserName><FromUserName><![CDATA" + toName + "]></FromUserName><CreateTime>" + time +"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[好像没有你想要的东西[尴尬]\n请检查你要搜索的关键字再问问小天哦~]]></Content></xml>";
		}
		
	}
	
	response.getWriter().print(respStr);
	response.getWriter().flush();
	response.getWriter().close();
%>