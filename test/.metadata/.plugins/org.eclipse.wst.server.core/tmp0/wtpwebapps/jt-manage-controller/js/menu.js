 $(function () {
	 var url = "menu/All";
     var data ={};            
     $.ajax({
 		url:url,
 		data:data,
 		type:'post',
 		dataType:'json',
 		success: function(result){
 			if(result.code==200){
 				// 获取登入名
 				$("#loginName").html(result.name);
 				getUsreNameABC(result.name);
 				//console.log(result.name);
 				var dataList = result.data;
 				//console.log(dataList);
    			var tbodyHtml = "";
    			//var tbodyHtml_2 = "";
    			var sign = 1;
    			var id = 0;
 				var ul = '</ul></li>'; //一级
 				var uls = ""; //多级
 				 $.each(dataList, function(i, data) { 
 					  
    				 if(data.pid==0){

    					 if(i!=0){
    						 if(sign==2){
        						// console.log(data.pid);
        						 tbodyHtml = tbodyHtml+uls;
        						 uls = "";
        						 sign = 1;
        						 tbodyHtml = tbodyHtml+ul;
        					}else {
        						tbodyHtml = tbodyHtml+ul;
        					}
    						 
    					 }
    					
    					 tbodyHtml = tbodyHtml + ' <li><a href="javascript:;"><i class="iconfont left-nav-li" lay-tips="'+data.name+'">'+data.iconfont+'</i><cite>'+data.name+'</cite> <i class="iconfont nav_right">&#xe6a7;</i></a><ul class="sub-menu">';
    					 
    				 }else {
    					 if(data.url==null || data.url==""){
    						 if(sign==2 && data.pid != id){
     							
    							 tbodyHtml = tbodyHtml+uls;
    							 uls = "";
    							 
    						 }
    					     uls = uls+ul;  
        				     tbodyHtml = tbodyHtml + ' <li><a href="javascript:;"><i class="iconfont">'+data.iconfont+'</i><cite>'+data.name+'</cite>  <i class="iconfont nav_right">&#xe697;</i></a><ul class="sub-menu">';
        					 sign = 2;  
        					 id = data.id
        					// console.log(id);
    					 }else{
    						// console.log(data.pid);
    						 if(sign==2 && data.pid != id){
    							
    							 tbodyHtml = tbodyHtml+uls;
    							 uls = "";
         						 sign = 1;
    						 }
    						 tbodyHtml = tbodyHtml + '<li><a onclick="xadmin.add_tab(\''+data.name+'\',\''+data.url+'\')"><i class="iconfont">&#xe6a7;</i><cite>'+data.name+'</cite></a></li>';
    						 
    					 }  
    				 }

    			 });
 				tbodyHtml = tbodyHtml+ul;
 				$("#nav").html(tbodyHtml);
 				//$("#menu"+sign).html(tbodyHtml_2);
    			 
 			}else{
 				layer.alert(result.msg, {
                     icon: 7
                 });
 			}
 			
 		},
 		error: function(e){
 			//alert("通信失败!");
 		}
 	});
});
 
 	//缓存数据
	function getUsreNameABC(user){
		localStorage.setItem('userNmaeABC',user); // 存入
	}