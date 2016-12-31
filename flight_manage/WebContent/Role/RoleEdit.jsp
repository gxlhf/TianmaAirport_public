<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户与角色管理 - 角色管理 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/CharacterEdit.css">
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
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")==null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li></ul></li>");
          		if(session.getAttribute("priv3")!=null&&session.getAttribute("priv4")!=null)
          			out.println("<li class='dropdown'><a href='#' class='dropdown-toggle curmenu' data-toggle='dropdown' data-hover='dropdown'>用户和角色管理</a><ul class='dropdown-menu' role='menu'><li><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li><li class='curmenu'><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li></ul></li>");
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
            	  out.println("<li role='presentation' class='second-menu-cur'><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li>");
              if(session.getAttribute("priv3")==null)
            	  out.println("<li role='presentation'><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li>");
              if(session.getAttribute("priv3")!=null&&session.getAttribute("priv4")!=null)
            	  out.println("<li role='presentation'><a href='"+basePath+"User/UserAdmin.jsp'>用户管理</a></li><li role='presentation' class='second-menu-cur'><a href='"+basePath+"Role/RoleAdmin.jsp'>角色管理</a></li>");
              %>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a>用户与角色管理</a>
            </li>
            <li>
              <a href="<%=basePath%>Role/RoleAdmin.jsp">角色管理</a>
            </li>
            <li class="active">
            <%
            	if(request.getParameter("roleName")!=null)
            		out.println("修改角色");
            	else
            		out.println("新增角色");
            %>
            </li>
          </ol>
          <%
          	if(request.getParameter("roleName")!=null)
          	{
          		if(admin.searchRole(request.getParameter("roleName"))!=null)
          		{
          			Role roleModify = admin.searchRole(request.getParameter("roleName"));
          		
          %>
          <form action="<%=basePath%>ModifyRole" method="post" class="form-horizontal" role="form" data-toggle="validator">
            <div class="form-group">
              <label for="rolename" class="col-sm-2 control-label">角色名称：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="roleName" value="<%=roleModify.getName() %>" data-error="请填写角色名称*" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="search-name" class="col-sm-2 control-label">描述：</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="roleDesc" rows="3" data-error="请填写角色描述*" required><%=roleModify.getDescription() %></textarea>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="container" id="mult-select-table">
              <div class="col-xs-6">
                <table class="table table-hover select-table" id="left-side-table">
                  <thead>
                    <tr>
                      <th>
                        <span class="glyphicon glyphicon-check th-check"></span>
                      </th>
                      <th>可分配权限</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>
                      <%
                      	if(roleModify.getAuthorityMap().get("用户管理")!=null)
                      	{
                      %>
                      		<span class="glyphicon glyphicon-check td-check"></span>
                      <%
                      	}
                      	else{
                      %>
                        <span class="glyphicon"></span>
                      <%
                      	}
                      %>
                      </td>
                      <td>用户管理</td>
                    </tr>
                    <tr>
                      <td>
                        <%
                      	if(roleModify.getAuthorityMap().get("角色管理")!=null)
                      	{
                      %>
                      		<span class="glyphicon glyphicon-check td-check"></span>
                      <%
                      	}
                      	else{
                      %>
                        <span class="glyphicon"></span>
                      <%
                      	}
                      %>
                      </td>
                      <td>角色管理</td>
                    </tr>
                    <tr>
                      <td>
                        <%
                      	if(roleModify.getAuthorityMap().get("航班信息管理")!=null)
                      	{
                      %>
                      		<span class="glyphicon glyphicon-check td-check"></span>
                      <%
                      	}
                      	else{
                      %>
                        <span class="glyphicon"></span>
                      <%
                      	}
                      %>
                      </td>
                      <td>航班信息管理</td>
                    </tr>
                    <tr>
                      <td>
                        <%
                      	if(roleModify.getAuthorityMap().get("机场设施管理")!=null)
                      	{
                      %>
                      		<span class="glyphicon glyphicon-check td-check"></span>
                      <%
                      	}
                      	else{
                      %>
                        <span class="glyphicon"></span>
                      <%
                      	}
                      %>
                      </td>
                      <td>机场设施管理</td>
                    </tr>
                    <tr>
                      <td>
                        <%
                      	if(roleModify.getAuthorityMap().get("新闻管理")!=null)
                      	{
                      %>
                      		<span class="glyphicon glyphicon-check td-check"></span>
                      <%
                      	}
                      	else{
                      %>
                        <span class="glyphicon"></span>
                      <%
                      	}
                      %>
                      </td>
                      <td>新闻管理</td>
                    </tr>
                    <tr>
                      <td>
                      </td>
                      <td>
                        <button type="button" id="btn-add" class="btn btn-success">添加选中权限</button>
                      </td>
                    </tr>
                  </tbody>
                </table>  
              </div> 
              <div class="col-xs-6">
                <table class="table table-hover select-table" id="right-side-table">
                  <thead>
                    <tr>
                      <th>
                        <span class="glyphicon glyphicon-check th-check"></span>
                      </th>
                      <th>
                        已分配权限
                        <div class="no-height form-group">
                        <div class="no-height">
                            <input class="form-control no-height" type="text" name="rolePriv" class="hide" data-error="请分配权限*" required>
                          </div>
                          <div class="no-height help-block with-errors">*</div>
                        </div>
                      </th>                      
                    </tr>
                  </thead>
                  <tbody>
                  <%
                  	int count=0;
                  	if(roleModify.getAuthorityMap().get("用户管理")!=null)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>用户管理</td>
                    </tr>
                  <%
                  		count++;
                  	}
                  %>
                  <%
                  	if(roleModify.getAuthorityMap().get("角色管理")!=null)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>角色管理</td>
                    </tr>
                  <%
                  		count++;
                  	}
                  %>
                  <%
                  	if(roleModify.getAuthorityMap().get("航班信息管理")!=null)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>航班信息管理</td>
                    </tr>
                  <%
                  		count++;
                  	}
                  %>
                  <%
                  	if(roleModify.getAuthorityMap().get("机场设施管理")!=null)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>机场设施管理</td>
                    </tr>
                  <%
                  		count++;
                  	}
                  %>
                  <%
                  	if(roleModify.getAuthorityMap().get("新闻管理")!=null)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>新闻管理</td>
                    </tr>
                  <%
                  		count++;
                  	}
                  %>
                  
                  <%
                    int privCount = count;
                  	count = 5 - count;
                  	while(count>0)
                  	{
                  %>
                  	<tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                  <%
                  	count--;
                  	}
                  %>
                    <tr>
                      <td>
                      </td>
                      <td>
                        <button type="button" id="btn-remove" class="btn btn-danger">删除选中权限</button>
                      </td>
                    </tr>
                  </tbody>
                </table>  
              </div> 
            </div>

            <script type="text/javascript">
              // var selected_count = <%=privCount%>;
            </script>
  
            <!-- <div class="form-group">
              <div>
                <input class="form-control no-height" type="text" name="rolePriv" class="hide" data-error="请分配权限*" required>
              </div>
              <div class="col-sm-2 help-block with-errors"></div>
            </div> -->

            <div class="col-sm-6 btn-modify">
              <div class="btn-group btn-group-justified">
                <a data-toggle="modal" id="btn-save" class="btn btn-success">修改</a><!--  data-target="#ensureBox" -->
                <a id="btn-cancel" class="btn btn-primary" href="<%=basePath%>Role/RoleAdmin.jsp">取消</a>
              </div>
            </div>
          </form>
          <%
          		}
          	}
          	else
          	{
          %>
          <form action="<%=basePath%>AddRole" method="post" class="form-horizontal" role="form" data-toggle="validator">
            <div class="form-group">
              <label for="rolename" class="col-sm-2 control-label">角色名称：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="roleName" value="" data-error="请填写角色名称*" data-remote="<%=basePath%>AddRole" data-remote-error="该角色名称已被占用*" required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="search-name" class="col-sm-2 control-label">描述：</label>
              <div class="col-sm-6">
                <textarea class="form-control" name="roleDesc" rows="3" data-error="请填写角色描述*" required></textarea>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="container" id="mult-select-table">
              <div class="col-xs-6">
                <table class="table table-hover select-table" id="left-side-table">
                  <thead>
                    <tr>
                      <th>
                        <span class="glyphicon glyphicon-check th-check"></span>
                      </th>
                      <th>可分配权限</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>用户管理</td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>角色管理</td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>航班信息管理</td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>机场设施管理</td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td>新闻管理</td>
                    </tr>
                    <tr>
                      <td>
                      </td>
                      <td>
                        <button type="button" id="btn-add" class="btn btn-success">添加选中权限</button>
                      </td>
                    </tr>
                  </tbody>
                </table>  
              </div> 
              <div class="col-xs-6">
                <table class="table table-hover select-table" id="right-side-table">
                  <thead>
                    <tr>
                      <th>
                        <span class="glyphicon glyphicon-check th-check"></span>
                      </th>
                      <th>
                        已分配权限
                        <div class="no-height form-group">
                        <div class="no-height">
                            <input class="form-control no-height" type="text" name="rolePriv" class="hide" data-error="请分配权限*" required>
                          </div>
                          <div class="no-height help-block with-errors">*</div>
                        </div>
                      </th>      
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>
                        <span class="glyphicon"></span>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td>
                      </td>
                      <td>
                        <button type="button" id="btn-remove" class="btn btn-danger">删除选中权限</button>
                      </td>
                    </tr>
                  </tbody>
                </table>  
              </div> 
            </div>

            <script type="text/javascript">
              // var selected_count = 0;
            </script>

            <div class="col-sm-6 btn-modify">
              <div class="btn-group btn-group-justified">
                <a data-toggle="modal" id="btn-save" class="btn btn-success">新增</a><!--  data-target="#ensureBox" -->
                <a id="btn-cancel" class="btn btn-primary" href="<%=basePath%>Role/RoleAdmin.jsp">取消</a>
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
                  <label class="col-xs-3 control-label">角色名称</label>
                  <div class="col-xs-9">
                    <p id="roleName-ensure" class="form-control-static">321</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">描述</label>
                  <div class="col-xs-9">
                    <p id="roleDesc-ensure" class="form-control-static">321</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">权限</label>
                  <div class="col-xs-9">
                    <p id="rolePriv-ensure" class="form-control-static">123</p>
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
    <script src="<%=basePath%>/js/CharacterEdit.js"></script>
    <script src="<%=basePath%>/js/ensureBox.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
</body></html>