package cn.jin.userapi.configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.hystrix.contrib.requestservlet.HystrixRequestContextServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-31 19:29
 */
@Configuration
public class ServletConfig {

    @Bean
    public HystrixMetricsStreamServlet hystrixMetricsStreamServlet() {
        return new HystrixMetricsStreamServlet();
    }

    @Bean
    public ServletRegistrationBean hystrixServletRegistrationBean(
            HystrixMetricsStreamServlet hystrixMetricsStreamServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(hystrixMetricsStreamServlet);
        registration.setEnabled(true);
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }

    @Bean
    public FilterRegistrationBean hystrixRequestContextServletFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HystrixRequestContextServletFilter hystrixRequestContextServletFilter = new HystrixRequestContextServletFilter();
        registrationBean.setFilter(hystrixRequestContextServletFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("GBK");
        characterEncodingFilter.setForceEncoding(true);
        registrationBean.setFilter(characterEncodingFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
