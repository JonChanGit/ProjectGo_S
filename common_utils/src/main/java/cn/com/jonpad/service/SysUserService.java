package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.entity.SysUserSecurity;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.repository.SysUserSecurityRepository;
import cn.com.jonpad.util.SecurityTool;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jon75 on 2017/6/2.
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserRepository sur;
	@Autowired
	private SysUserSecurityRepository susr;

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


}
