
// 获取cookie
export function getCookie(name) {
	const reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	let arr = document.cookie.match(reg);
	if(arr) {
		return unescape(arr[2]);
	}
	return null;
}

// 存储cookie
export function setCookie(name, value) {
	var exp = new Date();
	exp.setTime(exp.getTime() + 24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}