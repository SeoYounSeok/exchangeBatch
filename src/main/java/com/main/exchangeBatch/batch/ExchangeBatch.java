package com.main.exchangeBatch.batch;

import com.main.exchangeBatch.dto.ExchangeDTO;
import com.main.exchangeBatch.utils.ExchangeUtils;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
public class ExchangeBatch {
    @Autowired
    private ExchangeUtils exchangeUtils;

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
            List<ExchangeDTO> exchangeDTOList = exchangeUtils.getExchangeDataAsDtoList();

            for (ExchangeDTO exchangeDto : exchangeDTOList) {
                log.info("통화 : " + exchangeDto.getCur_nm());
                log.info("환율 : " + exchangeDto.getDeal_bas_r());
                // 추가적인 필드가 있다면 출력 또는 활용
            }
            return RepeatStatus.FINISHED;
        });
    }
}


