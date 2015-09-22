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

var auditDetails = function(empi,cardNo){
    formDialog = new JDialog({
        title : "EMPI实名信息更新审核",
        width : 720,
        height :330,
        buttons: [
            {
                text: '允许',
                id: 'audit_permit',
                handler: function () {
                    auditOperate('允许',cardNo);
                }
            },{
            text: '拒绝',
            id: 'audit_refuse',
            handler: function(){
                auditOperate('拒绝',cardNo);
            }
        }]
    });
    formDialog.show();
    formDialog.add("<iframe id='auditDetail' name='auditDetail' src='empiManage/updateAuditDetails.zb?empi="+empi+"' width='100%' height='100%' frameBorder='0'  />");
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
        {text: '申请机构', width: 230, textAlign: 'center', align: 'center', dataIndex: 'orgCode' },
        {text: '身份证号', width: 180, textAlign: 'center', align: 'left', dataIndex: 'cardNo'},
        {text: '姓名', width: 100, textAlign: 'center', align: 'left', dataIndex: 'name'},
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
                    return '<a href="javascript:auditDetails(\''+rowData['empi']+"\',\'"+rowData['cardNo']+'\')">审核</a>';
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
        dataUrl  : 'empiManage/updateApplyList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            curdButtonClick: function(btn){
                if(btn=='add'){
                	addRealApply("实名更新申请");
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

    auditOperate = function(operate,cardNo){
        var url =  "empiManage/refuseUpdateApply.zb";
        if(operate == '允许'){
            url = "empiManage/permitUpdateApply.zb"
        }
        JDialog.showConfirmDialog(operate+'EMPI实名信息更新确认', '是否'+operate+'更新身份证号' + cardNo + '的实名信息？', JDialog.WARNING, function (id, text) {
            if (id == 'yes') {
                formDialog.mask('正在更新，请稍候...');
                setBtnDisabled('audit_permit', false);
                setBtnDisabled('audit_refuse', false);
                var data = "{";
                $("#auditDetail").contents().find("#auditForm").find("input[type='text']").each(function () {
                    data += $(this).attr("name") + ":'" + $(this).val() + "',";
                });
                $("#auditDetail").contents().find("#auditForm").find("input[type='hidden']").each(function () {
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
    
    var addRealApply = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 600,
			height :320,
			buttons: [{
	 			text: '确定',
	 			id: 'empi_realApply_save',	 			
	 			handler: function(){
	 				empiManageAddRealApply.saveInfo(function(){
						formDialog.closeDialog();
						grid.setParams('');
						grid.loadData(1);
					});
	 			}
	 		},{
	 			text: '返回',
	 			id: 'empi_realApply_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='empiManageAddRealApply' name='empiManageAddRealApply' src='empiManage/addRealApply.zb' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	};
});//document end