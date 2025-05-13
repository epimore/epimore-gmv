package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@ApiModel(description = "云台控制模型（Ptz Control Model）")
public class PtzControlModel {

    @ApiModelProperty(value = "设备 ID，固定长度 20", required = true)
    @Size(min = 20, max = 20, message = "设备 ID 必须是 20 位")
    private String deviceId;

    @ApiModelProperty(value = "通道 ID，固定长度 20", required = true)
    @Size(min = 20, max = 20, message = "通道 ID 必须是 20 位")
    private String channelId;

    @ApiModelProperty(value = "镜头左移右移 (0: 停止, 1: 左移, 2: 右移)")
    @Min(0)
    @Max(2)
    private int leftRight;

    @ApiModelProperty(value = "镜头上移下移 (0: 停止, 1: 上移, 2: 下移)")
    @Min(0)
    @Max(2)
    private int upDown;

    @ApiModelProperty(value = "镜头放大缩小 (0: 停止, 1: 缩小, 2: 放大)")
    @Min(0)
    @Max(2)
    private int inOut;

    @ApiModelProperty(value = "水平移动速度 (0-255)")
    @Min(0)
    @Max(255)
    private int horizonSpeed;

    @ApiModelProperty(value = "垂直移动速度 (0-255)")
    @Min(0)
    @Max(255)
    private int verticalSpeed;

    @ApiModelProperty(value = "焦距缩放速度 (0-15)")
    @Min(0)
    @Max(15)
    private int zoomSpeed;

    // Getters and Setters
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

    public int getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    public int getUpDown() {
        return upDown;
    }

    public void setUpDown(int upDown) {
        this.upDown = upDown;
    }

    public int getInOut() {
        return inOut;
    }

    public void setInOut(int inOut) {
        this.inOut = inOut;
    }

    public int getHorizonSpeed() {
        return horizonSpeed;
    }

    public void setHorizonSpeed(int horizonSpeed) {
        this.horizonSpeed = horizonSpeed;
    }

    public int getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(int verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public int getZoomSpeed() {
        return zoomSpeed;
    }

    public void setZoomSpeed(int zoomSpeed) {
        this.zoomSpeed = zoomSpeed;
    }
}

