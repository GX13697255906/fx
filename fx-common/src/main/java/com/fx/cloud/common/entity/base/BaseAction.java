package com.fx.cloud.common.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统资源-功能操作
 *
 * @author: houcun
 * @date: 2018/10/24 16:21
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("base_action")
public class BaseAction extends AbstractEntity {

    private static final long serialVersionUID = 7611954466885953760L;

    /**
     * 资源ID
     */
    @TableId(type= IdType.ASSIGN_ID)
    private Long actionId;

    /**
     * 资源编码
     */
    private String actionCode;

    /**
     * 资源名称
     */
    private String actionName;

    /**
     * 资源父节点
     */
    private Long menuId;

    /**
     * 优先级 越小越靠前
     */
    private Integer priority;

    /**
     * 资源描述
     */
    private String actionDesc;

    /**
     * 状态:0-无效 1-有效
     */
    private Integer status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;

    /**
     * 服务ID
     */
    private String serviceId;
}
