var grid, dialog;
var editHTML = '';
var col = [
    {text: 'ID', width: 0, textAlign: 'center', align: 'center', dataIndex: 'id' },
    {text: '时间', width: 150, textAlign: 'center', align: 'left', dataIndex: 'createTime' },
    {text: '卫生机构', width: 150, textAlign: 'center', align: 'left', dataIndex: 'orgCode' },
    {text: '科室', width: 120, textAlign: 'center', align: 'left', dataIndex: 'deptCode' },
    {text: '医务人员', width: 100, textAlign: 'center', align: 'left', dataIndex: 'doctorCode' },
    {text: '文档类型', width: 120, textAlign: 'center', align: 'left', dataIndex: 'docType'},
    {text: '文档编号', width: 100, textAlign: 'center', align: 'left', dataIndex: 'docNo'},
    {text: '调阅状态', width: 80, textAlign: 'center', align: 'left', dataIndex: 'viewState'}
];

var queryCondition = function () {
    var datas = "{";
    $("#query").find("input").each(function () {
        if ($.trim($(this).val()) != '') {
            datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
        }
    });
    $("#query").find("select").each(function () {
        if ($.trim($(this).val()) != '') {
            datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
        }
    });
    if (datas.length > 1) datas = datas.substring(0, datas.length - 1);
    datas += "}";
    return datas;
};

$(function () {
    $("#querybtn").click(function () {
        var startDt = $("input[name='startDt']").val();
        var endDt = $("input[name='endDt']").val();
        if (startDt != '' && endDt != '') {
            if (startDt > endDt) {
                JDialog.tip("上传时间范围不合法", 2);
                $("input[name='startDt").focus();
                return;
            }
        }
        if (startDt != '' || endDt != '') {
            var date = new Date();
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1).toString();
            var day = date.getDate().toString();
            var sj = year + "-";
            sj += month.length == 1 ? "0" + month : month;
            sj += "-";
            sj += day.length == 1 ? "0" + day : day;

            if (endDt != '') {
                if (endDt > sj) {
                    JDialog.tip("时间不能超过今天", 2);
                    $("input[name='endDt").focus();
                    return;
                }
            }
            if (startDt != '') {
                if (startDt > sj) {
                    JDialog.tip("时间不能超过今天", 2);
                    $("input[name='startDt").focus();
                    return;
                }
            }
        }
        if ($("#orgCode").val() == '' && $("#orgName").val() != '') {
            JDialog.tip("机构不存在", 2);
            $("input[name='orgName").focus();
            return;
        }
        if ($("input[name='cardNo']").val() != '') {
            if ($("input[name='cardNo']").val().length > 20) {
                JDialog.tip("标识号长度超出范围", 2);
                $("input[name='cardNo").focus();
                return;
            }
        }
        //查询的条件
        var datas = queryCondition();
        grid.setParams(eval('(' + datas + ')'));
        grid.loadData(1);
    });

    $("#orgName").autocomplete({//获取医疗机构信息
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
        title: '日志列表',
        col: col,
        dataCol: '',
        checkbox: false,
        striped: true,
        height: document.body.clientHeight - 130,
        dataUrl: 'log/docViewLogList.zb',
        render: 'grid',
        pageBar: true,
        crudOpera: false,
        countEveryPage: 15
    });

    grid.loadData();
});