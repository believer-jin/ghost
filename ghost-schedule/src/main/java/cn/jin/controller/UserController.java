package cn.jin.userapi.controller;

import cn.jin.userapi.dao.user.entity.UserDO;
import cn.jin.userapi.user.UserManager;
import cn.jin.userapi.utils.HystrixCommonUtils;
import com.netflix.hystrix.HystrixInvokableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * user
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 22:22
 */
@RestController
public class UserController {


    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserManager userManager;

    @RequestMapping("/user/save")
    public Long save(@RequestParam(value = "name")String name,@RequestParam(value = "sex")Integer sex){
        UserDO user = new UserDO();
        user.setName(name);
        user.setSex(sex);
        return userManager.save(user);
    }

    @PutMapping("/user/savex")
    public Long saveX(@RequestBody MultiValueMap<String,String> values){
        return 1L;
    }

    @RequestMapping("/user/getUser/{id}")
    public Long getUserById(@PathVariable(value = "id")Long id) throws Exception {
        UserDO user = userManager.getUserById(id);
        HystrixInvokableInfo<?> getUserByIdCommand = HystrixCommonUtils.getLastExecutedCommand();
        LOG.error("Is use cache?{}",getUserByIdCommand.isResponseFromCache());
        return user.getUserId();
    }
}
