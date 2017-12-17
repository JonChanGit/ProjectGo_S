package cn.com.jonpad.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysGroupPermissionDao {
  void regist(@Param("permissionId") long permissionId, @Param("list") List groupIds);
}
