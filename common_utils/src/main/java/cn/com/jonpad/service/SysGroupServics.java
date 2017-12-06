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

  public SysGroup findOne(long id){
    return sgr.findOne(id);
  }

  public long count(){
    return sgr.count();
  }


  @Transactional(rollbackFor = Exception.class)
  public boolean addGroup(SysGroup group) {
    if (!ValidateTool.isEmptyString(group.getName())) {
      if (group.getParentid() == 0) {
        sgr.save(group);
        return true;
      }
      // 直接父亲节点
      SysGroup parentSg = sgr.findOne(group.getParentid());
      // 最终父亲节点
      SysGroup rootParentsg = this.getRootParentByParentGroup(parentSg);
      if (rootParentsg != null) {
        group.setRootParentid(rootParentsg.getId());
      } else {
        group.setRootParentid(parentSg.getId());
      }
      if (!ValidateTool.isEmptyString(parentSg.getParentids())) {
        group.setParentids(parentSg.getParentids() + "," + parentSg.getId());
      } else {
        group.setParentids(parentSg.getId() + "");
      }
      sgr.save(group);
      return true;
    }

    return false;
  }

  /**
   * 获取最终父亲节点
   * @param parentSg
   * @return
   */
  public SysGroup getRootParentByParentGroup(SysGroup parentSg) {
    if (parentSg != null) {
      String[] tmpRootArr = null;
      if (parentSg.getParentids() != null) {
        tmpRootArr = parentSg.getParentids().split(",");
      }
      if (tmpRootArr != null && tmpRootArr.length > 0) {
        long tmpRootId = Integer.parseInt(tmpRootArr[0]);
        return sgr.findOne(tmpRootId);
      }

    }
    return null;
  }


}
