package cn.e3mall.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by ZX on 2017/8/2 17:05.
 */
public class ActiveMqTest {
    /**
     * 点到点发送消息
     */
    @Test
    public void test_queue_producer() throws JMSException {
        //创建工厂对象,指定消息服务器ip
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建一个链接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        // 创建session对象,参数1:是否开启事物,参数2:应答模式(自动/手动)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建Destination对象
        Queue queue = session.createQueue("test-queue");
        //创建Producer对象
        MessageProducer producer = session.createProducer(queue);
        //创建Message对象
        TextMessage message = new ActiveMQTextMessage();
        message.setText("哈哈哈!!!");
        //发送消息
        producer.send(message);

        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 点到点接收消息
     */
    @Test
    public void test_queue_consumer() throws JMSException, IOException {
        //创建工厂对象,指定消息服务器ip
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建一个链接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("spring-queue");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage text = (TextMessage) message;
                    System.out.println(text.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 广播生产者
     * @throws JMSException
     */
    @Test
    public void test_topic_Producer() throws JMSException {
        //创建工厂对象,指定消息服务器ip
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建一个链接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        // 创建session对象,参数1:是否开启事物,参数2:应答模式(自动/手动)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建Destination对象
        Topic topic = session.createTopic("test-topic");
        //创建Producer对象
        MessageProducer producer = session.createProducer(topic);
        //创建Message对象
        TextMessage message = new ActiveMQTextMessage();
        message.setText("哈哈哈!!!广播啦!!!");
        //发送消息
        producer.send(message);

        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 广播消费者
     * @throws JMSException
     * @throws IOException
     */
    @Test
    public void test_topic_consumer() throws JMSException, IOException {
        //创建工厂对象,指定消息服务器ip
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //创建一个链接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage text = (TextMessage) message;
                    System.out.println(text.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 测试提供spring配置 发送消息
     */
    @Test
    public void test_spring_activemq_send_message(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        Destination destination= (Destination) context.getBean("activeMQQueue");

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("测试提供spring配置 发送消息:哈哈哈!!!");
            }
        });
    }
}
