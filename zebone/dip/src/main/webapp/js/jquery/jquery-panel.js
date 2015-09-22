/**
 * JQuery Panel
 */
JPanel = function(opt){
	if(opt) this.opt = opt;
	else this.opt = {};
	if(!this.opt.listeners) this.opt.listeners = {};
	this.expand = true;
	if(!this.opt.id) this.opt.id = Math.floor(Math.random()*10000) + "_jpanel";
	if(!this.opt.columns) this.opt.columns = 1;
	this.panelHTML = $("<div id='"+this.opt.id+"' class='panel_div'></div>");
	if(this.opt.title){
		this.panelHTML.append("<div class='panel_title_div'><div class=\"panel_title\">"+this.opt.title+"</div><div class='panel_coll'></div></div>");
	}
	if(!this.opt.collapse){
		this.panelHTML.find(".panel_coll").css("display", "none");
	}
	this.panelHTML.append("<div id='"+this.opt.id+"_panel_main' class='panel_main'>"+(this.opt.html?this.opt.html:"")+"</div>");
	if(this.opt.background){
		this.panelHTML.css("background-color", this.opt.background);
	}
	if(this.opt.icon){
		this.panelHTML.find(".panel_title").css({
			background: 'url("'+this.opt.icon+'") no-repeat 2px',
			'text-indent': '25px'
		});
	}
	this.registerEvent();
	this.loadPanel();	
	if(this.opt.items){
		this.itemsID = new Array();
		if(!this.opt.labelWidth) this.opt.labelWidth = 90;
		var len = this.opt.items.length;
		var fieldHTML = "<table class='form_table'><form class='checkForm'>";
		var j=0;
		this.combos = new Array();
		var comboCount = 0;
		for(var i=0; i<len; i++){
			var field = this.opt.items[i];
			var inputType = field.inputType?field.inputType:'text';
			if(inputType=='hidden'){
				if(!field.value) field.value = "";
				if(!field.id) field.id = this.opt.id+"_jinput"+i;
				if(!field.name) field.name = this.opt.id+"_jinputname"+i;
				this.itemsID[i] = field.id;
				fieldHTML += "<input type='hidden' id='"+field.id+"' name='"+field.name+"' value='"+field.value+"'/>";
			}else{
				if(!field.width) field.width = '100%';
				if(!field.value) field.value = "";
				if(isNaN(parseInt(field.width))) field.width = field.width + "px";							
				var colspan = field.colspan?field.colspan:1;
				var validate = "";
				var f_type = '';
				if(field.validate) validate = " validate='"+field.validate+"' ";
				if(inputType=='number'){
					inputType = 'text';
				}else if(inputType=='email'){
					inputType = 'text';
				}else if(inputType=='datefield'){
					f_type = ' class="Wdate" onClick="WdatePicker();" ';
					inputType = 'text';
				}
				if(!field.id){
					field.id = this.opt.id+"_jinput"+i;
				}
				if(!field.name){
					field.name = this.opt.id+"_jinputname"+i;
				}
				this.itemsID[i] = field.id;
				if(j==0||j%this.opt.columns==0){
					fieldHTML += "<tr>";
				}
				fieldHTML += "<td align='right' width='"+this.opt.labelWidth+"'>"+(field.fieldLabel?(field.fieldLabel+":"):"&nbsp;")+"</td>";
				fieldHTML += "<td align='left' colspan='"+colspan+"'>";
				if(inputType=='label'){
					fieldHTML += "<span id='"+field.id+"' name='"+field.name+"'>"+field.value+"</span>";
				}else if(inputType=='combo'){
					fieldHTML += "<select id='"+field.id+"' name='"+field.name+"' title='"+field.fieldLabel+"' "+validate+" style='width: "+field.width+";' comboCount='"+comboCount+"'>";
					if(field.data){
						for(var x=0; x<field.data.length; x++){
							fieldHTML += "<option value='"+field.data[x][0]+"'>"+field.data[x][1]+"</option>";
						}
					}
					fieldHTML += "</select>";
					if(!field.listeners) field.listeners = {};
					if(field.listeners.change) $("#"+field.id).live("change", field.listeners.change);					
					if(field.dataUrl){
						this.combos[comboCount] = new Array();
						this.combos[comboCount][0] = field.id;
						this.combos[comboCount][1] = field.dataUrl;
						this.combos[comboCount][2] = field.valueField?field.valueField:"value";
						this.combos[comboCount][3] = field.displayField?field.displayField:'text';
						this.combos[comboCount][4] = field.value?field.value:"";
						this.combos[comboCount][5] = field.listeners.afterload;
						comboCount++;
					}
				}else if(inputType=='checkbox'){
					fieldHTML += "<input id='"+field.id+"' name='"+field.name+"' type='"+inputType+"' title='"+field.fieldLabel+"' "+validate+" value='"+field.value+"'/>";
				}else if(inputType=='textarea'){
					if(field.rows) field.rows = " rows = '"+field.rows+"' ";
					else field.rows = "";
					fieldHTML += "<textarea id='"+field.id+"' name='"+field.name+"' title='"+field.fieldLabel+"'  "+field.rows+validate+" style='width: "+field.width+";'>"+field.value+"</textarea>";
				}else if(inputType=='panel'){
					fieldHTML += "<div id='"+field.id+"' name='"+field.name+"' style='width: "+field.width+";'>"+(field.html?field.html:"&nbsp;")+"</div>";
				}else{
					fieldHTML += "<input id='"+field.id+"' name='"+field.name+"' type='"+inputType+"' title='"+field.fieldLabel+"' "+validate+" style='width: "+field.width+";'  value='"+field.value+"' "+f_type+"/>";
					if(!field.listeners) field.listeners = {};
					if(field.listeners.blur) $("#"+field.id).live("blur", field.listeners.blur);	
				}
				fieldHTML+="</td>";
				j += (colspan-1)/2;
				if((j>0&&(j+1)%this.opt.columns==0)||(i+1==len)){
					fieldHTML += "</tr>";			
				}
				j++;
			}
		}
		fieldHTML += "</form></table>";
		this.panelHTML.find(".panel_main").append(fieldHTML);
		try{
			 if(typeof(eval(bindCheckEvent))=="function"){
				 bindCheckEvent();
			 }  				 
		}catch(e){}
		this.loadComboDatas(this.combos);
	}
	if(this.opt.buttons){		
		var buttons = $("<div class='btnDIV'></div>");
		var len = this.opt.buttons.length;
		for(var i=0; i<len; i++){
			var btn = this.opt.buttons[i];
			if(!btn.id){
				btn.id = this.opt.id+'_jbutton'+i;
			}
			var button = new JButton({
				id: btn.id,
				text: btn.text,
				icon: btn.icon
			});
			var comp = button.getComp();
			button.remove();
			comp.click(btn.handler);
			buttons.append(comp);	
			
		}		
		this.panelHTML.append(buttons);
		this.registerButtonHoverEvent();
	}
	var object = this;
	 $(window).resize(function(){
		 object.loadPanel();
	 });
};

