import React from 'react'
import ReactDom from 'react-dom'
import { createStore, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'
import { Provider } from 'react-redux'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import { LocaleProvider } from 'antd'
import zhCN from 'antd/lib/locale-provider/zh_CN';

import reducers from './redux/reducer'
import './config'
import AuthFilter from './components/auth/authFilter'
import LoginAdapter from './containers/login/loginAdapter'
import Main from './containers/main/main'

const store = createStore(reducers, compose(
    applyMiddleware(thunk),
    window.devToolsExtension ? window.devToolsExtension() : f => f
))

ReactDom.render((
    <LocaleProvider locale={zhCN}>
        <Provider store={store}>
            <BrowserRouter>        
                <div>
                    <AuthFilter />
                    <Switch>                
                        <Route path="/login" component={LoginAdapter} />                        
                        <Route path="/" component={Main} />
                    </Switch>
                </div>            
            </BrowserRouter>
        </Provider>
    </LocaleProvider>
    ), document.getElementById('root')
)