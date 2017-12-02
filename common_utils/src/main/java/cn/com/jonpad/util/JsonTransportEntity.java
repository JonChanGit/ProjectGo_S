package cn.com.jonpad.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jon75 on 2017/8/27.
 */

public class JsonTransportEntity<T,E> {
	/**
	 * 消息标记
	 * 标记动作是否成功
	 */
	private Boolean flag;

	/**
	 * 总个数
	 */
	private Long totalSize;

	/**
	 * 总页数
	 */
	private Long totalPage;


	/**
	 *
	 */
	private Integer currPage;

	/**
	 * 消息
	 */
	private String message ;

	/**
	 * 消息代码（需要时）
	 */
	private String code;

	/**
	 * 操作URL（需要时）
	 */
	private String url;

	/**
	 * 传输记录(需要时)
	 */
	private List<T> list;

	/**
	 * 单个对象实体
	 */
	private E entity;

	public Boolean getFlag() {
		return flag;
	}

	public JsonTransportEntity setFlag(Boolean flag) {
		this.flag = flag;
		return this;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}


	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String getMessage() {
		return message;
	}

	public JsonTransportEntity setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}


	public static JsonTransportEntity getInstance(Page page){
    JsonTransportEntity jte = new JsonTransportEntity();
    jte.setFlag(true);
    jte.setList(page.getContent());
    jte.setTotalSize(page.getTotalElements());
    jte.setTotalPage(Long.valueOf(page.getTotalPages()));
    return jte;
  }


}

