package cn.com.jonpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sys_role")
public class SysRole {
	public static  final int AVAILABLE_TRUE  = 1;
	public static  final int AVAILABLE_FALSE  = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_i_id")
	private long id;

	@Column(name="c_name")
    private String name;

    /**
     * 是否可用,1：可用，0不可用
     */
	@Column(name="i_available")
    private int available;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}
