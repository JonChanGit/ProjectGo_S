package cn.com.jonpad.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_sys_group_role_permission")
public class SysGroupRolePermission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "p_i_id")
  private long id;

  /**
   * 组id
   */
  @Column(name = "f_c_sysGroupId")
  private String sysGroupId;

  /**
   * 角色id
   */
  @Column(name = "f_c_sysRoleId")
  private String sysRoleId;

  /**
   * 权限id
   */
  @Column(name = "f_c_sysPermissionId")
  private String sysPermissionId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSysGroupId() {
    return sysGroupId;
  }

  public void setSysGroupId(String sysGroupId) {
    this.sysGroupId = sysGroupId.trim();
  }

  public String getSysPermissionId() {
    return sysPermissionId;
  }

  public void setSysPermissionId(String sysPermissionId) {
    this.sysPermissionId = sysPermissionId.trim();
  }

  public String getSysRoleId() {
    return sysRoleId;
  }

  public void setSysRoleId(String sysRoleId) {
    this.sysRoleId = sysRoleId;
  }

  public SysGroupRolePermission() {
  }

  public SysGroupRolePermission(long sysGroupId, long sysRoleId, long sysPermissionId) {
    this.sysGroupId = String.valueOf(sysGroupId);
    this.sysRoleId = String.valueOf(sysRoleId);
    this.sysPermissionId = String.valueOf(sysPermissionId);
  }
}
