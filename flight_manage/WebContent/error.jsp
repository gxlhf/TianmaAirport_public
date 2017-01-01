<%@ page language="java" import="java.util.*,com.entity.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- DW6 -->
  <head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新东方厨师招募 - 天马机场</title>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.min.css" >

    <style type="text/css">
      #main-div {
        background: white !important;
        padding: 100px 0px;
      }
      #main-div > div > div:first-child {
        text-align: right;
      }
      #main-div > div > p {
        margin-top: 30px;
      }
    </style>
      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <!-- The structure of this file is exactly the same as 2col_rightNav.html;
   the only difference between the two is the stylesheet they use -->
  <body>

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
            out.println("<li id='cur-user'><span class='glyphicon glyphicon-user'></span>"+admin.getName()+" | 已登录</li><li><a class='text-info' href='"+basePath+"EditMyInfo.jsp'>修改个人信息</a></li>");
            out.println("<li><a class='text-danger' href='"+basePath+"logout'>退出</a></li>");
      }else{
        out.println("<li><a class='text-info' href='"+basePath+"login.jsp'>登陆</a></li>");
      }
            %>
          </ul>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="container-fluid collapse navbar-collapse navbar-collapse-lower" id="header">
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
                out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>乘机指南</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"Public/PassengerGuide.jsp'>乘机指引</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");
                
            %>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">新闻中心</a>
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
                    out.println("<li><a href='"+basePath+"News/NewsEdit.jsp?todo=add'>发布新闻</a></li>");
                %>
                
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <!-- 头部结束 -->

    <!-- 内容开始 -->
    <div class="container" id="main-div">
      <div class="row">
        <div class="col-xs-4">
          <img src="img/diao.gif">
        </div>
        <p class="col-xs-8">你好像做了一些很厉害的事情<br>跟我一起学做菜吧！</p>
      </div>
    </div>
    <!-- 内容结束 -->

    <!-- 尾部开始 -->
    <footer class="container-fluid">
      <p class="text-center">©2016 软件1401班第三组</p>
    </footer>
    <!-- 尾部结束 -->

    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="js/public.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
  </body>
</html>