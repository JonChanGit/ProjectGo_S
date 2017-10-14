/**
 * 引用此文件，加入弹窗方法【提示框】
 * appendAlertWarning("","失败！"+data.errorMessige+","data.errorTips);
 */
var _alertTimedef = 1500;
var _alertTime = _alertTimedef;

/**
 * 危险警告窗（红）
 */
function appendAlertDanger(domID,message,_title,_alertTimes){
	if(domID == null || domID == "" || domID == "def"){
		domID = "alertDivSID";
	}
	if(_title == null || _title == "" ){
		_title = " 警告"; 
	}
	var strr = randomString(32);
	$("#"+domID).prepend(' <div style="display: none;" class="alert alert-danger alert-dismissable" id="'+strr+'">'+
			' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			' <h4><i class="icon fa fa-ban"></i>'+_title+'!</h4>'+
			message+
			' </div>');
	$("#"+strr).slideDown();
	if(_alertTimes == null || _alertTimes == ""){
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTime); 
	}else{
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTimes); 
	}
}
/**
 * 信息窗
 * @param domID
 * @param message
 * @returns
 */
function appendAlertInfo(domID,message,_title,_alertTimes){
	if(domID == null || domID == ""){
		domID = "alertDivSID";
	}
	if(_title == null || _title == "" ){
		_title = " 提示"; 
	}
	var strr = randomString(32);
	$("#"+domID).prepend('<div style="display: none;" class="alert alert-info alert-dismissable" id="'+strr+'">'+
			' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			'<h4><i class="icon fa fa-info"></i>'+_title+' </h4>'+
			message+
			' </div>');
	$("#"+strr).slideDown();
	if(_alertTimes == null || _alertTimes == ""){
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTime); 
	}else{
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTimes); 
	}
}
/**
 * 警告窗（黄）
 * @param domID
 * @param message
 * @returns
 */
function appendAlertWarning(domID,message,_title,_alertTimes){
	if(domID == null || domID == ""){
		domID = "alertDivSID";
	}
	if(_title == null || _title == "" ){
		_title = " 警告"; 
	}
	var strr = randomString(32);
	$("#"+domID).prepend('<div style="display: none;" class="alert alert-warning alert-dismissable" id="'+strr+'">'+
			'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			' <h4><i class="icon fa fa-warning"></i>'+_title+'</h4>'+
			message+
			' </div>');
	$("#"+strr).slideDown();
	if(_alertTimes == null || _alertTimes == ""){
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTime); 
	}else{
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTimes); 
	}
	
}
/**
 * 成功提示
 * @param domID
 * @param message
 * @returns
 */
function appendAlertSuccess(domID,message,_title,_alertTimes){
	if(domID == null || domID == ""){
		domID = "alertDivSID";
	}
	if(_title == null || _title == "" ){
		_title = " 成功"; 
	}
	var strr = randomString(32);
	$("#"+domID).prepend('<div style="display: none;" class="alert alert-success alert-dismissable" id="'+strr+'">'+
			'<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			' <h4>	<i class="icon fa fa-check"></i>'+_title+'</h4>'+
			message+
			' </div>');
	$("#"+strr).slideDown();
	if(_alertTimes == null || _alertTimes == ""){
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTime); 
	}else{
		window.setTimeout(function(){ 
			removeAlert(strr); 
			},_alertTimes); 
	}
}
/**
 * 移除
 * @param alertID
 * @returns
 */
function removeAlert(alertID){
	$("#"+alertID).slideUp("slow");
	window.setTimeout(function(){ 
		$("#"+alertID).remove();
		},_alertTime+1000); 
	//_alertTime = _alertTimedef;
}
/**
 * 生成随机ID
 * @param len
 * @returns
 */
function randomString(len) {
　　len = len || 32;
　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz1234567890';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
　　var maxPos = $chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
　　}
　　return pwd;
}