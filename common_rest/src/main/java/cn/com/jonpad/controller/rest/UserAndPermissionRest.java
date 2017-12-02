package cn.com.jonpad.controller.rest;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysUserService;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/access/user_and_permission")
public class UserAndPermissionRest {

  @Autowired
  private SysUserService sus;

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  public JsonTransportEntity getUserList(@RequestParam(value="page", defaultValue="1") int page,
                                         @RequestParam(value="searchKey", defaultValue="") String searchKey){
    Page<SysUser> userPage = sus.getPage(page, 10);

    return JsonTransportEntity.getInstance(userPage);
  }
}
