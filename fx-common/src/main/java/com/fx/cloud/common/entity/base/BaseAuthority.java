package com.fx.cloud.common.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统权限-菜单权限、操作权限、API权限
 *
 * @author houcun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_authority")
public class BaseAuthority extends AbstractEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long authorityId;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 菜单资源ID
     */
    private Long menuId;

    /**
     * API资源ID
     */
    private Long apiId;

    /**
     * 操作资源ID
     */
    private Long actionId;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
