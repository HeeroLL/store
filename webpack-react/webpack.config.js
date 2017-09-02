var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {	
	externals: { // 打包时屏蔽掉的js
		"react": 'react',
		'react-dom': 'ReactDOM'	
	},
	entry: {
		core: __dirname + '/src/component/core.js',
		carbonShare : __dirname + '/src/carbonShare.js',
		// vendor: ['react','react-dom','fastclick']
		vendor: ['fastclick']
	},
	output: {
		path: __dirname + "/dist",
		filename: 'js/[name].js'
		// publicPath: './dist/' // 通常这里是指定CDN路径
	},
	devServer: {
		contentBase: "./dist",//本地服务器所加载的页面所在的目录
		// historyApiFallback: true,//不跳转
		inline: true,//实时刷新
		hot: true
	},
	devtool: 'none',
	module: {
		rules:[
			{
                test: /(\.jsx|\.js)$/,
                use: {
                    loader: "babel-loader",
					options: {
                        presets: [
                            "es2015", "stage-0", "react"
                        ]/*,
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
						}*/
                    }
                },
				include: path.resolve(__dirname, 'src'),
                exclude: path.resolve(__dirname, 'node_modules')
            },
            {
                test: /\.css$/,
				use: ExtractTextPlugin.extract({
					fallback: "style-loader",
					use: "css-loader"
				})
            },
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
        new webpack.optimize.OccurrenceOrderPlugin(),
        new webpack.optimize.UglifyJsPlugin({
			output: {
				comments: false,  // remove all comments
			},
			compress: {
				warnings: false // 删除警告提升代码
			}
		}),
		new webpack.DefinePlugin({ // 生产环境插件
			'process.env':{
				'NODE_ENV': JSON.stringify('production')
			}
		}),
		new ExtractTextPlugin({
            filename: "[name].css"
			// filename: "styles.css"
        }),
		new webpack.optimize.CommonsChunkPlugin({
			name: 'vendor'
		})/*,
		new webpack.HotModuleReplacementPlugin()*/
    ],
};