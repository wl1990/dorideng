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
      			if(id=="passWord"){
      				warn="密码不能为空";
      			}
      		}
      		  document.getElementById("warn").innerHTML=warn;
      	}
      	
      	function check(){
      		var name=document.getElementById("name").value;
      		var password=document.getElementById("passWord").value;
      	   if(name=="" || !name || password=="" || !password){
      	   	 document.getElementById("warn").innerHTML="信息不全";
      	     return false;
      	   }
      	   $("#username").val(name);
      	   $("#userpassWord").val(password);
      	   return true;
      	}
      	function forgetpwd(){
				window.location.href=getRootPath()+"/server/pages/login/RecoverPwd.jsp";
      	}
      	
      //js获取项目根路径，如： http://localhost:8083/uimcardprj
		function getRootPath(){
		    //获取当前网址，如： http://localhost:8083/uimcardprj/
		    var curWwwPath=window.document.location.href;
		    //获取主机地址之后的目录，如： /uimcardprj/
		    var pathName=window.document.location.pathname;
		    var pos=curWwwPath.indexOf(pathName);
		    //获取主机地址，如： http://localhost:8083
		    var localhostPaht=curWwwPath.substring(0,pos);
		    //获取带"/"的项目名，如：/uimcardprj
		    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    return(localhostPaht+projectName);
		}
      </script>
</head>
<body>
<div class="header"> 
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>登录</h3>
    <hr>
   
    <br>
    <br>
	<div>
	<span id="warn" style="color:#990000;">${errmassage}</span>
	</div>
    <div>
    <span id="warn" style="color:#990000;"></span>
    </div>
    <form method="post" action="<%=request.getContextPath()%>/login/check.do" class="am-form" onsubmit="return check()">
      <label for="name">邮箱:</label>
      <input type="email" id="name" name="username"  onblur="checkEmpty(this)">
      <br>
      <label for="passWord">密码:</label>
      <input type="password" name="passWord" id="passWord" onblur="checkEmpty(this)">
      <br>
      <label for="remember-me">
        <input id="remember-me" type="checkbox" name="rempwd">
        记住密码
      </label>
      <br />
      <div class="am-cf">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <input type="button" name="forget_pwd" id="forget_pwd" value="忘记密码 ^_^? " onclick="forgetpwd()" class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </form>
    <hr>
    <p>© 2014 版权所有.  <a href="http://www.bxs.com/" target="_blank">百晓生</a></p>
  </div>
</div>
</body>
</html>
