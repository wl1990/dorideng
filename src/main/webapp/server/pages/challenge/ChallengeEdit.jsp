<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/admin.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/zTreeStyle/demo.css" type="text/css"/>
<title>题目编辑</title>
</head>
<body>
	<div class="admin-content">
	
	
	  <div class="am-cf am-padding">
	    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表单</strong> / <small>form</small></div>
	  </div>
	
	 <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">挑战树<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
           <div class="zTreeDemoBackground" >
				<ul id="treeDemo" class="ztree" zone="treeZone"></ul>
	   	   </div> 
          </div>
     </div>
     
      <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}">挑战编辑/添加<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-2">
           <form class="am-form" action="<%=request.getContextPath()%>/challenge/save.do" method="post" enctype="multipart/form-data">
           <input type="hidden" id="id" name="id" value="${chg.id}"/>
           <input type="hidden" id="selfId" name="selfId" value="${chg.selfId }"/>
           <input type="hidden" id="parentId" name="parentId" value="${chg.parentId }"/>
           <input type="hidden" id="newParentId"name="newParentId" value="${chg.parentId }"/>
          	<input type="hidden" id="creator" name="creator" value="${chg.creator }" />
         	<input type="hidden" id="createTime" name="createTime" value="${chg.createTime }" />
         	<input type="hidden" id="rimg" name="rimg" />
         	<input type="hidden" id="wimg" name="wimg" />
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	挑战标题
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" style="height:20px;" id="title" name="title" value="${chg.title}">
            </div>
            <div class="am-u-sm-6">*必填，不可重复</div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	挑战问题
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" style="height:20px;" id="content" name="content" value="${chg.content}">
            </div>
            <div class="am-u-sm-6">*必填，不可重复</div>
          </div>
          
		<div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	正确答案
            </div>
            <div class="am-u-sm-4">
              <input type="file" id="user-pic" name="rightAnswer" onchange="previewImage(this)"/>
                    <div id="preview">
		   				<img id="imghead" width="100" height="100" border="0" 
		   				/>
					</div>
            </div>
            <div class="am-u-sm-6">*必填</div>
          </div>
          
		  <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	正确描述
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" style="height:20px;" id="rightdesc" name="rightdesc" value="${chgimg.answerdesc}">
            </div>
            <div class="am-u-sm-6">*必填</div>
          </div>	
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	干扰答案
            </div>
            <div class="am-u-sm-4">
              <input type="file" id="user-pic" name="errorAnswer" onchange="previewImage1(this)" />
                    <div id="preview1">
		   				<img id="imghead1" width="100" height="100" border="0" 
		   				/>
					</div>
            </div>
            <div class="am-u-sm-6">*必填</div>
          </div>
          
		  <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              	干扰描述
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" style="height:20px;" id="errordesc" name="errordesc" value="${chgimg1.answerdesc}">
            </div>
            <div class="am-u-sm-6">*必填</div>
          </div>	
         
		  <div class="am-margin">
   			 <button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
   			 <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="javascript:history.go(-1)">放弃保存</button>
  		</div>
        </form>
          </div>
     </div>   

</div>
<!-- content end -->
	
	
	
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

<script src="<%=request.getContextPath()%>/resources/server/js/ztree/jquery.ztree.core-3.5.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/challengeEdit.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/fileupload.js"></script>
     <script>
     	$(document).ready(function(){
     		zNodes=${spec};
		setCheck();			
		$("#level").bind("change", setCheck);
		$("#all").bind("change", setCheck);
	});
  	</script>
</body>
</html>