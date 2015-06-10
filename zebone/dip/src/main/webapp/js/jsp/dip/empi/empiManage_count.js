var field, col, grid, formDialog, showResponse,saveInfo;
var field_field, field_col, field_grid
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
        {text: '注册机构编码', width: 200, textAlign: 'center', align: 'center', dataIndex: 'orgCode' },
        {text: '注册机构名称', width: 300, textAlign: 'center', align: 'left', dataIndex: 'orgName'},
        {text: '注册数', width: 100, textAlign: 'center', align: 'left', dataIndex: 'count'}
    ];

    //查询事件
    $("#querybtn").click(function () {
        //查询的条件
        var startDt = $("input[name='startDt']").val();
        var endDt = $("input[name='endDt']").val();
        if(startDt !='' && endDt != ''){
            if(startDt > endDt){
                JDialog.tip("上传时间范围不合法",2);
                $("input[name='startDt").focus();
                return;
            }
        }
        if(startDt !='' || endDt != ''){
            var date = new Date();
            var year = date.getFullYear().toString();
            var month = (date.getMonth()+1).toString();
            var day = date.getDate().toString();
            var sj = year+"-";
            sj +=month.length==1?"0"+month:month;
            sj += "-";
            sj +=day.length==1?"0"+day:day;

            if(startDt !=''){
                if(startDt > sj){
                    JDialog.tip("时间不能超过今天",2);
                    $("input[name='startDt").focus();
                    return;
                }
            }
            if(endDt != ''){
                if(endDt > sj){
                    JDialog.tip("时间不能超过今天",2);
                    $("input[name='endDt").focus();
                    return;
                }
            }
        }
        if ($("#orgCode").val() == '' && $("#orgName").val() != '') {
            JDialog.tip("注册机构不存在", 2);
            $("input[name='orgName").focus();
            return;
        }

        //查询的条件
        var datas=queryCondition();
        grid.setParams(eval('('+datas+')'));
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

    $("#orgName").autocomplete({//获取注册机构信息
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
        title: '主索引注册统计列表',
        col	     : col,
        dataCol  : '',
        checkbox : false,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'empiManage/countInfoList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            afterload:function(){
                field_grid.setParams('');
                field_grid.loadData(1);
            }
        }
    });
    grid.loadData(1);
    $("#grid").find("button[name='add']").css("display","none");
    $("#grid").find("button[name='update']").css("display","none");
    $("#grid").find("button[name='remove']").css("display","none");
    $("#grid").find("button[name='refresh']").css("display","none");



    $(window).resize(function(){
        grid.setGridHeight($(document.body).height()-112);
    });
});//document end