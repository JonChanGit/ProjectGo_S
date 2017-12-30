package cn.com.jonpad.repository;

import cn.com.jonpad.entity.MemoryData;
import cn.com.jonpad.entity.MemoryOriginalData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jon75 on 2017/6/4.
 */
public interface MemoryDataRepository extends JpaRepository<MemoryData, Long> {

}
