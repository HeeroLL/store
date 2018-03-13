import React from 'react';
import {Layout,Menu,Icon} from 'antd';
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {actionCollapsed} from '../../redux/menu/menu.redux.js';
import {actionLogout} from '../../redux/login/login.redux';
import { getItem } from '../../utils/store';

import './sysHeader.css';

@withRouter
@connect(state => state, {actionCollapsed, actionLogout})
class SysHeader extends React.Component {
    constructor(props) {
        super(props);
        this.userinfo = JSON.parse(getItem("token"));
        this.toggle = this.toggle.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
        this.menuClick = this.menuClick.bind(this);
    }

    toggle() {
        this.props.actionCollapsed(!this.props.menu.collapsed);
    }

    handleLogout() {        
        this.props.actionLogout();
        this.props.history.push('/login');
    }

    menuClick(e) {
        if (e.key === 'logout') {
            this.handleLogout();
        }
    }

    render() {
        return (
            <Layout.Header style={{ background: '#fff', padding: 0 }}>
                <Icon
                    className='trigger'
                    type={this.props.menu.collapsed ? 'menu-unfold' : 'menu-fold'}
                    onClick={this.toggle}
                />
                <Menu
                    mode="horizontal"
                    onClick={this.menuClick}
                    style={{ lineHeight: '64px', float: 'right' }}>
                    <Menu.SubMenu title={
                        <span>
                            <Icon type="user" />
                            {this.userinfo ? this.userinfo.username : null}
                        </span>
                    }>
                        <Menu.Item style={{textAlign: 'center', margin: '50'}} 
                            key="logout">
                            <span onClick={this.handleLogout}>退出</span>
                        </Menu.Item>
                    </Menu.SubMenu>
                </Menu>
            </Layout.Header>
        );
    }
}

export default SysHeader;