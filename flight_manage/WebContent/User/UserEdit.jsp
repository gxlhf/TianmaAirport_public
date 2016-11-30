<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <title>用户与角色管理- 用户管理 - 天马机场</title>

    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="../css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="../css/adminPage.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css">
    <!-- 支持时间控件 -->
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
          <a class="navbar-brand" href="#">
            <img class="navbar-logo" src="../img/logo.png">
          </a>
        </div>
        <div class="col-md-8 pull-right" id="personal-info">
          <ul class="list-inline">
            <li id="weather">正在获取天气...</li>
            <li id="cur-user">
              <span class="glyphicon glyphicon-user"></span>李静 | 已登录</li>
            <li>
              <a class="text-info" href="#">修改个人信息</a>
            </li>
            <li>
              <a class="text-danger" href="#">退出</a>
            </li>
          </ul>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-collapse-lower" id="header">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle curmenu" data-toggle="dropdown" data-hover="dropdown">用户和角色管理</a>
              <ul class="dropdown-menu" role="menu">
                <li class="curmenu">
                  <a href="#">用户管理</a>
                </li>
                <li>
                  <a href="#">角色管理</a>
                </li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">航班信息</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#">国内到港</a>
                </li>
                <li>
                  <a href="#">国内离港</a>
                </li>
                <li>
                  <a href="#">国际到港</a>
                </li>
                <li>
                  <a href="#">国际离港</a>
                </li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">机场设施管理</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#">机场资源</a>
                </li>
                <li>
                  <a href="#">物业设施</a>
                </li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">新闻中心</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#m1">机场介绍</a>
                </li>
                <li>
                  <a href="#m2">航班信息</a>
                </li>
                <li>
                  <a href="#m3">机场资源</a>
                </li>
                <li>
                  <a href="#m4">物业资源</a>
                </li>
                <li>
                  <a href="#m5">发布新闻</a>
                </li>
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
                <li role="presentation" class="second-menu-cur">
                  <a href="#">用户管理</a>
                </li>
                <li role="presentation">
                  <a href="#">角色管理</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="#">主页</a>
            </li>
            <li>
              <a href="#">用户与角色管理</a>
            </li>
            <li>
              <a href="#">用户管理</a>
            </li>
            <li class="active">用户修改</li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label for="user-id" class="col-sm-2 control-label">员工号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-id" disabled="disabled">
              </div>
            </div>
            <div class="form-group">
              <label for="user-name" class="col-sm-2 control-label">姓名：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-name">
              </div>
            </div>
            <div class="form-group">
              <label for="user-sex" class="col-sm-2 control-label">性别：</label>
              <div class="col-sm-6">
                <select class="form-control" name="user-sex">
                  <option>不限</option>
                  <option>男</option>
                  <option>女</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="user-pos" class="col-sm-2 control-label">职位：</label>
              <div class="col-sm-6">
                <select class="form-control" name="user-pos">
                  <option>不限</option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="user-roles" class="col-sm-2 control-label">角色：</label>
              <div class="col-sm-6">
                <select class="form-control" name="user-roles">
                  <option>不限</option>
                  <option>机场地勤人员</option>
                  <option>信息技术员</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="user-phone" class="col-sm-2 control-label">电话：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-phone">
              </div>
            </div>
            <div class="form-group">
              <label for="user-tel" class="col-sm-2 control-label">手机号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-tel">
              </div>
            </div>
            <div class="form-group">
              <label for="user-email" class="col-sm-2 control-label">电子邮箱：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="user-email">
              </div>
            </div>
            <div class="form-group">
              <label for="user-password" class="col-sm-2 control-label">登录密码：</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" name="user-password">
              </div>
            </div>
            <div class="form-group">
              <label for="user-password-check" class="col-sm-2 control-label">密码确认：</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" name="user-password-check">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-2"></div>
            </div>
          </form>
          <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="">修改</a>
              <a class="btn btn-success" href="">取消</a>
            </div>
          </div>
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
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="../js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="../js/public.js"></script>
    <script type="text/javascript" src="https://api.thinkpage.cn/v3/weather/now.json?key=hoqbrzywjm37qvzd&amp;location=changsha"></script>
  

</body></html>