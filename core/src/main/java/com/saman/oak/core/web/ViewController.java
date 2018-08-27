package com.saman.oak.core.web;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class ViewController implements Controller {

    protected HomeContent homeContent = new HomeContent();

    @ModelAttribute(HomeContent.NAME)
    public HomeContent getHomeContent() {
        return homeContent;
    }

    public abstract String view();
}
