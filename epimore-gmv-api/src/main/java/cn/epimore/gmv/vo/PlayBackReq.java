package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "PlayBackReq", description = "历史监控点播")
public class PlayBackReq extends IdMap {
    @ApiModelProperty("开始时间")
    private LocalDateTime st;
    @ApiModelProperty("结束时间")
    private LocalDateTime et;

    public LocalDateTime getSt() {
        return st;
    }

    public void setSt(LocalDateTime st) {
        this.st = st;
    }

    public LocalDateTime getEt() {
        return et;
    }

    public void setEt(LocalDateTime et) {
        this.et = et;
    }
}
