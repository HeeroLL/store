JButton = function(opt){
	if(opt) this.opt = opt;
	else this.opt = {};
	this.toggle = false;
	if(!this.opt.id) this.opt.id = Math.floor(Math.random()*10000) + "_jbutton";
	var buttonHTML = $("<button id='"+this.opt.id+"' class='JButtonClass'><span>"+this.opt.text+"</span></button>");
	if(this.opt.handler) buttonHTML.click(this.opt.handler);
	if(!this.opt.width) this.opt.width = JButton.NORMAL;
	if(this.opt.width==JButton.LARGE) {
		buttonHTML.width(150);
		buttonHTML.addClass('JButtonClass_large');
	}
	if(this.opt.margin){
		buttonHTML.css("margin", this.opt.margin);
	}
	if(this.opt.icon) buttonHTML.find("span").css({
		"background": 'url("'+this.opt.icon+'") no-repeat 5px',
		"padding-left": '5px'
	});
	var parentContainer;
	if(this.opt.render) parentContainer = $("#"+this.opt.render);
	else parentContainer = $(document.body);
	parentContainer.append(buttonHTML);
	var object = this;
	buttonHTML.hover(function(){
		if(object.opt.width==JButton.LARGE) {
			$(this).addClass('JButtonClass_large_hover');			
		}else{
			$(this).addClass('JButtonClass_hover');		
		}
	}, function(){
		if(object.opt.width==JButton.LARGE) {
			$(this).removeClass('JButtonClass_large_hover');
		}else{
			$(this).removeClass('JButtonClass_hover');		
		}		
	});
};

JButton.prototype = {
	click: function(callback){
		$("#"+this.opt.id).click(callback);
	},
	getComp: function(){
		return $("#"+this.opt.id);
	},
	remove: function(){
		$("#"+this.opt.id).remove();
	},
	setToggle: function(bool){
		if(this.opt.width==JButton.LARGE) {			
			if(bool){			
				this.toggle = true;
				$(this).addClass('JButtonClass_large_hover');
			}else{
				this.toggle = false;
				$(this).removeClass('JButtonClass_large_hover');
			}
		}else{
			if(bool){
				this.toggle = true;
				$(this).addClass('JButtonClass_hover');
			}else{
				this.toggle = false;
				$(this).removeClass('JButtonClass_hover');
			}
		}		
	}
};
JButton.NORMAL = 90;
JButton.LARGE = 150;