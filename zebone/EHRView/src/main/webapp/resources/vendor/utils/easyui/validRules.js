/***
 * author: charmyin
 * datetime: 2013-2-24 21:08
 * title: Extend more validate rules ~
 * Info: This bases on AMD , and the validatebox.js must have been loaded 
 ***/

define(function () {
	return function () {
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
		
});