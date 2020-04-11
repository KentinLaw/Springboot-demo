package com.kt.wms.config.handler;

import com.kt.wms.model.result.ResultFactory;
import com.kt.wms.model.system.User;
import com.kt.wms.util.ResponseUtils;
import com.kt.wms.util.TokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description 登陆成功handler
 */
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Resource
    TokenUtils tokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
            User user = (User) authentication.getPrincipal();
            String token = tokenUtils.getToken(user);
            ResponseUtils.write(httpServletResponse, ResultFactory.buildSuccessResult(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
