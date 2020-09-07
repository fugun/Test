// 截屏模板
var template = [];
document.getElementById("template").addEventListener('change', readFileaTask,
		false);
var showuis = document.getElementById("showuis");
function readFileaTask() {
	var fd = new FormData();
	var iLen = this.files.length;
	var index = 0;
	var currentReViewImgIndex = 0;
	for (var i = 0; i < iLen; i++) {
		if (!document.getElementById("template")['value']
				.match(/.jpg|.gif|.png|.jpeg|.bmp/i)) { //判断上传文件格式
			return alert("上传的图片格式不正确，请重新选择");
		}
		var reader = new FileReader();
		reader.index = i;
		fd.append(i, this.files[i]);
		reader.readAsDataURL(this.files[i]); //转成base64
		reader.fileName = this.files[i].name;
		reader.files = this.files[i];
		reader.onload = function(e) {
			var imgMsg = {
				name : randomStringTask(5), //获取文件名
				base64 : this.result, //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
			}
			template.push(imgMsg);
			console.log(template);
			for (var j = 0; j < template.length; j++) {
				currentReViewImgIndex = j
			}
			var result = '<img src="res/images/gg.png"/><img id="img" src="'
					+ this.result + '" width="100px"/>';
			var li = document.createElement('li');
			li.innerHTML = result;
			showuis.appendChild(li);
			index++;
		}
	}
}

function submitTemplate() {
	if (!template.length) {
		return alert('请先选择文件');
	}
	var sms_load = layer.load("上传中..");
	for (var j = 0; j < template.length; j++) {
		//console.log(dataArr[j].name);
		//console.log(dataArr[j].base64);
		var login_url = "steps/img";
		var data = {
			img : template[j].base64,
			name : template[j].name
		};
		$.ajax({
			url : login_url,
			data : data,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				//console.log(j);
				//console.log(dataArr.length);

				if (result.url != "1") {
					if (j == template.length) {
						layer.alert("成功!", 1, !1);
						template = [];
						layer.close(sms_load);
					}
					var urls = $("#template_img_url").val();
					//console.log(result.url);
					$("#template_img_url").val(result.url + ";" + urls);
					
					//console.log("j"+j--);
				} else {
					alert('上传失败!请 重新上传!');
					layer.close(sms_load);
				}
			},
			error : function(e) {
				//alert("通信失败!");
				layer.close(sms_load);
			}
		});

	}
}

function randomStringTask(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	/****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
//清除
function templateEnd() {
	template = [];
	$("ul#showuis").html("");
	$("#template_img_url").val("");
	$("#template").val("");
	$("#showuis").each(function() {//寻找#test下面所有的div
		/* var a=$(this).text(); *///获取#test下面每个div的文字
		$(this).empty();//清空#test下面所有的内容
		/*  $(this).text(a); *///把每个div的内容赋给清除内容以后的div */
	});
	$("#showTemplateImgs").each(function() {//寻找#test下面所有的div
		/* var a=$(this).text(); *///获取#test下面每个div的文字
		$(this).empty();//清空#test下面所有的内容
		/*  $(this).text(a); *///把每个div的内容赋给清除内容以后的div */
	});
}
