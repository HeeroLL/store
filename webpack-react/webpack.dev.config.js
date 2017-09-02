var webpack = require('webpack');
var path = require('path');
var merge = require('webpack-merge');
var config = require('./webpack.base.config.js');

module.exports = merge(config, {
	devServer: {
		contentBase: "./dist",//本地服务器所加载的页面所在的目录
		// historyApiFallback: true,//不跳转
		inline: true,//实时刷新
		hot: true
	},
	devtool: 'cheap-eval-source-map',
	module: {
		rules:[
			{
                test: /\.css$/,
				use: [
					{
						loader: 'style-loader',
					},
					{
						loader: 'css-loader',
					}
				]		
            },
			{
                test: /(\.jsx|\.js)$/,
                use: {
                    loader: "babel-loader",
					options: {
                        presets: [
                            "es2015", "stage-0", "react"
                        ],
						"env": {
							"development": {
								"plugins": [
									["react-transform", {
										"transforms": [{
											"transform": "react-transform-hmr",							 
											"imports": ["react"],							 
											"locals": ["module"]
										}]
									}]
								]
							}
						}
                    }
                },
				include: path.resolve(__dirname, 'src'),
                exclude: path.resolve(__dirname, 'node_modules')
            }
		]
	},
    plugins: [		
		new webpack.DefinePlugin({ // 开发环境插件
			'process.env':{
				'NODE_ENV': JSON.stringify('development')
			}
		}),
		new webpack.HotModuleReplacementPlugin()
    ],
});