import React from 'react';
import { connect } from 'react-redux';
import { Button, Input } from 'antd';
import { actionLogin } from '../redux/login.redux.js';

@connect(state => state.auth, {actionLogin})
class Login extends React.Component {
	constructor(props) {
		super(props);
		this.handleLogin = this.handleLogin.bind(this);
	}

	handleLogin() {
		return this.props.actionLogin(this.state);
	}

	handleChange(key, event) {
		this.setState({
			[key]:event.target.value
		});
	}

	render() {
		return (
			<div>
				<h2>登录页</h2>
				<Input size="large" placeholder="用户名" onChange={e=>this.handleChange('username', e)} />
				<Input type="password" size="large" placeholder="密码" onChange={e=>this.handleChange('password', e)} />
				<Button type="primary" onClick={this.handleLogin}>登录</Button>
			</div>
		)
	}
}

export default Login;