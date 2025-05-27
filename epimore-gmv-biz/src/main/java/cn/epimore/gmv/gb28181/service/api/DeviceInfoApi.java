package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GmvDeviceChannel;
import cn.epimore.gmv.vo.GmvDeviceInfo;
import cn.epimore.gmv.vo.ImageInfo;
import cn.epimore.gmv.vo.ImageQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeviceInfoApi {
    PageInfo<GmvDeviceInfo> getGmvDeviceInfoList(GmvDeviceInfo info);
    List<GmvDeviceChannel> getGmvDeviceChannelList(String deviceId);
    PageInfo<ImageInfo> getImageInfo(ImageQuery query);
}
