package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PlaySeekModel", description = "拖动历史回放")
@Getter
@Setter
public class PlaySeekModel {
    @ApiModelProperty
    private String deviceId;
    @ApiModelProperty("流ID")
    private String streamId;
    @ApiModelProperty("拖动时间秒[1,86400]")
    private int seekSecond;

}
