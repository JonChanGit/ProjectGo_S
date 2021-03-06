package cn.com.jonpad.entity;

import javax.persistence.*;

/**
 *
 *
 * @Title: SysPermission.java
 * @Package com.jon.web.entity
 * @Description: 资源 --- 菜单
 * @author JonChan
 * @date 2016年5月22日 上午8:44:03
 * @version V1.0
 *
 */
@Entity
@Table(name = "t_sys_permission")
public class SysPermission implements java.io.Serializable{
	/**
	 * 菜单
	 */
	public static final String MENU_TREE_TYPE_MENU = "menu";
	/**
	 * 根
	 */
	public static final String MENU_TREE_TYPE_ROOT = "root";
	/**
	 * 按钮
	 */
	public static final String MENU_TREE_TYPE_BUTTON = "button";

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
	 * 资源名称
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
	 * 权限代码字符串
	 */
	@Column(name="c_percode")
	private String percode;

	/**
	 * 最终父结点id
	 * 即祖宗
	 */
	@Column(name="i_rootParentid")
	private Long rootParentid;

	/**
	 * 排序号
	 */
	@Column(name="c_sortstring")
	private String sortstring;

	/**
	 * 资源类型：root,menu,button,
	 */
	@Column(name="c_type")
	private String type;

	/**
	 * 访问url地址
	 */
	@Column(name="c_url")
	private String url;

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

	public String getPercode() {
		return percode;
	}

	public String getSortstring() {
		return sortstring;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
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

	public void setPercode(String percode) {
		this.percode = percode;
	}

  public Long getRootParentid() {
    return rootParentid;
  }

  public void setRootParentid(Long rootParentid) {
    this.rootParentid = rootParentid;
  }

  public void setSortstring(String sortstring) {
		this.sortstring = sortstring;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }

  public SysPermission() {
  }

  public SysPermission(SysPermission oldObj) {
	  this.id = oldObj.getId();
    this.available = oldObj.getAvailable();
    this.name = oldObj.getName();
    this.parentid = oldObj.getParentid();
    this.parentids = oldObj.getParentids();
    this.percode = oldObj.getPercode();
    this.sortstring = oldObj.getSortstring();
    this.type = oldObj.getType();
    this.url = oldObj.getUrl();
    this.rootParentid = oldObj.getRootParentid();
  }
}
