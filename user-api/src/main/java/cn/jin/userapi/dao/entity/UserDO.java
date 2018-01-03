package cn.jin.userapi.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type UserDO
 * @Desc
 * @Date 2017-12-18 16:11
 */
@Entity
@Table(name="user")
public class UserDO implements Serializable{

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sex")
    private int sex;

    @Column(name = "age")
    private int age;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "pass_word")
    private String passWord;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar")
    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override public String toString() {
        return "UserDO{" + "id=" + id + ", sex=" + sex + ", age=" + age + ", userName='" + userName + '\''
                + ", passWord='" + passWord + '\'' + ", nickName='" + nickName + '\'' + ", avatar='" + avatar + '\''
                + '}';
    }
}
