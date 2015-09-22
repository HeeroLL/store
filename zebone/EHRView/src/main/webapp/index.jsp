<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器 - 登录</title>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-dialog.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/basic/authorize/login.css"/>
<style type="text/css">
    html, body {
        height: 100%;
        overflow: hidden;
        background-color: #fff;
    }

    .login_bg {
        width: 1024px;
        height: 768px;
        margin: auto auto;
        background: url("resources/img/login/login_bg.jpg") no-repeat #fff;
        position: relative;
    }

    .logo {
        width: 96px;
        height: 38px;
        position: absolute;
        left: 361px;
        top: 180px;
        background: url("resources/img/login/logo.png") no-repeat transparent;
    }

    .login_box {
        position: absolute;
        left: 326px;
        top: 200px;
        width: 371px;
        height: 235px;
        background: url("resources/img/login/login_box_bg.png") no-repeat transparent;
    }

    .login_box_inner {
        position: relative;
    }

    .orgCode, .doctorCode, .cardType, .cardNo, .patientName, .role, .orgCode_bg, .doctorCode_bg, .cardType_bg, .cardNo_bg, .patientName_bg,.role_bg {
        position: absolute;
        width: 180px;
        height: 20px;
        line-height: 20px;
        background-color: transparent;
        border: none;
    }

    .orgCode_bg, .doctorCode_bg, .cardType_bg, .cardNo_bg, .patientName_bg, .role_bg {
        background-color: #fff;
    }

    .orgCode, .orgCode_bg {
        left: 96px;
        top: 27px;
    }

    .doctorCode, .doctorCode_bg {
        left: 96px;
        top: 54px;
    }

    .cardType, .cardType_bg {
        left: 96px;
        top: 81px;
    }

    .cardNo, .cardNo_bg {
        left: 96px;
        top: 108px;
    }

    .patientName, .patientName_bg {
        left: 96px;
        top: 135px;
    }
    .role, .role_bg {
        left: 96px;
        top: 162px;
    }

    #orgCodeTip, #doctorCodeTip, #cardTypeTip, #cardNoTip, #patientNameTip, #roleTip {
        position: absolute;
        width: 180px;
        height: 20px;
        line-height: 20px;
        text-align: center;
    }

    #orgCodeTip {
        left: 96px;
        top: 27px;
    }

    #doctorCodeTip {
        left: 96px;
        top: 54px;
    }

    #cardTypeTip {
        left: 96px;
        top: 81px;
    }

    #cardNoTip {
        left: 96px;
        top: 108px;
    }

    #patientNameTip {
        left: 96px;
        top: 135px;
    }

    #roleTip {
        left: 96px;
        top: 162px;
    }

    #loginBtn, #loginState {
        width: 180px;
        height: 25px;
        display: block;
        line-height: 25px;
        position: absolute;
        left: 96px;
        top: 190px;
        background: url("resources/img/login/login_btn.png") no-repeat transparent;
        cursor: pointer;
        text-align: center;
    }

    #loginState {
        display: none;
    }
</style>

<!--[if IE 6]>
<script type="text/javascript" src="resources/js/ie6/DD_belatedPNG_0.0.8a-min.js"></script>
<script type="text/javascript">
    DD_belatedPNG.fix('DIV');
    DD_belatedPNG.fix('A');
    DD_belatedPNG.fix('SPAN');
    DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>
