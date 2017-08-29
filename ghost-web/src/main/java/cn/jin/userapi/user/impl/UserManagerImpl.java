package cn.jin.userapi.user.impl;

import cn.jin.userapi.dao.user.UserDAO;
import cn.jin.userapi.dao.user.entity.UserDO;
import cn.jin.userapi.user.UserManager;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Subscriber;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * UserManager实现类
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 19:28
 */
@Service
@DefaultProperties(defaultFallback = "fallback",groupKey = "User")
public class UserManagerImpl implements UserManager {

    @Resource
    private UserDAO userDAO;

    /**
     * note:@HystrixCommand不加任何参数，将采用默认的属性，groupKey是类名；commandKey是方法名
     * 如果要改变线程池的name，使用@HystrixCommand#threadPoolKey() fallbackMethod
     * 指明降级函数（必须要放在当前类中）
     * ignoreExceptions指明不回退的异常类型
     * 
     * @param user
     * @return
     */
    @Override
    @HystrixCommand(commandKey = "SaveUser", threadPoolKey = "UserThead", fallbackMethod = "syncFallBack",ignoreExceptions = {ArrayIndexOutOfBoundsException.class},commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800")
    })
    public Long save(UserDO user) {
        return userDAO.save(user);
    }

    @Override
    @HystrixCommand(fallbackMethod = "AsyncFallback")
    public Future<Long> saveAsync(final UserDO user) {
        return new AsyncResult<Long>() {
            @Override
            public Long invoke() {
                return userDAO.save(user);
            }
        };
    }

    @Override
    @CacheResult
    @HystrixCommand
    public UserDO getUserById(@CacheKey Long id) throws InterruptedException {
        Thread.sleep(1000);
        if(id == 321){
            throw new RuntimeException("this command always fails");
        }
        return userDAO.getUser();
    }

    @Override
    @HystrixCommand
    public Observable<Long> saveReactive(final UserDO user) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        Long userId = userDAO.save(user);
                        observer.onNext(userId);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    /**
     * 默认回退
     * 
     * @return
     */
    private UserDO fallback() {
        UserDO user = new UserDO();
        user.setUserId(2L);
        return user;
    }

    /**
     * 如果回退方案被HystrixCommand注解，那么该回退方法也可以拥有自己的回退
     * 
     * @return
     */
    @HystrixCommand
    private Long syncFallBack(UserDO user,Throwable e) {
        return 0L;
    }

    /**
     * 异步的回退 支持以下几种情况： 1.同步方法同步回退 2.异步方法同步回退 3.异步方法异步回退 不支持： 同步方法异步回退
     * 
     * @param user
     * @return
     */
    @HystrixCommand
    private Future<Long> AsyncFallback(UserDO user) {
        return new AsyncResult<Long>() {
            @Override
            public Long invoke() {
                return 1L;
            }
        };
    }

    /**
     * HystrixCollapser批量處理
     */
    /** Asynchronous Execution */
    @HystrixCollapser(batchMethod = "getUserByIds")
    public Future<UserDO> getUserByIdAsync(String id) {
        return null;
    }

    /** Reactive Execution */
    @HystrixCollapser(batchMethod = "getUserByIds")
    public Observable<UserDO> getUserByIdReact(String id) {
        return null;
    }

    @HystrixCommand
    public List<UserDO> getUserByIds(List<String> ids) {
        List<UserDO> users = new ArrayList<UserDO>();
        for (String id : ids) {
            users.add(new UserDO(Long.valueOf(id), "name: " + id));
        }
        return users;
    }

}
