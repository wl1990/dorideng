<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/server/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/server/css/admin.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/server/js/jquery-1.8.1.min.js"></script>
<title>Insert title here</title>
 
</head>
<body>
<div class="admin-content">
 <div class="am-cf am-padding" style="float:right;">
      <div class="am-fl am-cf">Language : <a href="list.do?langType=en">English</a>|<a href="list.do?langType=zh">中文</a></div>
 </div>
 <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户列表</strong> / <small>Table</small></div>
 </div>
	<spring:message code="welcome"/>
    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"><a href="${rootPath}/user/edit.do" target="main">新增</a></span> </button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
              <button type="button" class="am-btn am-btn-default" id="deleteall"><span class="am-icon-trash-o"></span> 删除</button>
            </div>

            <div class="am-form-group am-margin-left am-fl">
              <select>
                <option value="option1">所有类别</option>
                <option value="option2">IT业界</option>
                <option value="option3">数码产品</option>
                <option value="option3">笔记本电脑</option>
                <option value="option3">平板电脑</option>
                <option value="option3">只能手机</option>
                <option value="option3">超极本</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button">搜索</button>
                </span>
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form" action="${rootPath}/user/deleteall.do" id="listform">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"><input type="checkbox"  value="false" id="selectall"/></th>
                <th class="table-id">ID</th>
                <th class="table-author">用户名</th>
                <th class="table-author">邮箱</th>
                <th class="table-author">电话</th>
                <th class="table-date">修改日期</th>
                <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <%int i=0; %>
          
          <c:forEach items="${userlist}" var="user">
          <tr>
          <td><input type="checkbox" id="checkedid"  value="${user.id}" name="checkedid" /></td>
          <td><%=++i %></td>
          <td><span>${user.userName}</span></td>
          <td><span>${user.email}</span></td> 
          <td><span>${user.phone}</span></td>
          <td><span>${user.registrationTime}</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
              <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                   
                    <a href="${rootPath}/user/edit?id=${user.id}" target="main" class="am-btn am-btn-default am-btn-xs am-text-danger" style="background-color:white;color:#0e90d2"><span class="am-icon-pencil-square-o">编辑</span></a>
                    <a href="#" class="am-btn am-btn-default am-btn-xs am-text-danger" style="background-color:white;color:#0e90d2" name="${rootPath}/user/delete?id=${user.id}" onclick="suredelete(this.name)"><span class="am-icon-trash-o">删除</span></a>
                  </div>
                </div>
              </div>
            </td>
          </tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="am-cf">
        <span> 共 ${amount} 条记录</span>
		  <div class="am-fr">
		    <ul class="am-pagination">
		     <c:if test="${not empty  userlist}">
		              <li><a href="${rootPath}/user/list?page=${currentpage-1}" target="main">&laquo;</a></li>
		              <c:forEach var="index" begin="1" end="${maxpage}">
		              <c:choose >
		              	<c:when test="${index eq currentpage}">
		                 <li class="am-active"><a href="${rootPath}/user/list?page=${index}" target="main">${index}</a></li>
		                 </c:when>
		                 <c:otherwise>
		                 <li ><a href="${rootPath}/user/list?page=${index}" target="main">${index}</a></li>
		                 </c:otherwise>
		              </c:choose>
		              </c:forEach>
		              <li><a href="${rootPath}/user/list?page=${currentpage+1}" target="main">&raquo;</a></li>
		         </c:if>
		         <c:if test="${empty userlist }">
		          <span>暂无数据</span><a href="${rootPath}/user/edit?id=">添加</a>
		         </c:if>
		    </ul>
		  </div>
		</div>
          
        </form>
      </div>
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
<script src="<%=request.getContextPath()%>/resources/server/js/basepage.js"></script>
<script>
	$(function(){
		$("#deleteall").click(function(){
			$("#listform").submit();
		});
	});
	function suredelete(url){
		if(confirm("确定删除吗？")){
			window.location.href=url;
		}else{
			return false;
		}
	}
</script>
</body>
</html>