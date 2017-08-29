package cn.jin.userapi.dao.user.impl;

import cn.jin.userapi.dao.user.UserDAO;
import cn.jin.userapi.dao.user.entity.UserDO;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Repository;

/**
 * UserDAO实现类
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 19:29
 */
@Repository
public class UserDAOImpl implements UserDAO{

    @Override public Long save(UserDO user) {
        return RandomUtils.nextLong();
    }

    @Override public UserDO getUser() {
        UserDO user = new UserDO();
        user.setUserId(RandomUtils.nextLong());
        return user;
    }
}
