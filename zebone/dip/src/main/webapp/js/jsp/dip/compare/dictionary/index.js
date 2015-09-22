function notEndsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) == -1;
}
function uploadFormData() {
	if($("#file2").val()==""){
		alert("请选择文件！");
		return;
	}
	var flag = confirm("确定上传该文件？");
	if(!flag){
		return ;
	}
	$('#result').html('');
	var oMyForm = new FormData();
	oMyForm.append("file", file2.files[0]);
	oMyForm.append("fileType", 6);
	var sFileName = file2.files[0].name;
	if(notEndsWith(sFileName, ".xlsx")){
		alert("请上传Excel文件!");
		return;
	}
	$.ajax({
		url : 'compare/maindata/uploadFile.zb',
		data : oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		type : 'POST',
		success : function(data) {
			var dataObj = JSON.parse(data);
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
			}
			
			$("#checkSuccessCount").text(dataObj.correctCount);
			$("#checkFailCount").text(dataObj.failCount);
			
			/*if(dataObj.failCount==0){
				$("#dbSuccessCount").text(dataObj.successCount);
				$("#dbFailedCount").text(dataObj.correctCount-dataObj.successCount);
			}else{
				$("#dbSuccessCount").text("--");
				$("#dbFailedCount").text("--");
			}*/
			$("#aDwnLoadLink").attr("href", "dipCompare/errorExcelFile/"+dataObj.filePath+".xlsx");
			
		}
	});
}