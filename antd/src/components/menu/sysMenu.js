import React from 'react';
import {Layout,Menu} from 'antd';
import { withRouter } from 'react-router-dom'
import {connect} from 'react-redux'

import MenuContent from './menuContent';
import { SYS_NAME, LOGO_URL } from '../../utils/constants.js';
import { loadMenus } from '../../redux/menu/menu.redux.js';

@withRouter
@connect(state => state.menu, {loadMenus})
class SysMenu extends React.Component {
	constructor(props) {
        super(props);
        this.state = {
            currentMenu: ''
        }
        this.appTitle = {
            height: '32px',
            lineHeight: '32px',
            float: 'left',
            color: '#1890ff',
            marginLeft: '10px'
        };
        this.siderLogo = {
            height: '32px',
            margin: '16px'
        };
        this.siderStyle = {
            // overflowY: 'auto', 
            // height: '100vh'
        };
        this.logoImg = {
            width:32,
            height:32,
            float:'left'
        };
        this.handleClick = this.handleClick.bind(this);
    }

    // 页面初始化完成后加载菜单
    componentDidMount() {
    	const userInfo = JSON.parse(sessionStorage.getItem("token"));
        if (userInfo) {
            this.props.loadMenus({userId:userInfo.id});
        }        
    }

    // 点击菜单刷新右侧页面
    handleClick(e) {
        if (this.state.currentMenu !== e.key) {
            this.setState({
                currentMenu: e.key
            });
            this.props.history.push(e.key);         
        }
    }

	render() {
		return (
			<Layout.Sider style={this.siderStyle}
                trigger={null}
                collapsedWidth="0"
                breakpoint="lg"
                collapsible="true"
                collapsed={this.props.collapsed}>
                <div style={this.siderLogo}>
                    <img alt="logo" style={this.logoImg} 
                        src={LOGO_URL} />
                    <h3 style={this.appTitle}>{SYS_NAME}</h3>
                </div>
                <Menu theme="dark" mode="inline" onClick={this.handleClick}>
                {/*这里只渲染三级及以下菜单，超过三级需重新定制*/
                    this.props.menus.map(v => (
                        v.children && v.children.length > 0 ? 
                            <Menu.SubMenu key={v.url || v.id} title={<MenuContent {...v} />} 
                                children={
                                    v.children.map(val => (
                                        val.children && val.children.length > 0 ?
                                            <Menu.SubMenu key={val.url || val.id} 
                                                title={<MenuContent {...val} />}
                                                children={
                                                    val.children.map(value => (
                                                        <Menu.Item key={value.url || value.id}>
                                                            <MenuContent {...value} />
                                                        </Menu.Item>
                                                    ))
                                                }
                                            />
                                            :
                                            <Menu.Item key={val.url || val.id}>
                                                <MenuContent {...val} />
                                            </Menu.Item>
                                    ))
                            } />
                            : 
                            <Menu.Item key={v.url || v.id}>
                                <MenuContent {...v} />
                            </Menu.Item>
                    ))
                }
                </Menu>
            </Layout.Sider>
		)
	}
}

export default SysMenu;