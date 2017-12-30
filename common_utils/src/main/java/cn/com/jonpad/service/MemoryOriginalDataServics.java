package cn.com.jonpad.service;

import cn.com.jonpad.entity.MemoryData;
import cn.com.jonpad.entity.MemoryOriginalData;
import cn.com.jonpad.repository.MemoryDataRepository;
import cn.com.jonpad.repository.MemoryOriginalDataRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jon75
 * @date 2017/6/4
 */
@Service
public class MemoryOriginalDataServics {
  @Autowired
  private MemoryOriginalDataRepository modr;

  @Autowired
  private MemoryDataRepository mdr;

  @Transactional(rollbackFor = Exception.class)
  public boolean inserOriginalData(String content){
    MemoryOriginalData data = new MemoryOriginalData();
    data.setOriginalHtml(content);
    //保存当前毫秒
    data.setCreateDateTime(Instant.now().toEpochMilli());
    modr.save(data);
    return true;
  }

  /**
   * 解析数据
   * @param content
   */
  public void analysisData(String domId,MemoryOriginalData omd,String content){
    Document dom = Jsoup.parse(content);

    Element table = dom.getElementById(domId);
    Elements trs = table.getElementsByTag("tr");
    List<MemoryData> collect = trs.stream()
      .filter((e)->{
        return e.getElementsByClass("td_02").size()>0;
      })
      .map((e) -> {
        Elements tag_02 = e.getElementsByClass("td_02");
        Elements tag_03 = e.getElementsByClass("td_03");
        Element td_02 = tag_02.get(0);
        Element td_03 = tag_03.get(0);

        MemoryData md = new MemoryData();
        Elements e_content = td_02.getElementsByTag("a");
        if(e_content.size()>0){
          Element a_content = e_content.get(0);
          String _content = a_content.html();
          md.setContent(_content);
          String _link = a_content.attr("href");
          md.setLink(_link);
        }

        Elements e_mark = td_02.getElementsByTag("i");
        if(e_mark.size()>0){
          String _mark = e_mark.get(0).html();
          md.setMark(_mark);
        }

        Elements e_hots = td_03.getElementsByTag("span");
        if(e_hots.size()>0){
          String _hots = e_hots.get(0).html();
          md.setHots(_hots);
        }

        md.setOriginalData(omd);
        return md;
      }).collect(Collectors.toList());
    collect.forEach((m)->{
      System.out.println(m);
      System.out.println("=======================");
      mdr.save(m);
    });
  }


}
