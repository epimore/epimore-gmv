package cn.epimore.gmv.gb28181.service.api;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseApi {
    public SseEmitter connect(String uid);

    public void close(String uid);

    public void sendMsg(String uid, Object msg);
    public void sendDeviceMsg(String uid, Object msg);
    public void sendNotifyMsg(String uid, Object msg);
}
