package cn.miao.amq.push;

import cn.miao.amq.SubScriberUtils;
import com.miao.util.uuid.UUIDUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by Miaosk
 * @date Created on 2015/6/16.
 */
public class PushMessage {
    public static void main(String[] args){
        Map message = new HashMap();
        message.put("message_id", UUIDUtils.base58Uuid());
        message.put("message_context", "这是一条需要amq处理的消息");
        SubScriberUtils.sendMessage(message, "miao.topic.test.message");
    }
}
