<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>家庭档案详细信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
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

    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.zTree.css"/>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
	<style type="text/css">
        html,body{
			background-color: #F9F9F9;
			height: auto;
		}
		table tr td{border: 1px solid #CCCCCC;}
		.tableTitle{
			font-weight:bold;
			padding-left: 20px;
		}
		.tableKey{
			text-align:center;
			padding-left: 20px;
			width:25%;
		}
		.tableValue{
			padding-left: 15px;
			width:25%;
		}
		.familyTitle{
			font-weight:bold;
			text-align:center;
			font-size: 24;
		}
    </style>
    <script type="text/javascript">
    var col = [ {text: '序号',		  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'orderNumber'   },
            	{text: '与户主关系',	  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'householdCode'   },
				{text: '姓名',		  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'name'	},
				{text: '性别',	      width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'sex', },
				{text: '出生日期',	  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'birthDate'	},
				{text: '文化程度',	  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'educationDegree'	},
				{text: '职业',	      width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'careerCode'	},
				{text: '婚姻',	      width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'marriageCode'	},
				{text: '备注',	      width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'	},
				{text: '个人状态',	  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'state'	},
			
		  ];
    $(function(){
    	var grid = new JGrid({
			title : '家庭成员信息',
			height:200,
			col : col,
			checkbox : false,
			dataUrl : 'resourcesManage/familyMembers.zb?familyId='+'${resourceFamily.famiId}',
			render : 'grid',
			pageBar  : false,
			crudOpera: false,
		});
		
		grid.loadData();
	});
    </script>
</head>
<body>
<div style="text-align:right;padding-right: 30px;margin-top: 20px;">家庭档案编号：${resourceFamily.famiCode}</div>
<table style="width:95%;margin-bottom: 20px;border-collapse:collapse;" align="center" >
	<tr>
		<td colspan="4" class="tableTitle">基本信息</td>
	</tr>
	<tr>
		<td class="tableKey">居住类型</td>
		<td colspan="3" class="tableValue">${resourceFamily.liveType}</td>
	</tr>
	<tr>
		<td class="tableKey">房东姓名</td>
		<td class="tableValue">${resourceFamily.landlordName}</td>
		<td class="tableKey">房东电话</td>
		<td class="tableValue">${resourceFamily.landlordTel}</td>
	</tr>
	<tr>
		<td class="tableKey">户主姓名</td>
		<td class="tableValue">${resourceFamily.householdName}</td>
		<td class="tableKey">人    口    数</td>
		<td class="tableValue">${resourceFamily.residentPopuNumber}</td>
	</tr>
	<tr>
		<td class="tableKey">居住总面积<br/>（㎡）</td>
		<td class="tableValue">${resourceFamily.totalArea}</td>
		<td class="tableKey">人均居住面积<br/>（㎡）</td>
		<td class="tableValue">${resourceFamily.perArea}</td>
	</tr>
	<tr>
		<td class="tableKey">手机号码</td>
		<td class="tableValue">${resourceFamily.familyTel}</td>
		<td class="tableKey">纸质档案编号</td>
		<td class="tableValue">${resourceFamily.paperFileNo}</td>
	</tr>
	<tr>
		<td class="tableKey">住房性质</td>
		<td class="tableValue">${resourceFamily.housingPropertyCode}</td>
		<td class="tableKey">住房间数</td>
		<td class="tableValue">${resourceFamily.housingNumber}</td>
	</tr>
	<tr>
		<td class="tableKey">住房采光</td>
		<td class="tableValue">${resourceFamily.housingLightingCode}</td>
		<td class="tableKey">责任医生</td>
		<td class="tableValue">${resourceFamily.doctorCode}</td>
	</tr>
	<tr>
		<td class="tableKey">居住地址</td>
		<td class="tableValue">${resourceFamily.familyAddrCode}</td>
		<td class="tableKey">详细地址</td>
		<td class="tableValue">${resourceFamily.familyAddr}</td>
	</tr>
	<tr>
		<td class="tableKey">房屋类型</td>
		<td colspan="3" class="tableValue">${resourceFamily.housingCode}</td>
	</tr>
	<tr>
		<td class="tableKey">厨房</td>
		<td class="tableValue">${resourceFamily.kitchenCode}</td>
		<td class="tableKey">排风设施</td>
		<td class="tableValue">${resourceFamily.kitchenVentCode}</td>
	</tr>
	<tr>
		<td class="tableKey">户属性</td>
		<td colspan="3" class="tableValue">${resourceFamily.familyProCode}</td>
	</tr>
	<tr>
		<td class="tableKey">饮水</td>
		<td colspan="3" class="tableValue">${resourceFamily.waterCode}</td>
	</tr>
	<tr>
		<td class="tableKey">燃料</td>
		<td colspan="3" class="tableValue">${resourceFamily.fuelCode}</td>
	</tr>
	<tr>
		<td class="tableKey">家庭卫生厕所</td>
		<td colspan="3" class="tableValue">${resourceFamily.toiletsType}</td>
	</tr>
	<tr>
		<td class="tableKey">禽畜栏</td>
		<td colspan="3" class="tableValue">${resourceFamily.livestockCode}</td>
	</tr>
	<tr>
		<td class="tableKey">家用电器</td>
		<td colspan="3" class="tableValue">${resourceFamily.elecDeviceCode}</td>
	</tr>
	<tr>
		<td class="tableKey">交通工具</td>
		<td colspan="3" class="tableValue">${resourceFamily.vehicleCode}</td>
	</tr>
	<tr>
		<td class="tableTitle" colspan="4">经济状况</td>
	</tr>
	<tr>
		<td class="tableKey">经济状况</td>
		<td colspan="3" class="tableValue">${resourceFamily.econStatusCode}</td>
	</tr>
	<tr>
		<td class="tableKey">人均月收入</td>
		<td colspan="3" class="tableValue">${resourceFamily.perMonthIncomeCode}</td>
	</tr>
	<tr>
		<td class="tableKey">家庭总支出 <br/>（元/人/月）</td>
		<td class="tableValue">${resourceFamily.famiTotalPay}</td>
		<td class="tableKey">饮食支出 <br/>（元/人/月）</td>
		<td class="tableValue">${resourceFamily.famiFoodPay}</td>
	</tr>
	<tr>
		<td class="tableTitle" colspan="4">其他信息</td>
	</tr>
	<tr>
		<td class="tableKey">垃圾处理</td>
		<td class="tableValue">${resourceFamily.garDisposalCode}</td>
		<td class="tableKey">污水处理</td>
		<td class="tableValue">${resourceFamily.sewageDiposalCode}</td>
	</tr>
	<tr>
		<td class="tableKey">文体设备（多选）</td>
		<td colspan="3" class="tableValue">${resourceFamily.cultSportCode}</td>
	</tr>
	<tr>
		<td class="tableKey">建档日期</td>
		<td class="tableValue">${resourceFamily.fileDate}</td>
		<td class="tableKey">建当医生</td>
		<td class="tableValue">${resourceFamily.fileDoctorCode}</td>
	</tr>
	<tr>
		<td class="tableKey">登记日期</td>
		<td class="tableValue">${resourceFamily.inputDate}</td>
		<td class="tableKey">登记人</td>
		<td class="tableValue">${resourceFamily.inputUserCode}</td>
	</tr>
	<tr>
		<td class="tableKey">备注</td>
		<td colspan="3" class="tableValue">${resourceFamily.remark}</td>
	</tr>
</table>

<div class="c_content" style="margin-left: 10px;margin-right: 10px;">
<div id="grid"></div>
</div>

</body>
</html>