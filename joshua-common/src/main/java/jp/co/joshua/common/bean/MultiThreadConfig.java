package jp.co.joshua.common.bean;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * マルチスレッド処理の設定Configクラス
 * 
 * @version 1.0.0
 */
@EnableAsync
@Configuration
public class MultiThreadConfig {

    @Bean("Thread1")
    public Executor taskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setThreadNamePrefix("Thread1--");
        executor.initialize();
        return executor;
    }

    @Bean("Default")
    public Executor defaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // デフォルトのThreadのサイズ。あふれるとQueueCapacityのサイズまでキューイングする
        executor.setCorePoolSize(5);
        // 待ちのキューのサイズ。あふれるとMaxPoolSizeまでThreadを増やす
        executor.setQueueCapacity(1);
        // どこまでThreadを増やすかの設定。この値からあふれるとその処理はリジェクトされてExceptionが発生する
        executor.setMaxPoolSize(500);
        executor.setThreadNamePrefix("Default--");
        executor.initialize();
        return executor;
    }

}
