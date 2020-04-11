package com.kt.wms.config.handler;


import com.kt.wms.model.result.ResultCode;
import com.kt.wms.model.result.ResultFactory;
import com.kt.wms.util.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description 认证失败handle
 */
@Component
public class AuthenticationAnonymousAccess implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        try {
            ResponseUtils.write(httpServletResponse, ResultFactory.buildResult(ResultCode.UNAUTHORIZED,"认证失败",null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
