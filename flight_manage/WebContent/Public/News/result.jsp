<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%
	String type=(String)request.getAttribute("type");
    if(type.equals("flightInformation"))
    	out.println("新闻中心-航班信息- 天马机场");
    if(type.equals("airportResource"))
    	out.println("新闻中心-机场资源- 天马机场");
    else
    	out.println("新闻中心-物业资源- 天马机场");
    %></title>
</head>
<body>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 	Integer re=(Integer)request.getAttribute("result");
	 String forward=(String)request.getAttribute("forward");
	 if(forward!=null&&re!=null){
		if(forward.equals("add")){
			if(re.equals(0)){
	  			out.println("<script>alert('新增失败');</script>");
				}else if(re.equals(1)){
	  			out.println("<script>alert('新增成功');</script>");
				}
		}else if(forward.equals("delete")){
			if(re.equals(0)){
	  			out.println("<script>alert('删除失败\\n若删除成功后刷新页面，也会出现此弹框。');</script>");
				}else if(re.equals(1)){
	  			out.println("<script>alert('删除成功');</script>");
				}
		}else if(forward.equals("update")){
			if(re.equals(0)){
	  			out.println("<script>alert('修改失败');</script>");
				}else if(re.equals(1)){
	  			out.println("<script>alert('修改成功');</script>");
				}
		}
}
	 
	
	if(type!=null)
	{
%>

<script>window.location.href="../Public/News/NewsList.jsp?type=<%=type%>";</script>

<% }else { %>
<%} %>
</body>
</html>