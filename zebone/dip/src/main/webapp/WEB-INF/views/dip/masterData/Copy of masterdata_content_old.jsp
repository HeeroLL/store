<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>主数据管理内容</title>
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
    <script type="text/javascript" src="js/jsp/dip/masterData/masterdata_content.js"></script>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
    <script type="text/javascript">
        var projectPath = '${pageContext.request.contextPath}';
        var nodes = ${treeList};
        var mdCode = '${id}';

        var tableName = '${tableName}';
        var displayName = '${displayName}';
        var fieldName = '${fieldName}';
        var displayFlag = '${displayFlag}';
        var proName = '${proName}';
        var fieldType = '${fieldType}';

        var displayNameV = '';
        var fieldNameV = '';
        var displayFlagV = '';
        var proNameV = '';
        if (displayName.length > 0) {
            displayNameV = displayName.split(",");
        }
        if (fieldName.length > 0) {
            fieldNameV = fieldName.split(",");
        }
        if (displayFlag.length > 0) {
            displayFlagV = displayFlag.split(",");
        }
        if (proName.length > 0) {
            proNameV = proName.split(",");
        }

        var grid;
        var col = '';
        var column = '';

        //动态生成Grid列表
        var widthV;
        if (displayNameV.length > 0) {
            col = "{text: 'ID', width: 0, textAlign: 'center', align: 'center', dataIndex: 'pro0' },";
        }
        for (var k = 0; k < displayNameV.length; k++) {
            //判断该字段是否显示
            if ('n' == $.trim(displayFlagV[k]).toLowerCase()) {
                widthV = 0;
            } else {
                widthV = 150;
            }
            col = col + "{text: '" + $.trim(displayNameV[k]) + "', width: " + widthV + ", textAlign: 'center',	align : 'center', dataIndex: '" + $.trim(proNameV[k]) + "' },";
        }
        if (col.length > 1) col = col.substring(0, col.length - 1);
        column = eval('[' + col + ']');

        var zTreeOnClick = function (event, treeId, treeNode, clickFlag) {
            if (!treeNode.isParent) {
                window.location.href = projectPath+"/maindata/masterDataContent.zb?id=" + treeNode.id + "&tableName=" + treeNode.tableName;
            }else{
                window.location.href = projectPath+"/maindata/masterData.zb?id="+treeNode.id;
            }
        };
        var setting = {
            treeId: 'type_tree',
            data: {
                key: {
                    name: "name"
                },
                simpleData: {
                    enable: true,
                    idKey: 'id',
                    pIdKey: 'typeId',
                    rootPId: ''
                }
            },
            callback: {
                onClick: zTreeOnClick
            }
        };

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

        $(document).ready(function () {
            //查询事件
            $("#querybtn").click(function(){
                //查询的条件
                var conditions=queryCondition();
                grid.setParams(eval('('+conditions+')'));
                if (displayName.length > 0) {
                    grid.loadData(1);
                }
            });

            $("#query").bind('keypress', function (e) {
                if (e.keyCode == 13) {
                    var conditions = queryCondition();
                    grid.setParams(eval('(' + conditions + ')'));
                    if (displayName.length > 0) {
                        grid.loadData(1);
                        e.preventDefault();
                        e.stopPropagation();
                    }
                }
            });

            // grid加载地址
            var paramURI = 'maindata/masterDataList.zb?dbName=' + tableName + "&dbFields=" + fieldName;

            grid = new JGrid({
                title: '主数据列表',
                col: column,
                dataCol: '',
                checkbox: column.length > 0,
                striped: true,
                height: document.body.clientHeight-100,
                dataUrl: encodeURI(paramURI),
                render: 'grid',
                pageBar: column.length > 0,
                crudOpera: true,
                countEveryPage: 20,
                formToggleId: 'eoc',//切换表单显示的id
                listeners: {
                    dblclick: function (row) {//Grid双击事件
                        editData("修 改", row['pro0']);
                    },
                    curdButtonClick: function (btn) {
                        if (column.length > 0) {
                            if (btn == 'add') {
                                editData("新 建");
                            } else if (btn == 'update') {
                                var rows = grid.getSelections();
                                if (rows.length > 1) {
                                    JDialog.tip('只能选中一条记录进行修改');
                                    return;
                                } else if (rows.length == 0) {
                                    JDialog.tip('请选择一条主数据');
                                    return;
                                }
                                editData("修 改", rows[0]['pro0']);
                            } else if (btn == 'remove') {
                                var rows = grid.getSelections();
                                if (rows.length == 0) {
                                    JDialog.tip('请选择需要删除的主数据内容');
                                    return;
                                }
                                removeData();
                            } else if (btn == 'refresh') {
                                grid.loadData();
                            }
                        }
                    }
                }
            });
            if (displayName.length > 0) {
                grid.loadData(1);
            }

            //加载布局控件
            $('body').layout({
                west: {
                    size: 200
                }
            });
            //加载文档树
            var docTree = $.fn.zTree.init($("#masterdata_struct"), setting, nodes);
            docTree.expandAll(true);
            getSelectNodes(mdCode);

            if(column.length==0){
                $("button[name='add']").attr("disabled","disabled");
                $("button[name='update']").attr("disabled","disabled");
                $("button[name='remove']").attr("disabled","disabled");
                $("button[name='refresh']").attr("disabled","disabled");
                $("#querybtn").attr("disabled","disabled");
            }

            $(window).resize(function () {
                grid.setGridHeight($(document.body).height() - $('.container').outerHeight() - 25);
            });
        });

        function getSelectNodes(id) {
            var treeObj = $.fn.zTree.getZTreeObj("masterdata_struct");
            treeObj.selectNode(treeObj.getNodeByParam("id", id, null));
        }
    </script>

