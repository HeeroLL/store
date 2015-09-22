<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:true">
			<!-- Head -->
		    <div id="div_healthServiceTree" class="ztree" data-options="region:'west',split:true,collapsible:false,minWidth:80" style="width:200px;">
		     	
		    </div>
		    <div data-options="region:'center',split:true,collapsible:false" style="background:#eee;overflow:hidden;">
			   <iframe name="healthInfoFrame" id="healthInfoFrame" style="height:100%;width:100%;overflow:auto;border-style:none;">
			   </iframe>
		    </div>
		    <script type="text/javascript">
				
				//定义菜单树
				var menu_tree;
				//菜单树配置
				var menu_tree_setting = {
					callback : {
						onClick:function(event, treeId, treeNode){
							 
						}
					},
					data : {
						simpleData : {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					}
				};
				//测试用数据
				var treeNodes = [
		            {"id":1, "pId":0, "name":"建立个人健康档案"},
		            {"id":2, "pId":0, "name":"建立高血压转档"},
		            {"id":3, "pId":2, "name":"高血压随访", "url":"hypertentionFollowup.zb", target:"healthInfoFrame"},
		            {"id":4, "pId":0, "name":"建立糖尿病专档"},
		            {"id":7, "pId":4, "name":"糖尿病随访"},
		            {"id":5, "pId":0, "name":"老年人保健随访"},
		            {"id":6, "pId":0, "name":"成年人健康体检"}
		        ];
				
				menu_tree = $.fn.zTree.init($("#div_healthServiceTree"), menu_tree_setting, treeNodes);
		</script>
		</div>
		
		
 	</body> 
</html>