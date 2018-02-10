import React from 'react'
import ReactDom from 'react-dom'
import { createStore, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'
import { Provider } from 'react-redux'
import { BrowserRouter, Route, Switch } from 'react-router-dom'

import reducers from './redux/reducer'
import './config'
import Login from './container/login.js'
import Main from './container/main.js'

const store = createStore(reducers, compose(
    applyMiddleware(thunk),
    window.devToolsExtension ? window.devToolsExtension : f => f
))

ReactDom.render((
    <Provider store={store}>
        <BrowserRouter>
            <Switch>
                <Route path="/login" component={Login} />
                <Route path="/main" component={Main} />
                <Route path="/" render={props => (
                        <h1>你好</h1>
                    )} />
            </Switch>
        </BrowserRouter>
    </Provider>
    ), document.getElementById('root')
)

