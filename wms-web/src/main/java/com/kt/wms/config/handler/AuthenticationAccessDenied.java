package com.kt.wms.config.handler;

import com.kt.wms.model.result.ResultCode;
import com.kt.wms.model.result.ResultFactory;
import com.kt.wms.util.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description 无权限handler
 */
@Component
public class AuthenticationAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        try {
            ResponseUtils.write(httpServletResponse, ResultFactory.buildResult(ResultCode.PERMISSION_DENY,"无权限",null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
