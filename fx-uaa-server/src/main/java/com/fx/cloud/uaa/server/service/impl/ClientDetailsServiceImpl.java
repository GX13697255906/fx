package com.fx.cloud.uaa.server.service.impl;

import com.fx.cloud.uaa.server.entity.FxClientDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xun.guo
 * 获取客户端信息
 */
@Slf4j
@Service("clientDetailsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        //      手动填充客户端信息数据，后面改成数据库获取
        TokenServiceImpl tokenService = new TokenServiceImpl();
        FxClientDetails clientDetails = new FxClientDetails();
        clientDetails = tokenService.getClientDetails();
        return clientDetails;
    }
}
