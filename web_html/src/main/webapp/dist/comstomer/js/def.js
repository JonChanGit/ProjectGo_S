/**
 * md5加密工具
 */

function MD_kl(inStr){
	var charArray = inStr.split(""); 
	var value = 9;
	var index = 9099;
	for(var i = 0; i < charArray.length;i++){
		charArray[i] =String.fromCharCode( charArray[i].charCodeAt(0) ^ 'g'.charCodeAt(0));
	}
	return charArray.join("");
	
}

/**
 * 禁页面
 */
function desablePage(){
	$("#desablePageDIV").fadeIn();
}

/**
 * 启用页面
 */
function enablePage(){
	$("#desablePageDIV").fadeOut();
}

/**
 * miniAjax 
 * @param {Object} url 访问URL
 * @param {Object} dataJson  参数
 * @param {Object} successCallback  回掉函数 >> successCallback(data,[callbackOptions]);//callbackOptions可选
 * @param {Object} callbackOptions  回掉函数参数
 */
function miniAjax(url,dataJson,successCallback, callbackOptions){
	$.ajax({
		type: "post",
		url: url,
		async: true,
		data: dataJson,
		dataType: 'json',
		cache: false,
		success: function(data) {
			if(data.flag){
				appendAlertSuccess("", data.message);
				successCallback(data,callbackOptions);
			}else{
				appendAlertDanger("", data.message);
			}
		},
		timeout: (10000),
		error: function() {
			appendAlertDanger("", "查询失败!请查看网络是否正常！");
			$('#modalDefault #submitLink').removeClass("disabled");
		}

	});
}

/**
 * 字符串转数组 
 * @param {Object} str 需要转换的字符串
 * @param {Object} splitChar 分割符号
 */
function StringToArray(str,splitChar){
	var obj2 = str.split(splitChar);
	return obj2;
}
/**
 * 数组转字符串
 * @param {Object} arr 需要转换的对象
 * @param {Object} linkChar 各个元素的链接符号
 */
function ArrayToString(arr,linkChar){
	return arr.join(linkChar);
}

/**
 * JS动态加载JS与CSS文件 
 * 使用示例：
 * loadjscssfile("do.js","js");
 * loadjscssfile("test.css","css");
 * @param {Object} filename
 * @param {Object} filetype
 */
function loadjscssfile(filename,filetype){

    if(filetype == "js"){
        var fileref = document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src",filename);
    }else if(filetype == "css"){
    
        var fileref = document.createElement('link');
        fileref.setAttribute("rel","stylesheet");
        fileref.setAttribute("type","text/css");
        fileref.setAttribute("href",filename);
    }
   if(typeof fileref != "undefined"){
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }
    
}

//打印数组
function print_array(arr){
	for(var key in arr){
		if(typeof(arr[key])=='array'||typeof(arr[key])=='object'){//递归调用  
			print_array(arr[key]);
		}else{
			document.write(key + ' = ' + arr[key] + '<br>');
		}
	}
}

/**
 * 去除前导空格
 * @param str
 * @returns
 */
function LTrim(str)
{
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}
/**
 *  去除后导空格
 * @param str
 * @returns
 */
function RTrim(str)
{
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}
/**
 * 去除前后空格
 * @param str
 * @returns
 */
function Trim(str)
{
    return LTrim(RTrim(str));
}

/**
 * 删除所有HTML标签
 * @param tab
 * @returns
 */
function removeHtmlTab(tab) {
	return tab.replace(/<[^<>]+?>/g,'');//删除所有HTML标签
}
