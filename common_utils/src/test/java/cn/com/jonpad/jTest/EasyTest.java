package cn.com.jonpad.jTest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class EasyTest {
  @Test
  public void test01(){
    CloseableHttpClient httpClient = HttpClients.createDefault();

  }
}
