package cn.com.jonpad.shiro.exception;

/**
 * 
 *
 * @Title: CustomException.java 
 * @Package com.jon.web.shiro.exception 
 * @Description: 系统自定义的异常类型，实际开发中可能要定义多种异常类型
 * @author JonChan
 * @date 2016年5月21日 下午4:16:28 
 * @version V1.0   
 *
 */
public class CustomException extends Exception {
	// 异常信息
	private String message;

	public CustomException(String message) {
		super(message);
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