</head>
<body>
<div class="ui-layout-west">
	<!--  容器 -->
	<div class="container">
		<div class="c_nw">
	    	<div class="c_ne"><div class="c_n"></div></div>
	    </div>
	    <!-- 容器标题部分 -->
	    <span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">主数据类型树</span>
			</span>
		</span>
		<div class="c_w">
        	<div class="c_e">
        		<!-- 容器内容 -->
            	<div class="c_content">
            		<div id="masterdata_struct" class="ztree"></div>
                </div>
            </div>
       	</div>
		<div class="c_sw">
        	<div class="c_se"><div class="c_s"></div></div>
        </div>
	</div>
</div>
<div class="ui-layout-center">
	<!--  容器 -->
	<div class="container">
		<div class="c_nw">
	    	<div class="c_ne"><div class="c_n"></div></div>
	    </div>
	    <!-- 容器标题部分 -->
	    <span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">主数据查询</span>
			</span>
		</span>
        <div class="tools-panel"></div>
        <div class="c_w">
            <div class="c_e">
                <div class="c_content">
                    <table id="query">
                        <tr>
                            <td>主数据编码：<input type="text" name="mdCodeValue"/></td>
                            <td>主数据名称：<input type="text" name="mdNameValue"/></td>
                            <td>
                                <a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn">
									<span class="btn-text icon-search">查询</span>
								</span>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="c_sw">
            <div class="c_se">
                <div class="c_s"></div>
            </div>
        </div>
    </div>

    <div class="c_content" style="margin-left: 10px;margin-right: 10px;">
        <div id="grid"></div>
    </div>
</div>

<!-- 内容添加窗口 -->
<div id="edit" style="display: none">
    <form id='fromEdit' method="post" class="checkForm">
        <div class="container">
            <div class="c_nw">
                <div class="c_ne">
                    <div class="c_n"></div>
                </div>
            </div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">维护主数据内容信息</span>
		</span>
	</span>
            <div class="c_w">
                <div class="c_e">
                    <div class="c_content">
                        <table cellspacing="10" style="width: 100%;">
                            <tbody>
                            <input type="hidden" name="pro0" id="pro0"/>
                            <c:if test="${!empty masterDataFieldLic}">
                                <c:forEach items="${masterDataFieldLic}" var="masterDataField">
                                    <c:choose>
                                        <c:when test="${masterDataField.displayable == 'N'}">
                                            <tr>
                                                <td align="right" style="width: 80px;">
                                                    <input type="hidden" name="${masterDataField.proName}" id="${masterDataField.proName}"/>
                                                </td>
                                            </tr>
                                        </c:when>

                                        <c:when test="${!empty masterDataField.valueLic}">
                                            <tr>
                                                <td align="right" style="width: 80px;"><c:out
                                                        value="${masterDataField.displayName}"/>
                                                </td>
                                                <td>
                                                    <select name="${masterDataField.proName}"
                                                            id="${masterDataField.proName}" validate="select">
                                                        <c:forEach items="${masterDataField.valueLic}"
                                                                   var="strVal">
                                                            <option value="${strVal}">${strVal}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:when test="${masterDataField.fieldType == 'datatime'}">
                                            <tr>
                                                <td align="right" style="width: 80px;"><c:out value="${masterDataField.displayName}"/>
                                                </td>
                                                <td><input size="25" type="text" name="${masterDataField.proName}"
                                                           id="${masterDataField.proName}"
                                                           title="${masterDataField.displayName}"
                                                           validate="date|${masterDataField.startLen}-${masterDataField.fieldLength}"
                                                           onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'});"/>
                                                </td>
                                            </tr>
                                        </c:when>

                                        <c:when test="${masterDataField.fieldLength >= 60}">
                                            <tr>
                                                <td align="right" style="width: 80px;"><c:out value="${masterDataField.displayName}"/>
                                                </td>
                                                <td><textarea cols="20" rows="5" name="${masterDataField.proName}"
                                                              id="${masterDataField.proName}"
                                                              title="${masterDataField.displayName}"
                                                              reg="[]"
                                                              validate="${masterDataField.validateType}|${masterDataField.startLen}-${masterDataField.fieldLength}"></textarea>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td align="right" style="width: 80px;"><c:out value="${masterDataField.displayName}"/>
                                                </td>
                                                <td><input size="25" type="text" name="${masterDataField.proName}"
                                                           id="${masterDataField.proName}"
                                                           title="${masterDataField.displayName}"
                                                           reg="[]"
                                                           validate="${masterDataField.validateType}|${masterDataField.startLen}-${masterDataField.fieldLength}"/>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="c_sw">
                <div class="c_se">
                    <div class="c_s"></div>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>