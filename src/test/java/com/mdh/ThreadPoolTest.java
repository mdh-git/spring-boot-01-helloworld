package com.mdh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * Spring线程池ThreadPoolTaskExecutor
 * @Author: madonghao
 * @Date: 2019/3/1 14:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {

    ThreadPoolTaskExecutor executor = null;

    @Test
    public void executorTest() {
        executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(5);
        //配置队列大小
        executor.setQueueCapacity(100);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        List<Future<Object>> futureList = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            futureList.add(executor.submit(new Callable<Object>() {

                @Override
                public Object call() throws Exception{

                    for(int j = 0; j <6; j++){
                        if(j % 5 == 0){
                            Thread.sleep(6000);
                            return "超时";
                        }

                    }
                    return "成功";
                }
            }));

        }

        for(Future<Object> future : futureList){
            try {
                System.out.println("future:" + future.get());
            } catch (Exception e) {
            }
        }
    }
}
