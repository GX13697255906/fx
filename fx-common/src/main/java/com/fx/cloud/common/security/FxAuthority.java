package com.fx.cloud.common.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class FxAuthority implements GrantedAuthority {

    /**
     * 权限Id
     */
    private String authorityId;
    /**
     * 权限标识
     */
    private String authority;
    /**
     * 过期时间,用于判断权限是否已过期
     */
    private Date expireTime;
}
