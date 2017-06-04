package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.entity.SysUserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jon75 on 2017/6/2.
 */
public interface SysUserSecurityRepository extends JpaRepository<SysUserSecurity, Long> {
}
