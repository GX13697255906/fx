package com.fx.cloud.gateway.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 开放网关-路由
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
@Getter
@Setter
@TableName("gateway_route")
@ApiModel(value = "GatewayRoute对象", description = "开放网关-路由")
public class GatewayRoute extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("路由ID")
    private Long routeId;

    @ApiModelProperty("路由名称")
    private String routeName;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("服务ID")
    private String serviceId;

    @ApiModelProperty("完整地址")
    private String url;

    @ApiModelProperty("忽略前缀")
    private Integer stripPrefix;

    @ApiModelProperty("0-不重试 1-重试")
    private Integer retryable;

    @ApiModelProperty("状态:0-无效 1-有效")
    private Integer status;

    @ApiModelProperty("是否为保留数据:0-否 1-是")
    private Integer isPersist;

    @ApiModelProperty("路由说明")
    private String routeDesc;

    @ApiModelProperty("市场ID")
    private Long marketId;


}
