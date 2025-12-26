package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 国标信令服务
 *
 */
@Getter
@Setter
public class GbDomainInfo implements Serializable {
    @ApiModelProperty("sip服务器域ID")
    private String domainId;
    @ApiModelProperty("sip服务器域")
    private String domain;
    @ApiModelProperty("sip服务器地址")
    private String sipIp;
    @ApiModelProperty("sip服务器端口")
    private Integer sipPort;

    @ApiModelProperty("设备类型")
    private String deviceTypeCode;
//    @ApiModelProperty("设备类型")
//    private String typeCodeName;
    @ApiModelProperty("网络类型")
    private String networkTypeCode;
//    @ApiModelProperty("网络类型")
//    private String networkCodeName;

    @ApiModelProperty("别名")
    private String alias;
    @ApiModelProperty("sip用户名")
    private String deviceIdName;
    @ApiModelProperty("sip用户认证ID")
    private String deviceId;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("是否校验密码，0-否，1-是(默认)")
    private String pwdCheck;
    @ApiModelProperty("设备状态，0-停用，1-启用（默认）")
    private String status;
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    @ApiModelProperty("维度")
    private BigDecimal latitude;
    @ApiModelProperty("地址")
    private String address;

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

    public String getDeviceIdName() {
        return deviceId;
    }

}
