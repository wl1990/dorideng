<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/admin.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/server/css/zTreeStyle/demo.css" type="text/css"/>
<title></title>
</head>
<body>
         
	<div class="admin-content">
	  <div class="am-cf am-padding">
	    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表单</strong> / <small>form</small></div>
	  </div>
	
	 <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">菜单树<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
           <div class="zTreeDemoBackground" >
      		 <t:zone t:id="treeZone" id="treeZone">
				<ul id="treeDemo" class="ztree" zone="treeZone"></ul>
	   		 </t:zone>
	   	   </div> 
          </div>
     </div>
     
      <div class="am-panel am-panel-default">
       <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}">菜单编辑/添加<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-2">
           <form class="am-form" action="<%=request.getContextPath()%>/rightMenu/save.do" method="post">
           <input type="hidden" id="id" name="id" value="${rmenu.id}"/>
           <input type="hidden" id="selfId" name="selfId" value="${rmenu.selfId }"/>
           <input type="hidden" id="parentId" name="parentId" value="${rmenu.parentId }"/>
           <input type="hidden" id="newParentId"name="newParentId" value="${rmenu.parentId }"/>
          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              菜单名
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" style="height:20px;" id="menuName" name="menuName" value="${rmenu.menuName}">
            </div>
            <div class="am-u-sm-6">*必填，不可重复</div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              菜单URL
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" id="menuUrl" name="menuUrl" value="${rmenu.menuUrl }"style="height:20px;" >
            </div>
            <div class="am-u-sm-6">*必填，不可重复</div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              菜单类型
            </div>
            <div class="am-u-sm-4">
              <input type="text" class="am-input-sm" id="menuType" name="menuType" value="${rmenu.menuType }"style="height:20px;">
            </div>
            <div class="am-u-sm-6">选填</div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-2 am-text-right">
              菜单标记
            </div>
            <div class="am-u-sm-4">
              <input type="text" id="menuMark" name="menuMark" value="${rmenu.menuMark }"class="am-input-sm" style="height:20px;">
            </div>
            <div class="am-u-sm-6">选填</div>
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
<script src="<%=request.getContextPath()%>/resources/server/js/sysrightedit.js"></script>
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