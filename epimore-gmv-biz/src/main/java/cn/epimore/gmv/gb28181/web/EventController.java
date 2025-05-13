package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.EventApi;
import cn.epimore.gmv.vo.AlarmInfo;
import cn.epimore.gmv.vo.GmvSessionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Api(value = "/event", tags = "事件API")
public class EventController {
    private final static Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    private EventApi eventApi;

    @PostMapping("/alarm")
    @ApiOperation(value = "alarm", notes = "设备alarm事件")
    public GmvSessionResult<Boolean> alarm(@RequestBody AlarmInfo info) {
        try {
//            logger.info("alarm:{}", JSON.toJSONString(info));
            eventApi.handleAlarmEvent(null, info);
            return new GmvSessionResult<>(true, "success", 200);
        } catch (Exception e) {
            logger.error("alarm event:", e);
            return new GmvSessionResult<>(false, "alarm event error", 500);
        }

    }
}
