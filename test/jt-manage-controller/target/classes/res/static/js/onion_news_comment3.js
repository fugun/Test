function changeTab(index)
{
	for (var i=1;i<=2;i++) {		
		document.getElementById("li_" + index).className = "selected";
		document.getElementById("div" + i).style.display = "none";
	}
	document.getElementById("div"+index).style.display  ="block";
}

//生成分页内容
function pageText(pageModel,method){
	var currentPage=pageModel.number+1,allPageNumber=pageModel.totalPages,allObjectNumber=pageModel.totalElements,pageNumber=pageModel.size;
	var startPage=1,endPage=1;
	if(currentPage<=10){
		if(allPageNumber<=10){
			startPage=1;
			endPage=allPageNumber;
		}
		if(allPageNumber>10){
			startPage=1;
			endPage=10;
		}
		if(currentPage>5&&allPageNumber<currentPage+4){
			startPage=currentPage-5;
			endPage=allPageNumber;
		}
		if(currentPage>5&&allPageNumber>currentPage+4){
			startPage=currentPage-5;
			endPage=currentPage+4;
		}
	}else{
		if(currentPage>allPageNumber-4){
			startPage=currentPage-5;
			endPage=allPageNumber;
		}
		if(currentPage<allPageNumber-4){
			startPage=currentPage-5;
			endPage=currentPage+4;
		}
	}
	var pageText="<ul>";
	pageText=pageText+"<li class=\"disabled\"><span>总计:"+allObjectNumber+"条</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>每页"+pageNumber+"条</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>总计"+allPageNumber+"页</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>当前"+currentPage+"页</span></li>";
	if(currentPage==1){
		pageText=pageText+"<li class=\"prev disabled\"><a >上一页</a></li>";
	}else{
		pageText=pageText+"<li class=\"prev\"><a href=\"javascript:"+method+"("+(currentPage-1)+");\">上一页</a></li>";
	}
	for(startPage;startPage<=endPage;startPage++){
		if(currentPage==startPage){
			pageText=pageText+"<li class=\"active\"><a href=\"\">"+startPage+"</a></li>";
		}else{
			pageText=pageText+"<li><a href=\"javascript:"+method+"("+startPage+");\">"+startPage+"</a></li>";
		}
	}
	if(currentPage==allPageNumber){
		//pageText=pageText+"<li class=\"next disabled\"><a href=\"javascript;\">下一页</a></li>";
		pageText=pageText+"<li class=\"next disabled\"><a>下一页</a></li>";
		//console.log("上一页");
	}else{
		pageText=pageText+"<li class=\"next\"><a href=\"javascript:"+method+"("+(currentPage+1)+");\">下一页</a></li>";
		//console.log("下一页");
	}
	pageText=pageText+"</ul>";
    return pageText;
}



//生成分页内容
function pageTextV2(pageModel,method){
	var currentPage=pageModel.pageNum,
		allPageNumber=pageModel.pages,
		allObjectNumber=pageModel.total,
		pageNumber=pageModel.size;
	var startPage=1,endPage=1;
	if(currentPage<=10){
		if(allPageNumber<=10){
			startPage=1;
			endPage=allPageNumber;
		}
		if(allPageNumber>10){
			startPage=1;
			endPage=10;
		}
		if(currentPage>5&&allPageNumber<currentPage+4){
			startPage=currentPage-5;
			endPage=allPageNumber;
		}
		if(currentPage>5&&allPageNumber>currentPage+4){
			startPage=currentPage-5;
			endPage=currentPage+4;
		}
	}else{
		if(currentPage>allPageNumber-4){
			startPage=currentPage-5;
			endPage=allPageNumber;
		}
		if(currentPage<allPageNumber-4){
			startPage=currentPage-5;
			endPage=currentPage+4;
		}
	}
	var pageText="<ul>";
	pageText=pageText+"<li class=\"disabled\"><span>总计:"+allObjectNumber+"条</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>每页"+pageNumber+"条</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>总计"+allPageNumber+"页</span></li>";
	pageText=pageText+"<li class=\"disabled\"><span>当前"+currentPage+"页</span></li>";
	if(currentPage==1){
		pageText=pageText+"<li class=\"prev disabled\"><a >上一页</a></li>";
	}else{
		pageText=pageText+"<li class=\"prev\"><a href=\"javascript:"+method+"("+(currentPage-1)+");\">上一页</a></li>";
	}
	for(startPage;startPage<=endPage;startPage++){
		if(currentPage==startPage){
			pageText=pageText+"<li class=\"active\"><a href=\"\">"+startPage+"</a></li>";
		}else{
			pageText=pageText+"<li><a href=\"javascript:"+method+"("+startPage+");\">"+startPage+"</a></li>";
		}
	}
	if(currentPage==allPageNumber){
		pageText=pageText+"<li class=\"next disabled\"><a>下一页</a></li>";
		//console.log("上一页");
	}else{
		pageText=pageText+"<li class=\"next\"><a href=\"javascript:"+method+"("+(currentPage+1)+");\">下一页</a></li>";
		//console.log("上一页");
	}
	pageText=pageText+"</ul>";
	return pageText;
}



Date.prototype.pattern=function(fmt) {           
    var o = {           
    "M+" : this.getMonth()+1, //月份           
    "d+" : this.getDate(), //日           
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
    "H+" : this.getHours(), //小时           
    "m+" : this.getMinutes(), //分           
    "s+" : this.getSeconds(), //秒           
    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
    "S" : this.getMilliseconds() //毫秒           
    };           
    var week = {           
    "0" : "/u65e5",           
    "1" : "/u4e00",           
    "2" : "/u4e8c",           
    "3" : "/u4e09",           
    "4" : "/u56db",           
    "5" : "/u4e94",           
    "6" : "/u516d"          
    };           
    if(/(y+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
    }           
    if(/(E+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
    }           
    for(var k in o){           
        if(new RegExp("("+ k +")").test(fmt)){           
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
        }           
    }           
    return fmt;           
}







