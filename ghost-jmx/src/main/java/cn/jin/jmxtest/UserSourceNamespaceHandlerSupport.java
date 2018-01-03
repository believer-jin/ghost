package cn.jin.jmxtest;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserSourceNamespaceHandlerSupport
 * @Desc
 * @Date 2017-11-03 18:05
 */
public class UserSourceNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserSourceBeanDefinitionParser());
    }
}
