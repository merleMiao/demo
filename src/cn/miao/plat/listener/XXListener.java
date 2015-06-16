package cn.miao.plat.listener;

import cn.miao.amq.SubScriberUtils;
import cn.miao.amq.handler.ReceiveHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class XXListener extends ContextLoaderListener {

    public static WebApplicationContext ac;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        System.out.println("===========================XXListener start===============================");
        ac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        //订阅评论事件
        System.out.println("===============================create applicationContext=================================");
        SubScriberUtils.addTopicListener("miao.topic.test.message", true, new ReceiveHandler());
        System.out.println("===========================XXListener end===============================");
    }
}
