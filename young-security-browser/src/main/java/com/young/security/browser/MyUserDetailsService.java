package com.young.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Youngni
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录进来的用户名：" + username);

        String encode = passwordEncoder.encode("123456");
        logger.info("数据的密码为：" + encode);

        return new User(username,passwordEncoder.encode("123456"),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("用户登录所有权限"));

        /**
         * @param : username 数据库中的用户名
         * @param : password 数据库中的用户名所对应的密码
         * @param : authorities 授权 权限（接口）集合
         *
         * @param : enabled 是否删除（标识，不可恢复）
         * @param : accountNonExpired 用户是否过期
         * @param : credentialsNonExpired 密码是否过期
         * @param : accountNonLocked 是否冻结（标识，可恢复）
         *
         *        passwordEncoder.encode("123456")应该是在注册的时候调用，并把返回值存入数据库
         *        登录的时候将返回值取出和前台输入的密码做比较 passwordEncoder.matches(rawPassword,
         *        encodedPassword) 密码和权限 都是应该从数据中取出来的，依据的开始就是 username 参数
         */
    }
}
