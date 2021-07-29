package com.pss.premierservicesolutions.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@Deprecated
public class Scheduler {
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduledTask(){
        //System.out.println("Scheduler test");
    }
}
