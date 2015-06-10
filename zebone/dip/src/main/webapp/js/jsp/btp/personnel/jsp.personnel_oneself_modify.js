//保存提交前进行校验
var check = function(){
	if(!checkAll('formModify')){
		return false;
 	}
}

//保存成功后消息显示
var showResponse = function(data){
	if(data.bool){
		JDialog.tip('保存成功！');	
	}else{
		JDialog.tip('保存失败！');
	}
}

//信息保存提交
 var doSubmit = function(){
	var options = { 
        success:       showResponse,  // post-submit callback 
		//target:        '#output2',   // target element(s) to be updated with server response
		beforeSubmit:  check  // pre-submit callback 
        // other available options: 
        //url:       url         // 不设定默认使用 form 的action 值 
        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
        //clearForm: true        // clear all form fields after successful submit 
        //resetForm: true        // reset the form after successful submit 
        // $.ajax options can be used here too, for example: 
        //timeout:   3000 
    };
	$("#formModify").ajaxSubmit(options); 
}