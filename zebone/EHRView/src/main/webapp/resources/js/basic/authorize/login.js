//从cookie获取用户信息
function getUserInfoFromCookie() {
	var userName = cookie.get('loginUserName');
	var password = cookie.get('loginPassword');
	$('#inputUsername').val(userName);
	$('#inputPassword').val(password);
}
//将光标移动到焦点上
//参数为元素的id
function setEnterPressEvent() {
	//监听enter按键事件
	//username文本框，enter后跳转下个文本框
	$('#inputUsername').bind('keypress', function (e) {
		var code = e.keyCode ? e.keyCode : e.which;
		if(code == 13){
			$('#inputPassword').focus();
		}
	});
	//密码文本框，enter后跳转下个文本框
	$('#inputPassword').bind('keypress', function (e) {
		var code = e.keyCode ? e.keyCode : e.which;
		if(code == 13){
			document.authForm.valiCode.focus();
		}
	});
	//验证码文本框，enter后提交表单
	$('#inputValiCode').bind('keypress', function (e) {
		var code = e.keyCode ? e.keyCode : e.which;
		if(code == 13){
			submitLoginForm();
		}
	});
}
//提交表单
 function submitLoginForm() {
	//验证所填入信息
	if(!$('#authForm').form('validate')){
		return;
	}			
	
	var userName = $('#inputUsername').val();
	var password = $('#inputPassword').val();
	
	//保存cookie
	if($('#inputRememberMe').is(':checked')){
		if(userName){
			cookie.set('loginUserName', userName);
		}
		if(password){
			//TODO 需要加密
			cookie.set('loginPassword', password);
		}
		
		//alert("表单提交中，cookie中存入用户名密码，刷新后显示~");
	}
	
	if($('#inputDesktop_login').is(':checked')){
		window.location.href="http://localhost:8080/bbrj/jsframes/jQueryDesktop/index.html";
	}else{
		validUserByAjax(userName, password);
	}
}

//Ajax形式验证用户凭据
function validUserByAjax(userName, password){
	//alert(userName+password);
	$.ajax({
		  url: "identity/authenticate",
		  type:"POST",
		  data: {
		    passphrase : password,
		    username : userName
		  },
		  dataType: "json",
		  success: function( data ) {
		    if(data.status == 'ok'){
		    	window.location.href=$("base").attr("href");
		    }else{
		    	$.messager.show({
					title:'登录提示',
					msg:"<p style='color:red; font-weight:bold; text-align:center;'>"+data.msg+"</p>",
					timeout : 2000,
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop+150,
						bottom:''
					}
				});
		    }
		  },
		  error: function(data){
			// show message window on top center
			$.messager.show({
				title:'登录提示',
				msg:"<p style='color:red; font-weight:bold; text-align:center;'>"+data.msg+"</p>",
				timeout : 2000,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop+150,
					bottom:''
				}
			});
			  
		  }
		});
	
}

//监视easyloader加载组件完成的事件
$(function(){
	//去除加载mask效果
	if($("#divLoading").length > 0){
		$("#loginwindow").window('close');
		$('#divLoading').fadeOut("slow", function () {
			$(this).remove();
			$("#loginwindow").window('open');
			document.authForm.username.focus();
		});
	}
	
	if($.inArray("validatebox", event) !== -1){
		//添加验证规则 
		//固定长度
		$.extend($.fn.validatebox.defaults.rules, {
			fixedLength: {
				validator: function(value, param){
					return value.length === param[0];
				},
				message: '字符串长度应为{0}'
			}
		});
	}
	
	//页面加载完成后，执行以下操作
	// domReady(function () {
	//获取cookie中的用户名和密码
	getUserInfoFromCookie();
	//载入easyui,form框架
	//easyloader.load('plugins/jquery.form.js');
	//重置表单
	$('#aResetForm').click(function (event) {
		$('#authForm').form('reset');
		//设置authForm首个控件焦点
		document.authForm.username.focus();
	});
	//提交表单
	$('#aSubmitForm').click(function (event) {
		event.preventDefault();
		submitLoginForm();
	});
	//设定enter按钮事件
	setEnterPressEvent();
	
	$("#aForm").hover(function(){
		$(this),addClass("toggle");
	},function(){
		$(this),removeClass("notoggle");
	});
	
});