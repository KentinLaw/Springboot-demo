package com.kt.wms.config.filter;

import com.kt.wms.model.system.User;
import com.kt.wms.service.system.UserService;
import com.kt.wms.util.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 15:57
 * @description token通过在ThreadLocal添加用户信息
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    TokenUtils tokenUtils;

    @Resource
    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("x-access-token");
        if (!StringUtils.isEmpty(token)) {
            String username = tokenUtils.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User user = (User)userService.loadUserByUsername(username);
                if (tokenUtils.verify(token, user)){
                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
