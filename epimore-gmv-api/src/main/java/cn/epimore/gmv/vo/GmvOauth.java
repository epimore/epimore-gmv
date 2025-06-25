package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 认证表：可扩展其他业务关联信息，如地址，关联对象、路、站、厂、村等业务颗粒对象信息
 * @TableName GMV_OAUTH
 */
@ApiModel(value = "GmvOauth", description = "认证表")
@Getter
@Setter
public class GmvOauth extends BasePage implements Serializable {
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
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    @ApiModelProperty("维度")
    private BigDecimal latitude;
    @ApiModelProperty("地址")
    private String address;
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
    @ApiModelProperty("创建人")
    private String createBy;
    @ApiModelProperty("组织编码")
    private String sysOrgCode;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("更新人")
    private String updateBy;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
