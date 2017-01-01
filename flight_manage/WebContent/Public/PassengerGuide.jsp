<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String p;
if(request.getParameter("page")==null)
	p = "1";
else
{
	p = request.getParameter("page");
	if(!request.getParameter("page").matches("^\\d+$")||Integer.parseInt(p)<1)
		p = "1";
}
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="<%=basePath%>">
    <title>乘机指引 - 乘机指南 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/css/PassengerGuide.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/iconfont.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            		out.println("<li class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>乘机指南</a><ul class='dropdown-menu' role='menu'><li class='curmenu'><a href='"+basePath+"Public/PassengerGuide.jsp'>乘机指引</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");            		
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
              <strong>乘机指南</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <li role="presentation" class="second-menu-cur">
                  <a href="<%=basePath%>Public/PassengerGuide.jsp">乘机指引</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/Facility/Facility.jsp">物业设施</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a>乘机指南</a>
            </li>
            <li class="active">乘机指引</li>
          </ol>

          <form class="form-horizontal col-sm-offset-1" role="form" action="<%=basePath%>PassengerGuide">
            <div class="form-group">
              <label for="key" class="col-sm-2 control-label">航班号或城市</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="key">
              </div>
              <div class="btn-group col-sm-3" data-toggle="buttons">
                <label class="btn btn-default col-sm-6 active">
                  <input type="radio" name="flight_type" value="departure" checked>离港
                </label>
                <label class="btn btn-default col-sm-6">
                  <input type="radio" name="flight_type" value="arrival">到港
                </label>   
              </div>
            </div>
            <input type="text" class="hide" name="is_flightNo">
            <div class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-6">
                <button type="submit" class="col-sm-12 btn btn-primary">查询</button>
              </div>
            </div>
          </form>

          <ul class="flight-info-list">
            <%
            if(request.getAttribute("departureFlightInfos")!=null)
            {
            	DepartureFlightInfo[] departureFlightInfos = (DepartureFlightInfo[])request.getAttribute("departureFlightInfos");
            	int count = 0;
            	for(DepartureFlightInfo output:departureFlightInfos)
            	{
            		if(output!=null)
            			count++;
            	}
            	DepartureFlightInfo[] departureFlightInfosOutput = new DepartureFlightInfo[count];
            	int j=0;
            	for(DepartureFlightInfo output:departureFlightInfos)
            	{
            		if(output!=null)
            		{
            			departureFlightInfosOutput[j] = output;
            			j++;
            		}
            	}
            	String ul_path = "PassengerGuide?key="+request.getParameter("key")+"&flight_type="+request.getParameter("flight_type")+"&is_flightNo="+request.getParameter("is_flightNo")+"&page=";
            	
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
            %>
            <li>
              <div class="header-box">
              <%
              	
              	String airlineFlag = departureFlightInfosOutput[i].getFlightCourse().getFlightNumber().substring(0, 2).toLowerCase();
              %>
                <img src="img/airlineLogo/<%=airlineFlag %>.png">
                <p><%=departureFlightInfosOutput[i].getFlightCourse().getAirline() %> <strong><%=departureFlightInfosOutput[i].getFlightCourse().getFlightNumber() %></strong></p>
              </div>
              <div class="detail-box depature">
                <div>
                  <p><%=departureFlightInfosOutput[i].getFlightCourse().getFrom() %>
                  <%
                  		if(departureFlightInfosOutput[i].getFlightCourse().getStop().equals("")||departureFlightInfosOutput[i].getFlightCourse().getStop()==null)
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		else
                  		{
                  			out.println("<span class='iconfont icon-plane'></span>");
                  			out.println(departureFlightInfosOutput[i].getFlightCourse().getStop());
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		}
                  %> 
                  <!-- <span class="iconfont icon-plane"></span> -->
                  <%=departureFlightInfosOutput[i].getFlightCourse().getTo() %></p>
                </div>
                <div>
                  <p>起飞时间</p>
                  <%
                  	String[] t1 = departureFlightInfosOutput[i].getTime().split("-", 2);
              		String[] t2 = t1[1].split(":");
                  %>
                  <h6><%=t2[0]+":"+t2[1] %></h6>
                </div>
                <div>
                  <p>值机柜台</p>
                  <%
                  	String checkincounters = "";
                  	User user = new User();
              		int flag = 1;
              		String checkincountersAndLocations = "";
              		for(String checkincounteroutput:departureFlightInfosOutput[i].getCheckinCounter())
              		{
              			if(checkincounteroutput!=null)
              			{
              				AirportResource[] airportResources = user.searchAirportResource(checkincounteroutput, "");     				
              				
              				String t3[] = checkincounteroutput.split("台");
              				if(flag==1)
              				{
              					checkincounters = checkincounters + t3[1];
              					checkincountersAndLocations = checkincountersAndLocations + "<strong>" + checkincounteroutput + "</strong> " + airportResources[0].getLocation();
              					flag = 0;
              				}
              				else
              				{
              					checkincounters = checkincounters + ", " + t3[1];
              					checkincountersAndLocations = checkincountersAndLocations + "<br><strong>" + checkincounteroutput + "</strong> " + airportResources[0].getLocation();
              				}		
              			}			
              		}
                  %>
                  <h6><%=checkincounters %></h6>
                  <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<%=checkincountersAndLocations %>">查看位置</span>
                  <%-- <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<strong>值机柜台1</strong> 出发大厅东侧<br><strong>值机柜台3</strong> 出发大厅东侧<br><strong>值机柜台5</strong> 出发大厅东侧">查看位置</span> --%>
                </div>
                <div>
                  <p>登机门</p>
                  <%
                  	String[] t4 = departureFlightInfosOutput[i].getBoardingGate().split("门");
                  	AirportResource[] airportResources = user.searchAirportResource(departureFlightInfosOutput[i].getBoardingGate(), "");
                  %>
                  <h6><%=t4[1] %></h6>
                  <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<strong><%=departureFlightInfosOutput[i].getBoardingGate() %></strong> <%=airportResources[0].getLocation() %>">查看位置</span>
                </div>
              </div>
            </li>
            <%	
            	}
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
            }
            %>
            
            <%
            if(request.getAttribute("arrivalFlightInfos")!=null)
            {
            	ArrivalFlightInfo[] arrivalFlightInfos = (ArrivalFlightInfo[])request.getAttribute("arrivalFlightInfos");
            	User user = new User();
            	String ul_path = "PassengerGuide?key="+request.getParameter("key")+"&flight_type="+request.getParameter("flight_type")+"&is_flightNo="+request.getParameter("is_flightNo")+"&page=";
            	
            	if(arrivalFlightInfos.length!=0)
            	{
            		if(arrivalFlightInfos.length%10==0)
                    {
                      	if(Integer.parseInt(p)>arrivalFlightInfos.length/10)
                      		response.sendRedirect(basePath+ul_path+Integer.toString(arrivalFlightInfos.length/10)); 
                      			
                    }
                    else
                    {
                      	if(Integer.parseInt(p)>arrivalFlightInfos.length/10 + 1)
                      		response.sendRedirect(basePath+ul_path+Integer.toString(arrivalFlightInfos.length/10 + 1)); 
                      			
                    }
            	}
            	
            	
            	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            	{
            		if(i>=arrivalFlightInfos.length||arrivalFlightInfos.length==0)
        		          break;
            %>
            <li>
              <div class="header-box">
              <%	
              	String airlineFlag = arrivalFlightInfos[i].getFlightCourse().getFlightNumber().substring(0, 2).toLowerCase();
              %>
                <img src="img/airlineLogo/<%=airlineFlag %>.png">
                <p><%=arrivalFlightInfos[i].getFlightCourse().getAirline() %> <strong><%=arrivalFlightInfos[i].getFlightCourse().getFlightNumber() %></strong></p>
              </div>
              <div class="detail-box arrival">
                <div>
                  <p><%=arrivalFlightInfos[i].getFlightCourse().getFrom() %>
                  <%
                  		if(arrivalFlightInfos[i].getFlightCourse().getStop().equals("")||arrivalFlightInfos[i].getFlightCourse().getStop()==null)
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		else
                  		{
                  			out.println("<span class='iconfont icon-plane'></span>");
                  			out.println(arrivalFlightInfos[i].getFlightCourse().getStop());
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		}
                  %> 
                  <!-- <span class="iconfont icon-plane"></span> -->
                  <%=arrivalFlightInfos[i].getFlightCourse().getTo() %></p>
                </div>
                <div>
                  <p>降落时间</p>
                  <%
                  	String[] t1 = arrivalFlightInfos[i].getTime().split("-", 2);
              		String[] t2 = t1[1].split(":");
                  %>
                  <h6><%=t2[0]+":"+t2[1] %></h6>
                </div>
                <div>
                  <p>行李转盘</p>
                  <%
                  String[] t3 = arrivalFlightInfos[i].getLuggageCarousel().split("盘");
                  AirportResource[] airportResources = user.searchAirportResource(arrivalFlightInfos[i].getLuggageCarousel(), "");
                  %>
                  <h6><%=t3[1] %></h6>
                  <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<%=airportResources[0].getLocation() %>">查看位置</span>
                </div>
              </div>
            </li>
            <%
            	}
            	out.println("<div><ul class='pager'>");
            	if(arrivalFlightInfos.length!=0)
            	{
            		if(!p.equals("1"))
                        out.println("<li class='previous'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
             		if(arrivalFlightInfos.length%10==0)
                    {
                    	if(Integer.parseInt(p)!=arrivalFlightInfos.length/10)
                 			out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                    else
                    {
                        if(Integer.parseInt(p)!=arrivalFlightInfos.length/10 + 1)
                 		 	out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
            	}
         	    
                out.println("</ul></div>");
            }
            %>
            
            <%
            if(request.getAttribute("departureFlightInfos")==null&&request.getAttribute("arrivalFlightInfos")==null)
            {
            	User user = new User();
            	DepartureFlightInfo[] allLocalDepartureFlightInfos = user.returnAllLocalDepartureFlightInfo();
            	DepartureFlightInfo[] allInternationalDepartureFlightInfos = user.returnAllInternationalDepartureFlightInfo();
            	int count = 0;
            	for(DepartureFlightInfo output:allLocalDepartureFlightInfos)
            	{
            		if(output!=null)
            			count++;
            	}
            	for(DepartureFlightInfo output:allInternationalDepartureFlightInfos)
            	{
            		if(output!=null)
            			count++;
            	}
            	int j=0;
            	DepartureFlightInfo[] defaultDepartureFlightInfos = new DepartureFlightInfo[count];
            	for(DepartureFlightInfo output:allLocalDepartureFlightInfos)
            	{
            		if(output!=null)
            		{
            			defaultDepartureFlightInfos[j] = output;
            			j++;
            		}
            	}
            	for(DepartureFlightInfo output:allInternationalDepartureFlightInfos)
            	{
            		if(output!=null)
            		{
            			defaultDepartureFlightInfos[j] = output;
            			j++;
            		}
            	}
            	
            	if(defaultDepartureFlightInfos.length!=0)
            	{
            		if(defaultDepartureFlightInfos.length%10==0)
                    {
                      	if(Integer.parseInt(p)>defaultDepartureFlightInfos.length/10)
                      		response.sendRedirect(basePath+"Public/PassengerGuide.jsp?page="+Integer.toString(defaultDepartureFlightInfos.length/10)); 
                      			
                    }
                    else
                    {
                      	if(Integer.parseInt(p)>defaultDepartureFlightInfos.length/10 + 1)
                      		response.sendRedirect(basePath+"Public/PassengerGuide.jsp?page="+Integer.toString(defaultDepartureFlightInfos.length/10 + 1)); 
                      			
                    }
            	}
            	
            	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            	{
            		if(i>=defaultDepartureFlightInfos.length||defaultDepartureFlightInfos.length==0)
        		          break;
            	
            %>
            <li>
              <div class="header-box">
              <%
              	
              	String airlineFlag = defaultDepartureFlightInfos[i].getFlightCourse().getFlightNumber().substring(0, 2).toLowerCase();
              %>
                <img src="img/airlineLogo/<%=airlineFlag %>.png">
                <p><%=defaultDepartureFlightInfos[i].getFlightCourse().getAirline() %> <strong><%=defaultDepartureFlightInfos[i].getFlightCourse().getFlightNumber() %></strong></p>
              </div>
              <div class="detail-box depature">
                <div>
                  <p><%=defaultDepartureFlightInfos[i].getFlightCourse().getFrom() %>
                  <%
                  		if(defaultDepartureFlightInfos[i].getFlightCourse().getStop().equals("")||defaultDepartureFlightInfos[i].getFlightCourse().getStop()==null)
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		else
                  		{
                  			out.println("<span class='iconfont icon-plane'></span>");
                  			out.println(defaultDepartureFlightInfos[i].getFlightCourse().getStop());
                  			out.println("<span class='iconfont icon-plane'></span>");
                  		}
                  %> 
                  <!-- <span class="iconfont icon-plane"></span> -->
                  <%=defaultDepartureFlightInfos[i].getFlightCourse().getTo() %></p>
                </div>
                <div>
                  <p>起飞时间</p>
                  <%
                  	String[] t1 = defaultDepartureFlightInfos[i].getTime().split("-", 2);
              		String[] t2 = t1[1].split(":");
                  %>
                  <h6><%=t2[0]+":"+t2[1] %></h6>
                </div>
                <div>
                  <p>值机柜台</p>
                  <%
                  	String checkincounters = "";
                  	//User user = new User();
              		int flag = 1;
              		String checkincountersAndLocations = "";
              		for(String checkincounteroutput:defaultDepartureFlightInfos[i].getCheckinCounter())
              		{
              			if(checkincounteroutput!=null)
              			{
              				AirportResource[] airportResources = user.searchAirportResource(checkincounteroutput, "");     				
              				
              				String t3[] = checkincounteroutput.split("台");
              				if(flag==1)
              				{
              					checkincounters = checkincounters + t3[1];
              					checkincountersAndLocations = checkincountersAndLocations + "<strong>" + checkincounteroutput + "</strong> " + airportResources[0].getLocation();
              					flag = 0;
              				}
              				else
              				{
              					checkincounters = checkincounters + ", " + t3[1];
              					checkincountersAndLocations = checkincountersAndLocations + "<br><strong>" + checkincounteroutput + "</strong> " + airportResources[0].getLocation();
              				}		
              			}			
              		}
                  %>
                  <h6><%=checkincounters %></h6>
                  <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<%=checkincountersAndLocations %>">查看位置</span>
                  <%-- <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<strong>值机柜台1</strong> 出发大厅东侧<br><strong>值机柜台3</strong> 出发大厅东侧<br><strong>值机柜台5</strong> 出发大厅东侧">查看位置</span> --%>
                </div>
                <div>
                  <p>登机门</p>
                  <%
                  	String[] t4 = defaultDepartureFlightInfos[i].getBoardingGate().split("门");
                  	AirportResource[] airportResources = user.searchAirportResource(defaultDepartureFlightInfos[i].getBoardingGate(), "");
                  %>
                  <h6><%=t4[1] %></h6>
                  <span data-container="body" data-toggle="popover" data-placement="bottom" data-content="<strong><%=defaultDepartureFlightInfos[i].getBoardingGate() %></strong> <%=airportResources[0].getLocation() %>">查看位置</span>
                </div>
              </div>
            </li>
            <%
            	}
                out.println("<div><ul class='pager'>");
                if(defaultDepartureFlightInfos.length!=0)
                {
                	if(!p.equals("1"))
                        out.println("<li class='previous'><a href='"+basePath+"Public/PassengerGuide.jsp?page="+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
              		if(defaultDepartureFlightInfos.length%10==0)
                    {
                    	if(Integer.parseInt(p)!=defaultDepartureFlightInfos.length/10)
                  			out.println("<li class='next'><a href='"+basePath+"Public/PassengerGuide.jsp?page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                    else
                    {
                        if(Integer.parseInt(p)!=defaultDepartureFlightInfos.length/10 + 1)
                  		 	out.println("<li class='next'><a href='"+basePath+"Public/PassengerGuide.jsp?page="+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                }
          		
                out.println("</ul></div>");
            %>
            <%
            }
            %>
            
          </ul>
        </div>
      </div>
      <div id="backToTop-btn" onclick="scroll(0,0)">
        <span class="glyphicon glyphicon-chevron-up"></span>
      </div>
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
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>
    <script type="text/javascript">
      $('form').submit(function (e) {
        var flight_num_exp = /([a-z]{2}[0-9]{4}|[a-z]{1}[0-9]{5}|[0-9]{1}[a-z]{1}[0-9]{4})/i;
        var form_elem = $(this).parent();
        var key_text = form_elem.find('[name = "key"]').val();
        $('[name="is_flightNo"]').val(flight_num_exp.test(key_text));
      });    
    </script>
  

</body></html>