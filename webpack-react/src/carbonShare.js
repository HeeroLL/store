//import React from 'react';
//import ReactDOM from 'react-dom';
import Carbonbox from './component/Carbonbox';
import {v_contextPath} from './config';

ReactDOM.render(<Carbonbox domain={v_contextPath} />, document.getElementsByTagName("body")[0]);
