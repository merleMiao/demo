package cn.miao.task;


import cn.miao.task.pojo.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shaokang on 2015/5/26.
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
    }





}
