package cn.com.jonpad.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_sys_user")
public class SysUser implements Serializable {
	
	/**
	 * 账号签名
	 */
	@Column(name="c_info",length = 100)
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name="c_email",length = 32)
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_i_id")
	private long id;

	/**
	 * 账号是否锁定，1：锁定，0未锁定
	 */
	@Column(name="i_locked")
	private int locked;


	@OneToOne(mappedBy="sysUser")
	private SysUserSecurity security;


	/**
	 * usercode
	 * 用户标识（可用于登陆）
	 */
	@Column(name="c_usercode")
	private String usercode;

	@Column(name="c_username")
	private String username;
	
	/**
	 * 头像
	 * 用户自己设置
	 */
	@Column(name="c_head")
	private String head;

	/**
	 * 
	 * @Title: getEmail 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public String getHead() {
		return head;
	}

	public long getId() {
		return id;
	}

	public int getLocked() {
		return locked;
	}

 


	public String getUsercode() {
		return usercode;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @Title: setEmail 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email.trim();
	}

	public void setHead(String head) {
		this.head = head;
	}

 
	public void setId(long id) {
		this.id = id;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}


	public void setUsercode(String usercode) {
		this.usercode = usercode.trim();
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}


	public SysUserSecurity getSecurity() {
		return security;
	}

	public void setSecurity(SysUserSecurity security) {
		this.security = security;
	}
}
