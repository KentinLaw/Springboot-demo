package com.kt.wms.model.system;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/9 16:14
 * @description GrantedAuthority
 */
@Data
public class SysGrantedAuthority implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private String url;

    private String method;

    public SysGrantedAuthority (String url,String method){
        this.url = url;
        this.method = method;
    }
    @Override
    public String getAuthority() {
        return url + method;
    }
}
