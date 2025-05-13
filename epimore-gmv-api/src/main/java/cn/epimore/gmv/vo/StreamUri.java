package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StreamUri", description = "播放流地址")
public class StreamUri {
    @ApiModelProperty("流ID")
    private String streamId;
    @ApiModelProperty("flv流地址")
    private String flv;
    @ApiModelProperty("m3u8流地址")
    private String m3u8;

    public StreamUri() {
    }

    public StreamUri(String streamId, String flv, String m3u8) {
        this.streamId = streamId;
        this.flv = flv;
        this.m3u8 = m3u8;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getFlv() {
        return flv;
    }

    public void setFlv(String flv) {
        this.flv = flv;
    }

    public String getM3u8() {
        return m3u8;
    }

    public void setM3u8(String m3u8) {
        this.m3u8 = m3u8;
    }
}
