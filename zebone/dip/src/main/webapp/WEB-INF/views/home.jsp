<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>江苏振邦数据集成平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<btp:htmlbase/>
	<link rel="shortcut icon" href="favorite.ico" />
	<link rel="stylesheet" href="skin/${sessionScope.skin}/desktop.css" type="text/css" id="deskTopCss" />
	<link rel="stylesheet" href="skin/${sessionScope.skin}/jcarousel.css" type="text/css" id="jcarouselCss" />
	<link rel="stylesheet" href="skin/${sessionScope.skin}/jquery.window.css" type="text/css" id="windowCss" />
	<style type="text/css">
		#container {
			height: 100%;
			width: 100%;
		}
	</style>
	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.jcarousel.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-window.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
	<script type="text/javascript" src="js/jsp/jsp.desktop.js"></script>
	<script type="text/javascript" src="js/rightmenu.js"></script>
	<script type="text/javascript">
		var windows = [];
		var dragDiv = null;
		$(function() {
			var browserName = navigator.userAgent.toLowerCase();
			var chrome = (/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i
					.test(browserName));
			fixHeight();
			var w = document.body.clientWidth;
			var h = document.body.clientHeight;
			//打开一个功能
			$('.appButton').click(function() {
				clickApp($(this));
			});
		    $('.menuApp').click(function(e){
		    	$.stopBubble(e);
		    	clickApp($(this));
		    });
			arrangeIcons();
			oldOrder();
			document.onmousedown = function(e) {
				var e = document.all ? event.srcElement : e.target;
				if (e.className == 'appButton' || e.className == 'appButton_appIconImg') {
				} else {
					$('.popmenu').hide();
				}
				if (e.id != 'dock_tool_start') {
					setTimeout(function() {
						$('#startMenuContainer').hide();
					}, 200);

				}
			}
			//左侧菜单栏拖拽
			$('#dockItemList').find('.appButton').mousedown(function(e) {
				barDrag(e, $(this));
			});
			//桌面图标拖拽
			$('.appListContainer').find('.appButton').mousedown(function(e) {
				linkDrag(e, $(this));
			});
			if ($.browser.mozilla) {
				$('#dockItemList').find('.appButton_appIconImg').mousedown(function(e) {
					barDrag(e, $(this).parent().parent());
				});
				$('.appListContainer').find('.appButton_appIconImg').mousedown(
						function(e) {
							linkDrag(e, $(this).parent().parent());
						});
			}

			rightMenuEvent();
			bindStartEvent();
			//$('.taskbar_start_menu').height('160');//开始菜单临时高度
			$('#launchPad').click(function(){
				launchMenu();
			});
			$("div#slider").codaSlider({easeTime:600});
			loadSubModule();//加载子菜单
			currentDate();//显示日期
			$('#menuListContainer').click(function(){
				$('.subDir').slideUp(function(){$('.sharpCorner').hide();});
				menuDir.showAll();
			});
			$('#menuCloseA').click(function(e){
				$.stopBubble(e);launchMenu.hide();
			});
			//setTimeout(function(){$('#tip_div').fadeIn();},1000);
			bindTaskBarMove();
			var s='${sessionScope.skin}';
			if(s!=''){
				$('#skinDiv').find('a').removeClass('themeSetting_selected');
				$('#themeSetting_theme_'+s).addClass('themeSetting_selected');
			}
		});
		window.onresize = function() {
			fixHeight();
			arrangeIcons();
		}
	</script>
	<!--[if IE 6]>
		<script type="text/javascript" src="js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script type="text/javascript">
		 DD_belatedPNG.fix('DIV');
		 DD_belatedPNG.fix('A');
		 DD_belatedPNG.fix('SPAN');
		 DD_belatedPNG.fix('INPUT');
		 DD_belatedPNG.fix('IMG');
	</script>
	<![endif]-->
