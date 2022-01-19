package com.fx.cloud.gateway.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.fx.cloud.common.constants.RespEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 */
public class FxAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException accessDeniedException) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject result = new JSONObject();
        result.put("code", RespEnum.ACCESS_DENIED.getCode());
        result.put("msg", RespEnum.ACCESS_DENIED.getMessage());
        DataBuffer dataBuffer = response.bufferFactory().wrap(result.toJSONString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
