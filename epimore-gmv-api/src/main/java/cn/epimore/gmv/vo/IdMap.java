package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IdMap", description = "设备ID")
public class IdMap {
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
}
