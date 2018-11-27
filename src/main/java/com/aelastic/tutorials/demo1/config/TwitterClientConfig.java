package com.aelastic.tutorials.demo1.config;


import com.twitter.hbc.core.endpoint.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = true, ignoreInvalidFields = true)
@PropertySource("classpath:twitter-client.properties")
public class TwitterClientConfig {

    @Value("${search.terms}")
    private List<String> searchTerms;
    private List<Long> followings;
    private List<Location> locations;
    private String language;

    public List<String> getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(List<String> searchTerms) {
        this.searchTerms = searchTerms;
    }

    public List<Long> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Long> followings) {
        this.followings = followings;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "TwitterClientConfig{" +
                "searchTerms=" + searchTerms +
                ", followings=" + followings +
                ", locations=" + locations +
                ", language='" + language + '\'' +
                '}';
    }
}
