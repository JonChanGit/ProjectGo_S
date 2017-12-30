package cn.com.jonpad.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class JsonTool {

	public static <W> W parseObject(String json, Class<W> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public static String toFastjson(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
	}

	public static SerializeConfig dataFormatMapping = new SerializeConfig();

	static {
		dataFormatMapping.put(Date.class, new SimpleDateFormatSerializer("yyy-MM-dd HH:mm:ss"));
	}


	/**
	 * 传输  Boolean
	 *
	 * @param flag
	 * @param response
	 * @throws IOException
	 */
	public static void toJson(Boolean flag, HttpServletResponse response) throws IOException {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setFlag(flag);
		toJson(jsonTransportEntity, response);
	}

	/**
	 * 传输 message
	 *
	 * @param message
	 * @param response
	 * @throws IOException
	 */
	public static void toJson(String message, HttpServletResponse response) throws IOException {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setMessage(message);
		toJson(jsonTransportEntity, response);
	}

	/**
	 * 传输  message 和 标记
	 *
	 * @param flag
	 * @param message
	 * @param response
	 * @throws IOException
	 */
	public static void toJson(Boolean flag, String message, HttpServletResponse response) throws IOException {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setMessage(message);
		jsonTransportEntity.setFlag(flag);
		toJson(jsonTransportEntity, response);
	}
	public static JsonTransportEntity getJsonTransportEntity(Boolean flag, String message) {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setMessage(message);
		jsonTransportEntity.setFlag(flag);
		return jsonTransportEntity;
	}
	public static JsonTransportEntity getJsonTransportEntity(Boolean flag) {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setFlag(flag);
		return jsonTransportEntity;
	}

	/**
	 * 传输 集合(list)对象实体
	 *
	 * @param list     对象集合
	 * @param response
	 * @throws IOException
	 */
	public static void toJson(List list, HttpServletResponse response) throws IOException {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setEntity(list);
		toJson(jsonTransportEntity, response);
	}
	public static JsonTransportEntity getJsonTransportEntity(List list, boolean flag) {
		JsonTransportEntity jsonTransportEntity = new JsonTransportEntity();
		jsonTransportEntity.setList(list);
		jsonTransportEntity.setFlag(flag);
		return jsonTransportEntity;
	}

	/**
	 * 传输一个 Json传输对象 到客户端中
	 *
	 * @param obj
	 * @throws IOException
	 */
	public static void toJson(JsonTransportEntity obj, HttpServletResponse response) throws IOException {
		String result = toFastjson(obj);
		toClient(result, response);
	}

	public static void toJson(Map<String, Object> model, HttpServletResponse response) throws IOException {
		String result = toFastjson(model);
		toClient(result, response);
	}

	public static void toClient(String result, HttpServletResponse response) throws IOException {
		//这个是设置响应给客户端的文档格式
		response.setContentType("application/json;charset=UTF-8");
		//这个是消除客户端的缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		out.print(result);
		out.flush();
		out.close();
	}


}
