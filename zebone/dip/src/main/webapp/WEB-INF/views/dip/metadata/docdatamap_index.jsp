<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase />
<title>文档节点映射</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css" />
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css" />
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id="" />
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css" id="" />

<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid-n.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jsp/dip/metadata/map.js"></script>


<style>
ul.ztree {margin-top:10px;width:220px;}
</style>
</head>
<body>
	<div class="container">
		<div class="c_nw">
			<div class="c_ne">
				<div class="c_n"></div>
			</div>
		</div>
		<span class="title-l"> <span class="title-r"> <b
				class="icon"></b><span class="title-span">文档数据映射</span> </span> </span>
		<div class="tools-panel"></div>
		<div class="c_w">
			<div class="c_e">
				<div class="c_content">
                    <div style="margin: 10px;">
                        <span style="font-size: 15px;">文档名称：${docName}</span>
                    </div>
					<form action="" method="post" id="docDataMap">
					    <input type="hidden" name="docId" value="${docId}"/>
						<div id="grid" style="margin:10px"></div>
					</form>
					<div style="text-align: center; margin: 13px">
						<a href="javascript:void(0);" class="btn"> 
							<span class="btn-left"> 
								<span class="btn-text icon-save" onclick="javascript:tempSubForm();">暂存</span> 
							</span>
                        </a>
						<a href="javascript:void(0);" class="btn"> 
							<span class="btn-left"> 
								<span class="btn-text icon-save" onclick="javascript:subForm();">保存</span> 
							</span>
                        </a>
                        <a class="btn" href="javascript:void(0);" >
		                    <span class="btn-left">
		                        <span class="btn-text icon-back" onclick="javascript:backToDoc();">返回</span>
		                    </span>
                        </a>
					</div>
				</div>
			</div>
		</div>
		<div class="c_sw">
			<div class="c_se">
				<div class="c_s"></div>
			</div>
		</div>
	</div>
	<div id="menuContent" style="display:none;position:absolute;overflow-x:scroll;overflow-y:scroll;height:300px;background-color:#f0f6e4;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<input type="hidden" id="srnode"/>
	<script type="text/javascript">
        var projectPath = '${pageContext.request.contextPath}';
	    var map = new Map();
	    var start = 0;
	    var tableHeight = document.documentElement.clientHeight - 110;
	    var skip = Math.ceil(tableHeight/20);
	    var startMax = 0;
	    var docId = '${docId}';
	    var grid;
	    var load = false;

	    function init(){
	      $(document.body).mask("正在加载，请稍后...");
	      var treeData = ${treeValue};
	      var mod = treeData.length % skip;
	      if(mod == 0){
	      	startMax = treeData.length / skip;
	      }else{
	      	startMax = treeData.length / skip + 1;
	      }
	      initTable();
	      initTree(treeData);
	      getDocMapData(false);	      
	    }
	    
	    function initScroll(){
	       $('.jgridDataDIV').scroll(function(){
	       	     var bottomHeight = $('.jgridDataDIV').scrollTop();
	       	     var aHeight = (start-1)* tableHeight + 50;
	       	     if(bottomHeight > aHeight && !load){
	       	        getDocMapData(true);
	       	     }
	       });
	    }
	    
	    function getDocMapData(showInfo){
	        load = true;
	        if(showInfo){
	        	 $(document.body).mask("正在加载，请稍后...");
	        }
	        if(start < startMax){
	        	$.post('metadata/getDocDataMap.zb',{docId:docId,start:start,skip:skip}, function(data) {
	    	     if(typeof(data) != "undefined"){
	    	     	 for(var i=0;i<data.length;i++){
	    	     	    var join = '';
	    	     	    var trstyle = '';
	    	     	    var tdhstyle = '';
						for(var j = 3; j < data[i].level; j++) {
							join += '&nbsp;&nbsp;&nbsp;&nbsp;';
						}
						if(data[i].level == 1){
							tdhstyle="font-size:15px;";
						}else if(data[i].level == 2){
						    tdhstyle="font-size:15px;";
						}else if(data[i].level == 3){
							tdhstyle="font-size:15px;";
							trstyle="background-color:#CDEE64;"
						}
					    data[i].nodeCName = join + data[i].nodeCName;
				        grid.addRecord(data[i],trstyle,tdhstyle);
	    	        	grid.syncWidth();
	    	     	 }
	    	     	 start++;
	    	     	 autoComplete();
	    	     	 if(!showInfo){
	    	     	 	initScroll();
	    	     	 }
	    	     	 $(document.body).unmask();
	    	     	 JDialog.tip("加载成功",1.5);
	    	     }else{
	    	         $(document.body).unmask();
	    	     	 JDialog.tip("加载失败",1.5);
	    	     }	
	    	     load = false;     
   				});
	        }else{
	            $(document.body).unmask();
	        }   	
	    }
	    
	  		
		function initTable() {
			grid = new JGrid({
				title : '',
				checkbox : false,
				striped :  true,
				pageBar : false,
				render : 'grid',
				height : tableHeight,
				col : [ {
					text : '文档节点',
					textAlign : 'left',
					align : 'left',
					width : 300,
					dataIndex : 'nodeCName'
				}, {
					text : '节点逻辑上级',
					textAlign : 'center',
					align : 'center',
					width : 150,
					dataIndex : 'pNodeCName'
				},{
					text : '表名',
					textAlign : 'center',
					align : 'center',
					width : 150,
					dataIndex : 'table'
				}, {
					text : '表字段',
					textAlign : 'center',
					align : 'center',
					width : 150,
					dataIndex : 'col'
				}, 
				/**
				{
					text : '函数',
					textAlign : 'center',
					align : 'center',
					width : 0,
					dataIndex : 'function'
				}, {
					text : '组名',
					textAlign : 'center',
					align : 'center',
					width : 0,
					dataIndex : 'group'
				},
				*/{
					text : '行增加',
					width : 70,
					textAlign : 'center',
					align : 'center',
					dataIndex : 'ope'
				} ]
			});
		}
		
		/** 新增外键映射行  **/
		function addRow(obj, e, i) {
	        var num = $('#ddm_row_'+i+"_n").val();
			var smpara = 'ddmr_fi_o_' + i +'_'+num;
			var node = "<input type='text' style='width:100px' id='ddmr_fi_o_"+i+'_'+num+"'/>&nbsp;"+
			           "<input type='hidden' id='ddmr_fi_"+i+"_"+num+"' name='ddm["+i+"].dm2["+num+"].mappingId'/>"+
			           "<a id='menuBtn' href='#' onclick='javascript:showMenu(\""+ smpara + "\");return false;'>选择</a>";
			var table = '<input type="hidden" type="text" id="ddmr_table_'+i+'_'+num +'" />';
			table = table + '<input type="text" class="combo-text" type="test" id="ddmr_table_o_'+i+'_'+num +'" />';
					
			var ope = "<a href='javascript:void(0)' onclick='javascript:removeRow(this,event,\""+ i +"\");'>删除</a>";
			
			var col = '<input type="hidden" name="ddm['+i+'].dm2['+num+'].cloumnId" id="ddmr_col_'+i+'_'+num +'"/>';
			col = col + '<input type="text" class="combo-text" id="ddmr_col_o_'+i+'_'+num +'"/>';
			var data = {
				pNodeCName : node,
				table : table,
				col : col,
				ope : ope,
			};
			var index = obj.parentNode.parentNode.rowIndex;//获取当前点击行的索引
			grid.insertRow(data, index + 1, '');//在下方插入+1

			var tid = 'ddmr_table_o_'+i+'_'+num;
			var thid = 'ddmr_table_' +i+'_'+num;
			var opttable = {
				serviceUrl : 'metadata/getTable.zb',
				select : true,
				noCache : false,//不要缓存
				width : 384,
				textField : 'name',//显示值对应的json的name
				valueField : {
					dataIndex : 'id',
					dataId : thid,
				},//json的name和隐藏文本的name 
				col : [ {
					width : 150,
					dataIndex : 'name',
				} ],
				onSelect : function(s, d, m) {
						var colcpl = map.get(tid.replace("table", "col"));
						colcpl.options.params.tableId = $('#' + thid).val();
						
						//表名改变时，自动清空字段名中的值
						var coltid = tid.replace("table","col");
						var colthid = thid.replace("table","col");
						$("#"+coltid).val('');
						$("#"+colthid).val('');
				}
			};
		    map.put(tid,$('#' + tid).autocomplete(opttable));
			
			var cid = tid.replace('table', 'col');
			var chid = thid.replace("table", "col");
			var tableId = $('#' + thid).val();
			var optcol = {
				params : {
					'tableId' : tableId,
				},
				serviceUrl : 'metadata/getCol.zb',
				select : true,
				noCache : false,//不要缓存
				width : 384,
				textField : 'name',//显示值对应的json的name
				valueField : {
					dataIndex : 'id',
					dataId : chid,
				},//json的name和隐藏文本的name 
				col : [ {
					width : 150,
					dataIndex : 'name',
				} ],
			};
			map.put(cid,$('#' + cid).autocomplete(optcol));

			$('#ddm_row_' + i + "_n").val(++num);
		}

		function removeRow(obj, e, i) {
			var index = obj.parentNode.parentNode.rowIndex;//获取当前点击行的索引
			grid.removeRecord(index);
			var num = $('#ddm_row_' + i + "_n").val();
			$('#ddm_row_' + i + "_n").val(num--);
			map.remove("ddmr_col_o" +i);
			map.remove("ddmr_table_o" +i);
		}
	    
	    /***** autocomplete *****/
		function autoComplete() {
			/** 绑定表  **/
		  $("input[id^='ddm_table_o']").each(function(index, obj) {
			var tableInputId = $(obj).attr("id");
		    if(typeof(map.get(tableInputId)) == "undefined"){
				var tableInputHiddenId = tableInputId.replace("_o", "");
				var opttable = {
					serviceUrl : 'metadata/getTable.zb',
					select : true,
					noCache : false,//不要缓存
					width : 384,
					textField : 'name',//显示值对应的json的name
					valueField : {
						dataIndex : 'id',
						dataId : tableInputHiddenId,
					},//json的name和隐藏文本的name 
					col : [ {
						width : 150,
						dataIndex : 'name',
					} ],
					onSelect : function(s, d, m) {
						var colcpl = map.get(tableInputId.replace("table", "col"));
						colcpl.options.params.tableId = $('#' + tableInputHiddenId).val();
						
						//表名改变时，自动清空字段名中的值
						var colInputId = tableInputId.replace("table","col");
						var colInputHiddenHid = tableInputHiddenId.replace("table","col");
						$("#"+colInputId).val('');
						$("#"+colInputHiddenHid).val('');
					}
				};
				var tablecpl = $(obj).autocomplete(opttable);
				map.put(tableInputId, tablecpl);
			  }
			});
			
			/** 绑定表列  **/
			$("input[id^='ddm_col_o']").each(function(index, obj) {
			   var colInputId = $(obj).attr("id");
			   if(typeof(map.get(colInputId)) == "undefined"){
				var colInputHiddenId = colInputId.replace("_o", "");
				var tableInputHiddenid = colInputHiddenId.replace("col", "table");
				var optcol = {
					params : {
						'tableId' : $('#' + tableInputHiddenid).val(),
					},
					serviceUrl : 'metadata/getCol.zb',
					select : true,
					noCache : false,//不要缓存
					width : 384,
					textField : 'name',//显示值对应的json的name
					valueField : {
						dataIndex : 'id',
						dataId : colInputHiddenId
					},//json的name和隐藏文本的name 
					col : [ {
						width : 150,
						dataIndex : 'name'
					} ],
				};
				var colcpl = $(obj).autocomplete(optcol);
				map.put(colInputId, colcpl);
			  }
			});
			
			/** 绑定外键表  **/
			$("input[id^='ddmr_table_o']").each(function(index, obj) {
				var tableInputId = $(obj).attr("id");
				if(typeof(map.get(tableInputId)) == "undefined"){
				var tableInputHiddenId = tableInputId.replace("_o", "");
				var opttable = {
					serviceUrl : 'metadata/getTable.zb',
					select : true,
					noCache : false,//不要缓存
					width : 384,
					textField : 'name',//显示值对应的json的name
					valueField : {
						dataIndex : 'id',
						dataId : tableInputHiddenId,
					},//json的name和隐藏文本的name 
					col : [ {
						width : 150,
						dataIndex : 'name',
					} ],
					onSelect : function(s, d, m) {
						var colcpl = map.get(tableInputId.replace("table", "col"));
						colcpl.options.params.tableId = $('#' + tableInputHiddenId).val();
						
						//表名改变时，自动清空字段名中的值
						var colid = tableInputId.replace("table","col");
						var colhid = tableInputHiddenId.replace("table","col");
						$("#"+colid).val('');
						$("#"+colhid).val('');
					}
				};
				var tablcpl = $(obj).autocomplete(opttable);
				map.put(tableInputId, tablcpl);
				}
			});
			
			/** 绑定外键列  **/
			$("input[id^='ddmr_col_o']").each(function(index, obj) {
				var colInputId = $(obj).attr("id");
				if(typeof(map.get(colInputId)) == "undefined"){
				var colInputHiddenId = colInputId.replace("_o", "");
				var tableInputHiddenId = colInputHiddenId.replace("col", "table");
				var optcol = {
					params : {
						'tableId' : $('#' + tableInputHiddenId).val(),
					},
					serviceUrl : 'metadata/getCol.zb',
					select : true,
					noCache : false,//不要缓存
					width : 384,
					textField : 'name',//显示值对应的json的name
					valueField : {
						dataIndex : 'id',
						dataId : colInputHiddenId,
					},//json的name和隐藏文本的name 
					col : [ {
						width : 150,
						dataIndex : 'name',
					} ],
				};
				var colcpl = $(obj).autocomplete(optcol);
				map.put(colInputId, colcpl);
				}
			});
		}
	    
		
		/***** ztree *******/
		function initTree(data) {
			var setting = {
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					beforeClick : beforeClick,
					onClick : onClick
				}
			};
			var nodes = new Array();
			$.each(data, function(index, ele) {
				var id = ele.id;
				var pid = ele.pid;
				var nCname = ele.nodeCName;
				var fieldId = ele.fieldId;
				var isOpen = ele.floor ? false : true;
				var node = {
					id : id,
					name : nCname,
					open : isOpen,
					pId : pid,
					fieldId : fieldId,
					floor: ele.floor,
					mappingId: ele.mappingId
				};
				if(nCname != ""){
					nodes.push(node);
				}
				
			});
			$.fn.zTree.init($("#treeDemo"), setting, nodes);
		}

		function beforeClick(treeId, treeNode) {
			var check = (treeNode && !treeNode.isParent);
			if (!check){
				JDialog.tip("只能选择子结点",2);
				return check;
			}
			if(!treeNode.floor){
				JDialog.tip("只能选择源数据",2);
				return treeNode.floor;
			}
            return true;
		}

		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
					.getSelectedNodes(), v = "", hv = "";
			nodes.sort(function compare(a, b) {
				return a.id - b.id;
			});
			for ( var i = 0, l = nodes.length; i < l; i++) {
				v += nodes[i].name + ",";
				hv += nodes[i].mappingId + ",";
			}
			if (v.length > 0)
				v = v.substring(0, v.length - 1);
			if (hv.length > 0)
				hv = hv.substring(0, hv.length - 1);
			var snid = $('#srnode').val();
			var srnode = $("#" + snid);
			var srhnode = $("#" + snid.replace("_o", ""));
			srnode.attr("value", v);
			srhnode.val(hv);
			hideMenu();
		}

		function showMenu(id) {
			$('#srnode').val(id);
			var dh = $(document).height();
			var nodeObj = $("#" + id);
			var nodeOffset = $("#" + id).offset();
             
			var top = nodeOffset.top + nodeObj.outerHeight();
			var treeHight = $('#menuContent').height(); 
			if (dh < top + treeHight) {
				top = top - treeHight - nodeObj.outerHeight();
			}
			$("#menuContent").css({
				left : nodeOffset.left + "px",
				top : top + "px"
			}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn"
					|| event.target.id == "menuContent" || $(event.target)
					.parents("#menuContent").length > 0)) {
				hideMenu();
			}
		}

		/***提交表单 **/
		function subForm() {
            if(checkForm()){
                $(document.body).mask("正在加载，请稍后...");
				var options = {
					url : 'metadata/addDocDataMap.zb',
					dataType : 'json',
					success : function(data) {
						$(document.body).unmask();
						if (data == 'succeed') {
							JDialog.tip("保存成功", 1.5);
						} else {
							JDialog.tip("保存失败", 1.5);
						}
					}
				};
				$("#docDataMap").ajaxSubmit(options);
				return false;
			}

		}
		
		/**暂存*/
		function tempSubForm(){
			 $(document.body).mask("正在加载，请稍后...");
				var options = {
					url : 'metadata/addDocDataMap.zb',
					dataType : 'json',
					success : function(data) {
						$(document.body).unmask();
						if (data == 'succeed') {
							JDialog.tip("保存成功", 1.5);
						} else {
							JDialog.tip("保存失败", 1.5);
						}
					}
				};
				$("#docDataMap").ajaxSubmit(options);
				return false;
		}

		function checkForm() {
		    var flag =true;
			$("input[id^='ddm_table']").each(function(index, obj){
			   var ddmTableId = $(obj).attr('id');
			   if(ddmTableId.indexOf('_o_') == -1){
			         /**
			   		 var ddmTableVal = $(obj).val();
			   	     var ddmColId = ddmTableId.replace("table","col");
			   	     var ddmColVal = $('#'+ddmColId).val();
			         if(ddmTableVal.length == 0 ||ddmTableId.length == 0){
			   			JDialog.tip("请选择表名",1.5);
			   			flag = false;
			   			$('#'+ddmTableId.replace('table_','table_o_')).focus();
			   			return false;
			   		}
			   		if(ddmColVal.length == 0 || ddmColId.length == 0){
			   			JDialog.tip("请选择列名",1.5);
			   			flag = false;
			   			$('#'+ddmColId.replace('col_','col_o_')).focus();
			   			return false;
			   		}
			   		**/
			   }
			});
			if(flag){
				$("input[id^='ddmr_table']").each(function(index, obj) {
					var ddmrTableId = $(obj).attr('id');
					if(ddmrTableId.indexOf('_o_') == -1){
					    /**
						var ddmrTableVal = $(obj).val();
						var ddmrColId = ddmrTableId.replace("table", "col");
						var ddmrColVal = $('#' + ddmrColId).val();
						var ddmrFiId = ddmrTableId.replace("table", "fi");
						var ddmrFiIdVal = $('#' + ddmrFiId).val();
						if (ddmrFiIdVal.length == 0) {
							JDialog.tip("请选择节点逻辑上级",1.5);
							flag = false;
							$('#'+ddmrFiId).focus();
							return false;
						}
						if (ddmrTableVal.length == 0) {
							JDialog.tip("请选择外键映射表名",1.5);
							flag = false;
							$('#'+ddmrTableId.replace('table_','table_o_')).focus();
							return false;
						}
						if (ddmrColVal.length == 0) {
							JDialog.tip("请选择外键映射列名",1.5);
							flag = false;
							$('#'+ddmrColId.replace('col_','col_o_')).focus();
							return false;
						}
						**/
					}
				  });
			}
			return flag;
		}

        //返回文档管理页面
        function backToDoc(){
            window.location.href = projectPath + "/metadata/docvIndex.zb";
        }

		/***** init document ***/
		$(document).ready(function() {
			init();
		});
	</script>
</body>
</html>
