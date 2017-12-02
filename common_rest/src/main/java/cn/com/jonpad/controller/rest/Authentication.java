package cn.com.jonpad.controller.rest;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * REST 认证
 * @author chenjunlai
 * @date 2017/12/1
 */
@RestController
@RequestMapping(value="/api")
public class Authentication {
  /**
   *
   * @param loginKey 用户名
   * @param loginToken 密码
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value="/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JSONObject login(String loginKey,String loginToken,HttpServletRequest request)throws Exception{
    JSONObject jo = new JSONObject();
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(loginKey, loginToken);
    try {
      subject.login(token);
      jo.put("flag",true);
      return jo;
    } catch (AuthenticationException e) {
      jo.put("flag",false);
      jo.put("message",e.getMessage());
      return jo;
    }
  }
}
