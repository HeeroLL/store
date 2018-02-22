import React from 'react';
import {Icon} from 'antd';

class MenuContent extends React.Component {
	render() {
		return (
			<span>
                {this.props.icon ? <Icon type={this.props.icon} /> : null}
                {this.props.name}
            </span>
		)
	}
}

export default MenuContent;