<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id="";
id=request.getParameter("id");
int a=0;
String type="";
News[] news=(News[])request.getSession().getAttribute("news");
%>
<%
if(id!=null){
	if(news!=null){
		for(int i=0;i<news.length;i++){
		  if(news[i].getNewsId().equals(id))
			  a=i;
		}
		if(news[a].getKind()!=null){
			if(news[a].getKind().equals("航班信息"))
				type="flightInformation";
			else if(news[a].getKind().equals("机场资源"))
				type="airportResource";	
			else if(news[a].getKind().equals("物业资源"))
				type = "facilityResource";
		}
	}
}
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>新闻详情 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/css/News.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
  </head><body>
    <!-- 头部开始 -->
    <nav class="navbar navbar-default" role="navigation">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%=basePath%>index.jsp">
            <img class="navbar-logo" src="<%=basePath%>/img/logo.png">
          </a>
        </div>
        <div class="col-md-8 pull-right" id="personal-info">
          <ul class="list-inline">
            <li id="weather">正在获取天气...</li>
            <% 
            Admin admin=(Admin)session.getAttribute("admin");
			if(admin!=null){
        		out.println("<li id='cur-user'><span class='glyphicon glyphicon-user'></span>"+admin.getName()+" | 已登录</li><li><a class='text-info' href='#'>修改个人信息</a></li>");
        		out.println("<li><a class='text-danger' href='"+basePath+"logout'>退出</a></li>");
			}else{
				out.println("<li><a class='text-info' href='"+basePath+"login.jsp'>登陆</a></li>");
			}
            %>
          </ul>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-collapse-lower" id="header">
          <ul class="nav navbar-nav navbar-right">
          <%
          	if(session.getAttribute("priv3")!=null||session.getAttribute("priv4")!=null){
          		if(session.getAttribute("priv4")==null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")==null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")!=null&&session.getAttribute("priv4")!=null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li><li><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
          	}
          		
          %>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">航班信息</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=local">国内到港</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=international">国际到港</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=international">国际离港</a>
                </li>
              </ul>
            </li>
            <%
            	if(session.getAttribute("priv0")!=null)
            		out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>机场设施管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"Public/Facility/Resource.jsp'>机场资源</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");
            	else
            		out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>乘机指南</a><ul class='dropdown-menu' role='menu'><li><a href='#'>乘机指引</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");
            		
            %>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle curmenu" data-toggle="dropdown" data-hover="dropdown">新闻中心</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="<%=basePath%>Public/News/Intro.jsp">机场介绍</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=flightInformation">航班信息</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=airportResource">机场资源</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=facilityResource">物业资源</a>
                </li>
                <%
                	if(session.getAttribute("priv2")!=null)
                		out.println("<li><a href='"+basePath+"News/NewsEdit.jsp'>发布新闻</a></li>");
                %>
                
              </ul>
            </li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container-fluid -->
    </nav>
    <!-- 头部结束 -->
    <!-- 内容开始 -->
    <div class="container" id="main-div">
      <div class="row">
        <div class="col-md-2" id="sidebar">
          <ul class="nav nav-pills nav-stacked" role="tablist">
            <li role="presentation" class="first-menu">
              <strong>新闻中心</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <li role="presentation">
                  <a href="<%=basePath%>Public/News/Intro.jsp">机场介绍</a>
                </li>
                <li role="presentation" class="second-menu-cur">
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=flightInformation">航班信息</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=airportResource">机场资源</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=facilityResource">物业资源</a>
                </li>
                <%
                	if(session.getAttribute("priv2")!=null)
                		out.println("<li role='presentation'><a href='"+basePath+"News/NewsEdit.jsp?type=add'>发布新闻</a></li>");
                %>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="#">新闻中心</a>
            </li>
            <li>
              <a href="#">航班信息</a>
            </li>
            <li class="active">最新航班</li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <div class="page-header">
            <h2 class="text-center">机场新闻</h2>
          </div>
          <div id="main-text">
          <% 
			if(news[a]!=null){
          		out.println("<p>发布时间："+news[a].getTime()+"</p>");
          		out.println("<p>"+news[a].getContent()+"</p>  </div>");  
          		session.setAttribute("news", news[a]);
		  }
          if(session.getAttribute("priv2")!=null){
        	  out.println("<div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a class='btn btn-primary' href='"+basePath+"News/NewsEdit.jsp?type="+type+"&&news-id="+id+"'>修改</a></div></div>");
          }
          %>
          <%-- <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="<%=basePath%>News/NewsEdit.jsp">修改</a>
            </div>
          </div> --%>
        </div>
      </div>
      <div id="backToTop-btn" onclick="scroll(0,0)">
        <span class="glyphicon glyphicon-chevron-up"></span>
      </div>
    </div>
    <!-- 内容结束 -->
    <!-- 尾部开始 -->
    <footer class="container-fluid">
      <p class="text-center">
        <a href="#">About Us</a>·
        <a href="#">Site Map</a>·
        <a href="#">Privacy Policy</a>·
        <a href="#">Contact Us</a>· ©2016 软件1401班第三组</p>
    </footer>
    <!-- 尾部结束 -->
    <script src="<%=basePath%>/js/jquery-3.1.1.min.js"></script>
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>
    <script type="text/javascript" src="https://api.thinkpage.cn/v3/weather/now.json?key=hoqbrzywjm37qvzd&amp;location=changsha"></script>
  

</body></html>