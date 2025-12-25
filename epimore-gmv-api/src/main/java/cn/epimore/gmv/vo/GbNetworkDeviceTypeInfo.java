package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GbNetworkDeviceTypeInfo {
    @ApiModelProperty("网络类型")
    private List<GmvEnumCodeVo> networkTypes;
    @ApiModelProperty("设备类型")
    private List<GmvTreeEnumCodeVo> deviceTypes;

    public GbNetworkDeviceTypeInfo() {
    }

    public GbNetworkDeviceTypeInfo(List<GmvEnumCodeVo> networkTypes, List<GmvTreeEnumCodeVo> deviceTypes) {
        this.networkTypes = networkTypes;
        this.deviceTypes = deviceTypes;
    }
}
