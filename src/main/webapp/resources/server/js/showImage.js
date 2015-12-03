function showImage(img,title){
	var imgsrc=	$(img).attr("src");
	window.open("showImage.html"+"?imgsrc="+imgsrc+"&imgtitle="+title,title,"width=700px,height=500px");
}
function showImagePath(imgpath,title){
	window.open("showImage.html"+"?imgsrc="+imgpath+"&imgtitle="+title,title,"width=700px,height=800px");
}