package com.main.exchangeBatch.batch;

import com.main.exchangeBatch.utils.ExchangeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest
public class ExchangeBatchTest {

    @Autowired
    private Job job;

    @Autowired
    private ExchangeUtils exchangeUtils;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void testExchangeJob() throws Exception {
        // 배치 작업이 성공적으로 완료되었는지 확인
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        // 완료가 되었는지 상태 코드 확인
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}