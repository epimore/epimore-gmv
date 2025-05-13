package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.DcOptApi;
import cn.epimore.gmv.vo.*;
import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/opt")
@Api(value = "/stream", tags = "媒体流信息：无需关闭流,流未被使用时会自动关闭")
@Validated
public class DcOptController {
    private final static Logger logger = LoggerFactory.getLogger(DcOptController.class);
    @Autowired
    private DcOptApi dcOptApi;

    @PostMapping("/play/live")
    @ApiOperation(value = "/play/live", notes = "获取实时直播地址")
    public ResponseEntity<StreamUri> getPlayLiveUri(@RequestBody @NotNull IdMap idMap) {
        logger.info("getPlayLiveUri:{}", JSON.toJSONString(idMap));
        try {
            StreamUri streamUri = dcOptApi.getPlayLiveUri(idMap);
            return Result.success(streamUri);
        } catch (Exception e) {
            logger.error("getPlayLiveUri异常", e);
            return Result.failure("获取实时直播地址失败.");
        }
    }

    @PostMapping("/play/back")
    @ApiOperation(value = "/play/back", notes = "获取历史回放地址")
    public ResponseEntity<StreamUri> getPlayBackUri(@RequestBody @NotNull PlayBackReq backReq) {
        logger.info("getPlayBackUri:{}", JSON.toJSONString(backReq));
        try {
//            StreamUri streamUri = new StreamUri("streamId", "https://v-ngb.qf.56.com/live/3434287_1741004753492.flv", null);

            StreamUri streamUri = dcOptApi.getPlayBackUri(backReq);
            return Result.success(streamUri);
        } catch (Exception e) {
            logger.error("getPlayBackUri", e);
            return Result.failure("获取历史回放地址失败.");
        }
    }

    @PostMapping("/play/back/seek")
    @ApiOperation(value = "/play/back/seek", notes = "操作历史监控拖动播放")
    public ResponseEntity<Boolean> cmdPlayBackSeek(@RequestBody @NotNull PlaySeekModel model) {
        logger.info("cmdPlayBackSeek:{}", JSON.toJSONString(model));
        try {
            boolean b = dcOptApi.cmdPlayBackSeek(model);
            return Result.success(b);
        } catch (Exception e) {
            logger.error("cmdPlayBackSeek", e);
            return Result.failure("操作历史监控拖动播放失败.");
        }
    }

    @PostMapping("/play/back/speed")
    @ApiOperation(value = "/play/back/speed", notes = "操作历史监控倍速播放")
    public ResponseEntity<Boolean> cmdPlayBackSpeed(@RequestBody @NotNull PlaySpeedModel model) {
        logger.info("cmdPlayBackSpeed:{}", JSON.toJSONString(model));
        try {
            boolean b = dcOptApi.cmdPlayBackSpeed(model);
            return Result.success(b);
        } catch (Exception e) {
            logger.error("cmdPlayBackSpeed", e);
            return Result.failure("操作历史监控倍速播放失败.");
        }
    }

    @PostMapping("/control/ptz")
    @ApiOperation(value = "/control/ptz", notes = "云台控制")
    public ResponseEntity<Boolean> cmdControlPtz(@RequestBody @NotNull PtzControlModel model) {
        logger.info("cmdControlPtz:{}", JSON.toJSONString(model));
        try {
            boolean b = dcOptApi.cmdControlPtz(model);
            return Result.success(b);
        } catch (Exception e) {
            logger.error("cmdControlPtz", e);
            return Result.failure("云台控制失败.");
        }
    }

    @PostMapping("/create/down")
    @ApiOperation(value = "/create/create", notes = "创建云端录像")
    public ResponseEntity<Boolean> createDownloadTask(@RequestBody @NotNull PlayBackReq backReq) {
        logger.info("createDownloadTask:{}", JSON.toJSONString(backReq));
        try {
            boolean b = dcOptApi.createDownloadTask(backReq);
            return Result.success(b);
        } catch (Exception e) {
            logger.error("createDownloadTask", e);
            return Result.failure("创建云端录像失败.");
        }
    }

    @PostMapping("/down/info")
    @ApiOperation(value = "/down/info", notes = "获取下载信息")
    public ResponseEntity<List<RecordVideoInfo>> downTaskInfo(@RequestBody @NotNull IdMap idMap) {
        logger.info("downTaskInfo:{}", JSON.toJSONString(idMap));
        try {
            List<RecordVideoInfo> infos = dcOptApi.downTaskInfo(idMap);
            return Result.success(infos);
        } catch (Exception e) {
            logger.error("downTaskInfo", e);
            return Result.failure("获取下载信息失败.");
        }
    }

    @PostMapping("/teardown/task")
    @ApiOperation(value = "/teardown/task", notes = "结束下载")
    public ResponseEntity<Boolean> tearDownTask(@RequestBody @NotNull SingleParamModel<String> req) {
        logger.info("tearDownTask:{}", JSON.toJSONString(req));
        try {
            boolean b = dcOptApi.tearDownTask(req.getParam());
            return Result.success(b);
        } catch (Exception e) {
            logger.error("tearDownTask", e);
            return Result.failure("结束下载失败.");
        }
    }

    @PostMapping("/rm/file")
    @ApiOperation(value = "/rm/file", notes = "物联删除文件")
    public ResponseEntity<Boolean> rmFile(@RequestBody @NotNull SingleParamModel<Long> req) {
        logger.info("rmFile:{}", JSON.toJSONString(req));
        try {
            boolean b = dcOptApi.rmFile(req.getParam());
            return Result.success(b);
        } catch (Exception e) {
            logger.error("rmFile", e);
            return Result.failure("物联删除文件失败.");
        }
    }

    @PostMapping("/download/file")
    @ApiOperation(value = "/download/file", notes = "下载文件")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileId") Long fileId) {
        logger.info("downloadFile:{}", fileId);
        try {
            return dcOptApi.downloadFile(fileId);
        } catch (Exception e) {
            logger.error("downloadFile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
