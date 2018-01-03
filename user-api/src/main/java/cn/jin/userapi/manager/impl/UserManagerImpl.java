package cn.jin.userapi.manager.impl;

import cn.jin.userapi.dao.UserDAO;
import cn.jin.userapi.dao.entity.UserDO;
import cn.jin.userapi.manager.UserManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserManagerImpl
 * @Desc
 * @Date 2017-12-18 16:11
 */
@Service
public class UserManagerImpl implements UserManager {
    @Resource
    private UserDAO userDAO;

    @Override public UserDO insert(UserDO user) {
        return userDAO.save(user);
    }

    @Override public UserDO findByUserName(String userName) {
        UserDO userDO = new UserDO();
        userDO.setUserName(userName);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age","sex");
        Example<UserDO> example = Example.of(userDO,matcher);
        return userDAO.findOne(example);
    }

    @Cacheable("user")
    @Override public UserDO findById(Long id) {
        return userDAO.findOne(id);
    }
}
