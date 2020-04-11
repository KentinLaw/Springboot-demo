package com.kt.wms.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kt.wms.model.system.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 15:57
 * @description 用户方法接口
 */
public interface UserService extends IService<User>, UserDetailsService {

}
