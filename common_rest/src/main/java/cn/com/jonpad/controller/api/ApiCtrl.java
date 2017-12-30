package cn.com.jonpad.controller.api;

import cn.com.jonpad.service.MemoryOriginalDataServics;
import cn.com.jonpad.util.JsonTool;
import cn.com.jonpad.util.JsonTransportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jon75
 */
@RestController
@RequestMapping(value="/api_s")
public class ApiCtrl {

  @Autowired
  private MemoryOriginalDataServics service;

  @RequestMapping(value="/memoryOriginalData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
  public JsonTransportEntity memoryOriginalData(String content){
    service.inserOriginalData(content);
    return JsonTool.getJsonTransportEntity(true);
  }

}
