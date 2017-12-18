package cn.com.jonpad.mybatis;

import cn.com.jonpad.entity.SysGroupRolePermission;
import org.apache.ibatis.annotations.Param;

public interface SysGroupRolePermissionDao {
  long countByProps(@Param("gid") long gid, @Param("rId") long rId, @Param("pid") long pid);
}
