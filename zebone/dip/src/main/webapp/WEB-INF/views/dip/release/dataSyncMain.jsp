<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE>
<html>
	<head>
    	<meta charset="UTF-8">
        <btp:htmlbase/>
        <link rel="stylesheet" type="text/css" href="css/icons.css">
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css"/>
    	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"/>
        <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css"/>
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
 		<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
 
        
    	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.autocompleteForDict.js"></script>
        
        
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css"/>
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css"/>
       
        <script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
        <script type="text/javascript" src="js/jsp/dip/release/dataSyncMain.js"></script>
        <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
        
        <style type="text/css">
        	.hiddenSelect{
        		display:none;
        	}
			.left_input_label{
				 
				width:80px;
				padding-left:20px;
			 
			}
			.input_text{
				float:left;
				text-align:center;
				width:130px;
			}
			#dictionaryForm input[type=text]{
				width:180px;
			}
			input[disabled=disabled]{
				background:#E0E0E0 ;
				color:black;
			}
 	    </style>

    </head>
    <body>
        
        	<div  id="dict_container">
                <div class="container">
                    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
                    <span class="title-l">
                        <span class="title-r">
                            <b class="icon"></b><span class="title-span">基础数据同步</span>
                        </span>
                    </span>
                    <div class="tools-panel"></div>
                    <div class="c_w">
                        <div class="c_e">
                            <div class="c_content">
                            	<!-- 列表界面 -->
               					<div id="dataSyncMain_grid"></div>
                            </div>
                        </div>
                    </div>
                    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
                </div>	
               
                <!-- 数据字典编辑 -->
                <div id='editdataSyncMainEdit' style="display: none;">
                    <form id="editdataSyncMainForm" class="checkForm">
                        <div class="container">
                            <div class="tools-panel"></div>
                            
                            <!--  容器 -->
							<div class="container">
								<div class="c_nw">
							    	<div class="c_ne"><div class="c_n"></div></div>
							    </div>
							    <!-- 容器标题部分 -->
							    <span class="title-l">
									<span class="title-r">
										<b class="icon"></b><span class="title-span">维护同步设置</span>
									</span>
								</span>
								<div class="c_w">
						        	<div class="c_e">
						        		<!-- 容器内容 -->
						            	<div class="c_content">
						            		<input type="hidden" id="input_hidden_id" name="id" value="" />
				                                <table>	
				                                    <tr>
				                                        <td class="left_input_label">系统名称：</td>
				                                        <td><input type="text" id="sysName" name="sysName" value="" /></td>
				                                        <td class="left_input_label">数据源名称：</td>
				                                        <td>
				                                            <select name="dataSourceId" id="select_dataSourceId" style="width: 135px">
				                                                <c:forEach items="${dsObjList}" var="item">
				                                                	<option value="${item.id}">${item.pName}</option>
				                                                </c:forEach>
				                                            </select>
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td class="left_input_label">WS同步地址：</td>
				                                        <td  colspan="3"><input type="text" id="wsUrl" name="wsUrl" style="width:400px;" value=""/></td>
				                                    </tr>
				                                    
				                                    <tr>
				                                        <td class="left_input_label">同步设置：<input type="hidden" id="syncIsAll" name="isAll"></td>
				                                        <td class="left_input_label"><input type="checkbox" id="syncMainData" name="syncMainData"/>同步所有主数据</td>
				                                        <td style="width:120px;"><input type="checkbox" id="syncDictData" name="syncDictData"/>同步所有数据字典</td>
				                                        <td class="left_input_label"><input type="checkbox" id="syncMetaData" name="syncMetaData"/>同步所有元数据</td>
				                                    </tr>
				                                    <tr >
				                                        <td  colspan="4" style="padding-left:20px;">最近同步时间：<input type="text" class="Wdate" style="width: 190px;" name="currentSyncTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				                                    </tr>
				                                    <tr>
				                                        <td colspan="3" class="left_input_label">
				                                        <a class="btn" href="javascript:void(0);" id="btn_addDataTable"> <span class="btn-left"> <span class="btn-text icon-add">添加数据表</span> </span> </a>
				                                        <td></td>
				                                    </tr>
				                                     <tr>
				                                        <td colspan="4" class="left_input_label">
				                                        	<table id="tableManageTable" style="border-collapse:collapse;" width="100%" border="1px" >
				                                        		<tr>
				                                        			<td>数据类型</td>
				                                        			<td>数据内容</td>
				                                        			<td>操作<input type="hidden" id="input_tableNames" name="tableNames"/></td>
				                                        		</tr>
				                                        		<tr id="tableInitRow" style="display:none;">
				                                        			<td>
				                                        				<select onchange="changeSelectedDataType(this);">
				                                        					<option value="0">主数据</option>
				                                        					<option value="1">数据字典</option>
				                                        				</select>
				                                        			</td>
				                                        			<td>
			                                        					<select id="select_md" style="width: 135px">
							                                                <c:forEach items="${MDList}" var="item">
							                                                	<option value="${item.tableName}">${item.name}</option>
							                                                </c:forEach>
							                                            </select>
							                                            <input class="autoComplete" type="text" style="width:200px;"/>
				                                        			</td>
				                                        			<td>
				                                        			<a class="btn" href="javascript:void(0);" onclick="$(this).parent().parent().remove();"> <span class="btn-left"> <span class="btn-text icon-remove">删除</span> </span> </a>
				                                        			</td>
				                                        		</tr>
				                                        	</table>
				                                        </td>
				                                    </tr>
				                                    
				                                </table>
						                </div>
						            </div>
						       	</div>
								<div class="c_sw">
						        	<div class="c_se"><div class="c_s"></div></div>
						        </div>
							</div>
                           
                        </div>
                    </form>
                </div>
            </div>
    </body>
</html>