var setting = {
		view:{
			selectedMulti: false
			},
			check: {
				chkboxType:{"Y":"s","N":"ps"},
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				onCheck:onCheck
			}
		};

		var code;
		var log;
		var rights;
		var className = "dark";
		/*选中添加 添加数据
		 * 放弃勾选删除数据
		 * */
		function onCheck(e, treeId, treeNode) {
			var ischecked=treeNode.checked;
			if(ischecked==true){
				showLog(treeNode.id +":"+ treeNode.name);
			}
			if(ischecked==false){
				dropLog(treeNode.id +":"+ treeNode.name);
			}
		}		
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
		}
		
		function dropLog(str){
			if (!log) log = $("#log");
			for (var i=0, l=log.children("li").length; i<l; i++) {
				if(str===log.children("li")[i].innerHTML){
					log.get(0).removeChild(log.children("li")[i]);
				}
			}
		}
		
		function rights(){
			if (!log) log = $("#log");
			if(!rights) rights=$("#rights");
			var str="";
			for (var i=0, l=log.children("li").length; i<l; i++) {
				if(i==(l-1)){
					str=str+log.children("li")[i].innerHTML.split(":")[0];
				}
				else{
					str=str+log.children("li")[i].innerHTML.split(":")[0]+";";
					}
				
			}
			rights.value=str;
			return str;
		}
		
		/**
		 * 初始化显示权限ul
		 */
		function initShowLog(){
			var zTree=$.fn.zTree.getZTreeObj("checktree");
			var nodes=zTree.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				showLog(nodes[i].id +":"+ nodes[i].name);
			}
		}