</head>
<body>
	<div id="debug_text"></div>
	<div id="container">
		<!-- leftbar remove-->

		<!-- desktop -->
		<div id="desktopWrapper" style="">
			<div id="toolBar" class="toolBar">
				<div class="toolBarInner">
					<a href="javascript:logout();" class="logout" title="退出"></a>
					<a href="javascript:void(0);" class="sysSetting" title="系统设置"></a>
					<a href="javascript:skinSetting();" class="themeSetting" title="主题设置"></a>
					<span class="userName">${userDetails.fullname}</span>
					<a href="javascript:personalSetting();" class="personalSetting" title="个人设置"></a>
					<span id="clock"></span>
				</div>
			</div>
			<div id="desktopsContainer" class="desktopsContainer" style="top: 0px;">
				<!-- desks -->
				<!--
				<div class="desktopContainer desktop_current" index="0" style="padding:20px 0 0 0;">
				 	<iframe name="" src="" frameborder="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="true"  style="width:100%;height:100%; overflow: hidden;"></iframe>
				</div>
				 -->
				<div class="desktopContainer desktop_current" index="1"	style="left: 0px;" menu="context_menu_desk">
					<div class="appListContainer" customacceptdrop="1" index="2" menu="context_menu_desk"
						style="display: block; width: 100%; height: 294px;opacity: 1;">

						<c:forEach var="shortcutMenu" items="${shortcutMenuList}">
							<c:if test="${shortcutMenu.url eq tempval}">
								<c:set var="modelType" value="dir"/>
							</c:if>
							<c:if test="${shortcutMenu.url ne tempval}">
								<c:set var="modelType" value="app"/>
							</c:if>
							<div appid="${shortcutMenu.moduleId}" type="${modelType}" id="link_${shortcutMenu.moduleId}" img="${shortcutMenu.maxicon}"
									class="appButton" title="${shortcutMenu.name}" menu="context_menu_lnk" order="" _order=""	url="${shortcutMenu.url}">
								<div id="${modelInfo.modelId}_icon_div" class="appButton_appIcon">
									<img alt="${shortcutMenu.name}" src="${shortcutMenu.maxicon}" class="appButton_appIconImg" id="app_${shortcutMenu.moduleId}_img" menu="context_menu_lnk" />
								</div>
								<div class="appButton_appName">
									<div class="appButton_appName_inner" menu="context_menu_lnk">
										${shortcutMenu.name}
									</div>
									<div class="appButton_appName_inner_right" menu="context_menu_lnk"></div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- launchpad -->
					<div id="menuListContainer" class="menuListContainer">
						<div id="menuListBg" class="menuListBg">
							<img id="menuListImg" class="menuListImg" src="./images/wallpaper/${sessionScope.skin}_menu_bg.jpg"/>
						</div>
						<div id="sharpCorner" class="sharpCorner"></div>
						<div class="menuClose">
							<a href="javascript:void(0);" id="menuCloseA" class="menuCloseA" title="关闭功能菜单"></a>
						</div>
						<div class="menuListInner">
							<div id="stripNavL" class="stripNavL"><a id="stripNavLa" class="stripNavLa" href="javascript:void(0);" title="上一页菜单">Left</a></div>
							<div id="stripNavR" class="stripNavR"><a id="stripNavRa" class="stripNavRa" href="javascript:void(0);" title="下一页菜单">Right</a></div>
							<div id="slider" class="stripViewer">
								<div class="panelContainer" style="width: 3500px; left: 0px;">
									<div class="panel">
										<ul class="dock_item_list" customacceptdrop="1">
												<!-- 菜单图标start -->
											<c:forEach items="${moduleList}" var="module">
												<c:if test="${empty module.url}">
													<c:set var="divType" value="dir"/>
												</c:if>
												<c:if test="${not empty module.url}">
													<c:set var="divType" value="app"/>
												</c:if>

												<li id="" appid="${module.moduleId}"  type="${divType}" class="menuApp" title="${module.moduleName}" url="${module.url}" img=".."  menu="context_menu_app" order="${module.orderNo}">
													<div class="menu_appIcon">
														<c:if test="${not empty module.url}">
														<img id="${module.moduleId}_img" alt="${module.moduleName}" src="images/icons/modules/zhgl_large.png" class="menu_appIconImg" onerror="this.src='images/icons/modules/zhgl_large.png'" menu="context_menu_app"/>
														</c:if>
														<c:if test="${empty module.url}">
															<img id="${module.moduleId}_img" alt="${module.moduleName}" src="images/icons/folderIconBG.png" class="menu_dirIconImg" menu="context_menu_app"/>
														</c:if>
													</div>
													<div class="menu_appName">
														<div class="menu_appName_inner" id="">
															<c:out value="${module.moduleName}"/>
														</div>
														<div class="menu_appName_inner_right"></div>
													</div>
												</li>
											</c:forEach>
												<!-- 菜单图标end -->
										</ul>
									</div>
									<div class="panel">panel2</div>
									<div class="panel">panel3</div>
								</div>
							</div>
							<div id="stripNav0" class="stripNav">
								<a href="javascript:void(0);" class="current" title="1" page="1"></a>
								<a href="javascript:void(0);" title="2" page="2"></a>
								<a href="javascript:void(0);" title="3" page="3"></a>
							</div>
							<div class="sliderPoint">
							</div>
						</div>
					</div>
					<div id="tip_div" class="tip_div">
						<div class="tip_div_inner">
							<div class="tip_div_main">
							 欢迎您:${userDetails.fullname}
							</div>
							<div class="tip_div_title"></div>
							<div class="tip_div_close" onclick="$('#tip_div').fadeOut();"></div>
						</div>
					</div>
					<!-- window here-->
				</div>
			</div>
		</div>

		<!-- 背景 -->
		<div id="zoomWallpaperGrid" class="zoomWallpaperGrid" style="position: absolute; z-index: -10; left: 0pt; top: 0pt; overflow: hidden; height: 100%; width: 100%;">
			<img id="zoomWallpaper" class="zoomWallpaper" style="position: absolute; height: 100%; width: 100%; top: 0pt; left: 0pt;" src="images/wallpaper/${sessionScope.skin}.jpg" />
		</div>
		<!-- 任务栏 -->
		<div id="bottomBar" class="bottomBar" style="z-index: 12;">
			<div class="nw">
				<div class="ne">
					<div class="n">

					</div>
				</div>
			</div>
			<div id="taskLeft" class="taskLeft">
				<a id="taskLeftA" class="taskLeftA"></a>
			</div>
			<div id="taskContainer" class="taskContainer">
				<div id="taskContainerInner" class="taskContainerInner" >
				</div>
			</div>
			<div id="taskRight" class="taskRight">
				<a id="taskRightA" class="taskRightA"></a>
			</div>
			<div id="launchContainer" class="launchContainer">
				<a id="launchPad" class="launchPad" >
				</a>
			</div>
		</div>
	</div>
	<div class="popmenu">
		<div class="popmenu_tip popmenu_tip_top"></div>
		<div class="popmenu_header"></div>
		<div class="popmenu_body"></div>
		<div class="popmenu_footer"></div>
	</div>
	<div id="ui_maskLayer" class="ui_maskLayer " style="opacity: 0; z-index: 9000000; display: none;" _olddisplay="block">
		<div class="ui_maskLayerBody" id="ui_maskLayerBody"></div>
	</div>
	<div id="context_menu_app" class="context_menu" style="display: none; z-index: 300045; width: 140px; left: 352px; top: 309px;">
		<div class="context_menu_container ">
			<ul class="context_menu_item_list" id="context_menu_0_body">
				<li class="context_menu_item_container">
					<a title="打开应用" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">打开应用</span>
					</a>
				</li>
				<li class="context_menu_separator_container">
					<span class="context_menu_separator"></span>
				</li>
				<li class="context_menu_item_container">
					<a title="发送到桌面快捷方式" href="javascript:void(0);" 	class="context_menu_item">
						<span class="context_menu_item_text">发送到桌面快捷方式</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="context_menu_desk" class="context_menu" style="display: none; z-index: 300045; width: 140px; left: 352px; top: 309px;">
		<div class="context_menu_container ">
			<ul class="context_menu_item_list" id="context_menu_0_body">
				<li class="context_menu_item_container">
					<a title="主题设置" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">主题设置</span>
					</a>
				</li>
				<li class="context_menu_item_container">
					<a title="系统设置" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">系统设置</span>
					</a>
				</li>
				<li class="context_menu_separator_container">
					<span class="context_menu_separator"></span>
				</li>
				<li class="context_menu_item_container">
					<a title="重载系统" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">重载系统</span>
					</a>
				</li>
				<li class="context_menu_item_container">
					<a title="退出系统" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">退出系统</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="context_menu_lnk" class="context_menu" style="display: none; z-index: 300045; width: 140px; left: 352px; top: 309px;">
		<div class="context_menu_container">
			<ul class="context_menu_item_list" id="context_menu_0_body">
				<li class="context_menu_item_container">
					<a title="打开应用" href="javascript:void(0);" class="context_menu_item">
						<span class="context_menu_item_text">打开应用</span>
					</a>
				</li>
				<li class="context_menu_separator_container">
					<span class="context_menu_separator"></span>
				</li>
				<li class="context_menu_item_container">
					<a title="从桌面删除快捷方式" href="javascript:void(0);" 	class="context_menu_item">
						<span class="context_menu_item_text">删除快捷方式</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="skinDiv" style="display: none;">
		<div class="themeSetting_area">
			<a id="themeSetting_theme_default" href="javascript:void(0);" themeid="theme_blue_glow" name="default" onclick="changeSkin($(this));" class="themeSetting_settingButton  themeSetting_selected"> 
				<div style="background: url(http://0.web.qstatic.com/webqqpic/module/themesetting/images/theme_blue_glow.jpg) no-repeat;" class="themeSetting_settingButton_icon"></div>
				<div class="themeSetting_settingButton_text">
					蓝色主题
				</div>
			</a>
			<a id="themeSetting_theme_green" href="javascript:void(0);" themeid="theme_green" name="green" onclick="changeSkin($(this));" class="themeSetting_settingButton" id="themeSetting_theme_green">
				<div style="background: url(http://0.web.qstatic.com/webqqpic/module/themesetting/images/theme_green.jpg) no-repeat;" class="themeSetting_settingButton_icon"></div>
				<div class="themeSetting_settingButton_text">
					绿色主题
				</div>
			</a>
			<a 	id="themeSetting_theme_grey" href="javascript:void(0);" themeid="theme_metal" name="grey" onclick="changeSkin($(this));" class="themeSetting_settingButton">
				<div style="background: url(http://0.web.qstatic.com/webqqpic/module/themesetting/images/theme_metal.jpg) no-repeat;" class="themeSetting_settingButton_icon"></div>
				<div class="themeSetting_settingButton_text">
					酷炫金属
				</div>
			</a>
		</div>
	</div>
	<div class="taskbar_start_menu_container" id="startMenuContainer">
		<div class="startMenuImg taskbar_start_menu_body" id="taskbar_start_menu_body">
			<div uin="0" class="taskbar_start_menu_selfinfo" id="startMenuSelfInfo">
				<div class="taskbar_start_menu_nick" id="startMenuSelfNick">
					${sessionScope.LOGIN_USER.userName}
				</div>
				<a title="锁定" href="javascript:void(0);" class="startMenuImg startMenuTopControl_lock" cmd="lock">&nbsp;</a>
			</div>
			<ul class="taskbar_start_menu">
				<li cmd="location">
					<a title="切换到旧版" href="btp-index.zb">切换到旧版</a>
				</li>
			</ul>
			<a class="startMenuImg logout_botton" title="注销" href="javascript:logout();"></a>
		</div>
	</div>
</body>
</html>
