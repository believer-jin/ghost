package cn.jin.web.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type CustomUserDetailsService
 * @Desc
 * @Date 2017-12-18 17:21
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final static String ROLE_TAG = "ROLE_USER";

    private static Map<String,UserPrincipal> USER_MAP = null;

    static{
        USER_MAP = Maps.newHashMap();
        List<GrantedAuthority> authorities = Lists.newArrayList();
        authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
        UserPrincipal u1 = new UserPrincipal("111","111",authorities);
        USER_MAP.put("111",u1);
        UserPrincipal u2 = new UserPrincipal("222","222",authorities);
        USER_MAP.put("222",u2);
        UserPrincipal u3 = new UserPrincipal("333","333",authorities);
        USER_MAP.put("333",u3);
        UserPrincipal u4 = new UserPrincipal("444","444",authorities);
        USER_MAP.put("444",u4);
    }

/*
    @Resource
    private UserManager userManager;*/

    @Override public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       /* UserDO user = userManager.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
        // 用户认证（用户名，密码，权限）
        UserPrincipal userPrincipal = new UserPrincipal(s, user.getPassWord(), authorities);
        userPrincipal.setNickName(user.getNickName());
        userPrincipal.setAvatar(user.getAvatar());
        return userPrincipal;*/
       if(StringUtils.isNotBlank(s)){
           return USER_MAP.get(s);
       }
       return null;
    }
}
