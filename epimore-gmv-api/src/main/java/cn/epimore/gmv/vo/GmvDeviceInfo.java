package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class GmvDeviceInfo extends BasePage implements Serializable {

    /**
     * 设备主键ID
     */
    @ApiModelProperty("设备主键ID")
    private String deviceId;
    @ApiModelProperty("别名")
    private String alias;
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
    private LocalDateTime registerTime;
    /**
     * 设备本地地址
     */
    @ApiModelProperty("设备本地地址")
    private String localAddr;
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
    @ApiModelProperty("最后更新时间")
    private Date lastUpdateTime;

    @ApiModelProperty("相机接入总数")
    private Integer cameraInCount;
    @ApiModelProperty("相机离线数量")
    private Integer cameraOffCount;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Integer getRegisterExpires() {
        return registerExpires;
    }

    public void setRegisterExpires(Integer registerExpires) {
        this.registerExpires = registerExpires;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public Integer getMaxCamera() {
        return maxCamera;
    }

    public void setMaxCamera(Integer maxCamera) {
        this.maxCamera = maxCamera;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGbVersion() {
        return gbVersion;
    }

    public void setGbVersion(String gbVersion) {
        this.gbVersion = gbVersion;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getCameraInCount() {
        return cameraInCount;
    }

    public void setCameraInCount(Integer cameraInCount) {
        this.cameraInCount = cameraInCount;
    }

    public Integer getCameraOffCount() {
        return cameraOffCount;
    }

    public void setCameraOffCount(Integer cameraOffCount) {
        this.cameraOffCount = cameraOffCount;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
