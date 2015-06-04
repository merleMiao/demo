package cn.miao.code.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 * 切面
 */
public class ServiceAspect {

    private static Logger logger = LoggerFactory.getLogger(cn.miao.code.aop.ServiceAspect.class);

    public void doBefore(JoinPoint jp) {
        System.out.println("=============================================log Begining method: "
                + jp.getTarget().getClass().getName() + "."
                + jp.getSignature().getName());
    }

    public void doAfter(JoinPoint jp) {
        System.out.println("=============================================log Ending method: "
                + jp.getTarget().getClass().getName() + "."
                + jp.getSignature().getName());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        String method = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();
        logger.info("ServiceAspect\ttask\t" + method + "\t" + time);
        return retVal;
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("=============================================method " + jp.getTarget().getClass().getName()
                + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }
}
