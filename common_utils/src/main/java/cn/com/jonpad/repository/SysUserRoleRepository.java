package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by jon75 on 2017/9/3.
 */
public interface SysUserRoleRepository  extends JpaRepository<SysUserRole, Long> {
	List<SysUserRole> findBySysUserId(String userId);

	Set<SysUserRole> findBySysUserId(long userId);

	List<SysUserRole> findBySysRoleId(long roleId);
}
