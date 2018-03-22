import React from 'react';
import {Table} from 'antd';
import axios from 'axios';
import { setItem, getItem } from '../../utils/store';

class Grid extends React.Component {
    constructor(props) {
        super(props);        
        this.state = {
            data: [],
            pagination: {
                showQuickJumper: true,
                showSizeChanger: true,
                pageSize: getItem("pageSize") ? getItem("pageSize") : 10
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
        // 用户改变每页条数时，记录到本地变量中
        if (this.state.pagination.pageSize !== pagination.pageSize) {
            setItem("pageSize", pagination.pageSize);
        }
        const pager = { ...this.state.pagination, ...pagination };
        this.setState({
            pagination: pager,
        });
        this.fetch({
            pagecount: pagination.pageSize,
            page: pagination.current,
            ...this.props.params
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
        // let {url, refresh, params, ...props} = this.props
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