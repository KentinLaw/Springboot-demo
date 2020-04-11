package com.kt.wms.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kt.wms.mapper.system.PermissionMapper;
import com.kt.wms.mapper.system.RoleMapper;
import com.kt.wms.mapper.system.UserMapper;
import com.kt.wms.model.system.Permission;
import com.kt.wms.model.system.SysGrantedAuthority;
import com.kt.wms.model.system.User;
import com.kt.wms.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 15:57
 * @description 用户方法实现接口
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username",username);

        User user = userMapper.selectOne(qw);
        if (user == null){
            throw new UsernameNotFoundException("");
        }

        List<Permission> permissions = permissionMapper.listByUser(user.getId());
        List<SysGrantedAuthority> authorities = new ArrayList<>();

        for (Permission permission : permissions){
            authorities.add(new SysGrantedAuthority(permission.getUrl(),permission.getMethod()));
        }
        user.setAuthorities(authorities);
        return user;
    }
}
