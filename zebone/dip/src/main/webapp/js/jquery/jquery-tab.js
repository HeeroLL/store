/**
 * JQuery Tab 选项卡组件
 * 2011-8-4 增加了选项卡不可选择功能
 * @author 王聚金
 * @version 1.0
 * Example: 
 * var tab = new JTab();
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
 */
 JTab = function(opt){
 	if(opt){
 		this.opt = opt;
 	}else{
 		this.opt = {};
 	}
 	if($('#jtab').length==0){
 		var tabHTML = "<div id='jtab'>" +
			"<ul id=\"tabMenu\" class=\"tabs-nav\"></ul>"+
			"<div id='tabMain'></div>" +
			"</div>";
		var tab = $(tabHTML);
		$(document.body).append(tab);
 	}
 	$('#tabMain').css('height',document.body.clientHeight-34);
	window.onresize=function(){
		if($('#tabMenu').is(':visible')){
			$('#tabMain').css('height',document.body.clientHeight-34);//底部div自适应高度
		}else{
			$('#tabMain').css('height','100%');//底部div自适应高度
		}
	}
 };
 
 JTab.prototype = {
 	//注册事件
 	registerEvent: function(tab){
 		var object = this;
		$("#"+tab).mousedown(function(e){
			e.preventDefault();
			//setTab前监听			
			if(object.opt.listeners&&object.opt.listeners.beforeClickTab) {
				if(object.opt.listeners.beforeClickTab($(this).attr('id'), $(this).text(), object.getActiveTab())==1){
					return;
				}			
			}
 			object.setActiveTab($(this).attr('id'));
 			//setTab后监听
 			if(object.opt.listeners&&object.opt.listeners.afterClickTab) object.opt.listeners.afterClickTab($(this).attr('id'), $(this).text(), e);
 			/*拖拽事件*/
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
 		});
 		$("#"+tab).find(".closeTabImg").click(function(e){
 			var lastShow = false;
 			if($("#"+tab).attr("show")==1){
 				lastShow = true; 				
 			}
 			var id = $("#"+tab).attr("id");
 			var title = $("#"+tab).text();
 			//关闭Tab前监听
 			if(object.opt.listeners&&object.opt.listeners.beforeCloseTab) object.opt.listeners.beforeCloseTab(id, title, e);
 			$("#"+tab).remove();
 			$("#"+tab+"-main").remove(); 
 			if(lastShow) object.setActiveTab($("#tabMenu li:last-child").attr("id"));
 			//关闭Tab后监听
 			if(object.opt.listeners&&object.opt.listeners.afterCloseTab) object.opt.listeners.afterCloseTab(id, title, e);
 		});
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
 		$('#tabMenu').find('li[class=""]').each(function(){
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
 		$("#tabMenu li").each(function(){
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
 		$("#tabMenu li").each(function(){
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
 	//添加一个Tab，如果Tab存在，就直接显示
 	addTab: function(opt){
 		if($("#"+opt.id).length==0){
	 		$("#tabMenu").append("<li id='"+opt.id+"' show='0' able='1'><a  href=\"javascript:void(0);\"><span>"+opt.title+"</span></a></li>");
	 		if(opt.icon){
				var div='<div style=\"background:url(\''+opt.icon+'\')\" class=\"tab_icon\"></div>';
				$("#"+opt.id).append(div);
		 	}
	 		if(opt.closable){
		 		$("#"+opt.id).append("<div class=\"closeTabImg\"></div>");
		 	}
		 	if(opt.url){
		 		var iframe='<iframe id="workbench" name="workbench" src="'+opt.url+'" frameborder="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" style="width:100%;height:100%;"></iframe>';
		 		$("#tabMain").append("<div id='"+opt.id+"-main' name='tabMain-childDIV'>"+iframe+"</div>");
		 	}else{
		 		$("#tabMain").append("<div id='"+opt.id+"-main' name='tabMain-childDIV'>"+(opt.html?opt.html:"&nbsp;")+"</div>");
		 	}
	 		if(opt.disabled){
				$("#"+opt.id).attr('able', '0');
				$("#"+opt.id).addClass("disabled");
	 		}	 		
	 		this.registerEvent(opt.id);
 		}
 		if(!opt.disabled){
 			this.setActiveTab(opt.id);
 		}
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
 		return $("#tabMain").find(type+"[name='"+name+"']");
 	}
 };
 