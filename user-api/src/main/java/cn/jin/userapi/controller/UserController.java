package cn.jin.userapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Type UserController
 * @Desc user service api
 * @author shujin.ding
 * @Date 2017-08-29 16:23
 * @version 1.0
 */
@RestController
public class UserController {

    @GetMapping("/getUserInfo/{userId}")
    public String getUserInfo(@PathVariable Long userId){
        return "hi "+userId+"!";

    }
}
