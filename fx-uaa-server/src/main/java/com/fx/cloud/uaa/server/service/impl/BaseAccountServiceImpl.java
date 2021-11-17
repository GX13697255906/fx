package com.fx.cloud.uaa.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.cloud.common.entity.base.BaseAccount;
import com.fx.cloud.common.mybatis.service.impl.BaseServiceImpl;
import com.fx.cloud.uaa.server.mapper.BaseAccountMapper;
import com.fx.cloud.uaa.server.service.BaseAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通用账号
 *
 * @author houcun
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseAccountServiceImpl extends BaseServiceImpl<BaseAccountMapper, BaseAccount> implements BaseAccountService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseAccount getByUserNameAndPassword(String account, String password) {
        password = passwordEncoder.encode(password);
        QueryWrapper<BaseAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(BaseAccount::getAccount, account)
                .eq(BaseAccount::getPassword, password);
        return getOne(queryWrapper, false);
    }
}
