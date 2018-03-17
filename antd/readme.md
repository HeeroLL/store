- build 		// 生产环境代码生成目录
- config    	// webpack配置
- node_modules  // node模块目录（初始svn上不存在，本地安装node后在根目录执行npm install会自动生成）
- public		// 静态文件相关
  - favicon.ico // 网站小图标
  - index.html  // SPA的首页，一般情况下打包动态生成无需修改
  - manifest.json // 打包的文件描述自动生成
- scripts		// webpack脚本
  - build.js	// 生成环境代码生成脚本，执行npm run build生成代码到根目录的build目录
  - start.js	// 开发环境代码脚本，执行npm start本地会部署一个web服务用作开发
  - test.js		// 测试环境脚本，执行npm run test会对代码进行测试
- src
  - components	// 放各类通用/可复用组件
  - containers	// 放各个页面，即组件容器，按业务分目录
  - redux		// 单向数据流相关，如果只开发页面，建议前期不要用，比较深，难懂
  - utils		// 工具类相关
  - index.js	// 入口类
- package.json	// npm脚本文件
- package-lock.json	// npm脚本文件