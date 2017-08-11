package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 自定义消息监听器
 * Created by ZX on 2017/8/7 15:58.
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println(123+text);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
