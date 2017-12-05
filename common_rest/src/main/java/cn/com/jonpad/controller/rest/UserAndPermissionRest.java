package cn.com.jonpad.controller.rest;

import cn.com.jonpad.dto.MeunDetails;
import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysPermissionServics;
import cn.com.jonpad.service.SysRoleService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/api/access/user_and_permission")
public class UserAndPermissionRest {

  @Autowired
  private SysUserService sus;
  @Autowired
  private SysRoleService srs;
  @Autowired
  private SysPermissionServics sps;

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  public JsonTransportEntity getUserList(@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
                                         @RequestParam(defaultValue = "1")int currPage,
                                         @RequestParam(defaultValue = "10")int pageSize
                                         ) {
    Page<SysUser> userPage = sus.getPage(currPage, pageSize);

    return JsonTransportEntity.getInstance(userPage);
  }


  /**
   * 修改用户
   * @return
   */
  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  // @RequiresPermissions(ConstantesPermission.PERMISSION_USER_EDIT)
  public JsonTransportEntity userModify(@RequestParam int locked,@RequestParam long id,@RequestParam String email,@RequestParam String username) {

    SysUser user = new SysUser(id,email,locked,username);

    boolean b = sus.modifyUser(user);
    if (b) {
      return JsonTool.getJsonTransportEntity(true, "修改用户完成");
    } else {
      return JsonTool.getJsonTransportEntity(false, "修改用户失败");
    }
  }

  /**
   * 添加用户
   * @param user
   */
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_USER_ADD)
  public JsonTransportEntity userAdd(SysUser user)  {

    boolean b = sus.addUser(user);
    if (b) {
      return JsonTool.getJsonTransportEntity(true, "用户添加成功,初始密码【88888888】");
    } else {
      return JsonTool.getJsonTransportEntity(false, "用户添加失败");
    }
  }

  /**
   * 获取角色列表
   * @return
   */
  @RequestMapping(value = "/roleList", method = RequestMethod.GET)
  public JsonTransportEntity getUserList(){
    List<SysRole> list = srs.getAllSysRole();
    JsonTransportEntity jte = new JsonTransportEntity();
    jte.setFlag(true);
    jte.setList(list);
    return jte;
  }

  /**
   * 添加角色
   * @param role
   */
  @RequestMapping(value = "/role", method = RequestMethod.POST)
  // @RequiresPermissions(ConstantesPermission.PERMISSION_ROLE_ADD)
  public JsonTransportEntity roleAdd(SysRole role)  {

    boolean b = srs.addRole(role);
    if (b){
      return JsonTool.getJsonTransportEntity(true, "角色添加成功");
    }
    else {
      return  JsonTool.getJsonTransportEntity(false, "角色添加失败");
    }
  }

  /**
   * 修改角色 名称
   *
   * @param role
   */
  @RequestMapping(value = "/role", method = RequestMethod.PUT)
  // @RequiresPermissions(ConstantesPermission.PERMISSION_ROLE_EDIT)
  public JsonTransportEntity roleModify(SysRole role)  {

    boolean b = srs.modifyRoleName(role);
    if (b) {
      return JsonTool.getJsonTransportEntity(true, "名称已改为" + role.getName());
    } else {
      return JsonTool.getJsonTransportEntity(false, "修改失败");
    }
  }


  /**
   * 删除角色
   */
  @RequestMapping(value = "/role", method = RequestMethod.DELETE)
  // @RequiresPermissions(ConstantesPermission.PERMISSION_ROLE_DELETE)
  public JsonTransportEntity roleDelete(long id) {

    boolean b = srs.deleteRole(id);
    if (b) {
      return JsonTool.getJsonTransportEntity(true, "删除成功");
    } else {
      return JsonTool.getJsonTransportEntity(false, "删除失败");
    }
  }



// 资源 Begin
  /**
   * 只有节点信息
   * @param pid
   * @throws IOException
   */
  @RequestMapping(value = "/resourcesNode" ,method = RequestMethod.GET)
  public JsonTransportEntity permissionGetNode(@RequestParam(value = "pid",defaultValue = "0") long pid ) throws IOException {
    SysPermission meun = sps.getOne(pid);
    JsonTransportEntity jte = new JsonTransportEntity();
    jte.setEntity(meun);
    jte.setFlag(meun==null?false:true);
    return jte;
  }

  /**
   * 节点和子节点
   * @param pid
   * @throws IOException
   */
  @RequestMapping(value = "/resourcesChildrenNode" , method = RequestMethod.GET)
  public JsonTransportEntity permissionGetChildrenNode(@RequestParam(value = "pid",defaultValue = "0") long pid) throws IOException {
    if(pid<=0){
      return JsonTool.getJsonTransportEntity(false,"参数不正确");
    }
    MeunDetails meun = sps.getMeunDetails(pid);
    JsonTransportEntity jte = new JsonTransportEntity();
    jte.setEntity(meun);
    jte.setFlag(meun==null?false:true);
    return jte;
  }



  /**
   * 添加权限/菜单/按钮等
   *
   * 对permission表的操作
   *
   * @throws IOException
   */
  @RequestMapping(value = "/resourcesNode", method = RequestMethod.POST)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_PERMISSION_ADD)
  public JsonTransportEntity permissionAdd(SysPermission permission) throws IOException {

    boolean b = sps.addPermission(permission);
    if (b) {
      return  JsonTool.getJsonTransportEntity(true, "功能添加成功");
    } else {
      return JsonTool.getJsonTransportEntity(false, "功能添加失败");
    }
  }


  /**
   * 删除permission
   *
   * @throws IOException
   */
  @RequestMapping(value = "/resourcesNode", method = RequestMethod.DELETE)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_PERMISSION_DELETE)
  public JsonTransportEntity permissionDelete(long permissionId)  {

    boolean b = sps.deletePermission(permissionId);
    if (b) {
      return  JsonTool.getJsonTransportEntity(true, "删除成功");
    } else {
      return  JsonTool.getJsonTransportEntity(false, "删除失败");
    }
  }



  /**
   * 获取菜单详情
   *
   */
  @RequestMapping(value = "/resourcesNodeMeunDetails", method = RequestMethod.GET)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_PERMISSION)
  public JsonTransportEntity permissionGetMeunDetails(SysPermission permission)  {

    MeunDetails ms= sps.getMeunDetails(permission.getId());
    JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
    if(ms != null){
      jsonTransportEntity.setFlag(true);
      jsonTransportEntity.setEntity(ms);
    }
    return jsonTransportEntity;
  }

  /**
   * 修改权限可用状态
   *
   */
  @RequestMapping(value = "/resourcesNodeChangeState", method = RequestMethod.PUT)
  //@RequiresPermissions(ConstantesPermission.PERMISSION_PERMISSION_STATE)
  public JsonTransportEntity permissionChangeState(long permissionId)
     {

    boolean b = sps.modifypermissionState(permissionId);
    if (b) {
      return  JsonTool.getJsonTransportEntity(true, "状态修改成功");
    } else {
      return  JsonTool.getJsonTransportEntity(false, "状态修改失败");
    }
  }

  @RequestMapping(value = "/resourcesNode", method = RequestMethod.PUT)
  public JsonTransportEntity permissionChangeData(long permissionId, String name,String percode,String sortstring,String url)
     {

    boolean b = sps.edit(permissionId,name,percode,sortstring,url);
    if (b) {
      return  JsonTool.getJsonTransportEntity(true, "状态修改成功");
    } else {
      return  JsonTool.getJsonTransportEntity(false, "状态修改失败");
    }
  }
// 资源 End


}
