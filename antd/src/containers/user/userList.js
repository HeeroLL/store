import React from 'react';
import {Input, Button, Row, Col} from 'antd';
import Grid from '../../components/table/grid';
import AddUser from './addUser';

class UserList extends React.Component {
    constructor(props) {
        super(props);
        this.columns = [{
                title: '用户名',
                dataIndex: 'account',
                width:'20%'
            }, {
                title: '手机号',
                dataIndex: 'phone',
                width:'20%'
            }, {
                title: '创建时间',
                dataIndex: 'createTime',
                width:'30%'
            }, {
                title: '操作',
                width:'30%'
            }
        ];
        this.state = {
            params: {
                account: ''    
            },
            addUserModel: false,
            updateUserModer: false
        };
        this.search = this.search.bind(this);
        this.showAddUserModal = this.showAddUserModal.bind(this);
        this.hideAddUserModal = this.hideAddUserModal.bind(this);
    }

    showAddUserModal() {
        this.setState({
            addUserModel: true
        });
    }

    hideAddUserModal() {
        this.setState({
            addUserModel: false
        });
    }

    search(value) {
        this.setState({
            params: {
                account: value 
            }
        });
    }

    render() {
        return (
            <div>
                <Row gutter={16}>
                    <Col span={5}>
                        <Input.Search
                            placeholder="输入用户名"
                            onSearch={this.search}
                            enterButton
                        />
                    </Col>
                    <Col span={5}>
                        <Input placeholder="输入用户名" />
                    </Col>
                    <Col span={5}>
                        <Input placeholder="输入电话" />
                    </Col>
                    <Col span={2}>
                        <Button type="primary" onClick={this.showAddUserModal}>查询</Button>
                    </Col>
                    <Col span={2}>
                        <Button onClick={this.showAddUserModal}>新增</Button>
                    </Col>
                </Row>
                <p />
                <Grid params={this.state.params} columns={this.columns} url="/user/findAdminList" />
                <AddUser visible={this.state.addUserModel} close={this.hideAddUserModal} />
            </div>
        )
    }
}

export default UserList;