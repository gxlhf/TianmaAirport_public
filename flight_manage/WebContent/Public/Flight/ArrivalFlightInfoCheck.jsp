<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String area;
if(request.getParameter("area")==null||(!request.getParameter("area").equals("international")&&!request.getParameter("area").equals("local")))
	area = "local";
else
	area = request.getParameter("area");
%>
<html>
<head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
    <%
    if(area.equals("local"))
    	out.println("航班信息 - 国内到港 - 天马机场");
    else
    	out.println("航班信息 - 国际到港 - 天马机场");
    %>
    </title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
  </head>
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
              <a href="#" class="dropdown-toggle curmenu" data-toggle="dropdown" data-hover="dropdown">航班信息</a>
              <ul class="dropdown-menu" role="menu">
                <%
                if(area.equals("local"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li class="curmenu"> -->
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=local">国内到港</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <%
                if(area.equals("international"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li> -->
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
              <strong>航班信息</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <%
                if(area.equals("local"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation" class="second-menu-cur"> -->
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=local">国内到港</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <%
                if(area.equals("international"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation"> -->
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=international">国际到港</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=international">国际离港</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="#">航班信息</a>
            </li>
            <li class="active">
            <%
            if(area.equals("local"))
            	out.println("国内到港");
            else
            	out.println("国际到港");
            %>
            </li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form" action="<%=basePath%>ArrivalFlightSearch">
            <div class="form-group">
              <label for="flight-id" class="col-sm-2 control-label">航班号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="flight-id">
              </div>
            </div>
            <div class="form-group">
              <label for="init-site" class="col-sm-2 control-label">始发地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="from-site">
                	<option value="">请选择</option>
                <%
                	User user = new User();
            		if(area.equals("local")){
            			String[] result = user.returnAllLocalFrom();
            			for(String output:result)
            			{
            				if(output.equals("")||output==null)
          					    continue;
            				out.println("<option value='"+output+"'>"+output+"</option>");
            			}
            				
            		}
            		else{
            			String[] result = user.returnAllInternationalFrom();
            			for(String output:result)
            			{
            				if(output.equals("")||output==null)
          					    continue;
            				out.println("<option value='"+output+"'>"+output+"</option>");
            			}
            				
            		}
                %>
                  <!-- <option value="广州">广州</option>
                  <option value="上海">上海虹桥</option>
                  <option value="重庆">重庆</option> -->
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="airCompany-name" class="col-sm-2 control-label">航空公司：</label>
              <div class="col-sm-6">
                <select class="form-control" name="airCompany-name">
                  <option value="">请选择</option>
                  <%
                  String[] result;
                  if(area.equals("local"))
                      result = user.returnAllArrivalLocalAirline();
                  else
                	  result = user.returnAllArrivalInternationalAirline();
      			  for(String output:result)
      			  {
      				  if(output.equals("")||output==null)
      					  continue;
      				  out.println("<option value='"+output+"'>"+output+"</option>");
      			  } 
                %>
                  <!-- <option value="中国南方航空公司">中国南方航空公司</option>
                  <option value="海南航空公司">海南航空公司</option>
                  <option value="中国东方航空公司">中国东方航空公司</option> -->
                </select>
              </div>
            </div>
            <input type="hidden" name="area" value="<%=area%>" /> 
            <div class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-6">
                <button type="submit" class="col-sm-12 btn btn-primary">查询</button>
              </div>
            </div>
          </form>
          <%-- <table class="table table-hover select-table">
            <thead>
              <tr>
                <%
              if(session.getAttribute("priv1")!=null){
            	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
              }else{
            	  out.println("<th></th>");
              }
              %>
                <!-- <th>
                  <span class="glyphicon glyphicon-check th-check"></span>
                </th> -->
                <th>航空公司</th>
                <th>航班号</th>
                <th>始发地</th>
                <th>经停地</th>
                <th>目的地</th>
                <th>到港时间</th>
                <th>行李转盘</th>
                
              </tr>
            </thead>
            <tbody> --%>
            <%
            if(request.getAttribute("arrivalFlightInfos")!=null)
            {
            	out.println("<table class='table table-hover select-table'><thead><tr>");
            	if(session.getAttribute("priv1")!=null){
              	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
                }else{
              	  out.println("<th></th>");
                }
            	out.println("<th>航空公司</th><th>航班号</th><th>始发地</th><th>经停地</th><th>目的地</th><th>到港时间</th><th>行李转盘</th></tr></thead><tbody>");
            	ArrivalFlightInfo[] arrivalFlightInfos = (ArrivalFlightInfo[])request.getAttribute("arrivalFlightInfos");
                for(ArrivalFlightInfo output:arrivalFlightInfos)
                {
                	
                	if(area.equals("local")&&output.getFlightCourse().isInternationalOrLocal()==false){
                		out.println("<tr data-id='"+output.getFlightCourse().getFlightNumber()+"'>");
                    	if(session.getAttribute("priv1")!=null){
                        	out.println("<td><span class='glyphicon glyphicon'></span></td>");
                        }else{
                      	  out.println("<td></td>");
                        }
                		out.println("<td>"+output.getFlightCourse().getAirline()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFlightNumber()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFrom()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getStop()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getTo()+"</td>");
                    	out.println("<td>"+output.getTime()+"</td>");
                    	out.println("<td>"+output.getLuggageCarousel()+"</td>");
        				out.println("</tr>");
                	}
                	if(area.equals("international")&&output.getFlightCourse().isInternationalOrLocal()==true){
                		out.println("<tr data-id='"+output.getFlightCourse().getFlightNumber()+"'>");
                    	if(session.getAttribute("priv1")!=null){
                        	out.println("<td><span class='glyphicon glyphicon'></span></td>");
                        }else{
                      	  out.println("<td></td>");
                        }
                		out.println("<td>"+output.getFlightCourse().getAirline()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFlightNumber()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFrom()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getStop()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getTo()+"</td>");
                    	out.println("<td>"+output.getTime()+"</td>");
                    	out.println("<td>"+output.getLuggageCarousel()+"</td>");
        				out.println("</tr>");
                	}         
                }
                out.println("</tbody></table>");
                out.println("<div><ul class='pager'><li class='previous'><a href='#'>← 上一页</a></li><li class='next'><a href='#'>下一页 →</a></li></ul></div>");
            }
            
            %>
              <!-- <tr data-id="10001"> -->
                <%-- <%
                if(session.getAttribute("priv1")!=null){
                	out.println("<td><span class='glyphicon glyphicon-check'></span></td>");
                }else{
              	  out.println("<td></td>");
                }
                %> --%>
                <!-- <td>
                  <span class="glyphicon glyphicon-check"></span>
                </td> -->
                <!-- <td>系统管理员</td>
                <td>主要负责用户管理，权限分配等工作</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
              </tr> -->
              
            
          <!-- <div>
            <ul class="pager">
              <li class="previous">
                <a href="#">← 上一页</a>
              </li>
              <li class="next">
                <a href="#">下一页 →</a>
              </li>
            </ul>
          </div> -->
          <%
          if(session.getAttribute("priv1")!=null){
        	  out.println("<div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a class='btn btn-primary' href='"+basePath+"Flight/FlightEdit.jsp'>修改</a><a class='btn btn-danger' href=''>删除</a><a class='btn btn-success' href=''>新增</a></div></div>");
          }
          %>
          <%-- <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="<%=basePath%>Facility/FacilityEdit.jsp">修改</a>
              <a class="btn btn-danger" href="">删除</a>
              <a class="btn btn-success" href="">新增</a>
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
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>
    <script type="text/javascript" src="https://api.thinkpage.cn/v3/weather/now.json?key=hoqbrzywjm37qvzd&amp;location=changsha"></script>
  

</body></html>