import axios from 'axios';

const MENU_COLLAPSED = "MENU_COLLAPSED";
const MENU_EXTENDED = "MENU_EXTENDED";
const MENU_LOAD_DATA = "MENU_LOAD_DATA";

const initState = {
	collapsed: false,
	menus: [],
	defaultSelectedKeys: [],
    defaultOpenKeys: []
}

// reducer
export function menu(state=initState, action) {
    
	switch(action.type) {
		case MENU_COLLAPSED:
			return {...state, collapsed:true};
		case MENU_EXTENDED:
			return {...state, collapsed:false};
		case MENU_LOAD_DATA:
			return {...state, ...action.payload};
		default:
			return state;
	}
}

// action creator
function loadData(data, defaultSelectedKeys, defaultOpenKeys) {
	return {type:MENU_LOAD_DATA, payload:{menus:data, defaultSelectedKeys, defaultOpenKeys}};
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
export function loadMenus({userId,defaultUrl}) {
	return dispatch => {
		// 后端需按照id顺序排序
		axios.post('/menu/queryList', {
            userId
        }).then(res => {
        	if (res && res.data) {
        		let data = [];
                let defaultSelectedKeys = [];
                let defaultOpenKeys = [];
	            res.data.map(function(v, index) {
	                v.children = [];
	                if (v.parentId === '00') {                 
	                    data.push(v);
	                } else if (index > 0) { // 从第二个菜单开始判断
	                	if (defaultUrl === v.url) { // 设置默认选中的菜单
                            defaultSelectedKeys = [v.url];
                            defaultOpenKeys = [v.parentId];
	                	}
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
	            dispatch(loadData(data, defaultSelectedKeys, defaultOpenKeys));
        	}			
		});
	}
}