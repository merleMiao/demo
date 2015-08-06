package cn.miao.amq;


import cn.miao.amq.handler.ReceiveHandler;
import com.google.common.io.Closer;
import com.miao.util.basic.Config;
import com.miao.util.http.MObjectMapper;
import com.miao.util.json.JsonUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.net.InetAddress;

/**
 * @author created by Miaosk
 * @date created on 2015/4/28 14:31.
 * amp处理类
 */
public class SubScriberUtils {

    private static Logger logger = LoggerFactory.getLogger(SubScriberUtils.class);

    private static String url = "";

    static {
        try {
            String _url = Config.getLocalProperty("amq.host");
            if (StringUtils.isNotBlank(_url)) {
                url = _url;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTopicListener(String topicName, boolean persistent, final AbsHandler absHandler) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = activeMQConnectionFactory.createConnection();
            String hostName = getHostName();
            String clientId = topicName + "_handler." + hostName;
            connection.setClientID(clientId);
            connection.start();
            Session session = connection.createSession(false, 1);
            Topic topic = session.createTopic(topicName);
            MessageConsumer messageConsumer = null;
            if (persistent) {
                messageConsumer = session.createConsumer(topic);
            } else {
                messageConsumer = session.createDurableSubscriber(topic, hostName);
            }
            messageConsumer.setMessageListener(new MessageListener() {

                boolean result = false;

                @Override
                public void onMessage(Message message) {
                    String msg = "";
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        msg = textMessage.getText();
                        result = absHandler.process(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Object object, String topicName) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
        Closer closer = Closer.create();
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            closer.register(closer);
            connection.start();
            Session session = connection.createSession(false, 1);
            Topic topic = session.createTopic(topicName);
            MessageProducer messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(2);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(JsonUtils.toJson(object, new MObjectMapper()));
            messageProducer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("sendMessage" + JsonUtils.toJson(object, new MObjectMapper()));
            try {
                closer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getHostName() {
        String name = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            name = inetAddress.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public static void main(String args[]) {
        addTopicListener("miao.topic.test.message", true, new ReceiveHandler());
    }

}
