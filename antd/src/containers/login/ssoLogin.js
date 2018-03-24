import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import qs from 'qs';
import { ssoLogin } from '../../redux/login/login.redux.js';
import { SSO_URL } from '../../utils/constants';

@withRouter
@connect(state => state.auth, {ssoLogin})
class SsoLogin extends React.Component {
    componentDidMount() {
        if (this.props.location.search) {
            const token = qs.parse(this.props.location.search.substring(1)).token;
            if (token) {
                return this.props.ssoLogin({token});
            }
        }
        window.location.href = SSO_URL + 'removeAuthToken.do?type=sale&callback=' + window.location.href;
    }

    render() {
        return (
            <div>
            {this.props.redirectTo && this.props.location.pathname !== this.props.redirectTo 
                ? <Redirect to={this.props.redirectTo} /> : null}
            </div>
        );
    }
}

export default SsoLogin;