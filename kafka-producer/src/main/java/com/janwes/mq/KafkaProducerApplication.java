package com.janwes.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.mq
 * @date 2021/8/22 21:17
 * @description
 */
@EnableScheduling
@SpringBootApplication
public class KafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }
}
