package cn.epimore.gmv.vo;

import cn.epimore.gmv.ser.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "PlayBackReq", description = "历史监控点播")
public class PlayBackReq extends IdMap {
    @ApiModelProperty("开始时间")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime st;
    @ApiModelProperty("结束时间")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
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
