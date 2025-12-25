package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PlaySpeedModel", description = "倍速历史回放")
@Getter
@Setter
public class PlaySpeedModel {
    @ApiModelProperty
    private String deviceId;
    @ApiModelProperty("流ID")
    private String streamId;
    @ApiModelProperty("倍数：[0.25-8]")
    private double speedRate;

}
