import React from 'react';
import {Button, Row, Col, Divider, Popconfirm} from 'antd';
import Grid from '../table/grid';
import {activityMap,activityStatusMap} from '../../utils/activityConstants';

// 活动列表
class ActivityList extends React.Component {
    constructor(props) {
        super(props);
        this.columns = [{
                title: '活动名称',
                dataIndex: 'name',
                width:'20%'
            }, {
                title: '活动时间',
                dataIndex: 'beginTime',
                width:'25%',
                render: (text, record) => record.beginTime && record.endTime ? record.beginTime + ' ~ ' + record.endTime : '无'
            }, {
                title: '活动类型',
                dataIndex: 'activityType',
                width:'8%',
                render: (text, record) => activityMap.get(record.activityType) ? activityMap.get(record.activityType) : '未知'
            }, {
                title: '状态',
                dataIndex: 'status',
                width:'5%',
                render: (text, record) => activityStatusMap.get(record.status) ? activityStatusMap.get(record.status) : '未知'
            }, {
                title: '创建人',
                dataIndex: 'createUserName',
                width:'7%',                
                render: (text, record) => record.createUserName ? record.createUserName : '未知'
            }, {
                title: '创建时间',
                dataIndex: 'createTime',
                width:'15%'
            }, {
                title: '操作',
                width:'20%',
                key: 'action',
                render: (text, record) => (
                    <span>
                        {record.status === 1 ? <a onClick={() => this.startUsing(record.id, 2)}>暂停</a> : null}
                        {record.status === 2 ? <a onClick={() => this.startUsing(record.id, 1)}>启动</a> : null}
                        {record.status !== 3 ? 
                            <span>
                                <Divider type="vertical" />
                                <Popconfirm title="停止活动后您将无法在启动该活动，确认要停止该活动吗？" 
                                    okText="确认" onConfirm={() => this.startUsing(record.id, 1)} cancelText="取消">
                                    <a>停止</a>
                                </Popconfirm>
                                <Divider type="vertical" />
                                <a>修改</a>
                                <Divider type="vertical" />
                            </span> : null
                        }
                    </span>
                )
            }
        ];
        this.state = {
            params: {
                activityType: this.props.activityType
            },
            addModal: false,
            updateModal: false,
            userId: '',
            refresh: 0
        };
    }

    // 改状态
    startUsing(id, status) {

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
                    <Col span={24}>                        
                        <Button type="primary" onClick={() => null}>新增</Button>
                    </Col>
                </Row>
                <p />
                <Grid refresh={this.state.refresh} params={this.state.params} columns={this.columns} url="/activity/getActivityList" />
            </div>
        )
    }
}

export default ActivityList;