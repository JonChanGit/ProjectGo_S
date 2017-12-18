package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysGroupRolePermission;
import cn.com.jonpad.mybatis.SysGroupRolePermissionDao;
import cn.com.jonpad.repository.SysGroupRolePermissionRepository;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jon75
 */
@Service
public class SysGroupRolePermissionService {
  @Autowired
  private SysGroupRolePermissionRepository sgrpr;
  @Autowired
  private SysGroupRolePermissionDao sgrpDao;


  public JsonTransportEntity regist(long pid, long groupId, long roleId) {
    if(pid <= 0 || groupId <= 0 || roleId <= 0){
      return new JsonTransportEntity().setFlag(false).setMessage("参数错误");
    }
    long count = sgrpDao.countByProps(groupId, roleId, pid);
    if(count > 0){
      return new JsonTransportEntity().setFlag(false).setMessage("选择的组合已经存在");
    }
    SysGroupRolePermission sgrp = new SysGroupRolePermission(groupId,roleId,pid);
    sgrpr.save(sgrp);
    return new JsonTransportEntity().setFlag(true).setMessage("操作成功");
  }
}
