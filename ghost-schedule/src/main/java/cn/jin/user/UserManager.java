package cn.jin.userapi.user;

import cn.jin.userapi.dao.user.entity.UserDO;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * user操作类
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 19:27
 */
public interface UserManager {

    /**
     * 保存
     * @param user
     * @return
     */
    Long save(UserDO user);

    /**
     * 保存（异步实现）
     * @param user
     * @return
     */
    Future<Long> saveAsync(UserDO user);

    /**
     * 保存（异步实现）
     * @param user
     * @return
     */
    Observable<Long> saveReactive(UserDO user);

    /**
     * 获取user
     * @return
     */
    UserDO getUserById(Long id) throws Exception;
}
