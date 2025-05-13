package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.DeviceInfoMapper;
import cn.epimore.gmv.gb28181.service.api.DeviceInfoApi;
import cn.epimore.gmv.vo.GmvDeviceChannel;
import cn.epimore.gmv.vo.GmvDeviceInfo;
import cn.epimore.gmv.vo.ImageInfo;
import cn.epimore.gmv.vo.ImageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceInfoApiImpl implements DeviceInfoApi {
    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public List<GmvDeviceInfo> getGmvDeviceInfoList(GmvDeviceInfo info) {
        return deviceInfoMapper.getGmvDeviceInfoList(info);
    }

    @Override
    public List<GmvDeviceChannel> getGmvDeviceChannelList(String deviceId) {
        return deviceInfoMapper.getGmvDeviceChannelList(deviceId);
    }

    @Override
    public PageInfo<ImageInfo> getImageInfo(ImageQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ImageInfo> list = deviceInfoMapper.getImageInfo(query);
        return new PageInfo<>(list);
    }
}
