package cn.jin.jmxtest;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserSourceBeanDefinitionParser
 * @Desc
 * @Date 2017-11-03 17:41
 */
public class UserSourceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("userName");
        bean.addPropertyValue("userName", userName);

        String userId = element.getAttribute("userId");
        bean.addPropertyValue("userId", userId);

        String sex = element.getAttribute("sex");
        bean.addPropertyValue("sex", sex);
    }
}
