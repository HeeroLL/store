/**
 * JQuery Dialog
 * @param:titleBar:(true/false) 是否显示标题栏
 * @param:maximizable:(true/false) 是否可最大化
 * @param:onMax:function  最大化时执行的事件
 * @param:onRestore:function  最大还原时执行的事件
 * @param:minimizable:(true/false) 是否可最小化（暂未实现）
 * @param:closeable:(true/false) 是否可关闭
 * @param:onClose:function(){} 关闭窗口时执行的事件
 */
 var parentCols=''; 
 var contents=[]; 
 JDialog = function(opt){
	this.id='';
	this.contentDiv=null;
	this.options={
		closeable:true		
	};
	this.setOptions(opt); 	
 	var original = $(document.body);
 	var me=this;
	var o=me.opt;
	if(o.lock!=false){
		me.setMask();
	}
	me.init();
	//me.fixPosition();
	//校验事件
	if(o.autoCheck){
		bindCheckEvent();
	}	
	if(o.items){ this.addComp(o.items);}
	this.show();//自动显示	
	this.bindMoveEvent();
	this.bindClickEvent();
	this.bindButtonEvent();
	if(o.time){
		setTimeout(function(){me.closeDialog();},o.time*1000);
	}
 };
 //定义一组常量
 JDialog.OK_OPTION = 1;
 JDialog.YES_NO_OPTION = 2;
 JDialog.YES_NO_CANCEL_OPTION = 3;
 JDialog.OK_CANCEL_OPTION = 4;
 JDialog.SAVE_CLOSE_OPTION = 5;
 JDialog.INFORMATION = "./images/icons/info.png";
 JDialog.QUESTION = "./images/icons/question.png";
 JDialog.WARNING = "./images/icons/warning.png";
 JDialog.ERROR = "./images/icons/error.png";
 
 JDialog.prototype = {
	setOptions:function(opt){
		this.content=null; 	
		this.opt =opt?opt:{};
		if(!opt.id) opt.id = Math.floor(Math.random()*10000) + "_jdialog";
		this.id=opt.id?opt.id:Math.floor(Math.random()*10000) + "_jdialog";
		var o=this.options;
		$.extend(o, opt);
	},
 	loadPanel: function(){
 		this.maskDIV.css({
	 		position: 'absolute',
	        top: 0,
	        left: 0,
	        width: $(document.body).outerWidth(true),
	        height:$(document.body).outerHeight(),
	        'background-color': '#999',
			opacity: 0.4
	 	});
	 	var top = $(document.body).outerHeight()/2 - o.height/2;
		var left = $(document.body).outerWidth()/2 - o.width/2;
	 	this.dialog.css({
			width: o.width+'px',
			height: o.height+'px',
			background: '#fff',
			position: 'absolute',
			top: top,
			left: left
		});
 	},
	//创建dialog
	init:function(){
		//创建对话框
		var me=this;
		var o=me.opt
		var original=$(document.body)
		me.dialog = $("<div "+(o.id?("id='"+o.id+"'"):"")+" class='JDialogDIV' style='display:none;z-index:2;'></div>");
		this.dialog.appendTo(original);
		//工具栏
		var toolsPanel=$("<div class='tools-panel'></div>");
		//最小化
		if(o.minimizable){
			toolsPanel.append("<a href='javascript:void(0)' class='j-min' title='最小化'></a>");
			toolsPanel.find(".j-max").click(function(){me.minDialog();});
		}
		//最大化
		if(o.maximizable){
			toolsPanel.append("<a href='javascript:void(0)' class='j-max' title='最大化'></a>");
			toolsPanel.find(".j-max").click(function(){me.maxDialog();});
		}
		//关闭
		toolsPanel.append("<a href='javascript:void(0)' class='j-close' title='关闭' style='"+(o.closable==false?"display:none;":"")+"'></a>");
		//标题栏
		if(o.titleBar!=false){
			var titlebar = $("<div class='JDialogTitleDIV nw'><div class='ne'><div class='n'></div></div></div>");
			this.dialog.append(titlebar);
			if(o.title){
				this.dialog.append("<div class='j-title'><b class=\"j-ico\"></b><span class=\"j-tle\">"+o.title+"</span></div>");	
			}						
			if(typeof(o.closable)=='undefined') o.closable = true;
			if(o.maximizable){
				titlebar.dblclick(function(){me.maxDialog();});//双击最大化
			}		
			this.dialog.append(toolsPanel);				
			if(o.icon){
				titlebar.find('.j-ico').css({
					background: 'url("'+o.icon+'") no-repeat'
				});
			}
		}else{
			var titlebar = $("<div class='JDialogTitleDIV bordertop_w'><div class='bordertop_ne'><div class='bordertop_n'></div></div></div>");
			this.dialog.append(titlebar);
		}
		//content
		this.dialog.append("<div class='DmainDIV w'><div class='e'><div id='"+me.id+"_content' class='content'><img src=\"images/loading_cycle.gif\"/>loading...</div></div></div>");		
		this.contentDiv=$("#"+me.id+"_content");		
		if(o.content){
			this.contentDiv.html(o.content);
		}else if(this.opt.contentId){
			if(contents[this.opt.id]==undefined){
				contents[this.opt.id] = $('#'+this.opt.contentId).html();
				$('#'+this.opt.contentId).remove();
			}
			this.add(contents[this.opt.id]);
		}else if(this.opt.url){
			var  iframe='<iframe src="'+this.opt.url+'" name="f_'+this.opt.id+'" class="dialog_iframe" frameborder="0" frameborder="0" marginwidth="0" marginheight="0"  style="width: 100%; height: 100%; "></iframe>';
			this.add(iframe);
		}
		//按钮栏
		if(o.btns == JDialog.YES_NO_OPTION){
			this.dialog.append("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'><a class='btn' href='javascript:void(0);' name='yes'><span class='btn-left'><span class='btn-text icon-ok'>是</span></span></a> <a class='btn' href='javascript:void(0);' name='no'><span class='btn-left'><span class='btn-text icon-no'>否</span></span></a></div></div></div>");
		}else if(o.btns == JDialog.YES_NO_CANCEL_OPTION){
			this.dialog.append("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'><a class='btn' href='javascript:void(0);' name='yes'><span class='btn-left'><span class='btn-text icon-ok'>是</span></span></a> <a class='btn' href='javascript:void(0);' name='no'><span class='btn-left'><span class='btn-text icon-no'>否</span></span></a> <a class='btn' href='javascript:void(0);' name='cancel'><span class='btn-left'><span class='btn-text icon-cancel'>取消</span></span></a></div></div></div>");
		}else if(o.btns == JDialog.OK_CANCEL_OPTION){
			this.dialog.append("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'><a class='btn' href='javascript:void(0);' name='ok'><span class='btn-left'><span class='btn-text icon-ok'>确 定</span></span></a><a class='btn' href='javascript:void(0);' name='cancel'><span class='btn-left'><span class='btn-text icon-cancel'>取 消</span></span></a></div></div></div>");
		}else if(o.btns == JDialog.SAVE_CLOSE_OPTION){
			this.dialog.append("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'><a class='btn' href='javascript:void(0);' name='ok'><span class='btn-left'><span class='btn-text icon-save'>保 存</span></span></a><a class='btn' href='javascript:void(0);' name='cancel'><span class='btn-left'><span class='btn-text icon-cancel'>关 闭</span></span></a></div></div></div>");
		}else if(o.btns == JDialog.OK_OPTION){
			this.dialog.append("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'><a class='btn' href='javascript:void(0);' name='ok'><span class='btn-left'><span class='btn-text icon-ok'>确 定</span></span></a></div>");
		}else if(o.buttons){
				var buttonBar = $("<div class='JDialogBtnDIV sw'><div class='se'><div class='button s'></div></div></div>");
				var dialogButton=buttonBar.find('.button');
				var buttons = o.buttons;
				var len = buttons.length;
				for(var i=0; i<len; i++){
					if(!buttons[i].id) buttons[i].id = o.id + "jdialog_btn"+i;
					dialogButton.append("<a class='btn' href='javascript:void(0);' id='"+buttons[i].id+"' "+(buttons[i].hidden?"style='display: none;'":"")+"><span class='btn-left'><span class='btn-text'>"+buttons[i].text+"</span></span></a>");
					dialogButton.find("#"+buttons[i].id).click(buttons[i].handler);
				}
				this.dialog.append(buttonBar);
		}else{
			//noButtonBar
			this.dialog.append("<div class='JDialogBtnDIV borderbtm_sw'><div class='borderbtm_se'><div class='borderbtm_s'></div></div></div>");
		}
		//width,height
		var itemWidth = 0, itemHeight = 0;
		if(o.items){
			itemWidth = o.items.getComp().width();
			itemHeight = o.items.getComp().height();
		}
		if(!o.width) o.width = 200;
		if(!o.height) o.height = 80;
		if(parseInt(o.height)<80) o.height = 80;
		if(o.width<itemWidth) o.width = itemWidth+4;
		if(o.btns || o.buttons){
			if((o.height-55)<itemHeight) o.height = itemHeight + 55;
		}else{
			if((o.height-25)<itemHeight) o.height = itemHeight + 25;
		}
		var top = document.documentElement.clientHeight/2 - o.height/2;
		var left = original.outerWidth()/2 - o.width/2;
		this.dialog.css({
			position: ie6?'absolute':'fixed',
			top: top,
			left: left,
			width: o.width+'px',
			height: o.height+'px'
		});
		if(ie6){this.dialog.append('<iframe src="about:blank" style="width:'+this.dialog.width()+';height:'+this.dialog.height()+';position:absolute;top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>');}/*ie6样式问题*/
	},
	//显示Dialog
	show: function(callback){
		var o=this.opt
		var height;
		var dialog=$(o.id?("#"+o.id):".JDialogDIV");
		var tnbHeight=dialog.find('.JDialogTitleDIV').height()+dialog.find('.JDialogBtnDIV').height();//titlebar和buttonbar加起来的高度
		dialog.find(".DmainDIV").height(o.height-tnbHeight);//中部高度
		dialog.find(".JDialogMainTable").height(height);
		dialog.fadeIn();
		if(callback) callback();
	},
	setMaskSize:function(){
		this.maskDIV.css({
			width: $(document.body).outerWidth(true),
			height:document.body.scrollHeight<document.body.clientHeight?document.body.clientHeight:document.body.scrollHeight,
			opacity: 0.5
		});
 	},
 	//绑定拖动事件
 	bindMoveEvent: function(){
 		var object = this;
 		$((object.opt.id?("#"+object.opt.id+">"):"")+".JDialogTitleDIV").mousedown(function(e)//e鼠标事件  
        {  
            $(this).css("cursor","move");//改变鼠标指针的形状  
              
            var offset = $(this).parent().offset();//DIV在页面的位置  
            var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离  
            var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离  
            $(document).bind("mousemove",function(ev)//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件  
            {  
                ev.preventDefault();
                $((object.opt.id?("#"+object.opt.id+">"):"")+".JDialogTitleDIV").stop();//加上这个之后  
                  
                var _x = ev.pageX - x;//获得X轴方向移动的值  
                var _y = ev.pageY - y;//获得Y轴方向移动的值
                if(_x<0) _x = 0;
                if(_y<0) _y = 0;
                
                var widthX = $(document.body).width()- $("#"+object.opt.id).width()-10;
                var heightY = $(document.body).height()- $("#"+object.opt.id).height();
                if(_x>widthX) _x = widthX;
                if(_y>heightY) _y = heightY;
                  
                $((object.opt.id?("#"+object.opt.id+">"):"")+".JDialogTitleDIV").parent().css({left:_x+"px",top:_y+"px"});  
            });  
              
        });  
          
        $(document).mouseup(function()  
        {  
            $((object.opt.id?("#"+object.opt.id+">"):"")+".JDialogTitleDIV").css("cursor","default");  
            $(this).unbind("mousemove");  
        });
 	},
 	//buttonhover事件 替换成A后应该没用了
 	bindButtonEvent: function(){
		var o=this.opt;
 		$((o.id?("#"+o.id+">"):"")+".JDialogBtnDIV button").hover(function(){
 			$(this).addClass('JButtonClass_hover');	
 		},function(){
 			$(this).removeClass('JButtonClass_hover');	
 		});
 	},
 	//绑定事件
 	bindClickEvent: function(){
 		var object = this;
		var o=this.opt
 		$((o.id?("#"+o.id+">"):"")+".tools-panel .j-close").click(function(){
 			if(object.opt.listeners&&object.opt.listeners.buttonClick)
 				object.opt.listeners.buttonClick('close', '关闭');
 			else
 				object.closeDialog();
 		});
 		if(o.buttons){
 			
 		}else{
 		$((o.id?("#"+o.id+">"):"")+".JDialogBtnDIV a").click(function(){
 			if(object.opt.listeners&&object.opt.listeners.buttonClick)
 				object.opt.listeners.buttonClick($(this).attr('name'), $(this).val());
			else
 				object.closeDialog();
 		});
 		}
 	},
 	/**
 	 * 关闭对话框
 	 * @param {} callback 回调函数
 	 */
 	closeDialog: function(callback){
 		var object = this;
		var o=this.opt;
 		if(object.dialog.find('.j-max-restore').length!=0){
			object.maxDialog();
		}
		if(o.onClose){
 			var fn=o.onClose;
 			if ($.isFunction(fn)) { fn(); }//关闭窗口时执行的事件
 		}
 		$(document.body).find("> div"+(o.id?("#"+o.id+"MASK"):".JDialogMaskDIV")).fadeOut('fast',0,function(){
              $(this).remove();
          });
          $(document.body).find("> div"+(o.id?("#"+o.id):".JDialogDIV")).fadeOut('fast',0,function(){
              $(this).remove();
              if(callback) callback();
          });
          
 	},
 	//最大化窗口
 	maxDialog:function(){
		var o=this.opt;
 		if(window.top.hideAll){
 			window.top.hideAll();
 		}
 		if(parentCols==''&&window.parent.document.getElementsByTagName('frameset').length>0){
 		   parentCols=window.parent.document.getElementsByTagName('frameset')[0].cols;
 		}
 		if(this.dialog.find('.j-max-restore').length!=0){
 			//restore
 			if(o.onRestore){
	 			var fn=o.onRestore;
	 			if ($.isFunction(fn)) { fn(); }//最大化还原时执行的事件
	 		}
 			if(parentCols!=''){window.parent.document.getElementsByTagName('frameset')[0].cols=parentCols;}
 			this.dialog.css({width:o.width,height:o.height});
 			this.dialog.find('.j-max-restore').addClass('j-max').removeClass('j-max-restore').attr('title','最大化');
 		}else{
 		   //max
 		    if(o.onMax){
	 			var fn=o.onMax;
	 			if ($.isFunction(fn)) { fn(); }//最大化时执行的事件
	 		}
 		    if(parentCols!=''){window.parent.document.getElementsByTagName('frameset')[0].cols='0,100%';}
 			this.dialog.css({left:0,top:0,width:'100%',height:'100%'});
 			this.dialog.find('.j-max').addClass('j-max-restore').removeClass('j-max').attr('title','还原');
 		}
 		this.fixPosition();
 	},
 	//调整窗口及内部等大小
 	fixPosition:function(){
 		var dialog=this.dialog;
		var o=this.opt;
 		var width=dialog.width();
 		var height=dialog.height();
 		var tnbHeight=dialog.find('.JDialogTitleDIV').height()+dialog.find('.JDialogBtnDIV').height();//titlebar和buttonbar加起来的高度
 		dialog.find(".DmainDIV").height(height-tnbHeight);//中部高度
		if(this.dialog.find('.j-max-restore').length==0){
 			var top = $(document.body).outerHeight()/2 - o.height/2-60;
 			if (top<0){top=1};
		    var left = $(document.body).outerWidth()/2 - o.width/2;
 			this.dialog.css({left:left,top:top});
 		}
 	},
 	fixMask:function(){
 		this.maskDIV.css({
	        width: document.body.clientWidth,
	        height:document.body.scrollHeight,
			opacity: 0.5
	 	});
 	},
 	//添加组件
 	add: function(obj){
		var o=this.opt;
 		$(o.id?("#"+o.id+"_content"):".content").empty();
 		$(o.id?("#"+o.id+"_content"):".content").append(obj);
 	},
 	//消息样式
 	addMsg:function(icon,msg){
 		this.add("<table class='JDialogMainTable'><tr><td style='width:50px;height:40px;'><div class='iconDIV' style='background:url(\""+(icon!=""?icon:JDialog.INFORMATION)+"\") no-repeat 10px center;'></div></td><td>"+msg+"</td></tr></table>");
 	},
 	addComp: function(comp){
		var o=this.opt;
 		$(o.id?("#"+o.id+"_content"):".content").empty();
 		if(comp.setVisible) comp.setVisible(true);
 		comp.renderTo(o.id+"_content"); 		
 		$(o.id?("#"+o.id+"_content"):".content").append(comp.getComp());
 		if(bindCheckEvent) bindCheckEvent();
 	},
 	getComp: function(){
 		return this.opt.items;
 	},
	//获取contentDIv
 	getComponent: function(){
 		return $(this.opt.id?("#"+this.opt.id+"_content"):".content");
 	},
 	//根据name、type值获取组件对象
 	getObject: function(name, type){
 		if(!type) type = "input";
 		return this.getComponent().find(type+"[name='"+name+"']");
 	},
 	mask: function(msg){
        this.unmask();
        // 参数
        var op = {
            opacity: 0.4,
            z: 10000,
            bgcolor: '#999'
        };
        var original = this.getComponent();
        var position = original.position();
        // 创建一个 Mask 层，追加到对象中
        var maskDiv=$('<div class="maskdivgen"></div>');
        maskDiv.appendTo(original);
        var maskWidth=original.outerWidth()?original.outerWidth():original.width();
        var maskHeight=original.outerHeight()?original.outerHeight():original.height();
        maskDiv.css({
            position: 'absolute',
            top: 0,
            left: 0,
            //'z-index': op.z,
            width: maskWidth,
            height:maskHeight,
            'background-color': op.bgcolor,
			opacity: op.opacity
        });
        if(msg){
            var msgDiv=$('<div class="maskdivgenMessage" style="position:absolute;border:#6593cf 1px solid; padding:2px;background:#ccca;"><div style="line-height:32px;border:#a3bad9 1px solid;padding:2px 10px 2px 35px; background: #FFFFFF url(\'images/loading.gif\') no-repeat;font-size: 12px;">'+msg+'</div></div>');
            //msgDiv.appendTo(maskDiv);
            msgDiv.appendTo(original);
            var widthspace=(maskDiv.width()-msgDiv.width());
            var heightspace=(maskDiv.height()-msgDiv.height());
            msgDiv.css({
                        cursor:'wait',
                        top:(heightspace/2-2),
                        left:(widthspace/2-2)
              });
          }
        return maskDiv;
    },
	//创建遮罩层
	setMask:function(){
		var me=this;
		var o=me.opt;
		me.maskDIV = $("<div "+(o.id?("id='"+o.id+"MASK'"):"")+" class='JDialogMaskDIV'></div>");
		me.maskDIV.appendTo($(document.body)); 	
		me.setMaskSize();
		$(window).resize(function() {
		  me.setMaskSize();
		});
		if(ie6){this.maskDIV.append('<iframe src="about:blank" style="width:'+this.maskDIV.width()+';height:'+this.maskDIV.height()+';position:absolute;top:0;left:0;z-index:1000;"></iframe>');}	
	},
	unmask: function(){
         var original=this.getComponent();
	      original.find("> div.maskdivgen").fadeOut('fast',0,function(){
	          $(this).remove();
	      });
	      original.find("> div.maskdivgenMessage").fadeOut('fast',0,function(){
	          $(this).remove();
	      });
    },
    loadFormData:function(p){
    	var me=this;
    	me.mask('正在加载数据，请稍候...');
 		$.ajax({
			url: p.url,
			type: 'POST',
			cache: false,
			data: p.params,
			dataType: 'json',
			success: function(res){		
				if(res.success){
					me.getComponent().find("input").each(function(){
 						var name = $(this).attr('name');
 						if(name){
 							$(this).val(res[name]);
 						}
 					});
 					me.getComponent().find("select").each(function(){
 						var name = $(this).attr('name');
 						if(name){
 							$(this).val(res[name]);
 						}
 					});
 					me.getComponent().find("textarea").each(function(){
 						var name = $(this).attr('name');
 						if(name){
 							$(this).val(res[name]);
 						}
 					});	
 					if(res['modelMaxIcon']!=null){
 						$('img[name=moduleIco]').attr('src',res['modelMaxIcon']);
 					}
 					me.unmask();
				}else{
					me.unmask();
					JDialog.showMessageDialog('失败', '数据加载失败。', JDialog.ERROR);
				}									
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				me.unmask();
				JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
			}
		});
    }
 };
 
 /**
  * 显示消息对话框
  * @param {} title
  * @param {} message
  * @param {} callback
  * @param {} closable
  */
 JDialog.showMessageDialog = function(title, message, optionType, callback, closable){
	if(typeof(closable)=='undefined') closable = true;	
	//重复调用showMessageDialog造成格式混乱
 	if($('#MESSAGE_DIALOG').length==0){
 		//message+='<br/>'+$('#MESSAGE_DIALOG').find('.JDialogMainTable').find('td').html()
 		//$('#MESSAGE_DIALOG').remove(); 	
		var dialog = null;
		dialog = new JDialog({
 		id: 'MESSAGE_DIALOG',
 		title: title,
 		btns: JDialog.OK_OPTION,
 		width: 310,
 		height: 100,
 		closable: closable,
 		listeners: {
 			buttonClick: function(id, text){
 				dialog.closeDialog(function(){
 					if(callback) callback(id, text);
 				});
 			}
 		}
	 	});
	 	dialog.addMsg(optionType?optionType:JDialog.INFORMATION,message); 	
	 	dialog.show(function(){
	 		$("button[name='ok']").focus();
	 	});
	 	
 	} 	
 };
 //IE下提示框渲染bug
 JDialog.fixMessageDialog=function(){
 	if($('#MESSAGE_DIALOG').length!=0){
 		var top = $(document.body).outerHeight()/2 - $('#MESSAGE_DIALOG').height()/2;
		if (top<0){top=1};
	    var left = $(document.body).outerWidth()/2 - $('#MESSAGE_DIALOG').width()/2;
		$('#MESSAGE_DIALOG').css({left:left,top:top});
 	}
 }
 /**
  * 显示是/否对话框
  * @param {} title
  * @param {} message
  * @param {} callback
  * @param {} closable
  */
 JDialog.showConfirmDialog = function(title, message, optionType, callback, closable){
	 if(typeof(closable)=='undefined') closable = true;
	 if($('#CONFIRM_DIALOG').length==0){
	 	var dialog = null;
	 	dialog = new JDialog({
	 		id: 'CONFIRM_DIALOG',
	 		title: title,
	 		width: 350,
	 		height: 100,
	 		btns: JDialog.YES_NO_OPTION,
	 		listeners: {
	 			buttonClick: function(id, text){
	 				dialog.closeDialog();
	 				if(callback) callback(id, text);
	 			}
	 		}
	 	}); 
	 	dialog.addMsg(optionType?optionType:JDialog.INFORMATION,message); 		
	 	dialog.show(function(){
	 		$("button[name='yes']").focus();
	 	});
	 }
 };
 
 JDialog.showInputDialog = function(tilte, message, callback){
	 
 };
 
 JMessageDialog = function(){};
 /**
  * 消息对话框
  * @deprecated
  * @param {} title 标题
  * @param {} message 内容
  * @param {} callback 回调函数
  */
 JMessageDialog.alert = function(title, message, callback){
	var dialog = null;
 	dialog = new JDialog({
 		id: 'MESSAGE_DIALOG',
 		title: title,
 		content: message,
 		width: 300,
 		height: 100,
 		btns: JDialog.OK_OPTION,
 		listeners: {
 			buttonClick: function(id, text){
 				dialog.closeDialog(function(){
 					if(callback) callback(id, text);
 				});
 			}
 		}
 	}); 	
 	dialog.show();
 	$("#MESSAGE_DIALOG").css('line-height', '45px');
 };
 /**
  * 选择对话框
  * @deprecated
  * @param {} title 标题
  * @param {} message 内容
  * @param {} callback 回调函数
  */
 JMessageDialog.confirm = function(title, message, callback){
	 var dialog = null;
 	dialog = new JDialog({
 		id: 'CONFIRM_DIALOG',
 		title: title,
 		content: message,
 		width: 350,
 		height: 100,
 		btns: JDialog.YES_NO_OPTION,
 		listeners: {
 			buttonClick: function(id, text){
 				dialog.closeDialog();
 				if(callback) callback(id, text);
 			}
 		}
 	}); 	
 	dialog.show();
 	$("#CONFIRM_DIALOG").css('line-height', '45px');
 };
 /**
 * 短暂提示
 * @param	{String}	提示内容
 * @param	{Number}	显示时间 (默认1.5秒)
 */
 JDialog.tip=function(content,time){
	new JDialog({
 		id: 'tips',
 		title:'提示',
		fixed: true,
		lock: false,
		titleBar:true,
		closable:false,
		content:'<table style="width:100%;height:100%;"><tr><td valign="center" align="center"><div style="padding: 0 1em;">' + content + '</div></td></tr></table>',
		time:time || 1.5
 	}); 	
 }