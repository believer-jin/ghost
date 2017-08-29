package cn.jin.userapi.utils;


import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;

/**
 * pojo转换器
 * 
 * @author jin
 * @since 2017-03-31
 */
public class PojoConverter {

    /**
     * 方法前缀是get
     */
    static final String GET_PREFIX = "get";

    /**
     * 方法前缀是get
     */
    static final String SET_PREFIX = "set";

    /**
     * 通过反射转换对象
     * 
     * @param clz  原始对象
     * @param fromName  原始对象的名称
     * @param toName  要转换的对象的名称
     */
    public static void convert(Class<?> clz, String fromName,String toName) {
        Method[] fromMethods = clz.getDeclaredMethods();
        for (Method method : fromMethods) {
            String fromMehodName = method.getName();
            if(StringUtils.isNotBlank(fromMehodName) && fromMehodName.startsWith(GET_PREFIX)){
                System.out.println(toName+"."+SET_PREFIX+fromMehodName.substring(3,fromMehodName.length())+"("+fromName+"."+fromMehodName+"()"+");");
            }
        }

    }

    public static void main(String[] args) {
        convert(UserDO.class, "do1","vo");
    }
}
