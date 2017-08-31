var webpack = require('webpack');
var path = require('path');
var merge = require('webpack-merge');
var config = require('./webpack.base.config.js');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = merge(config, {
	externals: { // 打包时屏蔽掉的js
		"react": 'react',
		'react-dom': 'ReactDOM'	
	},
	devtool: 'none',
	module: {
		rules:[
			{
                test: /\.css$/,
				use: ExtractTextPlugin.extract({
					fallback: "style-loader",
					use: "css-loader"
				})			
            },
			{
                test: /(\.jsx|\.js)$/,
                use: {
                    loader: "babel-loader",
					options: {
                        presets: [
                            "es2015", "stage-0", "react"
                        ]
                    }
                },
				include: path.resolve(__dirname, 'src'),
                exclude: path.resolve(__dirname, 'node_modules')
            }
		]
	},
    plugins: [
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
		})
    ],
});