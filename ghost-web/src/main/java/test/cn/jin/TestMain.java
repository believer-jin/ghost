package test.cn.jin;

import cn.jin.web.manager.po.UserDO;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @Type TestMain
 * @Desc
 * @author alexander
 * @Date 2017-08-22 21:15
 * @version 1.0
 */
public class TestMain<T> {

    public static void main(String[] args) {
        try {
            objectToCell(new UserDO());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static<T> Set<String> objectToCell(T t) throws ClassNotFoundException {
        Set<String> set = new HashSet<String>();
        Class c = Class.forName(t.getClass().getName());
        Field[] fields = c.getDeclaredFields();
        for(Field m : fields){
            System.out.println(m.getName());
        }
        return set;
    }
}
