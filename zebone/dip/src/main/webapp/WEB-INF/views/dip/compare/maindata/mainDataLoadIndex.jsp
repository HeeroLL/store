<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<btp:htmlbase/>
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css" />
		<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css" />
		<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css"/>
		<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
		
		<title>主数据对照导入</title>
		<script type="text/javascript" src="js/jsp/dip/compare/jquery.form.js"></script>
		<script type="text/javascript" src="js/jsp/dip/compare/maindata/index.js"></script>
		
		<script type="text/javascript">
		</script>
		<style type="text/css">
		    table {
		      width:100%;
		    }
		    #treetable td{
		      text-align:center;
		      font-size:16px;
		      font-weight:bold;
		    }
		     #treetable tr{
		      height:30px;
		    }
		</style>
	</head>
	<body>
		<!-- 左侧树 -->
		<div id="leftTree" style="width:20%;float:left;height:500px;" >
			<div class="container" >
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">主数据类型</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<div class="ztree" id="maintypeInfo"></div>
							 <!-- <table id="treetable">
							 	<tr><td><a href="javascript:changeTitle('医疗机构信息导入', 1);">医疗机构信息</a></td></tr>
							 	<tr><td><a href="javascript:changeTitle('医疗机构人员信息导入', 2);">医疗机构人员信息</a></td></tr>
							 	<tr><td><a href="javascript:changeTitle('行政区划信息导入', 3);">行政区划信息</a></td></tr>
							    <tr><td><a href="javascript:changeTitle('诊疗项目信息导入', 4);">诊疗项目信息</a></td></tr>
							 	<tr><td><a href="javascript:changeTitle('药品信息导入', 5);">药品信息</a></td></tr>
							 </table> -->
						</div>
					</div>
				</div>
				<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
			</div>	
		</div>
		<!-- 查询界面  -->
		<div id="main" style="width:80%;float:left;">
			<div class="container" >
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span" id="span_title">数据导入</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content" >
							<div style="width:400px;margin:10px auto 40px auto;text-align:center;">
							<form id="form2" style="float:left;" method="post" action="compare/maindata/uploadFile.zb" enctype="multipart/form-data" target="fileIframe">
							  <!-- File input -->  
							   导入文件: 
							  <input name="file" id="uploadInput" type="file" />
							  <input type="hidden" name="fileType" id="fileType" value=""/>
							  <input type="hidden" name="tableName" id="tableName" value=""/>
							  <input type='submit' name='submit' id='submitId' style="display:none;" value='提交' />
							   <iframe 
							    name="fileIframe"
							    id="fileIframe"
							    style="display:none;"
							    onload="uploadDone('fileIframe');"
							    tabindex="-1">
							  </iframe>
							</form>
							<button style="float:left;" value="Submit" id="btn_submit" onclick="uploadFormData();" style="position:0 -10px 0 0 ;">提交</button>
							<div id="result"></div>
							 <!--http://hmkcode.com/spring-mvc-upload-file-ajax-jquery-formdata/ -->
							</div>
						</div>
					</div>
				</div>
				<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
			</div>	
			
			<div class="container" >
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">导入结果</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							 <div style="text-align:center;">
							 	<!-- <span>入库成功数量：</span><span id="dbSuccessCount">--</span>
								&nbsp;&nbsp;
								<span>与数据库重复数量：</span><span id="dbFailedCount">--</span>
								&nbsp;&nbsp; -->
								<span>成功数量：</span><span id="checkSuccessCount">--</span>
								&nbsp;&nbsp;
							 	<span>失败数量：</span><span style="color:red;font-weight:bold;" id="checkFailCount">--</span>
							 </div>
							  
							 <div style="text-align:center;margin:20px 0 20px 0;">
								<a id="aDwnLoadLink" href="javascript:void()">详情下载</a>
								<span id="resultDiv" ></span>
							 </div>
						</div>
					</div>
				</div>
				<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
			</div>	
		</div>

	</body>
</html>