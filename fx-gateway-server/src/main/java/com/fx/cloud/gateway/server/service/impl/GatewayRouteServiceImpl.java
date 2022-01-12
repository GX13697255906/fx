package com.fx.cloud.gateway.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.cloud.gateway.server.entity.GatewayRoute;
import com.fx.cloud.gateway.server.mapper.GatewayRouteMapper;
import com.fx.cloud.gateway.server.service.IGatewayRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 开放网关-路由 服务实现类
 * </p>
 *
 * @author xun.guo
 * @since 2022-01-12
 */
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Override
    public List<GatewayRoute> getAllFxService() {
        List<GatewayRoute> routeList = Lists.newArrayList();
        QueryWrapper<GatewayRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().likeRight(GatewayRoute::getServiceId, "fx-");
        routeList = gatewayRouteService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(routeList)) {
            return routeList;
        }
        return Lists.newArrayList();
    }
}
