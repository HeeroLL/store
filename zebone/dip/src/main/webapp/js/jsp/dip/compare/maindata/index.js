var gobal_tableName = "";
var nodes = [{name:'医疗机构信息',typeId:1,code:'WJD01.001',tableName:'BIZ_MEDICAL_ORGAN_M',pId:'1'},
			 {name:'医疗机构人员信息',typeId:2,code:'WJD01.003',tableName:'BIZ_MEDICAL_ORGAN_STAFF_M',pId:'1'},
			 {name:'行政区划信息',typeId:3,code:'WJD01.004',tableName:'BIZ_ADMINISTRATIVE_DIVISION_M',pId:'1'},
			 {name:'诊疗项目信息',typeId:4,code:'WJD01.006',tableName:'BIZ_DIAGNOSE_TREATMENT_ITEM_M',pId:'1'},
			 {name:'药品信息',typeId:5,code:'WJD01.008',tableName:'BIZ_DRUG_INFO_M',pId:'1'}];
var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
	 $("#aDwnLoadLink").hide();
	 $("#resultDiv").html("");
	 $("#uploadInput").val('');
	 changeTitle(treeNode.name+'导入', treeNode.typeId, treeNode.tableName)
};
var setting = {
    data: {
    	key: {
			name: "name"
		},
		simpleData: {
			enable: true,
			idKey:'code',
			pIdKey:'pId'
			
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};


$(function() {
    var maindatatree = $.fn.zTree.init($("#maintypeInfo"), setting, nodes);
    
    $("#aDwnLoadLink").hide();
});

function notEndsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) == -1;
}

function getFrameByName(name) {
	  for (var i = 0; i < frames.length; i++)
	    if (frames[i].name == name)
	      return frames[i];
	 
	  return null;
	}

var fileType=0;
function changeTitle(title, type, tableName){
	$("#span_title").text(title);
	fileType=type;
	$("#fileType").val(fileType);
	$("#tableName").val(tableName);
	//$("#aDwnLoadLink").show();
	//$("#file2").prop('disabled', false);
	//$("#btn_submit").prop('disable', false);
	$("#resultDiv").html("");
}

/* Same as the one defined in OpenJS */
function uploadDone(name) {
	try{
		var frame = getFrameByName(name);
		   if (frame) {
		     var response = $('#fileIframe').contents().find('body').text();;
		     /* If we got JSON, try to inspect it and display the result */
		     if (response.length) {
		       /* Convert from JSON to Javascript object */
		       var json = eval("("+response+")");
		       
		       if(response==""){
		     	  return;
		       }
		       var dataObj   = $.parseJSON(response);
		       if(dataObj.fileNameWrong=='true'){
		     		alert("导入文件类型与所选类型不匹配， 请检查上传文件后重新上传！");
		     		return;
		     	}
		     	if(dataObj.success=='false'){
		     		alert("系统在导入过程中出错, 请检查上传文件后重新上传！");
		     		return;
		     	}
		     	if(dataObj.failCount==0){
		     		$("#aDwnLoadLink").hide();
		     		//$("#file2").prop('disabled', true);
		     		//$("#btn_submit").prop('disable', true);
		     		$("#resultDiv").html("导入成功!");
		     		$("#resultDiv").show();
		     	}else{
		     		$("#aDwnLoadLink").show();
		     		$("#resultDiv").hide();
		     	}
		     	
		     	$("#checkSuccessCount").text(dataObj.successCount);
		     	$("#checkFailCount").text(dataObj.failCount);
		     	
		     	/*if(dataObj.failCount==0){
		     		$("#dbSuccessCount").text(dataObj.successCount);
		     		$("#dbFailedCount").text(dataObj.correctCount-dataObj.successCount);
		     	}else{
		     		$("#dbSuccessCount").text("--");
		     		$("#dbFailedCount").text("--");
		     	}*/
		     	//$("#aDwnLoadLink").attr("href", "dipCompare/errorExcelFile/"+dataObj.filePath+".xlsx");
		     	$("#aDwnLoadLink").click(function(){
		     		$("#aDwnLoadLink").attr("href", "compare/maindata/fileUpload.zb?name="+dataObj.filePath);
		     		//$.post("compare/maindata/fileUpload.zb",{name:dataObj.filePath});
		     	});
		     	//"compare/maindata/fileUpload.zb?name="+dataObj.filePath;
		     }
		  }
	}catch (err){
		
	}
   
}

function uploadFormData() {
	if(fileType==0){
		alert("请选择左侧类型！");
		return;
	}
	if($("#uploadInput").val()==""){
		alert("请选择文件！");
		return;
	}
	var flag = confirm("确定上传该文件？");
	if(!flag){
		return ;
	}
	var sFileName = $("#uploadInput").val();
	if(notEndsWith(sFileName, ".xlsx")){
		alert("请上传Excel文件!");
		return;
	}
	$("#submitId").trigger( "click" );
}