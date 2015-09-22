$(document).ready(function ()
{
	$.ajax(
	{
		url: 'submit.zb',
		type: 'POST',
		cache: false,
		data: 'sysId=' + $('#sysId').val(),
		dataType: 'json',
		success: function(res)
		{
			if (res == "error")
			{
				JDialog.showMessageDialog('异常', '没有找到该系统的子账户！', JDialog.ERROR);
				return;
			}
			// 赋值
			$('#ticket').val(res);
			// 提交表单
			$('#subsysForm').submit();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
		}
	});
});