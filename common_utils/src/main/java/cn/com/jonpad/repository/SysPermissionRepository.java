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
	 * 根据用户ID和菜单类型 查找菜单
	 *
	 * @param type   菜单类型 menu/root
	 * @param userid 用户ID
	 * @return
	 */
	@Query(value = "select sp from SysPermission sp where available = 1 and type = ?1 and id in (select sysPermissionId from SysRolePermission where sysRoleId in (select sysRoleId from SysUserRole where  sysUserId = ?2 )) order by sortstring")
	List<SysPermission> findMenuTreeByUserIdAndType(String type, String userid);


  @Query(value = "SELECT sp FROM SysPermission sp WHERE type = 'root' ORDER BY id")
  List<SysPermission>  getRoot();


	/**
	 * 获取所有根节点
	 * @return
	 */
	@Deprecated
	@Query(value = "select sp from SysPermission sp where type = 'root' order by sortstring")
	List<SysPermission> getAllRootSysPermission();

	@Query(value = "select sp from SysPermission sp where parentid = ?1 ")
	List<SysPermission> getMenuByRoot(long rootId);

	SysPermission findById(long parentid);
	long countById(long parentid);

	/**
	 * 查找子节点
	 * @param parentId
	 * @return
	 */
	@Query(value = " select sp from SysPermission sp where parentid = ?1")
	List<SysPermission> findChildPermission(long parentId);

	@Query(value = "select sp from SysPermission sp where type='button' and parentid= ?1 ")
	List<SysPermission> getButtonsByMeun(long parentid);


	@Query(value = "select sp from SysPermission sp where parentid = ?1 or rootPparentid = ?1 ")
	List<SysPermission> findChildrenSysPermission(long id);

	List<SysPermission> findByAvailable(int available);

  /**
   * 根据父节点查找子节点数量
   * @param parentid
   * @return
   */
	long countByParentid(long parentid);
}
