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

    componentDidMount() {
        this.fetch({
            pagecount: 10,
            page: 1
        });
    }

    componentDidUpdate(prevProps, prevState) {
        console.log(prevProps)
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

    fetch(pagination) {
        this.setState({ 
            loading: true 
        });
        axios.post(this.props.url, {            
            ...pagination,
            ...this.props.params
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