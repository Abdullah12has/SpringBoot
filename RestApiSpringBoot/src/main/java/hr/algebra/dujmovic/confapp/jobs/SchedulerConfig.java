/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.jobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author matij
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail testJobDetail() {
        return JobBuilder.newJob(TestJob.class).withIdentity("testJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger testJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(testJobDetail())
                .withIdentity("testTrigger").withSchedule(scheduleBuilder).build();
    }
    
}
