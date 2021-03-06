package cn.com.jonpad.repository;

import cn.com.jonpad.entity.SysGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jon75 on 2017/6/4.
 */
public interface SysGroupRepository extends JpaRepository<SysGroup, Long> {

  List<SysGroup> findByParentid(long parentid);

  /**
   * 根据父节点查找子节点数量
   * @param parentid
   * @return
   */
  long countByParentid(long parentid);

}
