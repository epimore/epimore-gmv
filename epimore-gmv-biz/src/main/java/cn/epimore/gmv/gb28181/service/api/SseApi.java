package cn.epimore.gmv.gb28181.service.api;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseApi {
    public SseEmitter connect(String uid,String tenantIds);

    public void close(String uid);

    public void sendMsg(String uid, Object msg);
    public void sendDeviceMsg(String tenantId, Object msg);
    public void sendNotifyMsg(String tenantId, Object msg);
}
