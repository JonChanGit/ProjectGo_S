package cn.com.jonpad.shiro;

import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.entity.ActiveUser;
import cn.com.jonpad.service.SysPermissionServics;
import cn.com.jonpad.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by jon75 on 2017/6/1.
 */
//@Service("customRealm")
public class CustomRealm extends AuthorizingRealm {
	@Autowired
	private SysUserService sus;
	@Autowired
	private SysPermissionServics sps;


	/**
	 * 设置realm的名称
	 */
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 用于认证
	 *
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的
		// 第一步从token中取出身份信息
		String userCode = (String) token.getPrincipal();

		// 第二步：判断用户是否存在
		SysUser sysUser = null;
		try {
			sysUser = sus.findByEmailOrUsercode(userCode);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 如果查询不到返回null
		// 数据库中用户账号是759282337
		if (sysUser == null) {//
			return null;
		}
		// 从数据库查询到密码，密文
		String password = sysUser.getSecurity().getPassword();
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid("" + sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		activeUser.setUseremail(sysUser.getEmail());
		activeUser.setSysUser(sysUser);
		// 根据ID取菜单
		Map<String, List<SysPermission>> menuMap = null;
		menuMap = sps.findMenuTreeByUserId(sysUser.getId() + "");
		activeUser.setMenus(menuMap);

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,
				password,
				ByteSource.Util.bytes(sysUser.getSecurity().getSalt()),
				this.getName());

		return simpleAuthenticationInfo;
	}


	/**
	 * 清除缓存
	 * 调用方式：
	 * 使用Spring把当前Realm注入需要调用的类中
	 * 然后调用
	 */
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
