package com.kt.wms.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:11
 * @description 用户类
 */
@Data
@ApiModel(description = "系统用户")
@TableName("sys_user")
@NoArgsConstructor
public class User implements Serializable , UserDetails {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", hidden=true)
    @TableId(type = AUTO)
    private int id;

    @ApiModelProperty(value = "用户账号", required=true)
    private String username;

    @ApiModelProperty(value = "用户名称")
    private String nickname;

    @ApiModelProperty(value = "用户密码", required=true)
    @NotNull(message = "密码不能为空！")
    private String password;

    @ApiModelProperty(value = "用户等级")
    private int level;

    @ApiModelProperty(value = "权限列表", hidden=true)
    @TableField(exist = false)
    private List<SysGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @ApiModelProperty(hidden=true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @ApiModelProperty(hidden=true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @ApiModelProperty(hidden=true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @ApiModelProperty(hidden=true)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
