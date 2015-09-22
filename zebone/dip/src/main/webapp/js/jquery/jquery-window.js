var window_zindex=14;
/*
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
	this.options = options;
	this.initialize();
	this.setOptions(options);
	this.show();
}
JWindow.prototype={
	initialize:function(){
		var me=this;
		this.width=this.options.width;
		this.height=this.options.height;
		this.options.appendTo=this.options.appendTo?this.options.appendTo:$(document.body);
		this.windowId=this.options.id?this.options.id:Math.floor(Math.random()*0x100000).toString(16);
		$('<div id="window_'+this.windowId+'" class="window" style="visibility: visible; display: block;"><div id="window_outer_'+this.windowId+'" class="window_outer"><div id="window_inner_'+this.windowId+'" class="window_inner"><div class="window_content"></div></div></div></div>').appendTo(this.options.appendTo);
		this.container=$('#window_'+this.windowId);
		var titleBar=$('<div id="window_titleBar_'+this.windowId+'" class="window_titleBar"></div>');
		this.container.find('.window_content').append(titleBar);
		var titleButtonBar=$('<div class="window_titleButtonBar" id="window_titleButtonBar_'+this.windowId+'"></div>');
		titleBar.append(titleButtonBar);
		//关闭
		if(me.options.closeable!=false){
			titleButtonBar.append('<a class="ui_button window_action_button window_close" title="关闭" href="javascript:void(0);" style="display: block;"></a>');
			titleButtonBar.find('.window_close').click(function(){me.close();
				if(me.options.onClose){
					var cFun=me.options.onClose;
					cFun();
				}
			});
		}
		//最大化
		if(me.options.maximizable!=false){
			titleButtonBar.append('<a class="ui_button window_action_button window_max" title="最大化" href="javascript:void(0);" style="display: block;"></a>');
			titleButtonBar.append('<a class="ui_button window_action_button window_restore" title="还原"  href="javascript:void(0);"  style="display: none;"></a>');
			titleBar.find('.window_max').click(function(){
				me.max();
				if(me.options.onMaximize){
					var mxFun=me.options.onMaximize;
					mxFun();
				}
			});
			titleBar.find('.window_restore').click(function(){
				me.max();
				if(me.options.onRestore){
					var reFun=me.options.onRestore;
					reFun();
				}
			});
			titleBar.dblclick(function(){
				me.max();
				if(me.container.find('.window_max').is(':hidden')){
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
			titleButtonBar.append('<a class="ui_button window_action_button window_min" title="最小化"  href="javascript:void(0);" style="display: block;"></a>');
			titleButtonBar.find('.window_min').click(function(){
				me.min();
				if(me.options.onMinimize){
					var miFun=me.options.onMinimize;
					miFun();
				}
			});
		}
		//titleBar
		var windowTitle=$('<div id="window_title_'+me.windowId+'" class="window_title titleText">'+this.options.title+'</div>');
		titleBar.append(windowTitle);
		titleBar.append('<ul id="window_tab_ul_'+me.windowId+'" class="windowTabBar"><li id="window_tab_'+me.windowId+'"><a href="javascript:void(0);"><span>'+this.options.title+'</span></a></li></ul>');
		$('#window_tab_'+me.windowId).click(function(){
			me.activeTab($(this).attr('id'));
		});
		var window_body=$('<div id="window_body_outer_'+me.windowId+'" class="window_bodyOuter" style=""><div id="window_body_'+me.windowId+'" class="window_bodyArea" ></div><div>');
		this.container.find('.window_content').append(window_body);
		var c=this.container;
		//resize
		if(me.options.resizable!=false){
			var corner=$("<div name='resizer' class='j-win-e'></div><div class='j-win-s'></div><div class='j-win-w'></div><div class='j-win-n'></div><div class='j-win-sw'></div><div class='j-win-es'></div><div class='j-win-wn'></div><div class='j-win-ne'></div>");
			this.container.find('.window_content').append(corner);
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
		titleBar.mousedown(function(e){
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
		var titleHeight=$('#window_titleBar_'+this.windowId).height();
		/*c.find('.window_outer').css({
			height:c.height()-20+'px'
		});*/
		c.find('.window_bodyOuter').css({
			height:c.height()-titleHeight+'px'
		});
		c.find('.window_bodyArea').css({
			height:c.height()-titleHeight+'px'
		});
	},
	show:function(){
		this.container.show();
	},
	close:function(){
		var me=this;
		this.container.fadeOut(function(){me.container.remove();});
	},
	max:function(){
		var c=this.container;
		if(c.find('.window_max').is(':hidden')){
			//还原
			c.css({
			top:this.top,
			left:this.left,
			width:this.width,
			height:this.height
			});
			c.find('.window_max').show();
			c.find('.window_restore').hide();
			this.dragable=true;
		}else{
			//最大化
			c.css({
			top:'0px',
			left:'0px',
			width:document.body.clientWidth,
			height:document.body.clientHeight
			});
			c.find('.window_max').hide();
			c.find('.window_restore').show();
			this.dragable=false;
		}
		this.fixPosition();
	},
	min:function(){
		this.container.hide();
	},
	add:function(content){
		this.container.find('.window_bodyArea').html(content);
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
