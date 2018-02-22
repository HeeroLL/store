import axios from 'axios';

const MENU_COLLAPSED = "MENU_COLLAPSED";
const MENU_EXTENDED = "MENU_EXTENDED";
const MENU_LOAD_DATA = "MENU_LOAD_DATA";

const initState = {
	collapsed: false,
	menus: [],
	currentMenu: ''
}

// reducer
export function menu(state=initState, action) {
	switch(action.type) {
		case MENU_COLLAPSED:
			return {...state, collapsed:true};
		case MENU_EXTENDED:
			return {...state, collapsed:false};
		case MENU_LOAD_DATA:
			return {...state, menus:action.payload};
		default:
			return state;
	}
}

// action creator
function loadData(data) {
	return {type:MENU_LOAD_DATA, payload:data};
}

function collapsed() {
	return {type:MENU_COLLAPSED};
}

function extended() {
	return {type:MENU_EXTENDED};
}

// 折叠或展开菜单栏
export function actionCollapsed(flag) {
	return flag ? collapsed() : extended();
}

// 加载菜单操作
export function loadMenus({userId}) {
	return dispatch => {
		// 后端需按照id顺序排序
		axios.post('/menu/queryList', {
            userId
        }).then(res => {
        	if (res && res.data) {
        		let data = [];
	            res.data.map(function(v, index) {
	                v.children = [];
	                if (v.parentId === '00') {                 
	                    data.push(v);
	                } else if (index > 0) { // 从第二个菜单开始判断
	                    let last = index - 1;
	                    if (v.parentId === res.data[last].id) {
	                        res.data[last].children.push(v);
	                        v.parent = res.data[last];
	                    } else if (v.parentId === res.data[last].parentId 
	                        && res.data[last].parent) {                        
	                        v.parent = res.data[last].parent;
	                        v.parent.children.push(v);
	                    }
	                }
	                return v;
	            });
	            dispatch(loadData(data));
        	}			
		});
	}
}