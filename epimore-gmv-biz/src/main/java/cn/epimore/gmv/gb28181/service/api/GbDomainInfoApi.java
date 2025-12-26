package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GbDomainInfo;
import cn.epimore.gmv.vo.GbNetworkDeviceTypeInfo;
import cn.epimore.gmv.vo.GbServerInfo;
import cn.epimore.gmv.vo.GbSessionServerQo;
import com.github.pagehelper.PageInfo;

public interface GbDomainInfoApi {
    PageInfo<GbServerInfo> queryGbServerInfos(GbSessionServerQo info);
    GbDomainInfo queryGbDomainDevice(String deviceId);
    GbNetworkDeviceTypeInfo getGbNetworkDeviceTypeInfo();
}
