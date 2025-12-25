package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GbServerInfo extends GbServerVo{
    @ApiModelProperty("服务器域ID")
    private String areaName;
    @ApiModelProperty("行业接入")
    private String industryName;

}
