package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 设备主表
 * @TableName GMV_DEVICE
 */
@ApiModel(value = "GmvDevice", description = "设备主表")
@Getter
@Setter
public class GmvDevice implements Serializable {
    /**
     * 设备主键ID
     */


    @ApiModelProperty("设备主键ID")

    private String deviceId;
    /**
     * 网络协议：TCP/UDP
     */

    @ApiModelProperty("网络协议：TCP/UDP")

    private String transport;
    /**
     * 注册有效期
     */
    @ApiModelProperty("注册有效期")
    private Integer registerExpires;
    /**
     * 最近注册时间
     */
    @ApiModelProperty("最近注册时间")
    private Long registerTime;
    /**
     * 设备本地地址
     */

    @ApiModelProperty("设备本地地址")

    private String localAddr;
    /**
     * 请求发送者
     */

    @ApiModelProperty("请求发送者")

    private String sipFrom;
    /**
     * 请求接收者
     */

    @ApiModelProperty("请求接收者")

    private String sipTo;
    /**
     * 设备类型IPC/NVR/DVR...
     */

    @ApiModelProperty("设备类型IPC/NVR/DVR...")

    private String deviceType;
    /**
     * 厂家名称
     */

    @ApiModelProperty("厂家名称")

    private String manufacturer;
    /**
     * 设备型号
     */

    @ApiModelProperty("设备型号")

    private String model;
    /**
     * 固件版本
     */

    @ApiModelProperty("固件版本")

    private String firmware;
    /**
     * 最大相机数
     */
    @ApiModelProperty("最大相机数")
    private Integer maxCamera;
    /**
     * 0-离线，1-在线
     */
    @ApiModelProperty("0-离线，1-在线")
    private Integer status;
    /**
     * 国标版本
     */

    @ApiModelProperty("国标版本")

    private String gbVersion;
    /**
     * 最后更新时间
     */
    @ApiModelProperty("设备注册最后更新时间")
    private Date lastUpdateTime;

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
