package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class ImageQuery extends BasePage {
    @ApiModelProperty("设备ID")
    private String deviceId;
    @ApiModelProperty("通道ID")
    private String channelId;
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
