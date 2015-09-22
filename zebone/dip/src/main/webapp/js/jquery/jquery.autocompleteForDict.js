/**
   Ajax Autocomplete, version 2.0
   
   两种返回值形式，
   1.只改变当前文本框值：需要提供文本数组：如：['January','February','March','April']
   2.改变当前和隐藏文本框值：json数组：格式：[{id:'AF',name:'Afghanistan'},{id:'AL',name:'Albania'},{id:'DZ',name:'Algeria'}]
   调用方法：$('#元素').autocomplete(options);
   参数
   var options={
   		width:提示框的宽度(可选),
   		maxHeight:提示框的最大高度,默认为300,
		select:true 是否可以下拉选择,
		multiple:true 是否同时多个选择,默认为false
		serviceUrl:'auto-test.zb'//查询服务器返回数组的url,数组在 json.data里 ，并要返回当前文本的值 json.query
		cacheToLocal:true/false//将服务器数据缓存到本地,将在本地数据中匹配,
		params: { country:'Yes' },//附加参数
		textField:'name',//文本框显示值对应的json的name
		valueField:{dataIndex:'id',dataName:'hiddenInputName',dataId:'hiddenInputId'},//json的name和隐藏文本的name,判断body中是否有name为对应项的，没有添加到body中
		//回调函数:
		onSelect: function(suggestions){ alert('你选择了: ' + suggestions); },
		//本地自动建议选项:
		lookup: ['January', 'February', 'March', 'April', 'May'],
		deferRequestBy: 0, //延迟请求,毫秒
		minChars:2,//几个字符以上开始提示
		delimiter: ,//分隔符,正则或字符
		//当json中含有多列数据时,设置显示的列，对每一列的定义.如果没有定义默认用textField
		col:[
				{width:100,dataIndex:'id'},
				{width:100,dataIndex:'name'}
			],
		//是否分页 默认为false
		paging:{
			pageSize: 5 //每页显示条数
		}
	};
	3.没有对应值将显示"没有匹配的结果"。
*/
(function($) {

  var reEscape = new RegExp('(\\' + ['/', '.', '*', '+', '?', '|', '(', ')', '[', ']', '{', '}', '\\'].join('|\\') + ')', 'g');

  function fnFormatResult(value, data, currentValue) {
    var pattern = '(' + currentValue.replace(reEscape, '\\$1') + ')';
    if(value){
    	return value.replace(new RegExp(pattern, 'gi'), '<strong>$1<\/strong>');
    }else{
    	return "";
    }    	
  }

  function Autocomplete(el, options) {
    this.el = $(el);
    this.el.attr('autocomplete', 'off');
    this.el.addClass('combo-text');
    this.suggestions = [];
    this.data = [];
    this.badQueries = [];
    this.selectedIndex = -1;
    this.currentValue = this.el.val();
    this.intervalId = 0;
    this.cachedResponse = [];
    this.onChangeInterval = null;
    this.fixInterval = null;
    this.ignoreValueChange = false;
    this.serviceUrl = options.serviceUrl;
    this.cached = false;
    this.isLocal = false;
    this.options = {
      autoSubmit: false,
      minChars: 1,
      maxHeight: 300,
      deferRequestBy: 0,
      width: 0,
      highlight: true,
      params: {},
      valueField : options.valueField,
      fnFormatResult: fnFormatResult,
      delimiter: null,
      zIndex: 9999,
      select:options.select,//是否有选择图标
      multiple:false,
      paging:false,
      curPage:1	
    };
    this.initialize();
    this.setOptions(options);
  }
  
  $.fn.autocomplete = function(options) {
    return new Autocomplete(this.get(0)||$('<input />'), options);
  };


  Autocomplete.prototype = {

    killerFn: null,

    initialize: function() {
      var me, uid, autocompleteElId, selectElid;
      me = this;
      uid = Math.floor(Math.random()*0x100000).toString(16);

      this.killerFn = function(e) {
      	//如果点击其他位置，关闭建议,点击下拉按钮应不执行此事件
        if ($(e.target).attr('autobtn')!='autobtn'&&$(e.target).parents('.autocomplete').size() === 0) {
          me.killSuggestions();
          me.disableKillerFn();
          if(me.options.select){ me.selectBtn.removeClass('ffb-arrow-focus');}
        }
      };
      this.mainContainerId = 'AutocompleteContainter_' + uid;//最外层容器ID
	  this.contentId='content_'+uid//内容div
      $('<div id="' + this.mainContainerId + '" style="position:absolute;z-index:9999;" class="atc-container"><div id="' + this.contentId + '" class="autocomplete-content"><table class="autocomplete" id="table_' + uid + '" ></table></div></div>').appendTo('body');
      this.mainContainer=$('#'+this.mainContainerId);//最外层容器
      this.content=$('#'+this.contentId);//table外层div
      this.table=$('#table_'+uid);
      //this.fixPosition();
      this.mainContainer.hide();
      if (window.opera) {
        this.el.keypress(function(e) { me.onKeyPress(e); });
      } else {
        this.el.keydown(function(e) { me.onKeyPress(e); });
      }
      this.el.keyup(function(e) { me.onKeyUp(e); });
      this.el.focus(function() { me.fixPosition();if(me.options.select){me.selectBtn.addClass('ffb-arrow-focus'); }});
      
      if(this.options.select){
      	selectElid='Select_'+uid;
      	if(this.el.next().attr('class')=='ffb-arrow'){
      		this.selectBtn=this.el.next();
      		this.selectBtn.attr('id',selectElid);
      		this.selectBtn.attr('autobtn','autobtn');
      	}else{
      		if($('#'+selectElid).length==0){$('<span id="'+selectElid+'" class="ffb-arrow out" autobtn="autobtn"></span>').insertAfter(this.el);}//不能采用绝对定位就插入文档流中
      		this.selectBtn=$('#'+selectElid);
      		this.selectBtn.css({ float: this.el.css('float')});
      	}
      	this.selectBtn.hover(function() {
                    $(this).addClass('over');
                }, function() {
                    $(this).removeClass('over');
                })
                .mousedown(function() {
                    $(this).removeClass('over').addClass('active');
                })
                .mouseup(function() {
                    $(this).removeClass('active').addClass('over');
                })
                .click(function(){me.onSelectClick();});
      }
      if (!this.options.width) { 
	      if(this.options.select){
	      	 this.options.width = this.el.outerWidth()+this.selectBtn.outerWidth(); 
	      }else{
	      	 this.options.width = this.el.width(); 
	      }
      }
      //如果body中没有hidden元素,自动添加hidden元素
      if(this.options.valueField){
      	$('<input type="hidden" />').insertBefore(this.el);
      }
    },
    setOptions: function(options){
      var o = this.options;
      var me=this;
      $.extend(o, options);
      this.el.blur(function() { me.enableKillerFn(); });//失去焦点事件
      if(o.lookup){
        this.isLocal = true;
        if($.isArray(o.lookup)){ o.lookup = { suggestions:o.lookup, data:[] }; }//将lookup转换格式
      }else if(o.cacheToLocal){
      	if(!o.serviceUrl){
      		alert('serviceUrl error'); return;
      	}
      	this.isLocal = true;
      	$.ajax({
			url: me.serviceUrl,
			type: 'POST',
			cache: false,
			data: '',
			dataType: 'json',
			success: function(d){
				o.lookup=d.data;//返回的json
				if($.isArray(o.lookup)){ o.lookup = { suggestions:o.lookup, data:[] }; }
				//如果有默认值
				if(o.valueField){
					if(o.valueField.defaultValue){
					if(o.valueField.dataName){
						$('input[name='+o.valueField.dataName+']').val(o.valueField.defaultValue);
					}
					if(o.valueField.dataId){
						$('#'+o.valueField.dataId).val(o.valueField.defaultValue);
					}	
					}
					var os=o.lookup.suggestions;
					for(var i=0;i<os.length;i++){
						if(o.valueField.defaultValue==os[i][o.valueField.dataIndex]){
							me.el.val(os[i][o.textField]);
						}
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('cache data failed');
			}
		});
      }
      if(o.paging){
      	var pageDiv=$("<div class='paging'>"+
			      	"<div class='pageBarButton page_first' title='首 页' name='first' able=true  autobtn='autobtn'></div>" +
					"<div class='pageBarButton page_prev' title='上 页' name='previous' able=true autobtn='autobtn'></div>" +
					"<div name='pageInfo2'><input type='text'  name='curPage' value='1' title='页码' class='box' autobtn='autobtn'/></div>"+
					"<div class='pageBarButton page_next' title='下 页' name='next' able=true autobtn='autobtn'></div>" +
					"<div class='pageBarButton page_last' title='尾 页' name='last' able=true autobtn='autobtn'></div>"+
					"<div name='pageInfo3' class='total'>共 <span name='pageCount' class='pageCount' autobtn='autobtn'>0</span>页 <span name='totalCount' class='totalCount'>0</span> 条记录"+ 
					"</div>");
      	me.content.append(pageDiv);
      	me.pageBar=this.content.find('.paging');
      	me.pageBar.find('.pageBarButton').click(function(){
      		if($(this).attr('able')=='false'){return;}
      		var name = $(this).attr("name");
      		if(name=='first'){
      			me.loadPageData(1);
			}else if(name=='previous'){
				me.loadPageData(me.options.curPage-1);
			}else if(name=='next'){
				me.loadPageData(me.options.curPage+1);
			}else if(name=='last'){
				me.loadPageData(me.options.pageCount);
			}
      	});
      	me.pageBar.find('.box').keydown(function(e){
			 if (e.keyCode == 13) {  
			 	var regx = /^[1-9]\d*$/;
			 	if(!regx.exec($(this).val())) return false;
			 	var val = $(this).val();
			 	if(parseInt(val)>me.options.pageCount){
			 		me.loadPageData(me.options.pageCount);
			 		$(this).val(me.options.pageCount);
			 	}else{
			 		me.loadPageData($(this).val());
			 	}
			 }
		});
      }
      $('#'+me.mainContainerId).css({ zIndex:o.zIndex });
      me.content.css({ maxHeight: o.maxHeight + 'px', width:o.width });
    },
    /*设置分页栏按钮*/
    setPageBar:function(){
    	this.pageBar.find('.pageCount').html(this.options.pageCount);
    	this.pageBar.find('.totalCount').html(this.options.totalCount);
    	if(this.options.curPage==1){
			this.pageBar.find("div[name='first']").addClass('first_disable');
			this.pageBar.find("div[name='first']").attr('able', false);
			this.pageBar.find("div[name='previous']").addClass('previous_disable');
			this.pageBar.find("div[name='previous']").attr('able', false);
		}else{
			this.pageBar.find("div[name='first']").removeClass('first_disable');
			this.pageBar.find("div[name='first']").attr('able', true);
			this.pageBar.find("div[name='previous']").removeClass('previous_disable');
			this.pageBar.find("div[name='previous']").attr('able', true);
		}
		if(this.options.curPage==this.options.pageCount||this.options.pageCount<=0){
			this.pageBar.find("div[name='next']").addClass('next_disable');
			this.pageBar.find("div[name='next']").attr('able', false);
			this.pageBar.find("div[name='last']").addClass('last_disable');
			this.pageBar.find("div[name='last']").attr('able', false);
		}else{
			this.pageBar.find("div[name='next']").removeClass('next_disable');
			this.pageBar.find("div[name='next']").attr('able', true);
			this.pageBar.find("div[name='last']").removeClass('last_disable');
			this.pageBar.find("div[name='last']").attr('able', true);
		}
    },
    loadPageData:function(pageNum){
    	this.options.curPage=pageNum;
    	this.pageBar.find('.box').val(pageNum);
    	this.getSuggestions(this.el.val());
    },
    /*select*/
    onSelectClick:function(){
      //点击选择就显示全部结果
      if(this.mainContainer.css('display')=='block'){
      	//this.mainContainer.hide();
      	this.killSuggestions();
        this.disableKillerFn();
      }else{
      	  /*this.currentValue = this.el.val();
	      var q = this.getQuery(this.currentValue);
      	  this.selectedIndex = -1;
      	  this.el.focus();
      	  if (q === '' || q.length < this.options.minChars){
	      	if(this.serviceUrl&&!this.paging){
	      		return;//实时查询并且不分页时不提示
	      	}else{
	      		this.getSuggestions(q);
	      	}
	      }*/
      	if(this.serviceUrl&&this.options.paging==false){
	      	//实时查询并且不分页时,暂时全部显示
	      	this.getSuggestions('');
      	}else{
      		this.selectBtn.addClass('autoloading');//show loading
      		this.showAllSuggestions();
      	}
      }
      this.el.focus();
    },
    
    clearCache: function(){
      this.cachedResponse = [];
      this.badQueries = [];
    },
    
    disable: function(){
      this.disabled = true;
    },
    
    enable: function(){
      this.disabled = false;
    },
	
    enableKillerFn: function() {
      var me = this;
      $(document).bind('click', me.killerFn);
    },

    disableKillerFn: function() {
      var me = this;
      $(document).unbind('click', me.killerFn);
    },

    killSuggestions: function() {
      var me = this;
      this.stopKillSuggestions();
      this.intervalId = window.setInterval(function() { me.hide(); me.stopKillSuggestions(); }, 300);
    },

    stopKillSuggestions: function() {
      window.clearInterval(this.intervalId);
      if(this.fixInterval){
      	window.clearInterval(this.fixInterval);//定位定时器
      }
    },

    onKeyPress: function(e){
      if (this.disabled || !this.enabled) { return; }
      // return will exit the function and event will not be prevented
      switch (e.keyCode) {
        case 27: //KEY_ESC:
          this.el.val(this.currentValue);
          this.hide();
          break;
        case 9: //KEY_TAB://if(e.keyCode === 9){ return; }
        case 13: //KEY_ENTER|KEY_return:
          if (this.selectedIndex === -1) {
            this.hide();
            return;
          }
          this.select(this.selectedIndex);
          break;
        case 38: //KEY_UP:
          this.moveUp();
          break;
        case 40: //KEY_DOWN:
          this.moveDown();
          break;
        default:
          return;
      }
      e.stopImmediatePropagation();
      e.preventDefault();
    },

    onKeyUp: function(e) {
      if(this.disabled){ return; }
      switch (e.keyCode) {
      	case 13:
        case 38: //KEY_UP:
        case 40: //KEY_DOWN:
          return;
      }
      clearInterval(this.onChangeInterval);
      if (this.currentValue !== this.el.val()) {
        if (this.options.deferRequestBy > 0) {
          // Defer lookup in case when value changes very quickly:
          var me = this;
          me.onChangeInterval = setInterval(function() { me.onValueChange(); }, this.options.deferRequestBy);
        } else {
          this.onValueChange();
        }
      }
    },
	//文本值改变时 获取建议
    onValueChange: function() {
	  clearInterval(this.onChangeInterval);
      this.currentValue = this.el.val();
      var q = this.getQuery(this.currentValue);
      this.selectedIndex = -1;
      /*if (this.ignoreValueChange) {
        this.ignoreValueChange = false;
        return;
      }*/
      //如果值为空
      if(q === '' || q.length < this.options.minChars) {
      	this.hide();
      } else {
        this.getSuggestions(q);
      }
      if(this.options.valueField){
	      this.setValueField('');
      }
    },

    getQuery: function(val) {
      var d, arr;
      d = this.options.delimiter;
      if (!d) { return $.trim(val); }
      arr = val.split(d);
      return $.trim(arr[arr.length - 1]);
    },

    getSuggestionsLocal: function(q) {
      var ret, arr, len, val, i;
      arr = this.options.lookup;//自定义数组
      len = arr.suggestions.length;
      ret = { suggestions:[], data:[] };
      q = q.toLowerCase();//q:currentValue
	      for(i=0; i< len; i++){
	        val = arr.suggestions[i];
	        valF=(typeof(val)=='string')?val:val[this.options.textField];	        
	        if(valF.toLowerCase().indexOf(q) === 0){
	          ret.suggestions.push(val);
	          ret.data.push(arr.data[i]);
	        }
	      }
      
      return ret;
    },
    /*获取建议：本地或Ajax(ajax分实时和缓存)*/
    getSuggestions: function(q) {
      //show loading
      if(this.options.select){
      	this.selectBtn.addClass('autoloading');
      }
      
      var cr, me;
      cr = this.isLocal ? this.getSuggestionsLocal(q) : this.cachedResponse[q];//初始化提供的数据或者之前缓存的ajax请求结果数组
      if (cr && $.isArray(cr.suggestions)) {
        this.suggestions = cr.suggestions?cr.suggestions:cr.data;
        this.data = cr.data;
        this.suggest();
      } else if(cr && $.isArray(cr.data)){
      	this.suggestions = cr.data;//缓存
        this.data = cr.data;
        this.suggest();
      }else if (!this.isBadQuery(q)) {
        me = this;
        me.options.params.query = q;
        //实时查询
        if(me.options.paging){
        	me.options.params.countEveryPage=me.options.paging.pageSize;
        	me.options.params.curPage=me.options.curPage;
        }
        $.get(encodeURI(this.serviceUrl), me.options.params, function(txt) { me.processResponse(txt);}, 'text');
      }else{
      	this.showNoMatches();//isBadQuery
      }
    },
    showAllSuggestions:function(){
    	var arr,ret;
    	if(this.options.serviceUrl&&this.options.paging){
    		this.getSuggestions('');
    	}else{
    		arr = this.options.lookup;//自定义数组
	    	len = arr.suggestions.length;
	    	ret = { suggestions:[], data:[] };
	    	for(i=0; i< len; i++){
	          val = arr.suggestions[i];
	          ret.suggestions.push(val);
	          ret.data.push(arr.data[i]);
	        }
	        this.suggestions = ret.suggestions;
	        this.data = ret.data;
	        this.suggest();
    	}
    },
    isBadQuery: function(q) {
      var i = this.badQueries.length;
      while (i--) {
        if (q.indexOf(this.badQueries[i]) === 0) { return true; }
      }
      return false;
    },

    hide: function() {
      this.enabled = false;
      this.selectedIndex = -1;
      this.mainContainer.hide();
    },
	//显示下拉框
    suggest: function(){
      if (this.suggestions.length === 0) {
      	this.showNoMatches();
        return;
      }
      var me, len, div, f, v, i, s, mOver, mClick,tr;//add tr
      me = this;
      len = this.suggestions.length;
      f = this.options.fnFormatResult;
      v = this.getQuery(this.currentValue);
      mOver = function(xi) { return function() { me.activate(xi); }; };
      mClick = function(xi) { return function() { me.select(xi); }; };
      this.mainContainer.hide();
      this.table.empty();
      for (i = 0; i < len; i++) {
        s = this.suggestions[i];
        //生成一条建议
        tr=$((me.selectedIndex === i ? '<tr class="selected"' : '<tr') + ' title="' + s + '"></tr>');//<td>' + f(s, this.data[i], v) + '</td>
        var title='';
        title=typeof(s)=='string'?s:s[me.options.textField];//每行的title
        tr.attr('title',title);
        var tds='';
        if(this.options.col){
        	var cols=this.options.col;
        	for(var j=0;j<cols.length;j++){
        		tds+='<td width="'+cols[j].width+'">' +f(s[cols[j].dataIndex], this.data[i], v) + '</td>';
        	}
        }else{
        	tds+='<td>' + f(s, this.data[i], v) + '</td>';
        }
        tr.append(tds);
        tr.mouseover(mOver(i));
        tr.click(mClick(i));
        this.table.append(tr);//添加一条记录
      }
      me.enabled = true;
      this.content.css({width:me.options.width});
      if(this.pageBar){
      		this.pageBar.show();	
      }
      me.mainContainer.show();
      me.fixPosition();
      me.fixInterval=setInterval(
      	function(){
      		me.fixPosition();//定位定时器
      	},100
      );
      /*$(window).resize(function() {
      	document.title=document.body.clientHeight;
      	  if(me.mainContainer.is(':visible')){
      	  	me.fixPosition();
      	  }
	  });*/
      //hide loading
      if(this.options.select){
      	this.selectBtn.removeClass('autoloading');
      }
      me.autoSelect();
    },
    //下拉框定位
    fixPosition: function() {
      //var p=this.el.position();
      var offset = this.el.offset();
      var elHeight=this.el.outerHeight();
      var contentHeight=this.content.height();
      var top = (document.documentElement && document.documentElement.scrollTop) || document.body.scrollTop;
      if(offset.top+elHeight+contentHeight>document.body.clientHeight+top){
       //在元素上部显示
      	$('#' + this.mainContainerId).css({ top: (offset.top-contentHeight)+'px', left: offset.left + 'px'});
      }else{
       //在元素下部显示
      	$('#' + this.mainContainerId).css({ top: (offset.top + elHeight)+'px', left: offset.left + 'px'});
      }
    },
    //如果文本框的内容有完全等于建议列表中的，自动匹配对应的ID
    autoSelect:function(){
    	var o = this.options;
    	var v=this.el.val();
    	if(v==''){
    		return;
    	}
    	var valueField=this.options.valueField;
    	if(v!=''){
    		if(o.lookup){
    			var os=o.lookup.suggestions;
    		}else{
    			var os=this.suggestions;
    		}
       		for(var i=0;i<os.length;i++){
       			if(v==os[i][o.textField]){
			      selectedValue = os[i];
			      if(valueField){
			      	if(valueField.dataName){
			      		$('input[name='+valueField.dataName+']').val(selectedValue[valueField.dataIndex]);
			      	}
			      	if(valueField.dataId){
			      		$('#'+valueField.dataId).val(selectedValue[valueField.dataIndex]);
			      	}
			      }
       			  return ;
       			}
       		}
    	}
    },
    showLoading:function(){
    	this.table.empty();
      	this.table.append('<tr class="noMatches"><td><img src="images/loading_cycle.gif" /></td></tr>');
      	this.table.show();
    },
	showNoMatches:function(){
		this.content.css({width:this.el.width()});
		this.table.empty();
      	this.table.append('<tr class="noMatches"><td>没有匹配的结果...</td></tr>');
      	if(this.pageBar){
      		this.pageBar.hide();	
      	}
      	this.mainContainer.show();
      	if(this.options.select) this.selectBtn.removeClass('autoloading');
	},
    processResponse: function(text) {
      var response;
      try {
        response = eval('(' + text + ')');
      } catch (err) { alert('json data format error.');return; }
      if(this.options.paging){
        this.options.totalCount=response.total;//信息总条数
        this.options.pageCount = Math.ceil(this.options.totalCount/this.options.paging.pageSize);//页数
        this.setPageBar();
      } 
      if (!$.isArray(response.data)) { response.data = []; }
      
      if(!this.options.noCache){
      	//cache
        this.cachedResponse[response.query] = response;
        if (response.data.length === 0) { this.badQueries.push(response.query); }
      }
      if (response.data.length === 0){this.showNoMatches();return;}//如果返回结果为0
      //if (response.query === this.getQuery(this.currentValue)) { 判断ajax返回的query值与当前文本的值是否一致
        this.suggestions = response.data;
        this.data = response.data;
        this.suggest(); 
      //}
    },
	//鼠标滑过变色事件
    activate: function(index) {
      var divs, activeItem;
      divs = this.table.find('tr');
      // Clear previous selection:
      if (this.selectedIndex !== -1 && divs.length > this.selectedIndex) {
        $(divs.get(this.selectedIndex)).removeClass('hover');
      }
      this.selectedIndex = index;
      if (this.selectedIndex !== -1 && divs.length > this.selectedIndex) {
        activeItem = divs.get(this.selectedIndex);
        $(activeItem).addClass('hover');
      }
      return activeItem;
    },

    deactivate: function(div, index) {
      div.className = '';
      if (this.selectedIndex === index) { this.selectedIndex = -1; }
    },
    //多选添加选中标志
    addSelect:function(index){
    	var trs = this.table.find('tr');
    	var tr=$(trs.get(index));
    	if(tr.hasClass('selected')){
    		tr.removeClass('selected');
    	}else{
    		tr.addClass('selected');
    	}
    },
    //多选获取选中的行
    getMultiSelect:function(){
    	var trs=this.table.find('tr.selected');
    	var s=[];
    	for(var i=0;i<trs.length;i++){
    		s.push(trs[i].rowIndex);
    	}
    	return s;
    },
	//选择某行
    select: function(i) {
      var me=this;
      var textField=me.options.textField;
      var valueField=me.options.valueField;
      var selectedValue = me.suggestions[i];//当前选择的值
      var getValue=me.getValue(selectedValue);
      if(me.options.multiple){
      	me.el.focus();
      //多选,点击一项后不关闭提示.只是给选中行添加选中标记.
      	me.addSelect(i);
      	var selects=me.getMultiSelect();
      	var arr=[];
      	for(var s=0;s<selects.length;s++){
      		arr.push(me.suggestions[selects[s]]);//取到所有选中的建议
      	}
      	var getValue=me.getMultiValue(arr);
      	if(textField){
      		var textValue=getValue[textField];
      		me.el.val(getValue.texts);
      	}
      	if(valueField){
//	      	//根据input的name传值
//	      	if(valueField.dataName){
//	      		$('input[name='+valueField.dataId+']').val(getValue.values);
//	      	}
	       
	       me.el.parent().parent().find("input").val(valueField.dataValue);
	       
      	}else{
      		me.el.val(getValue);
      	}
      }else{
      //单选,点击一项后关闭提示
      if(typeof(selectedValue)=='string'){
      	//text
      	var getValue=me.getValue(selectedValue);
      	this.el.val(getValue);
      }else{
      	//text&value
      	if(textField){
      		me.el.val(selectedValue[textField]);
      	}
        if(valueField){
    	  me.el.parent().parent().find("input:first").val(selectedValue['typeId']);
        }
      }
      if (selectedValue) {
        //this.ignoreValueChange = true;//选择值的时候忽略文本值改变
        this.hide();
        this.onSelect(i);//回调函数
      }
      if(this.fixInterval){
      	window.clearInterval(this.fixInterval);//定位定时器
      }
      }
      this.currentValue = this.el.val();
    },
    //设置隐藏文本的值
	setValueField:function(v){
		var valueField=this.options.valueField;
      	//根据input的name传值
      	if(valueField.dataName){
      		$('input[name='+valueField.dataName+']').val(v);
      	}
      	//根据input的id传值
      	if(valueField.dataId){
      		$('#'+valueField.dataId).val(v);
      	}
	},
    moveUp: function() {
      if (this.selectedIndex === -1) { 
      	this.adjustScroll(this.suggestions.length-1); 
      }else{
      if (this.selectedIndex === 0) {
        this.table.find('tr').get(0).className = '';
        this.selectedIndex = -1;
        this.el.val(this.currentValue);
        return;
      }
      this.adjustScroll(this.selectedIndex - 1);
      }
    },

    moveDown: function() {
      if (this.selectedIndex === (this.suggestions.length - 1)) {
	  	this.adjustScroll(0); 
	  }else{
	  	this.adjustScroll(this.selectedIndex + 1);
	  }
    },
	/*上下滚动事件*/
    adjustScroll: function(i) {
      if(this.table.find('.noMatches').length>0)return;
      var activeItem, offsetTop, upperBound, lowerBound;
      var valueField=this.options.valueField;
      activeItem = this.activate(i);
      offsetTop = activeItem.offsetTop;
      upperBound = this.content.scrollTop();
      lowerBound = upperBound + this.options.maxHeight - 25;
      if (offsetTop < upperBound) {
        this.content.scrollTop(offsetTop);
      } else if (offsetTop > lowerBound) {
        this.content.scrollTop(offsetTop - this.options.maxHeight + 25);
      }
      var s=this.suggestions[i];
      if(typeof(s)=='string'){
      	this.el.val(this.getValue(s));
      }else{
      	this.el.val(this.getValue(s[this.options.textField]));
      	if(this.options.valueField){
      		if(valueField.dataName){
				$('input[name='+valueField.dataName+']').val(s[valueField.dataIndex]);     	 		
      		}
      		if(valueField.dataId){
				$('#'+valueField.dataId).val(s[valueField.dataIndex]);     	 		
      		}
      	}
      }
    },

    onSelect: function(i) {
      var me, fn, s, d;
      me = this;
      fn = me.options.onSelect;
      s = me.suggestions[i];
      d = me.data[i];
      if ($.isFunction(fn)) { fn(s, d, me.el); }//执行回调函数
    },
    //根据分隔符返回处理后字符textField
    getValue: function(value){
        var del, currVal, arr, me;
        me = this;
        del = me.options.delimiter;
        if (!del) { return value; }
        currVal = me.currentValue;
        arr = currVal.split(del);
        if (arr.length === 1) { return value; }
        return currVal.substr(0, currVal.length - arr[arr.length - 1].length) + value;
    },
    //多选根据分隔符返回拼接的值
    getMultiValue:function(v){
    	var del=this.options.delimiter?this.options.delimiter:',';
    	if(typeof(v[0])=='string'){
    		var str='';
    		for(var i=0;i<v.length;i++){
    			str+=v[i]+del;
    		}
    		return str.substr(0,str.length-1);
    	}else{
    		var textField=this.options.textField;
        	var valueField=this.options.valueField.dataIndex;
    		var texts='';
    		var values='';
    		for(var i=0;i<v.length;i++){
    			texts+=v[i][textField]+del;
    			values+=v[i][valueField]+del;
    		}
    		return {texts:texts.substr(0,texts.length-1),values:values.substr(0,values.length-1)};
    	}
    	return null;
    },
    getValueArr:function(obj){
    	var del,currText,currVal,arr,me;
    	me=this;
    	del = me.options.delimiter;
    	if (!del) { return obj; }
    },
    //测试文本框值是否在本地的建议列表里,为空也返回true,只能验证options.lookup中的值,实时查询无法验证
    checktextField:function(){
    	var o = this.options;
    	var v=this.el.val();
    	if(v==''){
    		return true;
    	}
    	if(v!=''&&o.lookup&&o.lookup.suggestions){
    		var os=o.lookup.suggestions;
       		for(var i=0;i<os.length;i++){
       			var val = os[i];
	       		var valF=(typeof(val)=='string')?val:val[this.options.textField];	
       			if(v==valF){
       				return true;
       			}
       		}
    	}
    	return false;
    }
  };

}(jQuery));
