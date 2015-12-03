//通用js
function openWindow(_url,_name,_iWidth,_iHeight)
{
var url = _url; //转向网页的地址;
var name = _name; //网页名称，可为空;
var iWidth = _iWidth; //弹出窗口的宽度;
var iHeight = _iHeight; //弹出窗口的高度;
var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
iTop = iTop+'px';
var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
iLeft = iLeft+'px';
window.open(url,name,'height='+iHeight+'px,innerHeight='+iHeight+',width='+iWidth+'px,innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
};

function openModalDialog(url,vArguments,iWidth,iHeight){
var ret;
ret =  window.showModalDialog(url,vArguments,"dialogWidth='"+iWidth+"px';dialogHeight='"+iHeight+"px';center=yes;resizable=yes;scroll=no;help=no;location=no;menubar=no");
return ret;
}
//把url的参数编码
function urlEncode(str){
    var val=escape(str);
    val=val.replace(/%u/gi,'$');
    val=val.replace(/%/gi,'$00');
    val=val.replace(/\*/gi,'$002A');
    val=val.replace(/\+/gi,'$002B');
    val=val.replace(/@/gi,'$0040');
    val=val.replace(/\//gi,'$002F');
  return val;
}
//去掉字符串的所有空格
function trimAllspace(str){ 
	var ss= str.replace(/[ ]/g,""); 
//	alert(ss); 
	return ss;
} 
//去掉首尾空格
function trim(str){
	if (str == null)
		return;
	var	text =   str.replace(/(^\s*)|(\s*$)/g,'');
	//alert('text='+text+'|'); 
	return text;
}
//打开上传图片页函数
function upPic(upPage,id,mustMerch){
	var ret;var win={};mustMerch=mustMerch||true;
	if(mustMerch){
	var merchID=getid('merchantname_VALUEHIDDEN').value;
		if(merchID==''){
		 alert('请先选择商户!');return;
	}
		win.merchID=merchID;
	}
	win.id=(id=id||'');
	///product/UploadPic
	ret =  window.showModalDialog(upPage,win,"dialogWidth=500px;dialogHeight=600px;center=yes;resizable=yes;scroll=no;help=no;location=no;menubar=no");
	if(!ret)return;
	var imgdiv=document.getElementById('imgdiv');
	var div='<div class="shangjia_logo04"><div class="shangjia_logoimg">';
	div+= '<img src="'+ret.path+'" width="110" height="110" />';
	div+= '</div>';
	div+='<p><a  t:type="any" t:id="deleteimage" href="javascript:callimage(\''+ret.fileid+'\',\'imgdiv\',\''+ret.fn+'\');">删除</a></p>';
	div+= '</div>';
	imgdiv.innerHTML+=div;
	document.getElementById('fileids').value+='_'+ret.fileid;
}


var points = {
	//支付积分 = 优惠价格*(积分价值 + 积分支付手续费 *0.01)      //积分支付手续费按百分比设定的
	setPayPoints:function(oldPrice,sells,Points, scoreValue,scorePayHandlingFeeOnLine,discountToUser){
	
		getid(sells).value=parseFloat(getid(oldPrice).value * parseFloat(discountToUser*0.01)).toFixed(0);
		var p = parseFloat(getid(sells).value* (parseFloat(scoreValue)+parseFloat(scorePayHandlingFeeOnLine*0.01))).toFixed(0);
		getid(Points).value  = p;
//	getid(Points).value = parseFloat(getid(sells).value*scoreValue).toFixed(0);
},

	chSells:function(oldPrice,sells,Points, scoreValue,scorePayHandlingFeeOnLine,discountToUser){
//		getid(Points).value　= parseFloat(getid(sells).value* (parseFloat(scoreValue+parseFloat(scorePayHandlingFeeOnLine*0.01))) ).toFixed(0);
			var p= parseFloat(getid(sells).value* (parseFloat(scoreValue)+parseFloat(scorePayHandlingFeeOnLine*0.01))).toFixed(0);
					getid(Points).value= p;
	},
	setReturnPoints:function(sells,ReturnPoints,commisionRate,scoreReturnRate ){
	
	//佣金 = 优惠金额  * 商户交易佣金百分比
	//积分返还数 = 佣金 * 积分返还比*100
	var commision = parseFloat(getid(sells).value * parseFloat(getid(commisionRate).value*0.01));
	
	getid(ReturnPoints).value = parseFloat(commision * scoreReturnRate).toFixed(0);
	
}

	
};
