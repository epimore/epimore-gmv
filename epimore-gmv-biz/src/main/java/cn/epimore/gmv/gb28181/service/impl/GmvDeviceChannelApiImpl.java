package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.GmvDeviceChannelMapper;
import cn.epimore.gmv.gb28181.service.api.GmvDeviceChannelApi;
import cn.epimore.gmv.vo.IdMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GmvDeviceChannelApiImpl implements GmvDeviceChannelApi {
@Resource
private GmvDeviceChannelMapper channelMapper;

    @Override
    public int deleteByPrimaryKey(IdMap idMap) {
        return channelMapper.deleteByPrimaryKey(idMap);
    }
}
