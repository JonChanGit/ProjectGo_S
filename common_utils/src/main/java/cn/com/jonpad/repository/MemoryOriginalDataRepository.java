package cn.com.jonpad.repository;

import cn.com.jonpad.entity.MemoryOriginalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jon75 on 2017/6/4.
 */
public interface MemoryOriginalDataRepository extends JpaRepository<MemoryOriginalData, Long> {

}
