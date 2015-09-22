var grid, editData, dialog, setBtnDisabled;

var col = [{
	text : '元素',
	width : 0,
	textAlign : 'center',
	align : 'left',
	dataIndex : 'id'
}];


$(function() {
	grid = new JGrid({
		title : '列表',
		col : col,
		dataCol : '',
		checkbox : false,
		striped : true,
		height : document.body.clientHeight - 100,
		dataUrl : 'node/nodeManaList.zb',
		render : 'grid',
		pageBar : true,
		crudOpera : true,
		countEveryPage : 20,
	});

	grid.loadData();
});