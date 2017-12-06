package cn.com.jonpad.entity;

import javax.persistence.*;

/**
 * @Title: SysGroup.java
 * @Package com.jon.web.entity
 * @Description: 用户组
 * @author JonChan
 * @date 2017年12月6日 上午8:44:03
 * @version V1.0
 *
 */
@Entity
@Table(name = "t_sys_group")
public class SysGroup implements java.io.Serializable{

	public static final  int AVAILABLE_TREU = 1;
	public static final  int AVAILABLE_FALSE = 0;

	/**
	 * 是否可用,1：可用，0不可用
	 */
	@Column(name="i_available")
	private int available;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_i_id")
	private long id;

	/**
	 * 组名称
	 */
	@Column(name="c_name")
	private String name;

	/**
	 * 直接父结点id
	 */
	@Column(name="i_parentid")
	private Long parentid;

	/**
	 * 父结点id列表串
	 *
	 * 使用 ，隔开，从0开始（Root之上）
	 */
	@Column(name="c_parentids")
	private String parentids;

	/**
	 * 最终父结点id
	 * 即祖宗
	 */
	@Column(name="i_rootParentid")
	private Long rootPparentid;

	/**
	 * 排序号
	 */
	@Column(name="c_sortstring")
	private String sortstring;


  /**
   * 是否为叶子节点(最终节点)
   */
  @Transient
  private boolean leaf;

	public int getAvailable() {
		return available;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getParentid() {
		return parentid;
	}

	public String getParentids() {
		return parentids;
	}


	public Long getRootPparentid() {
		return rootPparentid;
	}

	public String getSortstring() {
		return sortstring;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public void setParentids(String parentids) {
		this.parentids = parentids;
	}


	public void setRootPparentid(Long rootPparentid) {
		this.rootPparentid = rootPparentid;
	}

	public void setSortstring(String sortstring) {
		this.sortstring = sortstring;
	}

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }

  public SysGroup() {
  }

  public SysGroup(SysGroup oldObj) {
	  this.id = oldObj.getId();
    this.available = oldObj.getAvailable();
    this.name = oldObj.getName();
    this.parentid = oldObj.getParentid();
    this.parentids = oldObj.getParentids();
    this.rootPparentid = oldObj.getRootPparentid();
    this.sortstring = oldObj.getSortstring();
  }
}
