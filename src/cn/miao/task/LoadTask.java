package cn.miao.task;

import cn.miao.task.pojo.ScheduleJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shaokang on 2015/5/27.
 */
public class LoadTask {

    private static List<ScheduleJob> jobMap = new ArrayList<ScheduleJob>();

    static {
        for(int i = 0; i < 5; i ++){
            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setJobId("1000" + 1);
            scheduleJob.setJobName("data_import" + i);
            scheduleJob.setJobGroup("dataWork");
            scheduleJob.setJobStatus("1");
            scheduleJob.setCronExpression("0/5 * * * * ?");
            scheduleJob.setDesc("数据导入任务");
            jobMap.add(scheduleJob);
        }
    }

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

//    public static void addJob(ScheduleJob scheduleJob){
//        jobMap.add, scheduleJob);
//    }

    public void initTask() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        for(ScheduleJob scheduleJob : jobMap){
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(null == cronTrigger){
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
                cronTrigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            }
        }
    }


}
