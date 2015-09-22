var field, col, grid, formDialog, showResponse,saveInfo;
var field_field, field_col, field_grid, field_formDialog, field_showResponse,field_saveInfo;
var empi;
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

var empiDetails = function(empi){
    formDialog = new JDialog({
        title : "主索引信息详情",
        width : 580,
        height :350,
        buttons: [{
            text: '关闭',
            id: 'p_dataset_close',
            handler: function(){
                formDialog.closeDialog();
            }
        }]
    });
    formDialog.show();
    formDialog.add("<iframe id='familyEdit' name='familyEdit' src='empiManage/empiDetals.zb?empi="+empi+"' width='100%' height='100%' frameBorder='0'  />");
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
        {text: '姓名', width: 75, textAlign: 'center', align: 'center', dataIndex: 'name' },
        {text: '主索引号', width: 250, textAlign: 'center', align: 'left', dataIndex: 'empi'},
        {text: '性别', width: 75, textAlign: 'center', align: 'left', dataIndex: 'sex',
            renderer: function (value) {
                if (value == '0') {
                    return "未知的性别";
                } else if (value == '1') {
                    return "男";
                } else if (value == "2") {
                    return "女";
                } else if (value == "9") {
                    return "未说明的性别"
                } else {
                    return ""
                }
            }},
        {text: '联系电话', width: 100, textAlign: 'center', align: 'left', dataIndex: 'mobileNumber'},
        { text: '操作',
            width:90,
            textAlign: 'center',
            align : 'center',
            formatter:
                function(rowData){
                    return '<a href="javascript:empiDetails(\''+rowData['empi']+'\')">查看详情</a>';
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

    grid = new JGrid({
        title: '主索引信息列表',
        col	     : col,
        selectModel:1,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'empiManage/empiInfoList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            afterload:function(){
                field_grid.setParams('');
                field_grid.loadData(1);
            },
            click : function(row) {
                field_grid.setParams({empi:row['empi']});
                empi = row['empi'];
                field_grid.loadData(1);
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
    $("#grid").find("button[name='add']").css("display","none");
    $("#grid").find("button[name='update']").css("display","none");
    $("#grid").find("button[name='remove']").css("display","none");
    $("#grid").find("button[name='refresh']").css("display","none");

    field_col = [
        {text: 'EMPI', width: 0, textAlign: 'center', align: 'center', dataIndex: 'empi' },
        {text: '操作时间', width: 130, textAlign: 'center', align: 'left', dataIndex: 'createTime'},
        {text: '操作情况', width: 60, textAlign: 'center', align: 'left', dataIndex: 'operState',
            renderer: function (value) {
                if (value == '1') {
                    return "注册";
                } else if (value == '2') {
                    return "更新";
                }
            }},
        {text: '机构编码', width: 220, textAlign: 'center', align: 'left', dataIndex: 'deptCode'}
    ];

    field_grid = new JGrid({
        title: '操作明细列表',
        col	     : field_col,
        dataCol  : '',
        checkbox : false,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'empiManage/operateList.zb',
        render   : 'operateGrid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            curdButtonClick: function(btn){
                if(btn=='add'){
                    editFieldData("新 建");
                }else if(btn=='update'){
                    var rows = field_grid.getSelections();
                    if(rows.length!=1){
                        JDialog.tip('请选择一条记录进行修改');
                        return;
                    }
                    editFieldData("修 改", rows[0]['id']);
                }else if(btn=='remove'){
                    var rows = field_grid.getSelections();
                    if(rows.length==0){
                        JDialog.tip('请至少选择一条记录进行删除');
                        return;
                    }
                    removeFieldData();
                }else if(btn=='refresh'){
                    field_grid.setParams({empi:empi});
                    field_grid.loadData();
                }
            }
        }
    });
    field_grid.loadData(1);
    $("#operateGrid").find("button[name='add']").css("display","none");
    $("#operateGrid").find("button[name='update']").css("display","none");
    $("#operateGrid").find("button[name='remove']").css("display","none");
    $("#operateGrid").find("button[name='refresh']").css("display","none");

    $(window).resize(function(){
        grid.setGridHeight($(document.body).height()-112);
        field_grid.setGridHeight($(document.body).height()-112);
    });
});//document end