JPanel.prototype = {
	loadPanel: function(){		
		if(this.opt.render){
			this.original = $("#"+this.opt.render);
			var width = this.original.width();
			var height = this.original.height();
			this.panelHTML.css({
				width: this.opt.width?this.opt.width:(width-4),
				height: this.opt.height?this.opt.height:(height-4),
				margin: '1px'
			});
			this.panelHTML.find(".panel_main").css({
				width: this.panelHTML.width(),
				height: this.panelHTML.height() - (this.opt.title?25:0) - (this.opt.buttons?35:0)
			});
		}else{
			this.original=$(document.body);
			var width = this.original.outerWidth();
			var height = this.original.outerHeight();
			this.panelHTML.css({
				width: this.opt.width?this.opt.width:(width-2),
				height: this.opt.height?this.opt.height:(height-2)
			});
			this.panelHTML.find(".panel_main").css({
				width: this.panelHTML.width(),
				height: this.panelHTML.height() - 2 - (this.opt.title?25:0) - (this.opt.buttons?35:0)
			});		
		}
		this.original.append(this.panelHTML);		
		this.maxHeight = this.panelHTML.height();
		if(!this.expand){
			this.panelHTML.find(".panel_main").css("display", "none");
			this.panelHTML.css("height", 23);
			this.panelHTML.find(".panel_coll").css("background", 'url("tools/jquery/images/jquery-panel/expand.png") no-repeat 5px');
		}
	},
	loadComboData: function(obj, params, callback){
		var object = this;
		var comboCount = obj.attr("comboCount");
		var cb = this.combos[comboCount];
		var datas = "";
		if(params){
			for(o in params){
				datas += o+"="+params[o] + "&";
			}
			if(datas.length > 0) datas = datas.substring(0, datas.length-1);
		}		
		$.ajax({
			url: cb[1],
			type: 'POST',
			cache: false,
			data:  encodeURI(datas),
			dataType: 'json',
			success: function(res){
				var selects = object.panelHTML.find(".checkForm").find("select[id='"+cb[0]+"']");
				selects.empty();
				var data = res.data;
				for(var j=0; j<data.length; j++){
					selects.append("<option value='"+data[j][cb[2]]+"' "+(cb[4]==data[j][cb[2]]?"selected":"")+">"+data[j][cb[3]]+"</option>");
				}
				if(callback) callback();
				if(cb[5]) cb[5](selects);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){}
		});	
	},
	loadComboDatas: function(){
		var object = this;
		if(this.combos.length>0){
			for(var i=0; i<this.combos.length; i++){				
				$.ajax({
					url: this.combos[i][1],
					type: 'POST',
					cache: false,
					dataType: 'json',
					data: encodeURI("aid="+i),
					success: function(res){
						var params = this.data.split("&");
						var aid = '';
						for(var x=0; x<params.length; x++){
							var par = params[x].split("=");
							if(par[0]=='aid') aid = par[1];							
						}
						var cb = object.combos[aid];
						var selects = object.panelHTML.find(".checkForm").find("select[id='"+cb[0]+"']");
						selects.empty();
						var data = res.data;
						for(var j=0; j<data.length; j++){
							selects.append("<option value='"+data[j][cb[2]]+"' "+(cb[4]==data[j][cb[2]]?"selected":"")+">"+data[j][cb[3]]+"</option>");
						}
						if(cb[5]) cb[5](selects);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){}
				});				
			}
		}
	},	
	registerEvent: function(){		
		var object = this;
		this.panelHTML.find(".panel_coll").click(function(){
			if(object.expand){
				object.panelHTML.find(".panel_main").css("display", "none");
				object.panelHTML.css("height", 23);
				object.expand = false;
				object.panelHTML.find(".panel_coll").css("background", 'url("tools/jquery/images/jquery-panel/expand.png") no-repeat 5px');
			}else{				
				object.panelHTML.find(".panel_main").css("display", "block");
				object.panelHTML.css("height", object.maxHeight);
				object.expand = true;
				object.panelHTML.find(".panel_coll").css("background", 'url("tools/jquery/images/jquery-panel/collapse.png") no-repeat 5px');
			}
		});
	},	
	registerButtonEvent: function(){
		if(this.opt.buttons){
			this.registerButtonHoverEvent();
			var len = this.opt.buttons.length;
			for(var i=0; i<len; i++){
				var btn = this.opt.buttons[i];
				this.panelHTML.find("#"+btn.id).click(btn.handler);			
			}	
		}
	},
	registerButtonHoverEvent: function(){
		this.panelHTML.find(".btnDIV").find("button").hover(function(){
			$(this).css({
				"background": "url('tools/jquery/images/jquery-panel/btn-bg-toggle.gif') no-repeat"
			});
		}, function(){
			$(this).css({
				"background": "url('tools/jquery/images/jquery-panel/btn-bg.gif') no-repeat 1px"
			});
		});
	},
	renderTo: function(id, size){
		this.opt.render = id;
		$("#"+this.opt.id).remove();		
		this.original = $("#"+this.opt.render);
		var width = this.original.width();
		var height = this.original.height();
		this.panelHTML.css({
			width: this.opt.width?(this.opt.width):(width-4),
			height: this.opt.height?(this.opt.height):(height-4)
		});
		this.panelHTML.find(".panel_main").css({
			width: '100%',
			height: this.panelHTML.height() - (this.opt.title?25:0) - (this.opt.buttons?15:0)
		});
		this.original.append(this.panelHTML);
		this.maxHeight = this.panelHTML.height();
		this.registerEvent();
		this.registerButtonEvent();			
	},
	loadPage: function(url){
		this.panelHTML.find(".panel_main").load(url);
	},
	get: function(name){		
		var formTable = this.panelHTML.find(".panel_main").find(".form_table");
		if(formTable){			
			if(formTable.find("#"+name).length > 0){
				return formTable.find("#"+name);
			}else if(formTable.find("input[name='"+name+"']").length > 0){				
				return formTable.find("input[name='"+name+"']");				
			}else if(formTable.find("select[name='"+name+"']").length > 0){
				return formTable.find("select[name='"+name+"']");
			}else if(formTable.find("textarea[name='"+name+"']").length > 0){
				return formTable.find("textarea[name='"+name+"']");
			}else{				
				return formTable.find("#"+this.itemsID[name]);									
			}
		}
	},
	getValue: function(name){
		var value = null;
		var formTable = this.panelHTML.find(".panel_main").find(".form_table");
		if(formTable){
			if(formTable.find("#"+name)&&typeof(formTable.find("#"+name).val())!='undefined'){
				value = formTable.find("#"+name).val();
			}else if(formTable.find("input[name='"+name+"']")&&typeof(formTable.find("input[name='"+name+"']").val())!='undefined'){
				if(formTable.find("input[name='"+name+"']").attr("type")=='checkbox'){
					if(formTable.find("input[name='"+name+"']").attr("checked")){
						value = formTable.find("input[name='"+name+"']").val();
					}
				}else{
					value = formTable.find("input[name='"+name+"']").val();
				}				
			}else if(formTable.find("select[name='"+name+"']")&&typeof(formTable.find("select[name='"+name+"']").val())!='undefined'){
				value = formTable.find("select[name='"+name+"']").val();
			}else if(formTable.find("textarea[name='"+name+"']")&&typeof(formTable.find("textarea[name='"+name+"']").val())!='undefined'){
				value = formTable.find("textarea[name='"+name+"']").val();
			}else{
				if(formTable.find("#"+this.itemsID[name]).attr("type")=='checkbox'){
					if(formTable.find("#"+this.itemsID[name]).attr("checked")){
						value = formTable.find("#"+this.itemsID[name]).val();
					}
				}else{
					value = formTable.find("#"+this.itemsID[name]).val();
				}							
			}
		}
		return value;
	},
	setValue: function(name, value){
		var formTable = this.panelHTML.find(".panel_main").find(".form_table");
		if(formTable){
			if(formTable.find("#"+name)&&typeof(formTable.find("#"+name).val())!='undefined'){
				formTable.find("#"+name).val(value);
			}else if(formTable.find("input[name='"+name+"']")&&typeof(formTable.find("input[name='"+name+"']").val())!='undefined'){
				if(formTable.find("input[name='"+name+"']").attr("type")=='checkbox'){
					if((value==formTable.find("input[name='"+name+"']").val())||value==true){
						formTable.find("input[name='"+name+"']").attr('checked', true);
					}else{
						formTable.find("input[name='"+name+"']").attr('checked', false);
					}
				}else{
					formTable.find("input[name='"+name+"']").val(value);
				}				
			}else if(formTable.find("select[name='"+name+"']")&&typeof(formTable.find("select[name='"+name+"']").val())!='undefined'){
				formTable.find("select[name='"+name+"']").val(value);
			}else if(formTable.find("textarea[name='"+name+"']")&&typeof(formTable.find("textarea[name='"+name+"']").val())!='undefined'){
				formTable.find("textarea[name='"+name+"']").val(value);
			}else{
				if(formTable.find("#"+this.itemsID[name]).attr("type")=='checkbox'){
					if((value==formTable.find("#"+this.itemsID[name]).val())||value==true){
						formTable.find("#"+this.itemsID[name]).attr("checked", true);
					}else{
						formTable.find("#"+this.itemsID[name]).attr("checked", false);
					}	
				}else{
					formTable.find("#"+this.itemsID[name]).val(value);
				}							
			}
		}
	},
	setText: function(name, value){
		var formTable = this.panelHTML.find(".panel_main").find(".form_table");
		if(formTable){
			if(formTable.find("#"+name)&&typeof(formTable.find("#"+name).val())!='undefined'){
				formTable.find("#"+name).html(value);
			}else if(formTable.find("span[name='"+name+"']")&&typeof(formTable.find("span[name='"+name+"']").val())!='undefined'){
				formTable.find("span[name='"+name+"']").html(value);
			}else{
				formTable.find("#"+this.itemsID[name]).html(value);									
			}
		}
	},
	getText: function(name){
		var value = null;
		var formTable = this.panelHTML.find(".panel_main").find(".form_table");
		if(formTable){
			if(formTable.find("#"+name)&&typeof(formTable.find("#"+name).val())!='undefined'){
				value = formTable.find("#"+name).html();
			}else if(formTable.find("span[name='"+name+"']")&&typeof(formTable.find("span[name='"+name+"']").val())!='undefined'){
				value = formTable.find("span[name='"+name+"']").html();
			}else{
				value = formTable.find("#"+this.itemsID[name]).html();									
			}
		}
		return value;
	},
	isValid: function(){
		if(checkAll) {
			if(!checkAll(this.panelHTML.find(".checkForm")[0])){
				return false;
			}
		}
		return true;
	},
	formSubmit: function(url, params){
		if(checkAll) {
			if(!checkAll(this.panelHTML.find(".checkForm")[0])){
				return;
			}
		}
		if(url) this.opt.submitUrl = url;
		if(params) this.opt.params = params;
		if(this.opt.items){
			var datas = '';
			var len = this.opt.items.length;
			for(var i=0; i<len; i++){
				datas += this.opt.items[i].name + "=" + this.getValue(i) + '&';
			}
			datas = datas.substring(0, datas.length-1);
			if(this.opt.params){
				for(o in this.opt.params){
					datas += "&"+o+"="+this.opt.params[o];
				}
			}
			var object = this;
			this.mask("正在保存数据，请稍候...");
			$.ajax({
				url: this.opt.submitUrl,
				type: 'POST',
				cache: false,
				data: encodeURI(datas),
				dataType: 'json',
				success: function(res){
					object.unmask();
					if(object.opt.listeners.submit) object.opt.listeners.submit(res.success, res.msg);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					object.unmask();
					if(object.opt.listeners.submit) object.opt.listeners.submit(false, '服务器出现异常，数据保存失败。');													
				}
			});
		}
	},
	formReset: function(){
		if(this.opt.items){			
			var len = this.opt.items.length;
			for(var i=0; i<len; i++){
				this.setValue(i, '');
			}			
		}
	},
	getComp: function(){		
		return this.panelHTML;
	},
	mask: function(msg,maskDivClass){
        this.unmask();
        // 参数
        var op = {
            opacity: 0.4,
            z: 10000,
            bgcolor: '#999'
        };
        var original=this.panelHTML.find(".panel_main");
        var position=original.position();                    
        // 创建一个 Mask 层，追加到对象中
        var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
        maskDiv.appendTo(original);
        var maskWidth=original.width();
        var maskHeight=original.height();
        maskDiv.css({
            position: 'absolute',
            top: position.top,
            left: position.left,
            width: maskWidth,
            height:maskHeight,
            'background-color': op.bgcolor,
			opacity: op.opacity
        });
        if(maskDivClass){
            maskDiv.addClass(maskDivClass);
        }
        if(msg){
            var msgDiv=$('<div class="maskdivgenMessage" style="position:absolute;border:#6593cf 1px solid; padding:2px;background:#ccca;"><div style="line-height:32px;border:#a3bad9 1px solid;padding:2px 10px 2px 35px; background: #FFFFFF url(\'images/loading.gif\') no-repeat;font-size: 12px;">'+msg+'</div></div>');
            msgDiv.appendTo(original);
            var widthspace=(maskDiv.width()-msgDiv.width());
            var heightspace=(maskDiv.height()-msgDiv.height());
            msgDiv.css({
                        cursor:'wait',
                        top:heightspace/2 + maskDiv.position().top,
                        left:widthspace/2 + maskDiv.position().left
              });
          }
        return maskDiv;
    },
    unmask: function(){
	     var original=this.panelHTML.find(".panel_main");
	      original.find("> div.maskdivgen").fadeOut('fast',0,function(){
	          $(this).remove();
	      });
	      original.find("> div.maskdivgenMessage").fadeOut('fast',0,function(){
	          $(this).remove();
	      });
    },
    getId: function(){
    	return this.opt.id;
    },
    hide: function(){
    	$("#"+this.opt.id).css("display","none");
    },
    setVisible: function(bool){
    	if(bool){
    		$("#"+this.opt.id).css("display","block");
    	}else{
    		$("#"+this.opt.id).css("display","none");
    	}    	
    }
};