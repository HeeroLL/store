import React from 'react';
import {Layout} from 'antd';
import Routers from '../routers/routers';
import SysMenu from '../../components/menu/sysMenu';
import SysHeader from '../../components/header/sysHeader';

const { Content, Footer } = Layout;
class Main extends React.Component {
	render() {
		return (
            <Layout hasSider="true">                
                {/*左侧菜单栏*/}
                <SysMenu />
                <Layout style={{ marginLeft: 200 }}>
                    {/*头部*/}
                    <SysHeader />
                    {/*内容*/}
                    <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}>
                        <div style={
                            { padding: 24, background: '#fff', minHeight: 'calc(100vh - 158px)'}
                        }>
                            <Routers />
                        </div>
                    </Content>
                    {/*底部*/}
                    <Footer style={{ textAlign: 'center' }}>
                        CopyRight©2018 Created by Starcharge
                    </Footer>
                </Layout>
            </Layout>
		);
	}
}

export default Main;