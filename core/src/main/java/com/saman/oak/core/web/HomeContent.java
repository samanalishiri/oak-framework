package com.saman.oak.core.web;

import org.springframework.stereotype.Component;

@Component(HomeContent.NAME)
public class HomeContent {

    public static final String NAME = "homeContent";

    private String url;
    private String title;

    public void clear() {
        this.url = new String();
        this.title = new String();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
