package cn.jin.userapi.configuration;

import com.netflix.hystrix.contrib.requestservlet.HystrixRequestContextServletFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * web Servlet
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-30 11:35
 */
@Order(3)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class, SpringSecurityConfig.class};
    }

    @Override protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        HystrixRequestContextServletFilter hystrixRequestContextServletFilter = new HystrixRequestContextServletFilter();
        return new Filter[] {characterEncodingFilter, hystrixRequestContextServletFilter};
    }
}
