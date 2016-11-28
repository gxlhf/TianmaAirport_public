<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- DW6 -->
  <head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登陆 - 天马机场</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css"><!-- 支持时间控件 -->
  </head>
  <!-- The structure of this file is exactly the same as 2col_rightNav.html;
   the only difference between the two is the stylesheet they use -->
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
          <a class="navbar-brand" href="#">
            <img class="navbar-logo" src="img/logo.png">
          </a>
        </div>


        <div class="col-md-6 pull-right" id="personal-info">
          <ul class="list-inline">
            <li id="weather">正在获取天气...</li>
            <li><a class="text-info" href="#">登陆</a></li>
          </ul>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-collapse-lower" id="header">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">用户和角色管理</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">用户管理</a></li>
                <li><a href="#">角色管理</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">航班信息</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">国内到港</a></li>
                <li><a href="#">国内离港</a></li>
                <li><a href="#">国际到港</a></li>
                <li><a href="#">国际离港</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">机场设施管理</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">机场资源</a></li>
                <li><a href="#">物业设施</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">新闻中心</a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#m1">机场介绍</a></li>
                <li><a href="#m2">航班信息</a></li>
                <li><a href="#m3">机场资源</a></li>
                <li><a href="#m4">物业资源</a></li>
                <li><a href="#m5">发布新闻</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <!-- 头部结束 -->

    <!-- 内容开始 -->
    <div class="container-fluid" id="login-underDiv">
      <div class="row">
        <div class="col-md-8" id="sidebar">
        </div>
        <div class="col-md-4" id="content">
          <form role="form" class="form-login">
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                <input name="username" class="form-control" type="text" placeholder="用户名">
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></div>
                <input name="password" class="form-control" type="password" placeholder="密码">
              </div>
            </div>
            <div class="form-group" id="captcha-group">
              <input name="captcha" class="form-control" type="text" placeholder="验证码">
              <img src="img/captcha.jpg">
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- 内容结束 -->

    <!-- 尾部开始 -->
    <footer class="container-fluid">
      <p class="text-center"> <a href="#">About Us</a> · <a href="#">Site Map</a> · <a href="#">Privacy Policy</a> · <a href="#">Contact Us</a> · &copy;2016 软件1401班第三组 </p>
    </footer>
    <!-- 尾部结束 -->

    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="js/public.js"></script>
    <script type="text/javascript" src="https://api.thinkpage.cn/v3/weather/now.json?key=hoqbrzywjm37qvzd&location=changsha"></script>
  </body>
</html>
