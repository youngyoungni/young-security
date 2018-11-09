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
public class MyUserDatailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDatailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @param username  : 前台登录( 登录名称 )传过来的 username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("当前登录的用户名：" + username);
        /*
         *  认证流程：
         *      1、注入数据库Dao层，根据 "username" 去数据库查询此用户信息
         *      2、使用 "User" 类 spring-security 的 User
         *      3、在自己使用的时候，可以自定义 User 类实现 UserDetails 接口
         *      4、通过数据库的字段定义实现业务逻辑
         */
        /*
             passwordEncoder.encode("123456")应该是在注册的时候调用，并把返回值存入数据库
             登录的时候将返回值取出和前台输入的密码做比较
             passwordEncoder.matches(rawPassword,encodedPassword)
         */
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是：" + password);

        return new User(username,passwordEncoder.encode("123456")
                , true ,true ,true ,true
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        /*
            四个 true 表示：
                 * @param : enabled 是否删除（标识，不可恢复）
                 * @param : accountNonExpired 用户是否过期
                 * @param : credentialsNonExpired 密码是否过期
                 * @param : accountNonLocked 是否冻结（标识，可恢复）
            new User(username,password,authorities):
                从数据库中取出：
                    username : 用户名
                    password : 密码
                    authorities : 用户的接口权限（菜单权限）...
         */
    }
}
