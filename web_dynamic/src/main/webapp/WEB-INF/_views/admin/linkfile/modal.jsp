<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	在需要使用模态窗的地方加入此文件

	模态窗口的使用，请参考 http://v3.bootcss.com/javascript/#modals 
	在需要调用的按钮中，加入需要插入的HTML代码即可
	使用onclick可以绑定
	示例：
		1.
			<div class="hidden" id="myformDiv">
				<!--表单内容 -->
				<div class="input-group">
                  <span class="input-group-addon">@</span>
                  <input class="form-control" placeholder="Username" type="text">
                </div>
			</div>
		
		2.
	    	function showUXDialog(e){
	    		$("#myModalLabel").html("添加角色");
	    		$("#myModalBody").html($("#myformDiv").html());
	    		$("#myModalSubmit").click(function(){
	    			alert();
	    		});
	    	}
--%>

<!-- Modal 正常模态-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body" id="myModalBody">
        数据加载中...
      </div>
      <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" id="myModalSubmit">保存更改</button>
      </div>
    </div>
  </div>
</div>

<!-- 蓝色模态窗口 -->
<div class="modal modal-primary" id="primaryModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="primaryModalLabel">Modal Primary</h4>
			</div>
			<div class="modal-body" id="primaryModalBody">
				<p> 数据加载中…</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline pull-left"
					data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-outline"  id="primaryModalSubmit">保存</button>
			</div>
		</div> <!-- /.modal-content -->
	</div> <!-- /.modal-dialog -->
</div>





<script>
window.onload=function(){
	load_js_css_file("dist/js/modal.js","js");
};
</script>
