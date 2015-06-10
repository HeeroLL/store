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
		<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
		<link href="js/jsp/dip/compare/uploadfile.css" rel="stylesheet">
		<script src="js/jsp/dip/compare/jquery.uploadfile.min.js"></script>
		<title>字典对照导入</title>
		<script type="text/javascript" src="js/jsp/dip/compare/dictionary/index1.js"></script>
		
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
		 
		<!-- 查询界面  -->
		<div id="main" style="width:80%;margin:0 auto 0 auto;">
			<div class="container" >
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">字典数据导入</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content" >
							<div style="width:400px;margin:10px auto 20px auto;text-align:center;">
							<form id="form1" style="float:left;" name="form1" method="post" enctype="multipart/form-data" action="compare/maindata/uploadFile.zb" target="fileIframe">
							  <input id="uploadInput" type="file" name="file" />
							  <input type="hidden" name="fileType" value="6"/>
							  <input type='submit' name='submit' id='submitId' style="display:none;" value='提交' />
							  <iframe 
							    name="fileIframe"
							    id="fileIframe"
							    style="display:none;"
							    onload="uploadDone('fileIframe');"
							    tabindex="-1">
							  </iframe>
							</form>
							<!-- <form id="form2" style="float:left;" method="post" action="compare/maindata/uploadFile.zb" enctype="multipart/form-data">
							  导入文件:
							  <input name="file2" id="file2" type="file" />
							</form> -->
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