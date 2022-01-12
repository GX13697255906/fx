package com.fx.cloud.gateway.server.mapper;

import com.fx.cloud.gateway.server.entity.GatewayAccessLogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 开放网关-访问日志 Mapper 接口
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
@Mapper
public interface GatewayAccessLogsMapper extends BaseMapper<GatewayAccessLogs> {

}
