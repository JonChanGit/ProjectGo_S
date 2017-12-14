package cn.com.jonpad.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionDao {
  void regist(@Param("permissionId") long permissionId, @Param("list") List roleIds);
  List<Long> findRoleListByPermission(@Param("pid") long permissionId);
}
