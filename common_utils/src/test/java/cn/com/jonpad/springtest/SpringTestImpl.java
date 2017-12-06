package cn.com.jonpad.springtest;

import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.mybatis.SysUserDao;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.service.SysRoleService;
import cn.com.jonpad.util.JsonTool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jon75 on 2017/11/11.
 */
public class SpringTestImpl extends SpringTest{

  @Autowired
  SysUserRepository sur;

  @Autowired
  SysUserDao sysUserDao;

  @Autowired
  SysRoleService srs;

  @Test
  public void loadTest(){
    System.out.printf("Spring装载测试");
  }

  /**
   * 测试JPA是否正常
   */
  @Test
  public void testJpa(){
    SysRole administratorRole = new SysRole();
    administratorRole.setAvailable(SysRole.AVAILABLE_TRUE);
    administratorRole.setName(SysRole.Super_Administrator_Name);
    srs.addRole(administratorRole);
  }

  @Test
  public void testJpa01(){
    SysRole administratorRole = new SysRole();
    administratorRole.setAvailable(SysRole.AVAILABLE_TRUE);
    administratorRole.setName("JPASAVETEST23");
    try {
      srs.addRole(administratorRole);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试MyBatis是否正常
   */
  @Test
  public void testMB(){
    SysUser u = new SysUser();
    u.setEmail("124");
    u.setUsername("234");
    sysUserDao.add(u);
  }

}
