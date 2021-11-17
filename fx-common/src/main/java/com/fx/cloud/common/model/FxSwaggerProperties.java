package com.fx.cloud.common.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 */
@ConfigurationProperties(prefix = "fx.cloud.swagger")
public class FxSwaggerProperties {
    /**
     * 前缀
     */
    private String suffix;
    /**
     * 是否启用swagger,生产环境建议关闭
     */
    private boolean enabled;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 版本号
     */
    private String version;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
