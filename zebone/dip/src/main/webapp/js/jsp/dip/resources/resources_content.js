var lostFocusInput = function(obj){
	var value = $.trim($(obj).val());
	if(!value) $(obj).val("机构名称");
}

var getFocusInput = function(obj){
	var value = $.trim($(obj).val());
	if(value == "机构名称") $(obj).val('');
}

    var zTreeOnClick = function (event, treeId, treeNode, clickFlag) {
		$("#titleNavi").text(treeNode.orgName);
		//重新加载表格
		var resourceTree = $.fn.zTree.getZTreeObj("resource_tree");
		var resourcenodes = resourceTree.getSelectedNodes();
		$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode="+treeNode.orgCode);
	};
	var zTree2OnClick = function (event, treeId, treeNode, clickFlag) {
		var treeObj = $.fn.zTree.getZTreeObj("medical_org");
		var nodes = treeObj.getSelectedNodes();
		if(nodes[0]==null){
				$("#mainFrameIframe").attr("src", treeNode.urlJump+"?orgCode=XXXX");	
			}else{
				$("#mainFrameIframe").attr("src", treeNode.urlJump+"?orgCode="+nodes[0].orgCode);	
			}
	};
    var nodes2 = [{name:'科室信息',urlJump:'resourcesManage/deptInfo.zb' },
	             {name:'人员信息',urlJump:'resourcesManage/userInfo.zb'},
	             {name:'家庭档案',urlJump:'resourcesManage/familyInfo.zb'}
				  ];
    var setting = {
	    data: {
	    	key: {
				name: "orgName"
			},
			simpleData: {
				enable: true,
				idKey:'orgCode',
				pIdKey:'orgParentCode'
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	var setting2 = {
	    data: {
	    	key: {
				name: "name"
			},
			simpleData: {
				enable: true,
				idKey:'orgCode',
				pIdKey:'orgParentCode'
			}
		},
		callback: {
			onClick: zTree2OnClick
		}
	};
/****
	var reloadTree = function(value){
		if(value!="机构名称"){
			//机构树初始加载
		    var medicalOrg = $.fn.zTree.init($("#medical_org"), setting, nodes);
		    var nodeList = medicalOrg.getNodesByParamFuzzy("orgName", value);  
		    medicalOrg = $.fn.zTree.init($("#medical_org"), setting, nodeList);
		    var node = medicalOrg.getNodes();
			if (node.length>0) {
				for(var i=0 ;i<node.length;i++){
					if(node[i].orgParentCode ==null){
						medicalOrg.expandNode(node[i], true, false, false, false);
						medicalOrg.selectNode(node[i],false);
						$("#titleNavi").text(node[i].orgName);
						break;
					}
				}
			}
			var resourceTree = $.fn.zTree.getZTreeObj("resource_tree");
		    //初始表格加载
			var orgnodes = medicalOrg.getSelectedNodes();
			var resourcenodes = resourceTree.getSelectedNodes();
			if(orgnodes[0]==null){
				JDialog.tip('医疗机构不存在',2);		
				$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode=XXXX");
				$("#treeQuery").focus();
			}else{
				$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode="+orgnodes[0].orgCode);
				$("#treeQuery").focus();
			}
			
		}
}
	**/
	$(function() {
			var options={
					width: 200,
				    maxHeight: 200,
					select:false,
					textField:'orgName',
					serviceUrl:'resourcesManage/getOrgInfo.zb',
					col:[{dataIndex:'orgName'},{dataIndex:'orgCode'}],
					valueField:{dataIndex:'id',dataName:'orgId'},//隐藏文本的name
					onSelect: function(suggestions){
						var treeObj = $.fn.zTree.init($("#medical_org"), setting, nodes);
						
						var treenode = treeObj.getNodeByParam("orgCode", suggestions.orgCode, null); 
						treeObj.expandNode(treenode, true, false, true,null); 
						treeObj.selectNode(treenode); 
						$("#titleNavi").text(treenode.orgName);
						
						var resourceTree = $.fn.zTree.getZTreeObj("resource_tree");
					    //初始表格加载
						var orgnodes = medicalOrg.getSelectedNodes();
						var resourcenodes = resourceTree.getSelectedNodes();
						if(orgnodes[0]==null){
							JDialog.tip('医疗机构不存在',2);		
							$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode=XXXX");
							$("#treeQuery").focus();
						}else{
							$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode="+orgnodes[0].orgCode);
							$("#treeQuery").focus();
						}
					}
				};
			
			$("#treeQuery").autocomplete(options);
		
		//机构树初始加载
	    var medicalOrg = $.fn.zTree.init($("#medical_org"), setting, nodes);
	    var node = medicalOrg.getNodes();
		if (node.length>0) {
			for(var i=0 ;i<node.length;i++){
				if(node[i].orgParentCode ==null){
					medicalOrg.expandNode(node[i], true, false, false, false);
					medicalOrg.selectNode(node[i],false);
					$("#titleNavi").text(node[i].orgName);
					break;
				}
			}
		}
		//资源树初始加载
	    var resourceTree = $.fn.zTree.init($("#resource_tree"), setting2, nodes2);
	    var node2 = resourceTree.getNodeByParam("name", "科室信息");
	    resourceTree.selectNode(node2,false);
	    
	    //初始表格加载
		var orgnodes = medicalOrg.getSelectedNodes();
		var resourcenodes = resourceTree.getSelectedNodes();
		$("#mainFrameIframe").attr("src", resourcenodes[0].urlJump+"?orgCode="+orgnodes[0].orgCode);
	    
	    //加载布局控件
         $('body').layout({
             west: {
                 size: 220
             }
         });
         //加载layout，布局控件
		$('#mainDiv').layout({ 
			north: {
                 size: 35
             }
		});
		$('#contentDiv').layout({
             west: {
                 size: 150
             }
         });
	});