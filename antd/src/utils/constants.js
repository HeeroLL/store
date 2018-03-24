// 无需登录校验的URL
export const LOGIN_UNCHECK_URLS = ['/login'];
// logo图片cdn路径
export const LOGO_URL = 'https://app-cdn.starcharge.com/starcharge-logo.png';
// 系统名称
export const SYS_NAME = '运营管理系统';
// 登录模式（0：系统自有登录，1：SSO单点登录，2：钉钉扫码登录）
export const LOGIN_MODE = 1;
// 后端url
export const BACK_BASE_URL = process.env.NODE_ENV === "production" ? "http://10.9.35.52/api" : "http://localhost:8006/api";
// ssourl
export const SSO_URL = process.env.NODE_ENV === "production" ? "https://usercenter.starcharge.com/" : "http://10.9.35.53:8004/";
