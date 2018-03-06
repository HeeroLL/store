import React from 'react';
import {Input, Button, Row, Col, Divider, Popconfirm} from 'antd';
import axios from 'axios';
import Grid from '../../components/table/grid';
import AddUser from './addUser';
import UpdateUser from './updateUser';

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
                width:'20%'
            }, {
                title: '描述',
                dataIndex: 'remark',
                width:'20%'
            }, {
                title: '操作',
                width:'20%',
                key: 'action',
                render: (text, record) => (
                    <span>
                        <Button onClick={() => this.toUpdate(record.id)}>修改</Button>
                        <Divider type="vertical" />
                        <Popconfirm title="确认需要删除吗？" okText="确认" onConfirm={() => this.delete(record.id)} cancelText="取消">
                            <Button>删除</Button>
                        </Popconfirm>
                    </span>
                )
            }
        ];
        this.state = {
            params: {
                account: '',
                phone: ''
            },
            addUserModal: false,
            updateUserModal: false,
            userId: '',
            refresh: 0
        };
    }

    delete(id) {
        axios.post('/user/deleteAdmin', {
            id
        }).then(res => {
            this.setState({
                refresh: this.state.refresh + 1
            });
        });
    }

    toUpdate(userId) {
        this.showModal('updateUserModal', true);
        this.setState({userId});
    }

    showModal(key, value, isRefresh) {
        let count = 0;
        if (isRefresh) {
            count += 1;
        }
        this.setState({
            [key]: value,
            refresh: this.state.refresh + count
        })
    }

    search(value) {
        this.setState({
            params: {...value},
            refresh: this.state.refresh + 1
        });
    }

    handleChange(key, event) {
        this.setState({
            [key]:event.target.value
        });
    }

    render() {
        return (
            <div>
                <Row gutter={{xs: 8, sm: 16, md: 24}}>
                    <Col span={5}>
                        <Input.Search
                            placeholder="输入用户名"
                            onSearch={value => this.search({account: value})}
                            enterButton
                        />
                    </Col>
                    <Col span={5}>
                        <Input placeholder="输入用户名" onChange={event => this.handleChange('account', event)} />
                    </Col>
                    <Col span={5}>
                        <Input placeholder="输入电话" onChange={event => this.handleChange('phone', event)} />
                    </Col>
                    <Col span={9}>
                        <Button type="primary" onClick={() => this.search({
                            account:this.state.account,
                            phone:this.state.phone
                        })} style={{marginRight:10}}>查询</Button>
                        <Button onClick={() => this.showModal('addUserModal', true)}>新增</Button>
                    </Col>
                </Row>
                <p />
                <Grid refresh={this.state.refresh} params={this.state.params} columns={this.columns} url="/user/findAdminList" />
                <AddUser visible={this.state.addUserModal} close={isRefresh => this.showModal('addUserModal', false, isRefresh)} />
                <UpdateUser userId={this.state.userId} visible={this.state.updateUserModal} close={isRefresh => this.showModal('updateUserModal', false, isRefresh)} />
            </div>
        )
    }
}

export default UserList;