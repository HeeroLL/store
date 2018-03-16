import React from 'react';
import { withRouter } from 'react-router'

@withRouter
class SsoLogin extends React.Component {
    render() {
        console.log(this.props)
        return (
            <div>sso登录</div>
        );
    }
}

export default SsoLogin;