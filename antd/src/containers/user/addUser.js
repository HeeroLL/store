import React from 'react';
import {Modal} from 'antd';

class AddUser extends React.Component {
	constructor(props) {		
        super(props);        
        this.ok = this.ok.bind(this);
        this.cancel = this.cancel.bind(this);
    }

    ok() {
		this.props.close();
    }

    cancel() {
		this.props.close();
    }

    render() {
    	return (
    		<Modal
	          	title="新增用户"
	          	visible={this.props.visible}
	          	onOk={this.ok}
	          	onCancel={this.cancel}
	          	okText="新增"
	          	cancelText="关闭"
	        >
		        <p>Bla bla ...</p>
		        <p>Bla bla ...</p>
		        <p>Bla bla ...</p>
	        </Modal>
    	);
    }
}

export default AddUser;