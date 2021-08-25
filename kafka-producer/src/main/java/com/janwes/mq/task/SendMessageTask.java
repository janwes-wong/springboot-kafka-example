package com.janwes.mq.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq.task
 * @date 2021/8/25 18:18
 * @description 定时任务发送消息
 */
@Slf4j
@Component
public class SendMessageTask {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 定时任务发送消息 每隔10s发送一次
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void sendMessage() {
        log.info(">>> send message task start....");
        kafkaTemplate.send("topic-test-110", "你好，我来自2050年...");
        log.info(">>> send message task finish....");
    }
}