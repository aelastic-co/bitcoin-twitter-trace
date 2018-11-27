package com.aelastic.tutorials.demo1.service;

import com.aelastic.tutorials.demo1.kafka.producer.KafkaTweetProducer;
import com.aelastic.tutorials.demo1.twitter.client.TwitterClient;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.httpclient.BasicClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class TweetsService {
    private TwitterClient twitterClient;
    private KafkaTweetProducer kafkaTweetProducer;

    @Autowired
    public TweetsService(TwitterClient twitterClient, KafkaTweetProducer kafkaTweetProducer) {
        this.twitterClient = twitterClient;
        this.kafkaTweetProducer = kafkaTweetProducer;
    }


    public void getTweets() {
        BasicClient basicClient = twitterClient.createClient();
        basicClient.connect();
        BlockingQueue<String> tweets = twitterClient.getTweets();
        while (!basicClient.isDone()){
            try {
                String tweet = tweets.take();
                System.out.println();
                kafkaTweetProducer.sendTweet(tweet);
            } catch (InterruptedException e) {
                basicClient.stop();
            }
        }

        basicClient.stop();
    }

}
