package ir.navaco.core.config.init;

import ir.navaco.core.config.orm.SpringDataJpaConfiguration;
import ir.navaco.core.config.web.SpringDispatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class ApplicationInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ContextBean.class, SpringDataJpaConfiguration.class};
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
