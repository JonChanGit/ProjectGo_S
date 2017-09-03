package cn.com.jonpad.dto;

import cn.com.jonpad.entity.SysPermission;

import java.util.List;

/**
 * 菜单详情
 * Created by jon75 on 2017/9/2.
 */
public class MeunDetails {
	SysPermission details;
	List<SysPermission> buttons;

	public SysPermission getDetails() {
		return details;
	}

	public void setDetails(SysPermission details) {
		this.details = details;
	}

	public List<SysPermission> getButtons() {
		return buttons;
	}

	public void setButtons(List<SysPermission> buttons) {
		this.buttons = buttons;
	}
}
