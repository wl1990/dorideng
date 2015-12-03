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
  
   <script>
      	function checkEmpty(b){
      		var val=b.value;
      		var id=b.id;
      		var warn="";
      		if(val==''|| !bal){
      			if(id=="name"){
      				warn="用户名不能为空";
      			}
      		}
      		  document.getElementById("warn").innerHTML=warn;
      	}
      	
      	function check(){
      		var name=document.getElementById("name").value;
      	   if(name=="" || !name){
      	   	 document.getElementById("warn").innerHTML="信息不全";
      	     return false;
      	   }
      	   $("#username").val(name);
      	   return true;
      	}
      </script>
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
      <form method="post" action="<%=request.getContextPath()%>/pwd/recover.do" class="am-form" onsubmit="return check()">
      <label for="name">邮箱:</label>
      <input type="email" id="name" name="username"  onblur="checkEmpty(this)">
      <br>
      <br />
      <div class="am-cf">
        <input type="submit" name="" value="发送邮件" class="am-btn am-btn-primary am-btn-sm am-fl">
      </div>
    </form>
    <hr>
    <p>© 2014 版权所有.  <a href="http://www.bxs.com/" target="_blank">百晓生</a></p>
  </div>
</div>
</body>
</html>
