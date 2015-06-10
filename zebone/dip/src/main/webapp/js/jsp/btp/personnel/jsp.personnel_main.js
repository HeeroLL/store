var zTreeOnClick,grid,queryCondition,deleteRelation,editData,formDialog,saveInfo,showResponse,onblurEdit,check,moveOption,moveAllOption;
var editHTML='';
$(function(){
/*****************查询grid***START*******************************/
var col=[  {text: '医疗工作人员ID',	width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'personnelId'		},
	   	    {text: '机构内编号',		width: 90,	textAlign: 'center',	align : 'left', 	dataIndex: 'deptPersonnelCode'	},
	   	    {text: '姓名',			width: 110,	textAlign: 'center',	align : 'left',		dataIndex: 'fullname'			},
	   	    {text: '职称',			width: 70,	textAlign: 'center',	align : 'left',		dataIndex: 'jobTitle'			,dictType:'zhicheng' },
	   	    {text: '关联机构',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'mhoName'			},
	   	    {text: '人员类别',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'personnelType'		,dictType:'rylx' },
	   	    {text: '账号启用/停用装态',width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'isAccountEnable',
	   	     renderer: function(value){
	   	     			  if(value=='1'){
	   	     			  	 return "启用";
	   	     			  }else if((value=='0')){
	   	     			  	 return "停用";
	   	     			  }else{
	   	     			  	 return "";
	   	     			  }
 					   
 				}
 			} ];

grid = new JGrid({
	title: '模块列表',
	col	 :col,
	dataCol:'',
	checkbox : true,
	striped:true,
	height   :document.body.clientHeight-135,
	dataUrl  : 'btp/personnel/personnelRightPage.zb',
	render   : 'grid',
	pageBar  : true,
	crudOpera: true,
	countEveryPage: 20,
	formToggleId:'eoc',//切换表单显示的id
	listeners: {
		dblclick: function(row){//Grid双击事件
			editData("修 改", row['personnelId']);
		},
		curdButtonClick: function(btn){
			if(btn=='add'){
				editData("新 建");
			}else if(btn=='update'){ 					
				var rows = grid.getSelections();
				if(rows.length!=1){
					JDialog.tip('请选择一条记录进行修改');
					return;
				}
				editData("修 改", rows[0]['personnelId']);
			}else if(btn=='remove'){ //删除操作
				var rows = grid.getSelections();
				if(rows.length==0){
					JDialog.tip('请至少选择一条记录进行删除');
					return;
				}
				grid.opt.removeUrl = "btp/personnel/personnelDeletePersonnelInfo.zb";
				grid.opt.crudID = "personnelId";
				grid.removeData();
			}else if(btn=='refresh'){
				grid.loadData();
			}
		}
	}
});

/************************查询grid***END*******************************/

/****************************查询页面涉及到的方法***START*******************************/
// 启用/停用 按钮 --start--
grid.addButton({
	text : '启用/停用',
	handler : function() {
		var rows = grid.getSelections();
		if(rows.length==0){
			JDialog.tip('请至少选择一条记录!');
			return;
		}
		var id = '';
		var isAccountEnable = '';
		for(var i=0; i<rows.length; i++){
			id += rows[i]['personnelId'] + ',';
			if(rows[i]['isAccountEnable']=='启用'){
				isAccountEnable += '1,';
			}else if(rows[i]['isAccountEnable']=='停用'){
				isAccountEnable += '0,';
			}
		}
		id = id.substring(0, id.length-1);
		isAccountEnable = isAccountEnable.substring(0, isAccountEnable.length-1);
		var datas = "personnelIds="+ id + "&isAccountEnables="+isAccountEnable;
		$.ajax({
			url : 'btp/personnel/isAccountEnableModify.zb',
			type : 'POST',
			cache : false,
			data : encodeURI(datas),
			dataType : 'json',
			success : function(res) {
				grid.loadData();
				JDialog.tip(res.msg);
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
			}
		});
	}
});
// 初始化密码 按钮 --start--
grid.addButton({
	text : '初始化密码',
	handler : function() {
		var rows = grid.getSelections();
		if(rows.length==0){
			JDialog.tip('请至少选择一条记录!');
			return;
		}
		var id = '';
		for(var i=0; i<rows.length; i++){
			id += rows[i]['personnelId'] + ',';
		}
		id = id.substring(0, id.length-1);
		$.ajax({
			url : 'btp/personnel/passwordInitialization.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("personnelIds="+ id),
			dataType : 'json',
			success : function(res) {
				JDialog.tip(res.msg);
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
			}
		});
	}
});
//查询条件的拼装
queryCondition = function(){
	var datas="{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	$("#query").find("select").each(function(){
		if($.trim($(this).val())!=''&&$.trim($(this).val())!='-1'){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
}

//单击左侧树节点，加载列表数据
zTreeOnClick = function(mhoId,mhoName){
	$("#query").find("input[name='mhoId']").val(mhoId);
	$("#query").find("input[name='mhoName']").val(mhoName);
	grid.setParams({
		mhoId: mhoId
	});
	if(editHTML!=''){
		formDialog.closeDialog();
	}
	grid.loadData();
}

//查询单击事件
$("#querybtn").click(function(){
	var value = $("#query").find("input[name='mhoId']").val();
	//首先查看页面隐藏框中是否存在机构id
	if(value==''){
		//如果不存在查看左侧树是否选择，已选择就给隐藏框赋值，否则提示
		value = parent.innerlLeftFrame.getTreeCheckedVaule();
		if(value==''){
			JDialog.tip('请在左侧选择医疗机构!');
			return;
		}else{
			$("#query").find("input[name='mhoId']").val(value);
			var name = parent.innerlLeftFrame.getTreeCheckedName();
			$("#query").find("input[name='mhoName']").val(name);
		}
	}
	var datas=queryCondition();
	grid.setParams(eval('('+datas+')'));
	grid.loadData();
});
/************************************查询页面涉及到的方法***END*******************************/

/**************************************新建修改页面***START************************************/
//新建修改
editData=function(title, id){
/****************弹出JDialog框********START*******************/
	formDialog=new JDialog({
		title : title,
		width : 700,
		height :500,
		buttons: [{
 			text: '保存',
 			id: 'personnel_save',	 			
 			handler: function(){
				saveInfo();
 			}
 		},{
 			text: '关闭',
 			id: 'personnel_close',
 			handler: function(){
 				formDialog.closeDialog();
 			}
 		}]
	});
	formDialog.show();
 	if(editHTML==''){
 		editHTML = $('#edit').html();
 		$('#edit').remove();
 	}
 	formDialog.add(editHTML);
 	bindCheckEvent();	
/****************弹出JDialog框********END*******************/

/****************新建、修改页面中的各种方法*****START**********************/
 	//关联机构表格添加行
	$("#addRelation").click(function(){
		var content = $("#tempTemplate").html();
		$("#relation").append(content);
	});
	
	//关联机构表格删除行
	deleteRelation = function(obj){
		var orgId = $(obj).parent().parent().parent().find("input[name='mhoId']").val();
		//删除可分配和已分配的角色
		if(orgId!=''){
			$("#left").find("option").each(function(){
				var value = $(this).val();
				if(value.indexOf(orgId)>=0){
					$(this).remove();
				}
			});
			$("#right").find("option").each(function(){
				var value = $(this).val();
				if(value.indexOf(orgId)>=0){
					$(this).remove();
				}
			});
		}
		//删除关联机构
		$(obj).parent().parent().parent().remove();
	}
	
	//保存按钮失效
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	//保存提交前进行校验
	check = function(){
		if(!checkAll('fromEdit')){
			setBtnDisabled("personnel_save",false);
			setBtnDisabled("personnel_close",false);
	 		formDialog.unmask();
			return false;
	 	}
	 	var mhoIds = '';
	 	var i = 0;
	 	$("#relation").find("tr").each(function(){
	 		if($(this).find("input[name='mhoId']").val()){
	 			mhoIds += $(this).find("input[name='mhoId']").val()+",";
	 		}
	 	});
	 	if(mhoIds.length>1) mhoIds = mhoIds.substring(0, mhoIds.length-1);
	 	
	 	if(mhoIds==''){
	 		JDialog.tip('请至少选择一个关联的医疗机构！');
	 		setBtnDisabled("personnel_save",false);
			setBtnDisabled("personnel_close",false);
	 		formDialog.unmask();
			return false;
	 	}
	 	var a = mhoIds.split(',');
	 	var bool = false;
	 	for(var i = 0;i<a.length;i++){
	 		for(var j = 0;j<a.length;j++){
	 			if(i!=j){
	 				if(a[i]==a[j]){
	 					bool = true;
	 				}
	 			}
	 		}
	 	}
	 	if(bool){
	 		JDialog.tip('有重复的关联机构，请重新选择！');
	 		setBtnDisabled("personnel_save",false);
			setBtnDisabled("personnel_close",false);
	 		formDialog.unmask();
			return false;
	 	}
	}
	
	//保存成功后返回的数据
	showResponse = function(data){
		setBtnDisabled("personnel_save",false);
		setBtnDisabled("personnel_close",false);
		if(data.bool){
			$('#personnelId').val(data.personnelId);
			JDialog.tip('保存成功！');
			formDialog.unmask();
			grid.loadData();	
			formDialog.closeDialog();
		}else{
			JDialog.tip('保存失败！');
			formDialog.unmask();
			grid.loadData();
		}
	}
	
	//保存方法
	saveInfo = function(){
		formDialog.mask('正在保存数据，请稍候...');
		setBtnDisabled("personnel_save",true);
		setBtnDisabled("personnel_close",true);
		var data = '';
		$("#right").find("option").each(function(){
			data +=$(this).val()+',';
		});
		if(data.length>1) data = data.substring(0, data.length-1);
		$('#haveRole').val(data);
		var options = { 
			beforeSubmit:  check,
	        success:   showResponse,
	        url:       "btp/personnel/personnelEditViewSave.zb"
	    };
		$("#fromEdit").ajaxSubmit(options); 
	}
	
	//账号校验，失去焦点事件
	onblurEdit = function(obj){
		var value= $(obj).val();
		var personnelId = $("#personnelId").val();
		if(value&&value!=""){
			$.ajax({
				url : 'btp/personnel/checkLoginName.zb',
				type : 'POST',
				cache : false,
				data : encodeURI("loginName="+value+"&personnelId="+personnelId),
				dataType : 'json',
				success : function(res) {
					if(res.bool){
						JDialog.tip(res.msg);
					}
				},
				error : function(XMLHttpRequest, textStatus,errorThrown) {
					JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
				}
			});
		}
	}
	//角色分配，左右两边进行单个移动
	moveOption = function(id1, id2){
		var e1 = document.getElementById(id1);
		var e2 = document.getElementById(id2);
		for(var i=0;i<e1.options.length;i++){
			if(e1.options[i].selected){
				var e = e1.options[i];
				e2.options.add(new Option(e.text, e.value));
				e1.remove(i);
				i=i-1
			}
		}
	}
	//角色分配，左右两边进行全部移动
	moveAllOption = function(id1, id2){
		var e1 = document.getElementById(id1);
		var e2 = document.getElementById(id2);
		for(var i=0;i<e1.options.length;i++){
			var e = e1.options[i];
			e2.options.add(new Option(e.text, e.value));
			e1.remove(i);
			i=i-1
		}
	}
/****************新建、修改页面中的各种方法*****END**********************/
	
/****************修改页面是加载数据****START***********************/
	//如果存在id存在说明是修改，需要加载数据
	if(id){
		formDialog.mask('正在加载数据，请稍候...');
	 		$.ajax({
				url: 'btp/personnel/personnelEditViewLoad.zb',
				type: 'POST',
				cache: false,
				data: encodeURI("personnelId="+id),
				dataType: 'json',
				success: function(res){
						var personnel = res.personnel;
						//基本信息和账号信息的加载
						formDialog.getComponent().find("input[type='hidden']").each(function(){
	 						var name = $(this).attr('name');
	 						if(name){
	 							$(this).val(personnel[name]);
	 						}
	 					});
	 					formDialog.getComponent().find("input[type='password']").each(function(){
	 						var name = $(this).attr('name');
	 						if(name){
	 							$(this).val(personnel[name]);
	 						}
	 					});
	 					formDialog.getComponent().find("input[type='text']").each(function(){
	 						var name = $(this).attr('name');
	 						if(name){
	 							if(name=='birthday'||name=='enableDate'||name=='disableDate'){
	 								var val = personnel[name];
	 								if(val!=null&&val!=''){
	 									val = val.substring(0,10);
	 								}
	 								$(this).val(val);
	 							}else{
	 								$(this).val(personnel[name]);
	 							}
	 						}
	 					});
	 					formDialog.getComponent().find("select").each(function(){
	 						var name = $(this).attr('name');
	 						if(name){
	 							$(this).val(personnel[name]);
	 						}
	 					});
	 					formDialog.getComponent().find("input[type='radio']").each(function(){
	 						var name = $(this).attr('name');
	 						if(name){
	 							if($(this).val()==personnel[name]){
	 								$(this).attr("checked",true);
	 							};
	 						}
	 					});
	 					//关联机构的加载
	 					var mhoRelations = res.personnelMhoRs;
	 					for(var i = 0; i<mhoRelations.length;i++){
		 					var content = $("#tempTemplate").html();
							$("#relation").append(content);
	 					}
	 					var len = -1;
	 					$("#relation").find("tr").each(function(){
	 						$(this).find("select").each(function(){
		 						var name = $(this).attr('name');
		 						if(name){
		 							$(this).val(mhoRelations[len][name]);
		 						}
		 					});
		 					$(this).find("input").each(function(){
		 						var name = $(this).attr('name');
		 						if(name){
		 							$(this).val(mhoRelations[len][name]);
		 						}
		 					});
		 					len++;
	 					});
	 					//角色的加载
	 					var roles = res.roles;
	 					var otherRoles = res.otherRoles;
	 					//已分配的角色
	 					if(roles&&roles.length>0){
	 						for(var n=0;n<roles.length;n++){
	 							$("#right").append("<option value="+roles[n].roleId+" >"+roles[n].name+ "</option>"); 
	 						}
	 					}
	 					//未分配的角色
	 					if(otherRoles&&otherRoles.length>0){
	 						for(var m=0;m<otherRoles.length;m++){
	 							$("#left").append("<option value="+otherRoles[m].roleId+" >"+otherRoles[m].name+ "</option>"); 
	 						}
	 					}
	 					formDialog.unmask();								
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					formDialog.unmask();
					JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
				}
			});
	}else{
		//新建时需要自动把左侧选中的机构加入到关联机构中
		//首先查看页面隐藏框中是否存在机构id
		var mhoId = $("#query").find("input[name='mhoId']").val();
		if(mhoId==''){
			//如果不存在查看左侧树是否选择，已选择就给隐藏框赋值
			mhoId = parent.innerlLeftFrame.getTreeCheckedVaule();
			if(mhoId!=''){
				$("#query").find("input[name='mhoId']").val(mhoId);
				var name = parent.innerlLeftFrame.getTreeCheckedName();
				$("#query").find("input[name='mhoName']").val(name);
			}
		}
		var mhoName = $("#query").find("input[name='mhoName']").val();
		if(mhoId&&mhoId!=''){
			var content = $("#tempTemplate").html();
			$("#relation").append(content);
			$("#relation").find("tr").find("input[name='mhoId']").val(mhoId);
			$("#relation").find("tr").find("input[name='mhoName']").val(mhoName);
		}
		
		//新建时通过医疗机构id，加载机构下的角色
		if(mhoId!=''){
			$.ajax({
				url: 'btp/personnel/getRoles.zb',
				type: 'POST',
				cache: false,
				data: encodeURI("mhoId="+mhoId),
				dataType: 'json',
				success: function(res){
					var roles = res.roles;
					var select = $("#left");
					for(var i=0;i<roles.length;i++){
						select.append("<option value="+roles[i].roleId+" >"+roles[i].name+ "</option>");   
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					JDialog.showMessageDialog('异常', '服务器出现异常，角色加载失败。', JDialog.ERROR);
				}
			});
		}
	}
	
// 机构选择树
orgTreeChoice = function(obj, type) {
	treeDialog = new JDialog({
		id : 'tree',
		title : '请选择机构',
		width : $(document.body).width() * 0.35,
		height : $(document.body).height() * 0.55,
		btns   : JDialog.OK_CANCEL_OPTION,
		listeners : {
			buttonClick : function(btnId, text) {
				if (btnId == "ok") {
					//给关联机构赋值
					var selectedOrg = window.frames["ztree"].getTreeValue();
					$(obj).parent().find('input[name=mhoName]').val(selectedOrg.split(':')[0]);
					$(obj).parent().find('input[name=mhoId]').val(selectedOrg.split(':')[1]);
					//增加可选择的角色
					$.ajax({
						url: 'btp/personnel/getRoles.zb',
						type: 'POST',
						cache: false,
						data: encodeURI("mhoId="+selectedOrg.split(':')[1]),
						dataType: 'json',
						success: function(res){
							var roles = res.roles;
							var select = $("#left");
							for(var i=0;i<roles.length;i++){
								select.append("<option value="+roles[i].roleId+" >"+roles[i].name+ "</option>");   
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							JDialog.showMessageDialog('异常', '服务器出现异常，角色加载失败。', JDialog.ERROR);
						}
					});
				}
				treeDialog.closeDialog();
			}
		}
	});
	treeDialog.show();
	treeDialog.add("<iframe id='ztree' name='ztree' src='btp/mho/mhoInnerTree.zb?type="+type+"' width='100%' height='100%' frameBorder='0' scrolling='auto' />");
};
/************************修改页面是加载数据***END***************************/
}
/*********************************新建修改页面***END*************************************/
});

