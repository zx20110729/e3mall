package cn.e3mall.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZX on 2017/7/27 14:40.
 */
public class ServiceServer {
    @Test
    public void test_server() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-*.xml");
        System.out.println("e3mall-manager-service启动.....");
        System.in.read();
        System.out.println("e3mall-manager-service结束.....");
    }
}
