package cn.com.jonpad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 尽可能的不用此方法，，因为URL增加会导致性能下降。
 * Created by jon75 on 2017/6/1.
 */
//@Controller
public class TestRESTController extends BaseController{

	@RequestMapping(value="/rest/{id}",method= RequestMethod.GET)
	public String testRESTGet(@PathVariable String id, Model model) {
		model.addAttribute("id",id);
		model.addAttribute("method","get");
		return "jsppage/TestRest";
	}
	@RequestMapping(value="/rest/{id}",method= RequestMethod.DELETE)
	public String testRESTDel(@PathVariable String id, Model model) {
		model.addAttribute("id",id);
		model.addAttribute("method","DELETE");
		return "jsppage/TestRest";
	}
}
