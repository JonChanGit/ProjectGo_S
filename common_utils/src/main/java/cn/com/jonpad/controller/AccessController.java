package cn.com.jonpad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jon75 on 2017/5/21.
 */
@Controller
@RequestMapping("/access")
public class AccessController extends BaseController{

	/**
	 * 登陆成功入口
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	//@RequiresPermissions(ConstantesPermission.ADMIN)
	public String toIndex(HttpServletRequest request, Model model) {
		return "admin/index";
	}

}
