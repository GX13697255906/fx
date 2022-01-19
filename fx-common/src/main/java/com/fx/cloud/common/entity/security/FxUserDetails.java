package com.fx.cloud.common.entity.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 自定义用户详情实体
 *
 * @author Administrator
 */
@Data
public class FxUserDetails implements UserDetails {

    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录名称
     */
    private String username;
    /**
     * 用户权限信息
     */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 账户没有过期
     */
    private boolean accountNonExpired;
    /**
     * 账户没有被锁定
     */
    private boolean accountNonLocked;
    /**
     * 身份认证是否是有效的
     */
    private boolean credentialsNonExpired;
    /**
     * 账户是否启用
     */
    private boolean enabled;
}
