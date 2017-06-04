package cn.com.jonpad.controller;


import javax.servlet.http.HttpServletRequest;

import cn.com.jonpad.shiro.exception.CustomException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	 
	/**
	 * 登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model)throws Exception{
		
		//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("msg","账号不存在,如果没有账号，请注册后在登录☺");
				//最终会抛给异常处理器
				//throw new CustomException("账号不存在,如果没有账号，请注册后在登录☺");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				model.addAttribute("msg","用户名/密码错误,请按下面返回按钮重试\uD83D\uDE02");
				//throw new CustomException("用户名/密码错误,请按下面返回按钮重试😂");
			}else if ("randomCodeError".equals(exceptionClassName)) {
				model.addAttribute("msg","验证码错误,请填写正确的验证码\uD83D\uDE0F");
				//throw new CustomException("验证码错误,请填写正确的验证码😏");
			}else {
				model.addAttribute("msg","发生未知错误");
				//throw new Exception();//最终在异常处理器生成未知错误
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败还到login页面
		return "jsppage/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showLoginPage(){
		return "jsppage/login";
	}
	
	 
	
}
