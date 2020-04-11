package com.kt.wms.config;


import com.kt.wms.config.filter.JwtAuthenticationTokenFilter;
import com.kt.wms.config.handler.AuthenticationAccessDenied;
import com.kt.wms.config.handler.AuthenticationAnonymousAccess;
import com.kt.wms.config.handler.AuthenticationFailure;
import com.kt.wms.config.handler.AuthenticationSuccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:09
 * @description SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //无权访问
    @Resource
    AuthenticationAccessDenied authenticationAccessDenied;

    //用户验证
    @Resource
    SecurityAuthentication authentication;

    //登陆失败
    @Resource
    AuthenticationFailure authenticationFailure;

    //认证失败
    @Resource
    AuthenticationAnonymousAccess authenticationAnonymousAccess;

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    AuthenticationSuccess authenticationSuccess;

    //安全过滤器
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        //关闭session 用token替代
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .anyRequest().authenticated();

        http.formLogin().successHandler(authenticationSuccess).failureHandler(authenticationFailure);

        http.authorizeRequests().accessDecisionManager(accessDecisionManager());

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        http.exceptionHandling()
                //匿名访问,没有权限的处理类
                .accessDeniedHandler(authenticationAccessDenied)
                //认证失败
                .authenticationEntryPoint(authenticationAnonymousAccess)
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authentication);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                // new RoleVoter(),
                new SecurityAuthorization(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }
}
