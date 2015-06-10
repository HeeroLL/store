<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EHRView</title>
		<!-- 指向http://xxx.xxx.xxx:xxxx/cmstudio/ -->
		<base href='http://192.168.1.199:8080/EHRView/'/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/five.ico"/>
		<!--Start importing the jquery files -->
		<script src="resources/vendor/jquery_1.8/jquery-1.8.3.min.js" type="text/javascript"></script>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/bootstrap/easyui.css" />
		<script src="resources/vendor/easyui_1.3.2/jquery.easyui.min.js" type="text/javascript"></script>
		<script src="resources/vendor/easyui_1.3.2/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<!--End importing the jeasyui files -->
		<link rel="stylesheet" type="text/css" href="resources/vendor/ztree_3.5/css/zTreeStyle.css" />
		<script src="resources/vendor/ztree_3.5/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
		<script src="resources/vendor/underscore/underscore.js"></script>
		<script src="resources/vendor/backbone/backbone.js"></script>
		<script type="text/javascript">
			$.parser.auto=false;
			$(function(){
				$("#a_indexPage").click(function(event){
					$('#div_index_tab').tabs("select",0);
					event.preventDefault();
					return false;
				});
				
				_.templateSettings = {
				    interpolate: /\<\@\=(.+?)\@\>/gim,
				    evaluate: /\<\@([\s\S]+?)\@\>/gim,
				    escape: /\<\@\-(.+?)\@\>/gim
				};
				
				var SpecDoc = Backbone.Model.extend({
					defaults:{
						name:'',
						time:'',
						org:'',
						code:''
					}
				});
				
				var SpecDocList = Backbone.Collection.extend({
					model: SpecDoc,
					url:'home/specialDocumentList.zb'
				});
				
				var DocInfoView = Backbone.View.extend({
					template:_.template($("#specInfoList_template").html()),
					render:function(){
						this.$el.html(this.template(this.model.toJSON()));
						return this;
					}
				});
				
				
				var DocInfoListView = Backbone.View.extend({
					render:function(){
						this.collection.forEach(function(doc){
							var docView = new DocInfoView({
								model:doc
							});
							$("#div_specInfoList").append(docView.render().el);
						});
						$.parser.parse();
					}
				});
				
				var specDocList = new SpecDocList();
				var specDoc = new SpecDoc();
				
				var listView = new DocInfoListView({
					collection:specDocList,
					model:specDoc
				});
				
				var docView = new DocInfoView({
					model:specDoc
				});
				
				specDocList.on('sync', listView.render, listView);
				
				specDocList.fetch();
				
				
				
			});
		</script>
		
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:true">

			<!-- Head -->
		    <div data-options="region:'north',split:false,collapsible:false" style="height:30px;padding:6px 40px 0 20px;">
		    	<span style="font-weight:bold;folat:left;">健康档案浏览器</span>
		    	<span style="float:right;">单位：某某社区卫生服务中心&nbsp;&nbsp;&nbsp;用户：某某某<a href="#" id="a_indexPage" style="margin-left:100px;">首页</a></span>
		    </div>
		    <div data-options="region:'center',split:false,collapsible:false" style="background:#eee;">
		    	<div class="easyui-layout" data-options="fit:true">
		    		<!-- 首页 -->
		    		<div data-options="region:'north',title:'基本信息',iconCls:'icon-save'" style="height:100px;">
		    			 <div style="padding:3px 30px 1px 30px;font-size:16px;font-weight:bold;text-align:center;">
		    			 	<span> 姓名：${name}； </span>
		    			 	<span> 性别：${sex}；</span>
		    			 	<span> 年龄：${age}；</span>
		    			 	<span><a href="#">详情</a></span>
		    			 </div>
		    			 <div style="margin:10px 0 0 0 ;line-height:21px;font-size:14px;text-align:center;">
		    			 	<span> 身份证号：${card_no}； </span>
		    			 	<span> 出生日期：${birthday}；</span>
		    			 	<span> 联系电话：${contacts_mobile}；</span>
		    			 	<span>现住址:${address}；</span>
		    			 </div>
		    		</div>
		    		<!-- 基本信息 -->
			    	<div data-options="region:'center',split:false,collapsible:false" style="background:#eee;">
			    		 <div id="div_index_tab" class="easyui-tabs" data-options="tabPosition:'left', fit:true">
			    				<div title="首页" data-options="iconCls:'icon-sum'" style="padding:2px">
			    					<div style="height:auto;">
							            <div class="easyui-panel" title="健康基本信息" style="padding:10px;background:#fafafa;" data-options="iconCls:'icon-save',closable:false,fit:true,collapsible:true">
										     <div style="padding:1px 30px 3px 30px;line-height:21px;font-size:14px;text-align:center;">
							    			 	<span>药物过敏史：${allergyHistory}； </span>
							    			 	<span>暴露史：${exposeHistory}；</span>
							    			 	
							    			 	<span>既往史：${allergyHistory}；</span>
							    			 	<span>手术史:${operationHistory}；</span>
							    			 </div>
										</div>
									</div>
									<div style="height:auto;">
										<div id="div_specDocPanel" class="easyui-panel" title="专项档案" style="padding:10px;background:#fafafa;" data-options="iconCls:'icon-edit',closable:false,fit:true,collapsible:true">
										    <div id="div_specInfoList" style="padding:1px 0px 3px 0px;line-height:21px;font-size:14px;text-align:center;">
										    	 <script type="text/template" id="specInfoList_template">
													 <a href="#"><@= name @></a><br/>
													 建档时间：<@= time @>  &nbsp;&nbsp;建档社区：<@= org @>
													 <hr/> 
												 </script>
							    			 </div>
										</div>
									</div>
						        </div>
						        <div title="卫生服务活动" data-options="iconCls:'icon-search',href:'HealthServiceActivity/healthServiceActivity.zb'" style="padding:2px;">
						        </div>
						        <div title="健康和疾病问题" data-options="iconCls:'icon-help'" style="padding:2px">
						        </div>
						        <div title="生命阶段" data-options="iconCls:'icon-tip'" style="padding:2px">
						            This is the help content.
						        </div>
					    </div>
			    	</div>
			    </div>
		    </div>
		</div>
 	</body> 
</html>