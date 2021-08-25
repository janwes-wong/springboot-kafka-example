package com.janwes.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq
 * @date 2021/8/25 16:23
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaMessageTest {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void sendMessage() {
        kafkaTemplate.send("topic-test-110", "hello world");
    }
}
