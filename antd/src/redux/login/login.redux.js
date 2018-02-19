import axios from 'axios';
import { setCookie } from '../../utils/utils'

const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
const ERROR_MSG = "ERROR_MSG";

const initState = {
	redirectTo:'',
	token: '',
	username: '',
	password: '',
	msg:''
}

// reducer
export function auth(state=initState, action) {
	switch(action.type) {
		case LOGIN_SUCCESS:
			return {...state, msg:'', password:'', redirectTo:'/', ...action.payload};
		case LOGOUT_SUCCESS:
			return {...state, token:'', redirectTo:'/login'};
		case ERROR_MSG:
			return {...state, token:''}
		default:
			return state;
	}
}

// action creator
function login(data) {
	return {type:LOGIN_SUCCESS, payload:data};
}

// function logout() {
// 	return {type:LOGOUT_SUCCESS};
// }

// function errorMsg(msg) {
// 	return {msg, type:ERROR_MSG};
// }

// 登录操作
export function actionLogin({username,password}) {
	return dispatch => {
		axios.post('/user/login', {
            username,
            password
        }).then(res => {
			if (res && res.data && res.data.token) {
                // 把token存入cookie
                setCookie('token', res.data.token)
				dispatch(login({username,token:res.data.token}));
			}
		});
	}
}