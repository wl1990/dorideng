var setting = {
			async:{
				enable:true,
				url:getRootPath()+"/production/asyncLoad.do",
				autoParam:["id"]
				
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "all"
			},
			data: {
				key:{
					title:"edit"
				},
				simpleData: {
					enable: true
				}
			},
			edit:{
				drag:{
				autoExpandTrigger:true,
				isCopy:true,
				isMove:true,
				prev:true,
				next:true,
				inner:true
				},
				enable:true,
				showRemoveBtn:true,
				showRenameBtn:false,
				removeTitle:"删除产品",
			},
			view:{
				expandSpeed:""
			},
			callback:{
				onCheck:onCheck,
				onRemove:zTreeOnRemove,
				beforeDrop:zTreeBeforeDrop,
				onDrop: zTreeOnDrop,
				onMouseDown: zTreeOnMouseDown,
				beforeExpand:beforeExpand,
				onAsyncSuccess:onAsyncSuccess,
				onAsyncError:onAsyncError
				
			}
		};
		
		var code, log,startTime = 0, endTime = 0, perCount = 100,perTime = 100;
		//拖拽结束前的回调函数,禁止拖拽成根节点
		function zTreeBeforeDrop(treeId,treeNodes,targetNode,moveType){
			return !(targetNode==null || (moveType!="inner" && !targetNode.parentTId))
//			return true;
		};
		//拖拽完成时的事件
		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
			var targetNodeId=null;
			if(targetNode!=null)
				targetNodeId=targetNode.id;
			else
				targetNodeId="";
			var root=getRootPath();
			$.ajax({
				type:"POST",
				url:root+"/production/dragUpdate.do",
				data:{nodeid:treeNodes[0].id,parentid:targetNodeId},
				dataType:"json",
				success:function(data){
					var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
					if(targetNode!=null)
						treeObj.reAsyncChildNodes(targetNode,"refresh");
					else
						treeObj.refresh();
				}
			});
			clearAllElem();
		}
		
		//修改父节点
		function onCheck(e,treeId,treeNode){
			clearAllElem();
			if(treeNode.checked==true){
				$("#newParentId").attr("value",treeNode.id);
			}else{
				$("#newParentId").attr("value",null);
			}
		}
		var code,path,perTime = 100;
		function setCheck() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
		
		//删除菜单
		function zTreeOnRemove(event,treeId,treeNode){
			var root=getRootPath();
			$.ajax({
				type:"POST",
				url:root+"/production/delete.do",
				data:{nodeid:treeNode.id},
				dataType:"json",
				success:function(data){
				}
			});
			/*$("#editForm").submit();*/
			
		}
		
		//修改菜单
		function zTreeOnMouseDown(event, treeId, treeNode) {
			if(treeNode!=null){
				var root=getRootPath();
				$.ajax({
					type:"POST",
					url:root+"/production/getProduction.do",
					data:{nodeid:treeNode.id},
					dataType:"json",
					success:function(data){
						clearAllElem();
//						var obj =(new Function("","return "+data))();
						$("#id").val(data.id);
						$("#selfId").val(data.selfId);
						$("#parentId").val(data.parentId);
						$("#newParentId").val(data.parentId);
						$("#proName").val(data.proname);
						$("#proType").val(data.proType);
						editor.html(data.detail);
						$("#status").val(data.status);
						$.each(data.ppic,function(i,item){
							$("#preview").append("<img width='100' height='100' border='0' style='margin-left:10px;'src="+getRootPath()+item+"/>");
						});
						
					/*	if(data.imageUrl!=null & data.imageUrl!=' '& data.imageUrl!='')
							$("#imghead").attr("src",root+data.imageUrl);
						else
							$("#imghead").attr("src",root+"/resources/server/images/nofoundimg.png");*/
						// 上传图片组件
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
				});
				
			}
		}
		
		//显示子节点前的操作
		function beforeExpand(treeId, treeNode) {
			if(!treeNode.isAjaxing){
				startTime = new Date();
				ajaxGetNodes(treeNode,"refresh");
				return true;
			}else{
				alert("正在加载数据，请稍后展开节点");
				return false;
			}
		}
		
		//异步加载成功后的操作
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			if(!msg||msg.length==0){
				return;
			}
			var zTree=$.fn.zTree.getZTreeObj("treeDemo");
			totalCount=treeNode.count;
			if(treeNode.children.length<totalCount){
				setTimeout(function(){ajaxGetNodes(treeNode);},perTime);
			}else{
				treeNode.icon="";
				zTree.updateNode(treeNode);
				zTree.selectNode(treeNode.children[0]);
				endTime=new Date();
//				var usedTime=(endTime.getTime()-startTime.getTime())/1000;
			}
		}
		
		//异步加载出错
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree=$.fn.zTree.getZTreeObj("treeDemo");
			alert("异步获取数据出现异常");
//			treeNode.icon="";
			zTree.updateNode(treeNode);
//			setCheck();
		}

		function ajaxGetNodes(treeNode, reloadType) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if(reloadType=="refresh"){
				treeNode.icon=getRootPath()+"/resources/server/css/zTreeStyle/img/loading.gif";
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode,reloadType,true);
		}
		
		function getTime(){
			var now=new Date();
			h=now.getHours();
			m=now.getMinutes();
			s=now.getSeconds();
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+" "+ms);
		}
			
		//清空页面的所有值
		function clearAllElem(){
			$("#id").val(null);
			$("#selfId").val(null);
			$("#parentId").val(null);
			$("#newParentId").val(null);
			$("#proName").val(null);
			$("#proType").val(null);
			$("#detail").val(null);
			$("#imghead").attr("src",null);
			$("#errordesc").val(null);
			$("#status").val('0');
			$("#preview").empty();
		}
		
		//js获取项目根路径，如： http://localhost:8083/uimcardprj
		function getRootPath(){
		    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		    var curWwwPath=window.document.location.href;
		    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		    var pathName=window.document.location.pathname;
		    var pos=curWwwPath.indexOf(pathName);
		    //获取主机地址，如： http://localhost:8083
		    var localhostPaht=curWwwPath.substring(0,pos);
		    //获取带"/"的项目名，如：/uimcardprj
		    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		    return(localhostPaht+projectName);
		}
		
		
		
