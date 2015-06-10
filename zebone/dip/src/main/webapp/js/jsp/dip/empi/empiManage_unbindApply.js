var field, col, grid, formDialog, showResponse,saveInfo,setBtnDisabled;
var empi,auditOperate;
var editHTML='';
var idkey;

var queryCondition = function () {
    var datas = "{";
    $("#query").find("input").each(function () {
        if ($.trim($(this).val()) != '') {
            datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
        }
    });
    $("#query").find("select").each(function () {
        if ($.trim($(this).val()) != '' && $.trim($(this).val()) != '-1') {
            datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
        }
    });
    if (datas.length > 1) datas = datas.substring(0, datas.length - 1);
    datas += "}";
    return datas;
};

var unbindAudit = function(id,empi){
    formDialog = new JDialog({
        title : "EMPI解绑审核",
        width : 720,
        height :330,
        buttons: [
            {
                text: '允许',
                id: 'audit_permit',
                handler: function () {
                    auditOperate('允许');
                }
            },{
                text: '拒绝',
                id: 'audit_refuse',
                handler: function(){
                    auditOperate('拒绝');
                }
            }]
    });
    formDialog.show();
    formDialog.add("<iframe id='unbindAudit' name='unbindAudit' src='empiManage/unbindAuditDetails.zb?empi="+empi+"&id="+id+"' width='100%' height='100%' frameBorder='0'  />");
};


