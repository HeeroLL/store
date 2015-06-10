/*调整页面布局*/
var fixHeight=function(){
	//$('#desktopWrapper').height(document.body.clientHeight-60);
	$('.note_body').height($('.noteDiv').height()-12);
	$('#note-place').height($('.noteDiv').height()-$('.noteToolBar').height()-10);
	$('#taskContainer').width(document.body.clientWidth-145);
}
/*排列图标*/
var arrangeIcons=function(){
	var rowHeigt=112;
	var rowWidth=142;
	var cHeight=document.body.clientHeight-$('#bottomBar').height();
	$('.appListContainer').height(cHeight);
	var i=0,j=0,k=1;
	$('.appListContainer').find('.appButton').each(function(){
		if(i*rowHeigt>(cHeight-88)){
			j++;
			i=0;
		}
		$(this).css({left:27+(j*rowWidth),top:i*rowHeigt});
		$(this).attr('order',k);
		k++;
		i++;
	});
}
/*原始排序*/
var oldOrder=function(){
	var o=1;
	$('.appListContainer').find('.appButton').each(function(){
		$(this).attr('_order',o);
		o++;
	});
}
//ajax更新排序号
var updateOrderRequest=function(){
	var params='orders=';
	$('.appListContainer').find('.appButton').each(function(){
		if($(this).attr('_order')!=$(this).attr('order')){
			params+=$(this).attr('appid')+'-'+$(this).attr('order')+',';
		}
	});
	if(params=='orders='){return;}
	$.ajax({
		url: 'upateShortcutMenuOrderNo.zb',
		type: 'POST',
		data: params,
		dataType: 'json',
		success: function(d){
			if(d.success){
				oldOrder();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('update-link-order-error');
		}
	});
}

//添加一项任务
var addTask=function(id,name,img){
	//document.title="addTask:"+id+'-'+name+'-'+img;
	var task='<div id="taskGroup_'+id+'" class="taskGroup taskGroupAnaWidth taskCurrent"><div class="taskItemBox" style="display: block;">';
	task+='<a id="taskItem_1" class="taskItem fistTaskItem" href="javascript:void(0);" appid="3402_3402" tid="3402" title="'+name+'">';
	task+='<div class="taskItemIcon"><img src="'+img+'"/><div class="taskItemIconState"></div></div>';
	task+='<div class="taskItemTxt">';
	task+=name;
	task+='</div></a></div></div>';
	var tD=$(task);
	tD.click(function(){
		activeTask(id);
	});
	$('#taskContainerInner').find('.taskGroup').removeClass('taskCurrent');
	$('#taskContainerInner').append(tD);
	taskAutoMove();
}
//激活某一任务
var activeTask=function(id){
	windows['w_'+id].show();
	windows['w_'+id].active();
	$('#taskContainerInner').find('.taskGroup').removeClass('taskCurrent');
	$('#taskGroup_'+id).addClass('taskCurrent');
}
//移除一项任务
var removeTask=function(id){
	$('#taskGroup_'+id).remove();
	windows['w_'+id]=null;
	taskAutoMove();
}
//状态栏左右移动
var taskMoveRight,taskMoveLeft;
var taskInner;
var bindTaskBarMove=function(){
	taskInner=$('#taskContainerInner');
	$('#taskLeftA').mousedown(function(){
		taskMoveRight=setInterval(function(){
			var m=parseInt(taskInner.css('margin-right'));
			var taskWidth=$('#taskContainerInner .taskGroup').length*114;
			var i=$('#taskContainer').width()-taskWidth;
			if(m>i){
				taskInner.css({'margin-right':m-10});
			}else{
				clearInterval(taskMoveRight);
			}
			taskShowControl();
		},10);
	}).mouseup(function(){
		clearInterval(taskMoveRight);
	});
	
	$('#taskRightA').mousedown(function(){
		taskMoveLeft=setInterval(function(){
			var m=parseInt(taskInner.css('margin-right'));
			if(m<0){
				taskInner.css({'margin-right':m+10});
			}else{
				clearInterval(taskMoveLeft);
			}
			var i=$('#taskContainer').width()-$('#taskContainerInner').width();
			taskShowControl();
		},10);
	}).mouseup(function(){
		clearInterval(taskMoveLeft);
	});
}
//状态栏左右移动
var taskShowControl=function(){
	var taskWidth=$('#taskContainerInner .taskGroup').length*114;
	var i=$('#taskContainer').width()-taskWidth;
	var m=parseInt(taskInner.css('margin-right'));
	if(m<0){
		$('#taskRight').show();
	}else{
		$('#taskRight').hide();
	}
	if(m>i){
		$('#taskLeft').show();
	}else{
		$('#taskLeft').hide();
	}
}
//添加一个任务后自动显示最新任务
var taskAutoMove=function(){
	var taskWidth=$('#taskContainerInner .taskGroup').length*114;
	var t=$('#taskContainerInner');
	var taskBarMargin=parseInt(t.css('margin-right'));
	var taskBarWidth=$('#taskContainer').width();
	if(taskWidth<=taskBarWidth){
		$('#taskRight').hide();$('#taskRight').hide();return;
	}
	if(taskWidth>taskBarWidth){
		$('#taskContainerInner').animate({'margin-right':taskBarWidth-taskWidth},function(){
			taskShowControl();
		});
	}else{
		$('#taskContainerInner').css({'margin-right':0},function(){
			taskShowControl();
		});
	}
}
/*加载悬浮菜单子项*/
var loadMenu = function(el) {
	$('.popmenu_body').html('<img src="./images/loading_cycle.gif"/>');
	var id=el.attr('appid');
	var $menuHtml = '';
	$.ajax({
		url:"btp-loadMenu-sub.zb",
		type: 'POST',
		cache: false,
		dataType: 'json',
		data:{modelPId : id},
		success: function(json){
			if(json&&json.success==true){
			 $('.popmenu_body').html('');
				$.each(json.data, function(i, obj){
					appendSubMenu(obj.modelId, obj.modelName, obj.modelMinicon, obj.modelMaxicon, obj.modelUrl);
					fixPop(el);
				});
	    	}	
		}
	});
};
function appendSubMenu(id, name, minicon, maxicon, url){
	var html='';
	html+='<div appid="'+id+'"';  
	html+='img="'+maxicon+'"'; 
	html+='class="appButton"';
	html+='title="'+name+'"'; 
	html+='url="'+url+'" menu="context_menu_app" >';
	html+='<div class="appButton_appIcon ">';
	html+='<img alt="'+name+'" src="'+maxicon+'" class="appButton_appIconImg" menu="context_menu_app"/>';
	html+='</div>';
	html+='<div class="appButton_appName"><div class="appButton_appName_inner">';
	html+=name;		
	html+='</div><div class="appButton_appName_inner_right"></div></div>';
	html+='</div>';
	var $html=$(html);
	$html.click(function(){
		clickApp($(this));
		$('.popmenu').hide();
	});
	$html.mousedown(function(e){
		barDrag(e,$(this));
	});
	$('.popmenu_body').append($html);
}
//调整菜单位置
var fixPop=function(el){
	var offset=el.offset();
	if((offset.top+$('.popmenu').height())>document.body.clientHeight){
		$('.popmenu').find('.popmenu_tip').removeClass('popmenu_tip_top').addClass('popmenu_tip_btm');
		$('.popmenu').css({top:offset.top-($('.popmenu').height()-75)});
	}else{
		$('.popmenu').find('.popmenu_tip').addClass('popmenu_tip_top').removeClass('popmenu_tip_btm');
	}
}
//图标点击事件***********************************************************
var clickApp=function(el){
	var me=el;
	if(me.attr('type')=='desk'){
			gotoDesk(me.attr('index'));
	}else if(me.attr('type')=='dir'){
		//显示文件夹
		//$('.popmenu').show();
		//var offset=me.offset();
		//$('.popmenu').css({left:offset.left+me.width(),top:offset.top});
		/*var sbc=$('#slider').find('.subDirCurrent');
		if(sbc.length!=0){
			sbc.slideUp(function(){$('#sharpCorner').hide();}).removeClass('subDirCurrent');
			menuDir.showAll();
			return;
		}*/
		$('.subDir').slideUp();
		var s=$('#sub_'+me.attr('appid'));
		if(s.find('dt').length==0){
			return;
		}
		if(s.is(":hidden")){
			$('#sub_'+me.attr('appid')).slideDown().addClass('subDirCurrent');
			var offset=me.offset();
			$('#sharpCorner').show().css({left:offset.left+me.width()/2-$('#sharpCorner').width()/2,top:offset.top+me.height()-14});;
			menuDir.hideAll();
		}else{
			$('#sub_'+me.attr('appid')).slideUp(function(){$('#sharpCorner').hide();}).removeClass('subDirCurrent');;
			menuDir.showAll();
		}
	}else{
		launchMenu.hide();
		if(windows['w_'+me.attr('appid')]){
			activeTask(me.attr('appid'));
		}else{
			//打开一个功能
			iconExplosion(me);
		}
	}
}
var openAWindow=function(me){
	var wWidth=$('#bottomBar').find('.n').width();
	windows['w_'+me.attr('appid')]=new JWindow({
		id:me.attr('appid'),
		title:me.attr('title'),
		width:wWidth,
		height:document.body.clientHeight-$('#bottomBar').height()-10,
		top:'0px',
		left:(document.body.clientWidth-wWidth)/2-10,
		url:me.attr('url'),
		appendTo:$('.desktopContainer'),
		onMaximize:function(){
			$('#bottomBar').css('z-index','10');
		},
		onRestore:function(){
			$('#bottomBar').css('z-index','12');
		},
		onClose:function(){
			removeTask(me.attr('appid'));
		}
	});
	windows['w_'+me.attr('appid')].active();
	//添加一个任务并激活，如果存在就激活
	addTask(me.attr('appid'),me.attr('title'),me.attr('img'));
}
//图标爆炸效果
var iconExplosion=function(o){
	var offset=o.find('img').offset();
	var ico=$('<img src="'+o.attr('img')+'" style="position: absolute;width:48px;height:48px;left:'+offset.left+'px;top:'+offset.top+'px;z-index:1000;"/>');
	ico.appendTo(document.body);
	var width=100;
	ico.animate({width:width,height:width,top:offset.top-(width-48)/2,left:offset.left-(width-48)/2,opacity:0},700,function(){
		openAWindow(o);
		$(this).remove();
	});
}
//注销
var quit=function(){
	if(window.confirm('确定退出系统?')){
		$('#login').fadeIn();
	}
}
//切换桌面
var gotoDesk=function(index){
	$('#desktopsContainer').find('.desktop_current').animate({left: '-2000'}, 600).removeClass('desktop_current').css({left:2000});
	var desks=$('#desktopsContainer').find('.desktopContainer');
	desks.each(function(){
		if($(this).attr('index')==index){
			$(this).animate({left: '0'}, 600).addClass('desktop_current');		
		}
	});
}
//绑定右键事件
var rightMenuEvent=function(){
	$('#context_menu_app').find(".context_menu_item:eq(0)").click(function(){
		//打开应用
		clickApp(convertObj(_rightObj));
	});
	$('#context_menu_app').find(".context_menu_item:eq(1)").click(function(){
		//发送到桌面
		addALinkRequest(convertObj(_rightObj));
	});
	$('#context_menu_lnk').find(".context_menu_item:eq(0)").click(function(){
		//打开应用
		clickApp(convertObj(_rightObj));
	});
	$('#context_menu_lnk').find(".context_menu_item:eq(1)").click(function(){
		//删除应用
		deleteAlink(convertObj(_rightObj));
	});
	$('#context_menu_desk').find(".context_menu_item:eq(0)").click(function(){
		//主题设置
		skinSetting();
	});
	$('#context_menu_desk').find(".context_menu_item:eq(1)").click(function(){
		//系统设置
		clickApp($('#system_setting_a'));
	});
	$('#context_menu_desk').find(".context_menu_item:eq(2)").click(function(){
		//重载系统
		window.location.reload();
	});
	$('#context_menu_desk').find(".context_menu_item:eq(3)").click(function(){
		//退出系统
		logout();
	});
}
//转换格式
var convertObj=function(o){
	var obj=null;
	if(o.getAttribute('class')=='menu_dirIconImg'||o.getAttribute('class')=='appButton_appIconImg'||o.getAttribute('class')=='appButton_appName_inner'||o.getAttribute('class')=='appButton_appName_inner_right'){
		obj=$(o).parent().parent();
	}else if(o.getAttribute('class')=='appButton'||o.getAttribute('class')=='menuApp'){
		obj=$(o);
	}
	if(obj==null){
		alert('转换对象为空');
	}
	return obj;
}
//传递div属性
var copyDivPro=function(from,to){
	to.attr('appid',from.attr('appid'));
	to.attr('title',from.attr('title'));
	to.attr('url',from.attr('url'));
	to.attr('type',from.attr('type'));
	to.attr('img',from.attr('img'));
	return to;
}
//添加linkajax请求
var addALinkRequest=function(obj,o){
	if(obj==null){return;}
	if($('#link_'+obj.attr('appid')).length!=0){
		alert('快捷方式已经存在!');
		return;
	}
	var order;
	if(o&&o!=''){
		order=o;
	}else{
		order=$('.desktop_current').find('.appButton').length;
	}
	$.ajax({
		url: 'addShortcutMenu.zb',
		type: 'POST',
		data: {moduleId:obj.attr('appid'),orderNo:order+1},
		dataType: 'json',
		success: function(d){
			if(d.success){
				addAlink(obj);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('error');
		}
	});
}
//通过moduId和order添加一个快捷方式，没有回调函数
var addALinkByOrder=function(moduId,linkOrder){
	$.ajax({
		url: 'addShortcutMenu.zb',
		type: 'POST',
		data: {moduleId:moduId,orderNo:linkOrder},
		dataType: 'json',
		success: function(d){
			if(d.success){
				//addAlink(obj);
				arrangeIcons();//排列图标
				updateOrderRequest();//更新排序号
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('error');
		}
	});
}
//添加link
var addAlink=function(o){
	var me=o;
	var linkDiv=$('<div id=link_'+me.attr('appid')+' class="appButton" "menu="context_menu_lnk" style="position: absolute;">'+me.html()+'</div>');
	linkDiv=copyDivPro(me,linkDiv);
	linkDiv.find('.appButton_appIconImg').attr('menu','context_menu_lnk');
	linkDiv.click(function(){
		clickApp($(this));
	});
	linkDiv.mousedown(function(e){
		linkDrag(e,$(this));
	});
	$('.desktop_current').find('.appListContainer').append(linkDiv);
	arrangeIcons();
}
//删除link
var deleteAlink=function(obj){
	$.ajax({
		url: 'deleteShortcutMenuOrder.zb',
		type: 'POST',
		data: {moduleId:obj.attr('appid')},
		dataType: 'json',
		success: function(d){
			if(d.success){
				$('#link_'+obj.attr('appid')).remove();
				arrangeIcons();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('error');
		}
	});
}
//左侧工具栏图标拖拽
var barDrag=function(e,el){
	if(e.button==2)return;
		var me=el;
		var offset=me.offset();//var el=e.srcElement?e.srcElement:e.target;
		var flag=true;
			e.preventDefault();
			$(document).bind("mouseup",function(ev){
				$(this).unbind("mousemove");
				$(this).unbind("mouseup");
				if(dragDiv!=null){
					$('#ui_maskLayer').hide();
					dragDiv.hide();
					if(ev.pageX>$('#leftBar').width()){
						//document.title="拖动到桌面";
						$('.appButton').bind("mouseover",function(){
							if($('#link_'+dragDiv.attr('appid')).length==0){
								dragDiv.insertBefore($(this)).show().attr('id','link_'+dragDiv.attr('appid')).attr('_order','-1').css({'z-index':''}).click(function(){clickApp($(this));}).mousedown(function(e){
									linkDrag(e,$(this));
								});
								addALinkByOrder(dragDiv.attr('appid'),$(this).attr('order'));//ajax
								$('.appButton').unbind("mouseover");
								$('.desktop_current').unbind("mouseover");
								dragDiv=null;
							}else{
								alert("快捷方式已存在！");
								$('.appButton').unbind("mouseover");
								dragDiv=null;
							}
						});
						if(dragDiv!=null){
							$('.desktop_current').bind("mouseover",function(){
								var desk=$(this).find('.appListContainer');
								if(dragDiv!=null){
								  	if($('#link_'+dragDiv.attr('appid')).length==0){
								  		addALinkRequest(dragDiv);
								  		$('.appButton').unbind("mouseover");
								  		$('.desktop_current').unbind("mouseover");
										dragDiv=null;
								  	}else{
										$('.appButton').unbind("mouseover");
										$('.desktop_current').unbind("mouseover");
										dragDiv=null;
										alert("快捷方式已存在！！");
									}
								}
							});
						}
					}else{
						//document.title="没有拖动";
						dragDiv.remove();
						dragDiv=null;
					}
				}
				
			});
		$(document).bind("mousemove",function(ev){
			//ev.stopImmediatePropagation();
		    ev.preventDefault();
		    if(dragDiv==null){
		    	dragDiv=$('<div class="appButton" style="z-index: 9999999;cursor:pointer;position: absolute;">'+me.html()+'</div>');
		    	dragDiv=copyDivPro(me,dragDiv);
		    }else{
		    	if(flag){
				dragDiv.appendTo($(document.body)).css({left:offset.left,top:offset.top});
				$('#ui_maskLayer').show();
				}
				flag=false;
				dragDiv.css({left:ev.pageX-30,top:ev.pageY-30});
		    }
			
		});
}
//桌面图标拖拽
var linkDrag=function(e,el){
	if(e.button==2)return;
	var me=el;
	var offset=me.offset();
	var flag=true;
	e.preventDefault();
	$(document).bind("mouseup",function(ev){
		$(this).unbind("mousemove");
		$(this).unbind("mouseup");
		if(dragDiv==null){
			return;
		}
		dragDiv.hide();
		if(dragDiv!=null){
			$('#ui_maskLayer').hide();
			if(ev.pageX>$('#leftBar').width()){
				$('.appButton').bind("mouseover",function(){
					if(dragDiv!=null){
						if(dragDiv.attr('appid')!=$(this).attr('appid')){
						changePosition($('#link_'+dragDiv.attr('appid')),$(this));//前，后
						updateOrderRequest();
						$('.appButton').unbind("mouseover");
						$('.desktop_current').unbind("mouseover");
						dragDiv.remove();
						dragDiv=null;
						}else{
							$('.appButton').unbind("mouseover");
							$('.desktop_current').unbind("mouseover");
							dragDiv.remove();
							dragDiv=null;
							//$('#debug_text').append('appButton-mouseover保持不变');
						}
					}
				});
				if(dragDiv!=null){
					//拖放到桌面其他位置
					$('.desktop_current').bind("mouseover",function(){
						if(dragDiv!=null){
							  var b=$('.desktop_current').find('.appListContainer').find('.appButton:last');
							  if(b.attr('appid')!=dragDiv.attr('appid')){
							  	$('#link_'+dragDiv.attr('appid')).insertAfter(b);
							  	  //$('#debug_text').append('插入最后');
							  	  $('.appButton').unbind("mouseover");
							  	  $('.desktop_current').unbind("mouseover");
								  arrangeIcons();
								  updateOrderRequest();
								  dragDiv.remove();
							  	  dragDiv=null;
							  }else{
							  	dragDiv.remove();
							    dragDiv=null;
							  	//$('#debug_text').append('最后一个保持不变');
							  }
						  }
					});
				}
			}else{
				dragDiv.remove();
				dragDiv=null;
			}
		}
		
	});
	$(document).bind("mousemove",function(ev){
		//ev.stopImmediatePropagation();
	    ev.preventDefault();
	    if(dragDiv==null){
	    		dragDiv=$('<div class="appButton" style="z-index: 9999999;cursor:pointer;position: absolute;">'+me.html()+'</div>');
	    		dragDiv=copyDivPro(me,dragDiv);
	    		$('#ui_maskLayer').show();
	    		dragDiv.appendTo($(document.body)).css({left:offset.left,top:offset.top});
	    		//$('#debug_text').append('new dragdiv:'+dragDiv.attr('appid'));
	    }else{
			dragDiv.css({left:ev.pageX-30,top:ev.pageY-30});
			////$('#debug_text').append('left:'+dragDiv.css('left')+'-top:'+dragDiv.css('top')+'-display:'+dragDiv.css('display')+'\n'+dragDiv.css('z-index'));
	    }
	   
		////$('#debug_text').append('<div class="appButton" style="z-index: 9999999;cursor:pointer;position: absolute;">\n'+me.html()+'\n</div>'); 
	});
}
//图标互换位置
var changePosition=function(a,b){
	a.insertBefore(b);
	arrangeIcons();
}
//显示菜单
var launchMenu=function(){
	if($('#menuListContainer').is(':hidden')){
		launchMenu.show();
		$('#bottomBar').css('z-index','10');
	}else{
		launchMenu.hide();
		$('#bottomBar').css('z-index','12');
	}
}
launchMenu.show=function(){
	$('#menuListContainer').fadeIn().css({height:document.body.clientHeight});
	$('#bottomBar').css('z-index','10');
}
launchMenu.hide=function(){
	$('#menuListContainer').fadeOut();
	$('#bottomBar').css('z-index','12');
}
//个人设置
var personalSetting=function(){
	windows['skin']=new JWindow({
		id:'skin_window',
		title:'个人设置',
		width:984,
		height:478,
		top:(document.body.clientHeight-478)/2,
		left:(document.body.clientWidth-984)/2,
		resizable:true,
		maximizable:false,
		minimizable:false,
		url:'btp/personnel/oneselfSetting.zb',
		appendTo:$('.desktopContainer'),
		onClose:function(){
			//保存
		}
	});
	windows['skin'].active();
}
//系统设置
var systemSetting=function(){
}
//主题设置
var skinSetting=function(){
	windows['skin']=new JWindow({
		id:'skin_window',
		title:'主题设置',
		width:582,
		height:180,
		top:(document.body.clientHeight-180)/2,
		left:(document.body.clientWidth-582)/2,
		resizable:true,
		maximizable:false,
		minimizable:false,
		content:$('#skinDiv').html(),
		appendTo:$('.desktopContainer'),
		onClose:function(){
			//保存
		}
	});
	windows['skin'].active();
}
//更换主题
var changeSkin=function(o){
	var url=o.attr('name');
	o.parent().find('a').removeClass('themeSetting_selected');
	o.addClass('themeSetting_selected');
	$('#deskTopCss').attr('href','skin/'+url+'/desktop.css');
	$('#windowCss').attr('href','skin/'+url+'/jquery.window.css');
	$('#zoomWallpaper').attr('src','./images/wallpaper/'+url+'.jpg');
	$('#menuListImg').attr('src','./images/wallpaper/'+url+'_menu_bg.jpg');
	for(var i=0;i<window.frames.length;i++){
		if(window.frames[i].className='iframeApp'){
			var f=$(window.frames[i].document);
			f.find('#common-css').attr('href','skin/'+url+'/main.css');
			f.find('#grid-css').attr('href','skin/'+url+'/jquery-grid.css');
			f.find('#dialog-css').attr('href','skin/'+url+'/jquery-dialog.css');
			f.find('#checkform-css').attr('href','skin/'+url+'/jquery.checkForm.css');
		}
	}
	$.ajax({
		url: 'btp/personnel/changeSkin.zb',
		data: 'skin='+url,
		type: 'POST',
		cache: false,
		dataType: 'json',
		success: function(data){	
	 		if(data.success){
	 		}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('服务器异常，主题更换失败');
		}
	});
}
//菜单
var bindStartEvent=function(){
	$('.dock_tool_start').click(function(){
		var o=$(this).offset();
		$('#startMenuContainer').css({top:o.top-$('#startMenuContainer').height()+35}).show();
	});
}
//注销帐户
var logout = function(){
	if(window.confirm("您确定要关闭系统吗？")){
		$.ajax({
			url: 'logout.zb',
			type: 'POST',
			cache: false,
			dataType: 'json',
			success: function(data){	
		 		if(data.success!=undefined&&data.success){
		 			window.location.href="./";
		 		}else{
		 			alert("帐户注销失败。");
		 			window.location.href="./";
		 		}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('服务器出现异常，帐户注销失败。');
				window.location.href="./";
			}
		});
	}
}
//关闭系统
var closeSystem = function(){
 	if(window.confirm("您确定要关闭系统吗？")){
		$.ajax({
			url: 'logout.zb',
			type: 'POST',
			cache: false,
			dataType: 'json',
			success: function(data){	
		 		if(data.success){
		 			try{
		 				window.close();
		 			}catch(e){
		 				window.close();
		 			}
		 			
		 		}else{
		 			//注销失败
		 		}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('服务器异常，帐户注销失败');
				window.close();
			}
		});
 	}
 };
 //打开一个窗口
var viewMore=function(id, title, icon, url) {
	var t_url = url.toString();
	if (t_url.indexOf("?") >= 0) {
		url = url + "&id=" + id;
	} else {
		url = url + "?id=" + id;
	}
	if(windows['w_'+id]){
		activeTask(id);
	}else{
		//打开一个窗口
		windows['w_'+id]=new JWindow({
			id:id,
			title:title,
			width:800,
			height:600,
			top:120+$('.window').length*25,
			left:150+$('.window').length*20,
			url:url,
			appendTo:$('.desktopContainer'),
			onMaximize:function(){
				$('#bottomBar').css('z-index','10');
			},
			onRestore:function(){
				$('#bottomBar').css('z-index','12');
			},
			onClose:function(){
				removeTask(id);
			}
		});
		windows['w_'+id].active();
		//添加一个任务并激活，如果存在就激活
		addTask(id,title,'images/icons/modules/06.png');
	}
}
//菜单滑动
jQuery.fn.codaSlider = function(settings) {
	 settings = jQuery.extend({
     easeFunc: "expoinout",
     easeTime: 750,
     toolTip: false
  }, settings);
    var container = jQuery(this);
    var panelWidth = container.find("div.panel").width();
    var panelCount = container.find("div.panel").size();
    var stripViewerWidth = panelWidth*panelCount;
    container.find("div.panelContainer").css("width" , stripViewerWidth);
    var cPanel = 1;
    var ca=container.parent().find('.stripNav'); 
  	container.parent().find('.stripNavLa').click(function(e){
		$.stopBubble(e);
		var cnt;
		cPanel -= 1;
		cnt = - (panelWidth*(cPanel-1));
		jQuery(this).parent().parent().find("div.panelContainer").animate({ left: cnt}, settings.easeTime);
		ca.find('a').removeClass('current');
		ca.find('a:eq('+(cPanel-1)+')').addClass('current');
		toggle();
	});
	container.parent().find('.stripNavRa').click(function(e){
		$.stopBubble(e);
		var cnt;
		if(cPanel==jQuery(this).parent().parent().find('.panel').length){return;}
		cPanel+=1;
		var cnt=-panelWidth*(cPanel-1);
		jQuery(this).parent().parent().find("div.panelContainer").animate({ left: cnt}, settings.easeTime);
		ca.find('a').removeClass('current');
		ca.find('a:eq('+(cPanel-1)+')').addClass('current');
		toggle();
	});
	container.parent().find('.stripNav a').click(function(){
		cPanel=parseInt($(this).attr('page'));
		cnt = - (panelWidth*(cPanel-1));
		jQuery(this).parent().parent().find("div.panelContainer").animate({ left: cnt}, settings.easeTime);
		ca.find('a').removeClass('current');
		$(this).addClass('current');
		toggle();
	});
	var toggle=function(){
		if(cPanel==1){
			container.parent().find('.stripNavLa').hide();
		}
		if(cPanel==ca.find('a').length){
			container.parent().find('.stripNavRa').hide();
		}
		if(cPanel>1){
			container.parent().find('.stripNavLa').show();
		}
		if(cPanel<ca.find('a').length){
			container.parent().find('.stripNavRa').show();
		}
		
	}
	toggle();
}
//查询出有子模块的顶级模块，查询出下级模块。
var loadSubModule=function(){
	$("#slider").find("li").each(function(){
		if($(this).attr('type')=='dir'){
			loadSubModuleAjax($(this),$(this).attr('appid'));
		}
	});
}
var loadSubModuleAjax=function(obj,pId){
	var url = "home/getModulesByParentId.zb";
	$.getJSON(url,{moduleId:pId},function(data){
		if(data){
			var subLi=$('<dt id="sub_'+obj.attr('appid')+'" class="subDir"><dl class="subUl"></dl></dt>');
			var n=(parseInt(obj.attr('order'))%8+1)*8;
			if(obj.parent().find('li').length<n){
				subLi.insertAfter(obj.parent().find('li:last'));
			}else{
				subLi.insertAfter(obj.parent().find('li:eq('+(n-1)+')'));
			}
			var subDir=$('#sub_'+obj.attr('appid')).find('.subUl');
			$.each(data,function(n,module){
				if(n<4){
					obj.find('.menu_appIcon').append('<img style="" src="'+module.maxicon+'" class="menu_subApp"/>');
				}
				subDir.append('<dt appid="'+module.moduleId+'"  type="app" class="menuApp" title="'+module.moduleName+'" url="'+module.url+'" img="'+module.maxicon+'"  menu="context_menu_app"><div class="menu_appIcon"><img alt="'+module.maxicon+'" src="'+module.maxicon+'" class="menu_dirIconImg" menu="context_menu_app"/></div><div class="menu_appName"><div class="menu_appName_inner">'+module.moduleName+'</div><div class="menu_appName_inner_right"></div></div></dt>');
			});
			
			subDir.find('dt').click(function(e){
				//$.stopBubble(e);
				clickApp($(this));
				//launchMenu.hide();
			});	
		}
	});
}
//当前日期
var currentDate=function(){
	var objDate=new Date();//创建一个日期对象表示当前时间
	var year=objDate.getFullYear();
	var month=objDate.getMonth()+1;//getMonth返回的月份是从0开始的，因此要加1
	var date=objDate.getDate();
	var day=objDate.getDay();
	switch(day){
	    case 0:day="星期日"; break;
	    case 1:day="星期一"; break;
	    case 2:day="星期二"; break;
	    case 3:day="星期三"; break;
	    case 4:day="星期四"; break;
	    case 5:day="星期五"; break;
	    case 6:day="星期六"; break;
	}
$(".tip_div_main").append("<div>今天是:"+year+"年"+month+"月"+date+"日&nbsp; "+day+"</div>");
$('#clock').html(year+"年"+month+"月"+date+"日"+day);
}
//菜单文件夹显示控制
var menuDir={
	hideAll:function(){
		$('.dock_item_list>.menuApp').css({opacity:0.5});
		$('#stripNavL').hide();$('#stripNavR').hide();$('#stripNav0').hide();
	},
	showAll:function(){
		$('.dock_item_list>.menuApp').css({opacity:1});
		$('#stripNavL').show();$('#stripNavR').show();$('#stripNav0').show();
	}
}