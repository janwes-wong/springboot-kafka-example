package com.janwes.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq.consumer
 * @date 2021/8/25 15:38
 * @description 消息监听
 */
@Slf4j
@Component
public class MessageListener {

    @KafkaListener(topics = "topic-test-110")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        if (Objects.nonNull(record)) {
            // 消息内容
            String message = record.value();
            // 消息所在的分区
            int partition = record.partition();
            log.info(">>> receive message:{},message partition:{}", message, partition);
        }
    }

    @KafkaListener(topics = "topic-test-120")
    @SendTo("topic-test-130") // 消息转发到topic-test-130
    public String receiveMsg(ConsumerRecord<String, String> record) {
        String message = null;
        if (Objects.nonNull(record)) {
            // 消息内容
            message = record.value();
            // 消息所在的分区
            int partition = record.partition();
            log.info(">>> receive message:{},message partition:{}", message, partition);
        }
        return message + "- forward message";
    }

    @KafkaListener(topics = "topic-test-130")
    public void receiveMsgContent(ConsumerRecord<String, String> record) {
        if (Objects.nonNull(record)) {
            // 消息内容
            String message = record.value();
            // 消息所在的分区
            int partition = record.partition();
            log.info(">>> receive message:{},message partition:{}", message, partition);
        }
    }
}
