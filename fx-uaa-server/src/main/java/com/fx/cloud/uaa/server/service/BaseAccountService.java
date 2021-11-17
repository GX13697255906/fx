package com.fx.cloud.uaa.server.service;

import com.fx.cloud.common.entity.base.BaseAccount;
import com.fx.cloud.common.mybatis.service.IBaseService;

/**
 * 系统用户登录账号管理
 * 支持多账号登陆
 *
 * @author houcun
 */
public interface BaseAccountService extends IBaseService<BaseAccount> {

    /**
     * 根据同户名和密码获取用户信息
     *
     * @param account
     * @param password
     * @return
     */
    BaseAccount getByUserNameAndPassword(String account, String password);

}
