package com.kt.wms.config.handler;

import com.kt.wms.model.result.ResultFactory;
import com.kt.wms.util.ResponseUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description 登陆失败handler
 */
@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        try {
            String message;
            if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                message = "用户名或密码输入错误，登录失败!";
            } else if (e instanceof DisabledException) {
                message = "账户被禁用，登录失败，请联系管理员!";
            } else {
                message = "登录失败!";
            }
            ResponseUtils.write(httpServletResponse, ResultFactory.buildFailResult(message));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}