package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysRolePermission;
import cn.com.jonpad.repository.SysRolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jon75 on 2017/9/9.
 */
@Service
public class SysRolePermissionService {
	@Autowired
	private SysRolePermissionRepository srpr;

	public boolean addSysRolePermission(long roleId, String[] perStrArr) {
		/**
		 * 库中有的
		 */
		// Set<SysUserRole> sysUserRoleList =
		// sysUserRoleDao.getSysUserRoleSet(userId);

		/**
		 * 使用Set 由于数据库逻辑和Set相似，所以可以这样使用 如果有BUG后面修复
		 */

		Set<SysRolePermission> dbSet = srpr.findBySysRoleId(String.valueOf(roleId));

		/**
		 * 新的Set
		 */
		Set<SysRolePermission> newSet = new HashSet<>();

		if (perStrArr != null) {
			for (int index = 0; index < perStrArr.length; index++) {
			  if("0".equals(perStrArr[index])){
			    continue;
        }
				SysRolePermission tmp = new SysRolePermission();
				tmp.setSysPermissionId(perStrArr[index]);
				tmp.setSysRoleId(roleId + "");
				newSet.add(tmp);
			}
		}

		// 临时Set 一个备份
		Set<SysRolePermission> tmpSet = tmpSet = new HashSet<>(dbSet);

		if (dbSet.equals(newSet)) {
			// 没有变动，不做任何操作
			return true;
		} else {
			// 找出变动的元素
			// 加入新增的
			dbSet.addAll(newSet);
			// 移除不存在的？
			// 保留新集合中的元素
			dbSet.retainAll(newSet);
			// 需要删除的集合
			tmpSet.removeAll(dbSet);

			// 提交更改
			return this.saveOrUpdataAll(dbSet, tmpSet);
		}
	}

	private boolean saveOrUpdataAll(Set<SysRolePermission> dbSet, Set<SysRolePermission> deleteSet) {
		for (SysRolePermission item : dbSet) {
			srpr.saveAndFlush(item);
		}
		for (SysRolePermission item : deleteSet) {
			srpr.delete(item);
		}
		return true;
	}

	public List<SysRolePermission> getSysRolePermissionList(long id) {
		Set<SysRolePermission> set = srpr.findBySysRoleId(String.valueOf(id));
		return new ArrayList<>(set);
	}
}
