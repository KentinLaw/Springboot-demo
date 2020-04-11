package com.kt.wms.config;

import com.kt.wms.model.system.User;
import com.kt.wms.service.system.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description Security验证用户
 */
@Component
public class SecurityAuthentication implements AuthenticationProvider {


    @Resource
    UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这个获取表单输入中的用户名
        String userName = authentication.getName();
        // 这个是表单中输入的密码
        String password = (String) authentication.getCredentials();

        // 这里调用我们的自己写的获取用户的方法
        User user = (User) userService.loadUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!new BCryptPasswordEncoder().matches(password,user.getPassword())){
            throw new BadCredentialsException("密码错误");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}