package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.AlarmInfo;

public interface EventApi {
    public void handleAlarmEvent(String uid, AlarmInfo info);
}
