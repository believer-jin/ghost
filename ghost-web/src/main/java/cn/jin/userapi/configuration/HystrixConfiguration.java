package cn.jin.userapi.configuration;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 *
 * 在spring boot里注册HystrixMetricsStreamServlet
 * @author Alexander
 * @since 2017-05-17
 * @version 1.0
*/
@Configuration
@PropertySource("classpath:application.properties")
public class HystrixConfiguration extends SpringBootServletInitializer{

    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }
}
