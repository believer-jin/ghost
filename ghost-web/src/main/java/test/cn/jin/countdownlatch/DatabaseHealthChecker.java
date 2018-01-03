package test.cn.jin.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type DatabaseHealthChecker
 * @Desc 数据库服务校验
 * @Date 2017-11-10 11:03
 */
public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch latch) {
        super("Database Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}