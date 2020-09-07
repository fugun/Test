 $(function () {
	   var ua = navigator.userAgent.toLowerCase();
	 var body = document.getElementsByTagName("body")[0];
	   body.setAttribute("id","iosiframe");
	   var screenwidth = window.screen.width;

	    if(!/iphone|ipad|ipod/.test(ua)){

	        $("#iosiframe").attr("scrolling","auto");

	    }else{

	        $('#iosiframe').width(screenwidth + 30+ 'px');

	    }
});
