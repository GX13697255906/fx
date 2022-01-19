package com.fx.cloud.gateway.server.constants;

/**
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
public class AuthConstant {

  public static final String AUTHORITY_PREFIX = "ROLE_";
  public static final String AUTHORITY_KEY = "Authorization";

  /**
   * redis存储token_key
   */
  public final static String AUTH_ACCESS_KEY = "fx_access";

  /**
   * 前端传递token前缀
   */
  public final static String BEARER_TOKEN_PREFIX = "Bearer";
}
