/*---------------------------------------------------------------------------*\
|  Subject:       Mobile Core JS
|  Version:       3.5.0
|  Author: tyotann
|  Comment:       核心方法封装,此类暂时依赖于jquery
\*---------------------------------------------------------------------------*/
import {v_contextPath,v_protocol,v_serverIp,v_serverPort} from '../config';
import FastClick from 'fastclick';

if (typeof AppEngine == "undefined") {
	window.AppEngine = {
		version : '1.0',
		UI : {}
	};

	// 框架变量
	document["frame"] = {
		"btnDisabled" : {}
	};

	window.AE = window.AppEngine;
}

Object.toJSON = function(v_data) {
	return JSON.stringify(v_data);
};

//document.writeln("<scri" + "pt src='" + v_contextPath + "resources/js/mobile/js/fastclick.min.js' type='text/javascript'></sc" + "ript>");

window.onerror = function(m, u, l) {
	// alert("JavaScript Error:" + m + "\nline:" + l + "\nURL:" + u);
	return true;
};

if (!window.jQuery) {
	alert("页面没有加载成功,请查看网络是否存在延迟!");
}

// jQuery重定义,防止$与velocity冲突
window.jq = window.jQuery;

// 回调函数
var onloadcallbacks = [];

/**
 * 页面加载完成后执行的方法
 */
AE.ready = function(funcb) {
	if (funcb) {
		onloadcallbacks[onloadcallbacks.length] = funcb;
	}
};

/**
 * 页面ready后，初始化工作
 */
$(document).ready(function() {

			// 手机端相应加速
			if (typeof(FastClick) != "undefined") {
				FastClick.attach(document.body);
			}

			// 调用页面初始化AE.ready内的回调函数
			// 所有控件事件绑定都需要在此内完成,然后框架再绑定事件
			if (onloadcallbacks) {
				for (var i = 0; i < onloadcallbacks.length; i++) {
					try {
						onloadcallbacks[i].call();
					} catch (e) {
						alert(e.message);
					}
				}
			}
		});

