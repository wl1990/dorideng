<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/server/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/server/css/admin.css">
<script src="<%=request.getContextPath() %>/resources/server/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/server/js/jquery-1.8.1.min.js"></script>
<title>Insert title here</title>
 <script type="text/javascript">
     	$(document).ready(function(){
     			$("#password").focus();
     		$("#userName").blur(function(){
     			$("#userForm").submit();
     		});
     	});
     
     </script>

</head>
<body>
      <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-8">
                <p>使用本地上传头像。 </p>
                <form class="am-form" action="<%=request.getContextPath() %>/user/uploadfile" method="post" enctype="multipart/form-data">
                  <div class="am-form-group">
                    <%-- <input type="file" id="user-pic" onchange="previewImage(this)"/>
                    <div id="preview">
		   				<img id="imghead" width="100" height="100" border="0" 
		   				<c:if test="${empty user.imageUrl}">src="<%=request.getContextPath() %>/resources/server/images/nofoundimg.png"</c:if>
		   				<c:if test="${not empty user.imageUrl}">src="<%=request.getContextPath() %>${user.imageUrl}"</c:if>
		   				/>
					</div> --%>
                    <p class="am-form-help">请选择要上传的文件...</p>
                    <button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="user-info">
              <p>等级信息</p>
              <div class="am-progress am-progress-sm">
                <div class="am-progress-bar" style="width: 60%"></div>
              </div>
              <p class="user-info-order">当前等级：<strong>LV8</strong> 活跃天数：<strong>587</strong> 距离下一级别：<strong>160</strong></p>
            </div>
            <div class="user-info">
              <p>信用信息</p>
              <div class="am-progress am-progress-sm">
                <div class="am-progress-bar am-progress-bar-success" style="width: 80%"></div>
              </div>
              <p class="user-info-order">信用等级：正常当前 信用积分：<strong>80</strong></p>
            </div>
          </div>
        </div>

      </div>
        <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
         <form  id="userForm" action="<%=request.getContextPath() %>/user/save" method="post" onsubmit="return checkps()" class="am-form am-form-horizontal" enctype="multipart/form-data">
          <input type="hidden" name="id" value="${user.id}"/>
          <input type="hidden" name="password" value="${user.password}"/>
          <div class="am-form-group">
            <label for="userName" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-9">
              <input type="text" id="userName" name="userName" value="${user.userName}"placeholder="姓名 / Name" autocomplete="on">
              <small>输入你的名字，让我们记住你。</small>
              <small style="color:red;" id="repeatName">${checkResult}</small>
            </div>
          </div>
		  
		  <div class="am-form-group">
		  	<label for="" class="am-u-sm-3 am-form-label">密码/ Password</label>
		  	<div class="am-u-sm-9">
		  		<input type="password" id="password" name="newpassword" value="${user.password}" onblur="checkps()" placeholder="密码/ Password"/>
		  		<small>密码</small>
		  		<small style="color:red;" id="isempt"></small>
		  	</div>
		  </div>
		  
		  <div class="am-form-group">
		  	<label for="" class="am-u-sm-3 am-form-label">确认密码/ Password</label>
		  	<div class="am-u-sm-9">
		  		<input type="password" id="surepassword" value="${user.password}" onblur="checkps()" placeholder="密码/ Password"/>
		  		<small>和密码一致</small>
		  		<small style="color:red;" id="warn" name="warn"></small>
		  	</div>
		  </div>
		  
          <div class="am-form-group">
            <label for="email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-9">
              <input type="email" id="email" name="email" placeholder="输入你的电子邮件 / Email" value="${user.email}"  onblur="checkps()" autocomplete="on" />
              <small>邮箱你懂得...</small>
              <small style="color:red;"  id="warnemail" name="warn"></small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
            <div class="am-u-sm-9">
              <input type="tel" id="user-phone" name="phone" value="${user.phone}" placeholder="输入你的电话号码 / Telephone" autocomplete="on" />
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-QQ" class="am-u-sm-3 am-form-label">QQ</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-QQ" name="qq" value="${user.qq}" placeholder="输入你的QQ号码" autocomplete="on">
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-weibo" class="am-u-sm-3 am-form-label">微博 / Twitter</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-weibo" name="weibo"value="${user.weibo}" placeholder="输入你的微博 / Twitter">
            </div>
          </div>
		
		 <div class="am-form-group">
		  <p>使用本地上传头像。 </p>
            <input type="file" id="user-pic" name="myfiles" onchange="previewImage(this)"/>
                    <div id="preview">
		   				<img id="imghead1" width="100" height="100" border="0" 
		   				<c:if test="${empty user.imageUrl}">src="<%=request.getContextPath() %>/resources/server/images/nofoundimg.png"</c:if>
		   				<c:if test="${not empty user.imageUrl}">src="${rootPath}${user.imageUrl}"</c:if>
		   				/>
					</div>
                    <p class="am-form-help">请选择要上传的文件...</p>
          </div>
          
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="submit" class="am-btn am-btn-primary am-btn-xs">保存修改</button>
              <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="javascript:history.go(-1)">放弃保存</button>
            </div>
          </div>
        </form>
      </div>
      </div>
      
      
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/server/js/useredit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/server/js/fileupload.js"></script>

</body>
</html>