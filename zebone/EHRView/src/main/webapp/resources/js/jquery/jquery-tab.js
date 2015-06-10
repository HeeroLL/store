/**
 * JQuery Tab, version 2.0
   选项卡组件
 * Example: 
 * var tab = new JTab(options);
 * Or
 * var tab = new JTab({
 * 		listeners: {...【回调函数】}
 * });
 * 方法说明：
 * 		addTab(opt); 加入一个选项卡，如果该选项卡ID已存在，那么直接显示该选项卡
 * 			opt参数 
 * 				[id]选项卡的唯一标识，不可重复
 * 				[title] 选项卡标题
 * 				[closable] 是否可以关闭选项卡
 * 				[icon] 选项卡图标
 *				[html] 选项卡内容html
 *				[url] 打开页面的url
 * 		setActiveTab(tabId); 显示选项卡
 * 			[tabId] 选项卡ID
 * 回调函数说明
 * 		beforeClickTab(tabId, tabTitle); 点击选项卡前触发
 * 			[tabId] 选项卡ID
 * 			[tabTitle] 选项卡标题
 * 		afterClickTab(tabId, tabTitle); 点击选项卡后触发
 * 			[tabId] 选项卡ID
 * 			[tabTitle] 选项卡标题
 * 		beforeCloseTab(tabId, tabTitle); 关闭选项卡前触发
 * 			[tabId] 选项卡ID
 * 			[tabTitle] 选项卡标题
 * 		afterCloseTab(tabId, tabTitle); 关闭选项卡后触发
 * 			[tabId] 选项卡ID
 * 			[tabTitle] 选项卡标题
 Jtab options选项：
 width：tab的宽度,默认充满父容器
 height:tab的高度,默认充满父容器
 renderTo:tab附加到哪个元素中，为空就为body
 header:是否显示tabHeader部分的背景
 */
 JTab = function(opt){
 	 this.opt = {
    	id:'jtab-'+Math.floor(Math.random()*0x100000).toString(16),
    	width:'auto',
		height:'100%',
		renderTo:$(document.body),
		header:true
    };
    this.moveInterval=null;
    this.tabWidth=0;
    $.extend(this.opt,opt);
    var o=this.opt;
 	if($('#'+o.id).length==0){
 		var tabHTML = "<div id='"+o.id+"' class='jtab'>" +
			"<div id='"+o.id+"-bar-outer' class='tabBar'><ul id='"+o.id+"-bar' class='tabs-nav'></ul></div>"+
			"<div id='"+o.id+"-body' class='tabBody'></div>" +
			"</div>";
		o.renderTo.append(tabHTML);
		this.tab=$('#'+o.id);
		this.tabBar=$('#'+o.id+'-bar');
		this.tabBarOuter=$('#'+o.id+'-bar-outer');
		this.tabBody=$('#'+o.id+'-body');
		this.tabBarOuter.append('<a id="'+o.id+'-left"  class="scroll-left" href="javascript:void(0);"></a><a id="'+o.id+'-right" class="scroll-right" href="javascript:void(0);"></a><a id="'+o.id+'-menu" class="scroll-menu" href="javascript:void(0);"></a>');
 		this.scrollLeft=$('#'+o.id+'-left');
 		this.scrollRight=$('#'+o.id+'-right');
 		this.menuButton=$('#'+o.id+'-menu');
 		this.tabBody.append('<div id="'+o.id+'-menulist" class="context_menu"  style="right:0;top:0;"><div class="context_menu_container"><ul class="context_menu_item_list"></ul></div></div>');
 		this.menuList=$('#'+o.id+'-menulist');
 	}
 	var me=this;
 	if(o.header){
 		me.tabBarOuter.addClass('tabBarBg');
 	}
	if(o.height=='100%'){
		//未指定tab高度,tab充满父容器
		this.tabBody.css('height',o.renderTo.outerHeight()-me.tabBarOuter.outerHeight());
		$(window).resize(function(){
	 		if(me.tabBar.is(':visible')){
				me.tabBody.css('height',o.renderTo.outerHeight()-me.tabBarOuter.outerHeight());//底部div自适应高度
				me.setScroll();
			}else{
				me.tabBody.css('height','100%');
			}
	 	});
 	}else{
 		//定义了tab高度
 		this.tab.addClass('tabBorder');
 		this.tab.css({width:o.width,height:o.height});
 		this.tabBody.css('height',o.height-this.tabBarOuter.outerHeight()-2);
 	}
 	me.registerScroll();
 };
 JTab.prototype = {
 	//添加一个Tab，如果Tab存在，就直接显示
 	addTab: function(opt){
 		var tabId=this.opt.id;
 		var options={
 			id:tabId+'-body-'+Math.floor(Math.random()*0x100000).toString(16),
 			closable:true,
 			disabled:false,
 		};
 		$.extend(options,opt);
 		if($("#"+options.id).length==0){
	 		this.tabBar.append("<li id='"+options.id+"' show='0' able='1' title='"+options.title+"'><a  href='javascript:void(0);'><span>"+(options.title.length>11?options.title.substr(0,11)+"...":options.title)+"</span></a></li>");
	 		if(options.icon){
				var div='<div style="background:url(\''+options.icon+'\')" class="tab_icon"></div>';
				$("#"+options.id).append(div);
		 	}
	 		if(options.closable){
		 		$("#"+options.id).append('<div class="closeTabImg"></div>');
		 	}
		 	if(options.url){
		 		var iframe='<iframe src="'+options.url+'" frameborder="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" style="width:100%;height:100%;"></iframe>';
		 		this.tabBody.append("<div id='"+options.id+"-main' class='tab-body-child'>"+iframe+"</div>");
		 	}else{
		 		this.tabBody.append("<div id='"+options.id+"-main' class='tab-body-child''>"+(options.html?options.html:"&nbsp;")+"</div>");
		 	}
	 		if(options.disabled){
				$("#"+options.id).attr('able', '0');
				$("#"+options.id).addClass("disabled");
	 		}	 		
	 		this.registerEvent(options.id);
 		}
 		if(!options.disabled){
 			this.setActiveTab(options.id);
 		}
 		this.tabWidth=this.getTabsWidth();
 		this.scrollToRight();
 		this.addMenuItem(options.id,options.title);
 	},
 	addMenuItem:function(id,title){
 		var me=this;
 		me.menuList.hide();
 		me.menuList.find('ul').append('<li id="'+id+'-menu" class="context_menu_item_container"><a href="javascript:void(0);" class="context_menu_item"><span class="context_menu_item_text">'+title+'</span></a></li>');
 		$('#'+id+'-menu').click(function(){
 			me.menuList.hide();
 			me.scrollToTab(id);
 		});
 	},
 	removeMenuItem:function(id){
 		$('#'+id+'-menu').remove();
 	},
 	//获取所有tab的宽度
 	getTabsWidth:function(){
 		var t=this.tabBar.find('li');
 		var w=0;
 		for(var i=0;i<t.length;i++){
 			w+=$(t[i]).outerWidth();
 		}
 		return w;
 	},
 	//显示最右tab
 	scrollToRight:function(){
 		var left=this.tabWidth-this.tabBarOuter.outerWidth()+this.menuButton.width();
 		if(left>0){
 			this.tabBar.animate({left:-left});
 			this.scrollLeft.show();
 			this.scrollRight.hide();
 			this.menuButton.show();
 		}else{
 			this.tabBar.css({left:0});
 			this.scrollLeft.hide();
 			this.scrollRight.hide();
 			this.menuButton.hide();
 		}
 	},
 	//当tab容器宽度改变时,设置左右滚动按钮的显示
 	setScroll:function(){
 		if(this.tabBarOuter.outerWidth()-this.tabWidth>=0){
 			this.tabBar.css({left:0});
 			this.scrollLeft.hide();
 			this.scrollRight.hide();
 			this.menuButton.hide();
 		}else{
 			if(parseInt(this.tabBar.css('left'))!=0){
 				this.scrollLeft.show();
 			}
 			this.scrollRight.show();
 			this.menuButton.show();
 		}
 	},
 	//注册关闭事件
 	registerEvent: function(tab){
 		var me = this;
		$("#"+tab).mousedown(function(e){
			e.preventDefault();
			//setTab前监听			
			if(me.opt.listeners&&me.opt.listeners.beforeClickTab) {
				if(me.opt.listeners.beforeClickTab($(this).attr('id'), $(this).text(), me.getActiveTab())==1){
					return;
				}			
			}
 			me.setActiveTab($(this).attr('id'));
 			//setTab后监听
 			if(me.opt.listeners&&me.opt.listeners.afterClickTab) me.opt.listeners.afterClickTab($(this).attr('id'), $(this).text(), e);
 			/*拖拽事件
 			var me=$(this);
 			var offset = me.offset();
 			var startX=e.clientX;
 			$(document).bind("mousemove",function(ev)  
            {  
            	ev.preventDefault();
            	object.createPlaceholder(me); 
                var _x = ev.clientX - startX+offset.left;
                if(_x<0) _x = 0;
                me.css({position:'absolute',left:_x+"px",zIndex:1000}); 
                object.mouseCapture(ev);
            });
            $(document).mouseup(function(){
				$(this).unbind("mousemove"); 
				me.css({left:'auto',position:'',zIndex:0});
				object.changePosition(me);   
			});
			*/
 		});
 		$("#"+tab).find(".closeTabImg").click(function(e){
 			var lastShow = false;
 			if($("#"+tab).attr("show")==1){
 				lastShow = true; 				
 			}
 			var id = $("#"+tab).attr("id");
 			var title = $("#"+tab).text();
 			//关闭Tab前监听
 			if(me.opt.listeners&&me.opt.listeners.beforeCloseTab) me.opt.listeners.beforeCloseTab(id, title, e);
 			$("#"+tab).remove();
 			$("#"+tab+"-main").remove();
 			me.tabWidth=me.getTabsWidth();
 			me.scrollToRight();
 			if(lastShow) me.setActiveTab(me.tab.find("li:last-child").attr("id"));
 			//关闭Tab后监听
 			if(me.opt.listeners&&me.opt.listeners.afterCloseTab) me.opt.listeners.afterCloseTab(id, title, e);
 			me.removeMenuItem(tab);
 		});
 	},
 	//滚动事件
 	registerScroll:function(){
 		var me=this;
 		this.scrollLeft.mousedown(function(){
 			me.scrollRight.show();
 			me.moveInterval=setInterval(function(){
 				var l=parseInt(me.tabBar.css('left'));
 				if(l>0){
 					window.clearInterval(me.moveInterval);
 					me.scrollLeft.hide();
 					me.tabBar.css({left:0});
 				}else{
 					me.tabBar.css({left:l+10});
 				}
 				
 			},20);
 		}).mouseup(function(){
 			window.clearInterval(me.moveInterval);
 		});
 		this.scrollRight.mousedown(function(){
 			me.scrollLeft.show();
 			me.moveInterval=setInterval(function(){
 				var l=parseInt(me.tabBar.css('left'));
 				var l2=me.tabWidth-me.tabBarOuter.outerWidth()+me.menuButton.width();
 				if(-l>l2){
 					window.clearInterval(me.moveInterval);
 					me.scrollRight.hide();
 					me.scrollToRight();
 				}else{
 					me.tabBar.css({left:l-10});
 				}
 			},20);
 		}).mouseup(function(){
 			window.clearInterval(me.moveInterval);
 		});
 		me.menuButton.click(function(){
 			if(me.menuList.is(':visible')){
 				me.menuList.hide();
 			}else{
 				me.menuList.show();
 			}
 		});
 		me.menuList.mouseover(function(){
 			me.menuList.show();
 		}).mouseout(function(){
			me.menuList.hide();
		});
 	},
 	scrollToTab:function(id){
 		var me=this;
 		me.setActiveTab(id);
 	},
 	createPlaceholder: function(d) {
 		if($('.placeHolder').length==0){
 			var li=$('<li class="placeHolder"></li>');
 			li.css({width:d.width(),height:d.height()}).insertAfter(d);
 		}
 	},
 	removePlaceholder: function(d) {
 		$('.placeHolder').remove();
 	},
 	mouseCapture:function(e){
 		var x=e.clientX;
 		var t=this;
 		this.tab.find('li[class=""]').each(function(){
 			//top.document.title=x+'-'+$(this).offset().left;
 			var os=$(this).offset();
 			var w=$(this).width();
			if(x>(os.left+w/2-10)&&x<(os.left+w/2)){
				t.insertPlaceholder($(this),'a');
			}
			if(x>(os.left+w/2)&&x<(os.left+w/2+10)){
				t.insertPlaceholder($(this),'b');
			}
 		});
 	},
 	insertPlaceholder:function(o,p){
 		this.removePlaceholder(o);
 		var li=$('<li class="placeHolder"></li>');
 		if(p=='b'){
 			li.insertBefore(o);
 		}else{
 			li.insertAfter(o);
 		}
 		li.css({width:o.width(),height:o.height()});
 	},
 	changePosition:function(o){
 		o.insertAfter($('.placeHolder'));
 		$('.placeHolder').remove();
 		o.css({position:'',left:"auto",zIndex:0}); 
 	},
 	//选中tab
 	setActiveTab: function(tab){
 		if($("#"+tab).attr("able")=='0'){
 			return;
 		}
 		this.tabBar.find("li").each(function(){
 			if($(this).attr("able")=='0'){
 				if($(this).attr('class')==''){
 					$(this).addClass("disabled");
 				}
 			}else{
 				$(this).removeClass("active");			
				$(this).attr('show', '0');
 			}
 			$("#"+$(this).attr("id")+"-main").css("display", "none");//全部隐藏掉
		});
		$("#"+tab).addClass("active");
		$("#"+tab).attr('show', '1');
		$("#"+tab+"-main").css("display", "block");
 	},
 	//获取选中的tab
 	getActiveTab: function(){
 		var tabId = '';
 		this.tabBar.find("li").each(function(){
			if($(this).attr('show')=='1'){
				tabId = $(this).attr("id");
				return;
			}
		});
 		return tabId;
 	},
 	//设置Tab不可选择
 	setDisableTab: function(tabId){
		$("#"+tabId).attr('able', '0');
		$("#"+tabId).addClass("disabled");
 	},
 	//设置Tab可以选择
 	setAbleTab: function(tabId){
 		$("#"+tabId).attr('able', '1');
 		$("#"+tabId).removeClass("disabled");
 	},
 	//获取Tab是否是可以选择的
 	isableTab: function(tabId){
 		return $("#"+tabId).attr("able")=='1'?true:false;
 	},
 	//获取某个Tab的主页面
 	getTabComponent: function(id){
 		if($("#"+id).length==0){
 			return null;
 		}
 		return $("#"+id+"-main");
 	},
 	//根据name、type值获取组件对象
 	getObject: function(name, type){
 		if(!type) type = "input";
 		return this.tabBody.find(type+"[name='"+name+"']");
 	}
 };
 