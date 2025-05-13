package cn.epimore.gmv.gb28181.mapper;


import cn.epimore.gmv.vo.*;

import java.util.List;

public interface DeviceInfoMapper {

    List<GmvDeviceInfo> getGmvDeviceInfoList(GmvDeviceInfo info);

    List<GmvDeviceChannel> getGmvDeviceChannelList(String deviceId);

    List<ImageInfo> getImageInfo(ImageQuery query);

    List<RecordVideoInfo> getRecordVideoInfos(IdMap idMap);

    int getRecordingCount(PlayBackReq req);

    String getFilePath(Long fileId);
}
