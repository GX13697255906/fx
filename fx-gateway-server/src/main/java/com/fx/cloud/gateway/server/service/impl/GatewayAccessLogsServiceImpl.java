package com.fx.cloud.gateway.server.service.impl;

import com.fx.cloud.gateway.server.entity.GatewayAccessLogs;
import com.fx.cloud.gateway.server.mapper.GatewayAccessLogsMapper;
import com.fx.cloud.gateway.server.service.IGatewayAccessLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 开放网关-访问日志 服务实现类
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
@Service
public class GatewayAccessLogsServiceImpl extends ServiceImpl<GatewayAccessLogsMapper, GatewayAccessLogs> implements IGatewayAccessLogsService {

}
