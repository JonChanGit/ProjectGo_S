package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jon75 on 2017/6/2.
 */
public interface SysUserRepository  extends JpaRepository<SysUser, Long> {
	public SysUser findByEmailOrUsercode(String email,String usercode);

}
