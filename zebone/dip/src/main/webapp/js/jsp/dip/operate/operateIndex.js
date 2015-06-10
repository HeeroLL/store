var zTreeOnClick = function (event, treeId, treeNode, clickFlag) {
		$("#titleNavi").text(treeNode.name);
		//重新加载表格
		var objTree = $.fn.zTree.getZTreeObj("operate_tree");
		var nodes = objTree.getSelectedNodes();
		$("#mainFrameIframe").attr("src", nodes[0].urlJump);
	};
var nodes = [{name:'节点管理',urlJump:'operationalGuidance/nodeManage.zb' },
             {name:'服务管理',urlJump:'operationalGuidance/serviceManage.zb'},
             {name:'订阅管理',urlJump:'operationalGuidance/subscribeManage.zb'}
			  ];
var setting = {
    data: {
    	key: {
			name: "name"
		},
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};
$(function() {
	//运行管理内容树初始加载
    var operateTree = $.fn.zTree.init($("#operate_tree"), setting, nodes);
    var node = operateTree.getNodeByParam("name", "节点管理");
    operateTree.selectNode(node,false);
    $("#mainFrameIframe").attr("src", node.urlJump);
    $("#titleNavi").text(node.name);
    
    //加载布局控件
    $('body').layout({
        west: {
            size: 200
        }
    });
    //加载layout，布局控件
	$('#mainDiv').layout({ 
		north: {
             size: 35
         }
	});
});