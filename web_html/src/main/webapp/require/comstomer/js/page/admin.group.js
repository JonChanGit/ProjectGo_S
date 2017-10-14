  var str = '          <form action="" method="post">	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-group"></i> 组名</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder="不可空" type="text" id="name"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-info"></i> 描述</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder="详细描述" type="text" id="description"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-picture-o"></i> 图标</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " value="fa-group" placeholder="fa-group" type="text" id = "icon"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-adjust"></i> 强调色--背景色</label><a href="showIcon.html" target="_black">图标和配色</a>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder="successColor" value="successColor" type="text" id="color"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-adjust"></i> 强调色--前景色</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder="successColor" value=fontinfoColor type="text" id="colorPre"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-trophy"></i> 等级</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder=">100 And <10000" type="text" id="leve" value="1000"></div>	          </div>	          <div class="row form-group has-success ">	          	<div class="col-md-12 col-sm-12 col-xs-12 ">					<label class="control-label" for="inputSuccess"><i class="fa fa-info"></i> 备注</label>				</div>	          	<div class="col-md-12 col-sm-12 col-xs-12"><input class="form-control " placeholder="" type="text" id="other"></div>	          </div>	          <button class="btn btn-default btn-block btn-flat" onclick="addGroup()" id="addBtn" type="button" >添加！</button>          </form>          ';
	
  var curPage = 1;
  //$("#sidebar").addClass("active");
