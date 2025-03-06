package services.notificationDao;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {
    public static void startQuartzScheduler() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // Define the job and tie it to the MatchCheckJob class
            JobDetail job = JobBuilder.newJob(MatchCheckJob.class)
                    .withIdentity("matchCheckJob", "group1")
                    .build();

            // Trigger the job every 4 hours
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("matchCheckTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInHours(4)
                            .repeatForever())
                    .build();

            // Schedule the job
            scheduler.scheduleJob(job, trigger);

            System.out.println("✅ Quartz Scheduler started. Job will run every 4 hours.");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
