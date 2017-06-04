package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.repository.SysPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jon75 on 2017/6/4.
 */
@Service
public class SysPermissionServics {
	@Autowired
	private SysPermissionRepository spr;

	/**
	 * 根据用户ID获取用户菜单
	 * @param userid
	 * @return
	 */
	public Map<String, List<SysPermission>> findMenuTreeByUserId(String userid){
		List<SysPermission> menuTree = spr.findMenuTreeByUserIdAndType(SysPermissionRepository.MENU_TREE_TYPE_MENU, userid);
		List<SysPermission> rootTree = spr.findMenuTreeByUserIdAndType(SysPermissionRepository.MENU_TREE_TYPE_ROOT, userid);
		Map<String, List<SysPermission>> map = new HashMap<String, List<SysPermission>>();
		map.put("menu", menuTree);
		map.put("root", rootTree);
		return map;
	}



}
