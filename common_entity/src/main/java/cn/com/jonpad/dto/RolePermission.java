package cn.com.jonpad.dto;

public class RolePermission {
  long permissionId;
  String type;
  long roleId;
  int level;
  boolean leaf;

  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public RolePermission() {
  }

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }

  public RolePermission(RolePermission ent) {
    this.permissionId = ent.getPermissionId();
    this.type = ent.getType();
    this.roleId = ent.getRoleId();
    this.level = ent.getLevel();
  }
}
