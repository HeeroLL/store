import React from 'react';
import {LOGIN_MODE} from '../../utils/constants';
import Login from './login'
import SsoLogin from './ssoLogin'
import DingdingLogin from './dingdingLogin'

class LoginAdapter extends React.Component {
    render() {
        let loginComponent;
        switch (LOGIN_MODE) {
            case 0: 
                loginComponent = <Login />;
                break;
            case 1: 
                loginComponent = <SsoLogin />;
                break;
            case 2:
                loginComponent = <DingdingLogin />;
                break;
            default:
                loginComponent = <Login />;
                break;
        }
        return loginComponent;
    }
}

export default LoginAdapter;