package test.cn.jin.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type NetworkHealthChecker
 * @Desc 缓存服务校验
 * @Date 2017-11-10 11:02
 */
public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch latch) {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
