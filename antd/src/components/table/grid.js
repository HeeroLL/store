import React from 'react';
import {Table} from 'antd';
import axios from 'axios';

class Grid extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            pagination: {
                showQuickJumper: true
            },
            loading: false
        };
        this.handleTableChange = this.handleTableChange.bind(this);
    }

    // 通过父组件更新子组件props时触发
    componentWillReceiveProps(nextProps) { 
        if (nextProps.refresh !== this.props.refresh) {
            this.fetch({
                pagecount: 10,
                page: 1,
                ...nextProps.params
            });
        }        
    }

    componentDidMount() {
        this.fetch({
            pagecount: 10,
            page: 1,
            ...this.props.params
        });
    }

    // 表单change事件
    handleTableChange(pagination, filters, sorter) {
        const pager = { ...this.state.pagination, current: pagination.current };
        this.setState({
            pagination: pager,
        });
        this.fetch({
            pagecount: pagination.pageSize,
            page: pagination.current
        });
    }

    // 后台查询数据
    fetch(params) {
        this.setState({ 
            loading: true 
        });
        axios.post(this.props.url, {            
            ...params
        }).then(res => {
            const pagination = { ...this.state.pagination, total: res.pageLimit.totalCount };
            this.setState({
                loading: false,
                data: res.data,
                pagination,
            });
        });
    }

    render() {
        return (
            <Table 
                bordered
                rowKey={record => record.id}
                size="middle"
                dataSource={this.state.data}
                pagination={this.state.pagination}
                loading={this.state.loading}
                onChange={this.handleTableChange}
                {...this.props}
            />
        )
    }
}

export default Grid;