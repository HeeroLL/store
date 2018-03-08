import axios from 'axios';

const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGOUT_SUCCESS = "LOGOUT_SUCCESS";

const initState = {
	redirectTo:'',
	token: '',
	username: '',
	password: ''
}

// reducer
export function auth(state=initState, action) {
	switch(action.type) {
		case LOGIN_SUCCESS:
			return {...state, password:'', redirectTo:'/', ...action.payload};
		case LOGOUT_SUCCESS:
			return {...state, token:'', redirectTo:'/login'};
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

// 登录操作
export function actionLogin({username,password}) {
	return dispatch => {
		axios.post('/user/login', {
            username,
            password
        }).then(res => {
			if (res && res.data) {
                // 把用户信息存入sessionStorage
                sessionStorage.setItem('token', JSON.stringify(res.data));               
				dispatch(login({username,...res.data}));
			}
		});
	}
}

// 登出操作
export function actionLogout() {
    return dispatch => {
        // 判断是否存在token，如果存在的话，则每个http header都加上token
        const userInfo = JSON.parse(sessionStorage.getItem("token"));
        if (userInfo) {
            axios.post('/user/logout', {
                token: userInfo.token
            }).then(res => {
                // if (res && res.data) {
                // 把用户信息从sessionStorage移除
                sessionStorage.removeItem('token');                
                //}
            });
        }
        dispatch(logout());
    }
}