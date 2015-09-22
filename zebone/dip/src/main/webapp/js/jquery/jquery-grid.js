/**
 * JQuery Grid
 * @param title :grid名称
 * @param checkbox :是否显示checkbox
 * @param height :grid高度
 * @param dataUrl :返回json的url
 * @param pageBar :是否显示分页栏
 * @param crudOpera :是否显示增删改查按钮栏
 * @param col :表数据列,为数组 一行表头 [,,];多行行表头[[,,],[,,]]
 * @param render :grid要附加到某个元素的ID或该元素对象
 * @param striped:true,1,2,3 是否隔行变色 为true默认隔一行变色。传其他数值就为隔所传数值行。
   col参数说明： 
     formatter：function(data){}数据转换方法.参数是当前行的所有数据.
     renderer:function(value){} 数据转换方法.参数是当前单元格的数据.
     dictType: String dictTypeCode 数据字典
   @param rowIdIndex: table tr的ID值对应的json的index
 */
 var field_height=0;
 JGrid = function(opt){
 	this.isMultirow=false;//是否多行head
 	this.dataCols=[];//headtable与datatable对应的列
 	this.dataTds=[];//headtable与datatable对应的td
 	this.headerOrgTds=[];//存放与定义col相对应的td
 	this.upTd=null;
 	this.upTdWidth=0;//临时存放跨列td关联td的宽度
 	this.original=null;
 	if(opt){
 		this.opt = opt;
 	}else{
 		this.opt = {};
 	}
 	if(!this.opt.listeners) this.opt.listeners = {};
 	this.total = 0;//记录总数 
 	this.pageCount = 0; //总页数
 	if(!this.opt.id) this.opt.id = Math.floor(Math.random()*10000) + "_jgrid";
 	if(!this.opt.countEveryPage){
 		this.opt.countEveryPage = 100; //默认为1页100条记录
 	}
 	if(!this.opt.curPage){
 		this.opt.curPage = 1; //默认为第1页
 	}
 	if(!this.opt.orderBy){
 		this.opt.orderBy = "";
 	}
 	
 	//生成table
 	this.init();
 	var cm = this.opt.col;//column
 	var table = this.jgridHeaderTable;
 	if(Object.prototype.toString.call(cm[0]) === '[object Array]'){
 		this.isMultirow=true;
 	}
 	//生成header的列
 	var tableWidth = 0;
 	this.createDataTd(cm);
 	this.createHeaderTh();
 	this.setTableWidth();
 	this.createHeaderTds();
 	this.removeTemp();
 	var me=this;
 	me.jgridHeaderTable.find('.header').find('td').mousedown(function(e){
 		e.preventDefault();
 		me.startColResize(e,$(this));
 	}).mousemove(function(e){
 		me.changeCursorState(e,this);
 	});
 	if(this.opt.pageBar){
 		this.createFooter();
 	}
	if(this.opt.title){		
		this.jgridcrudBtn.css("height", "25px"); 	
		this.jgridcrudBtn.append("<div class='jgridTitle'>"+this.opt.title+"</div>");
 		if(this.opt.icon){
 			$(".jgridTitle").css("background", "url('"+this.opt.icon+"') no-repeat 5px");
 		}
 	}
 	//设置操作按钮栏
 	if(this.opt.crudOpera){
 		this.setOperButtons();
 	}
 	this.loadPanel();
 	this.initData();
 	
 	this.registerCheckboxEvent(1);
 	if(this.opt.allowSort) this.registerHeaderEvent();
 	var object = this;
 	this.jHeaderAndDataDIV.scroll(function(){
 		object.jgridDataDIV.width(object.jgridMain.width()+$(this).scrollLeft());	 		 		 		
 	});
 	var t = 0;  
    window.onresize = function() {  
        var now = new Date();  
        now = now.getTime();  
         if (now - t > 300) {  
            t = now;  
            object.loadPanel();  
        }  
    }
    if(this.opt.formToggleId){
    	this.setToggle();
    }
 };
 
 JGrid.prototype = {
 	init:function(){
 		loadDictByType(this.opt);
 		this.grid = $("<div id='"+this.opt.id+"' class='jgridDIV'></div>");
		if(this.opt.render){
			if(typeof(this.opt.render)=='object'){
				this.original = this.opt.render;
			}else{
				this.original = $("#"+this.opt.render);
			}
			this.original.addClass('grid'); 
		}else{
			this.original = $(document.body); 
		}
		initPop(this.opt);
		this.original.append(this.grid); 
 		this.grid.append("<div class='jgridMain'>" +
 			"<div class='jgridcrudBtn'></div>" +
 			"<div class='jHeaderAndDataDIV'><div class='jHeaderDIV'><table class='jgridHeaderTable' border='0' cellpadding='0' cellspacing='0'></table></div>" +
 			"<div class='jgridDataDIV'>" +
 			"<table border='0' cellpadding='0' cellspacing='1'></table>" +
 			"</div></div>" +
 			"<div class='jgridPage'></div></div>");	
 		this.jgridcrudBtn = this.original.find(".jgridDIV").find(".jgridcrudBtn");
	 	this.jgridMain = this.original.find(".jgridDIV").find(".jgridMain");
	 	this.jHeaderAndDataDIV = this.original.find(".jgridDIV").find(".jHeaderAndDataDIV");
	 	this.jgridHeaderTable = this.original.find(".jgridDIV").find(".jgridHeaderTable");
	 	this.jgridDataDIV = this.original.find(".jgridDIV").find(".jgridDataDIV");
	 	this.jgridDataTable=this.jgridDataDIV.find('table');
	 	this.jgridPage = this.original.find(".jgridDIV").find(".jgridPage");
 	},
 	createHeaderTds:function(){
 		var cm = this.opt.col;
 		var table = this.jgridHeaderTable;
 		var tableWidth=0;
 		if(this.isMultirow){
 			//多行
	 		for(var i=0;i<cm.length;i++){
	 			var tr=$('<tr class="header"></tr>');
	 			table.append(tr);
			 	var c=cm[i];
	 			for(var j=0;j<c.length;j++){
	 				this.addTd(c[j],tr,j);
	 			}
	 		}
	 	}else{
	 		//一行
	 		var headerHTML = "<tr name='header' class='header'>";
		 	if(this.opt.checkbox){
		 		headerHTML += "<td style='width:25px;' align='center'><input type='checkbox' name='checkAll'/></td>";
		 		tableWidth += 25;
		 	}
		 	this.fields = '';
		 	for(var i=0; i<cm.length; i++){
		 		if(typeof(cm[i].width)=='undefined') cm[i].width = 100;
		 		var align=cm[i].textAlign?cm[i].textAlign:"center"; 		
		 		headerHTML += "<td width='"+cm[i].width+"' name='"+cm[i].dataIndex+"'  style='text-align:"+align+";"+(parseInt(cm[i].width)>0?"width:"+cm[i].width+"px":"display:none")+"' sort='"+cm[i].sort+"'><div class='headerTdDiv'>"+cm[i].text+"<span value='0'></span></div></td>";
		 		tableWidth += cm[i].width;
		 		this.fields += cm[i].dataIndex + ',';
		 	}
		 	this.fields = this.fields.substring(0, this.fields.length-1);
		 	this.jgridHeaderTable.css('width', tableWidth);
		 	this.jgridDataDIV.find("table").css('width', tableWidth); 	
		 	headerHTML += "</tr>";
		 	table.append(headerHTML); 
	 	}
	 	//多行&checkbox
	 	if(this.isMultirow&&this.opt.checkbox){
	 		var trs=table[0].getElementsByTagName('tr');
			var firstTd=trs[1].getElementsByTagName('td')[0]
			$("<td align='center' rowspan="+(trs.length-1)+"><input type='checkbox' name='checkAll' /></td>").insertBefore($(firstTd));
		}
 	},
 	//设置操作按钮
 	setOperButtons:function(){
 		this.jgridcrudBtn.css("height", "25px");
 		this.jgridcrudBtn.append("<button name='add' class='JButtonClass' toggle=''><span class='addSpan'>新 建</span></button>" +
 				"<button name='update' class='JButtonClass' toggle=''><span class='updateSpan'>修 改</span></button>"+
 				"<button name='remove' class='JButtonClass' toggle=''><span class='removeSpan'>删 除</span></button>"+
 				"<button name='refresh' class='JButtonClass' toggle=''><span class='refreshSpan'>刷 新</span></button>");
 		this.registerCRUDEvent();
 	},
 	//设置BottomBar
 	createFooter:function(){
 		this.jgridPage.css("height", "25px");
 		this.jgridPage.append("<div name='pageInfo'style='float: none;'>" +
 				"<div name='pageInfo3' class='page_count'>共 <span name='totalCount'>0</span>条记录 每页显示<input type='text' name='countEveryPage' value='"+this.opt.countEveryPage+"' class='countEveryPage'/>条</div>"+
 				"<div class='jgridPageButton page_first' title='首 页' name='first' able=true></div>" +
 				"<div class='jgridPageButton page_prev' title='上一页' name='previous' able=true></div>" +
 				"<div class='' name='pageInfo2' title='页码'><span name='curPage'>"+this.opt.curPage+"</span>/<span name='pageCount'>0</span></div>"+
 				"<div class='jgridPageButton page_next' title='下一页' name='next' able=true></div>" +
 				"<div class='jgridPageButton page_last' title='尾 页' name='last' able=true></div>"+
 				"<div class='jgridPageButton page_refresh' title='刷 新' name='refresh' able=true></div>"+
 				"<div style='float:right;'>跳到第<input type='text' name='curPage' value='"+this.opt.curPage+"' class='goToPageNo'/><input type='button' name='pageGo' class='pageGo'/></div>"+
 				"</div>");
 		this.registerPageBarEvent();
 	},
 	initData:function(){
 		var data = this.opt.data;
	 	if(data){
		 	for(var i=0; i<data.length; i++){
		 		this.addRecord(data[i]);
		 	}
		 	this.total = data.length;
		 	this.calculatePage();
		 	this.setPageBar();
		 	if(this.opt.pageBar){
		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(this.opt.curPage);
		 		this.jgridPage.find("input[name='curPage']").val(this.opt.curPage);
		 		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(this.opt.countEveryPage);
		 		this.jgridPage.find("div[name='pageInfo3']").find("span[name='totalCount']").text(this.total);
		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='pageCount']").text(this.pageCount);
		 	}
	 	}else{
	 		//this.loadData();
	 	}
 	},
 	//添加一个td
 	addTd:function(s,tr,index){
 		var td=$('<td></td>');
		tr.append(td);
		td.attr('tdIndex',index);
 		if(typeof(s)==='string'){
 			if(s==='#cspan'){
 				var pr=td.prev();
 				var c=1;
 				if(pr.attr('colspan')){
 					c=parseInt(pr.attr('colspan'));
 				}
 				pr.attr('colspan',c+1);
 				td.addClass('temp');
 			}
 			if(s==='#rspan'){
 				var pTr=tr.prev();
 				while(pTr.children()[index].getAttribute('class')&&pTr.children()[index].getAttribute('class')=='temp'){
 					pTr=pTr.prev();
 				}
 				var r=1;
 				var pTd=pTr.children()[index];
 				if(pTd.getAttribute('rowspan')){
 					r=parseInt(pTd.getAttribute('rowspan'));
 				}
 				pTd.setAttribute('rowspan',r+1);
 				td.addClass('temp');
 			}
 		}else{
 			if(s.text){
				td.html(s.text);
			}
 			td.css('text-align',s.align?s.align:'center');
 		}
 		if(s.width==0){
 			td.hide();
 		}
 	},
 	//生成dataTable的数据列的数组
 	createDataTd:function(col){
 		if(this.isMultirow){
 			var colIndex=col.length-1;
	 		var trLast=col[colIndex];
	 		var tds='';
	 		var c=this.opt.checkbox?1:0;
	 		for(var i=0;i<trLast.length;i++){
	 			var s=trLast[i];
	 			if(typeof(s)==='string'){
	 				if(s==='#rspan'){
	 				   var j=colIndex-1;
	 				   var upTd=col[j][i];
	 				   while(upTd==='#rspan'){
	 				   	   j--;
	 				   	   upTd=col[j][i];
	 				   }
	 				}
	 				this.dataCols.push(upTd);
	 			}else{
	 				this.dataCols.push(s);
	 			}
	 		}
 		}else{
 			this.dataCols=col;
 		}
 		
 	},
 	//生成headerTable的th
 	createHeaderTh:function(){
 		var tr=$('<tr style="height: auto;"></tr>');
	 	this.jgridHeaderTable.append(tr);
	 	if(this.opt.checkbox){tr.append($('<th style="height:0;width:25px;"></th>'));}
	 	for(var i=0;i<this.dataCols.length;i++){
	 		var cl=this.dataCols[i];
	 		tr.append($('<th style="height:0;width:'+cl.width+'px;'+((cl.width==0)?'display:none;':'')+'"></th>'));
	 	}
 	},
 	//设置datatable宽度
 	setTableWidth:function(){
 		var tableWidth=this.opt.checkbox?25:0;
 		for(var i=0;i<this.dataCols.length;i++){
	 		tableWidth+=this.dataCols[i].width;
	 	}
 		this.jgridHeaderTable.css('width',tableWidth);
 		this.jgridDataTable.css('width',tableWidth);
 	},
 	//移除占位
 	removeTemp:function(){
 		this.jgridHeaderTable.find('.temp').remove();
 	},
 	//查找被跨列的td对应的第一行td
 	findTheTd:function(td){
 		var c=this.opt.checkbox?1:0;
		var x=td.getAttribute('tdIndex')?(parseInt(td.getAttribute('tdIndex'))):td.cellIndex;
        var y=td.parentNode.rowIndex;
        var col=this.opt.col;
        //在col第一行中找到对应的列，如为#cspan 一直往前找到前面一个不为#cspan的
        var upTd=col[0][x];
        while(upTd==='#cspan'){
           x--;
	   	   upTd=col[0][x];
	    }
        this.upTd=this.headerOrgTds[x];
        document.title=(x+'-'+this.upTd.innerHTML);
        this.upTdWidth=$(this.upTd).width();
 	},
 	//加载面板
 	loadPanel: function(){
	 	this.jgridDataDIV.css("width", '100%');
	 	this.setGridHeight();
 	},
 	//设置grid高度:为初始化高度或指定高度;未设置高度,为body高度;
 	setGridHeight:function(h){
 		//设置高度
	 	 if(this.opt.height){
	 	 	//指定了grid高度
	 	 	var gridHeight=this.opt.height;
	 	 	if(h!=undefined){
	 	 		gridHeight=h;
	 	 	}
 	 		this.original.height(gridHeight);
 	 		this.jgridMain.height(gridHeight);
 	 		$('.jgridDIV').height(gridHeight);
	 		var height = gridHeight-24-30;//减去表头-第一行
	 		if(this.jgridHeaderTable.width()>this.jgridMain.width()){
	 			//height-=14//滚动条
	 			if(ie6){
	 				height+=10;
	 			}
	 		}
	 		if(this.opt.pageBar){
	 			height-=25; 
	 		}
	 		this.jgridDataDIV.css("height", height+"px");
	 	}else{
	 		this.jgridMain.css("height", this.original.height()-1+"px");
	 		var height;
	 		if(this.jgridHeaderTable.width()<=this.jgridMain.width()){
	 			height = this.original.height()-52;
	 		}else{
	 			height = this.original.height()-69;
	 		}
	 		if(this.opt.pageBar){
	 			height = height - 25; 			
	 		}	 		
	 		this.jgridDataDIV.css("height", height+"px");
	 	} 
	 	var hd=this.jgridHeaderTable.height()+this.jgridDataDIV.height();
	 	this.jHeaderAndDataDIV.css('height',hd);
 	},
 	//datagrid的第一行 用于设定列宽度,高度为0
 	/*
 	setFirstRow:function(){
 		try{
 			var newTr=this.jgridDataTable[0].insertRow(0);
 		newTr.style.height="0";
 		newTr.style.border="0";
 		//有checkbox要创建一个checkbox
		if(this.opt.checkbox){
			var td=newTr.insertCell(-1);
			td.style.textAlign='center';
			td.style.width='25px';
			td.style.height="0";
			td.style.border="0";
		}
		var c = this.opt.col;
		var i=this.opt.checkbox?1:0;
		for(i;i<c.length;i++){
			var td=newTr.insertCell(i);
			if(c[i].width==0){
				td.style.display='none';
			}else{
				td.style.width=c[i].width+'px';
			}
			if(c[i].align!=undefined){
				td.style.textAlign=c[i].align;
			}
			//td.style.height="0";
		}
 		}catch(e){
 			alert(e);
 		}
 		
 	},*/
 	changeCursorState:function(e,obj){
 		if((e.clientX-getOffset(obj).left)>(obj.offsetWidth-3)){
 			obj.style.cursor = "E-resize";
 		}else{
 			obj.style.cursor='default';
 		}
 	},
 	startColResize:function(e,obj){
 		var me=this;
 		var td = e.target || a.srcElement;
 		if(td.tagName != "TD"){td = this.getFirstParentOfType(td, "TD");}
 		if (td.tagName != "TD" ||td.style.cursor == "default")return;
 		var c=this.opt.checkbox?1:0;
 		var colspan=td.getAttribute('colspan')?(parseInt(td.getAttribute('colspan'))-1):0;
 		var i=td.getAttribute('tdIndex')?(parseInt(td.getAttribute('tdIndex'))+c+colspan):td.cellIndex;
 		var h = e.clientX,
 		f = me.jgridHeaderTable.offsetWidth,//table width
 		//d = parseInt(obj.width());//找到对应th
 		d=parseInt(this.jgridHeaderTable[0].rows[0].childNodes[i].style.width);
 		document.body.onmousemove = function(a) {
 			stopDefault(a);
            me.doColResize(a || window.event, td, d, h, f);
        }
		document.body.onmouseup = function() {
            me.stopColResize()
        }
 	},
 	//tdWidth为对应th的宽度
 	doColResize:function(e, td, tdWidth, clientX, headerTableWidth) {//e,td,tdWidth,e.clientX,headerTableWidth
		$(td).css('cursor','E-resize');
        this.resized = td;
        var mouseMove=e.clientX - clientX;
        var tdw = tdWidth + mouseMove,
        tbw = headerTableWidth + mouseMove;
        var c=this.opt.checkbox?1:0;
        var colspan=td.getAttribute('colspan')?(parseInt(td.getAttribute('colspan'))-1):0;
        var i=td.getAttribute('tdIndex')?(parseInt(td.getAttribute('tdIndex'))+c+colspan):td.cellIndex;
        this.setColumnSize(i,tdw, tbw);
 	},
 	//设置对应index的td宽度,table宽度
 	setColumnSize: function(i,tdw,tbw){
 		if(tdw<0)return;
 		this.jgridHeaderTable[0].rows[0].childNodes[i].style.width= tdw + "px";
 		if(this.jgridDataTable[0].rows[0]){
 			this.jgridDataTable[0].rows[0].childNodes[i].style.width = tdw + "px";
 		}
        this.jgridHeaderTable.css({width:tbw});
        this.jgridDataTable.css({width:tbw});
    },
 	stopColResize:function(){
 		document.body.onmousemove = "";
        document.body.onmouseup = "";
 	},
 	getFirstParentOfType :function(a, b) {
        for (; a && a.tagName != b && a.tagName != "BODY";) a = a.parentNode;
        return a
    },
    //同步表头和数据表列的宽度
    syncWidth:function(){
    	var hT=this.jgridHeaderTable[0].rows[0];
	    var dT=this.jgridDataTable[0].rows[0];
	    var c=this.opt.checkbox?1:0;
    	if(this.isMultirow){
    		if(dT){
    			for(var i=0;i<hT.childNodes.length;i++){
		    		dT.childNodes[i].style.width=hT.childNodes[i].style.width;
		    	}
    		}
    	}else{
	    	if(dT){
	    		for(var i=0;i<hT.childNodes.length;i++){
		    		dT.childNodes[i].style.width=hT.childNodes[i].style.width;
		    	}
	    	}
    	}
    	
    },
 	renderTo: function(id){
 		this.opt.render = id;
		$("#"+this.opt.id).remove();		
		this.original = $("#"+this.opt.render);
		var width = this.original.width();
		var height = this.original.height();
		this.grid.css({
			width: width-4,
			height: height-4,
			margin: '1px'
		});
		this.original.append(this.grid);		
 	},
 	setData: function(data){
 		if(data){
 			this.removeAll();
 		 	for(var i=0; i<data.length; i++){
 		 		this.addRecord(data[i]);
 		 	}
 		 	this.total = data.length;
 		 	this.calculatePage();
 		 	this.setPageBar();
 		 	if(this.opt.pageBar){
 		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(this.opt.curPage);
 		 		this.jgridPage.find("input[name='curPage']").val(this.opt.curPage);
 		 		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(this.opt.countEveryPage);
 		 		this.jgridPage.find("div[name='pageInfo3']").find("span[name='totalCount']").text(this.total);
 		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='pageCount']").text(this.pageCount);
 		 	}
 	 	}
 	 	this.syncWidth();		
 	},
 	//新建一条记录 参数为JSON格式
 	addRecord: function(data){
 		var object = this;
 		var idex=object.jgridDataDIV.find('tr').length+1;
 		dataHTML = "<tr name='data' index='"+idex+"'";
 		if(this.opt.rowIdIndex){
 			dataHTML+=" id='"+data[this.opt.rowIdIndex]+"' ";
 		}
 		if(this.opt.striped){
 			var n=(!isNaN(this.opt.striped)?this.opt.striped:1)+1;
 			if(idex%n==0){
	 			dataHTML+=" class='grid-row-alt'";
	 		}
 		}
 		dataHTML+=" >";
 		if(this.opt.checkbox){
 			dataHTML += "<td style='width:25px;' align='center'><input type='checkbox' name='checkChild' ";
 			if(this.opt.rowIdIndex){
 				dataHTML +="value='"+data[this.opt.rowIdIndex]+"'";
	 		}
 			dataHTML +="/></td>"; 			
 		}
 		var w, x=0;
 		var cm = this.dataCols;//用dataCols确定对应的信息项
 		for(var i=0;i<cm.length;i++){
 			w = (cm[i].width!=undefined?cm[i].width:100)-2;
			var value = data[cm[i].dataIndex]!=null?data[cm[i].dataIndex]:"";
			if(cm[i].dictType){//如果有dictType属性，表示是字典值，需要转换成字典名字
				var dt='dictType_'+cm[i].dictType;
				var dict=eval(dt.replace(/(\.|-)/g,'_'));//将.-转换成_
				value=dict[value];
				if(!value){
					value = "";
				}
			}
			var title = value;
			if(cm[i].formatter){
				value=cm[i].formatter(data); 
			}else if(cm[i].renderer){
				value = cm[i].renderer(value);
			}
			if(cm[i].pop){
				value='<a href="javascript:void(0);" onmouseover="showPop($(this),\''+cm[i].pop.title+'\',\'content\')" onmouseout="hidePop();">'+value+'</a>';
			} 				
			dataHTML += "<td align='"+(cm[x].align?cm[x].align:'center')+"'  style='"+(w<0?"display:none;":"")+"' selects='0' title='"+title+"'>"+value+"</td>";
 		}
 		dataHTML += "</tr>";
 		var dataJ = $(dataHTML);
 		this.jgridDataDIV.find("table").append(dataJ);
 		this.registerEvent(dataJ); 
 		this.registerCheckboxEvent(2);
 	},
 	//删除某行记录，如果行数为空则删除所选记录
 	removeRecord: function(index){
 		var tr = this.jgridDataDIV.find("table tr[name='data']");		
 		if(index||index==0){
 			$(tr.get(index)).fadeOut('slow',function(){$(this).remove()});
 		}else{	 		
			tr.each(function(){
				if($(this).find('input[type="checkbox"]').attr("checked")){
					$(this).remove();				
				}
			});
 		}
 	},
 	//删除所有数据
 	removeAll: function(){
 		var tr = this.jgridDataDIV.find("table tr[name='data']");	
 		tr.each(function(){				
			$(this).remove();								
		});
 	},
 	registerHeaderEvent: function(){
 		var object = this;
 		this.jgridHeaderTable.find("tr").css("cursor", "point");
 		var td = this.jgridHeaderTable.find("tr td");
 		td.hover(
			function(){
				if($(this).find("span").val()=='-1'){
					$(this).find("span").addClass("orderUpShow");
				}else{
					$(this).find("span").addClass("orderShow");
				}				
			},
			function(){
				if($(this).find("span").val()=='-1'){
					$(this).find("span").removeClass("orderUpShow");
				}else{
					$(this).find("span").removeClass("orderShow");
				}	
			}
		);
 		td.find("span").click(function(){
 			if($(this).parent().parent().find("input[type='checkbox']").attr("name")){
 				return;
 			}
 			var val = $(this).val();
 			var order = "";
 			if(val=='-1'){
 				$(this).val(1);
 				order = " DESC ";
 				$(this).removeClass("orderUpShow");
 				$(this).addClass("orderShow");
 			}else{
 				$(this).val(-1);
 				order = " ASC ";
 				$(this).removeClass("orderShow");
 				$(this).addClass("orderUpShow");
 			}
 			var name = $(this).parent().parent().attr("name"); 
 			if($(this).parent().parent().attr("sort")&&$(this).parent().parent().attr("sort")!='undefined'&&$(this).attr("sort")!='') name=$(this).attr("sort"); 			
 			if(object.opt.listeners.orderdata) object.opt.listeners.orderdata(name, order);
 			else{
 				object.opt.orderBy = "ORDER BY " + name + " " + order;
 				object.loadData(object.opt.curPage);
 			}
 		});
 	},
 	//注册事件
	registerEvent: function(tr){
		var object = this;
		tr.hover(
			function(){
					$(this).addClass('grid-row-over');
			},
			function(){
					$(this).removeClass('grid-row-over');
			}
		);
		tr.click(function(){
			var b = $(this).find("input[type='checkbox']").attr("checked");
			if(b) {
				$(this).find("input[type='checkbox']").attr("checked", false);
				$(this).parent().find("input[name='checkAll']").attr("checked", false);
				$(this).removeClass('grid-row-selected');
			}else {
				if(!object.opt.checkbox){
					if(object.opt.selectModel==JGrid.SINGLE_SELECTION){
						object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
						object.jgridDataDIV.find('tr').removeClass('grid-row-selected');				
					}
					
					$(this).addClass('grid-row-selected');
				}else{
					if(object.opt.selectModel==JGrid.SINGLE_SELECTION){
						object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
						object.jgridDataDIV.find('tr').removeClass('grid-row-selected');			
					}
					$(this).find("input[type='checkbox']").attr("checked", true);
					$(this).addClass('grid-row-selected');
				}				
			}
			var row = "{";
			$(this).each(function(){
				var cm = object.dataCols;
				var i=0, j=0;
				$(this).find("td").each(function(){
					if((object.opt.checkbox&&i>0)||(!object.opt.checkbox)){
						row += cm[j++].dataIndex+":'"+$(this).text()+"',";														
					}
					i++;
				});
			});
			row = row.substring(0, row.length-1) + "}";
			//单击事件回调函数
			if(object.opt.listeners&&object.opt.listeners.click) object.opt.listeners.click(eval("("+row+")"));
		});
		tr.dblclick(function(){
			//选中当前行 取消选中其他行
			var idex=$(this).attr('index');
			$(this).parent().find("input[type='checkbox']").attr("checked", false);
			object.jgridDataDIV.find('tr').	each(function(){
				if($(this).attr('index')!=idex){
					$(this).removeClass('grid-row-selected');
				}else{
					$(this).find("input").attr('checked', true)
				}
			});
			object.jHeaderAndDataDIV.find("input[name='checkAll']").attr("checked", false);	
			if(object.opt.crudOpera&&!object.opt.listeners&&!object.opt.listeners.dblclick){
				$(this).each(function(){
					var id = "";
					var cm = object.opt.col;
					var i=0, j=0;
					$(this).find("td").each(function(){
						if((object.opt.checkbox&&i>0)||(!object.opt.checkbox)){
							if(cm[j++].dataIndex==object.opt.crudID){
								id = $(this).text();	
								return false;
							}							
						}
						i++;
					});
					object.showFormDialog('修 改', id);		
				});
			}else{
				var row = "{";
				$(this).each(function(){
					var cm = object.opt.col;
					var i=0, j=0;
					$(this).find("td").each(function(){
						if((object.opt.checkbox&&i>0)||(!object.opt.checkbox)){
							row += cm[j++].dataIndex+":'"+$(this).text()+"',";														
						}
						i++;
					});
				});
				row = row.substring(0, row.length-1) + "}";
				//双击事件回调函数
				if(object.opt.listeners&&object.opt.listeners.dblclick) object.opt.listeners.dblclick(eval("("+row+")"));
			}
		});		
	},
	//注册Checkbox事件
	registerCheckboxEvent: function(index){
		var object = this;
		if(index==1){//注册CheckAll
			this.jgridHeaderTable.find("tr td").find("input[name='checkAll']").click(function(){
				if(object.opt.selectModel==JGrid.SINGLE_SELECTION) return;
				if($(this).attr('checked')){
					object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', true);
					object.jgridDataDIV.find('tr').addClass('grid-row-selected');
				}else{
					object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
					object.jgridDataDIV.find('tr').removeClass('grid-row-selected');
				}
			});
		}else{//CheckChild
			object.jgridDataDIV.find("table tr[name='data']").find("input").click(function(e){
				object.jgridHeaderTable.find("tr td input[name='checkAll']").attr("checked", false);
				var checked = $(this).attr("checked");
				if(object.opt.selectModel==JGrid.SINGLE_SELECTION){					
					object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
					object.jgridDataDIV.find('tr').removeClass('grid-row-selected');
					$(this).parent().parent().addClass('grid-row-selected');
					$(this).attr("checked", checked);
				}else{
					if(checked){
						$(this).parent().parent().addClass('grid-row-selected');
					}else{
						$(this).parent().parent().removeClass('grid-row-selected');
					}	
				}
				var row = "{";
				var cm = object.opt.col;					
				var i=0, j=0;
				$(this).parent().parent().find("td").each(function(){
					if(i>0) row += cm[j++].dataIndex+":'"+$(this).text()+"',";
					else i++;
				});				
				row = row.substring(0, row.length-1) + "}";
				if(object.opt.listeners&&object.opt.listeners.clickcheckbox) object.opt.listeners.clickcheckbox(eval("("+row+")"), checked);
				$.stopBubble(e);
			});
		}
	},
	//注册单个checkbox事件
	bindCheckboxEvent:function(c){
		var object=this;
		c.click(function(e){
			var checked = $(this).attr("checked");
			if(object.opt.selectModel==JGrid.SINGLE_SELECTION){					
				object.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
				object.jgridDataDIV.find('tr').removeClass('grid-row-selected');
				$(this).parent().parent().addClass('grid-row-selected');
				$(this).attr("checked", checked);
			}else{
				if(checked){
					$(this).parent().parent().addClass('grid-row-selected');
				}else{
					$(this).parent().parent().removeClass('grid-row-selected');
				}	
			}
			var row = "{";
			var cm = object.opt.col;					
			var i=0, j=0;
			$(this).parent().parent().find("td").each(function(){
				if(i>0) row += cm[j++].dataIndex+":'"+$(this).text()+"',";
				else i++;
			});				
			row = row.substring(0, row.length-1) + "}";
			if(object.opt.listeners&&object.opt.listeners.clickcheckbox) object.opt.listeners.clickcheckbox(eval("("+row+")"), checked);
			$.stopBubble(e);
		});
		
	},
	//注册分页栏事件
	registerPageBarEvent: function(){
		var object = this;
		this.jgridPage.find("div").hover(
 			function(){
 				if($(this).attr("name")!='pageInfo'&&$(this).attr("name")!='pageInfo2'&&$(this).attr("name")!='pageInfo3'){
 					if($(this).attr("able")=='true'){
 						$(this).addClass($(this).attr('name')+'_hover');
 					}
 				}
 			},
 			function(){
 				$(this).removeClass($(this).attr('name')+'_hover');
 			}
 		);
		this.jgridPage.find("div").click(function(){
 			var name = $(this).attr("name");
 			if(name=='pageInfo'||name=='pageInfo2'||name=='pageInfo3') return;
 			var able = $(this).attr("able"); 		
 			if(able=='false') return ;
			if(name=='first'){//首页
				object.loadData(1);
				object.pageParam = "countEveryPage="+object.opt.countEveryPage+"&curPage=1";
			}else if(name=='previous'){//上页
				object.loadData(parseInt(object.opt.curPage)-1);				
			}else if(name=='next'){//下页
				object.loadData(parseInt(object.opt.curPage)+1);
			}else if(name=='last'){//尾页
				object.loadData(object.pageCount);
			}else if(name=='refresh'){//刷新
				if(!object.opt.data){
					object.loadData();
				}
			}
 		});
 		//////go
		this.jgridPage.find("input[name='curPage']").keydown(function(event){
			 if (event.keyCode == 13) {  
			 	var regx = /^[1-9]\d*$/;
			 	event.preventDefault();
				event.stopPropagation();
			 	if(!regx.exec($(this).val())) return false;
			 	var val = $(this).val();
			 	if(parseInt(val)>object.pageCount){
			 		object.loadData(object.pageCount);
			 		$(this).val(object.pageCount);
			 	}else{
			 		object.loadData($(this).val());
			 	}
			 }
		});
		//跳转按钮
		this.jgridPage.find("input[name='pageGo']").click(function(){
			var val=object.jgridPage.find("input[name='curPage']").val();
			var regx = /^[1-9]\d*$/;
			if(!regx.exec(val)){ alert('数据不合法');return false;}
			if(parseInt(val)>object.pageCount){
		 		object.loadData(object.pageCount);
		 		object.jgridPage.find("input[name='curPage']").val(object.pageCount);
		 	}else{
		 		object.loadData(val);
		 	}
		});
		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").keydown(function(event){
			 $(this).focus();
			 if (event.keyCode == 13) {
				event.preventDefault();
				event.stopPropagation();
			 	var regx = /^[1-9]\d*$/;
			 	if(!regx.exec($(this).val())) return false;
			 	if(parseInt($(this).val())>2147483647){
			 		alert('数值过大');return false;
			 	}
			 	object.opt.countEveryPage = $(this).val();
				object.loadData(1);
			 }
		});
	},
	//注册增删改查事件
	registerCRUDEvent: function(){
		this.registerButtonHoverEvent();
		var object = this;		
		this.jgridcrudBtn.find("button").click(function(){
			var name = $(this).attr('name');
			if(object.opt.listeners&&object.opt.listeners.curdButtonClick) {
				object.opt.listeners.curdButtonClick(name);
				return ;
			}
			if(name=='add'){//新建
				object.showFormDialog('新 建');
			}else if(name=='update'){//修改
				var rows = object.getSelections();
				if(rows.length!=1){
					JDialog.tip('请选择一条记录进行修改。');					
					return;
				}
				object.showFormDialog('修 改', rows[0][object.opt.crudID]);
			}else if(name=='remove'){//删除
				object.removeData();
			}else if(name=='refresh'){//刷新
				object.loadData();
			}
		});	
	},
	registerButtonHoverEvent: function(id, index){
		if(id){
			this.jgridcrudBtn.find("button[id='"+id+"']").hover(function(){
				if($(this).attr("toggle")!='') return;
				$(this).addClass("JButtonClass"+(index?"_large":"")+"_hover");
			},function(){
				if($(this).attr("toggle")!='') return;
				$(this).removeClass("JButtonClass"+(index?"_large":"")+"_hover");
			});
		}else{
			this.jgridcrudBtn.find("button").hover(function(){
				if($(this).attr("toggle")!='') return;
				$(this).addClass('JButtonClass_hover');	
			},function(){
				if($(this).attr("toggle")!='') return;
				$(this).removeClass('JButtonClass_hover');	
			});
		}		
	},
	selectRecord: function(index){
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var i=0;
		tr.each(function(){
			if(i==index){
				$(this).find('input[type="checkbox"]').attr("checked", true);
				$(this).addClass('grid-row-selected');
				return false;
			}
			i++;
		});
	},
	//获取选中的行数
	getSelectionIndex: function(){
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var data = "";
		var i=0;
		tr.each(function(){
			if($(this).find('input[type="checkbox"]').attr("checked")){								
				data += i + ",";
			}
			i++;
		});
		if(data.length>0) data = data.substring(0, data.length-1);
		return data;
	},
	//获取选中的行数，返回的是JSON格式
	getSelections: function(){
		var cm = this.opt.col;
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var datas = "[";
		tr.each(function(){
			if($(this).find('input[type="checkbox"]').attr("checked")){
				var i=0, j=0;
				datas += "{";
				$(this).find("td").each(function(){
					if(i>0){
						datas += "'"+cm[j++].dataIndex+"': '"+$(this).text()+"',";
					}
					i++;
				});
				datas = datas.substring(0, datas.length-1) + "},";
			}
		});
		if(datas.length>1) datas = datas.substring(0, datas.length-1) + ']';
		else datas += "]";
		return eval('('+datas+')');
	},
	//获取选中的checkbox的id字符串拼接,必须指定rowIdIndex
	getSelectCheckbox:function(){
		if(this.opt.rowIdIndex!=undefined&&this.opt.rowIdIndex!=''){
			var ids='';
			var c=$('input[name=checkChild]');
			c.each(function(){
				if($(this).attr('checked')=='checked'){
					ids+=$(this).val()+ ',';
				}
			});
			if(ids.length>0){
				ids = ids.substring(0, ids.length-1);
			}
			return ids;
		}else{
			alert('未指定rowIdIndex');
		}
	},
	//获取选中的行信息，返回的是二维数组
	getSelectData: function(){
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var datas = new Array();
		var i=0;		
		tr.each(function(){
			if($(this).find('input[type="checkbox"]').attr("checked")){
				var record = new Array();
				var j=0, k=0;
				$(this).find("td").each(function(){
					if(j>0){
						record[k++] = $(this).text();
					}
					j++;
				});
				datas[i++] = record;				
			}
		});
		return datas;
	},
	//获取选中行的某列数据，返回的是一维数组
	getSelectColData: function(index){
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var datas = new Array();
		var i=0;
		tr.each(function(){
			if($(this).find('input[type="checkbox"]').attr("checked")){				
				var j=0;
				$(this).find("td").each(function(){
					if(j==index+1){
						datas[i++] = $(this).text();
						return false;
					}
					j++;
				});	
			}
		});
		return datas;
	},
	//获取某列数据，返回的是一维数组
	getColData: function(index){
		var tr = this.jgridDataDIV.find("table tr[name='data']");
		var datas = new Array();
		var i=0;
		tr.each(function(){						
			var j=0;
			$(this).find("td").each(function(){
				if(j==index+1){
					datas[i++] = $(this).text();
					return false;
				}
				j++;
			});				
		});
		return datas;
	},
	//获取行总数
	getRecordCount: function(){
		return this.jgridDataDIV.find("table tr[name='data']").length;
	},
	//计算总页数
	calculatePage: function(){
		this.pageCount = Math.ceil(this.total/this.opt.countEveryPage); //获取总页数
	},
	//设置分页面板
	setPageBar: function(){		
		if(this.opt.curPage==1){
			this.jgridPage.find("div[name='first']").addClass('first_disable');
			this.jgridPage.find("div[name='first']").attr('able', false);
			this.jgridPage.find("div[name='previous']").addClass('previous_disable');
			this.jgridPage.find("div[name='previous']").attr('able', false);
		}else{
			this.jgridPage.find("div[name='first']").removeClass('first_disable');
			this.jgridPage.find("div[name='first']").attr('able', true);
			this.jgridPage.find("div[name='previous']").removeClass('previous_disable');
			this.jgridPage.find("div[name='previous']").attr('able', true);
		}
		if(this.opt.curPage==this.pageCount||this.pageCount<=0){
			this.jgridPage.find("div[name='next']").addClass('next_disable');
			this.jgridPage.find("div[name='next']").attr('able', false);
			this.jgridPage.find("div[name='last']").addClass('last_disable');
			this.jgridPage.find("div[name='last']").attr('able', false);
		}else{
			this.jgridPage.find("div[name='next']").removeClass('next_disable');
			this.jgridPage.find("div[name='next']").attr('able', true);
			this.jgridPage.find("div[name='last']").removeClass('last_disable');
			this.jgridPage.find("div[name='last']").attr('able', true);
		}
	},
	//设置顶部工具栏
	setTopBar: function(bar){
		this.jgridcrudBtn.css("height", "25px");
		this.jgridDataDIV.css("height", (this.jgridDataDIV.height()-30)+"px");
		this.jgridcrudBtn.append(bar);
	},
	//获取顶部工具栏
	getTopBar: function(){
		return this.jgridcrudBtn;
	},
	
	setToggle: function(id, bool){
		if(typeof(id)=='object'){
			obj = id;
		}else{	
			obj = this.jgridcrudBtn.find("button[id='"+id+"']");			
		}
		if(bool){
			this.jgridcrudBtn.find("button").each(function(){
				$(this).attr("toggle", "");
				if($(this).width()+16==JButton.LARGE){
					$(this).css({
						"background": "url('tools/jquery/images/jquery-grid/btn-bg.png') no-repeat 1px"
					});
				}else{
					$(this).css({
					//	"background": "url('tools/jquery/images/jquery-grid/btn-bg.gif') no-repeat 1px"
					});
				}
			});
			obj.attr("toggle", "1");
		}else{
			obj.attr("toggle", "");
		}
		if(obj.width()+16==JButton.LARGE){		 
			obj.css({
				"background": "url('tools/jquery/images/jquery-grid/btn-bg"+(bool?"-toggle":"")+".png') no-repeat " + (bool?"":"1px")
			});			
		}else{
			obj.css({
				"background": "url('tools/jquery/images/jquery-grid/btn-bg"+(bool?"-toggle":"")+".gif') no-repeat " +  + (bool?"":"1px")
			});
		}
	},
	
	/**
	 * 添加顶部工具栏按钮
	 */
	addButton: function(btn){
		var button = new JButton({
			id: btn.id,
			text: btn.text,
			width: btn.width,
			icon: btn.icon,
			margin: '0 1 0 0'
		});
		var comp = button.getComp();
		button.remove();
		comp.click(btn.handler);
		this.jgridcrudBtn.append(comp);
		comp.attr("toggle", "");
		if(btn.width==JButton.LARGE){
			this.registerButtonHoverEvent(button.opt.id, 1);
		}else{
			this.registerButtonHoverEvent(button.opt.id);
		}		
	},
	setTitle: function(title){
		this.jgridcrudBtn.find(".jgridTitle").html(title);
	},
	/**
	 * 给加载参数赋值
	 * @params
	 */
	setParams: function(params){
		this.opt.params = params;
	},
	loadMask: function(text){
		this.jgridMain.mask(text);
	},
	unLoadMask: function(){
		this.jgridMain.unmask();
	},
	/**
	 * 数据加载
	 * @param {} curPage 当前页
	 */
	loadData: function(curPage){
		var object = this; 
		this.jgridMain.mask("正在加载数据，请稍候...");
		var datas = "";
		if(this.opt.pageBar){ //分页
			if(!curPage) curPage = this.opt.curPage;
			datas += "countEveryPage="+object.opt.countEveryPage+"&curPage="+curPage;
		}
		if(this.opt.params){
			for(o in this.opt.params){
				datas += "&"+o+"="+this.opt.params[o];
			}
		}
		if(this.fields){
			datas += "&fields="+this.fields;
		}
		if(this.orderBy!=''){
			datas += "&orderBy="+this.opt.orderBy;
		}
		$.ajax({
			url: this.opt.dataUrl,
			type: 'POST',
			cache: false,
			data: encodeURI(datas),
			dataType: 'json',
			success: function(res){
				object.removeAll();
		 		var data = res.data;		 	
		 		if(res.total){
		 			object.total = res.total;
		 		}else{
		 			object.total = data.length;
		 		}
		 		for(var i=0; i<data.length; i++){		 			
		 			object.addRecord(data[i]);//生成一行数据
		 		}
		 		object.syncWidth();		 		
		 		object.opt.curPage = curPage;
		 		object.calculatePage();
		 		object.setPageBar();
		 		object.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(object.opt.curPage);
		 		object.jgridPage.find("input[name='curPage']").val(object.opt.curPage);
		 		object.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(object.opt.countEveryPage);
		 		object.jgridPage.find("div[name='pageInfo3']").find("span[name='totalCount']").text(object.total);
		 		object.jgridPage.find("div[name='pageInfo2']").find("span[name='pageCount']").text(object.pageCount);
		 		object.jgridMain.unmask();
		 		if(object.opt.listeners.afterload) object.opt.listeners.afterload(data, object.total);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				if(XMLHttpRequest.responseText.indexOf("loginpage")>0){
					top.window.location.href='./login.zb';	
				}else{
					object.jgridMain.unmask();
					JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);	
				}
			}
		});
	},
	//显示Form编辑框
	showFormDialog: function(title, id){
		var object = this;			
		var form = $("<div class='jgridFormDIV'><table width='100%' style='padding: 5px;' border='0' cellpadding='0' cellspacing='1'></table></div>");
		var cm = this.opt.col;
	 	for(var i=0; i<cm.length; i++){
	 		if(!cm[i].inputType) cm[i].inputType='text';
	 		if(typeof(cm[i].update)=='undefined') cm[i].update = true;
	 		if(cm[i].inputType=='hidden'){
	 			form.find('table').append("<tr>" +
	 					"<td colspan='2'><input type='hidden' name='"+cm[i].dataIndex+"'/></td>" +
	 					"</tr>");
	 		}else if(cm[i].inputType=='combo'){
	 			var comboHTML = "<tr>" +
	 					"<td width='100' height='30'>"+cm[i].text+"</td>" +
	 					"<td align='left'><select name='"+cm[i].dataIndex+"' style='width: 90%;'>";
	 			if(cm[i].dataUrl){
	 				var valueField = cm[i].valueField;
	 				var displayField = cm[i].displayField;
	 				$.ajax({
						url: cm[i].dataUrl,
						type: 'POST',
						cache: false,
						//data: 'id='+id,
						dataType: 'json',
						success: function(res){					
							var select = $(".jgridFormDIV table tr td select");
							var datas = res.data;
							for(var x=0; x<datas.length; x++){
								select.append("<option value='"+datas[x][valueField]+"'>"+datas[x][displayField]+"</option>");
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);									
						}
					});
	 			}else{
	 				if(cm[i].datas){
	 					for(var x=0; x<cm[i].datas.length; x++){
	 						comboHTML += "<option value='"+cm[i].datas[x][0]+"'>"+cm[i].datas[x][1]+"</option>";
	 					}
	 				}
	 			}
	 			comboHTML += "</select></td></tr>";
	 			form.find('table').append(comboHTML);
	 		}else if(cm[i].inputType=='textarea'){	 		
	 		}else{
	 			form.find('table').append("<tr>" +
	 					"<td width='100' height='30'>"+cm[i].text+"</td>" +
	 					"<td align='left'><input type='"+cm[i].inputType+"' name='"+cm[i].dataIndex+"' " +
	 							((cm[i].inputType=='checkbox'||cm[i].inputType=='radio')?"":"style='width: 90%;'")+"" +
	 							(id&&!cm[i].update?'disabled':'') + " /></td>" +
	 					"</tr>");
	 		} 		
	 	}
	 	var formDialog = null;
		formDialog = new JDialog({
	 		title: title,	
	 		width: object.opt.crudWidth?object.opt.crudWidth:($(document.body).outerWidth() * 0.98),
	 		height: object.opt.crudHeight?object.opt.crudHeight:($(document.body).outerHeight() * 0.98),
	 		icon: 'images/jquery-tab/users.png',
	 		btns: JDialog.OK_CANCEL_OPTION,
	 		listeners: {
	 			buttonClick: function(btnId, text){
	 				if(btnId=='ok'){
	 					object.editData(formDialog, id);
	 				}else{
	 					formDialog.closeDialog();
	 				}
	 			}
	 		}
	 	});
		formDialog.show();
	 	formDialog.add(form);
	 	if(id){//加载Form数据
	 		$(".JDialogMainDIV").mask('正在加载数据，请稍候...');
	 		$.ajax({
				url: object.opt.findUrl,
				type: 'POST',
				cache: false,
				data: encodeURI('id='+id),
				dataType: 'json',
				success: function(res){					
					if(res.success){
						$(".jgridFormDIV table tr td input").each(function(){
							if($(this).attr('type')=='checkbox'){
								$(this).attr('checked', res[$(this).attr('name')]=='1'?true:false);
							}else{
								$(this).val(res[$(this).attr('name')]);
							}
						});
						$(".JDialogMainDIV").unmask();
					}else{
						$(".JDialogMainDIV").unmask();
						JDialog.showMessageDialog('出错', '数据加载失败。', JDialog.ERROR);		
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					$("#JDialogMainDIV").unmask();
					JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);							
				}
			});
	 	}
	},
	//新建数据
	editData: function(dialog, id){
		var object = this;
		var cm = this.opt.col;
		var datas = '';
		for(var i=0; i<cm.length; i++){
			if(typeof(cm[i].update)=='undefined') cm[i].update = true;			
			if(id){
				if(!cm[i].update) continue ;
			}
			if(cm[i].inputType=='checkbox'){
				datas += cm[i].dataIndex + "=" + ($(".jgridFormDIV table tr td").find("input[name='"+cm[i].dataIndex+"']").attr('checked')?'1':'0') + "&";
			}else if(cm[i].inputType=='combo'){
				datas += cm[i].dataIndex + "=" + $(".jgridFormDIV table tr td").find("select[name='"+cm[i].dataIndex+"'] option:selected").val() + "&";
			}else{
				datas += cm[i].dataIndex + "=" + $(".jgridFormDIV table tr td").find("input[name='"+cm[i].dataIndex+"']").val() + "&";
			}
	 	}
	 	datas = datas.substring(0, datas.length-1);
	 	$(".JDialogMainDIV").mask('正在保存数据，请稍候...');
		$.ajax({
			url: object.opt.saveUrl,
			type: 'POST',
			cache: false,
			data: encodeURI(datas),
			dataType: 'json',
			success: function(res){
				$(".JDialogMainDIV").unmask();
				if(res.success){
					dialog.closeDialog(function(){
						JDialog.showMessageDialog('信息', '数据保存成功。', JDialog.INFORMATION, function(id, text){
							if(id=='ok'){							
								object.loadData();
							}
						});		
					});					
				}else{					 		
					JDialog.showMessageDialog('错误', '数据保存失败。', JDialog.ERROR);		
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$(".JDialogMainDIV").unmask();
				JDialog.showMessageDialog('异常', '服务器出现异常，数据保存失败。', JDialog.ERROR);						
			}
		});
	},
	//删除数据
	removeData: function(callback){
		var object = this;
		var rows = object.getSelections();
		if(rows.length>0){			 
			JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
		 		if(id=='yes'){
		 			object.jgridMain.mask('正在删除数据，请稍候...');
					var id = '';
					for(var i=0; i<rows.length; i++){
						id += rows[i][object.opt.crudID] + ',';
					}
					id = id.substring(0, id.length-1);
					var datas = "id="+id;
					$.ajax({
						url: object.opt.removeUrl,
						type: 'POST',
						cache: false,
						data: encodeURI(datas),
						dataType: 'json',
						success: function(res){
							object.jgridMain.unmask();						
							if(res.success){
								var msg = res.msg?res.msg:'数据删除成功。';
								JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
									if(id=='ok'){
										object.loadData(1);
									}
								});	
							}else{					 		
								JDialog.tip('数据删除失败。', JDialog.ERROR);							
							}
							//回调函数
							if(callback) callback(res.success);
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							object.jgridMain.unmask();
							JDialog.tip('服务器出现异常，数据删除失败。', JDialog.ERROR);
						}
					});
		 		}
		 	});				
		}else{
				JDialog.tip('请选择记录进行删除。');
		}				
	},
	//获取一行json数据
	loadRowData:function(url,params,newRowId){
		var me=this.
		$.getJSON(url,params,function(d){
			me.insertRow(newRowId,d);
		});
	},
	//ajax获取一条记录并插入第一行
	getInsertRow:function(url,rowId){
		var me=this;
		var id=me.opt.rowIdIndex;
		$.getJSON('dcEhrAllInfoAjax.zb',id+'='+rowId,function(d){
			me.insertRow(rowId,d);
		});
	},
	//ajax获取一条记录并更新对应行
	getUpdateRow:function(url,rowId){
		var me=this;
		var id=me.opt.rowIdIndex;
		$.getJSON('dcEhrAllInfoAjax.zb',id+'='+rowId,function(d){
			me.updateRow(rowId,d);
		});
	},
	//插入数据表第一行
	insertRow:function(newRowId,data){
		var newTr=this.jgridDataTable[0].insertRow(0);
		newTr.id=newRowId;
		newTr.setAttribute('name','data');
		var i2=0;
		if(this.opt.checkbox){
			var td=newTr.insertCell(0);
			td.style.textAlign='center';
			td.innerHTML='<input type="checkbox" name="checkChild" value="'+newRowId+'">';
			i2=1;
			var cb=td.getElementsByTagName('input')[0];
			this.bindCheckboxEvent($(cb));
		}
		var c = this.opt.col;
		for(var i=0;i<c.length;i++){
			var td=newTr.insertCell(i+i2);
			//有checkbox要创建一个checkbox
			var v=data[c[i].dataIndex]!=undefined?data[c[i].dataIndex]:'';
			if(c[i].formatter){
				v=eval( c[i].formatter + "( data )" ); 
			}else if(c[i].renderer){
				v = c[i].renderer(v);
			}
			td.innerHTML= v;
			if(c[i].width==0){
				td.style.display='none';
			}
			if(c[i].align!=undefined){
				td.style.textAlign=c[i].align;
			}
		}
		this.registerEvent($(newTr));
		this.syncWidth();
	},
	//更新一行的数据
	updateRow:function(rowId,data){
		var tr=document.getElementById(rowId);
		var c = this.opt.col;
		for(var i=0;i<c.length;i++){
			var td=tr.cells(this.opt.checkbox?i+1:i);
			var v=data[c[i].dataIndex]!=undefined?data[c[i].dataIndex]:'';
			if(c[i].formatter){
				v=eval( c[i].formatter + "( data )" ); 
			}else if(c[i].renderer){
				v = c[i].renderer(v);
			}
			td.innerHTML= v;
		}
	},
	//移除一行
	removeRow:function(rowId){
		$('#'+rowId).find('td').each(function(){
			$(this).fadeOut('slow');
		});
		$('#'+rowId).fadeOut('slow',function(){$(this).remove();});
	}
 };
 
 JGrid.SINGLE_SELECTION = 1;
 JGrid.MULTIPLE_SELECTION = 2;
 /**一些工具方法**/
