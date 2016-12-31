 <%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>机场设施管理 - 机场资源 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
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
            		out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>机场设施管理</a><ul class='dropdown-menu' role='menu'><li class='curmenu'><a href='"+basePath+"Public/Facility/Resource.jsp'>机场资源</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");
            	else
            		out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>乘机指南</a><ul class='dropdown-menu' role='menu'><li class='curmenu'><a href='"+basePath+"Public/PassengerGuide.jsp'>乘机指引</a></li><li><a href='"+basePath+"Public/Facility/Facility.jsp'>物业设施</a></li></ul></li>");
            		
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
              <%
            if(session.getAttribute("priv0")!=null){
            	out.println("<strong>机场设施管理</strong>");
            }else{
            	out.println("<strong>乘机指南</strong>");
            }
            %>
              <!-- <strong>机场设施管理</strong> -->
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <li role="presentation" class="second-menu-cur">
                  <a href="<%=basePath%>Public/Facility/Resource.jsp">机场资源</a>
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
              <a>机场设施管理</a>
            </li>
            <li>
              <a href="<%=basePath%>Public/Facility/Resource.jsp">机场资源</a>
            </li>
            <li class="active">
            
            <%
                if(request.getParameter("rname")!=null)
                    out.println("修改");
                else
                    out.println("新增");
            %>
            </li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <%
            String name = request.getParameter("rname");
          	User user = new User();
            if(name!=null)
            {
                if(user.searchAirportResource(name, "").length!=0)
                {
            		AirportResource[] resourceModify = user.searchAirportResource(name, ""); 
          %>
          
          <!--修改资源-->
           <form class="form-horizontal" role="form" action="<%=basePath%>ModifyResource" method="post" data-toggle="validator">
            <div class="form-group">
              <label for="resource-type" class="col-sm-2 control-label">资源分类：</label>
              <div class="col-sm-6">
              	<input type="text" class="form-control" name="resource-type" data-error="请填写分类*"  value="<%=resourceModify[0].getType() %>"required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-name" class="col-sm-2 control-label">资源名称：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="resource-name" data-error="请填写资源名称*" value="<%=resourceModify[0].getName() %>" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-site" class="col-sm-2 control-label">位置：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="resource-site" data-error="请填写位置*"  value="<%=resourceModify[0].getLocation() %>"required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-extra" class="col-sm-2 control-label">备注：</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="resource-extra"><%
                if(resourceModify[0].getRemark()!=null)
                	out.println(resourceModify[0].getRemark());
                %></textarea>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
             </div>
              <!--  
              <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-6"></div>
              </div>
              -->
              <div class="col-sm-6 btn-modify">
                <div class="btn-group btn-group-justified">
                  <a class="btn btn-success"  id="btn-save">修改</a>
                  <a class="btn btn-primary" href="<%=basePath%>Public/Facility/Resource.jsp">取消</a>
                </div>
              </div>
            
          </form>
          
           <%
                }
            }
            else{
          %>
          <form class="form-horizontal" role="form" action = "<%=basePath%>AddResource" method = "post" data-toggle = "validator">
            <div class="form-group">
              <label for="resource-type" class="col-sm-2 control-label">资源分类：</label>
              <div class="col-sm-6">
                <select class="form-control" name="resource-type" data-error="请选择资源分类*"  required>
                  <option value = "行李转盘">行李转盘</option>
                  <option value = "登机门">登机门</option>
                  <option value = "值机柜台">值机柜台</option>
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-name" class="col-sm-2 control-label">资源名称：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="resource-name"  data-required-error="请填写资源名称*" data-checkname="checkname" data-validate="true" data-remote="<%=basePath%>AddResource" data-remote-error="该机场资源名称已被占用" required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-site" class="col-sm-2 control-label">位置：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="resource-site" data-error="请填写位置*"  required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="resource-extra" class="col-sm-2 control-label">备注：</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="resource-extra"></textarea>
              </div>
             </div>
              <!--  
              <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-6"></div>
              </div>
              -->
              <div class="col-sm-6 btn-modify">
                <div class="btn-group btn-group-justified">
                  <a class="btn btn-success"  id="btn-save">新增</a>
                  <a class="btn btn-primary" href="<%=basePath%>Public/Facility/Resource.jsp">取消</a>
                </div>
              </div>
            
          </form>
          <%
            }
          %>
        </div>
        <div id="backToTop-btn" onclick="scroll(0,0)">
          <span class="glyphicon glyphicon-chevron-up"></span>
        </div>
        
      <!-- 确认信息弹框开始 -->
      <div id="ensureBox" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
          <div class="modal-content">
            <!-- <div class="modal-header"> -->
              <!-- <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button> -->
              <!-- <h4 class="modal-title" id="myModalLabel">确认需要保存的信息</h4> -->
            <!-- </div> -->
            <div class="modal-body">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
              <h4>以下是即将保存的信息，请确认。<br></h4>
              <strong class="text-danger">提交后将无法撤销</strong>

              <hr>

              <div class="form-horizontal" role="form">
                <div class="form-group">
                  <label class="col-xs-3 control-label">资源分类：</label>
                  <div class="col-xs-9">
                    <p id="resource-type-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">资源名称：</label>
                  <div class="col-xs-9">
                    <p id="resource-name-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">位置：</label>
                  <div class="col-xs-9">
                    <p id="resource-site-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">备注：</label>
                  <div class="col-xs-9">
                    <p id="resource-extra-ensure" class="form-control-static"> </p>
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
      <script type="text/javascript">
        // 验证名称
        $('form').validator({
          custom: {
            checkname: function ($el) {
              var name = $el.val();
              var type = $('[name="resource-type"]').val();
              if(name.search(new RegExp(type + "[0-9]+")) != 0)
                return "请按照资源类别+编号填写资源名称*";
            }
          }
        });
      </script>
  

</body></html>