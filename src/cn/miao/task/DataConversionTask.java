package cn.miao.task;

import com.miao.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Shaokang on 2015/5/26.
 */
@Component
public class DataConversionTask {

    private static final Logger logger = LoggerFactory.getLogger(DataConversionTask.class);

    @Scheduled(cron="0/5 * * * * ?")
    public void run(){
        logger.info("数据转换任务线程开始执行" + DateUtil.lFormat(new Date()));
    }
}
