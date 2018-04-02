import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { getItem } from '../../utils/store'
import NotFound from '../error/notFound';
import UserList from '../user/userList';

import StubGroupDiscountList from '../activity/stubGroupDiscount/list';

class Routers extends React.Component {
	render() {
		const userinfo = JSON.parse(getItem("token"));
		return (
			<Switch>                
                <Route path="/user/list" component={UserList} />
                <Route path="/action.do" component={UserList} />
                <Route path="/activity/stubGroupDiscount/list" component={StubGroupDiscountList} />
                <Route path="/" exact render={() => (
                		<h2>欢迎你，{userinfo ? userinfo.account : null}</h2>
                	)} />
                <Route component={NotFound} />
            </Switch>
		)
	}
}

export default Routers;