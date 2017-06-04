package cn.com.jonpad.servlet;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.service.SysUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jon75 on 2017/6/3.
 */
public class ResourceSetUpServlet extends HttpServlet {


	public ApplicationContext getApplicationContext(){
		return WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		initDate();
		loadApplicationAttribute( getServletContext());
	}

	/**
	 * 装载application属性
	 */
	public void loadApplicationAttribute( ServletContext application) {
		System.out.println("---------resource setup [Service] start---------");

		//ServletContext application = getServletContext();


		//页面标题后缀模板
		application.setAttribute("app_PageTitle", "大学上课考勤系统");

		System.out.println("---------resource setup [Service] startED---------");
	}

	public void initDate(){
		ApplicationContext applicationContext = getApplicationContext();

		SysUserService sus =(SysUserService)applicationContext.getBean("sysUserService");
		long size = sus.getUserCount();
		if (size < 1){
			SysUser user = new SysUser();
			user.setEmail("admin@admin.com");
			user.setInfo("超级管理员");
			user.setLocked(0);
			user.setUsercode("admin");
			user.setUsername("超级管理员");
			user.setHead("dist/img/no_profile.png");
			sus.addUser(user,"system");
		}

	}

}
