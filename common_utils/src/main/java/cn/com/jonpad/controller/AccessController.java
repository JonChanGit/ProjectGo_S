package cn.com.jonpad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jon75 on 2017/5/21.
 */
@Controller
public class AccessController extends BaseController{


	@RequestMapping(value="/login",method= RequestMethod.GET)
	public String login(Model model) {
		return "jsppage/login";
	}

}
