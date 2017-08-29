package cn.jin.userapi.dao.user.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * user
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-05-29 21:25
 */
public class UserDO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public UserDO() {
    }

    public UserDO(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override public String toString() {
        return "UserDO{" + "userId=" + userId + ", name='" + name + '\'' + ", birthday=" + birthday + ", age=" + age
                + ", sex=" + sex + '}';
    }
}
