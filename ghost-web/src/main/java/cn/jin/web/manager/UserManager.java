package cn.jin.web.manager;

import cn.jin.web.manager.po.UserDO;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserManager
 * @Desc
 * @Date 2017-12-18 16:40
 */
public interface UserManager {

    UserDO findByUserName(String userName);

    UserDO insert(UserDO user);
}
