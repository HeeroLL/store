import AE from './component/core';
import {v_userId} from './config';
import './component/css/styles.css';
import head from './component/images/headImg.jpg';
import rank1 from './component/images/rank_01.png';
import rank2 from './component/images/rank_02.png';
import rank3 from './component/images/rank_03.png'

class CarbonUserInfo extends React.Component {
	constructor() {
		super();
		this.state = {
			myHeadImg: head,
			nickName: "无名氏",
			myCarbon: 0,
			myRanking: 0,
			unit: "g"
		}
	}
	
	handleImageErrored() {
		this.setState({
            myHeadImg: head
        });
	}
	
	render() {
		return (
			<div className="ranking_top">
				<img src={this.state.myHeadImg}
					onError={this.handleImageErrored.bind(this)} className="adm_img f_l"/>
				<div className="f_l">
					<p className="ranking_p1">{this.state.nickName}</p>
					<div className="ranking_span">
						<p className="ranking_p2 f_l">碳账户：</p>
						<p className="ranking_p3 f_l"><span>{this.state.myCarbon}</span>{this.state.unit}</p>
					</div>
				</div>
				<p className="ranking_num f_r">第 <span>{this.state.myRanking}</span> 名</p>
			</div>
		)
	}
	
	componentDidMount() {
		// 加载用户信息
		AE.api("webApi/mobile/getUserBaseInfo", {
			userId : v_userId
		}, function(v_data) {
			if(v_data){
				//$(".ranking_p1").html(v_data.nickName);
				var returnArr = formatCarbonEmissionReduction(v_data.carbonEmissionReduction);
				
				//$("#myCarbon").html(returnArr[0]);
				//$(".ranking_p3").append(returnArr[1]);
				this.setState({
					nickName: v_data.nickName,
					myCarbon: returnArr[0],
					unit: returnArr[1],
				});
				if (v_data.headImg) {
					this.setState({
						myHeadImg: v_data.headImg
					});
					//$("#myHeadImg").attr("src", v_data.headImg);
				}
				
				// 计算用户在所在城市的排名
				AE.api("webApi/carbon/getUserRanking", {
					userId : v_userId,
					city : v_city
				}, function(v_data) {
					if(v_data){
						this.setState({
							myRanking: v_data
						});
						// $("#myRanking").html(v_data);
					}
				}.bind(this));// 不绑定外层的this会导致内部this(Jquery对象)获取不到setState方法
			}
		}.bind(this));// 不绑定外层的this会导致内部this(Jquery对象)获取不到setState方法
	}
}

class RankingDiv extends React.Component {
	constructor() {
		super();
		this.state = {
			myHeadImg: head
		}
	}
	
	static defaultProps = {
		nickName: '无名氏',
		carbonEmissionReduction:0,
		ranking: '-'
	}
	
	handleImageErrored() {
		this.setState({
            myHeadImg: head
        });
	}
	
	componentDidMount() {
		this.setState({
			myHeadImg: this.props.headImg
		});
	}
	
	render() {
		let returnArr = formatCarbonEmissionReduction(this.props.carbonEmissionReduction);
		let rank;
		if (this.props.ranking === 1) {
			rank = rank1;
		} else if (this.props.ranking === 2) {
			rank = rank2;
		} else if (this.props.ranking === 3) {
			rank = rank3;
		} 
		return (
			<div className="ranking_list bb">				
				{this.props.ranking < 4 ? <img src={rank} className="rank_img f_l" /> : <p className="rank_img3 f_l">{this.props.ranking}</p>}
				<img src={this.state.myHeadImg}	onError={this.handleImageErrored.bind(this)} className="rank_img2 f_l"/>
				<p className="rank_name f_l">{this.props.nickName}</p>
				<p className="rank_number f_r"><span>{returnArr[0]}</span>{returnArr[1]}</p>
			</div>
		);
	}
}

let v_city = "";// 城市
let v_page = 1;// 当前页码
let v_isMax = false;// 是否全部加载
let v_currentRanking = 1; // 当前加载到的排名

