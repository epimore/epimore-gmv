package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PlaySpeedModel", description = "倍速历史回放")
public class PlaySpeedModel {
    @ApiModelProperty("流ID")
    private String streamId;
    @ApiModelProperty("倍数：[0.25-8]")
    private double speedRate;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public double getSpeedRate() {
        return speedRate;
    }

    public void setSpeedRate(double speedRate) {
        this.speedRate = speedRate;
    }
}
