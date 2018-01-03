package test.cn.jin.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type NetworkHealthChecker
 * @Desc 网络服务校验
 * @Date 2017-11-10 11:02
 */
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch latch) {
        super("Network Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
