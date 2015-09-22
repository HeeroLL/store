var field, col, grid, formDialog, showResponse,saveInfo;
var masterDataId,mdTableName,tableExistFlag;
var field_field, field_col, field_grid, field_formDialog, field_showResponse,field_saveInfo;
var editHTML='';
var idkey;
var queryCondition = function () {
    var datas = "{";
    $("#query").find("input").each(function () {
        if ($.trim($(this).val()) != '') {
            datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
        }
    });
    if (datas.length > 1) datas = datas.substring(0, datas.length - 1);
    datas += "}";
    return datas;
};

//新增修改数据start
var editData=function(title, id){

    formDialog=new JDialog({
        title : title,
        width: 560,
        height: 320,
        buttons: [{
            text: '确定',
            id: 'p_maindatastru_save',
            handler: function(){
                maindatastruEdit.saveInfo(function(){
                    formDialog.closeDialog();
                    grid.setParams('');
                    grid.loadData(1);
                });
            }
        },{
            text: '返回',
            id: 'p_maindatastru_close',
            handler: function(){
                formDialog.closeDialog();
            }
        }]
    });

    if (!id) id = "";
    formDialog.show();
    formDialog.add("<iframe id='maindatastruEdit' name='maindatastruEdit' src='masterDataStru/masterDataStruEdit.zb?id=" + id + "' width='100%' height='100%' frameBorder='0' scrolling='no' />");

    //按钮失效
    setBtnDisabled = function(btnId, bool)
    {
        $("#"+btnId).attr("disabled", bool);
    }

    //保存前校验
    var check = function()
    {
    }
};//新增修改数据end

var removeData = function(){
    var rows = grid.getSelections();
    if(rows.length>0){
        JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
            if(id=='yes'){
                grid.jgridMain.mask('正在删除数据，请稍候...');
                var ids = '';
                for(var i=0; i<rows.length; i++){
                    ids += rows[i]['id'] + ',';
                }
                ids = ids.substring(0, ids.length-1);
                var datas = "id="+ids;
                $.ajax({
                    url: "masterDataStru/removeMasterDataStruByIds.zb",
                    type: 'POST',
                    cache: false,
                    data: encodeURI(datas),
                    dataType: 'json',
                    success: function(res){
                        grid.jgridMain.unmask();
                        if(res.success){
                            var msg = res.msg?res.msg:'数据删除成功。';
                            JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
                                if(id=='ok'){
                                    grid.setParams('');
                                    grid.loadData(1);
                                }
                            });
                        }else{
                            JDialog.tip(res.msg?res.msg:'数据删除失败。', 2);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        grid.jgridMain.unmask();
                        JDialog.tip('服务器出现异常，数据删除失败。', 2);
                    }
                });
            }
        });
    }
};

