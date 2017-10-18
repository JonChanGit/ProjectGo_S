// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
/* global layer ,$ ,appendAlertInfo,miniAjax,Mustache*/

var myformDiv = '';

$(function () {
  console.log('hi');

  $('#addRolePanel_Btn').on('click',);

});

/**
 * 修改角色
 */
function submitModify() {

  if($('#roleDes').val() == ''){
    alert('请输入角色名称');
    $('#myModalSubmit').removeAttr('disabled');
    return false;
  }

  miniAjax('access/user_and_permission/roleModify.do',
    {
      'name': $('#roleDes').val(),
      'id': $('#roleID').val()
    }, function (data) {
      appendAlertInfo('alertDivSID', data.flag + ' ' + data.message);
    }
  );
}

function showUXDialogModify(roleId) {
  var html = Mustache.render(myformDiv, {'id':roleId});
  layer.open({
    title: '${app_PageTitle}'
    , content: html,
    yes: submitModify
  });
}

function modifyCof(userID, checked_lock) {
  layer.open({
    title: '修改用户(不填写为不更改)'
    , content: myformDiv,
    yes: function () {
      submitModify(userID);
    }
  });
  // 更改按钮状态
  if (checked_lock == 0) {
    document.getElementById('userLocked').checked = true;
  } else {
    document.getElementById('userLocked').checked = false;
  }
}
