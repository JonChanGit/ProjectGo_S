package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysGroup;
import cn.com.jonpad.entity.SysGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jon75 on 2017/6/4.
 */
public interface SysGroupPermissionRepository extends JpaRepository<SysGroupPermission, Long> {

  void deleteBySysPermissionId(String permissionId);

}
