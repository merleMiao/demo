package cn.miao.amq;


import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author created by Miaosk
 * @date created on 2015/5/25 14:31.
 */
public abstract class AbsHandler {

    public AbsHandler() {
    }

    public abstract boolean process(Message message) throws JMSException;
}
