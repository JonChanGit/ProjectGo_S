package cn.com.jonpad.controller;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jon75 on 2017/8/22.
 */
@Controller
@RequestMapping("/access/user_and_permission")
public class UserAndPermissionController extends BaseController{
	private final String MVC_VIEW_ROOT_PATH = "admin\\user_and_permission\\";

	@Autowired
	private SysUserService sus;


	@RequestMapping(value = "/userIndex", method = RequestMethod.GET)
	// @RequiresPermissions(ConstantesPermission.PERMISSION_USER)
	public String userIndexPage(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="searchKey", defaultValue="") String searchKey) {
		Page<SysUser> userPage = sus.getPage(page, 10);
		model.addAttribute("page",userPage);
		System.out.printf(userPage.toString());
		return MVC_VIEW_ROOT_PATH+"UserManagement";
	}
}
