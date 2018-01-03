package cn.jin.userapi.dao.user;

import cn.jin.userapi.dao.user.entity.UserDO;

/**
 * 用户操作DAO
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 19:28
 */
public interface UserDAO {

    /**
     * 保存
     * @param user
     * @return
     */
    Long save(UserDO user);

    /**
     * 获取user
     * @return
     */
    UserDO getUser();
}
