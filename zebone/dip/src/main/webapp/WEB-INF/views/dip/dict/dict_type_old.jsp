<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
 
        
    	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
        
        
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css"/>
        <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css"/>
       
        <script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
        <script type="text/javascript" src="js/jsp/dip/dict/dip_dict_old.js"></script>
        
        <style type="text/css">
			.left_input_label{
				 
				width:120px;
			 
			}
			.input_text{
				float:left;
				text-align:center;
				width:90px;
			}
			#dictionaryForm input[type=text]{
				width:150px;
			}
			input[disabled=disabled]{
				background:#E0E0E0 ;
				color:black;
			}
 	    </style>

    </head>
    <body>
    
        <div class="ui-layout-center">
        
        
        	<div  id="dict_container">
                <div class="container">
                    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
                    <span class="title-l">
                        <span class="title-r">
                            <b class="icon"></b><span class="title-span">字典类型查询</span>
                        </span>
                    </span>
                    <div class="tools-panel"></div>
                    <div class="c_w">
                        <div class="c_e">
                            <div class="c_content">
                            	<form id="style_query_form">
	                                <table id="queryType" border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
	                                    <tr>
	                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;类型编码：</td>
	                                        <td><input id="query_type_code" name="type_code" type="text" style="width: 110px;" maxlength="64" /></td>
	                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;类型名称：</td>
	                                        <td>
	                                            <input id="query_type_name" name="type_name" type="text" style="width: 110px;" maxlength="64" />
	                                        </td>
                                            <td>
                                                <a class="btn" href="javascript:void(0);">
                                                    <span class="btn-left" id="querytypebtn">
                                                        <span class="btn-text icon-search" id="findtype">查询</span>
                                                    </span>
                                                </a>
                                            </td>
	                                    </tr>
	                                </table>
                                 </form>
                            </div>
                        </div>
                    </div>
                    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
                </div>	
               
        
                <!-- 列表界面 -->
                <div id="dict_grid" style="margin:4px;border:1px solid #cdee64"></div>
                <!-- 数据字典编辑 -->
                <div id='edit' style="display: none;">
                    <form id="dictionaryTypeForm" action="" class="checkForm">
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
										<b class="icon"></b><span class="title-span">维护字典类型信息</span>
									</span>
								</span>
								<div class="c_w">
						        	<div class="c_e">
						        		<!-- 容器内容 -->
						            	<div class="c_content">
						            		  <input type="hidden" id="type_id" name="type_id" value=""/>
				                                <input type="hidden" id="parent_id" name="parent_id" value=""/>
				                                
				                                <table>
				                                    <tr>
				                                        <td class="left_input_label">类型编码：</td>
				                                        <td><input type="text" id="type_code" name="type_code" value="" /></td>
				                                    </tr>
				                                    <tr>
				                                        <td class="left_input_label">类型名称：</td>
				                                        <td><input type="text" id="type_name" name="type_name" value=""
                                                                   msg="输入格式不正确,不应包含特殊字符"
                                                                   reg="['$\\|&@#={}<>!^*~?！￥……（）—【】‘；：”“'。，、？]"
                                                                   validate="string|1-64"/></td>
				                                    </tr>
				                                    <tr>
				                                        <td class="left_input_label">父类型名称：</td>
				                                        <td>
				                                            <select name="parent_id" id="select_parent_id" style="width: 135px">
				                                                
				                                            </select>
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td class="left_input_label">版&nbsp;本&nbsp;号：&nbsp;</td>
				                                        <td><input type="text" id="version" name="version" value=""/></td>
				                                    </tr>
                                                    <tr  id="standard_codeTr">
                                                        <td class="left_input_label">字典标识：</td>
                                                        <td><input type="text" id="standard_code" name="standard_code"
                                                                   value="" msg="输入格式不正确,不应包含特殊字符"
                                                                   reg="['$\\|&@#={}<>!^*~?！￥……（）—【】‘；：”“'。，、？]"
                                                                   validate="string|1-16"/></td>
                                                    </tr>
				                                    <tr>
				                                        <td class="left_input_label">类型说明：</td>
				                                        <td> <textarea style="height:100px;width:150px;" type="text" id="remark" name="remark" value="" validate="string|1-128"></textarea></td>
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
            
            
            <div  id="domain_container">
                <div class="container">
                    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
                    <span class="title-l">
                        <span class="title-r">
                            <b class="icon"></b><span class="title-span">字典值域查询</span>
                        </span>
                    </span>
                    <div class="tools-panel"></div>
                    <div class="c_w">
                        <div class="c_e">
                            <div class="c_content">
                            	<form id="domain_query_form" >
	                               <table id="queryValue" border="0" cellpadding="1" cellspacing="1" align="center" style="float: left;margin-top:10px;">
	                                    <tr>
	                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;值：</td>
	                                        <td><input id="search_dict_code" name="dict_code" type="text" style="width: 110px;" reg="[]" msg="" validate="string|0-64"/></td>
	                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;值含义：</td>
	                                        <td>
	                                            <input id="search_dict_name" name="dict_name" type="text" style="width: 110px;" reg="[]" msg="" validate="string|0-64"/>
	                                        </td>
                                            <td>
                                                <a class="btn" href="javascript:void(0);">
                                                    <span class="btn-left" id="querydomainbtn">
                                                        <span class="btn-text icon-search">查询</span>
                                                    </span>
                                                </a>
                                            </td>
	                                    </tr>
	                                </table>
                                 </form>
                                 <a class="btn" id="goback" href="javascript:void(0);" style="float:right;margin:0 20px 0 0;">
                                     <span class="btn-left" id="querydomainbtn">
                                         <span class="btn-text icon-back">返回原字典类型界面</span>
                                     </span>
                                 </a>
                                 
                            </div>
                        </div>
                    </div>
                    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
                </div>	
               
        
                <!-- 列表界面 -->
                <div id="domain_grid"></div>
                <!-- 数据字典编辑 -->
                <div id='domain_edit' style="display: none;">
                    <form id="dictionaryForm" action="" class="checkForm">
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
										<b class="icon"></b><span class="title-span">维护字典值域信息</span>
									</span>
								</span>
								<div class="c_w">
						        	<div class="c_e">
						        		<!-- 容器内容 -->
						            	<div class="c_content">
						            		 <input type="hidden" id="dict_id" name="dict_id" value=""/>
				                             <input type="hidden" id="dicttype_id" name="dicttype_id" value=""/>
				                             <table>
				                                 <tr>
				                                     <td class="left_input_label">值：</td>
				                                     <td><input type="text" id="dict_code" name="dict_code" value="" reg="[]" msg="" validate="string|1-64"/></td>
				                                 </tr>
				                                 <tr>
				                                     <td class="left_input_label">值含义：</td>
				                                     <td><input type="text" id="dict_name" name="dict_name" value="" reg="[]" msg="" validate="string|1-64"/></td>
				                                 </tr>
				                                 <tr>
				                                     <td class="left_input_label">值说明：</td>
				                                     <td> <textarea style="height:100px;width:150px;" type="text" id="remark" name="remark" value="" reg="[]" msg="" validate="string|1-128"></textarea></td>
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
  
        </div>
        
        
        <div class="ui-layout-west" style="overflow:auto;">
        	<!--  容器 -->
			<div class="container" style="overflow:auto;">
				<div class="c_nw">
			    	<div class="c_ne"><div class="c_n"></div></div>
			    </div>
			    <!-- 容器标题部分 -->
			    <span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">字典类型树</span>
					</span>
				</span>
				<div class="c_w" style="overflow:auto;">
		        	<div class="c_e" style="overflow:auto;">
		        		<!-- 容器内容 -->
		            	<div class="c_content">
		            		<div id="type_tree" class="ztree" style="overflow:auto;"></div>
		                </div>
		            </div>
		       	</div>
				<div class="c_sw">
		        	<div class="c_se"><div class="c_s"></div></div>
		        </div>
			</div>
        </div>
   		
    </body>
</html>