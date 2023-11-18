package com.main.exchangeBatch.batch;

import com.main.exchangeBatch.dto.ExchangeDto;
import com.main.exchangeBatch.utils.ExchangeUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Configuration
public class ExchangeBatch {
    @Autowired
    private ExchangeUtils exchangeUtils;

    @Autowired
    @Bean
    public Job exchangeJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("exchangeJob", jobRepository)
                .start(step)
                .build();
    }
    @Bean
    public Step step(JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet, platformTransactionManager).build();
    }
    @Bean

    public Tasklet tasklet(){
        return ((contribution, chunkContext) -> {
            List<ExchangeDto> exchangeDtoList = exchangeUtils.getExchangeDataAsDtoList();

            for (ExchangeDto exchangeDto : exchangeDtoList) {
                System.out.println("Currency: " + exchangeDto.getCur_nm());
                System.out.println("Rate: " + exchangeDto.getTts());
                // 추가적인 필드가 있다면 출력 또는 활용
            }
            return RepeatStatus.FINISHED;
        });
    }
}


