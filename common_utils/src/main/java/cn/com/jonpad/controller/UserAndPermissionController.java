package cn.com.jonpad.controller;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysUserService;
import cn.com.jonpad.util.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		return MVC_VIEW_ROOT_PATH+"UserManagement";
	}

	/**
	 * 添加用户
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	//@RequiresPermissions(ConstantesPermission.PERMISSION_USER_ADD)
	public void userAdd(SysUser user, HttpServletRequest request, HttpServletResponse response) throws IOException {

		boolean b = sus.addUser(user);
		if (b)
			JsonTool.toJson(true, "用户添加成功,初始密码【88888888】", response);
		else {
			JsonTool.toJson(false, "用户添加失败", response);
		}
	}

	/**
	 * 删除用户
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userDelete", method = RequestMethod.POST)
	// @RequiresPermissions(ConstantesPermission.PERMISSION_USER_DELETE)
	public void userDelete(SysUser user, HttpServletRequest request, HttpServletResponse response) throws IOException {

		boolean b = sus.deleteUser(user.getId());
		if (b)
			JsonTool.toJson(true, "删除成功", response);
		else {
			JsonTool.toJson(false, "删除失败", response);
		}
	}

	/**
	 * 修改用户
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userModify", method = RequestMethod.POST)
	// @RequiresPermissions(ConstantesPermission.PERMISSION_USER_EDIT)
	public void userModify(SysUser user, HttpServletRequest request, HttpServletResponse response) throws IOException {

		boolean b = sus.modifyUser(user);
		if (b)
			JsonTool.toJson(true, "修改用户完成", response);
		else {
			JsonTool.toJson(false, "修改用户失败", response);
		}
	}




	/**
	 * 修改可用状态
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userChangeState", method = RequestMethod.POST)
	// @RequiresPermissions(ConstantesPermission.PERMISSION_USER_STATE)
	public void userChangeState(long userId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		boolean b = sus.modifyUserState(userId);
		if (b)
			JsonTool.toJson(true, "状态修改成功", response);
		else {
			JsonTool.toJson(false, "状态修改失败", response);
		}
	}

}
