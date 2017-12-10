package cn.com.jonpad.dto;

import cn.com.jonpad.entity.SysGroup;
import cn.com.jonpad.entity.SysPermission;

import java.util.List;

/**
 * 组详情
 * Created by jon75 on 2017/9/2.
 */
public class GroupDetails {
	SysGroup details;
	List<SysGroup> children;

  public SysGroup getDetails() {
    return details;
  }

  public void setDetails(SysGroup details) {
    this.details = details;
  }

  public List<SysGroup> getChildren() {
    return children;
  }

  public void setChildren(List<SysGroup> children) {
    this.children = children;
  }
}
