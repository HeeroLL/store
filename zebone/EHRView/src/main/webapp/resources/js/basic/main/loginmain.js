/***
 * author: charmyin
 * datetime: 2013-2-22 21:00
 * title: config the login
 ***/

requirejs.config({
	waitSeconds: 0,
	baseUrl: "../../../../",//此处根目录为引用requirejs所对应的根目录，由于使用了base标签，所以根目录为zeboneui文件夹
	paths: {
		'cookiejs': 'vendor/cookiejs/cookie.min',
		'loginapp': 'app/js/apps/easyui/login/loginapp',
		'domReady': 'vendor/domReady/domReady',
		'validRules': 'vendor/utils/easyui/validRules'
	}
});

require(['domReady', 'loginapp', 'cookiejs', 'validRules'], function (domReady, loginapp, cookiejs, validRules) {
	
	
		//页面加载完成后，执行以下操作
		// domReady(function () {
		//获取cookie中的用户名和密码
		loginapp.getUserInfoFromCookie();
		//载入easyui,form框架
		//easyloader.load('plugins/jquery.form.js');
		//重置表单
		$('#aResetForm').click(function (event) {
			$('#authForm').form('reset');
			//设置authForm首个控件焦点
			document.authForm.username.focus();
		});
		//提交表单
		$('#aSubmitForm').click(function () {
			loginapp.submitLoginForm();
		});
		//设定enter按钮事件
		loginapp.setEnterPressEvent(loginapp);
		
	// });

	
});