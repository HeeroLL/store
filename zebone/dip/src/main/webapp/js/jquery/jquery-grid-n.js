/**
 * JQuery Grid
 * @param title :grid标题
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
   listeners:{dblclick:function(row){}}
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
 	this.total = 0;//记录总数 
 	this.pageCount = 0; //总页数
 	this.viewModel='list';
 	
 	this.options={
 		//默认参数
 		id:"JGrid-"+Math.floor(Math.random()*0x100000).toString(16),
 		width:'auto',
 		height:'auto',
 		checkbox:false,
 		striped:true,
 		crudOpera:false,
 		pageBar:true,
 		col:[],
 		render:$(document.body),
 		listeners:{},
 		countEveryPage:100,
 		curPage:1,
 		orderBy:'',
 		allowSort:false
 	};
 	
 	$.extend(this.options,opt);
 	
 	var me=this;
 	var o=me.options;
 	me.id=o.id;
 	//生成table
 	me.init();
 	var cm = o.col;//column
 	var table = me.jgridHeaderTable;
 	if(Object.prototype.toString.call(cm[0]) === '[object Array]'){
 		me.isMultirow=true;
 	}
 	//生成header的列
 	var tableWidth = 0;
 	me.createDataTd(cm);
 	me.createHeaderTh();
 	me.setTableWidth();
 	me.createHeaderTds();
 	me.removeTemp();
 	me.setColResize();
 	
 	
 	this.loadPanel();
 	this.initData();
 	
 	this.registerCheckboxEvent(1);
 	if(o.allowSort) this.registerHeaderEvent();
 	var me = this;
 	this.jHeaderAndDataDIV.scroll(function(){
 		me.jgridDataDIV.width(me.jgridMain.width()+$(this).scrollLeft());	 		 		 		
 	});
 	if(o.height=='auto'){
 		$(window).resize(function(){
 			me.loadPanel();  
 		});
 	}
 };
 
 JGrid.prototype = {
 	init:function(){
 		var me=this;
 		var o=me.options;
 		loadDictByType(o);
		if(typeof(o.render)=='string'){
			o.render = $("#"+o.render);
		}
		if(o.render){
			//this.original.addClass('grid'); 
		}
		initPop(o);
		o.render.append("<div id='"+o.id+"' class='jgridDIV'></div>");
		me.grid = $('#'+o.id); 
 		me.grid.append("<div id='"+me.id+"-main' class='jgridMain'>" +
 			"<div id='"+me.id+"-header' class='j-panel-header'><div class='j-panel-title'></div></div>" +
 			"<div id='"+me.id+"-toolbar' class='jgridToolBar'></div>" +
 			"<div id='"+me.id+"-headerAndDataDiv' class='jHeaderAndDataDIV'><div class='jHeaderDIV'><table id='"+me.id+"-headerTable' class='jgridHeaderTable' border='0' cellpadding='0' cellspacing='0'></table></div>" +
 			"<div id='"+me.id+"-dataDiv' class='jgridDataDIV'>" +
 			"<table id='"+me.id+"-dataTable' border='0' cellpadding='0' cellspacing='1'></table>" +
 			"</div><div id='"+me.id+"-cardDiv' class='j-card-div'>card</div></div>" +
 			"<div id='"+me.id+"-page' class='jgridPage'></div></div>");
		
		me.jgridMain = $('#'+me.id+'-main');
		me.titleBar=$('#'+me.id+'-header');
 		me.jgridToolBar = $('#'+me.id+'-toolbar');
	 	me.jHeaderAndDataDIV = $('#'+me.id+'-headerAndDataDiv');
	 	me.jgridHeaderTable = $('#'+me.id+'-headerTable');
	 	me.jgridDataDIV = $('#'+me.id+'-dataDiv');
	 	me.jgridDataTable=$('#'+me.id+'-dataTable');
	 	me.cardDiv=$('#'+me.id+'-cardDiv');
	 	me.jgridPage = $('#'+me.id+'-page');
	 	//标题栏
		if(o.title){
			me.setTitle(o.title);
		}else{
			me.titleBar.hide();
		}
		//按钮栏
		if(o.crudOpera){
			me.setOperButtons();
		}
		//卡片模式
		if(o.cardView){
			me.setSwitchButton();
		}
		//分页栏
		if(o.pageBar){
 			me.createFooter();
	 	}else{
	 		me.jgridPage.hide();
	 	}
 	},
 	createHeaderTds:function(){
 		var o=this.options;
 		var cm = this.options.col;
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
		 	if(o.checkbox){
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
	 	if(this.isMultirow&&this.options.checkbox){
	 		var trs=table[0].getElementsByTagName('tr');
			var firstTd=trs[1].getElementsByTagName('td')[0]
			$("<td align='center' rowspan="+(trs.length-1)+"><input type='checkbox' name='checkAll' /></td>").insertBefore($(firstTd));
		}
 	},
 	//设置操作按钮
 	setOperButtons:function(){
 		var curdbuttons='';
 		curdbuttons+="<a href='###' class='btn btn-plain' name='add'><span class='btn-left'><span class='btn-text btn-icon-left icon-add'>新 建</span></span></a>";
 		curdbuttons+="<span class='btn-plain-separator'></span>";
 		curdbuttons+="<a href='###' class='btn btn-plain' name='update'><span class='btn-left'><span class='btn-text btn-icon-left icon-edit'>修 改</span></span></a>";
 		curdbuttons+="<span class='btn-plain-separator'></span>";
 		curdbuttons+="<a href='###' class='btn btn-plain' name='remove'><span class='btn-left'><span class='btn-text btn-icon-left icon-remove'>删 除</span></span></a>";
 		curdbuttons+="<span class='btn-plain-separator'></span>";
 		curdbuttons+="<a href='###' class='btn btn-plain' name='refresh'><span class='btn-left'><span class='btn-text btn-icon-left icon-reload'>刷 新</span></span></a>";
 		this.jgridToolBar.append(curdbuttons);
 		this.registerCRUDEvent();
 	},
 	//设置展示模式切换按钮
 	setSwitchButton:function(){
 		var me=this;
 		var sBtn='<label><input type="radio" name="'+me.id+'-switch" value="c"/>卡片模式</label><label><input type="radio" name="'+me.id+'-switch" value="l" checked/>列表模式</label>';
 		me.jgridToolBar.append(sBtn);
 		$('input[name='+me.id+'-switch]').click(function(){
 			if(this.value=='l'){
 				me.showListView();
 			}else{
 				me.showCardView();
 			}
 		});
 	},
 	//设置BottomBar
 	createFooter:function(){
 		this.jgridPage.append("<div name='pageInfo' class='pageInfo'>" +
 				"<div name='pageInfo3' class='page_count'>共 <span name='totalCount'>0</span>条记录 每页显示<input type='text' name='countEveryPage' value='"+this.options.countEveryPage+"' class='countEveryPage'/>条</div>"+
 				"<div class='jgridPageButton page_first' title='首 页' name='first' able=true></div>" +
 				"<div class='jgridPageButton page_prev' title='上一页' name='previous' able=true></div>" +
 				"<div class='pageNo' name='pageInfo2' title='页码'><span name='curPage'>"+this.options.curPage+"</span>/<span name='pageCount'>0</span></div>"+
 				"<div class='jgridPageButton page_next' title='下一页' name='next' able=true></div>" +
 				"<div class='jgridPageButton page_last' title='尾 页' name='last' able=true></div>"+
 				"<div class='jgridPageButton page_refresh' title='刷 新' name='refresh' able=true></div>"+
 				"<div class='pageJump'>跳到第<input type='text' name='curPage' value='"+this.options.curPage+"' class='goToPageNo'/>页<input type='button'title='GO' name='pageGo' class='pageGo'/></div>"+
 				"</div>");
 		this.registerPageBarEvent();
 	},
 	initData:function(){
 		var o=this.options;
 		var data = o.data;
	 	if(data){
		 	for(var i=0; i<data.length; i++){
		 		this.addRecord(data[i]);
		 	}
		 	this.total = data.length;
		 	this.calculatePage();
		 	this.setPageBar();
		 	if(o.pageBar){
		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(o.curPage);
		 		this.jgridPage.find("input[name='curPage']").val(o.curPage);
		 		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(o.countEveryPage);
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
	 		var c=this.options.checkbox?1:0;
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
 		var o=this.options;
 		var tr=$('<tr style="height: auto;"></tr>');
	 	this.jgridHeaderTable.append(tr);
	 	if(o.checkbox){tr.append($('<th style="height:0;width:25px;"></th>'));}
	 	for(var i=0;i<this.dataCols.length;i++){
	 		var cl=this.dataCols[i];
	 		tr.append($('<th style="height:0;width:'+cl.width+'px;'+((cl.width==0)?'display:none;':'')+'"></th>'));
	 	}
 	},
 	//设置datatable宽度
 	setTableWidth:function(){
 		var tableWidth=this.options.checkbox?25:0;
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
 		var c=this.options.checkbox?1:0;
		var x=td.getAttribute('tdIndex')?(parseInt(td.getAttribute('tdIndex'))):td.cellIndex;
        var y=td.parentNode.rowIndex;
        var col=this.options.col;
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
 	//设置宽度高度,如果设置了高度grid高度为设置的高度,未设置高度,为body高度
 	loadPanel: function(){
 		var me=this;
 		var o=me.options;
 		me.grid.css({width:o.width});
		var gridHeight=o.height?o.height:$(document.body).height();
		if(o.height!='auto'){
			var gridHeight=o.height;
			me.grid.height(gridHeight);
	 	 	me.jgridMain.height(gridHeight);
	 	 	var headerDataHeight=gridHeight;
	 	 	if(me.jgridHeaderTable.width()>me.jgridMain.width()){
	 			if(ie6){
	 				headerDataHeight+=10;
	 			}
	 		}
	 		if(o.title){
	 			headerDataHeight-=me.titleBar.outerHeight();
	 		}
	 		headerDataHeight-=me.jgridToolBar.outerHeight();
	 		if(o.pageBar){
	 			headerDataHeight-=me.jgridPage.outerHeight(); 		
	 		}
		 	me.jHeaderAndDataDIV.css('height',headerDataHeight);
		 	me.cardDiv.css('height',headerDataHeight);
		 	var dataDivHeight=headerDataHeight-me.jgridHeaderTable.outerHeight();
		 	me.jgridDataDIV.css("height",dataDivHeight);
		}
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
 	setColResize:function(){
 		var me=this;
 		me.jgridHeaderTable.find('.header').find('td').mousedown(function(e){
	 		e.preventDefault();
	 		me.startColResize(e,$(this));
	 	}).mousemove(function(e){
	 		me.changeCursorState(e,this);
	 	});
 	},
 	startColResize:function(e,obj){
 		var me=this;
 		var td = e.target || a.srcElement;
 		if(td.tagName != "TD"){td = this.getFirstParentOfType(td, "TD");}
 		if (td.tagName != "TD" ||td.style.cursor == "default")return;
 		var c=this.options.checkbox?1:0;
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
        var c=this.options.checkbox?1:0;
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
        this.jHeaderAndDataDIV.css({height:$('.jgridMain').height()+ 15});
        this.cardDiv.hide();
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
	    var c=this.options.checkbox?1:0;
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
 	setData: function(data){
 		var o=this.options;
 		if(data){
 			this.removeAll();
 			this.curPageData=data;
 		 	for(var i=0; i<data.length; i++){
 		 		this.addRecord(this.curPageData[i]);
 		 	}
 		 	this.total = data.length;
 		 	this.calculatePage();
 		 	this.setPageBar();
 		 	if(o.pageBar){
 		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(o.curPage);
 		 		this.jgridPage.find("input[name='curPage']").val(o.curPage);
 		 		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(o.countEveryPage);
 		 		this.jgridPage.find("div[name='pageInfo3']").find("span[name='totalCount']").text(this.total);
 		 		this.jgridPage.find("div[name='pageInfo2']").find("span[name='pageCount']").text(this.pageCount);
 		 	}
 	 	}
 	 	this.syncWidth();		
 	},
 	//列表模式
 	showListView:function(){
 		this.jgridHeaderTable.show(); 
 		this.jgridDataDIV.show();
 		this.cardDiv.hide();
 		this.loadListData();
 		this.viewModel='list';
 	},
 	//卡片模式
 	showCardView:function(){
 		this.jgridHeaderTable.hide(); 
 		this.jgridDataDIV.hide();
 		this.cardDiv.show();
 		this.loadCardData();
 		this.viewModel='card';
 	},
 	loadListData:function(){
 		this.removeAll();
 		for(var i=0; i<this.curPageData.length; i++){		 			
 			this.addRecord(this.curPageData[i]);//生成一行数据
 		}
 		this.syncWidth();	
 	},
 	loadCardData:function(){
 		this.jgridMain.mask("正在加载数据，请稍候...");
 		var html='<div class="j-card-inner">';
 		var t=this.options.temp?this.options.temp:alert('未定义卡片模板');
 		var c=this.curPageData;
 		for(var i=0;i<c.length;i++){
 			html += t.temp(c[i]);
 		}
 		html+='</div>'
 		this.cardDiv.empty();
 		this.cardDiv.append(html);
 		this.jgridMain.unmask();
 	},
 	//新建一条记录 参数为JSON格式
 	addRecord: function(data, trstyle,tdhstyle){
 		var me = this;
 		var o=me.options;
 		var idex=me.jgridDataDIV.find('tr').length+1;
 		dataHTML = "<tr name='data' index='"+idex+"'";
 		if(o.rowIdIndex){
 			dataHTML+=" id='"+data[o.rowIdIndex]+"' ";
 		}
 		if(o.striped){
 			var n=(!isNaN(o.striped)?o.striped:1)+1;
 			if(idex%n==0){
	 			dataHTML+=" class='grid-row-alt'";
	 		}
 		}
 		
 		if(trstyle != ''){
 			dataHTML+=" style='"+trstyle+"' ";
 		}
 		
 		dataHTML+=" >";
 		if(o.checkbox){
 			dataHTML += "<td style='width:25px;' align='center'><input type='checkbox' name='checkChild' ";
 			if(o.rowIdIndex){
 				dataHTML +="value='"+data[o.rowIdIndex]+"'";
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
			if(i==0){
				if(tdhstyle != ''){
					dataHTML += "<td align='"+(cm[i].align?cm[i].align:'center')+"'  style='"+tdhstyle+"' selects='0' title='"+title+"'>"+value+"</td>";				 			
		 		}else{
					dataHTML += "<td align='"+(cm[i].align?cm[i].align:'center')+"'  style='"+(w<0?"display:none;":"")+"' selects='0' title='"+title+"'>"+value+"</td>";				 			
		 		}
			}else{
				dataHTML += "<td align='"+(cm[i].align?cm[i].align:'center')+"'  style='"+(w<0?"display:none;":"")+"' selects='0' title='"+""+"'>"+value+"</td>";
			}
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
 		var me = this;
 		var o=me.options;
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
 			if(o.listeners.orderdata) o.listeners.orderdata(name, order);
 			else{
 				o.orderBy = "ORDER BY " + name + " " + order;
 				me.loadData(o.curPage);
 			}
 		});
 	},
 	//注册事件
	registerEvent: function(tr){
		var me = this;
		var o=me.options;
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
				if(!o.checkbox){
					if(o.selectModel==JGrid.SINGLE_SELECTION){
						me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
						me.jgridDataDIV.find('tr').removeClass('grid-row-selected');				
					}
					
					$(this).addClass('grid-row-selected');
				}else{
					if(o.selectModel==JGrid.SINGLE_SELECTION){
						me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
						me.jgridDataDIV.find('tr').removeClass('grid-row-selected');			
					}
					$(this).find("input[type='checkbox']").attr("checked", true);
					$(this).addClass('grid-row-selected');
				}				
			}
			var row = "{";
			$(this).each(function(){
				var cm = me.dataCols;
				var i=0, j=0;
				$(this).find("td").each(function(){
					if((o.checkbox&&i>0)||(!o.checkbox)){
						row += cm[j++].dataIndex+":'"+$(this).text()+"',";														
					}
					i++;
				});
			});
			row = row.substring(0, row.length-1) + "}";
			//单击事件回调函数
			if(o.listeners&&o.listeners.click) o.listeners.click(eval("("+row+")"));
		});
		tr.dblclick(function(){
			//选中当前行 取消选中其他行
			var idex=$(this).attr('index');
			$(this).parent().find("input[type='checkbox']").attr("checked", false);
			me.jgridDataDIV.find('tr').	each(function(){
				if($(this).attr('index')!=idex){
					$(this).removeClass('grid-row-selected');
				}else{
					$(this).find("input").attr('checked', true)
				}
			});
			me.jHeaderAndDataDIV.find("input[name='checkAll']").attr("checked", false);	
			if(o.crudOpera&&!o.listeners&&!o.listeners.dblclick){
				$(this).each(function(){
					var id = "";
					var cm = o.col;
					var i=0, j=0;
					$(this).find("td").each(function(){
						if((o.checkbox&&i>0)||(!o.checkbox)){
							if(cm[j++].dataIndex==o.crudID){
								id = $(this).text();	
								return false;
							}							
						}
						i++;
					});
					me.showFormDialog('修 改', id);		
				});
			}else{
				var row = "{";
				$(this).each(function(){
					var cm = o.col;
					var i=0, j=0;
					$(this).find("td").each(function(){
						if((o.checkbox&&i>0)||(!o.checkbox)){
							row += cm[j++].dataIndex+":'"+$(this).text()+"',";														
						}
						i++;
					});
				});
				row = row.substring(0, row.length-1) + "}";
				//双击事件回调函数
				if(o.listeners&&o.listeners.dblclick) o.listeners.dblclick(eval("("+row+")"));
			}
		});		
	},
	//注册Checkbox事件
	registerCheckboxEvent: function(index){
		var me = this;
		var o=me.options;
		if(index==1){//注册CheckAll
			me.jgridHeaderTable.find("tr td").find("input[name='checkAll']").click(function(){
				if(o.selectModel==JGrid.SINGLE_SELECTION) return;
				if($(this).attr('checked')){
					me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', true);
					me.jgridDataDIV.find('tr').addClass('grid-row-selected');
				}else{
					me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
					me.jgridDataDIV.find('tr').removeClass('grid-row-selected');
				}
			});
		}else{//CheckChild
			me.jgridDataDIV.find("table tr[name='data']").find("input").click(function(e){
				me.jgridHeaderTable.find("tr td input[name='checkAll']").attr("checked", false);
				var checked = $(this).attr("checked");
				if(o.selectModel==JGrid.SINGLE_SELECTION){					
					me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
					me.jgridDataDIV.find('tr').removeClass('grid-row-selected');
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
				var cm = o.col;					
				var i=0, j=0;
				$(this).parent().parent().find("td").each(function(){
					if(i>0) row += cm[j++].dataIndex+":'"+$(this).text()+"',";
					else i++;
				});				
				row = row.substring(0, row.length-1) + "}";
				if(o.listeners&&o.listeners.clickcheckbox) o.listeners.clickcheckbox(eval("("+row+")"), checked);
				$.stopBubble(e);
			});
		}
	},
	//注册单个checkbox事件
	bindCheckboxEvent:function(c){
		var me=this;
		var o=me.options;
		c.click(function(e){
			var checked = $(this).attr("checked");
			if(o.selectModel==JGrid.SINGLE_SELECTION){					
				me.jgridDataDIV.find("table tr[name='data']").find("input").attr('checked', false);
				me.jgridDataDIV.find('tr').removeClass('grid-row-selected');
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
			var cm = o.col;					
			var i=0, j=0;
			$(this).parent().parent().find("td").each(function(){
				if(i>0) row += cm[j++].dataIndex+":'"+$(this).text()+"',";
				else i++;
			});				
			row = row.substring(0, row.length-1) + "}";
			if(o.listeners&&o.listeners.clickcheckbox) o.listeners.clickcheckbox(eval("("+row+")"), checked);
			$.stopBubble(e);
		});
		
	},
	//注册分页栏事件
	registerPageBarEvent: function(){
		var me = this;
		var o=me.options;
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
				me.loadData(1);
				me.pageParam = "countEveryPage="+o.countEveryPage+"&curPage=1";
			}else if(name=='previous'){//上页
				me.loadData(parseInt(o.curPage)-1);				
			}else if(name=='next'){//下页
				me.loadData(parseInt(o.curPage)+1);
			}else if(name=='last'){//尾页
				me.loadData(me.pageCount);
			}else if(name=='refresh'){//刷新
				if(!o.data){
					me.loadData();
				}
			}
 		});
 		//////go
		this.jgridPage.find("input[name='curPage']").keydown(function(event){
			 if (event.keyCode == 13) {  
			 	var regx = /^[1-9]\d*$/;
			 	if(!regx.exec($(this).val())) return false;
			 	var val = $(this).val();
			 	if(parseInt(val)>me.pageCount){
			 		me.loadData(me.pageCount);
			 		$(this).val(me.pageCount);
			 	}else{
			 		me.loadData($(this).val());
			 	}
			 }
		});
		this.jgridPage.find("input[name='pageGo']").click(function(){
			var val=me.jgridPage.find("input[name='curPage']").val();
			var regx = /^[1-9]\d*$/;
			if(!regx.exec(val)){ alert('数据不合法');return false;}
			if(parseInt(val)>me.pageCount){
		 		me.loadData(me.pageCount);
		 		me.jgridPage.find("input[name='curPage']").val(me.pageCount);
		 	}else{
		 		me.loadData(val);
		 	}
		});
		this.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").keydown(function(event){
			 if (event.keyCode == 13) {
			 	var regx = /^[1-9]\d*$/;
			 	if(!regx.exec($(this).val())) return false;
			 	o.countEveryPage = $(this).val();
				me.loadData(1);
			 }
		});
	},
	//注册增删改查事件
	registerCRUDEvent: function(){
		var me = this;
		var o=me.options;		
		me.jgridToolBar.find("a").click(function(){
			var name = $(this).attr('name');
			if(o.listeners&&o.listeners.curdButtonClick) {
				o.listeners.curdButtonClick(name);
				return ;
			}
			if(name=='add'){//新建
				me.showFormDialog('新 建');
			}else if(name=='update'){//修改
				var rows = me.getSelections();
				if(rows.length!=1){
					JDialog.tip('请选择一条记录进行修改。');					
					return;
				}
				me.showFormDialog('修 改', rows[0][o.crudID]);
			}else if(name=='remove'){//删除
				me.removeData();
			}else if(name=='refresh'){//刷新
				me.loadData();
			}
		});	
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
		var cm = this.options.col;
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
		if(this.options.rowIdIndex!=undefined&&this.options.rowIdIndex!=''){
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
		this.pageCount = Math.ceil(this.total/this.options.countEveryPage); //获取总页数
	},
	//设置分页面板
	setPageBar: function(){		
		if(this.options.curPage==1){
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
		if(this.options.curPage==this.pageCount||this.pageCount<=0){
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
		this.jgridDataDIV.css("height", (this.jgridDataDIV.height()-30)+"px");
		this.jgridToolBar.append(bar);
	},
	//获取顶部工具栏
	getTopBar: function(){
		return this.jgridToolBar;
	},
	
	setToggle: function(id, bool){
		if(typeof(id)=='object'){
			obj = id;
		}else{	
			obj = this.jgridToolBar.find("button[id='"+id+"']");			
		}
		if(bool){
			this.jgridToolBar.find("button").each(function(){
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
		this.jgridToolBar.append(comp);
		comp.attr("toggle", "");
		if(btn.width==JButton.LARGE){
			this.registerButtonHoverEvent(button.options.id, 1);
		}else{
			this.registerButtonHoverEvent(button.options.id);
		}		
	},
	setTitle: function(title){
		this.titleBar.find(".j-panel-title").html(title);
	},
	/**
	 * 给加载参数赋值
	 * @params
	 */
	setParams: function(params){
		this.options.params = params;
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
		var me = this;
		var o=me.options;
		this.jgridMain.mask("正在加载数据，请稍候...");
		var datas = "";
		if(o.pageBar){ //分页
			if(!curPage) curPage = o.curPage;
			datas += "countEveryPage="+o.countEveryPage+"&curPage="+curPage;
		}
		if(o.params){
			for(a in o.params){
				datas += "&"+a+"="+o.params[a];
			}
		}
		if(me.fields){
			datas += "&fields="+me.fields;
		}
		if(me.orderBy!=''){
			datas += "&orderBy="+o.orderBy;
		}
		$.ajax({
			url: o.dataUrl,
			type: 'POST',
			cache: false,
			data: encodeURI(datas),
			dataType: 'json',
			success: function(res){
		 		me.curPageData= res.data;		 	
		 		if(res.total){
		 			me.total = res.total;
		 		}else{
		 			me.total = me.curPageData.length;
		 		}
		 		if(me.viewModel=='card'){
		 			me.loadCardData();
		 		}else{
		 			me.loadListData();
		 		}
		 		me.options.curPage = curPage;
		 		me.calculatePage();
		 		me.setPageBar();
		 		me.jgridPage.find("div[name='pageInfo2']").find("span[name='curPage']").text(o.curPage);
		 		me.jgridPage.find("input[name='curPage']").val(o.curPage);
		 		me.jgridPage.find("div[name='pageInfo']").find("input[name='countEveryPage']").val(o.countEveryPage);
		 		me.jgridPage.find("div[name='pageInfo3']").find("span[name='totalCount']").text(me.total);
		 		me.jgridPage.find("div[name='pageInfo2']").find("span[name='pageCount']").text(me.pageCount);
		 		me.jgridMain.unmask();
		 		if(o.listeners.afterload) o.listeners.afterload(data, me.total);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				me.jgridMain.unmask();
				JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);				
			}
		});
	},
	//显示Form编辑框
	showFormDialog: function(title, id){
		var me = this;
		var o=me.options;		
		var form = $("<div class='jgridFormDIV'><table width='100%' style='padding: 5px;' border='0' cellpadding='0' cellspacing='1'></table></div>");
		var cm = me.options.col;
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
	 		width: o.crudWidth?o.crudWidth:($(document.body).outerWidth() * 0.98),
	 		height: o.crudHeight?o.crudHeight:($(document.body).outerHeight() * 0.98),
	 		icon: 'images/jquery-tab/users.png',
	 		btns: JDialog.OK_CANCEL_OPTION,
	 		listeners: {
	 			buttonClick: function(btnId, text){
	 				if(btnId=='ok'){
	 					me.editData(formDialog, id);
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
				url: o.findUrl,
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
		var me = this;
		var o=me.options;
		var cm = thmeis.options.col;
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
			url: o.saveUrl,
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
								me.loadData();
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
		var me = this;
		var rows = me.getSelections();
		if(rows.length>0){			 
			JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
		 		if(id=='yes'){
		 			me.jgridMain.mask('正在删除数据，请稍候...');
					var id = '';
					for(var i=0; i<rows.length; i++){
						id += rows[i][o.crudID] + ',';
					}
					id = id.substring(0, id.length-1);
					var datas = "id="+id;
					$.ajax({
						url: me.options.removeUrl,
						type: 'POST',
						cache: false,
						data: encodeURI(datas),
						dataType: 'json',
						success: function(res){
							me.jgridMain.unmask();						
							if(res.success){
								var msg = res.msg?res.msg:'数据删除成功。';
								JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
									if(id=='ok'){
										me.loadData(1);
									}
								});	
							}else{					 		
								JDialog.tip('数据删除失败。', JDialog.ERROR);							
							}
							//回调函数
							if(callback) callback(res.success);
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							me.jgridMain.unmask();
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
		var id=me.options.rowIdIndex;
		$.getJSON(url,id+'='+rowId,function(d){
			me.insertRow(rowId,d);
		});
	},
	//ajax获取一条记录并更新对应行
	getUpdateRow:function(url,rowId){
		var me=this;
		var id=me.options.rowIdIndex;
		$.getJSON('dcEhrAllInfoAjax.zb',id+'='+rowId,function(d){
			me.updateRow(rowId,d);
		});
	},
	/**将json数据插入数据表指定行
		jsondata,数据
		index 插入行索引 可为空	
		trid,新行ID 可为空
	*/
	insertRow:function(data,index,newRowId){
		var newTrIndex=index&&index!=''?index:0;
		var newTr=this.jgridDataTable[0].insertRow(newTrIndex);
		newTr.id=newRowId;
		newTr.setAttribute('name','data');
		var i2=0;
		if(this.options.checkbox){
			var td=newTr.insertCell(0);
			td.style.textAlign='center';
			td.innerHTML='<input type="checkbox" name="checkChild" value="'+newRowId+'">';
			i2=1;
			var cb=td.getElementsByTagName('input')[0];
			this.bindCheckboxEvent($(cb));
		}
		var c = this.options.col;
		for(var i=0;i<c.length;i++){
			var td=newTr.insertCell(i+i2);
			//有checkbox要创建一个checkbox
			var v=data[c[i].dataIndex]!=undefined?data[c[i].dataIndex]:'';
			if(c[i].formatter){
				v=c[i].formatter(data); 
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
		var c = this.options.col;
		for(var i=0;i<c.length;i++){
			var td=tr.cells(this.options.checkbox?i+1:i);
			var v=data[c[i].dataIndex]!=undefined?data[c[i].dataIndex]:'';
			if(c[i].formatter){
				v=c[i].formatter(data); 
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
//模板
String.prototype.temp = function(obj) {
    return this.replace(/\$\w+\$/gi, function(matchs) {
        var returns = obj[matchs.replace(/\$/g, "")];		
        return (returns + "") == "undefined"? "": returns;
    });
};