package com.janwes.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq.consumer
 * @date 2021/8/29 17:04
 * @description
 */
@Slf4j
@Component
public class KafkaMsgListener {

    @Autowired
    private ConsumerFactory consumerFactory;

    /**
     * 消息过滤器
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 丢弃被过滤的消息
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(new RecordFilterStrategy() {
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                // 返回true则消息被过滤
                return Integer.parseInt(consumerRecord.value().toString()) % 2 != 0;
            }
        });
        return factory;
    }

    @KafkaListener(topics = "topic-test-140", containerFactory = "filterContainerFactory")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        if (Objects.nonNull(record)) {
            // 消息内容
            String message = record.value();
            // 消息所在的分区
            int partition = record.partition();
            // topic
            String topic = record.topic();
            log.info(">>> receive message:{},topic:{},message partition:{}", message, topic, partition);
        }
    }
}
