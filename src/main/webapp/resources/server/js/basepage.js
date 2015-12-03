$(function(){
	$("#selectall").click(function(){
		$("input[name='checkedid']").attr("checked",this.checked);
	});
	var subBox=$("input[name='checkedid']");
	subBox.click(function(){
		$("#selectall").prop("checked",subBox.length==subBox.filter(":checked").length?true:false);
	});
});

