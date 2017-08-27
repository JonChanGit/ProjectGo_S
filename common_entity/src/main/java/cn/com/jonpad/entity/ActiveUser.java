package cn.com.jonpad.entity;

import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.entity.SysUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @Title: ActiveUser.java
 * @Package com.jon.web.entity.dto
 * @Description: 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * @author JonChan
 * @date 2016年5月22日 上午8:26:08
 * @version V1.0
 *
 */
public class ActiveUser implements Serializable {

	private String userid;// 用户id（主键）
	private String usercode;// 用户账号
	private String username;// 用户名称
	private String useremail;// 用户邮箱
	private String userinfo;//用户签名
	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	private Map<String, List<SysPermission>> menus;// 菜单
	
	/**
	 * 管理员对象
	 * 从数据库查出来的
	 */
	private SysUser sysUser;

	private List<SysPermission> permissions;// 权限
	 
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Map<String, List<SysPermission>> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, List<SysPermission>> menus) {
		this.menus = menus;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public String getUsercode() {
		return usercode;
	}

	public String getUseremail() {
		return useremail;
	}

	public String getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	 

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
