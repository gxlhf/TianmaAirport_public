<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String area;
String p;
if(request.getParameter("area")==null||(!request.getParameter("area").equals("international")&&!request.getParameter("area").equals("local")))
	area = "local";
else
	area = request.getParameter("area");

if(request.getParameter("page")==null)
	p = "1";
else
{
	p = request.getParameter("page");
	if(!request.getParameter("page").matches("^\\d+$")||Integer.parseInt(p)<1)
		p = "1";

		
}
%>
<html>
<head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
    <%
    if(area.equals("local"))
    	out.println("航班信息 - 国内离港 - 天马机场");
    else
    	out.println("航班信息 - 国际离港 - 天马机场");
    %>
    </title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

<body>
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
			out.println("<script>alert('新增失败\\n若新增成功后刷新页面，也会出现此弹框。')</script>");
  		}else if(addResult.equals(1)){
	  		out.println("<script>alert('新增成功')</script>");
  		}
  	}
  	
  	Integer deleteResult=(Integer)request.getAttribute("deleteResult");
  	if(deleteResult!=null){
  		if(deleteResult.equals(0)){
			out.println("<script>alert('删除失败\\n若删除成功后刷新页面，也会出现此弹框。')</script>");
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
        		out.println("<li id='cur-user'><span class='glyphicon glyphicon-user'></span>"+admin.getName()+" | 已登录</li><li><a class='text-info' href='"+basePath+"EditMyInfo.jsp'>修改个人信息</a></li>");
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
                <li>
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=local">国内到港</a>
                </li>
                <%
                if(area.equals("local"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li class="curmenu"> -->
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <li>
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=international">国际到港</a>
                </li>
                <%
                if(area.equals("international"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li> -->
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
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp">机场介绍</a>
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
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=local">国内到港</a>
                </li>
                <%
                if(area.equals("local"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation" class="second-menu-cur"> -->
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=international">国际到港</a>
                </li>
                <%
                if(area.equals("international"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation"> -->
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=international">国际离港</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a>航班信息</a>
            </li>
            <li class="active">
            <%
            if(area.equals("local"))
            	out.println("国内离港");
            else
            	out.println("国际离港");
            %>
            </li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form" action="<%=basePath%>DepartureFlightSearch" data-toggle="validator">
            <div class="form-group">
              <label for="flight-id" class="col-sm-2 control-label">航班号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="flight-id" pattern="([A-z]{2}[0-9]{3,4}|[A-z][0-9]{4,5}|[0-9][A-z]{1}[0-9]{3,4})" data-pattern-error='请填写正确的航班号'>
              </div>
              <div class="col-sm-2 help-block with-errors"></div>
            </div>
            <div class="form-group">
              <label for="init-site" class="col-sm-2 control-label">目的地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="to-site">
                  <option value="">请选择</option>
                <%
                	User user = new User();
            		if(area.equals("local")){
            			String[] result = user.returnAllLocalDestination();
            			for(String output:result)
            			{
            				if(output.equals("")||output==null)
            					  continue;
            				out.println("<option value='"+output+"'>"+output+"</option>");
            			}
            				
            		}
            		else{
            			String[] result = user.returnAllInternationalDestination();
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
                      result = user.returnAllDepartureLocalAirline();
                  else
                	  result = user.returnAllDepartureInternationalAirline();
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
            <%
            if(request.getAttribute("departureFlightInfos")!=null)
            {
            	out.println("<table class='table table-hover select-table'><thead><tr>");
            	if(session.getAttribute("priv1")!=null){
              	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
                }else{
              	  out.println("<th></th>");
                }
            	out.println("<th>航空公司</th><th>航班号</th><th>始发地</th><th>经停地</th><th>目的地</th><th>离港时间</th><th>值机柜台</th><th>登机门</th></tr></thead><tbody>");
            	DepartureFlightInfo[] departureFlightInfos = (DepartureFlightInfo[])request.getAttribute("departureFlightInfos");
                int count = 0;
                for(DepartureFlightInfo output:departureFlightInfos)
                {
                	if(output!=null&area.equals("local")&&output.getFlightCourse().isInternationalOrLocal()==false)
                		count++;
                	if(output!=null&area.equals("international")&&output.getFlightCourse().isInternationalOrLocal()==true)
                		count++;
                }
                DepartureFlightInfo[] departureFlightInfosOutput = new DepartureFlightInfo[count];
                int j = 0;
                for(DepartureFlightInfo output:departureFlightInfos)
                {
                	if(output!=null&area.equals("local")&&output.getFlightCourse().isInternationalOrLocal()==false)
                	{
                		departureFlightInfosOutput[j] = output;
                		j++;
                	}
                	if(output!=null&area.equals("international")&&output.getFlightCourse().isInternationalOrLocal()==true)
                	{
                		departureFlightInfosOutput[j] = output;
                		j++;
                	}
                }
                String ul_path = "DepartureFlightSearch?flight-id="+request.getParameter("flight-id")+"&to-site="+request.getParameter("to-site")+"&airCompany-name="+request.getParameter("airCompany-name")+"&area="+area+"&page=";
                
                if(departureFlightInfosOutput.length!=0)
                {
                	if(departureFlightInfosOutput.length%10==0)
                	{
                		if(Integer.parseInt(p)>departureFlightInfosOutput.length/10)
                			response.sendRedirect(basePath+ul_path+Integer.toString(departureFlightInfosOutput.length/10)); 
                			
                	}
                	else
                	{
                		if(Integer.parseInt(p)>departureFlightInfosOutput.length/10 + 1)
                			response.sendRedirect(basePath+ul_path+Integer.toString(departureFlightInfosOutput.length/10 + 1)); 
                			
                	}
                }
                
                for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
        		{
        			if(i>=departureFlightInfosOutput.length||departureFlightInfosOutput.length==0)
        				break;
        			out.println("<tr data-id='flightNumber="+departureFlightInfosOutput[i].getFlightCourse().getFlightNumber()+"&time="+departureFlightInfosOutput[i].getTime()+"&area="+area+"&type=departure"+"'>");
                	if(session.getAttribute("priv1")!=null){
                    	out.println("<td><span class='glyphicon'></span></td>");
                    }else{
                  	  out.println("<td></td>");
                    }
            		out.println("<td>"+departureFlightInfosOutput[i].getFlightCourse().getAirline()+"</td>");
                	out.println("<td>"+departureFlightInfosOutput[i].getFlightCourse().getFlightNumber()+"</td>");
                	out.println("<td>"+departureFlightInfosOutput[i].getFlightCourse().getFrom()+"</td>");
                	out.println("<td>"+departureFlightInfosOutput[i].getFlightCourse().getStop()+"</td>");
                	out.println("<td>"+departureFlightInfosOutput[i].getFlightCourse().getTo()+"</td>");
                	String[] t1 = departureFlightInfosOutput[i].getTime().split("-", 2);
                	String[] t2 = t1[1].split(":");
                	out.println("<td>"+t2[0]+":"+t2[1]+"</td>");
                	String checkincounters = "";
                	int flag = 1;
                	for(String checkincounteroutput:departureFlightInfosOutput[i].getCheckinCounter())
                	{
                		if(checkincounteroutput!=null)
                		{
                			String t3[] = checkincounteroutput.split("台");
                			if(flag==1)
                			{
                				checkincounters = checkincounters + t3[1];
                				flag = 0;
                			}
                			else
                				checkincounters = checkincounters + ", " + t3[1];
                		}
                			
                	}
                	out.println("<td>"+checkincounters+"</td>");
                	
                	String[] t4 = departureFlightInfosOutput[i].getBoardingGate().split("门");
                	out.println("<td>"+t4[1]+"</td>");
    				out.println("</tr>");
        		}
                
                out.println("</tbody></table>");
                //out.println("<div><ul class='pager'><li class='previous'><a href='#'>← 上一页</a></li><li class='next'><a href='#'>下一页 →</a></li></ul></div>");
                out.println("<div><ul class='pager'>");
            	if(departureFlightInfosOutput.length!=0)
            	{
            		if(!p.equals("1"))
                        out.println("<li class='previous'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
                	if(departureFlightInfosOutput.length%10==0)
                    {
                        if(Integer.parseInt(p)!=departureFlightInfosOutput.length/10)
                    		out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                    else
                    {
                        if(Integer.parseInt(p)!=departureFlightInfosOutput.length/10 + 1)
                    		out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
            	}
                
                out.println("</ul></div>");
                if(session.getAttribute("priv1")!=null)
              	  out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"Flight/FlightInfoEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteDepartureFlightInfo'>删除</a><a class='btn btn-success' href='"+basePath+"Flight/FlightInfoEdit.jsp?type=departure&area="+area+"'>新增</a></div></div>");
            }
            else
            {
            	if(area.equals("local"))
            	{
            		out.println("<table class='table table-hover select-table'><thead><tr>");
                	if(session.getAttribute("priv1")!=null){
                  	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
                    }else{
                  	  out.println("<th></th>");
                    }
                	out.println("<th>航空公司</th><th>航班号</th><th>始发地</th><th>经停地</th><th>目的地</th><th>离港时间</th><th>值机柜台</th><th>登机门</th></tr></thead><tbody>");
                	
                	int count = 0;
                	for(DepartureFlightInfo output:user.returnAllLocalDepartureFlightInfo())
                	{
                		if(output!=null)
                			count++;
                	}
                	DepartureFlightInfo[] allLocalDepartureFlightInfos = new DepartureFlightInfo[count];
                	int j = 0;
                	for(DepartureFlightInfo output:user.returnAllLocalDepartureFlightInfo())
                	{
                		if(output!=null)
                		{
                			allLocalDepartureFlightInfos[j] = output;
                			j++;
                		}
                	}
                	
                	if(allLocalDepartureFlightInfos.length!=0)
                	{
                		if(allLocalDepartureFlightInfos.length%10==0)
                    	{
                    		if(Integer.parseInt(p)>allLocalDepartureFlightInfos.length/10)
                    			response.sendRedirect(basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=local&page="+Integer.toString(allLocalDepartureFlightInfos.length/10)); 
                    			
                    	}
                    	else
                    	{
                    		if(Integer.parseInt(p)>allLocalDepartureFlightInfos.length/10 + 1)
                    			response.sendRedirect(basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=local&page="+Integer.toString(allLocalDepartureFlightInfos.length/10 + 1)); 
                    			
                    	}
                	}
                	
                		
                	
                	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            		{
            			if(i>=allLocalDepartureFlightInfos.length||allLocalDepartureFlightInfos.length==0)
            				break;
            			out.println("<tr data-id='flightNumber="+allLocalDepartureFlightInfos[i].getFlightCourse().getFlightNumber()+"&time="+allLocalDepartureFlightInfos[i].getTime()+"&area="+area+"&type=departure"+"'>");
                    	if(session.getAttribute("priv1")!=null){
                        	out.println("<td><span class='glyphicon'></span></td>");
                        }else{
                      	  out.println("<td></td>");
                        }
                		out.println("<td>"+allLocalDepartureFlightInfos[i].getFlightCourse().getAirline()+"</td>");
                    	out.println("<td>"+allLocalDepartureFlightInfos[i].getFlightCourse().getFlightNumber()+"</td>");
                    	out.println("<td>"+allLocalDepartureFlightInfos[i].getFlightCourse().getFrom()+"</td>");
                    	out.println("<td>"+allLocalDepartureFlightInfos[i].getFlightCourse().getStop()+"</td>");
                    	out.println("<td>"+allLocalDepartureFlightInfos[i].getFlightCourse().getTo()+"</td>");
                    	String[] t1 = allLocalDepartureFlightInfos[i].getTime().split("-", 2);
                    	String[] t2 = t1[1].split(":");
                    	out.println("<td>"+t2[0]+":"+t2[1]+"</td>");
                    	String checkincounters = "";
                    	int flag = 1;
                    	for(String checkincounteroutput:allLocalDepartureFlightInfos[i].getCheckinCounter())
                    	{
                    		if(checkincounteroutput!=null)
                    		{
                    			String t3[] = checkincounteroutput.split("台");
                    			if(flag==1)
                    			{
                    				checkincounters = checkincounters + t3[1];
                    				flag = 0;
                    			}
                    			else
                    				checkincounters = checkincounters + ", " + t3[1];
                    		}
                    			
                    	}
                    	out.println("<td>"+checkincounters+"</td>");
                    	
                    	String[] t4 = allLocalDepartureFlightInfos[i].getBoardingGate().split("门");
                    	out.println("<td>"+t4[1]+"</td>");
        				out.println("</tr>");
            		}
            		out.println("</tbody></table>");
            		out.println("<div><ul class='pager'>");
            		if(allLocalDepartureFlightInfos.length!=0)
            		{
            			if(!p.equals("1"))
                        	out.println("<li class='previous'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=local&page="+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
                        if(allLocalDepartureFlightInfos.length%10==0)
                        {
                        	if(Integer.parseInt(p)!=allLocalDepartureFlightInfos.length/10)
                    			out.println("<li class='next'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=local&page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                        }
                        else{
                        	if(Integer.parseInt(p)!=allLocalDepartureFlightInfos.length/10 + 1)
                    			out.println("<li class='next'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=local&page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                        }
            		}
            		
            		
                    out.println("</ul></div>");
            	}
            	if(area.equals("international"))
            	{
            		out.println("<table class='table table-hover select-table'><thead><tr>");
                	if(session.getAttribute("priv1")!=null){
                  	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
                    }else{
                  	  out.println("<th></th>");
                    }
                	out.println("<th>航空公司</th><th>航班号</th><th>始发地</th><th>经停地</th><th>目的地</th><th>离港时间</th><th>值机柜台</th><th>登机门</th></tr></thead><tbody>");
                	
                	int count = 0;
                	for(DepartureFlightInfo output:user.returnAllInternationalDepartureFlightInfo())
                	{
                		if(output!=null)
                			count++;
                	}
                	DepartureFlightInfo[] allInternationalDepartureFlightInfos = new DepartureFlightInfo[count];
                	int j = 0;
                	for(DepartureFlightInfo output:user.returnAllInternationalDepartureFlightInfo())
                	{
                		if(output!=null)
                		{
                			allInternationalDepartureFlightInfos[j] = output;
                			j++;
                		}
                	}
                	
                	if(allInternationalDepartureFlightInfos.length!=0)
                	{
                		if(allInternationalDepartureFlightInfos.length%10==0)
                    	{
                    		if(Integer.parseInt(p)>allInternationalDepartureFlightInfos.length/10)
                    			response.sendRedirect(basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=international&page="+Integer.toString(allInternationalDepartureFlightInfos.length/10)); 
                    			
                    	}
                    	else
                    	{
                    		if(Integer.parseInt(p)>allInternationalDepartureFlightInfos.length/10 + 1)
                    			response.sendRedirect(basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=international&page="+Integer.toString(allInternationalDepartureFlightInfos.length/10 + 1)); 
                    			
                    	}
                	}
                	
                		
                	
                	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            		{
            			if(i>=allInternationalDepartureFlightInfos.length||allInternationalDepartureFlightInfos.length==0)
            				break;
            			out.println("<tr data-id='flightNumber="+allInternationalDepartureFlightInfos[i].getFlightCourse().getFlightNumber()+"&time="+allInternationalDepartureFlightInfos[i].getTime()+"&area="+area+"&type=departure"+"'>");
                    	if(session.getAttribute("priv1")!=null){
                        	out.println("<td><span class='glyphicon'></span></td>");
                        }else{
                      	  out.println("<td></td>");
                        }
                		out.println("<td>"+allInternationalDepartureFlightInfos[i].getFlightCourse().getAirline()+"</td>");
                    	out.println("<td>"+allInternationalDepartureFlightInfos[i].getFlightCourse().getFlightNumber()+"</td>");
                    	out.println("<td>"+allInternationalDepartureFlightInfos[i].getFlightCourse().getFrom()+"</td>");
                    	out.println("<td>"+allInternationalDepartureFlightInfos[i].getFlightCourse().getStop()+"</td>");
                    	out.println("<td>"+allInternationalDepartureFlightInfos[i].getFlightCourse().getTo()+"</td>");
                    	String[] t1 = allInternationalDepartureFlightInfos[i].getTime().split("-", 2);
                    	String[] t2 = t1[1].split(":");
                    	out.println("<td>"+t2[0]+":"+t2[1]+"</td>");
                    	String checkincounters = "";
                    	int flag = 1;
                    	for(String checkincounteroutput:allInternationalDepartureFlightInfos[i].getCheckinCounter())
                    	{
                    		if(checkincounteroutput!=null)
                    		{
                    			String t3[] = checkincounteroutput.split("台");
                    			if(flag==1)
                    			{
                    				checkincounters = checkincounters + t3[1];
                    				flag = 0;
                    			}
                    			else
                    				checkincounters = checkincounters + ", " + t3[1];
                    		}
                    			
                    	}
                    	out.println("<td>"+checkincounters+"</td>");
                    	
                    	String[] t4 = allInternationalDepartureFlightInfos[i].getBoardingGate().split("门");
                    	out.println("<td>"+t4[1]+"</td>");
        				out.println("</tr>");
            		}
            		out.println("</tbody></table>");
            		out.println("<div><ul class='pager'>");
            		if(allInternationalDepartureFlightInfos.length!=0)
            		{
            			if(!p.equals("1"))
                        	out.println("<li class='previous'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=international&page="+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
                        if(allInternationalDepartureFlightInfos.length%10==0)
                        {
                        	if(Integer.parseInt(p)!=allInternationalDepartureFlightInfos.length/10)
                    			out.println("<li class='next'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=international&page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                        }
                        else{
                        	if(Integer.parseInt(p)!=allInternationalDepartureFlightInfos.length/10 + 1)
                    			out.println("<li class='next'><a href='"+basePath+"Public/Flight/DepartureFlightInfoCheck.jsp?area=international&page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                        }
            		}
            		
            		
                    out.println("</ul></div>");
            	}
            	if(session.getAttribute("priv1")!=null)
                	  out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"Flight/FlightInfoEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteDepartureFlightInfo'>删除</a><a class='btn btn-success' href='"+basePath+"Flight/FlightInfoEdit.jsp?type=departure&area="+area+"'>新增</a></div></div>");
            }
            
            %>
              
        </div>
      </div>
      <div id="backToTop-btn" onclick="scroll(0,0)">
        <span class="glyphicon glyphicon-chevron-up"></span>
      </div>


      <!-- 确认信息弹框开始 -->
      <div id="ensureBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-body">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
              <h4>以下是即将删除的信息，请确认。<br></h4>
              <strong class="text-danger">删除后将无法撤销</strong>

              <hr>

              <div class="form-horizontal" role="form">
                <div class="form-group">
                  <label class="col-xs-3 control-label">航班号：</label>
                  <div class="col-xs-9">
                    <p id="flight-id-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">离港时间：</label>
                  <div class="col-xs-9">
                    <p id="flight-dep-time-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">始发地：</label>
                  <div class="col-xs-9">
                    <p id="flight-from-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">经停地：</label>
                  <div class="col-xs-9">
                    <p id="flight-via-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">目的地：</label>
                  <div class="col-xs-9">
                    <p id="flight-to-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">航空公司：</label>
                  <div class="col-xs-9">
                    <p id="flight-airline-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">值机柜台：</label>
                  <div class="col-xs-9">
                    <p id="flight-counter-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">登机门：</label>
                  <div class="col-xs-9">
                    <p id="flight-gate-ensure" class="form-control-static"> </p>
                  </div>
                </div>
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
              <button type="button" class="btn btn-danger">删除</button>
            </div>

          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div>
      <!-- 确认信息弹框结束 -->


      <!-- 报错弹框开始 -->
      <div id="errorBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-sm">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
              <h5 class="modal-title" id="myModalLabel">提示</h5>
            </div>
            <div class="modal-body">
              <p class="text-center">请选择航班</p>
            </div>

          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div>
      <!-- 报错弹框结束 -->
      

    </div>
    <!-- 内容结束 -->
    <!-- 尾部开始 -->
    <footer class="container-fluid">
      <p class="text-center">©2016 软件1401班第三组</p>
    </footer>
    <!-- 尾部结束 -->
    <!--[if lt IE 9]>
      <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
    <![endif]-->
    <!--[if gte IE 9]>
      <script type="text/javascript" src="<%=basePath%>/js/jquery-3.1.1.min.js"></script>
    <![endif]-->
    <!--[if !IE]><!-->
      <script type="text/javascript" src="<%=basePath%>/js/jquery-3.1.1.min.js"></script>
    <!--<![endif]-->

    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/validator.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/ensureBox.js"></script>

</body>
</html>