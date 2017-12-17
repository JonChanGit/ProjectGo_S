package cn.com.jonpad.service;

import cn.com.jonpad.dto.GroupDetails;
import cn.com.jonpad.entity.SysGroup;
import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.mybatis.SysGroupDao;
import cn.com.jonpad.repository.SysGroupRepository;
import cn.com.jonpad.util.ValidateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jon75
 * @date 2017/6/4
 */
@Service
public class SysGroupServics {
  @Autowired
  SysGroupRepository sgr;
  @Autowired
  SysGroupDao dao;

  public SysGroup findOne(long id) {
    return sgr.findOne(id);
  }

  public GroupDetails getGroupDetails(long id) {
    GroupDetails md = new GroupDetails();
    SysGroup group = sgr.findOne(id);
    if (group == null) {
      return null;
    }

    md.setDetails(group);
    List<SysGroup> groupList = sgr.findByParentid(id);

    List<SysGroup> rList = new ArrayList<>(groupList.size());
    for (SysGroup item : groupList) {
      long size = sgr.countByParentid(item.getId());
      SysGroup sg = new SysGroup(item);
      sg.setLeaf(size == 0);
      rList.add(sg);
    }
    md.setChildren(rList);
    return md;

  }

  public long count() {
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
   *
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


  public boolean deleteGroup(long id) {
    // 获取节点
    SysGroup group = sgr.findOne(id);
    if (group == null) {
      return false;
    }
    List<SysGroup> groupList = sgr.findByParentid(id);
    if (groupList != null && groupList.size() > 0) {
      // 有，不删除，报错
      return false;
    }
    // 没有 || 不是 删除
    sgr.delete(id);
    return true;
  }

  public boolean edit(long id, String name, String sortstring) {
    SysGroup group = sgr.findOne(id);
    if (group == null) {
      return false;
    }
    if (!StringUtils.isEmpty(name.trim())) {
      group.setName(name);
    }
    if (!StringUtils.isEmpty(sortstring.trim())) {
      group.setSortstring(sortstring);
    }
    sgr.saveAndFlush(group);

    return true;
  }

  public List<SysGroup> getAll() {
    return dao.findAllOrder();
  }
}
