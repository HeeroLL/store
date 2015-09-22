<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>EMPI查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<style type="text/css">
html,body{background-color: #EEF7FE;overflow: hidden;}
.term{
	margin-left: 10px;
}
#query input{
	width: 100px;
}
#query select{
	width: 106px;
}
</style>
<script type="text/javascript">
var grid;
var col = [{text: '时间', width: 130, textAlign: 'center',	align : 'center', dataIndex: 'eventTime' },
			{text: '事件', width: 100, textAlign: 'center', align : 'center', dataIndex: 'event' },
			{text: '姓名', width: 90, textAlign: 'center',	align : 'center', dataIndex: 'name' },
			{text: '标识类型', width: 100, textAlign: 'center',	align : 'center', dataIndex: 'cardType' },
			{text: '标识号', width: 140, textAlign: 'center',	align : 'center', dataIndex: 'cardNo' },
			{text: '状态', width: 90, textAlign: 'center',	align : 'center', dataIndex: 'status' },
			{text: '错误描述', width: 120, textAlign: 'center',	align : 'center', dataIndex: 'errorDescription;' }
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

$(function(){
	grid = new JGrid({
		title: 'EMPI查询列表',
		col	 :col,
		dataCol:'',
		checkbox : false,
		striped:true,
		height   :document.body.clientHeight-150,
		dataUrl  : 'log/empiSearch.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: false,
		countEveryPage: 15
	});
	
	grid.loadData();
	
	$("#querybtn").click(function(){
		var startDt = $("input[name='startTime']").val();
        var endDt = $("input[name='endTime']").val();
        if (startDt != '' && endDt != '') {
            if (startDt > endDt) {
                JDialog.tip("上传时间范围不合法", 2);
                $("input[name='startTime").focus();
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
                    $("input[name='endTime").focus();
                    return;
                }
            }
            if (startDt != '') {
                if (startDt > sj) {
                    JDialog.tip("时间不能超过今天", 2);
                    $("input[name='startTime").focus();
                    return;
                }
            }
        }
        
        //查询的条件
        var datas = queryCondition();
        grid.setParams(eval('(' + datas + ')'));
        grid.loadData(1);
        
	});
});
</script>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">EMPI查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="query">
					<div style="padding: 5px;">
						<span class="term">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</span>
						<select name="status">
							<option value="">请选择</option>
							<option value="0">empi未上传</option>
							<option value="1">已匹配</option>
							<option value="2">未匹配</option>
						</select>
						<span class="term">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</span>
						<input type="text" name="startTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> ---- 
						<input type="text" name="endTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div style="padding: 5px;">
						<span class="term">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
						<input type="text" name="name"/>
						<span class="term">标识类型：</span>
						<select name="cardType">
							<option value="">请选择</option>
							<c:forEach items="${cardTypeList}" var="cardInfo">
                                <option value="${cardInfo.code}">${cardInfo.name}</option>
                            </c:forEach>
						</select>
						<span class="term">标&nbsp;识&nbsp;&nbsp;号：</span>
						<input type="text" name="cardNo"/>
						<span class="term">
						<a class="btn" href="javascript:void(0);">
							<span class="btn-left" id="querybtn">
								<span class="btn-text icon-search">查询</span>
							</span>
						</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>
</body>
</html>
