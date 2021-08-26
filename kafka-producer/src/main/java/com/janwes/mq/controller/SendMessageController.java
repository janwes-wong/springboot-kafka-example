package com.janwes.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq.controller
 * @date 2021/8/26 14:42
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/msg")
public class SendMessageController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public static boolean flag = false;

    /**
     * 带回调方法的消息生产者
     *
     * @param msgContent 消息内容
     * @return
     */
    @GetMapping("/sendMsg/{msgContent}")
    public String sendMsg(@PathVariable("msgContent") String msgContent) {
        // kafkaTemplate提供了一个回调方法addCallback，可以在回调方法中监控消息是否发送成功 或 失败时做补偿处理
        kafkaTemplate.send("topic-test-120", msgContent).addCallback(new SuccessCallback<SendResult<String, String>>() {
            // 消息发送成功处理
            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                // 消息发送到的topic
                String topic = stringStringSendResult.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = stringStringSendResult.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = stringStringSendResult.getRecordMetadata().offset();
                log.info(">>> send message success,topic-{},partition-{},offset-{}", topic, partition, offset);
                flag = true;
            }
        }, new FailureCallback() {
            // 消息发送失败处理
            @Override
            public void onFailure(Throwable throwable) {
                log.info(">>> send message failure", throwable.getMessage());
            }
        });
        if (flag) {
            return "发送成功";
        }
        return "发送失败";
    }
}
