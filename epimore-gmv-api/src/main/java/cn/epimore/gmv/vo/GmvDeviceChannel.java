package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 摄像机通道信息
 * @TableName GMV_DEVICE_CHANNEL
 */
public class GmvDeviceChannel implements Serializable {
    /**
     * 设备ID
     */
    @ApiModelProperty("设备ID")
    private String deviceId;
    /**
     * 通道ID
     */
    @ApiModelProperty("通道ID")
    private String channelId;
    /**
     * 设备名称
     */
    @ApiModelProperty("设备名称")
    private String name;
    /**
     * 设备厂商
     */
    @ApiModelProperty("设备厂商")
    private String manufacturer;
    /**
     * 设备型号
     */
    @ApiModelProperty("设备型号")
    private String model;
    /**
     * 设备归属
     */
    @ApiModelProperty("设备归属")
    private String owner;
    @ApiModelProperty("设备状态ON/OFF")
    private String status;
    /**
     * 行政区域
     */
    @ApiModelProperty("行政区域")
    private String civilCode;
    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    private String address;
    /**
     * 是否有子设备 1 有， 0 没有
     */
    @ApiModelProperty("是否有子设备 1 有， 0 没有")
    private String parental;
    /**
     * 警区
     */
    @ApiModelProperty("警区")
    private String block;
    /**
     * 父设备/区域/系统 ID
     */
    @ApiModelProperty("父设备/区域/系统 ID")
    private String parentId;
    /**
     * 设备/区域/系统 IP 地址
     */
    @ApiModelProperty("设备/区域/系统 IP 地址")
    private String ipAddress;
    /**
     * 设备/区域/系统端口
     */
    @ApiModelProperty("设备/区域/系统端口")
    private Integer port;
    /**
     * 设备口令
     */
    @ApiModelProperty("设备口令")
    private String password;
    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private BigDecimal latitude;
    /**
     * 摄像机类型扩展，标识摄像机类型： 1-球机； 2-半球； 3-固定枪机；4-遥控枪机,5遥控半球，6多目设备拼接通道，7多目设备分割通道。
     */
    @ApiModelProperty("摄像机类型扩展，标识摄像机类型： 1-球机； 2-半球； 3-固定枪机；4-遥控枪机,5遥控半球，6多目设备拼接通道，7多目设备分割通道。")
    private Integer ptzType;
    /**
     * 摄像机类型扩展，标识摄像机类型： 1-球机； 2-半球； 3-固定枪机；4-遥控枪机,5遥控半球，6多目设备拼接通道，7多目设备分割通道。
     */
    @ApiModelProperty("摄像机类型扩展，标识摄像机类型： 1-球机； 2-半球； 3-固定枪机；4-遥控枪机,5遥控半球，6多目设备拼接通道，7多目设备分割通道。")
    private String ptzTypeStr;
    /**
     * 摄像机补光属性。 1-无补光、 2-红外补光、 3-白光补光。
     */
    @ApiModelProperty("摄像机补光属性。 1-无补光、 2-红外补光、 3-白光补光。")
    private String supplyLightType;
    /**
     * 设备别名
     */
    @ApiModelProperty("设备别名")
    private String aliasName;
    @ApiModelProperty("设备图片")
    private String picUrl;
    @ApiModelProperty("是否启用拍照：0-否，1-是，2-设备不支持；默认1")
    private Integer snapshot;

    public Integer getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Integer snapshot) {
        this.snapshot = snapshot;
    }

    public String getPicUrl() {
        if (StringUtils.isNotEmpty(picUrl) && picUrl.startsWith("./")) {
            return picUrl.substring(1);
        }
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCivilCode() {
        return civilCode;
    }

    public void setCivilCode(String civilCode) {
        this.civilCode = civilCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParental() {
        return parental;
    }

    public void setParental(String parental) {
        this.parental = parental;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getPtzType() {
        return ptzType;
    }

    public void setPtzType(Integer ptzType) {
        this.ptzType = ptzType;
    }

    public String getSupplyLightType() {
        return supplyLightType;
    }

    public void setSupplyLightType(String supplyLightType) {
        this.supplyLightType = supplyLightType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getPtzTypeStr() {
        String ptzTypeStr = "";
        if (this.ptzType == null) {
            return ptzTypeStr;
        }
        switch (this.ptzType) {
            case 1:
                ptzTypeStr = "球机";
                break;
            case 2:
                ptzTypeStr = "半球";
                break;
            case 3:
                ptzTypeStr = "固定枪机";
                break;
            case 4:
                ptzTypeStr = "遥控枪机";
                break;
            case 5:
                ptzTypeStr = "遥控半球";
                break;
            case 6:
                ptzTypeStr = "多目设备拼接通道";
                break;
            case 7:
                ptzTypeStr = "多目设备分割通道";
                break;
        }

        return ptzTypeStr;
    }
}
