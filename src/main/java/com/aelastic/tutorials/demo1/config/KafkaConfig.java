package com.aelastic.tutorials.demo1.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;

@Configuration
@PropertySource("classpath:kafka/tweets-producer.properties")
@ConfigurationProperties
public class KafkaConfig {

    @Value("${tweet.topic}")
    private String tweetTopic;

    @Value("${broker.list}")
    private List<String> brokerList;

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        HashMap<String, Object> configs = new HashMap<>();
        //some kafka configuration
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, tweetTopic);

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return (KafkaProducer<String, String>) new KafkaProducer(configs);
    }


    public String getTweetTopic() {
        return tweetTopic;
    }

    public void setTweetTopic(String tweetTopic) {
        this.tweetTopic = tweetTopic;
    }

    public List<String> getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(List<String> brokerList) {
        this.brokerList = brokerList;
    }
}
