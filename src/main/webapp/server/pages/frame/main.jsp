<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/resources/server/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/resources/server/i/app-icon72x72@2x.png">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/admin.css">
  
</head>
<body>
	<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>DoriDeng</strong> <small>管理后台</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="<%=request.getContextPath()%>/user/list.do" target="main"><span class="am-icon-home"></span> 首页</a></li>
     	<c:forEach items="${ menulist}" var="menu">
       		<li><a href="<%=request.getContextPath() %>${menu.menuUrl}" class="am-cf" target="main"><span class="am-icon-pencil-square-o"></span>${menu.menuName }<!-- <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span> --></a></li>
     	</c:forEach>
     <!--  <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
        </ul>
      </li> -->
      <li><a  href="<%=request.getContextPath()%>/rightMenu/edit.do" target="main"><span class="am-icon-table"></span>菜单</a></li>
      <li><a href="<%=request.getContextPath()%>/login/loginout.do"><span class="am-icon-sign-out"></span> 注销</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同.</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>Welcome to the Amaze wiki!</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->
  <!-- content start  -->

  	<iframe  src="<%=request.getContextPath()%>/user/list.do" scrolling="no" frameborder="0" width="980" id="main" name="main" style="height:1400px;" runat="server">

  	<script>
  	
  	$("body").bind("keydown", function(event) {
        if (event.keyCode == 116) {
             if(typeof('main')!= 'undefined') {
                frames['main'].window.location.reload();      
                return false;
            } 
        }
    }); 
	</script>
  	</iframe>
  <!-- content end -->


</div>

<footer>
  <hr>
  <p class="am-padding-left">© 2015  <a href="http://www.mycodes.net/" target="_blank">DoriDeng</a></p>
</footer>

<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/resources/server/js/jquery1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/modernizr.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/polyfill/rem.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/polyfill/respond.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=request.getContextPath()%>/resources/server/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/app.js"></script>
</body>
</html>