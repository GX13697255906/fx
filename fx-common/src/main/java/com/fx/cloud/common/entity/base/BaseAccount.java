package com.fx.cloud.common.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统用户-登录账号
 *
 * @author houcun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_account")
public class BaseAccount extends AbstractEntity {

    private static final long serialVersionUID = -9061271824337759269L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long accountId;

    /**
     * 系统用户Id
     */
    private Long userId;

    /**
     * 标识：手机号、邮箱、 系统用户名、或第三方应用的唯一标识
     */
    private String account;

    /**
     * 密码凭证：站内的保存密码、站外的不保存或保存token）
     */
    private String password;

    /**
     * 登录类型:password-密码、mobile-手机号、email-邮箱、weixin-微信、weibo-微博、qq-等等
     */
    private String accountType;

    /**
     * 注册IP
     */
    private String registerIp;

    /**
     * 状态:0-禁用 1-启用 2-锁定
     */
    private Integer status;

    /**
     * 账号域
     */
    private String domain;

    /**
     * 是否更新密码  0-更新  1-未更新
     */
    private Integer isUpdatePassword;

    public BaseAccount(Long userId, String account, String password, String accountType, String domain, String registerIp) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.accountType = accountType;
        this.domain = domain;
        this.registerIp = registerIp;
    }

}
