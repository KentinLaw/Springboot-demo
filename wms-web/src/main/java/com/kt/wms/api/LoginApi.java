package com.kt.wms.api;

import com.kt.wms.model.system.User;
import com.kt.wms.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 17:12
 * @description 登陆
 */
@Api(value = "登陆接口")
@RestController
public class LoginApi {


    @Resource
    TokenUtils tokenUtil;

    @ApiOperation(value = "测试接口")
    @GetMapping("/code/image")
    public User createCode(HttpServletResponse response,User username){
        return username;
    }
}
