import React from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom'
import { Form, Button, Input, Row } from 'antd';
import { actionLogin } from '../../redux/login/login.redux.js';
import './login.css';

const FormItem = Form.Item;

@connect(state => state.auth, {actionLogin})
@Form.create()
class Login extends React.Component {
	constructor(props) {
		super(props);
		this.handleLogin = this.handleLogin.bind(this);
	}

	handleLogin() {
		this.props.form.validateFields((err, values) => {
			if (!err) {
				return this.props.actionLogin(values);
			}
			return null;
		})
	}

	// handleChange(key, event) {
	// 	this.setState({
	// 		[key]:event.target.value
	// 	});
	// }

	render() {
		const { getFieldDecorator } = this.props.form;
		return (			
			<div className="form">
				{this.props.redirectTo ? <Redirect to={this.props.redirectTo} /> : null}
		      	<div className="logo">
		        	<img alt="logo" src="https://app-cdn.starcharge.com/starcharge-logo.png" />
			        <span>SaaS后台登录系统</span>
			    </div>
		      	<form>
		        	<FormItem>
		          		{getFieldDecorator('username', {
		            		rules: [
			              		{
			                		required: true,
			                		message: '请输入用户名'
			              		}
		            		]
			          	})(<Input onPressEnter={this.handleLogin} placeholder="用户名" />)}
			        </FormItem>
			        <FormItem>
				        {getFieldDecorator('password', {
				            rules: [
					            {
					                required: true,
					                message: '请输入密码'
					            }
				            ]
				        })(<Input type="password" onPressEnter={this.handleLogin} placeholder="密码" />)}
			        </FormItem>
			        <Row>
			          <Button type="primary" onClick={this.handleLogin}>
			            登 录
			          </Button>
			        </Row>
			    </form>
		    </div>
		)
	}
}

export default Login;