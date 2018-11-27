package com.aelastic.tutorials.demo1.twitter.client;


import com.aelastic.tutorials.demo1.config.TwitterClientConfig;
import com.aelastic.tutorials.demo1.config.TwitterSecret;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TwitterClient {
    private BasicClient basicClient;
    private TwitterSecret twitterSecret;
    private TwitterClientConfig twitterClientConfig;
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);


    @Autowired
    public TwitterClient(TwitterSecret twitterSecret, TwitterClientConfig twitterClientConfig) {
        this.twitterSecret = twitterSecret;
        this.twitterClientConfig = twitterClientConfig;
    }


    public BasicClient createClient() {

        if (basicClient != null) {
            return basicClient;
        }

        /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        // Optional: set up some followings and track terms
        hosebirdEndpoint.trackTerms(twitterClientConfig.getSearchTerms());

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(
                twitterSecret.getConsumerKey(),
                twitterSecret.getConsumerSecretKey(),
                twitterSecret.getToken(),
                twitterSecret.getSecretToken());

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-03")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        basicClient = builder.build();
        return basicClient;
    }

    public BlockingQueue<String> getTweets() {
        return msgQueue;
    }

}
