package cn.miao.amq.handler;

import cn.miao.amq.AbsHandler;
import com.miao.util.http.MObjectMapper;
import com.miao.util.json.JsonUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/16.
 */
public class ReceiveHandler extends AbsHandler {
    @Override
    public boolean process(Message message) throws JMSException {
        TextMessage tm = (TextMessage) message;
        Map map = JsonUtils.toBean(tm.getText(), Map.class);
        System.out.println(JsonUtils.toJson(map, new MObjectMapper()));
        return false;
    }
}
