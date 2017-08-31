export const v_contextPath = "http://app4.test.ccchong.com:8081/";//"$!{req.contextPath}/";
	
export const v_cdnUrl = "https://app-cdn.starcharge.com/"

//export const v_userId = "$!{userId}";
//export const v_userName = "$!{userName}";

//export const v_verify = "$!verify";

//export const v_appid = "$!{appid}" == "" ? window.top["frame_appid"] : "$!{appid}";

export const v_serverIp = document.location.hostname;
export const v_serverPort = document.location.port;
export const v_protocol = (document.location.protocol == "" ? "http:" : document.location.protocol);