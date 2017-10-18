package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.entity.SysUserRole;
import cn.com.jonpad.entity.SysUserSecurity;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.repository.SysUserRoleRepository;
import cn.com.jonpad.repository.SysUserSecurityRepository;
import cn.com.jonpad.util.SecurityTool;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon75 on 2017/6/2.
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserRepository sur;
	@Autowired
	private SysUserSecurityRepository susr;
  @Autowired
  private SysUserRoleRepository surr;
  @Autowired
  private SysRoleService srs;

  public boolean hasUser(String key){
		return sur.findByEmailOrUsercode(key,key) == null ?false:true;
	}

	public SysUser findByEmailOrUsercode(String key){
		return   sur.findByEmailOrUsercode(key,key);
	}

	public long getUserCount(){
		return sur.count();
	}

	public Page<SysUser> getPage(int pageNo, int pageSize){
		PageRequest pageRequest = new PageRequest(pageNo-1,pageSize);
		return sur.findAll(pageRequest);
	}

	/**
	 * 保存用户
	 * @param user 用户基本消信息
	 * @param password 明文密码
	 */
	@Transactional
	public void addUser(SysUser user, String password) {
		// 原始密码
		String source = password;
		// 盐
		String salt = SecurityTool.getRandomString();
		// 散列次数，需要和shiro中的配置的凭证散列次数一致
		int hashIterations = 1;
		// 上边散列1次

		// 构造方法中：
		// 第一个参数：明文，原始密码
		// 第二个参数：盐，通过使用随机数
		// 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

		String password_md5 = md5Hash.toString();
		//user.setSecurity(security);
		sur.save(user);
		SysUserSecurity security = new SysUserSecurity();
		security.setPassword(password_md5);
		security.setSalt(salt);
		security.setSysUser(user);
		susr.save(security);
	}

	@Transactional
	public boolean addUser(SysUser user){

		SysUser dbUser = sur.findByEmailOrUsercode(user.getEmail(), user.getUsercode());
		if(dbUser != null){
			return false;
		}
		addUser(user,"88888888");
		return true;
	}

	@Transactional
	public boolean deleteUser(long id) {
		sur.delete(id);
		return true;
	}

	public boolean modifyUser(SysUser user) {
		SysUser oldUser = sur.findOne(user.getId());

		if (oldUser != null) {
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				oldUser.setEmail(user.getEmail());
			}
			oldUser.setLocked(user.getLocked());

			if (user.getUsercode() != null && !user.getUsercode().isEmpty()) {
				oldUser.setUsercode(user.getUsercode());
			}
			if (user.getInfo() != null && !user.getInfo().isEmpty()){
				oldUser.setInfo(user.getInfo());
			}
			if (user.getUsername() != null && !user.getUsername().isEmpty()) {
				oldUser.setUsername(user.getUsername());
			}
			if(user.getHead() != null && !user.getHead().isEmpty()){
				oldUser.setHead(user.getHead());
			}
			sur.saveAndFlush(oldUser);
			return true;
		}

		return false;
	}

	/**
	 * 权限加入后，要判断蓝光拥有管理员角色，不可进行次操作
	 * @param userId
	 * @return
	 */
	public boolean modifyUserState(long userId) {
		SysUser oldUser = sur.findOne(userId);
		if (oldUser != null) {
			if (oldUser.getLocked() == 1) {
				oldUser.setLocked(0);
			} else {
				oldUser.setLocked(1);
			}
			sur.saveAndFlush(oldUser);
			return true;
		}
		return false;
	}

	public List<SysUser> getAllUser() {
		return sur.findAll();
	}

  /**
   * 获取管理员对应列表
   * @return
   */
	public List<SysUserRole> getAdministration(){
    SysRole administratorRole = srs.getAdministratorRole();
    try{
      return  surr.findBySysRoleId(String.valueOf(administratorRole.getId()));
    }catch (Exception e){
      e.printStackTrace();
      return new ArrayList<>();
    }

  }

}
