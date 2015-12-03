function checkinfo(){
	var password=$("#password").val();
	var confirm=$("#confirmpassword").val();
	if(!password || password==""){
		$("#isempt").text("密码不能为空");
		return false;
	}else{
		 $("#isempt").text("");
	}
	if(password!=confirm){
		 $("#warn").text("两次密码不一致");
  		 $("#confirmpassword").attr("value","");
  		 return false;
	}else{
		$("#warn").text("");
	}
	return true;
}