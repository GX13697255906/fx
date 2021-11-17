package com.fx.cloud.uaa.server.service.impl;

import com.fx.cloud.uaa.server.entity.FxUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xun.guo
 * 获取用户权限、认证信息
 */
@Slf4j
@Service("userDetailService")
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//      手动填充用户信息，后面改成数据库获取
        FxUserDetails userDetails = new FxUserDetails();
        userDetails.setAccountNonLocked(true);
        userDetails.setEnabled(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setUsername("xun.guo");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDetails.setPassword(passwordEncoder.encode("123456"));
        Set authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户角色
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        userDetails.setAuthorities(authoritiesSet);
        return userDetails;
    }
}
