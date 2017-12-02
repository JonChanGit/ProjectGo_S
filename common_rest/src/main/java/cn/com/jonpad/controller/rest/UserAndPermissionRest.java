package cn.com.jonpad.controller.rest;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysUserService;
import cn.com.jonpad.util.JsonTool;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/access/user_and_permission")
public class UserAndPermissionRest {

  @Autowired
  private SysUserService sus;

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  public JsonTransportEntity getUserList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "searchKey", defaultValue = "") String searchKey) {
    Page<SysUser> userPage = sus.getPage(page, 10);

    return JsonTransportEntity.getInstance(userPage);
  }


  /**
   * 修改用户
   * @param user
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  // @RequiresPermissions(ConstantesPermission.PERMISSION_USER_EDIT)
  public JsonTransportEntity userModify(@RequestParam int locked,@RequestParam long id,@RequestParam String email,@RequestParam String username) throws IOException {

    SysUser user = new SysUser(id,email,locked,username);

    boolean b = sus.modifyUser(user);
    if (b) {
      return JsonTool.toJson(true, "修改用户完成");
    } else {
      return JsonTool.toJson(false, "修改用户失败");
    }
  }

  /**
   * 添加用户
   * @param user
   * @throws IOException
   */
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_USER_ADD)
  public JsonTransportEntity userAdd(SysUser user) throws IOException {

    boolean b = sus.addUser(user);
    if (b) {
      return JsonTool.toJson(true, "用户添加成功,初始密码【88888888】");
    } else {
      return JsonTool.toJson(false, "用户添加失败");
    }
  }


}
