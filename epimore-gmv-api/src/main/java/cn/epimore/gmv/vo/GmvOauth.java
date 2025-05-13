package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 认证表：可扩展其他业务关联信息，如地址，关联对象、路、站、厂、村等业务颗粒对象信息
 * @TableName GMV_OAUTH
 */
@ApiModel(value = "GmvOauth", description = "认证表")
public class GmvOauth implements Serializable {
    /**
     * 中心8行业2类型3网络1序号6
     */
    @ApiModelProperty("中心8行业2类型3网络1序号6")
    private String deviceId;
    /**
     * 设备域ID
     */
    @ApiModelProperty("设备域ID")
    private String domainId;
    /**
     * 设备域
     */
    @ApiModelProperty("设备域")
    private String domain;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String pwd;
    /**
     * 是否校验密码，0-否，1-是(默认)
     */
    @ApiModelProperty("是否校验密码，0-否，1-是(默认)")
    private String pwdCheck;
    /**
     * 别名
     */
    @ApiModelProperty("别名")
    private String alias;
    /**
     * 设备状态，0-停用，1-启用（默认）
     */
    @ApiModelProperty("设备状态，0-停用，1-启用（默认）")
    private String status;
    /**
     * 心跳间隔：秒
     */
    @ApiModelProperty("心跳间隔：默认60秒")
    private Integer heartbeatSec;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdCheck() {
        return pwdCheck;
    }

    public void setPwdCheck(String pwdCheck) {
        this.pwdCheck = pwdCheck;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHeartbeatSec() {
        return heartbeatSec;
    }

    public void setHeartbeatSec(Integer heartbeatSec) {
        this.heartbeatSec = heartbeatSec;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
