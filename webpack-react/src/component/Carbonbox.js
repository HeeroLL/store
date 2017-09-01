//import React from 'react';
import DownloadApp from './DownloadApp';
import AE from './core.js';
import './css/styles.css';
import {v_userId} from '../config'

var Carbonbox = React.createClass({
	getInitialState: function(){
		return {
			joinTime: "",
			chargeCount: 0,
			electric: 0,
			carbonEmissionReduction: 0,
			treeCount: 0
		}
	},
	render: function() {
		return (
			<div className="carbon_box">
				<div className="carbon">
					<div className="carbon_list">
						<p><span>{this.state.joinTime}</span>加入星星充电</p>
					</div>
					<div className="carbon_list">
						<p>共充电<span>{this.state.chargeCount}</span>次，充电<span>{this.state.electric}</span>度</p>
						<p>为全球减少碳排放<span>{this.state.carbonEmissionReduction}</span>g</p>
					</div>
					<div className="carbon_list">
						<p>相当于种植了<span>{this.state.treeCount}</span>颗树</p>
					</div>
					<div className="carbon_list2">
						<p>快来加入我们吧</p>
					</div>
				</div>
				<DownloadApp domain={this.props.domain} />
			</div>
		);
	},
	componentDidMount: function() {		
		AE.api("webApi/mobile/getUserBaseInfo", {
			userId : v_userId
		}, function(v_data) {
			if(v_data){
				this.setState({
					joinTime: v_data.createTime,
					chargeCount: v_data.chargeCount,
					electric: v_data.chargeElectric,
					carbonEmissionReduction: v_data.carbonEmissionReduction,
					treeCount: parseInt(v_data.carbonEmissionReduction/18000)
				});				
			}
		}.bind(this));// 不绑定外层的this会导致内部this(Jquery对象)获取不到setState方法
		
	}
});

export default Carbonbox;