package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GbDomainInfo;
import cn.epimore.gmv.vo.GbServerInfo;
import cn.epimore.gmv.vo.GbNetworkDeviceTypeInfo;

import java.util.List;

public interface GbDomainInfoApi {
    List<GbServerInfo> queryGbServerInfos();
    GbDomainInfo queryGbDomainInfo(String deviceId);
    GbNetworkDeviceTypeInfo getGbNetworkDeviceTypeInfo();
}
