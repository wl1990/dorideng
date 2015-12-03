 function checkps(){
      		var pas=$("#password").val();
      		var pas1=$("#surepassword").val();
      		var emailvalue=$("#email").val();
      		 var email=/^([0-9a-zA-Z]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      		if(!pas || pas==""){
      			 $("#isempt").text("密码不能为空");
      			return false;
      		}else{
      			 $("#isempt").text("");
      		}
      		if(pas!=pas1){
      		 $("#warn").text("两次密码不一致");
      		 $("#surepassword").attr("value","");
      		 return false;
      		}else{
      			$("#warn").text("");
      		}
      		if(!email.test(emailvalue)){
      			$("#warnemail").text("邮件格式不对");
      			$("#email").attr("value","");
                return false;
      		}else{
      			$("#warnemail").text("");
      		}
      		return true;
      	}

 