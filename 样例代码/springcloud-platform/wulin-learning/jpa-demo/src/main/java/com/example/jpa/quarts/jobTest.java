package com.example.jpa.quarts;

import org.quartz.SchedulerException;

/**
 * @author 武林
 * @date 2020/7/14 14:00
 */
public class jobTest {

    public static void main(String[] args) {
        try {
            QuartzUtil.addJob("job1", "trigger1", QuartzJob.class, 2);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
