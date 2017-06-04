package cn.com.jonpad.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

/**
 * 提供加密有关的方法
 * @author jon75
 * @date 2016年7月10日 下午4:23:59
 */
public class SecurityTool {
	
	/**
	 * 盐中的字符串集合
	 * 盐仓库
	 */
	private static final String SALT_REPOSITORY = "qwertyuiopasdfghjklzxcvbnm0192837465MNBVCXZLKJHGFDSAPOIUYTREWQ ,.<>?;'[]{}|!@#$%^&*~`()_+-=";
	
	/**
	 * 获取一个随机的字符串
	 * @return
	 */
	public static String getRandomString(){
		
		Random random = new Random();
		
		StringBuilder salt = new StringBuilder("");
		
		//长度
		int saltLength = random.nextInt(15); 
		
		if(saltLength < 5) saltLength = 11;
		
		for(int i = 0; i < saltLength;i++){
			int x = random.nextInt(SALT_REPOSITORY.length());
			salt.append(SALT_REPOSITORY.substring(x, x+1));
		}
		
		return salt.toString();
	}
	
	
	/**
	 * Base64解码两次
	 * @param target 需要解码的目标
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	 public static String decodeBase64Twice(String target) throws UnsupportedEncodingException{
		byte[] asByte = Base64.getDecoder().decode(target);
		asByte = Base64.getDecoder().decode(asByte);
		String result = new String(asByte,"utf-8");
		System.out.println(result);
		return result;
	 }
	
	 
	 /**
	  * Base64编码两次
	  * @param target 需要编码的目标
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public static String encodeBase64Twice(String target) throws UnsupportedEncodingException{
		String encodeToString = Base64.getEncoder().encodeToString(target.getBytes("utf-8"));
		encodeToString = Base64.getEncoder().encodeToString((encodeToString).getBytes("utf-8"));
		System.out.println(encodeToString);
		return encodeToString;
	 }
}
