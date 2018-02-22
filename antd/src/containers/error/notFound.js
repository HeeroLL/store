import React from 'react';
import {Icon} from 'antd';
// 404页面
class NotFound extends React.Component {
	constructor(props) {
		super(props);
		this.style = {
			color: 'black',
			textAlign: 'center',
			position: 'absolute',
			top: '40%',
			marginTop: '-50px',
			left: '50%',
			marginLeft: '-100px',
			fontSize: '30px',
    		marginBottom: '16px'
		};
	}

	render() {
		return (
			<div style={this.style}>
			    <h1><Icon type="frown-o" /></h1>
			    <h1 style={{fontFamily: 'cursive'}}>404 Not Found</h1>
			</div>
		)
	}
}

export default NotFound;