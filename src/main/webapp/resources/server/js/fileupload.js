function previewImage(file){
	var MAXWIDTH=100;
	var MAXHEIGHT=100;
	var div=document.getElementById("preview");
	$("#rimg").val("");
	
	if(file.files && file.files[0]){ //是chrome
		if(!/image\/\w+/.test(file.files[0].type)){
			alert("只能上传图片");
			return false;
		}
		div.innerHTML='<img id=imghead />';
		var img=document.getElementById("imghead");
		img.onload=function(){
			var rect=clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
			img.width=rect.width;
			img.height=rect.height;
			img.style.marginLeft=rect.left+'px';
			img.style.marginRigh=rect.right+'px';
		}
		var reader=new FileReader();
		reader.onload=function(evt){img.src=evt.target.result};
		//将文件以 data url形式读入页面
		reader.readAsDataURL(file.files[0]);
		//将文件以二进制形式读入页面
		//reader.readAsBinaryString(file);
		//将文件以文本形式读入
		//reader.readAsText(file)
	}else{//是IE浏览器
		var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		file.blur();
		var src=document.selection.createRange().text;
		div.innerHTML='<img id=imghead>';
		var img=document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src=src;
		var rect=clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
		status=('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		div.innerHTML="<div id=divhead " +
				"style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"
				+sFilter+src+"\"'></div>";
	}
  }


	function clacImgZoomParam( maxWidth, maxHeight, width, height ){
		var param={top:0,left:0,width:width,height:height};
		if(width>maxWidth || height>maxHeight){
			rateWidth=width/maxWidth;
			rateHeight=height/maxHeight;
			if(rateWidth>rateHeight){//长宽超过最大值，按最小比例缩小 保持图片的长宽比例不变
				param.width=maxWidth;
				param.height=Math.round(height/rateWidth);
			}else{
				param.width=Math.round(width/rateHeight);
				param.height=maxHeight;
			}
		}
		//保持图片居中显示
		param.left=Math.round((maxWidth-param.width)/2);
		param.top=Math.round((maxHeight-param.height)/2);
		return param;
	}
	
	function previewImage1(file){
		var MAXWIDTH=100;
		var MAXHEIGHT=100;
		var div=document.getElementById("preview1");
		$("#wimg").val("");
		if(file.files && file.files[0]){ //是chrome
			if(!/image\/\w+/.test(file.files[0].type)){
				alert("只能上传图片");
				return false;
			}
			div.innerHTML='<img id=imghead1 />';
			var img=document.getElementById("imghead1");
			img.onload=function(){
				var rect=clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
				img.width=rect.width;
				img.height=rect.height;
				img.style.marginLeft=rect.left+'px';
				img.style.marginRigh=rect.right+'px';
			}
			var reader=new FileReader();
			reader.onload=function(evt){img.src=evt.target.result};
			//将文件以 data url形式读入页面
			reader.readAsDataURL(file.files[0]);
			//将文件以二进制形式读入页面
			//reader.readAsBinaryString(file);
			//将文件以文本形式读入
			//reader.readAsText(file)
		}else{//是IE浏览器
			var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			file.blur();
			var src=document.selection.createRange().text;
			div.innerHTML='<img id=imghead1>';
			var img=document.getElementById('imghead1');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src=src;
			var rect=clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
			status=('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
			div.innerHTML="<div id=divhead1 " +
					"style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"
					+sFilter+src+"\"'></div>";
		}
	}