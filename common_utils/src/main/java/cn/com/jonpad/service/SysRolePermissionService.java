package cn.com.jonpad.service;

import cn.com.jonpad.dto.RolePermission;
import cn.com.jonpad.entity.SysRolePermission;
import cn.com.jonpad.mybatis.SysPermissionDao;
import cn.com.jonpad.mybatis.SysRolePermissionDao;
import cn.com.jonpad.repository.SysPermissionRepository;
import cn.com.jonpad.repository.SysRolePermissionRepository;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jon75 on 2017/9/9.
 */
@Service
public class SysRolePermissionService {
  @Autowired
  private SysRolePermissionDao dao;
  @Autowired
  private SysRolePermissionRepository srpr;

  @Transactional(rollbackFor = Exception.class)
  public JsonTransportEntity regist(long pid, List<Long> roleIds){
    srpr.deleteBySysPermissionId(String.valueOf(pid));
    //去除超级管理员角色
    roleIds = roleIds.stream()
      .filter(l -> !l.equals(1L))
      .collect(Collectors.toList());
    dao.regist(pid,roleIds);
    return new JsonTransportEntity().setFlag(true);
  }
}
