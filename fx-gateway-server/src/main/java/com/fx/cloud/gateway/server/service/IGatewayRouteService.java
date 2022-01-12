package com.fx.cloud.gateway.server.service;

import com.fx.cloud.gateway.server.entity.GatewayRoute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 开放网关-路由 服务类
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
public interface IGatewayRouteService extends IService<GatewayRoute> {

    /**
     * 获取所有fx服务
     *
     * @return
     */
    List<GatewayRoute> getAllFxService();

}
