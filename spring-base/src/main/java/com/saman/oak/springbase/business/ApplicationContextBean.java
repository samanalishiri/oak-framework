package com.saman.oak.springbase.business;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

@Component(value = ApplicationContextBean.NAME)
public class ApplicationContextBean implements ApplicationContextAware, ServletContextAware {

    public static final String NAME = "applicationContextBean";

    private static ApplicationContext applicationContext = null;
    private static ServletContext servletContext;

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> c) {
        return applicationContext.getBean(beanName, c);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> beanType) {
        return applicationContext.getBeansOfType(beanType);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextBean.applicationContext = applicationContext;
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        ApplicationContextBean.servletContext = servletContext;
    }

}
