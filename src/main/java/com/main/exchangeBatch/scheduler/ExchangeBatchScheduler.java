package com.main.exchangeBatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExchangeBatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(cron = "0/10 * * * * *") // 10초
    public void runJob() throws Exception {
        JobParameters parameters = new JobParametersBuilder()
                .addString("jobName", "exchangeJob" + System.currentTimeMillis())
                .toJobParameters();
        // add parameters as needed
        jobLauncher.run(job, parameters);
    }
}
