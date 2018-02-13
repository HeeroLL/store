import axios from 'axios';

const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
const ERROR_MSG = "ERROR_MSG";

const initState = {
	isLogin: false,
	username: '',
	password: '',
	msg:''
}

// reducer
export function auth(state=initState, action) {
	switch(action.type) {
		case LOGIN_SUCCESS:
			return {...state, msg:'', isLogin:true, ...action.payload};
		case LOGOUT_SUCCESS:
			return {...state, isLogin:false};
		case ERROR_MSG:
			return {...state, isLogin:false}
		default:
			return state;
	}
}

// action creator
function login(data) {
	return {type:LOGIN_SUCCESS, payload:data};
}

function logout() {
	return {type:LOGOUT_SUCCESS};
}

function errorMsg(msg) {
	return {msg, type:ERROR_MSG};
}

// 登录操作
export function actionLogin({username,password}) {
	return dispatch => {
		dispatch(login({username,password}));
		// axios.post('/user/login',{username,password})
		// 	.then(res => {
		// 		if (res.status === 200 && res.data.code === 0) {
		// 			dispatch(login({username,password}));
		// 		} else {
		// 			dispatch(errorMsg(res.data.msg));
		// 		}
		// 	});
	}
}