class RankingBox extends React.Component {

	constructor() {
		super();
		this.state = {
			rankingList: []
		}
	}
	
	componentDidMount() {
		loadRankingList(function(data) {
			this.setState({
				rankingList: data
			});
		}.bind(this));// 不绑定外层的this会导致内部this(Jquery对象)获取不到setState方法		
		window.addEventListener('scroll', this.handleScroll.bind(this));
	}
	
	componentWillUnmount() {
		window.removeEventListener('scroll', this.handleScroll.bind(this));
	}
	
	handleScroll(e) {
		var scrollTop = $(window).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(window).height();
		if(scrollTop + windowHeight >= scrollHeight){
			let list = this.state.rankingList;
			loadRankingList(function(data) {
				this.setState({
					rankingList: this.state.rankingList.concat(data)
				});
			}.bind(this));// 不绑定外层的this会导致内部this(Jquery对象)获取不到setState方法		
		}
	}
	
	render() {
		return (
			<div className="ranking_box">
				<CarbonUserInfo />
				<div className="ranking">			
				{
					this.state.rankingList.map(function(item, i) {
						item.ranking = i + 1;
						return <RankingDiv {...item} key={item.id} />
					})
				}
				</div>
			</div>
		);
	}
}

ReactDOM.render(<RankingBox />,	document.getElementsByTagName('body')[0]);

// 格式化碳减排数据
function formatCarbonEmissionReduction(val) {
	var unit = ["g","kg","t"];
	var returnVal = val;
	var i = 0;
	for (; i < unit.length; i++) {
		if (returnVal < 1000) {
			break;
		}
		returnVal = (returnVal / 1000).toFixed(2);
	}
	if (i == 3) {
		i = i - 1;
	}
	var returnArray = [returnVal,unit[i]];
	return returnArray;
}

//加载排行榜
function loadRankingList(callback) {
	if (v_isMax) {
        return;
    }
	AE.api("webApi/carbon/getRankingList", {
        city : v_city,
        page : v_page, // 加载页数
        pagecount : 15 // 每页15条
    }, function(v_data) {
        if (v_data) {
        	if (v_data.length < 15) { // 如果一次加载小于15条，则说明已经到底。
                v_isMax = true;
            }
            v_page += 1;
			if (callback) {
				callback(v_data);
				return;
			}
            for (var i = 0; i < v_data.length; i++) {
            	var v_ranking = '<div class="ranking_list bb">';
            	if (v_currentRanking < 4) {
            		v_ranking += '<img src="./images/rank_0' + v_currentRanking + '.png" class="rank_img f_l">';
            	} else {
            		v_ranking += '<p class="rank_img3 f_l">' + v_currentRanking + '</p>';
            	}
            	var headImg = v_data[i].headImg;
            	if (headImg == null || headImg == "") {
            		headImg = "./images/headImg.jpg";
            	}
            	v_ranking += '<img src="' + headImg 
            		+ '" onerror="javascript:this.src=\'./images/headImg.jpg\'" class="rank_img2 f_l">';
            	var nickName = v_data[i].nickName;
            	if (nickName == null || nickName == "") {
            		nickName = "无名氏";
            	}
            	var returnArr = formatCarbonEmissionReduction(v_data[i].carbonEmissionReduction);
            	v_ranking += '<p class="rank_name f_l">' + nickName + '</p>';
            	v_ranking += '<p class="rank_number f_r"><span>' + returnArr[0] + '</span>' + returnArr[1] + '</p>';
            	v_ranking += '</div>';
            	$(".ranking").append(v_ranking);
            	v_currentRanking++; // 排名+1
            }
        } else {
        	v_isMax = true;
        }
    });
}

//判断滚动条有没到底部
/*
$(window).scroll(function(){
　　 var scrollTop = $(this).scrollTop();
　　 var scrollHeight = $(document).height();
　　 var windowHeight = $(this).height();
　　 if(scrollTop + windowHeight >= scrollHeight){
	    loadRankingList();
　　 }
});
*/