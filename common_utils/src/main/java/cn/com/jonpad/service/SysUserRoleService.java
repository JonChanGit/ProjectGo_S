package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysUserRole;
import cn.com.jonpad.repository.SysUserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jon75 on 2017/9/3.
 */
@Service
public class SysUserRoleService {
	private SysUserRoleRepository surr;

	public boolean addSysUserRole(long userId, String[] roleIdArr) {
		/**
		 * 库中有的
		 */
		// Set<SysUserRole> sysUserRoleList =
		// sysUserRoleDao.getSysUserRoleSet(userId);

		/**
		 * 使用Set 由于数据库逻辑和Set相似，所以可以这样使用 如果有BUG后面修复
		 */
		Set<SysUserRole> dbSet = surr.findBySysUserId(userId);

		/**
		 * 新的Set
		 */
		Set<SysUserRole> newSet = new HashSet<>();

		if (roleIdArr != null) {
			for (int index = 0; index < roleIdArr.length; index++) {
				SysUserRole tmp = new SysUserRole();
				tmp.setSysRoleId(roleIdArr[index]);
				tmp.setSysUserId(userId + "");
				newSet.add(tmp);
			}
		}

		// 临时Set 一个备份
		Set<SysUserRole> tmpSet = tmpSet = new HashSet<>(dbSet);

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

	private boolean saveOrUpdataAll(Set<SysUserRole> dbSet, Set<SysUserRole> deleteSet) {
		for (SysUserRole item : dbSet) {
			surr.saveAndFlush(item);
		}
		for (SysUserRole item : deleteSet) {
			surr.saveAndFlush(item);
		}
		return true;
	}
}