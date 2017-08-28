package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jon75 on 2017/8/28.
 */
@Service
public class SysRoleService {
	@Autowired
	private SysRoleRepository srr;

	public List<SysRole> getAllSysRole() {
		return srr.findAll();
	}

	public boolean addRole(SysRole role) {
		srr.save(role);
		return true;
	}

	public boolean modifyRoleName(SysRole role) {
		SysRole one = srr.getOne(role.getId());
		if(one != null){
			one.setName(role.getName());
			srr.save(one);
			return  true;
		}
		return false;
	}

	public boolean modifyRoleAvailable(long id) {
		SysRole role = srr.getOne(id);
		role.setAvailable(role.getAvailable()==1?0:1);
		srr.save(role);
		return true;
	}

	public boolean deleteRole(long id) {
		srr.delete(id);
		return true;
	}
}
