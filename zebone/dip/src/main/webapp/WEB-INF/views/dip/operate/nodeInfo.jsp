<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>节点管理内容</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-button.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.zTree.css"/>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
    <script type="text/javascript" src="js/jsp/dip/operate/nodeInfo.js"></script>
	<style type="text/css">
        html,body{
			background-color: white;
			overflow: hidden;
			height: auto;
		}
    </style>
    <script type="text/javascript">
		
    </script>
</head>
<body>
<div  >
<!--  容器 -->
<div class="container">
	<div class="c_nw">
    	<div class="c_ne"><div class="c_n"></div></div>
    </div>
    <!-- 容器标题部分 -->
    <span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">节点查询</span>
		</span>
	</span>
       <div class="tools-panel"></div>
       <div class="c_w">
           <div class="c_e">
               <div class="c_content">
                   <table id="query">
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;节点名称:&nbsp;&nbsp;<input type="text" name="nodeNameQuery"  style="width: 150px;" /></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;所属机构:&nbsp;&nbsp;
                        <input type="text" name="query_mhoName" id='query_mhoName' style="width: 150px;"/>
                       	<input type="hidden" name='query_orgCode'/>
                       	</td>
                        <td>
                              <a class="btn" href="javascript:void(0);">
						<span class="btn-left" id="querybtn">
							<span class="btn-text icon-search">查询</span>
						</span>
                              </a>
                        </td>
                       </tr>
                   </table>
               </div>
           </div>
       </div>
       <div class="c_sw">
           <div class="c_se">
               <div class="c_s"></div>
           </div>
       </div>
   </div>
</div>  
<div class="c_content" style="margin-left: 10px;margin-right: 10px;"><div id="grid"></div></div>
<div id='edit' style="display: none;">
	<form id="nodeForm" action="" class="checkForm">
		<!--  容器 -->
		<div class="container">
			<div class="c_nw">
		    	<div class="c_ne"><div class="c_n"></div></div>
		    </div>
		    <!-- 容器标题部分 -->
		    <span class="title-l">
				<span class="title-r">
					<b class="icon"></b><span class="title-span">节点信息</span>
				</span>
			</span>
		       <div class="tools-panel"></div>
		       <div class="c_w">
		           <div class="c_e">
		               <div class="c_content">
		                   <table id="mainTABLE" cellspacing="10" style="width: 100%;">
								<tr height="30px">
									<td>节点名称：</td>
									<td>
										<input type="hidden" name="id" id="nodeId" />
										<input size="30" type="text" name="nodeName1" title="节点名称" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-32"/>
									</td>
								</tr>
								<tr height="30px">
									<td>所属机构：</td>
									<td>
										<input size="30" type="text" name="nodeOrgName" title="所属机构" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-30" onclick="orgTreeChoice(this,'radio');"/>
										<input type="hidden" name='nodeOrg'  value=''/>
										<input type="hidden" name='nodeState'  value=''/>
									</td>
								</tr>
								<tr>
									<td>IP地址：</td>
									<td>
										<input size="30" type="text" name="nodeIp" title="IP地址" msg="输入IP格式不正确" reg="/^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([0-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/" validate="string|1-30"/>
									</td>
								</tr>
								<tr>
									<td>MQ地址：</td>
									<td>
										<input size="30" type="text" name="mqQueueUrl" title="MQ地址" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-30"/>
									</td>
								</tr>
								<tr>
									<td>节点说明：</td>
									<td>
										<textarea  cols="20" rows="3" style="width: 185px" name="nodeDesc" title="节点说明" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-200"></textarea>
									</td>
								</tr>
							</table>
		               </div>
		           </div>
		       </div>
		       <div class="c_sw">
		           <div class="c_se">
		               <div class="c_s"></div>
		           </div>
		       </div>
		   </div>
	</form>
</div>
</body>
</html>