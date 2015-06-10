
function getFrameByName(name) {
  for (var i = 0; i < frames.length; i++)
    if (frames[i].name == name)
      return frames[i];
 
  return null;
}
 
function notEndsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) == -1;
}

/* Same as the one defined in OpenJS */
function uploadDone(name) {
	try{
		var frame = getFrameByName(name);
		   if (frame) {
		     var response = $('#fileIframe').contents().find('body').text();
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
		     	
		     	$("#aDwnLoadLink").click(function(){
		     		$("#aDwnLoadLink").attr("href", "compare/maindata/fileUpload.zb?name="+dataObj.filePath);
		     		//$.post("compare/maindata/fileUpload.zb",{name:dataObj.filePath});
		     	});
		     	
		     	//$("#aDwnLoadLink").attr("href", "dipCompare/errorExcelFile/"+dataObj.filePath+".xlsx");
		     }
		  }
	}catch (err){
		
	}
   
}

$(function(){
	$("#aDwnLoadLink").hide();
	$("#resultDiv").hide();
});

function uploadFormData(){
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
 