var getPosition = function(a, b) {
    if (!b && !_isChrome) {
        var c = getOffset(a);
        return [c.left, c.top]
    }
    for (var b = b || document.body, d = a, k = 0, g = 0; d && d != b;) k += d.offsetLeft - d.scrollLeft,
    g += d.offsetTop - d.scrollTop,
    d = d.offsetParent;
    b == document.body && (_isIE ? (g += document.body.offsetTop || document.documentElement.offsetTop, k += document.body.offsetLeft || document.documentElement.offsetLeft) : _isFF || (k += document.body.offsetLeft, g += document.body.offsetTop));
    return [k, g]
}
function getAbsoluteTop(htmlObject){
	return getOffset(htmlObject).top;
}

function getOffsetSum(elem) {
	var top=0, left=0;
	while(elem) {
		top = top + parseInt(elem.offsetTop);
		left = left + parseInt(elem.offsetLeft);
		elem = elem.offsetParent;
	}
	return {top: top, left: left};
}
function getOffsetRect(elem) {
	var box = elem.getBoundingClientRect();
	var body = document.body;
	var docElem = document.documentElement;
	var scrollTop = window.pageYOffset || docElem.scrollTop || body.scrollTop;
	var scrollLeft = window.pageXOffset || docElem.scrollLeft || body.scrollLeft;
	var clientTop = docElem.clientTop || body.clientTop || 0;
	var clientLeft = docElem.clientLeft || body.clientLeft || 0;
	var top  = box.top +  scrollTop - clientTop;
	var left = box.left + scrollLeft - clientLeft;
	return { top: Math.round(top), left: Math.round(left) };
}
function getOffset(elem) {
	if (elem.getBoundingClientRect) {
		return getOffsetRect(elem);
	} else {
		return getOffsetSum(elem);
	}
}
function stopDefault(e) { 
     if (e && e.preventDefault) {//FF
        e.preventDefault();
    }else{ 
        window.event.returnValue = false;//IE
    }
    return false;
}
//显示浮动信息
var showPop=function(obj,title,content){
	var os=obj.offset();
	$('#grid_pop').css({left:os.left+obj.width(),top:os.top-$('#grid_pop').height()+20,display:'block'}).find('.pop_content').html(content);
	$('#grid_pop').find('.pop_title').html(title);
}
var hidePop=function(){
	$('#grid_pop').hide();
}
//初始化浮动窗口
var initPop=function(o){
	var pop=$('<div id="grid_pop" class="pop" onmouseover="$(this).show();" onmouseout="$(this).hide();"><div class="pop_title">标题</div><div class="pop_nw"><div class="pop_ne"><div class="pop_n"></div></div></div> <div class="pop_w"><div class="pop_e"><div class="pop_content"></div></div></div> <div class="pop_sw"><div class="pop_se"><div class="pop_s"></div></div></div></div>');
	pop.css({width:'474px',display:'none'}).appendTo(document.body);
}
//取出所有字典值
var loadDictByType=function(o){
	var c=o.col;
	for(var i=0;i<c.length;i++){
		if(c[i].dictType){
			var t=document.createElement('script');
	        t.src='dict/getPlainDictByType.zb?dictType='+c[i].dictType;
	        t.type='text/javascript';
	        document.getElementsByTagName('head')[0].appendChild(t);
		}
	}
}