//     $(function () {使用智能表格
//       try {
//     	  $("#example1").dataTable();
// 		} catch (e) {
// 		}
//      });
	function addNewItem(id , o){
		activitiVAR = null;
		activitiVAR = new Activiti("activitiDIV","1000",str,"添加","infoColor");
		activitiVAR.initialization();
		activitiVAR.loaded();
	}
	
	function addGroup(){
		$("#addBtn").attr("disabled","disabled");
		var b = true;
		if($("#name").val() == null || $("#name").val() == ""  ){
			appendAlertDanger("","组名称不能为空!");
			b = false;
		}
// 		alert(typeof(parseInt($("#leve").val())));
		var x = Number($("#leve").val());
		if( !(x > 100 && x < 10000)){
			appendAlertDanger("","权限级别需要等于100小于10000");
			b = false;
		}
		if(b){
			$.ajax({
				type: "POST",
				url:"addUserGroupAction.html",
				data:{'c_name':$("#name").val(),'i_leve':$("#leve").val(),'c_description':$("#description").val(),'c_notes':$("#other").val(),'c_fountIcon':$("#icon").val(),'c_leveColor':$("#color").val(),'c_leveColorForeground':$("#colorPre").val()},
				dataType:'json',
				success:function(data){
					if(data == true){
						appendAlertSuccess("","添加成功！")
					}else{
						appendAlertWarning("","失败！"+data.errorMessige+","+data.errorTips);
					}
					$("#addBtn").removeAttr("disabled");
				},
				timeout:(10000),
				error:function(){
					appendAlertDanger("alertDivSID","查询失败!请查看网络是否正常！");
					$('#modalDefault #submitLink').removeClass("disabled");
					$("#addBtn").removeAttr("disabled");
				}
			});
		}else{
			$("#addBtn").removeAttr("disabled");
		}
		return false;
	}
	
	
	function getXPage(page,o){
		appendAlertInfo("","数据读取中请稍后。。。")
		desablePage();
		$(o).addClass("disabled");
		curPage = page;
		window.setTimeout(function(){ 
			getpage(page);
			},1000); 
	}
	
	
	function getpage(page){
		if(page == null || page <= 0){
			appendAlertDanger("","发生为止错误，请联系管理员解决");
			enablePage();
		}else{
			$("#resultTableDIV").css("display","")
			$.ajax({
				type: "POST",
				url:"getGroupPageUserGroupAjaxAction.html",
				data:{'page':page},
				dataType:'json',
				success:function(data){
					if(data.errorMessige == null){//
						var htmpTemp = "";
						for(var i = 0 ; i < data.result.length;i++){
							htmpTemp +='<tr class="'+data.result[i].c_leveColorForeground+'" >'+
							'  <td><input type="checkbox" /></td>'+
							'  <td class="mailbox-star"><a href="#"><i class="fa '+data.result[i].c_fountIcon+' '+data.result[i].c_leveColorForeground+'"></i></a></td>'+
							' <td class="mailbox-name"><a  class="'+data.result[i].c_leveColorForeground+'"  href="#">'+data.result[i].c_name+'</a></td>'+
							' <td class="mailbox-subject"><b>'+data.result[i].c_description+'</b> - '+data.result[i].c_notes+'</td>'+
							' <td class="mailbox-attachment"></td>'+
							'  <td class="mailbox-date "><a class="text-yellow" href="#"> <span class="label label-default '+data.result[i].c_leveColor+'">'+data.result[i].i_leve+'</span></a></td>'+
							'<td class="mailbox-date">'+
							'<div class="btn-group"> '+
							'  	<button class="btn btn-default" onclick="editItem('+data.result[i].p_i_id+',this)"><i class="fa  fa-edit"></i> &nbsp; &nbsp;编辑</button>'+
							'  	<button class="btn btn-danger" onclick="deleteItems('+data.result[i].p_i_id+')"><i class="fa  fa-trash-o"></i> &nbsp; &nbsp;删除</button>'+
							'</div>'+
							'</td>'+
							'</tr>';
							
						}
						//$("#h3temp").remove();
						$("#h3temp").slideUp("fast");
						$("#resultTable").html(htmpTemp);
						$("#resultTableDIV").css("display","none")
						$("#resultTableDIV").slideDown(1000);
						var pageboxstr = getPageTurnHTMLString(data);
						$("#PageBox").html(pageboxstr);
					}else{
						appendAlertWarning("","失败！"+data.errorMessige+","+data.errorTips);
					}
					$("#addBtn").removeAttr("disabled");
					enablePage();
				},
				timeout:(10000),
				error:function(){
					appendAlertDanger("alertDivSID","查询失败!请查看网络是否正常！");
					$('#modalDefault #submitLink').removeClass("disabled");
					$("#addBtn").removeAttr("disabled");
					enablePage();
				}
			});
		}
	}
	
	
	function editItem(id,o){
		appendAlertInfo("","提示：请删除后从建,ID:"+id)
	}
	
	
	/***
	*/
	function deleteItems(ids){
		//var idargs= new Array(); //定义一数组 
		//idargs=ids.split(","); //字符分割 
		if(false){
			appendAlertDanger("","发生未知错误！,参数不正确!");
		}else {
			$.ajax({
				type: "POST",
				url:"deleteGroupUserGroupAjaxAction.html",
				data:{'ugidargs':ids},
				dataType:'json',
				success:function(data){
					if(data == true){
						appendAlertSuccess("","请求成功！");
					}else{
						appendAlertDanger("","失败！"+data.errorMessige+","+data.errorTips);
					}
				},
				timeout:(10000),
				error:function(){
					appendAlertDanger("alertDivSID","请查看网络是否正常！","查询失败!");
					$('#modalDefault #submitLink').removeClass("disabled");
				}
			});
			_initPage();
		}
	}
	
	/**
	*翻页按钮生成
	*/
	function getPageTurnHTMLString(data){
		var pageboxstr = ' <ul class="pagination pagination-sm inline">                      ';
		for(var i = 0 ; i < data.totalPage; i++){
			if((i+1) == data.currentPage){
				pageboxstr+='<li class="disabled active "><a href="#"  >'+(i+1)+'</a></li>  ';
			}else{
				pageboxstr+='<li  onclick="getXPage('+(i+1)+',this)"><a href="#"  >'+(i+1)+'</a></li>  ';
			}
		}
		pageboxstr+='</ul>';
		return pageboxstr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	function _initPage(){
		appendAlertInfo("","数据读取中请稍后。。。","",500);
		desablePage();
		window.setTimeout(function(){ 
			getpage(1);
			},_alertTime); 
	}
	
	_initPage();
	
	
	
