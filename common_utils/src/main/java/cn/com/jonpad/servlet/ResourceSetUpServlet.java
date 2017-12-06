package cn.com.jonpad.servlet;

import cn.com.jonpad.entity.*;
import cn.com.jonpad.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
		application.setAttribute("app_PageTitle", "统一认证平台");

		System.out.println("---------resource setup [Service] startED---------");
	}

	public void initDate(){
		ApplicationContext applicationContext = getApplicationContext();

    SysRoleService srs = applicationContext.getBean(SysRoleService.class);
    SysRole administratorRole = srs.getAdministratorRole();
    if(administratorRole == null){
      administratorRole = new SysRole();
      administratorRole.setAvailable(SysRole.AVAILABLE_TRUE);
      administratorRole.setName(SysRole.Super_Administrator_Name);
      srs.addRole(administratorRole);
    }

    SysUserService sus =applicationContext.getBean(SysUserService.class);
    List<SysUserRole> administrationList = sus.getAdministration();
    SysUser user = null;
    if (administrationList.size() < 1){
      user = new SysUser();
			user.setEmail("admin@admin.com");
			user.setInfo("超级管理员");
			user.setLocked(0);
			user.setUsercode("admin");
			user.setUsername("超级管理员");
			user.setHead("dist/img/no_profile.png");
			sus.addUser(user,"system");

			// 注册权限
      SysUserRoleService surs = applicationContext.getBean(SysUserRoleService.class);
      surs.registerAdministration(user.getId());

		}else{

    }

    SysPermissionServics sps = applicationContext.getBean(SysPermissionServics.class);
    long spsSize = sps.countSize();
    if(spsSize < 1){
      SysPermission sp = new SysPermission();
      sp.setAvailable(1);
      sp.setName("Root");
      sp.setParentid(0L);
      sp.setParentids("0");
      sp.setPercode("root");
      sp.setRootParentid(0L);
      sp.setSortstring("0");
      sp.setType(SysPermission.MENU_TREE_TYPE_ROOT);
      sp.setUrl("/#");
      sps.addPermission(sp);
    }

    SysGroupServics sgs = applicationContext.getBean(SysGroupServics.class);
    long sgsSize = sgs.count();
    if(sgsSize < 1){
      SysGroup sp = new SysGroup();
      sp.setAvailable(1);
      sp.setName("Root");
      sp.setParentid(0L);
      sp.setParentids("0");
      sp.setRootParentid(0L);
      sp.setSortstring("0");
      sgs.addGroup(sp);
    }

  }

}
