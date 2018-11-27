package com.aelastic.tutorials.demo1.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class KafkaConfig {

    private static final String TWEETS = "tweets";

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        HashMap<String, Object> configs = new HashMap<>();
        //some kafka configuration
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, TWEETS);

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return (KafkaProducer<String, String>) new KafkaProducer(configs);
    }


}
