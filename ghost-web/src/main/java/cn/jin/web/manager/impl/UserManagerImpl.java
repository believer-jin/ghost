package cn.jin.web.manager.impl;

import cn.jin.web.manager.UserManager;
import cn.jin.web.manager.po.UserDO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserManagerImpl
 * @Desc
 * @Date 2017-12-18 16:41
 */
@Service
public class UserManagerImpl implements UserManager {

    private static final String URL = "http://localhost:8762/user";

    @Resource
    private RestTemplate restTemplate;

    @Override public UserDO findByUserName(String userName) {
        return restTemplate.getForObject(URL+"/getUserInfo/"+userName,UserDO.class);
    }

    @Override public UserDO insert(UserDO user) {
        ResponseEntity<UserDO> res =  restTemplate.postForEntity(URL+"/add",user,UserDO.class);
        return res.getStatusCode().equals(HttpStatus.OK) ? res.getBody() : null;
    }
}
