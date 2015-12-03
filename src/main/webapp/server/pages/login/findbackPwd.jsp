<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>百晓生后台管理系统</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="<%=request.getContextPath()%>/resources/server/i/favicon.png">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
  
</head>
<body>
<div class="header"> 
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>找回密码</h3>
    <hr>
   
    <br>
    <br>
    <div>
	<span id="warn" style="color:#990000;">${errmassage}</span>
	</div>
    <div>
    <span id="warn" style="color:#990000;"></span>
    </div>
	<div>
    <form method="post" action="<%=request.getContextPath()%>/pwd/setnewpwd.do" class="am-form am-form-horizontal" onsubmit="return checkps()">
    	<input type="hidden" value="${email }" name="email"/>
      <div class="am-form-group">
		  	<label for="" class="am-u-sm-3 am-form-label">新密码/ Password</label>
		  	<div class="am-u-sm-9">
		  		<input type="password" id="password" name="newpassword" value="" onblur="checkps()" placeholder="密码/ Password"/>
		  		<small>密码</small>
		  		<small style="color:red;" id="isempt"></small>
		  	</div>
		 </div>
		  
		  <div class="am-form-group">
		  	<label for="" class="am-u-sm-3 am-form-label">确认密码/ Password</label>
		  	<div class="am-u-sm-9">
		  		<input type="password" id="surepassword" value="" onblur="checkps()" placeholder="密码/ Password"/>
		  		<small>和密码一致</small>
		  		<small style="color:red;" id="warn" name="warn"></small>
		  	</div>
		  </div>
		  <br>
      	  <br />
		   <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="submit" class="am-btn am-btn-primary am-btn-xs">保存修改</button>
              <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="javascript:history.go(-1)">放弃保存</button>
            </div>
          </div>
    </form>
  	</div>
  	<br>
  	<br>
  	<br>
  	<br>
    	<hr>
    	<p>© 2014 版权所有.  <a href="http://www.bxs.com/" target="_blank">百晓生</a></p>
    
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/server/js/findbackpwd.js"></script>
</body>
</html>
