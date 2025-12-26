package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.GbDomainInfoApi;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/domain")
@Api(value = "/domain", tags = "SIP域信息")
@Validated
public class GmvDomainController {
    private final static Logger logger = LoggerFactory.getLogger(GmvDomainController.class);
    private final GbDomainInfoApi domainInfoApi;

    @Autowired
    public GmvDomainController(GbDomainInfoApi domainInfoApi) {
        this.domainInfoApi = domainInfoApi;
    }

    @PostMapping("/servers")
    @ApiOperation(value = "servers", notes = "查询服务器域列表")
    public ResponseEntity<PageInfo<GbServerInfo>> queryGbServerInfos(@RequestBody @NotNull GbSessionServerQo info) {
        logger.info("queryGbServerInfos:{}", JSON.toJSONString(info));
        try {
            PageInfo<GbServerInfo> infos = domainInfoApi.queryGbServerInfos(info);
            return Result.success(infos);
        } catch (Exception e) {
            logger.error("查询服务器域列表-异常", e);
            return Result.failure("查询服务器域列表-失败");
        }

    }

    @PostMapping("/device")
    @ApiOperation(value = "device", notes = "查询设备域详情")
    public ResponseEntity<GbDomainInfo> queryGbDomainInfo(@RequestBody @NotNull IdMap map) {
        logger.info("queryGbDomainInfo:{}", JSON.toJSONString(map));
        try {
            GbDomainInfo info = domainInfoApi.queryGbDomainDevice(map.getDeviceId());
            return Result.success(info);
        } catch (Exception e) {
            logger.error("查询设备域详情-异常", e);
            return Result.failure("查询设备域详情-失败");
        }
    }

    @PostMapping("/types")
    @ApiOperation(value = "types", notes = "查询设备域类型")
    public ResponseEntity<GbNetworkDeviceTypeInfo> getGbNetworkDeviceTypeInfo() {
        logger.info("getGbNetworkDeviceTypeInfo");
        try {
            GbNetworkDeviceTypeInfo typeInfo = domainInfoApi.getGbNetworkDeviceTypeInfo();
            return Result.success(typeInfo);
        } catch (Exception e) {
            logger.error("查询设备域类型-异常", e);
            return Result.failure("查询设备域类型-失败");
        }
    }

}
