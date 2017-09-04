let v_contextPath;
if (process.env.NODE_ENV === 'development') {
	v_contextPath = "http://10.9.35.50:8005/";
} else {
	v_contextPath = "http://app4.test.ccchong.com:8081/"
}
export {v_contextPath};
// export const v_contextPath = process.env.NODE_ENV === 'development' ? "http://10.9.35.53:8005/" : "http://app4.test.ccchong.com:8081/"
	
export const v_cdnUrl = "https://app-cdn.starcharge.com/"

let v_userId;
if (localStorage.userId) {
	v_userId = localStorage.userId;
} else {
	localStorage.userId = "283717f7-9346-4015-9f90-36b9fdb3f984";
	v_userId = localStorage.userId;
}
export {v_userId};
//export const v_userName = "$!{userName}";

//export const v_verify = "$!verify";

//export const v_appid = "$!{appid}" == "" ? window.top["frame_appid"] : "$!{appid}";

export const v_serverIp = document.location.hostname;
export const v_serverPort = document.location.port;
export const v_protocol = (document.location.protocol == "" ? "http:" : document.location.protocol);