$(document).ready(function() {
    //加载布局控件
    $('body').layout({

    });
    $('#listshow').layout({
        east: {
            size: 470
        }
    });

    var col = [
        {text: '申请机构', width: 180, textAlign: 'center', align: 'center', dataIndex: 'orgName' },
        {text: '卡类型', width: 80, textAlign: 'center', align: 'left', dataIndex: 'secCardType'},
        {text: '卡号', width: 130, textAlign: 'center', align: 'left', dataIndex: 'secCardNo'},
        {text: '姓名', width: 80, textAlign: 'center', align: 'left', dataIndex: 'name'},
        {text: '证件类型', width: 80, textAlign: 'center', align: 'left', dataIndex: 'firstCardType'},
        {text: '证件号', width: 150, textAlign: 'center', align: 'left', dataIndex: 'firstCardNo'},
        {text: '状态', width: 100, textAlign: 'center', align: 'left', dataIndex: 'auditStatus',
            renderer: function (value) {
                if (value == '1') {
                    return "待审核";
                } else if (value == '2') {
                    return "审核通过";
                } else if (value == "3") {
                    return "审核未通过";
                }
            }},
        { text: '操作',
            width:90,
            textAlign: 'center',
            align : 'center',
            formatter: function (rowData) {
                if (rowData['auditStatus'] == '1') {
                    return '<a href="javascript:unbindAudit(\''+rowData['id']+"\',\'"+rowData['empi']+'\')">审核</a>';
                }else{
                    return "";
                }
            }
        }
    ];

    //查询事件
    $("#querybtn").click(function () {
        //查询的条件
        var conditions = queryCondition();
        grid.setParams(eval('(' + conditions + ')'));
        grid.loadData(1);
    });

    $("#query input").bind('keypress', function (e) {
        if (e.keyCode == 13) {
            var conditions = queryCondition();
            grid.setParams(eval('(' + conditions + ')'));
            grid.loadData(1);
            e.preventDefault();
            e.stopPropagation();
        }
    });

    $("#orgName").autocomplete({//获取申请机构信息
        width: 350,
        select: false,
        noCache: true,
        textField: 'name',
        serviceUrl: 'log/viewOrgInfo.zb',
        col: [
            {dataIndex: 'name'},
            {dataIndex: 'code'}
        ],
        valueField: {dataIndex: 'code', dataName: 'orgCode'},//隐藏文本的name
        paging: {pageSize: 10 }//每页显示条数
    });

    grid = new JGrid({
        title: '申请列表',
        col	     : col,
        selectModel:1,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'empiManage/unbindApplyList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            curdButtonClick: function(btn){
                if(btn=='add'){
                	addUnbindApply("empi解绑申请");
                }else if(btn=='update'){
                    var rows = grid.getSelections();
                    if(rows.length!=1){
                        JDialog.tip('请选择一条记录进行修改');
                        return;
                    }
                    editData("修 改", rows[0]['id']);
                }else if(btn=='remove'){
                    var rows = grid.getSelections();
                    if(rows.length==0){
                        JDialog.tip('请选择记录进行删除');
                        return;
                    }
                    removeData();
                }else if(btn=='refresh'){
                    grid.loadData();
                }
            }
        }
    });
    grid.loadData(1);
    /**
    $("#grid").find("button[name='add']").css("display","none");
    **/
    $("#grid").find("button[name='update']").css("display","none");
    $("#grid").find("button[name='remove']").css("display","none");
    $("#grid").find("button[name='refresh']").css("display","none");

    setBtnDisabled = function(btnId, bool){
        $("#"+btnId).attr("disabled", bool);
    };

    $(window).resize(function(){
        grid.setGridHeight($(document.body).height()-112);
    });

    auditOperate = function(operate){
        var target = $("#unbindAudit").contents().find("#auditForm");
        var oldCardNo = target.find("input[name='oldCardNo']").val();
        var newCardNo = target.find("input[name='firstCardNo']").val();
        var oldCardType = target.find("input[name='oldCardType']").val();
        var newCardType = target.find("input[name='firstCardType']").val();
        var secCardType = target.find("input[name='secCardType']").val();
        var secCardNo = target.find("input[name='secCardNo']").val();

        var url =  "empiManage/refuseUnbindApply.zb";
        var alertMsg = '是否拒绝'+secCardType+secCardNo+'解除与'+oldCardType+'号'+oldCardNo+'的绑定？';
        if(operate == '允许'){
            url = "empiManage/permitUnbindApply.zb"
            //alertMsg = '是否允许'+secCardType+secCardNo+'解除与'+oldCardType+'号'+oldCardNo+'的绑定,重新绑定的'+newCardType+'号为'+newCardNo+'？';
            alertMsg = '是否允许'+secCardType+secCardNo+'解除与'+oldCardType+'号'+oldCardNo+'的绑定？';
        }
        JDialog.showConfirmDialog(operate+'EMPI解绑确认', alertMsg, JDialog.WARNING, function (id, text) {
            if (id == 'yes') {
                formDialog.mask('正在解绑，请稍候...');
                setBtnDisabled('audit_permit', false);
                setBtnDisabled('audit_refuse', false);
                var data = "{";
                target.find("input[type='text']").each(function () {
                    data += $(this).attr("name") + ":'" + $(this).val() + "',";
                });
                target.find("input[type='hidden']").each(function () {
                    data += $(this).attr("name") + ":'" + $(this).val() + "',";
                });
                if (data.length > 1) {
                    data = data.substring(0, data.length - 1);
                }
                data += "}";
                $.ajax({
                    url: url,
                    type: 'POST',
                    cache: false,
                    data: eval('(' + data + ')'),
                    dataType: 'json',
                    success: function (res) {
                        if (res.success) {
                            JDialog.tip(res.msg, JDialog.INFORMATION);
                            formDialog.closeDialog();
                            grid.loadData();
                        } else {
                            JDialog.tip(res.msg, JDialog.ERROR);
                        }
                        formDialog.unmask();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        formDialog.unmask();
                        setBtnDisabled('audit_permit', false);
                        setBtnDisabled('audit_refuse', false);
                        JDialog.tip('服务器出现异常，更新失败。', JDialog.ERROR);
                    }
                });
            }
        });
    }
    
    
    var addUnbindApply = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 600,
			height :320,
			buttons: [{
	 			text: '确定',
	 			id: 'empi_unbindApply_save',	 			
	 			handler: function(){
	 				empiManageAddUnbindApply.saveInfo(function(){
						formDialog.closeDialog();
						grid.setParams('');
						grid.loadData(1);
					});
	 			}
	 		},{
	 			text: '返回',
	 			id: 'empi_unbindApply_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='empiManageAddUnbindApply' name='empiManageAddUnbindApply' src='empiManage/addUnbindApply.zb' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	};
});//document end