<script type="text/javascript">
    jQuery.fn.shake = function (setting) {
        var so = jQuery(this);
        var style = so[0].style,
                p = [4, 8, 4, 0, -4, -8, -4, 0],
                fx = function () {
                    style.marginLeft = p.shift() + 'px';
                    if (p.length <= 0) {
                        style.marginLeft = 0;
                        clearInterval(timerId);
                    }
                };
        p = p.concat(p.concat(p));
        timerId = setInterval(fx, 13);
        return this;
    };
    var tipBg = function () {
        if ($.trim($("#orgCode").val()) == '') {
            $('#orgCodeTip').show();
        } else {
            $('#orgCodeTip').hide();
        }
        if ($.trim($("#doctorCode").val()) == '') {
            $('#doctorCodeTip').show();
        } else {
            $('#doctorCodeTip').hide();
        }
        if ($.trim($("#cardType").val()) == '') {
            $('#cardTypeTip').show();
        } else {
            $('#cardTypeTip').hide();
        }
        if ($.trim($("#cardNo").val()) == '') {
            $('#cardNoTip').show();
        } else {
            $('#cardNoTip').hide();
        }
        if ($.trim($("#patientName").val()) == '') {
            $('#patientNameTip').show();
        } else {
            $('#patientNameTip').hide();
        }
        if ($.trim($("#role").val()) == '') {
            $('#roleTip').show();
        } else {
            $('#roleTip').hide();
        }
    };
    var loginStart = function () {
        $('#loginState').show();
        $('#loginBtn').hide();
    };
    var loginEnd = function () {
        $('#loginState').hide();
        $('#loginBtn').show();
    };
    var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
    var loginAjax = function () {
        var orgCode = $.trim($("#orgCode").val());
        var doctorCode = $.trim($("#doctorCode").val());
        var cardType = $.trim($("#cardType").val());
        var cardNo = $.trim($("#cardNo").val());
        var patientName = $.trim($("#patientName").val());
        var role = $.trim($("#role").val());
        if (orgCode == '') {
            JDialog.tip('机构编码不能为空');
            $('#login-box').shake();
            return;
        }
        if (doctorCode == '') {
            JDialog.tip('医生编码不能为空');
            $('#login-box').shake();
            return;
        }

        if (cardType == '') {
            JDialog.tip('患者卡类型不能为空');
            $('#login-box').shake();
            return;
        }

        if (cardNo == '') {
            JDialog.tip('患者卡号不能为空');
            $('#login-box').shake();
            return;
        }

        if (patientName == '') {
            JDialog.tip('患者姓名不能为空');
            $('#login-box').shake();
            return;
        }

        if (role == '') {
            JDialog.tip('角色不能为空');
            $('#login-box').shake();
            return;
        }
//        var data = "{orgCode:'" + orgCode + "',doctorCode:'" + doctorCode + "',cardType:'" + cardType + "',cardNo:'" + cardNo + "',patientName:'" + patientName + "'}";
        loginStart();
        window.location.href = "home/login.zb?orgCode=" + orgCode+"&doctorCode="+doctorCode+"&cardType="+cardType+"&cardNo="+cardNo+"&patientName="+patientName+"&role="+role;
    };
    $(function () {
        $('#orgCode').focus();
        $('#orgCode').click(function () {
            $('#orgCodeTip').hide();
        }).keydown(function () {
                    $('#orgCodeTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#doctorCode').click(function () {
            $('#doctorCodeTip').hide();
        }).keydown(function () {
                    $('#doctorCodeTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#cardType').click(function () {
            $('#cardTypeTip').hide();
        }).keydown(function () {
                    $('#cardTypeTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#cardNo').click(function () {
            $('#cardNoTip').hide();
        }).keydown(function () {
                    $('#cardNoTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#patientName').click(function () {
            $('#patientNameTip').hide();
        }).keydown(function () {
                    $('#patientNameTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#role').click(function () {
            $('#roleTip').hide();
        }).keydown(function () {
                    $('#roleTip').hide();
                }).blur(function () {
                    tipBg();
                });
        $('#loginBtn').click(function () {
            loginAjax();
        });
//            $('#password').bind('keydown', function (event) {
//                if (event.keyCode == "13") {
//                    loginAjax();
//                }
//            });
        tipBg();
    });
</script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/ie6/DD_belatedPNG_0.0.8a-min.js"></script>
<script type="text/javascript">
    DD_belatedPNG.fix('DIV');
    DD_belatedPNG.fix('A');
    DD_belatedPNG.fix('SPAN');
    DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
</head>
<body>
<div class="login_bg">
    <div class="login_box">
        <div id="login-box" class="login_box_inner">
            <div class="orgCode_bg"></div>
            <div class="doctorCode_bg"></div>
            <div class="cardType_bg"></div>
            <div class="cardNo_bg"></div>
            <div class="patientName_bg"></div>
            <div class="role_bg"></div>
            <div id="orgCodeTip">机构编码</div>
            <div id="doctorCodeTip">医生编码</div>
            <div id="cardTypeTip">患者卡类型</div>
            <div id="cardNoTip">患者卡号</div>
            <div id="patientNameTip">患者姓名</div>
            <div id="roleTip">角色</div>
            <input type="text" id="orgCode" name="orgCode" class="orgCode"/>
            <input type="text" id="doctorCode" name="doctorCode" class="doctorCode"/>
            <input type="text" id="cardType" name="cardType" class="cardType"/>
            <input type="text" id="cardNo" name="cardNo" class="cardNo"/>
            <input type="text" id="patientName" name="patientName" class="patientName"/>
            <input type="text" id="role" name="role" class="role">

            <a id="loginBtn">
                <span>登 录</span>
            </a>
            <a id="loginState">
                <span>正在登录...</span>
            </a>
        </div>
    </div>
    <div class="logo"></div>
</div>


<div style="color:red;">
    <c:forEach items="${errors}" var="error">
        <c:out value="${error}"/><br/>
    </c:forEach>
</div>
</body>
</html>