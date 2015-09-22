/**
 * JQuery Tree 树组件
 * @author 王聚金
 * @version 1.0
 * Example:
 * var tree = new JTree(opt);
 * tree.show();
 * 构造函数参数opt说明
 * 		[data] 客户端JSON数据
 * 		[dataUrl] 服务端数据请求URL
 * 		[async] 是否使用异步请求。默认为false
 * 		[checkbox] 是否是树的节点可以选择（CheckboxTree）
 * 回调函数说明
 * 		nodeclick(node) 单击树的节点后触发
 * 			[node] 说明见【树节点Node参数说明】
 * 		nodedblclick(node) 双击树的节点后触发
 * 			[node] 说明见【树节点Node参数说明】
 * 树节点Node参数说明
 * 		[id] 节点唯一标识，不可重复
 * 		[text] 节点名称
 * 		[leaf] 是否是叶节点
 * 		[icon] 节点图标
 * 		[url] 功能路径
 * 		[childrens] 包含的子节点数据【回调函数中的node不包含此属性】
 */
JTreeNode = function(opt){
	this.opt = opt;
};
JTree = function(opt){
	if(opt){
 		this.opt = opt;
	}else{
		this.opt = {};
	}
 };
 
JTree.prototype={
	//生成HTML
	createTreeHTML: function(data, parentNode){
		var treeHTML = "";
		for(var i=0; i<data.length; i++){ 
			if(data[i].leaf){
				treeHTML +="<li><span id='"+data[i].id+"' name='leaf' class='"+parentNode+"'  leaf='true' load='1' iconUrl='"+(data[i].icon?data[i].icon:"")+"' pageUrl='"+data[i].url+"'>";
 				if(this.opt.checkbox){
 					treeHTML += "<input type='checkbox' "+(data[i].checked?"checked":"")+"/>";
 				}
 				treeHTML += "<b>"+data[i].text+"</b></span></li>"; 				
			}else{
				if(!data[i].childrens) data[i].childrens = [];
 				treeHTML +="<li><ul><span id='"+data[i].id+"' name='folder' class='"+parentNode+"' expand='0' leaf='false' load='0' iconUrl='"+(data[i].icon?data[i].icon:"")+"' pageUrl='"+data[i].url+"'>";
 				if(this.opt.checkbox){
 					treeHTML += "<input type='checkbox' "+(data[i].checked?"checked":"")+"/>";
 				}
 				treeHTML += "<b>"+data[i].text+"</b></span>";
 				treeHTML += this.createTreeHTML(data[i].childrens, data[i].id);
 				treeHTML += "</ul></li>";
			}			
 		}
 		return treeHTML;
	},
	//生成ICON
	setNodeIcon: function(data){
		for(var i=0; i<data.length; i++){
			if(data[i].icon){
				$("#"+data[i].id).css("background-image", "url('"+data[i].icon+"')");
			}
			if(!data[i].childrens) data[i].childrens = [];
			this.setNodeIcon(data[i].childrens);
		}
	},
	//数据加载
	loadData: function(tree, node){
		var datas = ""; 
		if(node&&node!='root') datas = "node="+node;
		var object = this;
		$.ajax({
			url: this.opt.dataUrl,
			type: 'POST',
			data: encodeURI(datas),
			cache: false,
			dataType: 'json',
			success: function(data){	
		 		tree.append(object.createTreeHTML(data, node));		 		
		 		if(node=='root') {
		 			object.collapseAll();			 		
		 		}else{
		 			if($("#"+node).attr('iconUrl')==''){
		 				$("#"+node).css("background-image", "url('tools/jquery/images/jquery-tree/folder.gif')");
		 			}
		 		}
		 		if(object.opt.async){
		 			object.bindClickEvent(node);
		 		}else{
		 			object.registerEvent();
		 		}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('Server exception!');
			}
		});
	},
	//显示
 	show: function(){
 		var object = this;
 		var data = this.opt.data;
 		var treeHTML = "<div id='jtree'></div>";
 		var tree = $(treeHTML);
 		$(document.body).append(tree);	
 		if(data){	 	
	 		tree.append(this.createTreeHTML(data, "root"));
	 		this.setNodeIcon(data);
	 		object.collapseAll();
		 	this.registerEvent();
 		}else{
 			this.loadData(tree, "root");
 		}	 	 		
 	},
 	//绑定事件
 	bindClickEvent: function(node){
 		var object = this; 		
 		$("."+node).click(function(){
	 		if($(this).attr("leaf")=='true') return;
	 		var expandState = $(this).attr("expand");
	 		if(expandState==1){//折叠
	 			object.collapse($(this).attr("id"));
	 			if($(this).attr('iconUrl')==''){
	 				$(this).css("background-image", "url('tools/jquery/images/jquery-tree/folder-closed.gif')");
	 			}
	 			$(this).attr("expand", "0");
	 		}else{
	 			if(object.opt.async){
	 				if($(this).attr("load")==1){
	 					object.expand($(this).attr("id"));
	 					if($(this).attr('iconUrl')==''){
	 						$(this).css("background-image", "url('tools/jquery/images/jquery-tree/folder.gif')");
	 					}
	 					$(this).attr("expand", "1");
	 				}else{
	 					$(this).css("background-image", "url('tools/jquery/images/jquery-tree/loading.gif')");
	 					object.loadData($(this).parent(),$(this).attr("id"));	 					
	 					$(this).attr("expand", "1");
	 					$(this).attr("load", "1");
	 				}
	 			}else{
	 				object.expand($(this).attr("id"));
	 				if($(this).attr('iconUrl')==''){
	 					$(this).css("background-image", "url('tools/jquery/images/jquery-tree/folder.gif')");
	 				}
	 				$(this).attr("expand", "1");
	 			}	 			
	 		}	 		
	 	});
	 	$("."+node).find("b").click(function(e){
	 		//执行回调函数
	 		if(object.opt.listeners&&object.opt.listeners.nodeclick) object.opt.listeners.nodeclick({
	 			id: $(this).parent().attr("id"),
	 			text: $(this).text(),
	 			leaf: eval($(this).parent().attr("leaf")),
	 			icon: $(this).parent().attr("iconUrl"),
	 			url: $(this).parent().attr("pageUrl")
	 		});
	 		$.stopBubble(e);
	 	});
	 	$("."+node).find("b").dblclick(function(e){
	 		//执行回调函数
	 		if(object.opt.listeners&&object.opt.listeners.nodedblclick) object.opt.listeners.nodedblclick({
	 			id: $(this).parent().attr("id"),
	 			text: $(this).text(),
	 			leaf: eval($(this).parent().attr("leaf")),
	 			icon: $(this).parent().attr("iconUrl"),
	 			url: $(this).parent().attr("pageUrl")
	 		});
	 		$.stopBubble(e);
	 	});
	 	$("."+node).find("input").bind("click", function(e){
	 		$.stopBubble(e);
	 	});
 	},
 	//注册事件
 	registerEvent: function(){
 		var object = this;
 		$("#jtree span").click(function(){
	 		if($(this).attr("leaf")=='true') return;
	 		var expandState = $(this).attr("expand");
	 		if(expandState==1){//折叠
	 			object.collapse($(this).attr("id"));
	 			if($(this).attr('iconUrl')==''){
	 				$(this).css("background-image", "url('tools/jquery/images/jquery-tree/folder-closed.gif')");
	 			}
	 			$(this).attr("expand", "0");
	 		}else{
	 			if(object.opt.async){
	 				if($(this).attr("load")==1){
	 					object.expand($(this).attr("id"));
	 				}else{	 				
	 					object.loadData($(this).parent(),$(this).attr("id"));
	 					$(this).attr("load", "1");	 				
	 				}
	 			}else{
	 				object.expand($(this).attr("id"));
	 			}
	 			if($(this).attr('iconUrl')==''){
	 				$(this).css("background-image", "url('tools/jquery/images/jquery-tree/folder.gif')");
	 			}
	 			$(this).attr("expand", "1");
	 		}	 		
	 	});
	 	$("#jtree span b").click(function(e){
	 		//执行回调函数
	 		if(object.opt.listeners&&object.opt.listeners.nodeclick) object.opt.listeners.nodeclick({
	 			id: $(this).parent().attr("id"),
	 			text: $(this).text(),
	 			leaf: eval($(this).parent().attr("leaf")),
	 			icon: $(this).parent().attr("iconUrl"),
	 			url: $(this).parent().attr("pageUrl")
	 		});
	 		$.stopBubble(e);
	 	});
	 	$("#jtree span b").dblclick(function(e){
	 		//执行回调函数
	 		if(object.opt.listeners&&object.opt.listeners.nodedblclick) object.opt.listeners.nodedblclick({
	 			id: $(this).parent().attr("id"),
	 			text: $(this).text(),
	 			leaf: eval($(this).parent().attr("leaf")),
	 			icon: $(this).parent().attr("iconUrl"),
	 			url: $(this).parent().attr("pageUrl")
	 		});
	 		$.stopBubble(e);
	 	});
	 	$("#jtree").find("input").bind("click", function(e){
	 		$.stopBubble(e);
	 	});
 	},
 	//折叠
 	collapse: function(node){
 		var childs = $("span").filter("."+node);
 		childs.attr("expand", "0");
 		for(var i=0; i<childs.length; i++){
 			childs[i].style.display="none";
 			this.collapse(childs[i].id);
 		}
 	},
 	//全部折叠
 	collapseAll: function(){
 		var childs = $("span").filter(".root");
 		for(var i=0; i<childs.length; i++){ 			
 			this.collapse(childs[i].id);
 		}
 	},
 	//展开
 	expand: function(node){
 		var childs = $("span").filter("."+node);
 		childs.attr("expand", "1");
 		for(var i=0; i<childs.length; i++){
 			childs[i].style.display="block";
 			if(childs[i].expand==1){
 				this.expand(childs[i].id);
 			}
 		}
 	},
 	//节点是否展开
 	isExpand: function(node){
 		var nodes = $("#"+node);
 		var expandState = nodes.attr("expand");
 		return expandState=='1';
 	}
 };