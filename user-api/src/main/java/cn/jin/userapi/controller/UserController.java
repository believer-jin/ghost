package cn.jin.userapi.controller;

import cn.jin.userapi.dao.entity.UserDO;
import cn.jin.userapi.manager.UserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Type UserController
 * @Desc user service api
 * @author shujin.ding
 * @Date 2017-08-29 16:23
 * @version 1.0
 */
@RestController
public class UserController {

    @Resource
    private UserManager userManager;

    @GetMapping("/user/getUserInfo/{id}")
    public UserDO getUserInfo(@PathVariable Long id){
        return userManager.findById(id);

    }

    @GetMapping("/user/getUserByUserName/{userName}")
    public UserDO getUserInfo(@PathVariable String userName){
        return userManager.findByUserName(userName);

    }

    @PostMapping("/user/add")
    public UserDO addUser(@RequestBody UserDO user){
        return userManager.insert(user);
    }
}
