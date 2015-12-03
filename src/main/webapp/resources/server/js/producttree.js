var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				beforeCheck:beforeCheck,
				onCheck:onCheck
			}
		};

		var code, log, className = "dark";
		function beforeCheck(treeId,treeNode){
			className = (className === "dark" ? "":"dark");
			return (treeNode.doCheck!=false);
		}
		function onCheck(e,treeId,treeNode){
			$("#parentType").attr("value",treeNode.id);
		}
		var code;		
		function setCheck() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}