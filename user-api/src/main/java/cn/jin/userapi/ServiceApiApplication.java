package cn.jin.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot启动器
 * @author Alexander
 * @since 2017-08-29
 * @version 1.0
 */
@SpringBootApplication
/*
@EnableEurekaClient
*/
public class ServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApiApplication.class, args);
    }
}
