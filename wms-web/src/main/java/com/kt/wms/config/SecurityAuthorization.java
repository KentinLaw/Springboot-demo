package com.kt.wms.config;

import com.kt.wms.model.system.SysGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/8 17:04
 * @description Security鉴权决策器 统一在这里处理url权限
 */
public class SecurityAuthorization implements AccessDecisionVoter<Object>{

    /**
     * @Author kentinlaw
     * @Description authentication为验证过后保存在SecurityContext的用户信息，
     *              object为FilterInvocation类型用于获取访问路径
     *              collection展示路径权限信息(FilterInvocationSecurityMetadataSource定义)
     * @Params [authentication, object, collection]
     * @Return int
     * @CreateDate 2020/4/8 19:49
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> collection) {
        int result = ACCESS_DENIED;
        if(authentication == null) {
            return result;
        }
        AntPathMatcher ant = new AntPathMatcher();

        List<SysGrantedAuthority> authorities = (List<SysGrantedAuthority>) extractAuthorities(authentication);
        FilterInvocation filterInvocation = (FilterInvocation) object;

        String url = filterInvocation.getRequestUrl();
        String method = filterInvocation.getHttpRequest().getMethod();

        for (SysGrantedAuthority authority : authorities) {
            if (ant.match(authority.getUrl(), url) && method.equals(authority.getMethod())) {
                result = ACCESS_GRANTED;
                break;
            }
        }
        System.out.println(result);
        return result;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }
}
