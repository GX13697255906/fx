package com.fx.cloud.common.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统用户-基础信息
 *
 * @author houcun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_user")
public class BaseUser extends AbstractEntity {

    private static final long serialVersionUID = -735161640894047414L;

    /**
     * 系统用户ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long userId;

    /**
     * 登陆名
     */
    private String userName;

    /**
     * 用户类型:super-超级管理员 normal-普通管理员
     */
    private String userType;

    /**
     * 企业ID
     */
    @TableField(value = "company_id", strategy = FieldStrategy.IGNORED)
    private Long companyId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String userDesc;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField(exist = false)
    private String password;

    /**
     * 状态:0-禁用 1-正常 2-锁定
     */
    private Integer status;

    /**
     * 职位ID
     */
    @TableField(value = "post_id", strategy = FieldStrategy.IGNORED)
    private Long postId;

    /**
     * 区域code
     */
    @TableField(value = "area_code")
    private String areaCode;

    private String privateKey;
}