AE.api = function(serviceName, params, v_callback, options) {

	try {

		// mobile加入1秒无法多次提交功能

		// 防止按钮多次点击:ajax前使按钮disable,后使enable
		var v_btnItem = (typeof(event) != "undefined" && event && event.srcElement && event.srcElement.type && (event.srcElement.type == "button" || event.srcElement.type == "submit"))
				? event.srcElement
				: null;

		var seqno = _createUUID;

		$.ajax({
					url : v_contextPath + serviceName,
					type : 'post',
					data : {
						"FRAMEparams" : JSON.stringify(params || {})
					},
					async : (options && typeof(options.async) != "undefined" && options.async != null) ? options.async : true,
					beforeSend : function(jqXHR, settings) {

						try {

							if ((!options || !options.unShowWait) && AE.UI && AE.UI.showwaitform) {
								AE.UI.showwaitform();
							}

							// 防止按钮多次提交设置
							if (v_btnItem != null) {
								if (!document["frame"]["btnDisabled"][v_btnItem]) {
									document["frame"]["btnDisabled"][v_btnItem] = 1;
									v_btnItem.disabled = true;
								} else {
									document["frame"]["btnDisabled"][v_btnItem] = document["frame"]["btnDisabled"][v_btnItem] + 1;
								}
							}
						} catch (e) {
							AE.UI.alert("ajax请求beforeSend时出现异常:" + e.message + ":" + e.description);
						}
					},
					success : function(responseText) {

						try {
							var data = null;

							if (responseText && responseText != "") {
								data = eval("(" + responseText + ")");
							}

							if (data && typeof data["code"] != "undefined") {
								var code = Number(data.code);

								// 0:正常 1:业务警告 -1:业务逻辑异常 -2:系统异常 -98:Session 超时
								if (code === 200 && v_callback) {
									v_callback.call(this, data.data, data.pageLimit);
								} else if (code === 300) {
									AE.UI.alert("调用服务" + serviceName + "警告，警告信息：" + data["text"], "error");
								} else if (code === 400 || code === 401 || code === 402) {

									// 如果用户有自定义异常,则处理
									if (options && options.ECallBack) {
										options.ECallBack.call(this, data);
									} else {
										if (code === 400) {
											AE.UI.alert(data["text"], "error");
										} else if (code === 401) {
											AE.UI.alert("系统异常，异常信息：" + data["text"], "error");
										} else if (code === 402) {
											AE.UI.alert("系统异常，异常代码：401", "error");
										} else {
											AE.UI.alert("调用服务" + serviceName + "异常，异常信息：" + data["text"], "error");
										}
									}
								} else {
									AE.UI.alert(data["text"], "error");
								}
							}
						} catch (e) {
							AE.UI.alert("ajax请求success时出现异常:" + e.message + ":" + e.description, "error");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						try {
							if (jqXHR.status == 12029) {

								if (confirm("服务器连接中断，是否关闭系统？")) {
									var win = window.parent;
									while (win.name != 'main') {
										win = win.parent;
									}

									// 清除首页定时器
									if (win.update_news) {
										win.clearInterval(win.update_news);
										win.update_news = undefined;
									}
									win.close();
								}
							} else if (jqXHR.status == 404) {
								AE.UI.alert("【" + jqXHR.status + "】- 请查询请求的资源是否存活或已被锁定!", "error");
							} else if (jqXHR.status == 0) {
								// status=0的情况不报异常
							} else {
								AE.UI.alert("【" + jqXHR.status + "】- 请求出错!", "error");
							}
						} catch (e) {
							AE.UI.alert("ajax请求error时出现异常:" + e.message + ":" + e.description);
						}
					},
					complete : function() {

						try {

							// sucess回调函数中关闭页面，会导致此处AE为undefine
							if (document.body) {

								if ((!options || !options.unShowWait) && AE.UI && AE.UI.hidewaitform) {
									AE.UI.hidewaitform();
								}

								if (v_btnItem != null) {
									try {
										document["frame"]["btnDisabled"][v_btnItem] = document["frame"]["btnDisabled"][v_btnItem] - 1;
										if (document["frame"]["btnDisabled"][v_btnItem] == 0) {
											v_btnItem.disabled = false;
											if (!$(v_btnItem).is(":hidden")) {
												v_btnItem.focus();
											}
										}
									} catch (se) {
										v_btnItem.disabled = false;
										if (!$(v_btnItem).is(":hidden")) {
											v_btnItem.focus();
										}
									}
								}
							}
						} catch (e) {
							AE.UI.alert("ajax请求complete时出现异常:" + e.message + ":" + e.description);
						}
					}
				});
	} catch (e) {
		AE.UI.alert("请求服务过程,程序出现异常:" + e.message + ":" + e.description);
	}

	function _createUUID() {
		var s = [];
		var hexDigits = "0123456789ABCDEF";
		for (var i = 0; i < 32; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[12] = "4";
		s[16] = hexDigits.substr((s[16] & 0x3) | 0x8, 1);
		var uuid = s.join("");
		return uuid;
	}
};

/**
 * 页面域对象序列化
 */
AppEngine.serialize = function(v_formId) {

	var v_serializedata = {};

	// 重复name的设置为数组
	var f_setData = function(v_data, v_name, v_value) {
		if (v_data[v_name]) {

			if (!Utils.isArray(v_data[v_name])) {
				var v_tmp = v_data[v_name];
				v_data[v_name] = [];
				v_data[v_name].push(v_tmp);
			}
			v_data[v_name].push(v_value);
		} else {
			v_data[v_name] = v_value;
		}
	};

	// 遍历所有非隐藏，非按钮，非checkbox的值
	$.each($("#" + v_formId + " input[type!='hidden'][type!='button'][type!='checkbox']").serializeArray(), function(i, item) {
				if (item.name || item.id) {
					f_setData(v_serializedata, (item.name ? item.name : item.id), item.value);
				}
			});

	// 模糊查询设置
	var allEmts = $("#" + v_formId + " input[type!='hidden'][type!='button'][likeQuery='true']");
	$.each(allEmts, function(i, item) {
				if (v_serializedata[item.name] != "") {
					v_id = item.name == "" ? item.id : item.name;
					v_serializedata[item.name] = "%" + v_serializedata[item.name] + "%";
				}
			});

	// checkbox值序列化问题
	var v_checkbox = $("#" + v_formId + " input[type!='hidden'][type=checkbox]");
	$.each(v_checkbox, function(i, item) {
				if (item.name || item.id) {
					f_setData(v_serializedata, (item.name ? item.name : item.id), (item.checked ? "1" : "0"));
				}
			});

	return v_serializedata;
};

AppEngine.deserialize = function(v_formId, v_data) {

	if (!v_data) {
		return;
	}

	// 遍历所有input
	$.each($("#" + v_formId + " input[type!='button']"), function(i, v_item) {

				var value = v_data[(v_item.name || v_item.id)];

				if (value == null) {
					return;
				}

				if (v_item.type == 'text' || v_item.type == 'password' || v_item.type == 'hidden' || v_item.type == 'textarea') {
					v_item.value = value;
				} else if (v_item.type == "checkbox") {
					v_item.checked = (v_item.value == value || value == "1");
				} else if (v_item.type == "radio") {
					if (v_item.value == value) {
						v_item.checked = true;
						return;
					}
				} else if (v_item.type == "select-one") {
					$A(v_item.options).each(function(e) {
								if (e.value == value) {
									e.selected = true;
									return;
								}
							});
				}
			});
};

// APP JS Beidge
if (typeof MobileJSBridge == "undefined") {
	window.MobileJSBridge = {
		version : '1.0'
	};

	window.MB = window.MobileJSBridge;
}

window["frame_android_uniqueId"] = 1;
MobileJSBridge.call = function(v_methodName, v_param, v_callback, v_options) {

	if (window.JSBridge || window.WebViewIOSJavascriptBridge) {

		var v_callbackName = '';
		if (v_callback) {
			v_callbackName = 'cb_' + (window["frame_android_uniqueId"]++) + '_' + new Date().getTime();

			// 如果不存在回掉函数,默认塞一个
			window[v_callbackName] = v_callback;
		}

		try {
			if (window.JSBridge && window.JSBridge.AndroidMobileJSBridge) {
				window.JSBridge.AndroidMobileJSBridge(v_methodName, v_param ? JSON.stringify(v_param) : "", v_callbackName);
			} else if (window.WebViewIOSJavascriptBridge) {
				window.WebViewIOSJavascriptBridge(v_methodName, v_param ? JSON.stringify(v_param) : "", v_callbackName);
			}
		} catch (e) {
		}
	}

};

AE.UI = {

	alert : function(v_msg, v_type, v_function) {

		if (window.JSBridge) {
			MobileJSBridge.call('frameShowAlert', {
						type : (v_type ? v_type : 'success'),
						message : v_msg
					}, function(v_data) {
					});
		} else {
			alert(v_msg);
		}
	},

/*	showwaitform : function() {

		if (typeof(window.top["frame_req_counter"]) == "undefined" && !window.JSBridge) {
			window.top.$(document.body).append("<div id=\"ajax-loading\" style=\"display: none;\"><div></div></div>");
			window.top["frame_req_counter"] = 0;
		}

		try {
			if (window.top["frame_req_counter"] == 0) {

				if (window.JSBridge) {
					MobileJSBridge.call('frameShowLoading');
				} else {
					window.top.$("#ajax-loading").show();
				}

				// 5秒后关闭
				setTimeout(function() {
							window.top["frame_req_counter"] = 0;
							AE.UI.hidewaitform();
						}, 5000);
			}
		} catch (e) {
		} finally {
			window.top["frame_req_counter"] = window.top["frame_req_counter"] ? window.top["frame_req_counter"] + 1 : 1;
		}
	}, */

/* 	hidewaitform : function() {

		try {
			window.top["frame_req_counter"] = window.top["frame_req_counter"] <= 0 ? 0 : (window.top["frame_req_counter"] - 1);

			if (window.top["frame_req_counter"] == 0) {

				if (window.JSBridge) {
					MobileJSBridge.call('frameDismissLoading', {}, function(v_data) {
							});
				} else {
					window.top.$("#ajax-loading").hide();
				}
			}
		} catch (e) {
		}
	} */
};

export function f_showMsg(v_msg){
	//frameShowMsg--只是提醒用户，自动消失
	MB.call('frameShowMsg', {
		message : v_msg
	});
};

export function f_showAlert(v_msg){

	//frameShowAlert--提醒用户，手动消失
	MB.call('frameShowAlert', {
		message : v_msg
	});
};

/**
 * 显示页面
 * @param url
 * @param title
 * @returns
 */
export function f_showPage(url,title){
	var pageUrl = v_protocol + "//" + v_serverIp + ":" + v_serverPort + v_contextPath + url;
	if (window.JSBridge || window.WebViewIOSJavascriptBridge) {
		MB.call('frameShowPage', {
			url : pageUrl,
			title : title
		});
	}else{
		window.location = pageUrl;
	}
}

/**
 * 显示深层次的H5页面
 * @param url
 * @param title
 * @returns
 */
export function f_showDeepPage(url,title){
	var pageUrl = v_protocol + "//" + v_serverIp + ":" + v_serverPort + v_contextPath + url;
	if (window.JSBridge || window.WebViewIOSJavascriptBridge) {
		MB.call('frameShowDeepPage', {
			url : pageUrl,
			title : title
		});
	}else{
		window.location = pageUrl;
	}
}

/**
 * 返回两位有效数字
 * @param strNum
 * @returns
 */
export function f_getFloatWithTwoDigits(strNum){
	var num = parseFloat(strNum);
	if(!isNaN(num)){
		return Number((num).toFixed(2));
	}else{
		return 0.00;
	}
}

/**
 * 返回整数
 * @param strNum
 * @returns
 */
export function f_getIntNum(strNum){
	var num = parseInt(strNum);
	if(!isNaN(num)){
		return num;
	}else{
		return 0;
	}
}

export {MB};

export default AE;