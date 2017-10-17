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

	@Transactional(rollbackFor = Exception.class)
	public boolean addRole(SysRole role) {
    //不允许重复名称
    long size = srr.countAllByName(role.getName());
    if(size > 0){
      return false;
    }
		srr.save(role);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean modifyRoleName(SysRole role) {
	  //不允许重复名称
    long size = srr.countAllByName(role.getName());
    if(size > 0){
      return false;
    }
    SysRole one = srr.getOne(role.getId());
    //如果是超级管理员，拒绝更改
		if(one != null){

		  if(SysRole.Super_Administrator_Name.equals(one.getName())){
		    return false;
      }

			one.setName(role.getName());
			srr.save(one);
			return  true;
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean modifyRoleAvailable(long id) {
		SysRole role = srr.getOne(id);
    if(role != null){
      if(SysRole.Super_Administrator_Name.equals(role.getName())){
        return false;
      }
      role.setAvailable(role.getAvailable()==1?0:1);
      srr.save(role);
      return true;
    }
    return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean deleteRole(long id) {
    SysRole role = srr.getOne(id);
    if(role != null){
      if (SysRole.Super_Administrator_Name.equals(role.getName())){
        return false;
      }
      srr.delete(id);
    }
		return true;
	}

	public List<SysRole> getAllAvailableSysRole() {
		return srr.findByAvailable(SysRole.AVAILABLE_TRUE);
	}

	public List<SysUserRole> getSysUserRoleList(long id) {
		return surr.findBySysUserId(String.valueOf(id));
	}

	public long getRoleCount(){
	  return srr.count();
  }

  public SysRole getAdministratorRole(){
    return srr.findByName(SysRole.Super_Administrator_Name);
  }

}
