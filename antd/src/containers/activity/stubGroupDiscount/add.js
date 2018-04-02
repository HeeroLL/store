import React from 'react';
import {Modal, Form, Input} from 'antd';
import axios from 'axios';
import {formItemLayout} from '../../utils/styles.js'

const FormItem = Form.Item;

@Form.create()
class AddUser extends React.Component {
	constructor(props) {		
        super(props);        
        this.ok = this.ok.bind(this);
        this.cancel = this.cancel.bind(this);
    }

    ok() {
    	this.props.form.validateFields((err, values) => {
			if (!err) {
				axios.post('/admin/addAdmin', {
		            ...values
		        }).then(res => {
		            this.props.form.resetFields();
					this.props.close(true);
		        });				
			}
			return null;
		})		
    }

    cancel() {
		this.props.close();
    }

    render() {
    	const { getFieldDecorator } = this.props.form;
    	return (
    		<Modal
	          	title="新增用户"
	          	visible={this.props.visible}
	          	onOk={this.ok}
	          	onCancel={this.cancel}
	          	okText="新增"
	          	cancelText="关闭"
	        >
		        <form>
		        	<FormItem {...formItemLayout} label="用户名">
		          		{getFieldDecorator('account', {
		            		rules: [
			              		{
			                		required: true,
			                		message: '请输入用户名'
			              		}
		            		]
			          	})(<Input onPressEnter={this.ok} placeholder="用户名" />)}
			        </FormItem>
			        <FormItem {...formItemLayout} label="手机号">
				        {getFieldDecorator('phone', {
				            rules: [
					            {
					                required: true,
					                message: '请输入手机号'
					            }
				            ]
				        })(<Input onPressEnter={this.ok} placeholder="手机号" />)}
			        </FormItem>
			        <FormItem {...formItemLayout} label="描述">
				        {getFieldDecorator('remark')(<Input onPressEnter={this.ok} placeholder="描述" />)}
			        </FormItem>
			    </form>
	        </Modal>
    	);
    }
}

export default AddUser;