<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>文档结构管理界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
    html, body {
        background-color: #EEF7FE;
        overflow: hidden;
        height: auto;
    }

    .c_content div {
        height: 500px;
        overflow: auto;
    }

    .dropdown-menu li {
        text-align: center;
    }
</style>
<script type="text/javascript">
<%   
          java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat("yyyyMMdd"); 
          java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat("yyyyMMdd HHmmss"); 
          java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
          String date8 = formatter1.format(currentTime); //将日期时间格式化 
          String date15 = formatter2.format(currentTime);
          date15=date15.replace(" ", "T");
 %> 
var projectPath = '${pageContext.request.contextPath}';
//树形控件配置
var docTree;


var setting = {
    view:{addDiyDom: addDiyDom,
		fontCss : {}
    },
    data: {
        key: {
            name: "name"
        },
        simpleData:{
            enable : true,
            idKey:'id',
            pIdKey:'pid'

        }
    },
    callback: {
    }
};

//为子节点添加input框
function addDiyDom(treeId, treeNode) {
    if(treeNode.isFeild =='1'){
        var sObj = $("#" + treeNode.tId + "_a");
        if (treeNode.editNameFlag || $("input[id*='"+treeNode.id+"'").length>0) return;
         var str=$.ajax({
         		url:'doc/getFieldInfo.zb',
				type: 'GET',
				async: false,
				cache:false,
				data:  {fieldCode:treeNode.fieldCode},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("服务器异常，保存失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
       					 }
         }).responseText;//获取节点数据
        str = jQuery.parseJSON(str).fieldInfo;
        var editStr='';
        var options;
        if(str.fieldType=='1')//日期类型
        {
    	    if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='DT')
      	  {
        	editStr =  '<input type="text" class="Wdate" id="date_'+treeNode.id+'" style="border: 0.5px solid rgb(204, 204, 204);" validate="date|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"") +'" title="出生日期" onclick="WdatePicker({dateFmt:\'yyyyMMddTHHmmss\',skin:\'whyGreen\'});" />';
        	
       		 }
      	 	else 
      	 	{
       	     	editStr =  '<input type="text" class="Wdate" id="date_'+treeNode.id+'" style=" border: 0.5px solid rgb(204, 204, 204);" validate="date|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"") +'" title="出生日期" onclick="WdatePicker({dateFmt:\'yyyyMMdd\',skin:\'whyGreen\'});" />';
       	
       	
       		}
        
        }
        else if(str.fieldType=='2')//字符
        {
     	  editStr = '<input type="text" id="char_'+treeNode.id+'" validate="';
     	  if(str.fieldFormat.split('..')[0]!=str.fieldFormat)
     	  {
     	  
      	 	 if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
      		}
      		else 
      		{
      			if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
       		   else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='D'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='DT')
       		   {
      		      editStr = editStr + 'date|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
      		    }
      		
      		}
        }
        else if(str.fieldType=='3')//数字
        {
       		 if(str.fieldFormat.split('..')[0]!=str.fieldFormat)
     		   {
     		   	if(str.fieldFormat.split(',')[0]==str.fieldFormat)
     		 	  {
         			 editStr = '<input type="text" id="number_'+treeNode.id+'" validate="number|0-'+ str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
        		}
        		else 
        		{
        			editStr = '<input type="text" id="number_'+treeNode.id+'" validate="float|0-'+ (parseInt(str.fieldFormat.split(',')[0].replace(/[^\d]/g ,""))+parseInt(str.fieldFormat.split(',')[1])+1)+'" />';
        		}
        
        
       		 }
       		 
       		 else
       		 {
       		   if(str.fieldFormat.split(',')[0]==str.fieldFormat)
     		 	  {
         			 editStr = '<input type="text" id="number_'+treeNode.id+'" validate="number|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
        		}
        		else 
        		{
        			editStr = '<input type="text" id="number_'+treeNode.id+'" validate="float|'+(parseInt(str.fieldFormat.split(',')[0].replace(/[^\d]/g ,""))+parseInt(str.fieldFormat.split(',')[1])+1)+'-' + (parseInt(str.fieldFormat.split(',')[0].replace(/[^\d]/g ,""))+parseInt(str.fieldFormat.split(',')[1])+1)+'" />';
        		}
       		 }
      	  
        }
        else if(str.fieldType=='4')//字典
        {
        	editStr = '<input type="text" id="directory_'+treeNode.id+'" validate="';
     	  if(str.fieldFormat.split('..')[0]!=str.fieldFormat)
     	  {
     	  
      	 	 if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
      		}
      		else 
      		{
      			if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
       		   else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='D'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='DT')
       		   {
      		      editStr = editStr + 'date|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
      		    }
      		    else if(str.fieldFormat=='T/F')
       		 	  {
      		      editStr = editStr + 'string|1-1" />';
      		    }
      		
      		}
      		options={
				width: 300,
				maxHeight: 200,
				select:false,
				textField:'typeName',
				serviceUrl:'doc/listDirectoryValueByName.zb',
				noCache:true,
				onSelect: function(value){ 
				$("input[id*='"+treeNode.id+"'][type='text']").val(value.dict_name);
				$("input[id*='"+treeNode.id+"'][type='hidden']").val(value.dict_code); 
				},
				params: { dicttypeid: str.fieldValue},
				col:[{dataIndex:'dict_name'},{dataIndex:'dict_code'}],
				valueField:{dataIndex:'dict_id',dataName:'dicttype_id'}
				};
        	
        }
        else if(str.fieldType=='5')//主数据
        {
        
        	editStr = '<input type="text" id="md_'+treeNode.id+'" validate="';
     	  if(str.fieldFormat.split('..')[0]!=str.fieldFormat)
     	  {
     	  
      	 	 if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|0-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
      		}
      		else 
      		{
      			if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='AN'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='A')
      		  {
      			  editStr = editStr + 'string|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
     	 	  }
       		  else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='N')
       		  {
        	    editStr = editStr + 'number|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
       		   }
       		   else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='D'||str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='DT')
       		   {
      		      editStr = editStr + 'date|'+str.fieldFormat.replace(/[^\d]/g ,"")+'-' + str.fieldFormat.replace(/[^\d]/g ,"")+'" />';
      		    }
      		}
      		options={
				width: 300,
				async: false,
				maxHeight: 200,
				select:false,
				textField:'typeName',
				serviceUrl:'doc/listMasterDateByName.zb',
				noCache:true,
				onSelect: function(value){ 
				$("input[id*='"+treeNode.id+"'][type='text']").val(value.mdname);
				$("input[id*='"+treeNode.id+"'][type='hidden']").val(value.mdcode); 
				},
				params: { masterdataname: str.fieldValname},
				col:[{dataIndex:'mdname'},{dataIndex:'mdcode'}]
				};
        }
        //<input type='text' class='Wdate' name='beginDate'  style='width: 120px; border: 1px solid rgb(204, 204, 204);' validate='date|0-20' title='出生日期' onclick='WdatePicker({skin:\'whyGreen\'});' />
        editStr = editStr.substring(0, editStr.length-2);
        
        if(str.fieldType=='1')//添加说明
        {
        	editStr = editStr + 'value="日期 '+str.fieldFormat+'" />';
        }
        else if(str.fieldType=='2')
        {
        	editStr = editStr + 'value="字符 '+str.fieldFormat+'" />';
        }
        else if(str.fieldType=='3')
        {
        	editStr = editStr + 'value="数字 '+str.fieldFormat+'" />';
        }
        else if(str.fieldType=='4')
        {
        	editStr = editStr + 'value="字典 " />';
        	editStr = editStr + '<input type="hidden" id="directory_'+treeNode.id+'_hidden" value=""/>';//字典添加value框
        }
        else if(str.fieldType=='5')
        {
        	editStr = editStr + 'value="主数据 " />';
        	editStr = editStr + '<input type="hidden" id="md_'+treeNode.id+'_hidden" value=""/>';//主数据添加value框
        }
        if(str.fieldName=='文档类型代码')//添加提示
        {
        	editStr = editStr + '<input type="text" id="directory_doctype_tip" style="color:#ff0000;width:200px;" value="请注意，该项与卫生服务活动相关！" onfocus="this.blur();"/>';
        }
        
        //editStr =  '<input type="text" class="Wdate" name="beginDate"  style="width: 120px; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="出生日期" onclick="WdatePicker({skin:\'whyGreen\'});" />';
        //var editStr = "<input type='radio' class='radioBtn' id='radio_" +treeNode.id+"_"+treeNode.pId+"' onfocus='this.blur();'></input>";
        sObj.append(editStr);
        var btnEdit;
        
      	btnEdit = $("input[id*='"+treeNode.id+"'][type='text']");
      	$("#"+treeNode.tId+"_span").css({"width":"100px"});
        btnEdit.css({"color":"#888","height":"90%"});
      	if(str.fieldType=='4')//为字典自动生成随机数据
      	{
      		var directoryData=$.ajax({
         		url:'doc/getDirectoryRandom.zb',
				type: 'GET',
				async: false,
				cache:false,
				data:  {dicttype_id: str.fieldValue},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("服务器异常，保存失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
       					 }
         }).responseText;
      	directoryData = jQuery.parseJSON(directoryData).directoryInfo;
      	btnEdit.val(directoryData.dict_name);
      	btnEdit.next().val(directoryData.dict_code);
      	btnEdit.css({"color":"#000","height":"90%"});
      	}
      	if(str.fieldValname=='行政区划信息')
      	{	
      		var MasterData=$.ajax({
         		url:'doc/listMasterDateByName.zb',
				type: 'GET',
				async: false,
				cache:false,
				data:  { masterdataname: str.fieldValname,query:'五顶山乡'},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("获取卫生服务活动代码失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
       					 }
      	   }).responseText;
      		MasterData = jQuery.parseJSON(MasterData).data;
      		btnEdit.val(MasterData[0].mdname);
      		btnEdit.next().val(MasterData[0].mdcode);
      		btnEdit.css({"color":"#000","height":"90%"});
      	}
      	if(str.fieldName=='文档类型代码'||str.fieldName=="卫生服务活动代码")
        {
       	 if(str.fieldName=="卫生服务活动代码")
      	  {
        	if(docName!='ceshi11_1.1'&&docName!='test_1.0'&&docName!='sample_1.1')
        	{
        	var directoryData=$.ajax({
         		url:'doc/listDirectoryValueByName.zb',
				type: 'GET',
				async: false,
				cache:false,
				data:  { dicttypeid: str.fieldValue,query:docName},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("获取卫生服务活动代码失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
       					 }
         }).responseText;
      	directoryData = jQuery.parseJSON(directoryData).data;
      	btnEdit.val(directoryData[0].dict_name);
      	btnEdit.next().val(directoryData[0].dict_code);
      	btnEdit.css({"color":"#000","height":"90%"});
      			}
      		}
      		else 
      		{
      			var directoryData=$.ajax({
         		url:'doc/listDirectoryValueByDocId.zb',
				type: 'GET',
				async: false,
				cache:false,
				data:  { docid:docId},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("获取文档类型代码失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
       					 }
       			  }).responseText;
       			  directoryData = jQuery.parseJSON(directoryData).data;
     			  btnEdit.val(directoryData.dict_name);
   				  btnEdit.next().val(directoryData.dict_code);
    			  btnEdit.css({"color":"#000","height":"90%"});
    			  docName=directoryData.dict_name;
      		}
        }
      	if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='DT')//为时间类型生成默认值
        {
        	btnEdit.val('<%=date15%>');
        	btnEdit.css({"color":"#000","height":"90%"});
        }
        else if(str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")=='D'&&str.fieldFormat.replace(/[^A-Za-z]+$/ ,"")!='DT')
        {
        	btnEdit.val('<%=date8%>');
        	btnEdit.css({"color":"#000","height":"90%"});
        }
        if(str.fieldType=='5'||str.fieldType=='4')//载入已存在文档
        {
        	btnEdit.autocomplete(options);
        	if(treeNode.displayName!=""&&typeof(treeNode.displayName) != "undefined")
        	{
        		btnEdit.val(treeNode.displayName);
        		btnEdit.next().val(treeNode.code);
        	}
        	
        }
        else
        {
       		 if(treeNode.displayName!=""&&typeof(treeNode.displayName) != "undefined")
        	{
        		btnEdit.val(treeNode.displayName);
        	}
        }
        
       
      //  if(str.fieldType=='4')
    //  	{
    //  		var directoryData=$.ajax({
    //     		url:'doc/getDirectoryRandom.zb',
	//			type: 'GET',
	//			async: false,
	//			cache:false,
	//			data:  {dicttype_id: str.fieldValue},
	//			dataType: 'json',
	//			error: function(XMLHttpRequest, textStatus, errorThrown){
	//			JDialog.tip("服务器异常，保存失败");
	//			$(document.body).unmask();
	//				},
	//			success: function(date){
     //    		 //signUpAlert.successAlert();
    //     		 //alert(date);
       //					 }
    //     }).responseText;
      	//directoryData = jQuery.parseJSON(directoryData).directoryInfo;
    //  	btnEdit.val(directoryData.dict_name);
    //  	btnEdit.css({"color":"#000","height":"90%"});
    //  	btnEdit.next().val(directoryData.dict_code);
    //  	}
        if(treeNode.displayName!=""&&typeof(treeNode.displayName) != "undefined")
        {
        btnEdit.css({"color":"#000","height":"90%"});
        }
        
        if (btnEdit) btnEdit.bind({ //添加聚焦事件
				focus:function(){ 
				if (this.value == this.defaultValue){ 
				this.value=""; 
				$(this).css({"color":"#000"});
				} 
				}, 
				blur:function(){ 
				if (this.value == ""){ 
				this.value = this.defaultValue; 
				 $(this).css({"color":"#888"});
				} 
				} 
			}); 
       
    }
}


