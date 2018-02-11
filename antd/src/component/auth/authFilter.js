import React from 'react'
import axios from 'axios'
import { withRouter } from 'react-router-dom'
import { LOGIN_UNCHECK_URLS } from '../../app.props'

// 登录认证过滤器
@withRouter
class AuthFilter extends React.Component {
    componentDidMount() {
        // 判断url是否需要校验
        let pathname = this.props.location.pathname;
        if (LOGIN_UNCHECK_URLS.indexOf(pathname) > -1) {
            return;
        }
        // 判断是否有用户信息
        this.props.history.push('/login');
    }

    render() {
        return null
    }
}

export default AuthFilter;