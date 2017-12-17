package cn.com.jonpad.service;

import cn.com.jonpad.mybatis.SysGroupPermissionDao;
import cn.com.jonpad.mybatis.SysRolePermissionDao;
import cn.com.jonpad.repository.SysGroupPermissionRepository;
import cn.com.jonpad.repository.SysRolePermissionRepository;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jon75 on 2017/9/9.
 */
@Service
public class SysGroupPermissionService {
  @Autowired
  private SysGroupPermissionDao dao;
  @Autowired
  private SysGroupPermissionRepository srpr;

  @Transactional(rollbackFor = Exception.class)
  public JsonTransportEntity regist(long pid, List<Long> groupIds){
    srpr.deleteBySysPermissionId(String.valueOf(pid));
    if(groupIds.size() > 0){
      dao.regist(pid,groupIds);
    }
    return new JsonTransportEntity().setFlag(true).setMessage("操作成功");
  }

}
