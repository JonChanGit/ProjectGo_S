package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.entity.SysUserRole;
import cn.com.jonpad.repository.SysRoleRepository;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.repository.SysUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jon75 on 2017/8/28.
 */
@Service
public class SysRoleService {
	@Autowired
	private SysRoleRepository srr;
	@Autowired
	private SysUserRoleRepository surr;


	public List<SysRole> getAllSysRole() {
		return srr.findAll();
	}

	@Transactional
	public boolean addRole(SysRole role) {
		srr.save(role);
		return true;
	}

	@Transactional
	public boolean modifyRoleName(SysRole role) {
		SysRole one = srr.getOne(role.getId());
		if(one != null){
			one.setName(role.getName());
			srr.save(one);
			return  true;
		}
		return false;
	}

	@Transactional
	public boolean modifyRoleAvailable(long id) {
		SysRole role = srr.getOne(id);
		role.setAvailable(role.getAvailable()==1?0:1);
		srr.save(role);
		return true;
	}

	@Transactional
	public boolean deleteRole(long id) {
		srr.delete(id);
		return true;
	}

	public List<SysRole> getAllAvailableSysRole() {
		return srr.findByAvailable(SysRole.AVAILABLE_TRUE);
	}

	public List<SysUserRole> getSysUserRoleList(long id) {
		return surr.findBySysUserId(String.valueOf(id));
	}
}
