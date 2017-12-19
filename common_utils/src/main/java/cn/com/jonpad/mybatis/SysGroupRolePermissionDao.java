package cn.com.jonpad.mybatis;

import cn.com.jonpad.entity.SysGroupRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysGroupRolePermissionDao {
  long countByProps(@Param("gid") long gid, @Param("rId") long rId, @Param("pid") long pid);

  List<SysGroupRolePermission> findByPermissionId(@Param("pid") long pid);

  List<HashMap> findViewByPermissionId(@Param("pid") long pid);

}
