package test.cn.jin.countdownlatch;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type ApplicationStartupUtil
 * @Desc 启动类，检测子服务是否加载成功
 * @Date 2017-11-10 11:04
 */
public class ApplicationStartupUtil {

    /**
     * 服务列表
     */
    private static List<BaseHealthChecker> _services;

    /**
     * CountDownLatch
     */
    private static CountDownLatch _latch;

    private ApplicationStartupUtil() {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        // Initialize the latch with number of service checkers
        _latch = new CountDownLatch(3);

        // All add checker in lists
        _services = new ArrayList<BaseHealthChecker>();
        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new CacheHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));

        // Start service checkers using executor framework
        Executor executor = Executors.newFixedThreadPool(_services.size());

        for (final BaseHealthChecker v : _services) {
            executor.execute(v);
        }

        // Now wait till all services are checked
        _latch.await();

        // Services are file and now proceed startup
        for (final BaseHealthChecker v : _services) {
            if (!v.isServiceUp()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
           Class s =  Class.forName("MovieResourcePO");
            Method method = s.getMethod("getTitle",null);
            Annotation annotation = method.getAnnotation(ResponseBody.class);
            if(annotation != null){
                System.out.println(111);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
