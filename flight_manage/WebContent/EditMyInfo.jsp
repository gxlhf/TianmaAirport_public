<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="<%=basePath%>">

    <title>修改个人信息 - 天马机场</title>

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
  <%
  	Boolean modifyResult=(Boolean)request.getAttribute("modifyResult");
  	if(modifyResult!=null){
		if(modifyResult==false){
			out.println("<script>alert('修改失败')</script>");
		}else if(modifyResult==true){
	  		out.println("<script>alert('修改成功')</script>");
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
        	if(admin==null)
            	response.sendRedirect(basePath+"index.jsp");
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
              <strong>修改个人信息</strong>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <!-- <h2 class="page-header">用户管理</h2> -->
          <%
          	if(admin!=null){
          		
          %>
          <form action="<%=basePath%>EditMyInfo" method="post" class="form-horizontal" role="form" data-toggle="validator">
            <div class="form-group">
              <label for="user-id" class="col-sm-2 control-label">员工号：</label>
              <div class="col-sm-6">
                 <input type="text" class="form-control" name="user-id" pattern='\\d{4}' data-error="请填写4位员工号*" value="<%=admin.getEmpno() %>" required readonly> 
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            session.setAttribute("empnoValidate", admin.getEmpno());
            %>
            <div class="form-group">
              <label for="user-name" class="col-sm-2 control-label">姓名：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-name" data-error="请填写姓名*" value="<%=admin.getName() %>" required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="user-sex" class="col-sm-2 control-label">性别：</label>
              <div class="btn-group col-sm-6" data-toggle="buttons">
              	<%
              		if(admin.getSex()==1)
              		{
              	%>
		                <label class="btn btn-default col-md-5 active">
		                  <input type="radio" name="user-sex" value="男" data-error="请选择性别*" autocomplete="off" required checked>男
		                </label>
		                <label class="btn btn-default col-md-5">
		                  <input type="radio" name="user-sex" value="女" data-error="请选择性别*" autocomplete="off" required>女
		                </label>
                <%
              		}
                %>
                <%
                	if(admin.getSex()==0)
                	{
                %>
                		<label class="btn btn-default col-md-5">
		                  <input type="radio" name="user-sex" value="男" data-error="请选择性别*" autocomplete="off" required>男
		                </label>
		                <label class="btn btn-default col-md-5 active">
		                  <input type="radio" name="user-sex" value="女" data-error="请选择性别*" autocomplete="off" required checked>女
		                </label>
                <%
                	}
                %>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            
            <div class="form-group">
              <label for="user-pos" class="col-sm-2 control-label">部门：</label>
              <div class="col-sm-6">
                <select class="form-control" name="user-dep" data-error="请选择部门*" required>
                  <option value="<%=admin.getDepartment() %>"><%=admin.getDepartment() %></option>  
                  <%
                  	if(!admin.getDepartment().equals("信息部"))
                  	{
                  %> 
                  		<option value="信息部">信息部</option>
                  <%
                  	}
                  %>
                  <%
                  	if(!admin.getDepartment().equals("空管部"))
                  	{
                  %> 
                  		<option value="空管部">空管部</option>
                  <%
                  	}
                  %> 
                  <%
                  	if(!admin.getDepartment().equals("后勤部"))
                  	{
                  %> 
                  		<option value="后勤部">后勤部</option>
                  <%
                  	}
                  %> 
                  <%
                  	if(!admin.getDepartment().equals("人力资源部"))
                  	{
                  %> 
                  		<option value="人力资源部">人力资源部</option>
                  <%
                  	}
                  %>
                  <%
                  	if(!admin.getDepartment().equals("系统维护部"))
                  	{
                  %> 
                  		<option value="系统维护部">系统维护部</option>
                  <%
                  	}
                  %>         
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            
            <div class="form-group">
              <label for="user-pos" class="col-sm-2 control-label">职位：</label>
              <div class="col-sm-6">
                <select class="form-control" name="user-pos" data-error="请选择职位*" required>
                  <option value="<%=admin.getPosition() %>"><%=admin.getPosition() %></option>
                  <%
                  	for(String output:admin.returnAllPosition())
                  	{
                  		if(!output.equals(admin.getPosition()))
                  		{
                  %>
                  			<option value="<%=output %>"><%=output %></option>
                  <%
                  	
                  		}
                  	}
                  %>
                  <!-- <option value="机场地勤人员">机场地勤人员</option>
                  <option value="信息技术员">信息技术员</option> -->
                </select>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="user-roles" class="col-sm-2 control-label">角色：</label>
              <div class="col-sm-6">
              	<input type="text" class="form-control" name="user-role" data-error="请选择角色*" value="<%=admin.getRole().getName() %>" required readonly>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <%
            session.setAttribute("roleValidate", admin.getRole().getName());
            %>
            <div class="form-group">
              <label for="user-phone" class="col-sm-2 control-label">电话：</label>
              <div class="col-sm-6">
                <input type="text" pattern="\d*" data-pattern-error="请输入正确的电话" class="form-control" name="user-phone" value="<%=admin.getPhone() %>">
              </div>
              <div class="col-sm-2 help-block with-errors"> </div>
            </div>
            <div class="form-group">
              <label for="user-tel" class="col-sm-2 control-label">手机号：</label>
              <div class="col-sm-6">
                <input type="text" pattern="\d*" data-pattern-error="请输入正确的手机号" class="form-control" name="user-tel"  value="<%=admin.getMobile() %>">
              </div>
              <div class="col-sm-2 help-block with-errors"> </div>
            </div>
            <div class="form-group">
              <label for="user-email" class="col-sm-2 control-label">电子邮箱：</label>
              <div class="col-sm-6">
                <input type="text" pattern="^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$" class="form-control" name="user-email" data-error="请填写正确的电子邮箱*" value="<%=admin.getEmail() %>" required>
              </div>
              <div class="col-sm-2 help-block with-errors">*</div>
            </div>
            <div class="form-group">
              <label for="user-password" class="col-sm-2 control-label">修改密码：</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" name="user-password" data-minlength="6" data-required-error="请填写密码*" data-minlength-error="密码长度至少为6位*">
              </div>
              <div class="col-sm-2 help-block with-errors"></div>
            </div>
            <div class="form-group">
              <label for="user-password-check" class="col-sm-2 control-label">密码确认：</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" data-match='[name="user-password"]' data-required-error="请确认密码*" data-match-error="密码不一致*">
              </div>
              <div class="col-sm-2 help-block with-errors"></div>
            </div>
            <div class="col-sm-6 btn-modify">
              <div class="btn-group btn-group-justified">
                <a class="btn btn-success" id="btn-save">修改</a>
                <a class="btn btn-primary" href="#">取消</a>
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
                  <label class="col-xs-3 control-label">员工号：</label>
                  <div class="col-xs-9">
                    <p id="user-id-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">姓名：</label>
                  <div class="col-xs-9">
                    <p id="user-name-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">性别：</label>
                  <div class="col-xs-9">
                    <p id="user-sex-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">部门：</label>
                  <div class="col-xs-9">
                    <p id="user-dep-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">职位：</label>
                  <div class="col-xs-9">
                    <p id="user-pos-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">角色：</label>
                  <div class="col-xs-9">
                    <p id="user-role-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">电话：</label>
                  <div class="col-xs-9">
                    <p id="user-phone-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">手机号：</label>
                  <div class="col-xs-9">
                    <p id="user-tel-ensure" class="form-control-static"> </p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">电子邮箱：</label>
                  <div class="col-xs-9">
                    <p id="user-email-ensure" class="form-control-static"> </p>
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

    <script type="text/javascript" src="<%=basePath%>/js/ensureBox.js"></script>
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/validator.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/public.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/ensureBox.js"></script>
  

</body></html>