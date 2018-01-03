package cn.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Springboot启动器
 * @author Alexander
 * @since 2017-08-29
 * @version 1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class GateWayServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(GateWayServiceApplication.class, args);

    }
}
