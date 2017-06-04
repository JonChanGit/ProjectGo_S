package cn.com.jonpad.service;

import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.util.SecurityTool;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jon75 on 2017/6/2.
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserRepository sur;

	public boolean hasUser(String key){
		return sur.findByEmailOrUsercode(key,key) == null ?false:true;
	}

	public SysUser findByEmailOrUsercode(String key){
		return   sur.findByEmailOrUsercode(key,key);
	}

	public long getUserCount(){
		return sur.count();
	}


	/**
	 * 保存用户
	 * @param user 用户基本消信息
	 * @param password 明文密码
	 */
	public void addUser(SysUser user, String password) {
		// 原始密码
		String source = password;
		// 盐
		String salt = SecurityTool.getRandomString();
		// 散列次数
		int hashIterations = 1;
		// 上边散列1次

		// 构造方法中：
		// 第一个参数：明文，原始密码
		// 第二个参数：盐，通过使用随机数
		// 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

		String password_md5 = md5Hash.toString();
		user.setPassword(password_md5);
		user.setSalt(salt);
		sur.save(user);
	}


}
