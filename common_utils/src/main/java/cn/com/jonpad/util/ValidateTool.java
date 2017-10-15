package cn.com.jonpad.util;

/**
 * 数据验证工具
 * Created by jon75 on 2017/10/15.
 */
public class ValidateTool {
  /**
   * null 或 空字符串("")，返回true
   * @param str
   * @return
   */
  public static boolean isEmptyString(String str){
    if(str == null)
      return true;
    if("".equals(str.trim())){
      return true;
    }
    return false;
  }
}
