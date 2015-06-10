JViewport = function(opt){
	if(opt) this.opt = opt;
	else this.opt = {};
	if(!this.opt.id) this.opt.id = Math.floor(Math.random()*10000) + "_jviewport";
	this.viewport = $("<div class='viewport'></div>");
	var parentContainer = $(document.body);
	this.viewport.css({
		width: parentContainer.width()-2 + "px",
		height: parentContainer.height()-2 + "px",
		border: "1px solid"
	});
	parentContainer.append(this.viewport);
	if(!this.opt.layout) this.opt.layout = "border";
	var layoutConfig = this.opt.layout;	
	this.north = $("<div id='"+this.opt.id+"_vNorth'></div>");
	this.north.css({
		width: this.viewport.width()-2+"px"
	});
	this.main = $("<div id='"+this.opt.id+"_vMain'></div>");
	this.main.css({
		width: this.viewport.width()-2+"px"
	});
	this.west = $("<div id='"+this.opt.id+"_vWest'></div>");
	this.west.css({
		float: 'left',
		margin: "1px 1px 1px 0px"
	});
	this.center = $("<div id='"+this.opt.id+"_vCenter'></div>");
	this.center.css({
		float: 'left',
		margin: "1px 1px 1px 0px"
	});	
	this.east = $("<div id='"+this.opt.id+"_vEast'></div>");
	this.east.css({
		float: 'right',
		margin: "1px 0px 1px 0px"
	});
	this.south = $("<div id='"+this.opt.id+"_vSouth'></div>");
	this.south.css({
		width: this.viewport.width()-2+"px",
		margin: "1px"
	});
	this.main.append(this.west).append(this.center).append(this.east);
	this.viewport.append(this.north).append(this.main).append(this.south);
	if(this.opt.items){
		for(var i=0; i<this.opt.items.length; i++){
			var item = this.opt.items[i];
			if(!item.region) item.region = "center";
			if(item.region=='north'){
				if(item.height) this.north.height(item.height);
			}else if(item.region=='west'){
				if(item.width) this.west.width(item.width);
			}else if(item.region=='east'){
				if(item.width) this.east.width(item.width);
			}else if(item.region=='south'){
				if(item.height) this.south.height(item.height);
			}		
		}
		this.main.height(this.viewport.height() - this.north.height() - this.south.height() - 4);
		this.west.height(this.main.height()-2);
		this.center.height(this.main.height()-2);
		this.east.height(this.main.height()-2);
		this.center.width(this.main.width() - this.west.width() - this.east.width() - 8);
		for(var i=0; i<this.opt.items.length; i++){
			var item = this.opt.items[i];
			if(!item.region) item.region = "center";
			if(item.region=='north'){
				if(item.height) $("#"+this.opt.id+"_vNorth").height(item.height);
				var northPanel = new JPanel({
					render: this.opt.id+"_vNorth",
					title: item.title,
					html: item.html
				});
			}else if(item.region=='west'){
				if(item.width) $("#"+this.opt.id+"_vWest").width(item.width);
				var westPanel = new JPanel({
					render: this.opt.id+"_vWest",
					title: item.title,
					html: item.html
				});
			}else if(item.region=='east'){
				if(item.width) $("#"+this.opt.id+"_vEast").width(item.width);
				var eastPanel = new JPanel({
					render: this.opt.id+"_vEast",
					title: item.title,
					html: item.html
				});
			}else if(item.region=='south'){
				if(item.height) $("#"+this.opt.id+"_vSouth").height(item.height);
				var southPanel = new JPanel({
					render: this.opt.id+"_vSouth",
					title: item.title,
					html: item.html
				});
			}
		}
	}
};