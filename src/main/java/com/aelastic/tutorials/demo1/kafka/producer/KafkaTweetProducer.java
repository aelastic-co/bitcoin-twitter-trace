package com.aelastic.tutorials.demo1.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTweetProducer {


    public KafkaProducer<String, String> kafkaProducer;

    @Autowired
    public KafkaTweetProducer(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendTweet(String tweet) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object o = mapper.readValue(tweet, Object.class);
            tweet = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        ProducerRecord tweetRecord = new ProducerRecord("tweets", tweet);
        kafkaProducer.send(tweetRecord,
                (r, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    System.out.println(tweetRecord.value());
                });
    }


}
