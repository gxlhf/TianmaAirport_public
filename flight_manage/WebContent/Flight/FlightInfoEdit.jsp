<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>航班信息管理 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-tokenfield.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/tokenfield-typeahead.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
    <link rel="stylesheet" href="<%=basePath%>/css/jquery-ui.min.css">
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
                <li class="curmenu">
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
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/DepartureFlightInfoCheck.jsp?area=local">国内离港</a>
                </li>
                <li role="presentation">
                  <a href="<%=basePath%>Public/Flight/ArrivalFlightInfoCheck.jsp?area=international">国际到港</a>
                </li>
                <li role="presentation">
                  <a href="Public/Flight/DepartureFlightInfoCheck.jsp">国际离港</a>
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
            <!-- <li>
              <a href="#">国内到港</a>
            </li> -->
            <%
            if(request.getParameter("flightNumber")!=null&&request.getParameter("time")!=null&&request.getParameter("area")!=null&&request.getParameter("type")!=null)
            	out.println("<li class='active'>修改</li>");
            else
            	out.println("<li class='active'>新增</li>");
            %>
            <!-- <li class="active">新增</li> -->
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <%
          if(request.getParameter("flightNumber")!=null&&request.getParameter("time")!=null&&request.getParameter("area")!=null&&request.getParameter("type")!=null)
          {
        	  /* System.out.println(request.getParameter("time")); */
        	  String[] t1 = request.getParameter("time").split(":");
        	  String time = t1[0]+":"+t1[1];
        	  User user = new User();
        	  DepartureFlightInfo[] departureFlightModifyInfo = null;
        	  ArrivalFlightInfo[] arrivalFlightModifyInfo = null;
        	  if(request.getParameter("type").equals("departure"))
        	  {
        		  departureFlightModifyInfo = user.searchDepartureFlightInfo("", request.getParameter("flightNumber"), "", time);
        	      /* System.out.println(departureFlightModifyInfo[0].getFlightCourse().getAirline()); */
        	  }
        	  if(request.getParameter("type").equals("arrival"))
        	  {
        		  arrivalFlightModifyInfo = user.searchArrivalFlightInfo("", request.getParameter("flightNumber"), "", time);
        		  /* System.out.println(arrivalFlightModifyInfo[0].getFlightCourse().getAirline()); */
        	  }
        		  
        	  /* System.out.println(t1[0]+":"+t1[1]); */
          %>
          <form action="PostTest" method="post" class="form-horizontal" role="form" data-toggle="validator">
            <div class="form-group">
              <label for="flight-no" class="col-sm-2 control-label">航班号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="flight-no" pattern="([A-z]{2}[0-9]{4}|[A-z][0-9]{5}|[0-9][A-z]{1}[0-9]{4})" data-required-error='请填写航班号*' data-pattern-error='请填写正确的航班号*' 
                value="<%
                if(request.getParameter("type").equals("departure"))
                	out.println(departureFlightModifyInfo[0].getFlightCourse().getFlightNumber());
                if(request.getParameter("type").equals("arrival"))
                	out.println(arrivalFlightModifyInfo[0].getFlightCourse().getFlightNumber());
                %>" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            if(request.getParameter("type").equals("departure"))
            {
            %>
            <div class="form-group">
              <label for="flight-dep-time" class="col-sm-2 control-label">离港时间：</label>
              <div class="col-sm-6">
                <input type="datetime" format="yyyy-mm-dd hh:ii" startview="0" minview="0" maxview="4" class="form-control" name="flight-dep-time" data-required-error='请填写离港时间*' value="<%=time %>" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            }
            if(request.getParameter("type").equals("arrival"))
            {
            %>
            <div class="form-group">
              <label for="flight-arr-time" class="col-sm-2 control-label">到港时间：</label>
              <div class="col-sm-6">
                <input type="datetime" format="yyyy-mm-dd hh:ii" startview="0" minview="0" maxview="4" class="form-control" name="flight-arr-time" data-required-error='请填写到港时间*' value="<%=time %>" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            }
            %>
            <div class="form-group">
              <label for="flight-from" class="col-sm-2 control-label">始发地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-from" data-required-error='请选择始发地*' required>
                  <%
                  if(request.getParameter("type").equals("departure"))
                	  out.println("<option value='"+departureFlightModifyInfo[0].getFlightCourse().getFrom()+"'>"+departureFlightModifyInfo[0].getFlightCourse().getFrom()+"</option>");
            	  if(request.getParameter("type").equals("arrival"))
            	  {
            		  out.println("<option value='"+arrivalFlightModifyInfo[0].getFlightCourse().getFrom()+"'>"+arrivalFlightModifyInfo[0].getFlightCourse().getFrom()+"</option>");
            		  if(request.getParameter("area").equals("local"))
                      {
                		  /* String[] result = user.returnAllLocalFrom(); */
                		  String[] localFrom = {"成都","宁波","昆明","哈尔滨","重庆","温州","厦门","福州","青岛","天津","西安","三亚","北京","南昌","杭州","南京","张家界","广州","西宁","北海","珠海","太原","揭阳","上海浦东","济南","大连","长春","海口","运城","乌鲁木齐","深圳","兰州","上海虹桥","贵阳","丽江","南宁","景洪","呼和浩特","泉州","绵阳","泸州","盐城","石家庄","无锡","烟台"};
                		  for(String output:localFrom)
                		  {
                			  if(request.getParameter("type").equals("departure"))
                			  {
                				  if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
                					  out.println("<option value='"+output+"'>"+output+"</option>");
                			  }
                			  if(request.getParameter("type").equals("arrival"))
                			  {
                				  if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
                					  out.println("<option value='"+output+"'>"+output+"</option>");
                			  }
                		  }
                      }
                	  if(request.getParameter("area").equals("international"))
                	  {
                		  String[] internationalFrom = {"首尔","法兰克福","曼谷","吉隆坡","新加坡"};
                	  	  for(String output:internationalFrom)
                	  	  {
                	  	      if(request.getParameter("type").equals("departure"))
              			      {
              				      if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
              					      out.println("<option value='"+output+"'>"+output+"</option>");
              			      }
              			      if(request.getParameter("type").equals("arrival"))
              			      {
              				      if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
              					      out.println("<option value='"+output+"'>"+output+"</option>");
              			      }
                	  	  }
                	  }
            	  }
            		  
            	  
                  %>
                  <!-- <option value=""></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option> -->
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-via" class="col-sm-2 control-label">经停地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-via">
                  <%
                  if(request.getParameter("type").equals("departure"))
                  {
                	  if(departureFlightModifyInfo[0].getFlightCourse().getStop()!=null&&departureFlightModifyInfo[0].getFlightCourse().getStop()!="")
                	      out.println("<option value='"+departureFlightModifyInfo[0].getFlightCourse().getStop()+"'>"+departureFlightModifyInfo[0].getFlightCourse().getStop()+"</option>");
              		  else
              			  out.println("<option value=''></option>");
                  }
            	  if(request.getParameter("type").equals("arrival"))
            	  {
            		  if(arrivalFlightModifyInfo[0].getFlightCourse().getStop()!=null&&arrivalFlightModifyInfo[0].getFlightCourse().getStop()!="")
            		      out.println("<option value='"+arrivalFlightModifyInfo[0].getFlightCourse().getStop()+"'>"+arrivalFlightModifyInfo[0].getFlightCourse().getStop()+"</option>");
            		  else
            			  out.println("<option value=''></option>");
            	  }
            	  if(request.getParameter("area").equals("local"))
            	  {
            		  String[] stopStation = {"青岛","西安","西宁","烟台","济南","杭州","昆明","宁波","天津","呼和浩特","南京"};
            		  for(String output:stopStation)
            	  	  {
            	  	      if(request.getParameter("type").equals("departure"))
          			      {
          				      if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
          					      out.println("<option value='"+output+"'>"+output+"</option>");
          			      }
          			      if(request.getParameter("type").equals("arrival"))
          			      {
          				      if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
          					      out.println("<option value='"+output+"'>"+output+"</option>");
          			      }
            	  	  }
            	  }
            	  if(request.getParameter("area").equals("international"))
            	  {
            		  String[] internationalStop = {"首尔","法兰克福","曼谷","吉隆坡","新加坡"};
            	  	  for(String output:internationalStop)
            	  	  {
            	  	      if(request.getParameter("type").equals("departure"))
          			      {
          				      if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
          					      out.println("<option value='"+output+"'>"+output+"</option>");
          			      }
          			      if(request.getParameter("type").equals("arrival"))
          			      {
          				      if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
          					      out.println("<option value='"+output+"'>"+output+"</option>");
          			      }
            	  	  }
            	  }
                  %>
                  <!-- <option></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option> -->
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="flight-to" class="col-sm-2 control-label">目的地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-to" data-required-error='请选择目的地*' required>
                  <%
                  if(request.getParameter("type").equals("arrival"))
                	  out.println("<option value='"+arrivalFlightModifyInfo[0].getFlightCourse().getTo()+"'>"+arrivalFlightModifyInfo[0].getFlightCourse().getTo()+"</option>");
            	  if(request.getParameter("type").equals("departure"))
            	  {
            		  out.println("<option value='"+departureFlightModifyInfo[0].getFlightCourse().getTo()+"'>"+departureFlightModifyInfo[0].getFlightCourse().getTo()+"</option>");
            		  if(request.getParameter("area").equals("local"))
                      {
                		  /* String[] result = user.returnAllLocalFrom(); */
                		  String[] localTo = {"成都","宁波","昆明","哈尔滨","重庆","温州","厦门","福州","青岛","天津","西安","三亚","北京","南昌","杭州","南京","张家界","广州","西宁","北海","珠海","太原","揭阳","上海浦东","济南","大连","长春","海口","运城","乌鲁木齐","深圳","兰州","上海虹桥","贵阳","丽江","南宁","景洪","呼和浩特","泉州","绵阳","泸州","盐城","石家庄","无锡","烟台"};
                		  for(String output:localTo)
                		  {
                			  if(request.getParameter("type").equals("departure"))
                			  {
                				  if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
                					  out.println("<option value='"+output+"'>"+output+"</option>");
                			  }
                			  if(request.getParameter("type").equals("arrival"))
                			  {
                				  if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
                					  out.println("<option value='"+output+"'>"+output+"</option>");
                			  }
                		  }
                      }
                	  if(request.getParameter("area").equals("international"))
                	  {
                		  String[] internationalTo = {"首尔","法兰克福","曼谷","吉隆坡","新加坡"};
                	  	  for(String output:internationalTo)
                	  	  {
                	  	      if(request.getParameter("type").equals("departure"))
              			      {
              				      if(!output.equals(departureFlightModifyInfo[0].getFlightCourse().getFrom()))
              					      out.println("<option value='"+output+"'>"+output+"</option>");
              			      }
              			      if(request.getParameter("type").equals("arrival"))
              			      {
              				      if(!output.equals(arrivalFlightModifyInfo[0].getFlightCourse().getFrom()))
              					      out.println("<option value='"+output+"'>"+output+"</option>");
              			      }
                	  	  }
                	  }
            	  }
                  %>
                  <!-- <option></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option> -->
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            
            <div class="form-group">
              <label for="flight-airline" class="col-sm-2 control-label">航空公司：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" name="flight-airline" data-required-error='请选择航空公司*' required>
                  <%
                  if(request.getParameter("type").equals("departure"))
                	  out.println("<option value='"+departureFlightModifyInfo[0].getFlightCourse().getAirline()+"'>"+departureFlightModifyInfo[0].getFlightCourse().getAirline()+"</option>");
            	  if(request.getParameter("type").equals("arrival"))
                      out.println("<option value='"+arrivalFlightModifyInfo[0].getFlightCourse().getAirline()+"'>"+arrivalFlightModifyInfo[0].getFlightCourse().getAirline()+"</option>");
                  %>
                  <!-- <option value="航司1">
                  <option value="航司2">
                  <option value="航司3">
                  <option value="航司4">
                  <option value="航司5"> -->
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            if(request.getParameter("type").equals("arrival"))
            {
            %>
            <div class="form-group">
              <label for="flight-baggage" class="col-sm-2 control-label">行李转盘：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" id="inp-flight-baggage" name="flight-baggage" data-required-error='请选择行李转盘*' required>
                  <option value=""></option>
                  <option value="转盘1">转盘1</option>
                  <option value="转盘2">转盘2</option>
                  <option value="转盘3">转盘3</option>
                  <option value="转盘4">转盘4</option>
                  <option value="转盘5">转盘5</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            }
            if(request.getParameter("type").equals("departure"))
            {
            %>
            <div class="form-group">
              <label for="flight-counter" class="col-sm-2 control-label">值机柜台：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control no-height" name="flight-counter" id="inp-flight-counter-mirror" data-required-error='请选择值机柜台*' required>
                <input type="text" class="form-control" id="inp-flight-counter">
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>

              <!-- 放置值机柜台信息 -->
              <script>
                var counterList = ['值机柜台1', '值机柜台2', '值机柜台3', '值机柜台4', '值机柜台5', '值机柜台6', '值机柜台8', '值机柜台9', '值机柜台10'];
              </script>

              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-gate" class="col-sm-2 control-label">登机门：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" id="inp-flight-gate" list="list-gate" name="flight-gate" data-required-error='请选择登机门*' required>
                  <option value="登机门1"></option>
                  <option value="登机门1">登机门1</option>
                  <option value="登机门2">登机门2</option>
                  <option value="登机门3">登机门3</option>
                  <option value="登机门4">登机门4</option>
                  <option value="登机门5">登机门5</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            }
            %>
            <div class="col-sm-6 btn-modify">
              <div class="btn-group btn-group-justified">      
                <a id="btn-save" class="btn btn-success">新增</a>
                <a class="btn btn-primary" href="">取消</a>
              </div>
            </div>
          </form>
          <%
          }
          else
          {
          %>
          <form action="PostTest" method="get" class="form-horizontal" role="form" data-toggle="validator">
            <div class="form-group">
              <label for="flight-no" class="col-sm-2 control-label">航班号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="flight-no" pattern="([A-z]{2}[0-9]{4}|[A-z][0-9]{5}|[0-9][A-z]{1}[0-9]{4})" data-required-error='请填写航班号*' data-pattern-error='请填写正确的航班号*' required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-dep-time" class="col-sm-2 control-label">离港时间：</label>
              <div class="col-sm-6">
                <input type="datetime" format="yyyy-mm-dd hh:ii" startview="0" minview="0" maxview="4" class="form-control" name="flight-dep-time" data-required-error='请填写离港时间*' required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-arr-time" class="col-sm-2 control-label">到港时间：</label>
              <div class="col-sm-6">
                <input type="datetime" format="yyyy-mm-dd hh:ii" startview="0" minview="0" maxview="4" class="form-control" name="flight-arr-time" data-required-error='请填写到港时间*' required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-from" class="col-sm-2 control-label">始发地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-from" data-required-error='请选择始发地*' required>
                  <option></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-to" class="col-sm-2 control-label">目的地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-to" data-required-error='请选择目的地*' required>
                  <option></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-via" class="col-sm-2 control-label">经停地：</label>
              <div class="col-sm-6">
                <select class="form-control" name="flight-via">
                  <option></option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="flight-airline" class="col-sm-2 control-label">航空公司：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" name="flight-airline" data-required-error='请选择航空公司*' required>
                  <option></option>
                  <option>航司1</option>
                  <option>航司2</option>
                  <option>航司3</option>
                  <option>航司4</option>
                  <option>航司5</option>
                </select>
                <!-- jQuery UI Autocomplete -->
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-baggage" class="col-sm-2 control-label">行李转盘：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" id="inp-flight-baggage" name="flight-baggage" data-required-error='请选择行李转盘*' required>
                  <option></option>
                  <option>转盘1</option>
                  <option>转盘2</option>
                  <option>转盘3</option>
                  <option>转盘4</option>
                  <option>转盘5</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="flight-counter" class="col-sm-2 control-label">值机柜台：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control no-height" name="flight-counter" id="inp-flight-counter-mirror" data-required-error='请选择值机柜台*' required>
                <input type="text" class="form-control" id="inp-flight-counter">
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>

              <!-- 放置值机柜台信息 -->
              <script>
                var counterList = ['值机柜台1', '值机柜台2', '值机柜台3', '值机柜台4', '值机柜台5', '值机柜台6', '值机柜台8', '值机柜台9', '值机柜台10'];
              </script>

            </div>
            <div class="form-group">
              <label for="flight-gate" class="col-sm-2 control-label">登机门：</label>
              <div class="col-sm-6">
                <select type="text" class="form-control" id="inp-flight-gate" list="list-gate" name="flight-gate" data-required-error='请选择登机门*' required>
                  <option></option>
                  <option>登机门2</option>
                  <option>登机门3</option>
                  <option>登机门4</option>
                  <option>登机门5</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <!-- <div class="form-group">
              <label for="flight-gate" class="col-sm-2 control-label">登机门：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" id="inp-flight-gate1" name="flight-gate" data-required-error='请选择登机门*' required>
                <div>
                  <input type="text" class="form-control" id="inp-flight-gate1-org">
                </div>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div> -->
            <div class="col-sm-6 btn-modify">
              <div class="btn-group btn-group-justified">      
                <a id="btn-save" class="btn btn-success">新增</a>
                <a class="btn btn-primary" href="">取消</a>
              </div>
            </div>
          </form>
          <%
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
              <h4>以下是即将保存的信息，请确认。<br></h4>
              <strong class="text-danger">提交后将无法撤销</strong>

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
                  <label class="col-xs-3 control-label">到港时间：</label>
                  <div class="col-xs-9">
                    <p id="flight-arr-time-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">始发地：</label>
                  <div class="col-xs-9">
                    <p id="flight-from-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">目的地：</label>
                  <div class="col-xs-9">
                    <p id="flight-to-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">经停地：</label>
                  <div class="col-xs-9">
                    <p id="flight-via-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">航空公司：</label>
                  <div class="col-xs-9">
                    <p id="flight-airline-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">行李转盘：</label>
                  <div class="col-xs-9">
                    <p id="flight-baggage-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">值机柜台：</label>
                  <div class="col-xs-9">
                    <p id="flight-counter-ensure" class="form-control-static"> </p>
                  </div>
                </div>
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
              <button type="button" class="btn btn-primary">保存</button>
            </div>

          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div>
      <!-- 确认信息弹框结束 -->

    </div>
    <!-- 内容结束 -->
    <!-- 尾部开始 -->
    <footer class="container-fluid">
      <p class="text-center">
        <a href="#">About Us</a>·
        <a href="#">Site Map</a>·
        <a href="#">Privacy Policy</a>·
        <a href="#">Contact Us</a>· ©2016 软件1401班第三组
      </p>
    </footer>
    <!-- 尾部结束 -->



    <!-- <script src="<%=basePath%>/js/jquery-3.1.1.min.js"></script> -->
    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/validator.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-tokenfield.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/ensureBox.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/FlightInfoEdit.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>

</body>
</html>