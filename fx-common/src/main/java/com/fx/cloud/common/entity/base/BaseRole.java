package com.fx.cloud.common.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统角色-基础信息
 *
 * @author: houcun
 * @date: 2018/10/24 16:21
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_role")
public class BaseRole extends AbstractEntity {

    private static final long serialVersionUID = 5197785628543375591L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long roleId;

    @ApiModelProperty(value = "市场id")
    private Long marketId;

    @ApiModelProperty(value = "区域id")
    private Long areaId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 状态:0-无效 1-有效 2-禁用
     */
    private Integer status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;
}
