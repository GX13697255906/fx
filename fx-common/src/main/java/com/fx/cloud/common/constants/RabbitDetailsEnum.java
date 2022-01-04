package com.fx.cloud.common.constants;

import java.io.Serializable;

/**
 * @author Administrator
 */
public enum RabbitDetailsEnum implements Serializable {

//    private final static String FX_EXCHANGE = "fx_exchange";

    DIRECT_LOG("fx_exchange_direct", "fx_queue_log", "直连型-日志传输队列"),
    FANOUT_LOG("fx_exchange_fanout", "fx_queue_log", "扇型-日志传输队列"),
    TOPIC_LOG("fx_exchange_topic", "fx_queue_log", "主题型-日志传输队列"),
    HEADERS_LOG("fx_exchange_headers", "fx_queue_log", "headers型-日志传输队列"),

    API("fx_exchange", "fx_queue_api", "API传输队列");

    private String exchange;
    private String queue;
    private String des;

    private RabbitDetailsEnum(String exchange, String queue, String des) {
        this.exchange = exchange;
        this.queue = queue;
        this.des = des;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
