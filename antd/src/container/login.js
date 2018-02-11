import React from 'react';
import { connect } from 'react-redux';
import { Button } from 'antd';
import { login } from '../redux/login.redux.js';

@connect(state => state.auth, {login})
class Login extends React.Component {
	constructor(props) {
		super(props);
		this.handleLogin = this.handleLogin.bind(this);
	}

	handleLogin() {
		return this.props.login();
	}

	render() {
		return (
			<div>
				<h2>登录页</h2>
				<Button type="primary" onClick={this.handleLogin}>登录</Button>
			</div>
		)
	}
}

export default Login;