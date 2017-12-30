package cn.com.jonpad.entity;

import javax.persistence.*;

/**
 * 记忆
 * 解析后的数据
 * @author jon75
 */
@Entity
@Table(name = "m_data")
public class MemoryData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="p_i_id")
  private long id;

  /**
   * 指向原始记录
   */
  @JoinColumn(name = "f_i_original_data_id")
  @ManyToOne
  private MemoryOriginalData originalData;

  /**
   * 热度正文
   */
  @Column(name="c_content")
  private String content;

  /**
   * 点击链接
   */
  @Column(name="c_link")
  private String link;

  /**
   * 热度值
   */
  @Column(name="c_hots")
  private String hots;
  /**
   * 数据源标记
   */
  @Column(name="c_mark")
  private String mark;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public MemoryOriginalData getOriginalData() {
    return originalData;
  }

  public void setOriginalData(MemoryOriginalData originalData) {
    this.originalData = originalData;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getHots() {
    return hots;
  }

  public void setHots(String hots) {
    this.hots = hots;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  @Override
  public String toString() {
    return "MemoryData{" +
      "id=" + id +
      ", originalData=" + originalData +
      ", content='" + content + '\'' +
      ", link='" + link + '\'' +
      ", hots='" + hots + '\'' +
      ", mark='" + mark + '\'' +
      '}';
  }
}
