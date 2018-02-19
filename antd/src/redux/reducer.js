// 合并所有的reducer并返回
import { combineReducers } from 'redux'
import {auth} from './login/login.redux.js'

export default combineReducers({auth})