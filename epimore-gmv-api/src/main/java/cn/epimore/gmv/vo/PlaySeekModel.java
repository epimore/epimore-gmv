package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PlaySeekModel", description = "拖动历史回放")
public class PlaySeekModel {
    @ApiModelProperty("流ID")
    private String streamId;
    @ApiModelProperty("拖动时间秒[1,86400]")
    private int seekSecond;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public int getSeekSecond() {
        return seekSecond;
    }

    public void setSeekSecond(int seekSecond) {
        this.seekSecond = seekSecond;
    }
}
