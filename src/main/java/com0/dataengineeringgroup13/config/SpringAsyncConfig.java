package com0.dataengineeringgroup13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static com0.dataengineeringgroup13.common.AppContanst.EXCEL_IMPORT_NUMBER_OF_THREAD;

@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean(name = "threadPoolExecutor")
    public Executor getThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(EXCEL_IMPORT_NUMBER_OF_THREAD);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("ExcelAsync-");
        executor.initialize();
        return executor;
    }
}
