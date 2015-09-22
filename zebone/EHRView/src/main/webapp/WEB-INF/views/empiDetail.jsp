<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器-基本信息</title>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-dialog.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/css/index.css"/>
<style type="text/css">
html,body{height:100%;overflow: hidden;}
#fieldset-body1 span{
	margin:10px 40px 0 0;
	display:inline-block;
}
#fieldset-body1 {
	padding:10px 40px 40px 40px;
}
#fieldset-body2 span{
	margin:10px 100px 10px 0;
	display:inline-block;
}
#fieldset-body2 {
	text-align:left;
	padding:10px 0 20px 0;
}
#fieldset-body2 a{
	margin-top:20px;
}
</style>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>

<script type="text/javascript">
var ie=navigator.appName=="Microsoft Internet Explorer"?true:false;
$(function(){
	var panel_1 = new JPanel({
        title: '基本信息',
        html: $('#fieldset-body1'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });
    var panel_2 = new JPanel({
        title: '二级标识信息',
        html: $('#fieldset-body2'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });

});
</script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript">
 DD_belatedPNG.fix('DIV');
 DD_belatedPNG.fix('A');
 DD_belatedPNG.fix('SPAN');
 DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
</head>
<body>
<div id="container" class="" style="padding:10px;"></div>
	<div id="fieldset-body1" class="j-fieldset-body ">
        <table>
            <tr>
                <td><span>身份证件类别：${card_type}</span></td>
                <td><span>身份证件号码：${card_no}</span></td>
            </tr>
            <tr>
                <td><span>姓名：${name}</span></td>
                <td><span>性别：${sex}</span></td>
                <td><span>出生日期：${birthday}</span></td>
            </tr>
            <tr>
                <td><span>年龄：${age}</span></td>
                <td><span>国籍：${nationality}</span></td>
                <td><span>民族：${nation}</span></td>
            </tr>
            <tr>
                <td><span>婚姻状况：${ marital_status}</span></td>
                <td><span>手机号码：${tel_number}</span></td>
                <td><span>联系电话：${contacts_mobile}</span></td>
            </tr>
            <tr>
                <td><span>职业：${profession}</span></td>
                <td><span>工作单位：${work_unit}</span></td>
            </tr>
            <tr>
                <td><span>联系人：${contacts}</span></td>
                <td><span>联系人手机号码:${contacts_mobile}</span></td>
                <td></td>
            </tr>
            <tr>
                <td><span>户籍地址：${address}</span></td>
                <td><span>户籍地址邮编：${address_zipcode}</span></td>
                <td></td>
            </tr>
            <tr>
                <td><span>常住地址：${permanent_address}</span></td>
                <td><span>常住地址邮编：${permanent_zipcode}</span></td>
                <td></td>
            </tr>
        </table>
	</div>


	<div id="fieldset-body2" class="j-fieldset-body">
		<div id="JGrid" class="jgridDIV" >
			<div id="JGrid-9b4d6-main" class="jgridMain" style="">
				<div id="JGrid-9b4d6-headerAndDataDiv" class="jHeaderAndDataDIV" style="height: auto;">
					<div class="jHeaderDIV">
						<table id="JGrid-9b4d6-headerTable" class="jgridHeaderTable" cellspacing="0" cellpadding="0" border="0" >
							<tr class="header" name="header">
								<td width="100"  style="text-align:center;width:100px" name="maxicon"><div class="headerTdDiv">序号<span value="0"></span></div></td>
								<td width="100"  style="text-align:center;width:200px" name="orderNo"><div class="headerTdDiv">卡类型<span value="0"></span></div></td>
								<td width="110"  style="text-align:center;width:250px" name="moduleName"><div class="headerTdDiv">卡号<span value="0"></span></div></td>
								<td width="150"  style="text-align: center; width: 150px;" name="url"><div class="headerTdDiv">卡序列号<span value="0"></span></div></td>
								<td width="150"  style="text-align: center; width: 150px;" name="url"><div class="headerTdDiv">发卡单位<span value="0"></span></div></td>
							</tr>
						</table>
					</div>
					<div id="JGrid-85a5b-dataDiv" class="jgridDataDIV" style="height: 503px;">
						<table cellspacing="1" cellpadding="0" border="0" id="JGrid-85a5b-dataTable">
							<c:forEach items="${cardList}" var="object" varStatus="theCount">
							    <tr index="1" name="data" class="">
									<td align="center" title="maxicon1" selects="0" style="width: 100px;">${theCount.index+1}</td>
									<td align="center" title="1" selects="0" style="width: 200px;">${object.cardType}</td>
									<td align="center" title="moduleName1" selects="0" style="width: 250px;">${object.cardNo}</td>
									<td align="center" title="url1" selects="0" style="width: 150px;">${object.cardSerialNo}</td>
									<td align="center" title="url1" selects="0" style="width: 150px;">${object.cardOrg}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

