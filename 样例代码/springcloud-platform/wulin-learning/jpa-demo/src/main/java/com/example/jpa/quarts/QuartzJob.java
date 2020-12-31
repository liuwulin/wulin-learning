package com.example.jpa.quarts;

/**
 * @author 武林
 * @date 2020/7/14 13:52
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJob implements Job {

    private Logger log = LoggerFactory.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        log.info("我也不知道这个啥时候执行，反正这个是Job 的实现类");
    }

}