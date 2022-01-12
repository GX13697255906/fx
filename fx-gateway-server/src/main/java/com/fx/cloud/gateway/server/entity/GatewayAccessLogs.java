package com.fx.cloud.gateway.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 开放网关-访问日志
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
@Getter
@Setter
@TableName("gateway_access_logs")
@ApiModel(value = "GatewayAccessLogs对象", description = "开放网关-访问日志")
public class GatewayAccessLogs extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("访问ID")
    private Long accessId;

    @ApiModelProperty("访问路径")
    private String path;

    @ApiModelProperty("请求数据")
    private String params;

    @ApiModelProperty("请求头")
    private String headers;

    @ApiModelProperty("请求IP")
    private String ip;

    @ApiModelProperty("响应状态")
    private String httpStatus;

    private String method;

    @ApiModelProperty("访问时间")
    private LocalDateTime requestTime;

    private LocalDateTime responseTime;

    private Long useTime;

    private String userAgent;

    @ApiModelProperty("区域")
    private String region;

    @ApiModelProperty("认证信息")
    private String authentication;

    @ApiModelProperty("服务名")
    private String serviceId;

    @ApiModelProperty("错误信息")
    private String error;

    @ApiModelProperty("用户姓名")
    private String nickName;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("操作类型 添加、删除、更新、查询")
    private String operationType;

    @ApiModelProperty("请求名称")
    private String apiName;

    @ApiModelProperty("客户端IP")
    private String clientIp;

    @ApiModelProperty("市场名称")
    private Long marketId;

    @ApiModelProperty("区域编码")
    private String areaCode;


}
