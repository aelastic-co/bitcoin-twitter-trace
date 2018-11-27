package com.aelastic.tutorials.demo1.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("classpath:twitter-secret.properties")
@ConfigurationProperties
public class TwitterSecret {

    @Value("${consumer.key}")
    private String consumerKey;
    @Value("${consumer.secret.key}")
    private String consumerSecretKey;
    @Value("${token}")
    private String token;
    @Value("${secret.token}")
    private String secretToken;


    public String getConsumerKey() {
        return consumerKey;
    }


    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecretKey() {
        return consumerSecretKey;
    }

    public void setConsumerSecretKey(String consumerSecretKey) {
        this.consumerSecretKey = consumerSecretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterSecret that = (TwitterSecret) o;
        return Objects.equals(consumerKey, that.consumerKey) &&
                Objects.equals(consumerSecretKey, that.consumerSecretKey) &&
                Objects.equals(token, that.token) &&
                Objects.equals(secretToken, that.secretToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerKey, consumerSecretKey, token, secretToken);
    }

    @Override
    public String toString() {
        return "TwitterSecret{" +
                "consumerKey='" + consumerKey + '\'' +
                ", consumerSecretKey='" + consumerSecretKey + '\'' +
                ", token='" + token + '\'' +
                ", secretToken='" + secretToken + '\'' +
                '}';
    }
}
