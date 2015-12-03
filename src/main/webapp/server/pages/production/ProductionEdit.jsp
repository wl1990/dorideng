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
<!-- 多图片上传 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/diyUpload/css/diyUpload.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/diyUpload/css/webuploader.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/kindeditor/themes/default/default.css" />
<title>产品管理</title>
</head>
<body>

	<div class="admin-content">
	
	
	 <!--  <div class="am-cf am-padding">
	    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表单</strong> / <small>form</small></div>
	  </div> -->
	
	 <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">产品<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
           <div class="zTreeDemoBackground" >
				<ul id="treeDemo" class="ztree" zone="treeZone"></ul>
	   	   </div> 
          </div>
     </div>
     
      <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}">产品编辑/添加<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-2">
	           <form class="am-form" action="<%=request.getContextPath()%>/production/save.do" method="post" enctype="multipart/form-data">
	           <input type="hidden" id="id" name="id" value="${proc.id}"/>
	           <input type="hidden" id="selfId" name="proselfId" value="${proc.proselfId }"/>
	           <input type="hidden" id="parentId" name="proparentId" value="${proc.proparentId }"/>
	           <input type="hidden" id="newParentId"name="newParentId" value="${proc.proparentId}"/>
	           <input type="hidden" id="status"name="status" value="${status}"/>
	          <div class="am-g am-margin-top">
	            <div class="am-u-sm-2 am-text-right">
	              	产品名字
	            </div>
	            <div class="am-u-sm-4">
	              <input type="text" class="am-input-sm" style="height:20px;" id="proName" name="proName" value="${proc.proName}">
	            </div>
	            <div class="am-u-sm-6">*必填，不可重复</div>
	          </div>
	          
	          <div class="am-g am-margin-top">
	            <div class="am-u-sm-2 am-text-right">
	              	产品类型
	            </div>
	            <div class="am-u-sm-4">
	              <input type="text" class="am-input-sm" style="height:20px;" id="proType" name="proType" value="${proc.proType}">
	            </div>
	            <div class="am-u-sm-6">*必填，不可重复</div>
	          </div>
	          <div class="am-g am-margin-top">
	            <div class="am-u-sm-2 am-text-right">
	              	产品介绍
	            </div>
	            <div class="am-u-sm-4">
	              <textarea cols="60" id="detail" name="detail" style="width:600px;height:300px;">${proc.detail }</textarea>
	            </div>
	            	<div class="am-u-sm-6"></div>
	          </div>
	          
			<div class="am-g am-margin-top">
	            <div class="am-u-sm-2 am-text-right">
	              	产品图片
	            </div>
	            <div class="am-u-sm-4">
	            	<div id="preview">
			   		</div>
	            </div>
	            <div class="am-u-sm-6">*选填</div>
	          </div>
			  <div class="am-margin">
	   			 <button type="submit" id="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
	   			 <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="javascript:history.go(-1)">放弃保存</button>
	  		</div>
	        </form>
	        <div class="am-g am-margin-top">
	         <div class="am-u-sm-2 am-text-right">
	              	上传图片
	            </div>
	            <div class="am-u-sm-4">
	        		<div id="procimg"></div>
	            </div>
	            <div class="am-u-sm-6">*添加时,添加完基本信息后才能上传图片</div>
	        </div>
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
<script src="<%=request.getContextPath()%>/resources/server/js/productionEdit.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/js/fileupload.js"></script>
<!-- 多图片上传 -->
<script src="<%=request.getContextPath()%>/resources/server/diyUpload/js/webuploader.html5only.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/diyUpload/js/diyUpload.js"></script>
<script src="<%=request.getContextPath()%>/resources/server/kindeditor/kindeditor-min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">

var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="detail"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});
    </script>

     <script>
     	$(document).ready(function(){
     		zNodes=${spec};
		setCheck();			
		$("#level").bind("change", setCheck);
		$("#all").bind("change", setCheck);
	});
     	var pid=$('#id').val();
     	if(pid!=null && pid!=undefined && pid!=''){
     		$("#procimg").diyUpload({
				url:getRootPath()+'/production/uploadimg.do?productionid='+$('#id').val()+'&status='+$('#status').val(),
				success:function(data){
					console.info(data);
				},
				error:function(err){
					console.info(err);
				}
				
			});
     	}
     
  	</script>
</body>
</html>