var webpack = require('webpack');

var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {		
	entry: {
		// vendor: ['react','react-dom','fastclick'],
		vendor: ['fastclick'],
		core: __dirname + '/src/component/core.js',
		carbonShare : __dirname + '/src/carbonShare.js',
		carbonRanking : __dirname + '/src/carbonRanking.js'
	},
	output: {
		path: __dirname + "/dist",
		filename: 'js/[name].js'
		// publicPath: './dist/' // 通常这里是指定CDN路径
	},	
	module: {
		rules:[
			{
				test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
				loader: "url-loader?limit=4096&name=images/[name].[ext]"
			}
		]
	},
    plugins: [        
		new HtmlWebpackPlugin({
			filename: 'carbonShare.html',
			template: 'src/template/wap.html',
			title: '碳积分分享',
			chunks: ['vendor','core','carbonShare'],
			chunksSortMode: 'manual' // 导出的js按chunks的数组顺序排序
		}),
		new HtmlWebpackPlugin({
			filename: 'carbonRanking.html',
			template: 'src/template/wap.html',
			title: '碳积分排名',
			chunks: ['vendor','core','carbonRanking'],
			chunksSortMode: 'manual' // 导出的js按chunks的数组顺序排序
		}),
        new webpack.optimize.OccurrenceOrderPlugin()		
    ],
};