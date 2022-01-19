package com.fx.cloud.uaa.server.service.impl;

import com.fx.cloud.common.entity.base.BaseAccount;
import com.fx.cloud.common.entity.security.FxAuthority;
import com.fx.cloud.common.entity.security.FxUserDetails;
import com.fx.cloud.uaa.server.service.BaseAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author xun.guo
 * 获取用户权限、认证信息
 */
@Slf4j
@Service("userDetailService")
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BaseAccountService baseAccountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//      手动填充用户信息，后面改成数据库获取
        FxUserDetails userDetails = new FxUserDetails();
        userDetails.setAccountNonLocked(true);
        userDetails.setEnabled(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setAccountNonExpired(true);
        BaseAccount baseAccount = baseAccountService.getByUserNameAndPassword(s);
        userDetails.setUsername(s);
        if (!ObjectUtils.isEmpty(baseAccount)) {
            userDetails.setPassword(baseAccount.getPassword());
        }
        Collection<FxAuthority> authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户角色
        FxAuthority authority = new FxAuthority();
        authority.setAuthorityId("1");
        authority.setAuthority("/es/person/search");
        authoritiesSet.add(authority);
        userDetails.setAuthorities(authoritiesSet);
        return userDetails;
    }
}