//当鼠标离开结点时，隐藏删除菜单按钮
/*function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#editBtn_"+treeNode.tId).unbind().remove();
}*/

//进行相同层级的排序
function zTreeBeforeDrop(treeId, treeNodes,targetNode,moveType){
    //alert(treeNodes[0].name);alert(targetNode.name);
    if(treeNodes[0].pid == targetNode.pid && moveType != "inner"){

    }else{
        return false;
    }
}

var nodes = ${docStruInfo};
var docId = '${docId}';
var docName = '${docName}';
var getInfoByPage = function(){//获取输入信息
    var data = '{"docid":"' + docId +'";"xmlinfo":[';
    $("#doc_struct").find("input[type='text']").each(function(){
        var id = $(this).attr("id");
        var ids = id.split('_');
        if($(this).val().split(' ')[0]=='日期'||$(this).val().split(' ')[0]=='字符'||$(this).val().split(' ')[0]=='数字'
        ||$(this).val().split(' ')[0]=='字典'||$(this).val().split(' ')[0]=='主数据')
        {
        	data='0';
       		return false;
        }
        if(ids[0]=='md'||ids[0]=='directory'&&ids[2]!='tip')
        {
        	data += '{"' + id + '":"' + $(this).val() +'","';
        	data +=  id + '_hidden":"' + $('#'+id+'_hidden').val() +'"} ';
        
        }
        else if(ids[2]!='tip')
        {
        	data += '{"' + id + '":"' + $(this).val() +'"} ';
        }
    });
    if(data.length>1){
        data = data.substring(0,data.length-1);
    }
    else return data;
    data +="]}";
    return data;
};
$(document).ready(function () {
        //加载文档树
    docTree = $.fn.zTree.init($("#doc_struct"), setting, nodes);
    docTree.expandAll(true);
    $("#saveXmlToLocal").click(function(){//保存文档信息
		var allNodes = docTree.transformToArray(docTree.getNodes());
		var tempStr = getInfoByPage();
		if(tempStr=='0')
		{
		JDialog.tip("存在空白项");
		$(document.body).unmask();
		}
		else
		{
		$.ajax({
         		url:'doc/saveXmlInfo.zb',
				type: 'POST',
				async: false,
				cache:false,
				data:  {json: tempStr},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip("服务器异常，保存失败");
				$(document.body).unmask();
					},
				success: function(date){
         		 //signUpAlert.successAlert();
         		 //alert(date);
         		 if(date.success=='true')
         		 {
         		 JDialog.tip("保存成功，请至下载管理页面下载！");
         		 }
         		 else
         		 {
         		  alert(date.message);
         		 }
       					 }
         })
         }
		//window.location.href ='doc/saveXmlInfo.zb?json='+tempStr;
//		$.ajax({
 //       url:'doc/saveXmlInfo.zb?json='+tempStr,
  //      type: 'GET'
  //  });
	});

});

</script>
</head>
<body>
<div class="container" style="width:90%;margin-left: 10px;">
    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">文档结构</span>
		</span>
	</span>
    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e" style="text-align:center;">
            <div class="c_content"	>
                <div class="ztree" id="doc_struct"><input type="hidden" id="docId" name="docId" value=""/></div>
            </div>
            <div style="height: 30px;margin: 5px 0px;text-align: center;">
			<a class="btn" href="javascript:void(0);" id="saveXmlToLocal">
		 	   <span class="btn-left">
		    	    <span class="btn-text icon-save" >保存文档到本地</span>
		   		</span>
			</a>
		<!-- 
		<a class="btn" href="javascript:void(0);" id="backToDoc">
		    <span class="btn-left">
		        <span class="btn-text icon-back" >返回</span>
		    </span>
		</a>
		 -->
	</div>
        </div>
    </div>
    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>