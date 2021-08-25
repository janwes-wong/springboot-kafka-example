package com.janwes.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq
 * @date 2021/8/22 21:16
 * @description
 */
@SpringBootApplication
public class KafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }
}
