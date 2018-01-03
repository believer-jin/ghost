package cn.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Springboot启动器
 * @author Alexander
 * @since 2017-05-17
 * @version 1.0
 */
@SpringBootApplication
@ServletComponentScan
public class WebApplication {

    /**
     * 注册使用jetty启动
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory factory =
                new JettyEmbeddedServletContainerFactory();
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.setConnectTimeout(1000);
        builder.setReadTimeout(1000);
        return builder.build();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WebApplication.class,args);
    }
}
