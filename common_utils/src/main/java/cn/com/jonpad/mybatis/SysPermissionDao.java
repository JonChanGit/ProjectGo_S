package cn.com.jonpad.mybatis;

import cn.com.jonpad.dto.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysPermissionDao {
  List<RolePermission> findSysRolePermissionListBySysRoleId(@Param("roleId") long roleId);
}
