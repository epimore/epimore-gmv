package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "RecordVideoInfo", description = "录像信息")
public class RecordVideoInfo {
    @ApiModelProperty("文件ID")
    private String fileId;
    @ApiModelProperty("设备ID")
    private String deviceId;
    @ApiModelProperty("通道ID")
    private String channelId;
    @ApiModelProperty("业务ID")
    private String bizId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("录像开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("录像结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("业务生成时间")
    private LocalDateTime bizTime;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("录像地址")
    private String videoUrl;
    @ApiModelProperty("状态")
    private int state;
    @ApiModelProperty("文件大小")
    private int size;
    @ApiModelProperty("文件大小")
    private String sizeStr;
    @ApiModelProperty("状态描述")
    private String stateStr;
    @ApiModelProperty("节点")
    private String nodeName;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeStr() {
        return sizeStr;
    }

    public void setSizeStr(String sizeStr) {
        this.sizeStr = sizeStr;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public LocalDateTime getBizTime() {
        return bizTime;
    }

    public void setBizTime(LocalDateTime bizTime) {
        this.bizTime = bizTime;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
