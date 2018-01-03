package cn.jin.jmxtest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserXmlTest
 * @Desc
 * @Date 2017-11-03 18:13
 */
public class UserXmlTest {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring.xml");
        User ds = (User) context.getBean("user");
        System.out.println(ds);
    }
}
