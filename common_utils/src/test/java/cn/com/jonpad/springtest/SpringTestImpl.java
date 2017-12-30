package cn.com.jonpad.springtest;

import cn.com.jonpad.entity.MemoryOriginalData;
import cn.com.jonpad.entity.SysRole;
import cn.com.jonpad.entity.SysUser;
import cn.com.jonpad.mybatis.SysUserDao;
import cn.com.jonpad.repository.MemoryOriginalDataRepository;
import cn.com.jonpad.repository.SysUserRepository;
import cn.com.jonpad.service.MemoryOriginalDataServics;
import cn.com.jonpad.service.SysRoleService;
import cn.com.jonpad.util.JsonTool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jon75 on 2017/11/11.
 */
public class SpringTestImpl extends SpringTest{

  @Autowired
  SysUserRepository sur;

  @Autowired
  SysUserDao sysUserDao;

  @Autowired
  SysRoleService srs;

  @Autowired
  MemoryOriginalDataServics memoryOriginalDataServics;
  @Autowired
  MemoryOriginalDataRepository memoryOriginalDataRepository;

  @Test
  public void loadTest(){
    System.out.printf("Spring装载测试");
  }

  /**
   * 测试JPA是否正常
   */
  @Test
  public void testJpa(){
    SysRole administratorRole = new SysRole();
    administratorRole.setAvailable(SysRole.AVAILABLE_TRUE);
    administratorRole.setName(SysRole.Super_Administrator_Name);
    srs.addRole(administratorRole);
  }

