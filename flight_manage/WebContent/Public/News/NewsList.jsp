 <%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type;
if(request.getParameter("type")==null||(!request.getParameter("type").equals("flightInformation")&&!request.getParameter("type").equals("airportResource")&&!request.getParameter("type").equals("facilityResource")))
	type = "flightInformation";
else
	type = request.getParameter("type");
%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><%
    if(type.equals("flightInformation"))
    	out.println("新闻中心-航班信息- 天马机场");
    if(type.equals("airportResource"))
    	out.println("新闻中心-机场资源- 天马机场");
    else
    	out.println("新闻中心-物业资源- 天马机场");
    %></title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/adminPage.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
  </head><body>
  <%
 	 Integer re=(Integer)request.getAttribute("result");
  	 String forward=(String)request.getAttribute("forward");
  	 if(forward!=null&&re!=null){
			if(forward.equals("add")){
				if(re.equals(0)){
		  			out.println("<script>alert('新增失败')</script>");
					}else if(re.equals(1)){
		  			out.println("<script>alert('新增成功')</script>");
					}
			}else if(forward.equals("delete")){
				if(re.equals(0)){
		  			out.println("<script>alert('删除失败')</script>");
					}else if(re.equals(1)){
		  			out.println("<script>alert('删除成功')</script>");
					}
			}else if(forward.equals("update")){
				if(re.equals(0)){
		  			out.println("<script>alert('修改失败')</script>");
					}else if(re.equals(1)){
		  			out.println("<script>alert('修改成功')</script>");
					}
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
              <a href="#" class="dropdown-toggle curmenu" data-toggle="dropdown" data-hover="dropdown">新闻中心</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="<%=basePath%>Public/News/Intro.jsp">机场介绍</a>
                </li>
                <%
                if(type.equals("flightInformation"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li> -->
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=flightInformation">航班信息</a>
                </li>
                <%
                if(type.equals("airportResource"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li> -->
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=airportResource">机场资源</a>
                </li>
                <%
                if(type.equals("facilityResource"))
                	out.println("<li class='curmenu'>");
                else
                	out.println("<li>");
                %>
                <!-- <li> -->
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
              <strong>新闻中心</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <li role="presentation" >
                  <a href="<%=basePath%>Public/News/Intro.jsp">机场介绍</a>
                </li>
                <%
                if(type.equals("flightInformation"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation" class="second-menu-cur"> -->
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=flightInformation">航班信息</a>
                </li>
                <%
                if(type.equals("airportResource"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation" class="second-menu-cur"> -->
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=airportResource">机场资源</a>
                </li>
                <%
                if(type.equals("facilityResource"))
                	out.println("<li role='presentation' class='second-menu-cur'>");
                else
                	out.println("<li role='presentation'>");
                %>
                <!-- <li role="presentation" class="second-menu-cur"> -->
                  <a href="<%=basePath%>Public/News/NewsList.jsp?type=facilityResource">物业资源</a>
                </li>
                <%
                	if(session.getAttribute("priv2")!=null)
                		out.println("<li role='presentation'><a href='"+basePath+"News/NewsEdit.jsp?todo=add'>发布新闻</a></li>");
                %>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="<%=basePath %>Public/News/Intro.jsp">新闻中心</a>
            </li>
            <li class="active"><%
            if(type.equals("flightInformation"))
            	out.println("航班信息");
            if(type.equals("airportResource"))
            	out.println("机场资源");
            if(type.equals("facilityResource"))
            	out.println("物业资源");
            %></li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form" action="<%=basePath %>Public/News/NewsSearch">
          <input type="text" name="type" value="<%=type%>" style="display:none" >
            <div class="form-group">
              <label for="news-title" class="col-sm-2 control-label">新闻标题：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="news-title">
              </div>
            </div>
            <div class="form-group">
              <label for="news-outtime" class="col-sm-2 control-label">发布时间：</label>
              <div class="col-sm-6">
                <input type="text" format="yyyy-mm-dd hh:ii" startview="0" minview="0" maxview="4" class="form-control" name="news-outtime">
              </div>
            </div>
            <%
            if(session.getAttribute("priv2")!=null){
            	out.println("<div class='form-group'><label for='news-outname' class='col-sm-2 control-label'>发布人：</label><div class='col-sm-6'><input type='text' class='form-control' name='news-name'></div></div>");
            }
            %>
            <!-- <div class="form-group">
              <label for="news-outname" class="col-sm-2 control-label">发布人：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="news-outname">
              </div>
            </div> -->
            <div class="form-group">
              <div class="col-sm-2"></div>
              <div class="col-sm-6">
                <button type="submit" class="col-sm-12 btn btn-primary">查询</button>
              </div>
            </div>
          </form>
          
          <table class="table table-hover select-table">
            <thead>
              <tr>
                <%
              if(session.getAttribute("priv2")!=null){
            	  out.println("<th><span class='glyphicon glyphicon-check th-check'></span></th>");
              }else{
            	  out.println("<th></th>");
              }
              %>
                <!-- <th>
                  <span class="glyphicon glyphicon-check th-check"></span>
                </th> -->
                <!-- <th>序号</th> -->
                <th>新闻标题</th>
                <th>发布时间</th>
                <th>发布人</th>
              </tr>
            </thead>
            <tbody>
			<%
				News[] news=(News[])request.getAttribute("news");
							/*
						if(news[i].getKind().equals("航班信息")&&type.equals("flightInformation")||news[i].getKind().equals("机场资源")&&type.equals("airportResource")||news[i].getKind().equals("物业资源")&&type.equals("facilityResource")){
						out.println(" <tr data-id='&news-id="+news[i].getNewsId()+"'>");
						if(session.getAttribute("priv2")!=null){
			                	out.println("<td><span class='glyphicon'></span></td>");
			                }else{
			              	  out.println("<td></td>");
			                }
			                out.println(" <td><a href='"+basePath+"/Public/News/NewsDetail.jsp?id="+news[i].getNewsId()+"'>"+news[i].getTitle()+"</a></td><td>"+news[i].getTime()+"</td><td>"+news[i].getPublisher_name()+"</td> </tr>");
						}*/
				if(news==null){
					List<News>newslist=new LinkedList<News>();
					User user=new User();
					News[] n=user.returnAllNews();
					for(int i=0;i<n.length;i++){
						if(n[i].getKind().equals("航班信息")&&type.equals("flightInformation")||n[i].getKind().equals("机场资源")&&type.equals("airportResource")||n[i].getKind().equals("物业资源")&&type.equals("facilityResource")){
							newslist.add(n[i]);
						}
					}
					int size=newslist.size();
					news=(News[])newslist.toArray(new News[size]);
					/*
					news=user.returnAllNews();
					session.setAttribute("news", news);
					for(int i=0;i<news.length;i++){
						if(news[i].getKind().equals("航班信息")&&type.equals("flightInformation")||news[i].getKind().equals("机场资源")&&type.equals("airportResource")||news[i].getKind().equals("物业资源")&&type.equals("facilityResource")){
						out.println(" <tr data-id='&news-id="+news[i].getNewsId()+"'>");
						 if(session.getAttribute("priv2")!=null){
			                	out.println("<td><span class='glyphicon glyphicon'></span></td>");
			                }else{
			              	  out.println("<td></td>");
			                }
			                out.println(" <td><a href='"+basePath+"Public/News/NewsDetail.jsp?id="+news[i].getNewsId()+"'>"+news[i].getTitle()+"</a></td><td>"+news[i].getTime()+"</td><td>"+news[i].getPublisher_name()+"</td> </tr>");
					}
				}*/
				}
				request.setAttribute("news", news);
				request.getSession().setAttribute("news", news);
				String p=request.getParameter("page");
				if(p==null){
					if(news.length>10){
						for(int i=0;i<10;i++){
							out.println(" <tr data-id='&news-id="+news[i].getNewsId()+"'>");
							 if(session.getAttribute("priv2")!=null){
				                	out.println("<td><span class='glyphicon glyphicon'></span></td>");
				                }else{
				              	  out.println("<td></td>");
				                }
				                out.println(" <td><a href='"+basePath+"Public/News/NewsDetail.jsp?id="+news[i].getNewsId()+"'>"+news[i].getTitle()+"</a></td><td>"+news[i].getTime()+"</td><td>"+news[i].getPublisher_name()+"</td> </tr>");
						}
						
						out.println("  </tbody> </table>");
						out.println("          <div><ul class='pager'> <li class='next'><a href='"+basePath+"Public/News/NewsList.jsp?type="+type+"&page=2'>下一页 →</a></li></ul></div>");
					}else{
						for(int i=0;i<news.length;i++){
							out.println(" <tr data-id='&news-id="+news[i].getNewsId()+"'>");
							 if(session.getAttribute("priv2")!=null){
				                	out.println("<td><span class='glyphicon glyphicon'></span></td>");
				                }else{
				              	  out.println("<td></td>");
				                }
				                out.println(" <td><a href='"+basePath+"Public/News/NewsDetail.jsp?id="+news[i].getNewsId()+"'>"+news[i].getTitle()+"</a></td><td>"+news[i].getTime()+"</td><td>"+news[i].getPublisher_name()+"</td> </tr>");
						}
						
						out.println("  </tbody> </table>");
					}
				
				}else{
					int pageNum=1;
				    try {       
				        pageNum=Integer.valueOf(p);   
				        
				    } catch (Exception e) {   
				          
				    }
					if(pageNum<1||pageNum>(int)((news.length+9)/10)){
						out.println("  </tbody> </table>");
					}else{
						int from=(pageNum-1)*10+1;
						int end=pageNum*10+1<news.length?pageNum+10:news.length;
						for(int i=from;i<end;i++){
							 if(session.getAttribute("priv2")!=null){
				                	out.println("<td><span class='glyphicon glyphicon'></span></td>");
				                }else{
				              	  out.println("<td></td>");
				                }
				                out.println(" <td><a href='"+basePath+"Public/News/NewsDetail.jsp?id="+news[i].getNewsId()+"'>"+news[i].getTitle()+"</a></td><td>"+news[i].getTime()+"</td><td>"+news[i].getPublisher_name()+"</td> </tr>");
						}
						out.println("  </tbody> </table>");
						if(pageNum==1&&news.length>10){
							out.println("          <div><ul class='pager'> <li class='next'><a href='"+basePath+"Public/News/NewsList.jsp?type="+type+"&page=2'>下一页 →</a></li></ul></div>");
						}else if(pageNum>1&&(pageNum+1)<=(news.length+9)/10){
							out.println("<div><ul class='pager'><li class='previous'><a href='"+basePath+"Public/News/NewsList.jsp?type="+type+"&page="+(pageNum-1)+"'>← 上一页</a></li><li class='next'><a href='"+basePath+"Public/News/NewsList.jsp?type="+type+"&page="+(pageNum+1)+"'>下一页 →</a></li></ul></div>");
						}else{
							out.println("<div><ul class='pager'><li class='previous'><a href='"+basePath+"Public/News/NewsList.jsp?type="+type+"&page="+(pageNum-1)+"'>← 上一页</a></li></ul></div>");
						}
					}
					
				}
				
			%>
          
          <%
          if(session.getAttribute("priv2")!=null){
        	  out.println(" <div class='col-sm-6 btn-modify'><div class='btn-group btn-group-justified'><a class='btn btn-success' href='"+basePath+"News/NewsEdit.jsp?type="+type+"&todo=add'>新增</a><a id='btn-delete' class='btn btn-danger' href='"+basePath+"News/NewsDelete?type=null'>删除</a><a id='btn-modify' class='btn btn-primary' href='"+basePath+"News/NewsEdit.jsp?type="+type+"'>修改</a></div></div>");
          }
          %>
          <%-- <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="<%=basePath%>News/NewsEdit.jsp">修改</a>
            </div>
          </div> --%>
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
              <h4>以下是即将删除的信息，请确认。<br></h4>
              <strong class="text-danger">提交后将无法撤销！</strong>

              <hr>

              <div class="form-horizontal" role="form">
                <div class="form-group">
                  <label class="col-xs-3 control-label">新闻标题：</label>
                  <div class="col-xs-9">
                    <p id="news-title-ensure" class="form-control-static">321</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">发布时间：</label>
                  <div class="col-xs-9">
                    <p class="form-control-static">321</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-xs-3 control-label">发布人：</label>
                  <div class="col-xs-9">
                    <p class="form-control-static">123</p>
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
              <p class="text-center">请选择新闻</p>
            </div>

          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div>
      <!-- 报错弹框结束 -->
      
      
      

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
    <script type="text/javascript" src="<%=basePath%>/js/ensureBox.js"></script>
  

</body></html>