package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysGroup;
import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.repository.SysGroupRepository;
import cn.com.jonpad.util.ValidateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jon75
 * @date 2017/6/4
 */
@Service
public class SysGroupServics {
  @Autowired
  SysGroupRepository sgr;


  @Transactional(rollbackFor = Exception.class)
  public boolean addPermission(SysGroup group) {
    if (!ValidateTool.isEmptyString(group.getName())) {
      /*// 直接父亲节点
      SysPermission parentSp = sgr.findById(group.getParentid());
      // 最终父亲节点
      SysPermission rootParentsp = this.getRootPparentByParentPermission(parentSp);
      if (rootParentsp != null) {
        group.setRootParentid(rootParentsp.getId());
      } else {
        group.setRootParentid(parentSp.getId());
      }
      if (!ValidateTool.isEmptyString(parentSp.getParentids())) {
        group.setParentids(parentSp.getParentids() + "," + parentSp.getId());
      } else {
        group.setParentids(parentSp.getId() + "");
      }
      sgr.save(permission);*/
      return true;
    }

    return false;
  }
}
