var id,name,formDialog;
var loadMho=function(mId,mName,type,mhoId){
	formDialog = new JDialog({
			title: "医疗机构信息",
//			width: 400,
//			height: 500,
			height: $(document.body).height()*0.8,
			width:  $(document.body).width()*0.8,
     		icon: 'images/jkda_tiny.png',
	 		maximizable:true,
	 		buttons: [{
	 			text: '确定',
	 			id: 'save_user',
	 			handler: function(){
	 				var result;
	 				var strs= new Array(); //定义一数组
	 				var  id='';//用于存放分割后的 医疗机构ID
	 				var  name='';//用来存放 机构名称
	 				//判断是不是IE浏览器
	 				if($.browser.msie){
	 					result = document.frames["ztree"].document.getElementById("tree_value").value;
	 					//alert(result);
	 					strs=result.split(":"); //字符分割 
	 					for (i=0;i<strs.length ;i++){
//	 					alert(i);
//	 						alert(strs[i]);
	 						if(i%2==0){
	 							name += strs[i] +"--"; 
	 						} else{
	 							id += strs[i] +"-";
	 						}  
					    } 
					    name = name.substring(0,name.length-2);
					    id = id.substring(0,id.length-1);
					   if($('#'+mId).lengh>0){
 				 		$('#'+mId).val(id);
 				 	   }else{
 				 		$('input[name='+mId+']').val(id);
 				       }
 				        if($('#'+mName).lengh>0){
 				 		$('#'+mName).val(name);
 				 		$('#'+mName).focus();
 				 		$('#'+mName).attr("readonly",true);
 				 	}else{
 				 		$('input[name='+mName+']').val(name);
 				 		$('input[name='+mName+']').focus();
 				 		$('input[name='+mName+']').attr("readonly",true);
 				 	}
//					    alert(name);
//					    alert(id);
					    formDialog.closeDialog();
	 				}else{
	 					result = document.getElementById("ztree").contentDocument.getElementById("tree_value").value;
 						//alert(result);
 						strs=result.split(":"); //字符分割 
 						for (i=0;i<strs.length ;i++){
 //						alert(strs[i]);
	 						if(i%2==0){
	 							name += strs[i] +"--"; 
	 						} else{
	 							id += strs[i] +"-";
	 						}  
					    } 
					    name = name.substring(0,name.length-2);
					    id = id.substring(0,id.length-1);
					    if($('#'+mId).lengh>0){
 				 		$('#'+mId).val(id);
 				 	   }else{
 				 		$('input[name='+mId+']').val(id);
 				       }
 				        if($('#'+mName).lengh>0){
 				 		$('#'+mName).val(name);
 				 		$('#'+mName).focus();
 				 		$('#'+mName).attr("readonly",true);
	 				 	}else{
	 				 		$('input[name='+mName+']').val(name);
	 				 		$('input[name='+mName+']').focus();
	 				 		$('input[name='+mName+']').attr("readonly",true);
	 				 	}
//					    alert(name);
//					    alert(id);
					    formDialog.closeDialog();
	 				}
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'save_close',
	 			handler: function(){
	 				JDialog.showConfirmDialog("警告", "您确定要关闭吗？", JDialog.WARNING, function(btn){
	 					if(btn=='yes'){
 		 					formDialog.closeDialog();
 						}else{
 							
 						}
	 				});
	 			}
	 	    }]
		});
		formDialog.show();
		formDialog.add("<iframe id='ztree' name='ztree' src='btp/mho/mhoInnerTree.zb?type="+type+"&mhoId="+mhoId+"' width='100%' height='100%' frameBorder='0' scrolling='auto' />"); 
};