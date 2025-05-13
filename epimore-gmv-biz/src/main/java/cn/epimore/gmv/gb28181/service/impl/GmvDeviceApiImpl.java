package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.GmvDeviceMapper;
import cn.epimore.gmv.gb28181.service.api.GmvDeviceApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GmvDeviceApiImpl implements GmvDeviceApi {
    @Resource
    private GmvDeviceMapper gmvDeviceMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gmvDeviceMapper.deleteByPrimaryKey(id);
    }
}
