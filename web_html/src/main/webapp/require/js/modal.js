/**
 * 初始化模态窗的JS函数集合
 * @date 2016年7月13日21:03:47
 */


/**
 * 表单字符串  全局字段
 */
var modalFormStr = "";


/**
 * 初始化模态窗环境
 * @returns
 */
function initModal(){
	modalFormStr += $("#myformDiv").html();
	$("#myformDiv").html("");
}



/**
 * 在被调用页面中调用
 */
initModal()