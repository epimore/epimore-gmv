package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.DeviceInfoMapper;
import cn.epimore.gmv.gb28181.service.api.EventApi;
import cn.epimore.gmv.gb28181.service.api.SseApi;
import cn.epimore.gmv.vo.AlarmInfo;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.config.TenantContext;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventApiImpl implements EventApi {
    private final static Logger logger = LoggerFactory.getLogger(EventApiImpl.class);
    @Autowired
    private SseApi sseApi;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    private static final String DEVICE_INFO = "gmv:device_info:";

    @Override
    public void handleAlarmEvent(AlarmInfo info) {
        sseApi.sendDeviceMsg(getTenantId(info.getDeviceId()), info);
    }

    private String getTenantId(String deviceId) {
        Object tenantIdObj = redisTemplate.opsForValue().get(DEVICE_INFO + deviceId);
        int tenantId;
        if (tenantIdObj == null) {
            tenantId = deviceInfoMapper.getTenantIdByDeviceId(deviceId);
            redisTemplate.opsForValue().set(DEVICE_INFO + deviceId, tenantId);
        } else {
            tenantId = Integer.parseInt(tenantIdObj.toString());
        }
        return String.valueOf(tenantId);
    }
}
