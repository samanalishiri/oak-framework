package com.saman.oak.core.web;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component(HomeContent.NAME)
public class HomeContent {

    public static final String NAME = "homeContent";

    private String url;
    private String title;

    public void clear() {
        this.url = new String();
        this.title = new String();
    }

}
