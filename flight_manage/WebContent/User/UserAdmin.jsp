<%@page import="java.awt.print.Printable"%>
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
    <title>用户与角色管理- 用户管理 - 天马机场</title>
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
              <a>用户与角色管理</a>
            </li>
            <li class="active">用户管理</li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form" action="<%=basePath%>SearchAdmin" data-toggle="validator">
            <div class="form-group">
              <label for="user-id" class="col-sm-2 control-label">员工号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="search-empno" pattern='\d{4}' data-error='请填写4位纯数字员工号'>
              </div>
              <div class="col-sm-2 help-block with-errors"></div>
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
            	String ul_path = "SearchAdmin?search-empno="+request.getParameter("search-empno")+"&search-name="+request.getParameter("search-name")+"&search-sex="+request.getParameter("search-sex")+"&search-position="+request.getParameter("search-position")+"&search-role="+request.getParameter("search-role")+"&page=";
            	
   			    if(adminInfos.length!=0)
            	{
            		if(adminInfos.length%10==0)
                    {
                      	if(Integer.parseInt(p)>adminInfos.length/10)
                      		response.sendRedirect(basePath+ul_path+Integer.toString(adminInfos.length/10)); 
                      			
                    }
                    else
                    {
                      	if(Integer.parseInt(p)>adminInfos.length/10 + 1)
                      		response.sendRedirect(basePath+ul_path+Integer.toString(adminInfos.length/10 + 1)); 
                      			
                    }
            	}
            	
            	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            	{
            		if(i>=adminInfos.length||adminInfos.length==0)
        		         break;
            		out.println("<tr data-id='empno="+adminInfos[i].getEmpno()+"'>");
            		out.println("<td><span class='glyphicon'></span></td>");
            		out.println("<td>"+adminInfos[i].getEmpno()+"</td>");
            		out.println("<td>"+adminInfos[i].getName()+"</td>");
            		
            		if(adminInfos[i].getSex()==1)
            			out.println("<td>男</td>");
            		else
            			out.println("<td>女</td>");
            		out.println("<td>"+adminInfos[i].getPhone()+"</td>");
            		out.println("<td>"+adminInfos[i].getMobile()+"</td>");
            		out.println("<td>"+adminInfos[i].getEmail()+"</td>");
            		out.println("<td>"+adminInfos[i].getDepartment()+"</td>");
            		out.println("<td>"+adminInfos[i].getPosition()+"</td>");
            		out.println("<td>"+adminInfos[i].getRole().getName()+"</td>");
            		out.println("</tr>");
            	}
            	
                
                out.println("</tbody></table>");
                out.println("<div><ul class='pager'>");
                if(adminInfos.length!=0)
                {
                	if(!p.equals("1"))
                   	    out.println("<li class='previous'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
           		    if(adminInfos.length%10==0)
                    {
                   	    if(Integer.parseInt(p)!=adminInfos.length/10)
               			    out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                    else
                    {
                   	    if(Integer.parseInt(p)!=adminInfos.length/10 + 1)
               			    out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                }
       		    
                out.println("</ul></div>");
            	out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"User/UserEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteAdmin'>删除</a><a class='btn btn-success' href='"+basePath+"User/UserEdit.jsp'>新增</a></div></div>");
            }
            else
            {
            	Admin[] admins = admin.searchAdmin("", "", -1, "", "");
            	String ul_path = "User/UserAdmin.jsp?page=";
   			    
   			    if(admins.length!=0)
   			    {
   			    	if(admins.length%10==0)
   	                {
   	                  	if(Integer.parseInt(p)>admins.length/10)
   	                  		response.sendRedirect(basePath+ul_path+Integer.toString(admins.length/10)); 
   	                  			
   	                }
   	                else
   	                {
   	                  	if(Integer.parseInt(p)>admins.length/10 + 1)
   	                  		response.sendRedirect(basePath+ul_path+Integer.toString(admins.length/10 + 1)); 
   	                  			
   	                }
   			    }
            	
            	out.println("<table class='table table-hover select-table'><thead><tr>");
              	out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
            	out.println("<th>员工号</th><th>姓名</th><th>性别</th><th>电话</th><th>手机</th><th>邮箱</th><th>部门</th><th>职位</th><th>角色</th></tr></thead><tbody>");
            	for(int i = (Integer.parseInt(p)-1)*10; i < Integer.parseInt(p)*10; i++)
            	{
            		if(i>=admins.length||admins.length==0)
        		         break;
            		out.println("<tr data-id='empno="+admins[i].getEmpno()+"'>");
            		out.println("<td><span class='glyphicon glyphicon'></span></td>");
            		out.println("<td>"+admins[i].getEmpno()+"</td>");
            		out.println("<td>"+admins[i].getName()+"</td>");
            		
            		if(admins[i].getSex()==1)
            			out.println("<td>男</td>");
            		else
            			out.println("<td>女</td>");
            		out.println("<td>"+admins[i].getPhone()+"</td>");
            		out.println("<td>"+admins[i].getMobile()+"</td>");
            		out.println("<td>"+admins[i].getEmail()+"</td>");
            		out.println("<td>"+admins[i].getDepartment()+"</td>");
            		out.println("<td>"+admins[i].getPosition()+"</td>");
            		out.println("<td>"+admins[i].getRole().getName()+"</td>");
            		out.println("</tr>");
            	}
            	out.println("</tbody></table>");
            	out.println("<div><ul class='pager'>");
            	if(admins.length!=0)
            	{
            		if(!p.equals("1"))
                   	    out.println("<li class='previous'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)-1)+"'>← 上一页</a></li>");
           		    if(admins.length%10==0)
                    {
                   	    if(Integer.parseInt(p)!=admins.length/10)
               			    out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
                    else
                    {
                   	    if(Integer.parseInt(p)!=admins.length/10 + 1)
               			    out.println("<li class='next'><a href='"+basePath+ul_path+Integer.toString(Integer.parseInt(p)+1)+"'>下一页 →</a></li>");
                    }
            	}
       		    
                out.println("</ul></div>");
            	out.println("<input class='hide' name='selected-option'><div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a id='btn-modify' class='btn btn-primary' href='"+basePath+"User/UserEdit.jsp'>修改</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"DeleteAdmin'>删除</a><a class='btn btn-success' href='"+basePath+"User/UserEdit.jsp'>新增</a></div></div>");
            	
            }
            // 表格中的data-id属性填写的是需要进行修改或删除时向对应jsp传送的参数字符串
            // 例如需要将变量名为"EmpNo"值为"1004"和变量名为"Type"值为"1"的两个参数传送到jsp页面，则data-id填写的是"EmpNo=1004&Type=1"
            
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
              <strong class="text-danger">删除后将无法撤销！</strong>

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
              <p class="text-center">请选择用户</p>
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
  

</body></html>