/*
package test.cn.jin.java8;

import cn.jin.web.manager.po.UserDO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

*/
/**
 * java8
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-07-25 19:40
 *//*

public class Class1 {

    */
/**
     *
     *//*

    public static List<UserDO> userList;

    static{
        userList = Lists.newArrayList();
        UserDO user1 = new UserDO();
        user1.setId(1L);
        user1.setAge(23);
        user1.setName("");
        UserDO user2 = new UserDO();
        user2.setId(2L);
        user2.setAge(13);
        user2.setName("");
        UserDO user3 = new UserDO();
        user3.setId(3L);
        user3.setAge(75);
        user3.setName("");
        UserDO user4 = new UserDO();
        user4.setId(4L);
        user4.setAge(3);
        user4.setName("");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    */
/**
     * ����java8�ļ������򣨼��������ͣ�
     *//*

    @Test
    public void testSortSimple(){
        List<Integer> list = Lists.newArrayList(1,3,4,7,5,2);
        list.sort(Integer::compareTo);
        System.out.println(new Gson().toJson(list));
    }


    */
/**
     * ����java8�ļ�������(��������)
     *//*

    @Test
    public void testSortObject(){
        userList.sort(Comparator.comparing(UserDO::getAge));
        System.out.println(new Gson().toJson(userList));
    }

    */
/**
     * ����java8�ļ���filter(�������ͣ��첽)
     *//*

    @Test
    public void testStreamAync(){
        List<UserDO> youngUser = userList.stream().filter((UserDO u) -> u.getAge() < 20).collect(Collectors.toList());
        System.out.println(new Gson().toJson(youngUser));
    }

    */
/**
     * ����java8�ļ�������filter(�������ͣ�ͬ��)
     *//*

    @Test
    public void testStreamSync(){
        List<UserDO> oldUser = userList.parallelStream().filter((UserDO u) -> u.getAge() < 60).collect(Collectors.toList());
        System.out.println(new Gson().toJson(oldUser));
    }

    */
/**
     * Optional ����
     *//*

    @Test
    public void testOptional(){
        Optional<UserDO> user = Optional.ofNullable(new UserDO());
        System.out.println(user.map(UserDO::getName).orElse("unknown"));
    }

    */
/**
     * foreach����
     *//*

    @Test
    public void testForEach(){
        userList.forEach(System.out::println);
        userList.forEach(u -> System.out.println(u.getName()));
    }

    @Test
    public void testStringEncode(){
        String s = "Lianers-MacBook-Pro.local";
        System.out.println(LocalDateTime.now());
        try {
            System.out.println(s.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void test111(){
        System.out.println(isPermission(123L));
    }

    private boolean isPermission(Long userId){
        String permissionUser = "123";
        if(StringUtils.isNotBlank(permissionUser)){
            String[] userIds = StringUtils.split(permissionUser,",");
            if(userIds != null && userIds.length > 0){
                for (String user : userIds) {
                    if(String.valueOf(userId).equals(user)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
*/
