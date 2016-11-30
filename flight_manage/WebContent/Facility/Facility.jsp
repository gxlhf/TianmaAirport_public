<html><head>
    <!-- Copyright 2016 软件1401第三组, Inc. All rights reserved. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户与角色管理 - 天马机场</title>
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
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">用户和角色管理</a>
              <ul class="dropdown-menu" role="menu">
                <li>
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
              <a href="#" class="dropdown-toggle  curmenu" data-toggle="dropdown" data-hover="dropdown">机场设施管理</a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#">机场资源</a>
                </li>
                <li class="curmenu">
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
              <strong>机场设施管理</strong>
            </li>
            <li>
              <ul class="nav nav-pills nav-stacked sub-menu" role="tablist">
                <li role="presentation">
                  <a href="#">机场资源</a>
                </li>
                <li role="presentation" class="second-menu-cur">
                  <a href="#">物业设施</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="col-md-10" id="content">
          <ol class="breadcrumb">
            <li>
              <a href="#">机场设施管理</a>
            </li>
            <li class="active">物业设施</li>
          </ol>
          <!-- <h2 class="page-header">用户管理</h2> -->
          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label for="search-id" class="col-sm-2 control-label">设施名称：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="search-id">
              </div>
            </div>
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
                <th>
                  <span class="glyphicon glyphicon-check th-check"></span>
                </th>
                <th>序号</th>
                <th>设施名称</th>
                <th>位置</th>
                <th>备注</th>
              </tr>
            </thead>
            <tbody>
              <tr data-id="10001">
                <td>
                  <span class="glyphicon glyphicon-check"></span>
                </td>
                <td>1</td>
                <td>邮局01</td>
                <td>B1出口</td>
                <td>主要负责用户管理，权限分配等工作</td>
              </tr>
              <tr data-id="10002">
                <td>
                  <span class="glyphicon"></span>
                </td>
                <td>2</td>
                <td>航班信息管理员</td>
                <td>1</td>
                <td>主要负责管理航班</td>
              </tr>
              <tr data-id="10003">
                <td>
                  <span class="glyphicon"></span>
                </td>
                <td>3</td>
                <td>机场信息管理员</td>
                <td>1</td>
                <td>主要负责管理机场设施</td>
              </tr>
              <tr data-id="10003">
                <td>
                  <span class="glyphicon"></span>
                </td>
                <td>4</td>
                <td>新闻发布员</td>
                <td>1</td>
                <td>主要负责管理新闻</td>
              </tr>
              <tr data-id="10003">
                <td>
                  <span class="glyphicon"></span>
                </td>
                <td>...</td>
                <td>李静</td>
                <td>1</td>
                <td>女</td>
              </tr>
            </tbody>
          </table>
          <div>
            <ul class="pager">
              <li class="previous">
                <a href="#">← 上一页</a>
              </li>
              <li class="next">
                <a href="#">下一页 →</a>
              </li>
            </ul>
          </div>
          <div class="col-sm-6 btn-modify">
            <div class="btn-group btn-group-justified">
              <a class="btn btn-primary" href="">修改</a>
              <a class="btn btn-danger" href="">删除</a>
              <a class="btn btn-success" href="">新增</a>
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