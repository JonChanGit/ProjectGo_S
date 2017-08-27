package cn.com.jonpad.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jon75 on 2017/6/4.
 */
@Entity
@Table(name = "t_sys_userSecurity")
public class SysUserSecurity  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_i_id")
	private long id;

	@JoinColumn(name="i_user",unique = true)
	@OneToOne
	private SysUser sysUser;

	@Column(name="c_password")
	private String password;

	/**
	 * 盐
	 */
	@Column(name="c_salt")
	private String salt;





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
