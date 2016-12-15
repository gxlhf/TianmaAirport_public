<%@page import="java.awt.print.Printable"%>
<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户与角色管理- 用户管理 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
  </head><body>
  <%
  	Integer modifyResult=(Integer)request.getAttribute("modifyResult");
  	if(modifyResult!=null){
  		if(modifyResult.equals(0)){
			out.println("<script>alert('修改失败')</script>");
  		}else if(modifyResult.equals(1)){
	  		out.println("<script>alert('修改成功')</script>");
  		}
  	}
  	
  	Integer addResult=(Integer)request.getAttribute("addResult");
  	if(addResult!=null){
  		if(addResult.equals(0)){
			out.println("<script>alert('新增失败')</script>");
  		}else if(addResult.equals(1)){
	  		out.println("<script>alert('新增成功')</script>");
  		}
  	}
  	
  	Integer deleteResult=(Integer)request.getAttribute("deleteResult");
  	if(deleteResult!=null){
  		if(deleteResult.equals(0)){
			out.println("<script>alert('删除失败')</script>");
  		}else if(deleteResult.equals(1)){
	  		out.println("<script>alert('删除成功')</script>");
  		}
  	}
  %>
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
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")==null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li class='curmenu'><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")!=null&&session.getAttribute("priv4")!=null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li curmenu><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li><li><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
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
              <strong>用户与角色管理</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
              <%
              if(session.getAttribute("priv4")==null)
            	  out.println("<li role='presentation'><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li>");
              if(session.getAttribute("priv3")==null)
            	  out.println("<li role='presentation' class='second-menu-cur'><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li>");
              if(session.getAttribute("priv3")!=null&&session.getAttribute("priv4")!=null)
            	  out.println("<li role='presentation' class='second-menu-cur'><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li><li role='presentation'><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li>");
              %>
                
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="#">用户与角色管理</a>
            </li>
            <li class="active">用户管理</li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <%-- <%
          Admin[] admins = admin.searchAdmin("", "", -1, "", "");
          %> --%>
          <form class="form-horizontal" role="form" action="<%=basePath%>SearchAdmin">
            <div class="form-group">
              <label for="user-id" class="col-sm-2 control-label">员工号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="search-empno">
              </div>
            </div>
            <div class="form-group">
              <label for="user-name" class="col-sm-2 control-label">姓名：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="search-name">
              </div>
            </div>
            <div class="form-group">
              <label for="search-sex" class="col-sm-2 control-label">性别：</label>
              <div class="col-sm-6">
                <select class="form-control" name="search-sex">
                  <option value="">不限</option>
                  <option value="1">男</option>
                  <option value="0">女</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="search-pos" class="col-sm-2 control-label">职位：</label>
              <div class="col-sm-6">
                <select class="form-control" name="search-position">
                  <option value="">不限</option>
                  <%
                  	for(String output:admin.returnAllPosition())
                  	{
                  %>
                  		<option value="<%=output %>"><%=output %></option>
                  <%
                  	}
                  %>
                  <%-- <%
                  for(Admin output:admins)
                  {
                	  out.println("<option value='"+output.getPosition()+"'>"+output.getPosition()+"</option>");
                  }
                  %> --%>
                  <!-- <option value="机场地勤人员">机场地勤人员</option>
                  <option value="信息技术员">信息技术员</option> -->
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">角色：</label>
              <div class="col-sm-6">
                <select class="form-control" name="search-role">
                  <option value="">不限</option>
                  <%
                  	for(Role output:admin.returnAllRole())
                  	{
                  %>
                  		<option value="<%=output.getName() %>"><%=output.getName() %></option>
                  <%
                  	}
                  %>
                  <!-- <option value="系统管理员">系统管理员</option>
                  <option value="航班信息管理员">航班信息管理员</option>
                  <option value="机场设施管理员">机场设施管理员</option>
                  <option value="新闻管理员">新闻管理员</option> -->
                </select>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-6">
                <button type="submit" class="col-sm-12 btn btn-primary">筛选</button>
              </div>
            </div>
          </form>
          <%
            if(request.getAttribute("adminsInfo")!=null)
            {
            	out.println("<table class='table table-hover select-table'><thead><tr>");
              	out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
            	out.println("<th>员工号</th><th>姓名</th><th>性别</th><th>电话</th><th>手机</th><th>邮箱</th><th>部门</th><th>职位</th><th>角色</th></tr></thead><tbody>");
            	//ArrivalFlightInfo[] arrivalFlightInfos = (ArrivalFlightInfo[])request.getAttribute("arrivalFlightInfos");
            	Admin[] adminInfos = (Admin[])request.getAttribute("adminsInfo");
            	for(Admin output:adminInfos)
            	{
            		out.println("<tr data-id='empno="+output.getEmpno()+"'>");
            		out.println("<td><span class='glyphicon glyphicon'></span></td>");
            		out.println("<td>"+output.getEmpno()+"</td>");
            		out.println("<td>"+output.getName()+"</td>");
            		
            		if(output.getSex()==1)
            			out.println("<td>男</td>");
            		else
            			out.println("<td>女</td>");
            		out.println("<td>"+output.getPhone()+"</td>");
            		out.println("<td>"+output.getMobile()+"</td>");
            		out.println("<td>"+output.getEmail()+"</td>");
            		out.println("<td>"+output.getDepartment()+"</td>");
            		out.println("<td>"+output.getPosition()+"</td>");
            		out.println("<td>"+output.getRole().getName()+"</td>");
            		out.println("</tr>");
            	}
            	/* out.println("<tr data-id='"+roleInfo.getName()+"'>");
            	out.println("<td><span class='glyphicon glyphicon'></span></td>");
            	out.println("<td>"+"1"+"</td>");
            	out.println("<td>"+roleInfo.getName()+"</td>");
            	out.println("<td>"+roleInfo.getDescription()+"</td>"); */
                /* for(ArrivalFlightInfo output:arrivalFlightInfos)
                {
                	out.println("<tr data-id='"+output.getFlightCourse().getFlightNumber()+"'>");
                	if(session.getAttribute("priv1")!=null){
                    	out.println("<td><span class='glyphicon glyphicon'></span></td>");
                    }else{
                  	  out.println("<td></td>");
                    }
                	if(area.equals("local")&&output.getFlightCourse().isInternationalOrLocal()==false){
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
                		out.println("<td>"+output.getFlightCourse().getAirline()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFlightNumber()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getFrom()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getStop()+"</td>");
                    	out.println("<td>"+output.getFlightCourse().getTo()+"</td>");
                    	out.println("<td>"+output.getTime()+"</td>");
                    	out.println("<td>"+output.getLuggageCarousel()+"</td>");
        				out.println("</tr>");
                	}         
                } */
                out.println("</tbody></table>");
                out.println("<div><ul class='pager'><li class='previous'><a href='#'>← 上一页</a></li><li class='next'><a href='#'>下一页 →</a></li></ul></div>");
            	out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"User/UserEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteAdmin'>删除</a><a class='btn btn-success' href='"+basePath+"User/UserEdit.jsp'>新增</a></div></div>");
            }
            else
            {
            	Admin[] admins = admin.searchAdmin("", "", -1, "", "");
            	out.println("<table class='table table-hover select-table'><thead><tr>");
              	out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
            	out.println("<th>员工号</th><th>姓名</th><th>性别</th><th>电话</th><th>手机</th><th>邮箱</th><th>部门</th><th>职位</th><th>角色</th></tr></thead><tbody>");
            	for(Admin output:admins)
            	{
            		out.println("<tr data-id='empno="+output.getEmpno()+"'>");
            		out.println("<td><span class='glyphicon glyphicon'></span></td>");
            		out.println("<td>"+output.getEmpno()+"</td>");
            		out.println("<td>"+output.getName()+"</td>");
            		
            		if(output.getSex()==1)
            			out.println("<td>男</td>");
            		else
            			out.println("<td>女</td>");
            		out.println("<td>"+output.getPhone()+"</td>");
            		out.println("<td>"+output.getMobile()+"</td>");
            		out.println("<td>"+output.getEmail()+"</td>");
            		out.println("<td>"+output.getDepartment()+"</td>");
            		out.println("<td>"+output.getPosition()+"</td>");
            		out.println("<td>"+output.getRole().getName()+"</td>");
            		out.println("</tr>");
            	}
            	out.println("</tbody></table>");
                out.println("<div><ul class='pager'><li class='previous'><a href='#'>← 上一页</a></li><li class='next'><a href='#'>下一页 →</a></li></ul></div>");
            	out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"User/UserEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteAdmin'>删除</a><a class='btn btn-success' href='"+basePath+"User/UserEdit.jsp'>新增</a></div></div>");
            	
            }
            // 表格中的data-id属性填写的是需要进行修改或删除时向对应jsp传送的参数字符串
            // 例如需要将变量名为"EmpNo"值为"1004"和变量名为"Type"值为"1"的两个参数传送到jsp页面，则data-id填写的是"EmpNo=1004&Type=1"
            
            %>
          <%-- <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="<%=basePath%>Role/RoleEdit.jsp">修改</a>
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