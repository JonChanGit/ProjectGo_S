package cn.com.jonpad.entity;

import javax.persistence.*;

/**
 * 记忆
 * 获取到的原始数据
 * @author jon75
 */
@Entity
@Table(name = "m_original_data")
public class MemoryOriginalData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="p_i_id")
  private long id;
  /**
   * 计算机时间戳
   */
  @Column(name="l_create_date_time")
  private long createDateTime;

  /**
   * 读取到的原始HTML
   */
  @Column(name="c_original_html")
  @Lob
  private String originalHtml;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCreateDateTime() {
    return createDateTime;
  }

  public void setCreateDateTime(long createDateTime) {
    this.createDateTime = createDateTime;
  }

  public String getOriginalHtml() {
    return originalHtml;
  }

  public void setOriginalHtml(String originalHtml) {
    this.originalHtml = originalHtml;
  }
}
