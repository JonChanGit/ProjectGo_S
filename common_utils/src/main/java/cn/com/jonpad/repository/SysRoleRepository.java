package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jon75 on 2017/8/28.
 */
public interface SysRoleRepository  extends JpaRepository<SysRole, Long> {
	List<SysRole> findByAvailable(int available);
}
