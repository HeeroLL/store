const LOGIN = "LOGIN";
const LOGOUT = "LOGOUT";

// reducer
export function auth(state={isLogin:false, user:null}, action) {
	switch(action.type) {
		case LOGIN:
			return {...state, isLogin:true};
		case LOGOUT:
			return {...state, isLogin:false};
		default:
			return state;
	}
}

// action creator
export function login() {
	return {type:LOGIN};
}

export function logout() {
	return {type:LOGOUT};
}