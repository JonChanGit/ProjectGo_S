package cn.com.jonpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "t_sys_user_role")
public class SysUserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_i_id")
	private long id;

	@Column(name="f_i_sysUserId")
    private String sysUserId;

    /**
     * 角色ID
     */
	@Column(name="f_i_sysRoleId")
    private String sysRoleId;


    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId == null ? null : sysUserId.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }
    
    
    /**
     * id不在计算范围内
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUserRole other = (SysUserRole) obj;
		
		if (sysRoleId == null) {
			if (other.sysRoleId != null)
				return false;
		} else if (!sysRoleId.equals(other.sysRoleId))
			return false;
		if (sysUserId == null) {
			if (other.sysUserId != null)
				return false;
		} else if (!sysUserId.equals(other.sysUserId))
			return false;
		return true;
	}

    /**
     * id不在计算范围内
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((sysRoleId == null) ? 0 : sysRoleId.hashCode());
		result = prime * result + ((sysUserId == null) ? 0 : sysUserId.hashCode());
		return result;
	}
}
