package cn.jin.userapi.dao;

import cn.jin.userapi.dao.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserDAO
 * @Desc
 * @Date 2017-12-18 16:11
 */
public interface UserDAO extends JpaRepository<UserDO, Long> {
}
