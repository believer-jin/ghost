package cn.jin.jmxtest;

import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.Serializable;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type User
 * @Desc
 * @Date 2017-11-03 17:46
 */
@ManagedResource(objectName = "bean:name=UserMBeanTest" , description= "My Managed Bean")
public class User implements Serializable{

    private String userName;

    private String userId;

    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", userId='" + userId + '\'' + ", sex='" + sex + '\'' + '}';
    }
}
