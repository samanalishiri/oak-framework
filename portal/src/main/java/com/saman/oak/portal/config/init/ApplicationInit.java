package com.saman.oak.portal.config.init;

import com.saman.oak.portal.config.security.SpringSecurityConfiguration;
import com.saman.oak.portal.config.web.ContextBean;
import com.saman.oak.portal.config.web.SpringDispatcher;
import com.saman.oak.springdatajpa.config.SpringDataJpaConfiguration;
import org.h2.server.web.WebServlet;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        ServletRegistration.Dynamic servlet = servletContext
                .addServlet("h2-console", new WebServlet());
        servlet.setLoadOnStartup(2);
        servlet.addMapping("/h2-console/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ContextBean.class, SpringSecurityConfiguration.class, SpringDataJpaConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringDispatcher.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        MultipartFilter multipartFilter = new MultipartFilter();

        return new Filter[]{characterEncodingFilter, multipartFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(createMultipartConfigElement());
    }

    public MultipartConfigElement createMultipartConfigElement() {
        return new MultipartConfigElement("", (1024 * 1024 * 10), (1024 * 1024 * 10), 0);
    }


}
