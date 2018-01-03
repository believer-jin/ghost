package cn.jin.userapi.manager;

import cn.jin.userapi.dao.entity.UserDO;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserDAO
 * @Desc
 * @Date 2017-12-18 16:10
 */
public interface UserManager {

    UserDO findByUserName(String userName);

    UserDO findById(Long id);

    UserDO insert(UserDO user);
}
