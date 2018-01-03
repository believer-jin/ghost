package test.cn.jin.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type BaseHealthChecker
 * @Desc
 * @Date 2017-11-10 10:49
 */
public abstract class BaseHealthChecker implements Runnable{

    /**
     * CountDownLatch
     */
    private CountDownLatch _latch;

    /**
     * 服务名称
     */
    private String _serviceName;

    /**
     * 服务是否加载完成
     */
    private boolean _serviceUp;

    public BaseHealthChecker(String serviceName, CountDownLatch latch){
        super();
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            _serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            _serviceUp = false;
        } finally {
            if(_latch != null) {
                _latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }

    /**
     * 校验服务是否加载完成
     */
    public abstract void verifyService();
}
