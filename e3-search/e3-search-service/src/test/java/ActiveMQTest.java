import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.MessageListener;
import java.io.IOException;

/**
 * ActiveMQ单元测试类
 * Created by ZX on 2017/8/7 16:04.
 */
public class ActiveMQTest {
    @Test
    public void test_receive_message() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        System.in.read();
    }
}
