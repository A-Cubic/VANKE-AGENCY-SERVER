package com.cubic.api.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskServer {
	@Scheduled(fixedRate = 10000) //测试使用：每隔10秒执行一次
    // @Scheduled(cron = "0 0 6 * * ?") //正式使用：每天早6点执行一次
    public void doSharedPoolTask() {
        System.out.println("定时任务：" + System.currentTimeMillis());
    }
}
