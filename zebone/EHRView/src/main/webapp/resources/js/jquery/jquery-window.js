var window_zindex=14;
/*
jquery window, version 1.5
options.content:
options.resizable:true/false 可改变大小
options.minimizable:true/false 最小化
options.onClose:
options.onMinimize:
options.onMaximize:
options.onRestore:
*/
JWindow=function(options){
	this.dragable=true;
	this.windowId=null;
	this.width=null;
	this.height=null;
	this.grabx=null;
	this.graby=null;
	this.minWidth=250;
	this.minHeight=45;
	this.options = {
		renderTo:$(document.body)
	};
	$.extend(this.options,options);
	this.initialize();
	this.setOptions(options);
	this.show();
}
JWindow.prototype={
	initialize:function(){
		var me=this;
		me.width=me.options.width;
		me.height=me.options.height;
		me.options.renderTo=me.options.renderTo?me.options.renderTo:$(document.body);
		me.windowId=this.options.id?this.options.id:Math.floor(Math.random()*0x100000).toString(16);
		if($('#window_'+this.windowId).length>0){
			me.container=$('#window_'+me.windowId);
			me.active();
			return;
		}
		$('<div id="window_'+me.windowId+'" class="window" style="visibility: visible; display: block;"><div id="window_outer_'+this.windowId+'" class="window_outer"><div id="window_inner_'+this.windowId+'" class="window_inner"><div class="window_content"></div></div></div></div>').appendTo(this.options.renderTo);
		me.container=$('#window_'+me.windowId);
		me.container.find('.window_content').append('<div id="window-'+me.windowId+'-titleBar" class="window_titleBar"></div>');
		me.titleBar=$('#window-'+me.windowId+'-titleBar');
		me.titleBar.append('<div class="j-panel-tools" id="window-'+me.windowId+'-titleButtonBar"></div>');
		me.titleButtonBar=$('#window-'+me.windowId+'-titleButtonBar');
		//关闭
		if(me.options.closeable!=false){
			me.titleButtonBar.append('<a class="j-tool j-tool-close" title="关闭" href="javascript:void(0);" style="display: block;"></a>');
			me.titleButtonBar.find('.j-tool-close').click(function(){me.close();
				if(me.options.onClose){
					var cFun=me.options.onClose;
					cFun();
				}
			});
		}
		//最大化
		if(me.options.maximizable!=false){
			me.titleButtonBar.append('<a class="j-tool j-tool-maximize" title="最大化" href="javascript:void(0);" style="display: block;"></a>');
			me.titleButtonBar.append('<a class="j-tool j-tool-restore" title="还原"  href="javascript:void(0);"  style="display: none;"></a>');
			me.titleBar.find('.j-tool-maximize').click(function(){
				me.max();
				if(me.options.onMaximize){
					var mxFun=me.options.onMaximize;
					mxFun();
				}
			});
			me.titleBar.find('.j-tool-restore').click(function(){
				me.max();
				if(me.options.onRestore){
					var reFun=me.options.onRestore;
					reFun();
				}
			});
			me.titleBar.dblclick(function(){
				me.max();
				if(me.container.find('.j-tool-maximize').is(':hidden')){
					if(me.options.onMaximize){
						var mxFun=me.options.onMaximize;
						mxFun();
					}
				}else{
					if(me.options.onRestore){
						var reFun=me.options.onRestore;
						reFun();
					}
				}
				
			});
		}
		//最小化
		if(me.options.minimizable!=false){
			me.titleButtonBar.append('<a class="j-tool j-tool-minimize" title="最小化"  href="javascript:void(0);" style="display: block;"></a>');
			me.titleButtonBar.find('.j-tool-minimize').click(function(){
				me.min();
				if(me.options.onMinimize){
					var miFun=me.options.onMinimize;
					miFun();
				}
			});
		}
		//titleBar
		me.titleBar.append('<div id="window-'+me.windowId+'-title" class="window_title titleText">'+this.options.title+'</div>');
		me.titleBar.append('<ul id="window_tab_ul_'+me.windowId+'" class="windowTabBar"><li id="window_tab_'+me.windowId+'"><a href="javascript:void(0);"><span>'+this.options.title+'</span></a></li></ul>');
		$('#window_tab_'+me.windowId).click(function(){
			me.activeTab($(this).attr('id'));
		});
		var window_body=$('<div id="window_body_outer_'+me.windowId+'" class="window_bodyOuter" style=""><div id="window_body_'+me.windowId+'" class="window_bodyArea" ></div><div>');
		me.container.find('.window_content').append(window_body);
		me.windowBody=$('#window_body_'+me.windowId);//body
		var c=me.container;
		//resize
		if(me.options.resizable!=false){
			var corner=$("<div name='resizer' class='j-win-e'></div><div class='j-win-s'></div><div class='j-win-w'></div><div class='j-win-n'></div><div class='j-win-sw'></div><div class='j-win-es'></div><div class='j-win-wn'></div><div class='j-win-ne'></div>");
			me.container.find('.window_content').append(corner);
			//resize事件
			corner.mousedown(function(e){
				e.preventDefault(); 
				var el=e.srcElement?e.srcElement:e.target;
				var className=el.className;
				me.resizable=className.split('-')[2];
				var offset = c.offset(); 
	            me.width=c.width();
	         	me.height=c.height();
				me.grabx = e.pageX;
				me.graby = e.pageY;
				me.setMark();
				$(document).bind("mousemove",function(ev){
					ev.preventDefault();
					if(me.resizable!=''){
	             		if(me.resizable.indexOf("e") != -1){
	             			c.css({width:Math.max(me.minWidth,me.width+ev.pageX-me.grabx)});
	             		}
	             		if(me.resizable.indexOf("s") != -1){
	             			c.css({height:Math.max(me.minHeight,me.height+ev.pageY-me.graby)});
	             		}
	             		if(me.resizable.indexOf("w") != -1){
	             			c.css({
	             			width:Math.max(me.width-ev.pageX+me.grabx,me.minWidth),
	             			left:Math.min(offset.left+me.width-me.minWidth,offset.left+ev.pageX-me.grabx)});
	             		}
	             		if(me.resizable.indexOf("n") != -1){
	             			c.css({height:Math.max(me.height-ev.pageY+me.graby,me.minHeight),
	             			top:Math.min(offset.top+me.height-me.minHeight,offset.top+ev.pageY-me.graby)});
	             		}
						me.fixPosition();
	             	}
				})
			});
		}
		//窗口拖拽
		me.titleBar.mousedown(function(e){
			 e.preventDefault(); 
			 me.active();//z-index
			 me.setMark();
			 var offset = c.offset();
             me.grabx = e.clientX;
			 me.graby = e.clientY;
			 if(me.dragable){
            	 $(document).bind("mousemove",function(ev){
            	 	 ev.preventDefault(); 
		             var _x=ev.clientX- me.grabx+offset.left;
		             var _y=ev.clientY-me.graby+offset.top;
		             if(_y<-10){_y=-10;}
		             if(_y>(document.body.clientHeight-35)){_y=document.body.clientHeight-35;}
		             c.css({left:_x+"px",top:_y+"px"});
		             me.left=_x;
		             me.top=_y;
             	})
             } 
		});
		$(document).mouseup(function(){
			$(this).unbind("mousemove");
			me.resizable='';
			me.setUnMark();
		});
	},
	setOptions: function(options){
		var o = this.options;
		var c=this.container;
		var a=this.options.appendTo;
		var me=this;
		this.top =this.options.top?this.options.top:(a.outerHeight()/2 - this.options.height/2);
		this.left = this.options.left?this.options.left:(a.outerWidth()/2 - this.options.width/2);
		this.width=this.options.width;
		this.height=this.options.height;
		c.css({
			left:this.left,
			top:this.top,
			width:o.width+22+'px',
			height:o.height+'px'
		});
		this.fixPosition();
		if(o.content){
			this.add(o.content);
			this.windowBody.css({'overflow':'auto'});
		}else if(o.url){
			var iframe='<iframe src="'+o.url+'" name="f_'+this.windowId+'" class="iframeApp" frameborder="0" frameborder="0" marginwidth="0" marginheight="0"  style="width: 100%; height: 100%; "></iframe><div id="iframe_dragResizeMask_'+this.windowId+'" class="iframeDragResizeMask" style="display: none;"></div>';
			this.add(iframe);
			c.find('.iframeDragResizeMask').mousedown(function(){
				me.active();//z-index
				$(this).hide();
			});
		}
	},
	fixPosition:function(){
		var me=this;
		var c=this.container;
		var titleHeight=me.titleBar.outerHeight();
		/*c.find('.window_outer').css({
			height:c.height()-20+'px'
		});*/
		c.find('.window_bodyOuter').css({
			height:c.height()-titleHeight+'px'
		});
		me.windowBody.css({
			height:c.height()-titleHeight+'px'
		});
	},
	show:function(){
		this.container.show();
	},
	close:function(){
		var c=this.container;
		/*c.find('iframe').remove();
		var offset=c.offset();
		c.animate({width:100,height:100,left:offset.left+(c.width()-100)/2,top:offset.top+(c.height()-100)/2},400,function(){c.remove()});*/
		c.fadeOut(function(){c.remove();});
	},
	max:function(){
		var c=this.container;
		if(c.find('.j-tool-maximize').is(':hidden')){
			//还原
			c.css({
			top:this.top,
			left:this.left,
			width:this.width,
			height:this.height
			});
			this.titleBar.find('.j-tool-maximize').show();
			this.titleBar.find('.j-tool-restore').hide();
			this.dragable=true;
		}else{
			//最大化
			c.css({
			top:'0px',
			left:'0px',
			width:document.body.clientWidth-5,
			height:document.body.clientHeight-5
			});
			this.titleBar.find('.j-tool-maximize').hide();
			this.titleBar.find('.j-tool-restore').show();
			this.dragable=false;
		}
		this.fixPosition();
	},
	min:function(){
		this.container.hide();
	},
	add:function(content){
		this.windowBody.html(content);
	},
	active:function() {
		window_zindex+=1;
		this.container.css('z-index',window_zindex);
		$('.window').removeClass('window_current');
		$('.window').find('.iframeDragResizeMask').css('display','block');
		this.container.addClass('window_current');
		this.container.find('.iframeDragResizeMask').css('display','none');
    },
    setMark:function(){
    	this.container.find('.iframeDragResizeMask').css('display','block');
    },
    setUnMark:function(){
    	this.container.find('.iframeDragResizeMask').css('display','none');
    },
    addTab:function(o){
    	var me=this;
    	var c=me.container;
    	var body=c.find('.window_bodyOuter');
    	var tabBar=$('#window_tab_ul_'+this.windowId);
    	body.find('.window_bodyArea').hide();
    	c.find('.window_title').hide();
    	if($('#window_body_'+o.id).length!=0){
    		//激活
    		this.activeTab(o.id);
    	}else{
    		var newTab=$('<li id="window_tab_'+o.id+'" class="window_tab_active"><a href="javascript:void(0);"><span>'+o.title+'</span></a><div class="tab_icon" style="background:url();"></div><div class="tabClose"></div></li>');
    		newTab.appendTo(tabBar);
    		newTab.click(function(){
    			me.activeTab($(this).attr('id'));
    		});
    		var newBody=$('<div id="window_body_'+o.id+'" class="window_bodyArea" ></div>');
    		newBody.appendTo(body);
    		var content=o.url?'<iframe src="'+o.url+'" name="" frameborder="0" marginwidth="0" marginheight="0" style="width:100%;height: 100%;"></iframe>':o.content;
    		newBody.html(content);
    		newTab.find('.tabClose').click(function(){
    			me.closeTab(o.id);
    		});
    	}
    },
    activeTab:function(id){
    	var c=this.container;
    	var body=c.find('.window_bodyOuter');
    	var tabBar=$('#window_tab_ul_'+this.windowId);
    	if(tabBar.find('.window_tab_active').attr('id')==id){
			return;
		}
		id=id.replace('window_tab_','');
		tabBar.find('li').removeClass('window_tab_active');
		$('#window_tab_'+id).addClass('window_tab_active');
		body.find('.window_bodyArea').hide();
		$('#window_body_'+id).show();
    },
    closeTab:function(id){
    	$('#window_body_'+id).remove();
    	$('#window_tab_'+id).remove();
    	if($('#window_tab_ul_'+this.windowId).find('li').length==1){
    		this.container.find('.window_title').show();
    		$('#window_tab_ul_'+this.windowId).hide();
    	}
    	
    }
}
