package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.SseApi;
import cn.epimore.gmv.gb28181.utils.CurrentUserHelper;
import cn.epimore.gmv.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
@Api(value = "/sse", tags = "服务端推送API")
public class SseController {
    private final static Logger logger = LoggerFactory.getLogger(SseController.class);
    @Autowired
    private SseApi sseApi;

    @PostMapping(value = "/connect", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @ApiOperation(value = "connect", notes = "建立连接")
    @CrossOrigin(origins = {"https://epimore.cn", "http://localhost:3100", "http://127.0.0.1:1573"}, allowCredentials = "true")
    public SseEmitter connect() {
        try {
            String username = CurrentUserHelper.getSystemUser().getUsername();
            logger.info("消息推送: 用户:{}，请求建立连接", username);
            return sseApi.connect(username);
        } catch (Exception e) {
            logger.error("消息推送，连接失败:", e);
            return null;
        }
    }

    @PostMapping(value = "/close")
    @ApiOperation(value = "close", notes = "关闭连接")
    public ResponseEntity<Boolean> close() {
        try {
            String username = CurrentUserHelper.getSystemUser().getUsername();
            logger.info("消息推送: 用户:{}，主动断开连接", username);
            sseApi.close(username);
            return Result.success(true);
        } catch (Exception e) {
            logger.error("消息推送，断开连接:", e);
            return Result.failure("关闭连接失败.");
        }
    }
}