  @Test
  public void testJpa01(){
    SysRole administratorRole = new SysRole();
    administratorRole.setAvailable(SysRole.AVAILABLE_TRUE);
    administratorRole.setName("JPASAVETEST23");
    try {
      srs.addRole(administratorRole);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试MyBatis是否正常
   */
  @Test
  public void testMB(){
    SysUser u = new SysUser();
    u.setEmail("124");
    u.setUsername("234");
    sysUserDao.add(u);
  }

  @Test
  public void testAnalysisData(){

    MemoryOriginalData one = memoryOriginalDataRepository.findOne(7L);

    memoryOriginalDataServics.analysisData("realtimehot",one,"<table tab=\"realtimehot\" id=\"realtimehot\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"star_bank_table\">\n" +
      " <thead>\n" +
      "  <tr class=\"thead_tr\">\n" +
      "  <th class=\"th_01\">排名</th>\n" +
      "  <th class=\"th_02\">关键词</th>\n" +
      "  <th class=\"th_03\">搜索指数</th>\n" +
      "  <th class=\"th_04\">搜索热度</th>\n" +
      "  <th class=\"th_05\"></th>\n" +
      "  </tr>\n" +
      " </thead>\n" +
      "   <tbody><tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankntop\"><em>1</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E7%25A7%258B%25E7%2593%25B7%25E7%2582%25AB%2B%25E4%25BA%258E%25E6%2599%2593%25E5%2585%2589&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">秋瓷炫 于晓光</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>288139</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:100%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E7%25A7%258B%25E7%2593%25B7%25E7%2582%25AB%2B%25E4%25BA%258E%25E6%2599%2593%25E5%2585%2589&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankntop\"><em>2</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E8%25BF%25AA%25E4%25B8%25BD%25E7%2583%25AD%25E5%25B7%25B4%25E8%25B7%25B3%25E8%2588%259E&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">迪丽热巴跳舞</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>175511</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:61%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E8%25BF%25AA%25E4%25B8%25BD%25E7%2583%25AD%25E5%25B7%25B4%25E8%25B7%25B3%25E8%2588%259E&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankntop\"><em>3</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href_to=\"/weibo/%25E7%258E%258B%25E4%25BF%258A%25E5%2587%25AF%25E6%2596%25AF%25E6%25B2%2583%25E7%2590%25AA%25E5%2585%25A8%25E7%2590%2583%25E4%25BB%25A3%25E8%25A8%2580&amp;Refer=top\" href=\"javascript:void(0);\" action-data=\"ad_id=21504&amp;num=3&amp;type=&amp;cate=PC_realtime\" action-type=\"realtimehot_ad\" word=\"王俊凯斯沃琪全球代言\" url_show=\"\" url_click=\"\" suda-data=\"key=tblog_search_list&amp;value=ad_hotword_realtime_21504\">王俊凯斯沃琪全球代言</a>\n" +
      "   <i class=\"icon_txt icon_recommend\">荐</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>174075</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:61%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E7%258E%258B%25E4%25BF%258A%25E5%2587%25AF%25E6%2596%25AF%25E6%25B2%2583%25E7%2590%25AA%25E5%2585%25A8%25E7%2590%2583%25E4%25BB%25A3%25E8%25A8%2580&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>4</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/angelababy%25E5%2594%25B1%25E6%25AD%258C&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">angelababy唱歌</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>170383</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:60%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/angelababy%25E5%2594%25B1%25E6%25AD%258C&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>5</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/NASA%25E6%259C%2580%25E5%258F%2597%25E6%25AC%25A2%25E8%25BF%258E%25E7%259A%258410%25E5%25BC%25A0%25E7%2585%25A7%25E7%2589%2587&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">NASA最受欢迎的10张照片</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>169205</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:59%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/NASA%25E6%259C%2580%25E5%258F%2597%25E6%25AC%25A2%25E8%25BF%258E%25E7%259A%258410%25E5%25BC%25A0%25E7%2585%25A7%25E7%2589%2587&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>6</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%2591%258A%25E8%25AF%2589%25E8%2580%25B3%25E8%2581%258B%25E5%25A5%25B3%25E5%2584%25BF%2B%25E4%25BD%25A0%25E8%25A6%2581%25E5%25BD%2593%25E5%25A7%2590%25E5%25A7%2590%25E4%25BA%2586&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">告诉耳聋女儿 你要当姐姐...</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>169179</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:59%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%2591%258A%25E8%25AF%2589%25E8%2580%25B3%25E8%2581%258B%25E5%25A5%25B3%25E5%2584%25BF%2B%25E4%25BD%25A0%25E8%25A6%2581%25E5%25BD%2593%25E5%25A7%2590%25E5%25A7%2590%25E4%25BA%2586&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>7</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25B8%25AE%25E5%2595%2586%25E8%25B4%25A9%25E5%258D%2596%25E6%25A9%2598%25E5%25AD%2590%2B500%25E6%2596%25A41%25E5%25B0%258F%25E6%2597%25B6%25E5%258D%2596%25E5%2585%2589&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">帮商贩卖橘子 500斤1小时...</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>160227</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:56%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25B8%25AE%25E5%2595%2586%25E8%25B4%25A9%25E5%258D%2596%25E6%25A9%2598%25E5%25AD%2590%2B500%25E6%2596%25A41%25E5%25B0%258F%25E6%2597%25B6%25E5%258D%2596%25E5%2585%2589&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>8</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25A5%25B3%25E6%259C%258B%25E5%258F%258B%25E8%25AF%25B4%25E6%2588%2591%25E7%25BA%25BF%25E5%25A4%25B4%25E8%2590%25BD%25E5%25A5%25B9%25E5%25AE%25B6%25E4%25BA%2586&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">女朋友说我线头落她家了</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>140874</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:49%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25A5%25B3%25E6%259C%258B%25E5%258F%258B%25E8%25AF%25B4%25E6%2588%2591%25E7%25BA%25BF%25E5%25A4%25B4%25E8%2590%25BD%25E5%25A5%25B9%25E5%25AE%25B6%25E4%25BA%2586&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>9</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E4%25B8%258E%25E5%2590%258C%25E4%25BA%258B%25E9%25A5%25AE%25E9%2585%2592%25E5%2590%258E%25E6%25AD%25BB%25E4%25BA%25A1%2B%25E5%25AE%25B6%25E5%25B1%259E%25E7%25B4%25A2%25E8%25B5%2594&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">与同事饮酒后死亡 家属索...</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>118799</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:42%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E4%25B8%258E%25E5%2590%258C%25E4%25BA%258B%25E9%25A5%25AE%25E9%2585%2592%25E5%2590%258E%25E6%25AD%25BB%25E4%25BA%25A1%2B%25E5%25AE%25B6%25E5%25B1%259E%25E7%25B4%25A2%25E8%25B5%2594&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>10</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%2586%25AF%25E6%258F%2590%25E8%258E%25AB%2B%25E5%25BF%25AB%25E6%259C%25AC&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">冯提莫 快本</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>113571</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:40%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%2586%25AF%25E6%258F%2590%25E8%258E%25AB%2B%25E5%25BF%25AB%25E6%259C%25AC&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>11</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2599%2588%25E8%25B5%25AB%2B%25E7%25B2%25A4%25E8%25AF%25AD&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">陈赫 粤语</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>90939</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:32%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2599%2588%25E8%25B5%25AB%2B%25E7%25B2%25A4%25E8%25AF%25AD&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>12</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/gai%2B%25E6%2588%2591%25E8%25A6%2581%25E4%25B8%258A%25E6%2598%25A5%25E6%2599%259A&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">gai 我要上春晚</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>69919</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:25%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/gai%2B%25E6%2588%2591%25E8%25A6%2581%25E4%25B8%258A%25E6%2598%25A5%25E6%2599%259A&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>13</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%25A3%259E%25E6%259C%25BA%25E4%25B8%258A%25E6%2594%25B9%25E8%25AF%2595%25E5%258D%25B7%2B5%25E5%25B0%258F%25E6%2597%25B6210%25E4%25BB%25BD&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">飞机上改试卷 5小时210份</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>69527</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:25%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%25A3%259E%25E6%259C%25BA%25E4%25B8%258A%25E6%2594%25B9%25E8%25AF%2595%25E5%258D%25B7%2B5%25E5%25B0%258F%25E6%2597%25B6210%25E4%25BB%25BD&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>14</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/twins%25E5%2590%2588%25E4%25BD%2593&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">twins合体</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>67933</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:24%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/twins%25E5%2590%2588%25E4%25BD%2593&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>15</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%25B9%25BF%25E6%2599%2597%25E8%25B7%25A8%25E5%25B9%25B4&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">鹿晗跨年</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>64793</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:23%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%25B9%25BF%25E6%2599%2597%25E8%25B7%25A8%25E5%25B9%25B4&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>16</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/SHE%25E5%2590%2588%25E4%25BD%2593&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">SHE合体</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55739</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/SHE%25E5%2590%2588%25E4%25BD%2593&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>17</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%2581%258B%25E4%25B8%258E%25E5%2588%25B6%25E4%25BD%259C%25E4%25BA%25BA%25E8%25BF%259B%25E4%25B8%258D%25E5%258E%25BB&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">恋与制作人进不去</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55685</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%2581%258B%25E4%25B8%258E%25E5%2588%25B6%25E4%25BD%259C%25E4%25BA%25BA%25E8%25BF%259B%25E4%25B8%258D%25E5%258E%25BB&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>18</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E8%2591%25AC%25E7%2588%25B1%25E5%25AE%25B6%25E6%2597%258F%25E5%25BC%2580%25E5%25B9%25B4%25E4%25BC%259A&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">葬爱家族开年会</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55666</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E8%2591%25AC%25E7%2588%25B1%25E5%25AE%25B6%25E6%2597%258F%25E5%25BC%2580%25E5%25B9%25B4%25E4%25BC%259A&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>19</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E7%258E%258B%25E6%2580%259D%25E8%2581%25AA%25E7%259B%25B4%25E6%2592%25AD&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">王思聪直播</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55630</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E7%258E%258B%25E6%2580%259D%25E8%2581%25AA%25E7%259B%25B4%25E6%2592%25AD&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>20</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%2588%2591%25E5%25B0%25BD%25E5%258A%259B%25E4%25BA%2586%2B%25E6%2588%2591%25E6%25B2%25A1%25E7%258A%25AF%25E7%25BD%25AA&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">我尽力了 我没犯罪</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55614</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%2588%2591%25E5%25B0%25BD%25E5%258A%259B%25E4%25BA%2586%2B%25E6%2588%2591%25E6%25B2%25A1%25E7%258A%25AF%25E7%25BD%25AA&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>21</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25A4%25A9%25E4%25BD%2591&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">天佑</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55485</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25A4%25A9%25E4%25BD%2591&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>22</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25B9%2596%25E5%258D%2597%25E5%258D%25AB%25E8%25A7%2586%25E8%258A%2582%25E7%259B%25AE%25E5%258D%2595&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">湖南卫视节目单</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55386</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25B9%2596%25E5%258D%2597%25E5%258D%25AB%25E8%25A7%2586%25E8%258A%2582%25E7%259B%25AE%25E5%258D%2595&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>23</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25BC%2594%25E5%2591%2598%25E7%259A%2584%25E8%25AF%259E%25E7%2594%259F&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">演员的诞生</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55263</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25BC%2594%25E5%2591%2598%25E7%259A%2584%25E8%25AF%259E%25E7%2594%259F&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>24</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%2586%258D%25E8%25A7%2581%25E5%2589%258D%25E4%25BB%25BB%25E5%25BD%25B1%25E8%25AF%2584&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">再见前任影评</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>55225</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:20%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%2586%258D%25E8%25A7%2581%25E5%2589%258D%25E4%25BB%25BB%25E5%25BD%25B1%25E8%25AF%2584&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>25</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2598%25AE%25E7%25BB%258F%25E5%25A4%25A9%2B18%25E5%25B2%2581&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">阮经天 18岁</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>52336</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:19%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2598%25AE%25E7%25BB%258F%25E5%25A4%25A9%2B18%25E5%25B2%2581&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>26</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25AC%25A7%25E8%25B1%25AA%25E9%25A9%25AC%25E6%2580%259D%25E7%25BA%25AF%25E7%25A0%25B4%25E5%2588%2586%25E6%2589%258B%25E4%25BC%25A0%25E9%2597%25BB&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">欧豪马思纯破分手传闻</a>\n" +
      "   <i class=\"icon_txt icon_hot\">热</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>52091</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:19%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25AC%25A7%25E8%25B1%25AA%25E9%25A9%25AC%25E6%2580%259D%25E7%25BA%25AF%25E7%25A0%25B4%25E5%2588%2586%25E6%2589%258B%25E4%25BC%25A0%25E9%2597%25BB&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>27</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2599%2588%25E4%25BC%259F%25E9%259C%2586%25E5%2596%259D%25E9%2585%2592&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">陈伟霆喝酒</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>49947</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:18%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2599%2588%25E4%25BC%259F%25E9%259C%2586%25E5%2596%259D%25E9%2585%2592&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>28</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25BC%25A0%25E6%259D%25B0&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">张杰</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>44501</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:16%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25BC%25A0%25E6%259D%25B0&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>29</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E8%259B%2587%25E5%2593%25A5%2B%25E8%2587%25AA%25E8%25AF%2581&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">蛇哥 自证</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>43998</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:16%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E8%259B%2587%25E5%2593%25A5%2B%25E8%2587%25AA%25E8%25AF%2581&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>30</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%259D%258E%25E6%2599%25A8%25E6%2599%2592%25E8%25B7%2591%25E7%2594%25B7%25E5%259B%25A2%25E5%2590%2588%25E7%2585%25A7&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">李晨晒跑男团合照</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>43933</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:16%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%259D%258E%25E6%2599%25A8%25E6%2599%2592%25E8%25B7%2591%25E7%2594%25B7%25E5%259B%25A2%25E5%2590%2588%25E7%2585%25A7&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>31</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%2596%259C%25E6%25AC%25A2%25E6%2598%25AF%25E8%2597%258F%25E4%25B8%258D%25E4%25BD%258F%25E7%259A%2584&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">喜欢是藏不住的</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>40344</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:15%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%2596%259C%25E6%25AC%25A2%25E6%2598%25AF%25E8%2597%258F%25E4%25B8%258D%25E4%25BD%258F%25E7%259A%2584&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>32</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25BF%25AB%25E4%25B9%2590%25E5%25A4%25A7%25E6%259C%25AC%25E8%2590%25A5&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">快乐大本营</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>36394</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:13%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25BF%25AB%25E4%25B9%2590%25E5%25A4%25A7%25E6%259C%25AC%25E8%2590%25A5&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>33</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2582%2593%25E7%25B4%25AB%25E6%25A3%258B%2B%25E5%25A3%25B0%25E9%259F%25B3&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">邓紫棋 声音</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>36085</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:13%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2582%2593%25E7%25B4%25AB%25E6%25A3%258B%2B%25E5%25A3%25B0%25E9%259F%25B3&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>34</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25BE%25AE%25E4%25BF%25A1%25E8%25B7%25B3%25E4%25B8%2580%25E8%25B7%25B3&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">微信跳一跳</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>35482</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:13%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25BE%25AE%25E4%25BF%25A1%25E8%25B7%25B3%25E4%25B8%2580%25E8%25B7%25B3&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>35</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25A5%25B3%25E5%25A4%25A7%25E5%25AD%25A6%25E7%2594%259F%25E5%25AE%25BF%25E8%2588%258D%25E5%25BC%2580%25E7%25BE%258E%25E7%2594%25B2%25E5%25BA%2597&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">女大学生宿舍开美甲店</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>33868</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:12%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25A5%25B3%25E5%25A4%25A7%25E5%25AD%25A6%25E7%2594%259F%25E5%25AE%25BF%25E8%2588%258D%25E5%25BC%2580%25E7%25BE%258E%25E7%2594%25B2%25E5%25BA%2597&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>36</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E7%258E%258B%25E4%25BF%258A%25E5%2587%25AF%2B%25E9%259D%2592%25E8%2597%258F%25E9%25AB%2598%25E5%258E%259F&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">王俊凯 青藏高原</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>33697</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:12%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E7%258E%258B%25E4%25BF%258A%25E5%2587%25AF%2B%25E9%259D%2592%25E8%2597%258F%25E9%25AB%2598%25E5%258E%259F&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>37</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25B1%259F%25E8%258B%258F%25E5%258D%25AB%25E8%25A7%2586%25E8%25B7%25A8%25E5%25B9%25B4%25E6%25BC%2594%25E5%2594%25B1%25E4%25BC%259A&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">江苏卫视跨年演唱会</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>32062</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:12%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25B1%259F%25E8%258B%258F%25E5%258D%25AB%25E8%25A7%2586%25E8%25B7%25A8%25E5%25B9%25B4%25E6%25BC%2594%25E5%2594%25B1%25E4%25BC%259A&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>38</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E4%25B8%25AD%25E5%259B%25BD%25E4%25BA%25BA%25E4%25B8%25BA%25E4%25BB%2580%25E4%25B9%2588%25E8%25B6%258A%25E4%25B9%25B0%25E8%25B6%258A%25E8%25B4%25B5&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">中国人为什么越买越贵</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>29843</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:11%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E4%25B8%25AD%25E5%259B%25BD%25E4%25BA%25BA%25E4%25B8%25BA%25E4%25BB%2580%25E4%25B9%2588%25E8%25B6%258A%25E4%25B9%25B0%25E8%25B6%258A%25E8%25B4%25B5&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>39</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25A2%2581%25E9%259D%2599%25E8%258C%25B9%2B%25E5%258B%2587%25E6%25B0%2594&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">梁静茹 勇气</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>29720</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:11%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25A2%2581%25E9%259D%2599%25E8%258C%25B9%2B%25E5%258B%2587%25E6%25B0%2594&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>40</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E7%2594%25B7%25E5%25AD%2590%25E9%2585%2592%25E9%25A9%25BE%25E5%258E%25BB%25E7%25A6%25BB%25E5%25A9%259A%2B%25E5%25B4%25A9%25E6%25BA%2583%25E5%25A4%25A7%25E5%2593%25AD&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">男子酒驾去离婚 崩溃大哭</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>29645</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:11%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E7%2594%25B7%25E5%25AD%2590%25E9%2585%2592%25E9%25A9%25BE%25E5%258E%25BB%25E7%25A6%25BB%25E5%25A9%259A%2B%25E5%25B4%25A9%25E6%25BA%2583%25E5%25A4%25A7%25E5%2593%25AD&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>41</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2580%2582%25E5%2590%2588%25E9%2580%2581%25E5%25A5%25B3%25E6%259C%258B%25E5%258F%258B%25E7%259A%2584%25E5%258F%25A3%25E7%25BA%25A2%25E5%2590%2588%25E9%259B%2586&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">适合送女朋友的口红合集</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>27587</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:10%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2580%2582%25E5%2590%2588%25E9%2580%2581%25E5%25A5%25B3%25E6%259C%258B%25E5%258F%258B%25E7%259A%2584%25E5%258F%25A3%25E7%25BA%25A2%25E5%2590%2588%25E9%259B%2586&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>42</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2583%2591%25E6%2581%25BA%25E5%2594%25B1%25E6%25AD%258C&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">郑恺唱歌</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>27166</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:10%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2583%2591%25E6%2581%25BA%25E5%2594%25B1%25E6%25AD%258C&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>43</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E8%2594%25A1%25E4%25BE%259D%25E6%259E%2597%2B%25E6%25B5%2599%25E6%25B1%259F%25E5%258D%25AB%25E8%25A7%2586&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">蔡依林 浙江卫视</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>26909</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:10%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E8%2594%25A1%25E4%25BE%259D%25E6%259E%2597%2B%25E6%25B5%2599%25E6%25B1%259F%25E5%258D%25AB%25E8%25A7%2586&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>44</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E8%2591%25A3%25E5%25AD%2590%25E5%2581%25A5&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">董子健</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>26825</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:10%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E8%2591%25A3%25E5%25AD%2590%25E5%2581%25A5&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>45</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%259F%25A9%25E6%25B0%2591%25E4%25BC%2597%25E9%2580%2581%25E5%259B%259A%25E8%25A1%25A3%25E7%25BB%2599%25E6%259D%258E%25E6%2598%258E%25E5%258D%259A&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">韩民众送囚衣给李明博</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>26516</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:10%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%259F%25A9%25E6%25B0%2591%25E4%25BC%2597%25E9%2580%2581%25E5%259B%259A%25E8%25A1%25A3%25E7%25BB%2599%25E6%259D%258E%25E6%2598%258E%25E5%258D%259A&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>46</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%25B4%25BE%25E5%2587%25BA%25E6%2589%2580%25E5%258F%25AF%25E4%25BB%25A5%25E5%25BC%2580%25E5%2593%25AA%25E4%25BA%259B%25E8%25AF%2581%25E6%2598%258E&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">派出所可以开哪些证明</a>\n" +
      "   <i class=\"icon_txt icon_new\">新</i></p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>25752</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:9%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%25B4%25BE%25E5%2587%25BA%25E6%2589%2580%25E5%258F%25AF%25E4%25BB%25A5%25E5%25BC%2580%25E5%2593%25AA%25E4%25BA%259B%25E8%25AF%2581%25E6%2598%258E&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>47</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25A6%2596%25E7%258C%25AB%25E4%25BC%25A0&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">妖猫传</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>25044</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:9%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25A6%2596%25E7%258C%25AB%25E4%25BC%25A0&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>48</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E5%25BA%25BE%25E6%25BE%2584%25E5%25BA%2586%25E6%259D%258E%25E6%2599%25A8%2B%25E6%2598%25A5%25E6%25B3%25A5&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">庾澄庆李晨 春泥</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>24935</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:9%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E5%25BA%25BE%25E6%25BE%2584%25E5%25BA%2586%25E6%259D%258E%25E6%2599%25A8%2B%25E6%2598%25A5%25E6%25B3%25A5&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>49</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E9%2582%2593%25E8%25B6%2585&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">邓超</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>23716</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:9%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E9%2582%2593%25E8%25B6%2585&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   <tr action-type=\"hover\">\n" +
      "   <td class=\"td_01\"><span class=\"search_icon_rankn\"><em>50</em></span></td>\n" +
      "   <td class=\"td_02\"><div class=\"rank_content\"><p class=\"star_name\">\n" +
      "   <a href=\"/weibo/%25E6%259C%258B%25E5%258F%258B%25E5%259C%2588%2B%25E5%258D%2581%25E5%2585%25AB%25E5%25B2%2581%25E7%2585%25A7%25E7%2589%2587&amp;Refer=top\" target=\"_blank\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\">朋友圈 十八岁照片</a>\n" +
      "   </p></div></td>\n" +
      "   <td class=\"td_03\"><p class=\"star_num\"><span>22719</span></p></td>\n" +
      "   <td class=\"td_04\"><p class=\"rank_long\"><span class=\"long_con\" style=\"width:8%\"></span></p></td>\n" +
      "   <td class=\"td_05\"><a href=\"/weibo/%25E6%259C%258B%25E5%258F%258B%25E5%259C%2588%2B%25E5%258D%2581%25E5%2585%25AB%25E5%25B2%2581%25E7%2585%25A7%25E7%2589%2587&amp;Refer=top\" target=\"_blank\" class=\"search_icon icon_search\" suda-data=\"key=tblog_search_list&amp;value=list_realtimehot\"></a></td>\n" +
      "  </tr>\n" +
      "   </tbody></table>");
  }


}
