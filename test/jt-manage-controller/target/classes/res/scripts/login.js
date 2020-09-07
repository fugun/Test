var url = "";
									
									
$(function(){		
	$('#count').focus();
	$('#login').click(loginAction);
	var pathName=window.document.location.pathname;
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	url =window.location.protocol + '//' + window.location.host+projectName+"/";
	RefreshCode();	
	//timer2=window.setTimeout("RefreshCode()",2000);
	timer2 = window.setInterval("RefreshCode()",60000);
});

//点击获取验证码
function getVerify(obj){
	//$("#imgVerify").attr("src", "");
	 obj.src = url + "/user/getVerify?"+Math.random();
}

//刷新验证码
function RefreshCode(){
	$("#imgVerify").attr("src",url + "/user/getVerify?"+Math.random());
}

//IE，火狐都支持
document.onkeydown = function(e)
{
	//console.log("回车");
    var e = window.event   ?   window.event   :   e; 
    if(e.keyCode == 13){    	
    	loginAction();
     }
}

function loginAction(){	
	document.getElementById('span').innerHTML="";
        var login_url = "index";
        var data ={
            	loginname: $('#count').val(),
                password: $('#password').val()
                /*code: $('#code').val()*/
            };            
        $.ajax({
    		url:login_url,
    		data:data,
    		type:'post',
    		dataType:'json',
    		success: function(result){
    			console.log(result);
    			if(result.resultCode=="1001"){
    				//alert('账号或者密码有误,请检查!');
    				document.getElementById('span').innerHTML=result.resultMessage;
    				$('#password').val("");
    				/*$('#code').val("");*/
    				RefreshCode();
    			}else if(result.resultCode=="SUCCESS"){
    				window.clearTimeout(timer2); 
    				location.href="management";  				   				
    			}
    		},
    		error: function(e){
    			alert("通信失败!");
    			
    		}
    	});
    

	
}

