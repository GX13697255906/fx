package com.fx.cloud.gateway.server.filter;

import com.fx.cloud.common.constants.AuthConstant;
import com.fx.cloud.gateway.server.configuration.security.IgnoreUrlsConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xun.guo
 */
@Data
@Slf4j
@Component
@AllArgsConstructor
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    private final IgnoreUrlsConfig ignoreUrlsConfig;


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String uriPath = request.getPath().toString();

        log.info("url:{}", uriPath);
        boolean action = false;
        for (String url : ignoreUrlsConfig.getUrls()) {
            if (antPathMatcher.match(url, uriPath)) {
                action = true;
                break;
            }
        }
        // 跳过不需要验证的路径
        if (action) {
            return chain.filter(exchange);
        }

//        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        if (null == token || token.isEmpty()) {
//            ServerHttpResponse response = exchange.getResponse();
//            //当请求不携带Token或者token为空时，直接设置请求状态码为401，返回
//            InetSocketAddress remoteAddress = request.getRemoteAddress();
//            String clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
//            log.info("非法请求，客户端IP：" + clientIp + "URL：" + request.getPath());
//            JSONObject message = new JSONObject();
//            message.put("code", HttpStatus.UNAUTHORIZED.value());
//            message.put("msg", "非法请求");
//            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//
//        }
        ServerHttpRequest authorization = request.mutate().headers(httpHeaders -> {
            httpHeaders.add(AuthConstant.AUTHORITY_KEY, "");
        }).build();
        ServerWebExchange serverWebExchange = exchange.mutate().request(authorization).build();
        return chain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
