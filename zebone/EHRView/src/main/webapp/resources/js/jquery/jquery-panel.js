/*
jquery panel, version 1.0
@author zhangcy
title String 显示在面板头部的标题信息
width number 面板宽度，默认为auto
height number 面板高度，默认为auto
html String/Object设置面板（body）元素的内容为HTML片段或内容。
renderTo Object 面板附加到哪个元素中，为空就为body
bodyStyle Object 应用于面板体（body）的自定义样式。默认为null
collapsed Boolean 设置面板在第一次渲染时是否处于收缩状态，true则收缩，false则展开，默认为false
collapsible Boolean 设置是否允许面板进行展开和收缩，true则允许进行展开和收缩，并在面板头部显示伸缩按钮。默认为false
tools Array 工具按钮配置对象的数组，这些按钮会被添加到面板头部功能区[{icon:'j-tool-help',handler:function(){alert(1);}}]
header Boolean 设置是否创建面板头部（header）元素，true则创建，false则跳过header元素的创建
*/
JPanel=function(options){
	this.options={
		icon:null,
		width:'auto',
		height:'auto',
		renderTo:$(document.body),
		bodyStyle:null,
		collapsed:false,
		collapsible:false,
		closeable:false,
		header:true,
		title:null,
		tools:null,
		html:null
	}
	this.id='';
	this.setOptions(options);
	this.init();
}
JPanel.prototype={
	setOptions:function(options){
		var o=this.options;
		$.extend(o,options);
	},
	init:function(){
		var me=this;
		var o=me.options;
		me.id='panel-'+Math.floor(Math.random()*0x100000).toString(16);
		var panel='<div id="'+me.id+'" class="j-panel"></div>';
		$(panel).appendTo(me.options.renderTo);
		me.panel=$('#'+me.id);
		if(o.header){
			var header='<div id="'+me.id+'-header" class="j-panel-header">';
			var icon=o.icon?'j-panel-icon '+o.icon:'';
			if(o.title){header+='<div id="'+me.id+'-title" class="j-panel-title '+icon+'">'+o.title+'</div>'};
			header+='<div id="'+me.id+'-tools" class="j-panel-tools"></div>';
			header+='</div>';
			me.panel.append(header);
			me.header=$('#'+me.id+'-header');
			me.tools=$('#'+me.id+'-tools');
			if(o.closeable){
				me.tools.append('<a class="j-tool j-tool-close"></a>');
				me.tools.find('.j-tool-close').click(function(){
					me.panel.slideUp();
				});
			}
			if(o.collapsible){
				me.tools.append('<a class="j-tool j-tool-collapse-top"></a>');
				me.tools.find('.j-tool-collapse-top').click(function(){
					if(me.body.is(':hidden')){
						me.body.fadeIn();
						me.panel.animate({height:me.header.outerHeight()+me.body.outerHeight()},200);
						$(this).removeClass('j-tool-collapse-bottom').addClass('j-tool-collapse-top');
					}else{
						me.body.fadeOut();
						me.panel.animate({height:me.header.outerHeight()},200);
						$(this).removeClass('j-tool-collapse-top').addClass('j-tool-collapse-bottom');
					}
				});
			}
		}
		var body=$('<div id="'+me.id+'-body" class="j-panel-body"></div>');
		me.panel.append(body);
		me.body=$('#'+me.id+'-body');
		me.fixheight();
		if(o.bodyStyle){
			var s=o.bodyStyle;
			if(typeof(s)=='object'){me.body.css(s);}
			
		}
		if(typeof(o.html)=='object'){
			o.html.appendTo(me.body);
		}else{
			me.body.append(o.html);
		}
		if(o.collapsed){
			me.body.hide();
			me.panel.height(me.header.outerHeight());
			me.tools.find('.j-tool-collapse-top').removeClass('j-tool-collapse-top').addClass('j-tool-collapse-bottom');
		}
		if(o.tools){
			var t=o.tools;
			for(var i=0;i<t.length;i++){
				var tool=$('<a class="j-tool '+t[i].icon+'"></a>');
				var h=t[i].handler;
				tool.click(function(){(h());});
				tool.appendTo(me.tools);
			}
		}
	},
	//设置panel和body的高度 panel number auto 100%
	fixheight:function(){
		var me=this;
		me.panel.css({width:me.options.width,height:me.options.height});
		if(me.options.height=='auto'){
			//me.panel.height(me.panel.find('.j-panel-header').outerHeight()+me.body.height());
		}else{
			me.body.height(me.panel.height()-me.panel.find('.j-panel-header').outerHeight());
		}
	},
	append:function(obj){
		this.body.append(obj);
	},
	empty:function(){
		this.body.empty();
	},
	mask:function(){
	
	}
}