$(document).ready(function() {
	//加载布局控件
    $('body').layout({
       
    });
    $('#listshow').layout({
        east: {
            size: 560
        }
    });
	
    var col = [
        {text: 'ID_', width: 0, textAlign: 'center', align: 'center', dataIndex: 'id' },
        {text: '主数据编码', width: 100, textAlign: 'center', align: 'left', dataIndex: 'code'},
        {text: '主数据名称', width: 150, textAlign: 'center', align: 'left', dataIndex: 'name'},
        {text: '数据库表名称', width: 220, textAlign: 'center', align: 'left', dataIndex: 'tableName'},
        {text: '类型', width: 0, textAlign: 'center', align: 'left', dataIndex: 'typeId'},
        {text: '主数据描述', width: 200, textAlign: 'center', align: 'left', dataIndex: 'comment'},
        { text: '操作',
            width:60,
            textAlign: 'center',
            align : 'center',
            formatter:
                function(rowData){
                    return '<a href="javascript:generateSql(\''+rowData['id']+"\',\'"+rowData['tableName']+'\')">生成SQL</a>';
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
        col	     : col,
        selectModel:1,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'masterDataStru/masterDataStruList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            afterload:function(){
                field_grid.setParams('');
                field_grid.loadData(1);
                $("#gridField").find("button[name='add']").attr("disabled","disabled");
                $("#gridField").find("button[name='update']").attr("disabled","disabled");
                $("#gridField").find("button[name='remove']").attr("disabled","disabled");
            },
            click : function(row) {
                field_grid.setParams({masterDataId:row['id']});
                masterDataId = row['id'];
                mdTableName = row['tableName'];
                tableExistFlag = isMDTableExist(mdTableName);
                field_grid.loadData(1);
                $("#gridField").find("button[name='add']").attr("disabled",false);
                $("#gridField").find("button[name='update']").attr("disabled",false);
                $("#gridField").find("button[name='remove']").attr("disabled",false);
            },

            dblclick: function(row){//Grid双击事件
                editData("修 改", row['id']);
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

//------------------- 以下用于主数据结构字段管理
    field_col = [
        {text: 'ID', width: 0, textAlign: 'center', align: 'center', dataIndex: 'id' },
        {text: '数据库字段名称', width: 140, textAlign: 'center', align: 'left', dataIndex: 'fieldName'},
        {text: '显示名称', width: 140, textAlign: 'center', align: 'left', dataIndex: 'displayName'},
        {text: '字段类型', width: 70, textAlign: 'center', align: 'left', dataIndex: 'fieldType',
            formatter: function(cellvalue, options, rowObject) {
                if (cellvalue.fieldType == 'varchar') {
                    return "字符型";
                }
                else if(cellvalue.fieldType == 'datatime')
                {
                    return "日期";
                }
                else if(cellvalue.fieldType == 'number')
                {
                    return "数字";
                }
                else if(cellvalue.fieldType == 'dt')
                {
                    return "字典型";
                }
                else if(cellvalue.fieldType == 'md')
                {
                    return "主数据";
                }
            }
        },
        {text: '字段说明', width: 75, textAlign: 'center', align: 'left', dataIndex: 'comment'},
        {text: '字段长度', width: 0, textAlign: 'center', align: 'left', dataIndex: 'fieldLength'},
        {text: '类型编码', width: 0, textAlign: 'center', align: 'left', dataIndex: 'typeCode'},
        {text: '是否为空', width: 0, textAlign: 'center', align: 'left', dataIndex: 'nullable',
            formatter: function(cellvalue, options, rowObject) {
                if(cellvalue.nullable == 1)
                    return "是";
                else return "否";
            }},
        {text: '排序号', width: 0, textAlign: 'center', align: 'left', dataIndex: 'sortNo'},
        {text: '主数据id', width: 0, textAlign: 'center', align: 'left', dataIndex: 'masterDataId'},
        {text: '是否显示', width: 0, textAlign: 'center', align: 'left', dataIndex: 'displayable'},
        {text: '检验规则', width: 0, textAlign: 'center', align: 'left', dataIndex: 'checkRule'}
    ];

    field_grid = new JGrid({
        col	     : field_col,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'masterDataStru/masterDataFieldList.zb',
        render   : 'gridField',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            dblclick: function(row){//Grid双击事件
                if (tableExistFlag == '1') {
                    JDialog.tip('主数据表已经创建,字段不能维护');
                }else if(row['fieldName'] == 'MD_CODE' || row['fieldName'] == 'MD_NAME'){
                    JDialog.tip('不允许维护编码和名称字段');
                }else {
                    editFieldData("修 改", row['id']);
                }
            },
            curdButtonClick: function(btn){
                if (tableExistFlag == '1') {
                    JDialog.tip('主数据表已经创建,字段不能维护');
                }else {
                    if (btn == 'add') {
                        editFieldData("新 建");
                    } else if (btn == 'update') {
                        var rows = field_grid.getSelections();
                        if (rows.length != 1) {
                            JDialog.tip('请选择一条记录进行修改');
                            return;
                        }
                        if(rows[0]['fieldName'] == 'MD_CODE' || rows[0]['fieldName']=='MD_NAME'){
                            JDialog.tip('不允许维护编码和名称字段');
                            return ;
                        }
                        editFieldData("修 改", rows[0]['id']);
                    } else if (btn == 'remove') {
                        var rows = field_grid.getSelections();
                        if (rows.length == 0) {
                            JDialog.tip('请至少选择一条记录进行删除');
                            return;
                        }
                        if(rows[0]['fieldName'] == 'MD_CODE' || rows[0]['fieldName']=='MD_NAME'){
                            JDialog.tip('不允许维护编码和名称字段');
                            return ;
                        }
                        removeFieldData();
                    }
                }
            }
        }
    });
    field_grid.loadData(1);
    $("#gridField").find("button[name='refresh']").css("display","none");

    $(window).resize(function(){
        grid.setGridHeight($(document.body).height()-112);
        field_grid.setGridHeight($(document.body).height()-112);
    });
});//document end


//新增修改数据start
var editFieldData=function(title, id){

    field_formDialog=new JDialog({
        title : title,
        width: 560,
        height: 320,
        buttons: [{
            text: '确定',
            id: 'p_maindatastrufield_save',
            handler: function(){
                maindatastrufieldEdit.saveInfo(function(){
                    field_formDialog.closeDialog();
                    field_grid.setParams({masterDataId:masterDataId});
                    field_grid.loadData(1);
                });
            }
        },{
            text: '返回',
            id: 'p_maindatastrufield_close',
            handler: function(){
                field_formDialog.closeDialog();
            }
        }]
    });

    if(!id) id = "";
    field_formDialog.show();
    field_formDialog.add("<iframe id='maindatastrufieldEdit' name='maindatastrufieldEdit' src='masterDataStru/masterDataStruFieldEdit.zb?masterDataId="+masterDataId+"&id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");

    //按钮失效
    setBtnDisabled = function(btnId, bool)
    {
        $("#"+btnId).attr("disabled", bool);
    };

    //保存前校验
    var check = function()
    {
        //alert("555555555");
    }
};//新增修改数据end

var removeFieldData = function(){
    var rows = field_grid.getSelections();
    if(rows.length>0){
        JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
            if(id=='yes'){
                field_grid.jgridMain.mask('正在删除数据，请稍候...');
                var ids = '';
                for(var i=0; i<rows.length; i++){
                    ids += rows[i]['id'] + ',';
                }
                ids = ids.substring(0, ids.length-1);
                var datas = "id="+ids;
                $.ajax({
                    url: "masterDataStru/removeMasterDataStruFieldByIds.zb",
                    type: 'POST',
                    cache: false,
                    data: encodeURI(datas),
                    dataType: 'json',
                    success: function(res){
                        field_grid.jgridMain.unmask();
                        if(res.success){
                            var msg = res.msg?res.msg:'数据删除成功。';
                            JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
                                if(id=='ok'){
                                    field_grid.setParams({masterDataId:masterDataId});
                                    field_grid.loadData(1);
                                }
                            });
                        }else{
                            JDialog.tip(res.msg?res.msg:'数据删除失败。',3);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        field_grid.jgridMain.unmask();
                        JDialog.tip('服务器出现异常，数据删除失败。',3);
                    }
                });
            }
        });
    }
};

