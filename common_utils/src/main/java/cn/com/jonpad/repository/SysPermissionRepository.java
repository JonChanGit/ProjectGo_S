package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jon75 on 2017/6/4.
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
	/**
	 * 菜单
	 */
	public static final String MENU_TREE_TYPE_MENU = "menu";
	/**
	 * 根
	 */
	public static final String MENU_TREE_TYPE_ROOT = "root";
	/**
	 * 按钮
	 */
	public static final String MENU_TREE_TYPE_BUTTON = "button";

	/**
	 * 根据用户ID和菜单类型 查找菜单
	 *
	 * @param type   菜单类型 menu/root
	 * @param userid 用户ID
	 * @return
	 */
	@Query(value = "select sp from SysPermission sp where available = 1 and type = ?1 and id in (select sysPermissionId from SysRolePermission where sysRoleId in (select sysRoleId from SysUserRole where  sysUserId = ?2 )) order by sortstring")
	List<SysPermission> findMenuTreeByUserIdAndType(String type, String userid);
}
