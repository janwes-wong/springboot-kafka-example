package com.janwes.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq.consumer
 * @date 2021/8/25 15:38
 * @description
 */
@Slf4j
@Component
public class MessageListener {

    @KafkaListener(topics = "topic-test-110")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        if (Objects.nonNull(record)) {
            String message = record.value();
            log.info(">>> receive message:{}", message);
        }
    }
}
