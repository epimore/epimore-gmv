package cn.epimore.gmv.gb28181.web;

import cn.epimore.gmv.gb28181.service.api.GmvOauthApi;
import cn.epimore.gmv.vo.GmvOauth;
import cn.epimore.gmv.vo.IdMap;
import cn.epimore.gmv.vo.Result;
import com.alibaba.fastjson2.JSON;
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
@RequestMapping("/enrolls")
@Api(value = "/enrolls", tags = "设备注册管理")
@Validated
public class GmvEnrollsController {
    private final static Logger logger = LoggerFactory.getLogger(GmvEnrollsController.class);
    private final GmvOauthApi gmvOauthApi;

    @Autowired
    public GmvEnrollsController(GmvOauthApi gmvOauthApi) {
        this.gmvOauthApi = gmvOauthApi;
    }

    @PostMapping("/rm")
    @ApiOperation(value = "rm", notes = "删除设备")
    public ResponseEntity<String> deleteByPrimaryKey(@RequestBody @NotNull IdMap idMap) {
        logger.info("deleteByPrimaryKey:{}", JSON.toJSONString(idMap));
        try {
            int i = gmvOauthApi.deleteByPrimaryKey(idMap.getDeviceId());
            return Result.build(i > 0);
        } catch (Exception e) {
            logger.error("删除设备异常", e);
            return Result.failure("删除失败");
        }

    }

    @PostMapping("/add")
    @ApiOperation(value = "add", notes = "添加设备")
    public ResponseEntity<String> insert(@RequestBody @NotNull GmvOauth record) {
        logger.info("insert:{}", JSON.toJSONString(record));
        try {
            int i = gmvOauthApi.insert(record);
            return Result.build(i > 0);
        } catch (Exception e) {
            logger.error("添加设备异常", e);
            return Result.failure("添加失败");
        }
    }

    @PostMapping("/modify")
    @ApiOperation(value = "modify", notes = "修改设备")
    public ResponseEntity<String> updateByPrimaryKeySelective(@RequestBody @NotNull GmvOauth record) {
        logger.info("updateByPrimaryKeySelective:{}", JSON.toJSONString(record));
        try {
            int i = gmvOauthApi.updateByPrimaryKeySelective(record);
            return Result.build(i > 0);
        } catch (Exception e) {
            logger.error("修改设备异常", e);
            return Result.failure("修改失败");
        }
    }

    @PostMapping("/query")
    @ApiOperation(value = "query", notes = "查询设备")
    public ResponseEntity<List<GmvOauth>> getGmvOauthList(@RequestBody @NotNull GmvOauth record) {
        logger.info("getGmvOauthList:{}", JSON.toJSONString(record));
        try {
            List<GmvOauth> list = gmvOauthApi.getGmvOauthList(record);
            return Result.success(list);
        } catch (Exception e) {
            logger.error("查询设备异常", e);
            return Result.failure("查询失败");
        }
    }
}
