package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.service.api.EventApi;
import cn.epimore.gmv.gb28181.service.api.SseApi;
import cn.epimore.gmv.vo.AlarmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventApiImpl implements EventApi {
    @Autowired
    private SseApi sseApi;

    //todo 数据持久化
    @Override
    public void handleAlarmEvent(String uid, AlarmInfo info) {
        sseApi.sendDeviceMsg(uid, info);
    }
}
