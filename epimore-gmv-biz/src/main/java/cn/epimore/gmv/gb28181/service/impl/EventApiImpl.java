package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.DeviceInfoMapper;
import cn.epimore.gmv.gb28181.service.api.EventApi;
import cn.epimore.gmv.gb28181.service.api.SseApi;
import cn.epimore.gmv.vo.AlarmInfo;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.config.TenantContext;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventApiImpl implements EventApi {
    @Autowired
    private SseApi sseApi;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    private static final String DEVICE_INFO = "gmv:device_info:";

    //todo 数据持久化
    @Override
    public void handleAlarmEvent(String uid, AlarmInfo info) {
        if (checkTenantId(info.getDeviceId())) {
            sseApi.sendDeviceMsg(uid, info);
        }
    }

    private boolean checkTenantId(String deviceId) {
        Object tenantIdObj = redisTemplate.opsForValue().get(DEVICE_INFO + deviceId);
        int tenantId;
        if (tenantIdObj == null) {
            tenantId = deviceInfoMapper.getTenantIdByDeviceId(deviceId);
            redisTemplate.opsForValue().set(DEVICE_INFO + deviceId, tenantId);
        } else {
            tenantId = Integer.parseInt(tenantIdObj.toString());
        }

        String c_tenantId = TenantContext.getTenant();
        //如果通过线程获取租户ID为空，则通过当前请求的request获取租户（shiro排除拦截器的请求会获取不到租户ID）
        if (oConvertUtils.isEmpty(c_tenantId)) {
            try {
                c_tenantId = TokenUtils.getTenantIdByRequest(SpringContextUtils.getHttpServletRequest());
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        if (oConvertUtils.isEmpty(c_tenantId)) {
            c_tenantId = "0";
        }
        return StringUtils.equals(c_tenantId, String.valueOf(tenantId));
    }
}
