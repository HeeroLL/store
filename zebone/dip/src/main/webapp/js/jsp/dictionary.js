/*
 * 字典辅助js
 * 使用说明：
 *     在需要使用字典值填充的select元素上设计如下3个值
 *         id：设置唯一的id值
 *         在class属性中增加 dict
 *         增加dictType属性，并且值设置为字典类别代码
 *      在需要使用此功能的jsp页面引用此js。
 *      当引用此js的页面装载完成后，程序通过ajax方式取出指定字典
 *      类别下面的所有字典值，并填充到对应的select下面。
 * @author 宋俊杰 
 */

/**
 * 定义字典工具类
 */
Dict = function(){};

/**
 * 初始化一个下拉框,将字典列表填充到下拉框中。
 * @param selectId 下拉框ID
 * @param dictType  字典类型代码
 */
Dict.initDictSelect = function (selectId ,dictType){
	var url = "dict/getDictByType.zb";
	var param = {dictType:dictType,selectId:selectId};
	$.getJSON(url,param,
		function(dictData){
			// dictData 类型为：{selectId:123,dictList:[[{id:1,name:'a',code:'n',jianpin:'n'},...]}
			if(dictData==null ){
				return;
			}
			var dictList = dictData.dictList;
			if(dictList==null || dictList.length<1){
				return;
			}
			var option = "<option value=''>请选择</option>";
			var val = $("#"+selectId).attr("selectedValue");
			$.each(dictList,function(index,dict){
				var selected = "";
				if(val!=null && val == dict.code){
					selected="selected=\"selected\"";
				}
				option+="<option value='"+dict.code+"' "+selected + ">"+dict.name +"</option>\n"
			})
			$("#"+selectId).html(option);
		}
	);	
}

/**
 * 页面装载完成后，初始化所有用户指定的需要初始化的下拉框
 */
$(document).ready(function(){
	$("select[dictType]").each(function(){
		var selectId = $(this).attr("id");
		var dictType = $(this).attr("dictType");
		if(selectId==null || dictType==null){
			return;
		}
		Dict.initDictSelect(selectId,dictType);
	})
});
