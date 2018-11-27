package com.aelastic.tutorials.demo1.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class KafkaTweetProducer {


    public KafkaProducer<String, String> kafkaProducer;

    @Autowired
    public KafkaTweetProducer(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendTweet(String tweet) {
        kafkaProducer.send(new ProducerRecord("tweets", tweet),
                (r, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        Object o = mapper.readValue(tweet, Object.class);
                        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    IntStream.range(0,3).forEach(System.out::println);
                });
    }


}
