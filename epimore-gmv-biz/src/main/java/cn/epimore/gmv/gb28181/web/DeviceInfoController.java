package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.DeviceInfoApi;
import cn.epimore.gmv.vo.*;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/info")
@Api(value = "/info", tags = "设备信息")
@Validated
public class DeviceInfoController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);
    private final DeviceInfoApi deviceInfoApi;

    @Autowired
    public DeviceInfoController(DeviceInfoApi deviceInfoApi) {
        this.deviceInfoApi = deviceInfoApi;
    }

    @PostMapping("/devices")
    @ApiOperation(value = "devices", notes = "查询设备列表")
    public ResponseEntity<PageInfo<GmvDeviceInfo>> getGmvDeviceInfoList(@RequestBody @NotNull GmvDeviceInfo info) {
        logger.info("getGmvDeviceInfoList;{}", JSON.toJSONString(info));
        try {
            PageInfo<GmvDeviceInfo> infos = deviceInfoApi.getGmvDeviceInfoList(info);
            return Result.success(infos);
        } catch (Exception e) {
            logger.error("getGmvDeviceInfoList异常", e);
            return Result.failure("查询设备列表失败.");
        }
    }

    @PostMapping("/channels")
    @ApiOperation(value = "channels", notes = "查询相机通道列表")
    public ResponseEntity<List<GmvDeviceChannel>> getGmvDeviceChannelList(@RequestBody @NotNull IdMap idMap) {
        logger.info("getGmvDeviceChannelList,查询相机通道列表;{}", JSON.toJSONString(idMap));
        try {
            List<GmvDeviceChannel> list = deviceInfoApi.getGmvDeviceChannelList(idMap.getDeviceId());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("getGmvDeviceChannelList异常", e);
            return Result.failure("查询相机通道列表失败.");
        }
    }

    @PostMapping("/channel/images")
    @ApiOperation(value = "images", notes = "查询相机抓拍列表")
    public ResponseEntity<PageInfo<ImageInfo>> getImageInfo(@RequestBody @NotNull ImageQuery query) {
        logger.info("getImageInfo,查询相机抓拍列表;{}", JSON.toJSONString(query));
        try {
            PageInfo<ImageInfo> info = deviceInfoApi.getImageInfo(query);
            return Result.success(info);
        } catch (Exception e) {
            logger.error("getImageInfo", e);
            return Result.failure("查询相机抓拍列表失败.");
        }
    }


}
