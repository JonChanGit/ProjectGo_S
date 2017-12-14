package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by jon75 on 2017/9/9.
 */
public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, Long> {
	Set<SysRolePermission> findBySysRoleId(String roleId);

	void deleteBySysPermissionId(String permissionId);

}