//主数据表是否已经创建
var isMDTableExist =function(mdTableName){
    var flag = "";
    $.ajax({
        url : 'masterDataStru/isMDTableExist.zb',
        type : 'POST',
        cache : false,
        data : encodeURI("mdTableName=" + mdTableName),
        dataType : 'json',
        async:false,
        success : function(res) {
            if(res.mdTableExist){
                flag = "1";
            }else{
            }
        },
        error : function(XMLHttpRequest,textStatus, errorThrown) {
        }
    });

    return flag;
};

function generateSql(masterDataId, tableName) {
    JDialog.showConfirmDialog('警告', '数据库表一旦创建，数据结构将不能进行维护，请确认所对应的数据结构是否都已创建？', JDialog.WARNING, function (id, text) {
        if (id == 'yes') {
            field_formDialog = new JDialog({
                title: '建表语句',
                width: 560,
                height: 320,
                buttons: [
                    {
                        text: '关闭',
                        id: 'p_mdstru_sql_close',
                        handler: function () {
                            field_formDialog.closeDialog();
                        }
                    }
                ]
            });
            field_formDialog.show();
            field_formDialog.add("<iframe src='masterDataStru/generateSql.zb?masterDataId=" + masterDataId + "&tableName=" + tableName + "' width='100%' height='100%' frameBorder='0' scrolling='no' />");
        }
    });
}