package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.cons.ApiPathConstants;
import cn.epimore.gmv.gb28181.mapper.DeviceInfoMapper;
import cn.epimore.gmv.gb28181.mapper.GbDomainInfoMapper;
import cn.epimore.gmv.gb28181.mapper.GmvDeviceChannelMapper;
import cn.epimore.gmv.gb28181.service.api.DcOptApi;
import cn.epimore.gmv.gb28181.utils.DateTimeUtil;
import cn.epimore.gmv.gb28181.utils.GmvHttpUtil;
import cn.epimore.gmv.vo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DcOptApiImpl implements DcOptApi {
    private final static Logger logger = LoggerFactory.getLogger(DcOptApiImpl.class);

    private final DeviceInfoMapper deviceInfoMapper;
    private final GmvDeviceChannelMapper gmvDeviceChannelMapper;
    private final GbDomainInfoMapper gbDomainInfoMapper;

    @Autowired
    public DcOptApiImpl(DeviceInfoMapper deviceInfoMapper,
                        GmvDeviceChannelMapper gmvDeviceChannelMapper, GbDomainInfoMapper gbDomainInfoMapper) {
        this.deviceInfoMapper = deviceInfoMapper;
        this.gmvDeviceChannelMapper = gmvDeviceChannelMapper;
        this.gbDomainInfoMapper = gbDomainInfoMapper;
    }

    private String getSessionSourceUrl(String deviceId) {
        SessionSourceVo sourceVo = gbDomainInfoMapper.getSessionSourceVo(deviceId);
        if (sourceVo == null) {
            throw new RuntimeException("设备未知或已注销");
        }
        if (sourceVo.getDeviceStatus()==0){
            throw new RuntimeException("设备已禁用");
        }
        if (sourceVo.getServerStatus()==0){
            throw new RuntimeException("信令服务已禁用");
        }
        return sourceVo.getHttpSource();
    }

    @Override
    public StreamUri getPlayLiveUri(IdMap idMap) {
        String url = String.format("%s%s", getSessionSourceUrl(idMap.getDeviceId()), ApiPathConstants.PLAY_LIVE);
        Map<String, String> map = new HashMap<>();
        map.put("device_id", idMap.getDeviceId());
        map.put("channel_id", idMap.getChannelId());
        GmvSessionResult<StreamUri> result = GmvHttpUtil.post(url, map, StreamUri.class);
        if (result == null) {
            throw new RuntimeException("获取实时直播地址失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return result.getData();
    }

    @Override
    public StreamUri getPlayBackUri(PlayBackReq backReq) {
        String url = String.format("%s%s", getSessionSourceUrl(backReq.getDeviceId()), ApiPathConstants.PLAY_BACK);
        Map<String, Object> map = new HashMap<>();
        map.put("device_id", backReq.getDeviceId());
        map.put("channel_id", backReq.getChannelId());
        ZoneId zoneId = ZoneId.systemDefault();
        Long st = backReq.getSt().atZone(zoneId).toInstant().getEpochSecond();
        Long et = backReq.getEt().atZone(zoneId).toInstant().getEpochSecond();
        map.put("st", st);
        map.put("et", et);
        GmvSessionResult<StreamUri> result = GmvHttpUtil.post(url, map, StreamUri.class);
        if (result == null) {
            throw new RuntimeException("获取历史回放地址失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return result.getData();
    }

    @Override
    public boolean cmdPlayBackSeek(PlaySeekModel seekModel) {
        String url = String.format("%s%s", getSessionSourceUrl(seekModel.getDeviceId()), ApiPathConstants.PLAY_BACK_SEEK);
        Map<String, Object> map = new HashMap<>();
        map.put("streamId", seekModel.getStreamId());
        map.put("seekSecond", seekModel.getSeekSecond());
        GmvSessionResult<Boolean> result = GmvHttpUtil.post(url, map, Boolean.class);
        if (result == null) {
            throw new RuntimeException("拖动操作失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return result.getData();
    }

    @Override
    public boolean cmdPlayBackSpeed(PlaySpeedModel speedModel) {
        String url = String.format("%s%s", getSessionSourceUrl(speedModel.getDeviceId()), ApiPathConstants.PLAY_BACK_SPEED);
        Map<String, Object> map = new HashMap<>();
        map.put("streamId", speedModel.getStreamId());
        map.put("speedRate", speedModel.getSpeedRate());
        GmvSessionResult<Boolean> result = GmvHttpUtil.post(url, map, Boolean.class);
        if (result == null) {
            throw new RuntimeException("倍速操作失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return result.getData();
    }

    @Override
    public boolean cmdControlPtz(PtzControlModel ptzControlModel) {
        String url = String.format("%s%s", getSessionSourceUrl(ptzControlModel.getDeviceId()), ApiPathConstants.PTZ);
        GmvSessionResult<Boolean> result = GmvHttpUtil.post(url, ptzControlModel, Boolean.class);
        if (result == null) {
            throw new RuntimeException("云台控制失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return result.getData();
    }

    @Override
    public boolean createDownloadTask(PlayBackReq backReq) {
        ZoneId zoneId = ZoneId.systemDefault();
        Long st = backReq.getSt().atZone(zoneId).toInstant().getEpochSecond();
        Long et = backReq.getEt().atZone(zoneId).toInstant().getEpochSecond();
        if (st >= et) {
            throw new RuntimeException("开始时间大于结束时间");
        }
        if (st < 0) {
            throw new RuntimeException("开始时间或结束时间小于0");
        }
        if (et - st > 2 * 60 * 60) {
            throw new RuntimeException("录制时间不能大于2小时");
        }
        int count = deviceInfoMapper.getRecordingCount(backReq);
        if (count > 0) {
            throw new RuntimeException("该设备已存在下载任务");
        }
        String url = String.format("%s%s", getSessionSourceUrl(backReq.getDeviceId()), ApiPathConstants.DOWNLOAD);
        Map<String, Object> map = new HashMap<>();
        map.put("device_id", backReq.getDeviceId());
        map.put("channel_id", backReq.getChannelId());
        map.put("st", st);
        map.put("et", et);
        GmvSessionResult<String> result = GmvHttpUtil.post(url, map, String.class);
        if (result == null) {
            throw new RuntimeException("创建下载任务失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return true;
    }

    @Override
    public boolean tearDownTask(PairParamModel<String,String> req) {
        String url = String.format("%s%s", getSessionSourceUrl(req.getParam1()), ApiPathConstants.TEARDOWN);
        Map<String, Object> map = new HashMap<>();
        map.put("param", req.getParam2());
        GmvSessionResult<Boolean> result = GmvHttpUtil.post(url, map, Boolean.class);
        if (result == null) {
            throw new RuntimeException("停止失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return true;
    }

    @Override
    public List<RecordVideoInfo> downTaskInfo(IdMap idMap) {
        List<RecordVideoInfo> infos = deviceInfoMapper.getRecordVideoInfos(idMap);
        if (CollectionUtils.isNotEmpty(infos)) {
            for (RecordVideoInfo info : infos) {
                long st = DateTimeUtil.toTimestampSeconds(info.getStartTime());
                long et = DateTimeUtil.toTimestampSeconds(info.getEndTime());
                if (info.getState() == 0 && StringUtils.isNotEmpty(info.getBizId())) {
                    String url = String.format("%s%s", getSessionSourceUrl(idMap.getDeviceId()), ApiPathConstants.DOWNING);
                    Map<String, Object> map = new HashMap<>();
                    map.put("stream_id", info.getBizId());
//                    map.put("stream_server", info.getNodeName());
                    GmvSessionResult<RecordingInfo> result = GmvHttpUtil.post(url, map, RecordingInfo.class);
                    if (result == null) {
                        info.setStateStr("0.00|%");
                        logger.info("查询下载任务失败");
                        continue;
                    }
                    if (result.getCode() != 200) {
                        info.setStateStr("0.00|%");
                        logger.info("查询下载任务失败,{}", result.getMsg());
                        continue;
                    }
                    RecordingInfo data = result.getData();
                    info.setSizeStr(buildFileSize(data.getFileSize()));
//                    //保留两位小数，装换为百分比字符串
                    double ratio = (double) data.getTimestamp() / (et - st);
                    if (ratio > 0.9728) {
                        info.setStateStr("97.28|%");
                    } else {
                        String percentStr = String.format("%.2f|%%", ratio * 100);
                        info.setStateStr(percentStr);
                    }
                } else {
                    switch (info.getState()) {
                        case 1:
                            info.setStateStr("100.00|%");
                            info.setSizeStr(buildFileSize(info.getSize()));
                            break;
                        case 2:
                            long bt = DateTimeUtil.toTimestampSeconds(info.getBizTime());
                            long ct = DateTimeUtil.toTimestampSeconds(info.getCreateTime());
                            double ratio = (double) (bt - ct) / (et - st);
                            if (ratio>1.0){
                                ratio=1.0;
                            }
                            String percentStr = String.format("%.2f|%%", ratio * 100);
                            info.setStateStr(percentStr);
                            info.setSizeStr(buildFileSize(info.getSize()));
                            break;
                        case 3:
                            info.setStateStr("0.00|%");
                            break;
                    }
                }
            }
        }
        return infos;
    }

    @Override
    public String snapshotImage(IdMap idMap) {
        String url = String.format("%s%s", getSessionSourceUrl(idMap.getDeviceId()), ApiPathConstants.SNAPSHOT_IMAGE);
        Map<String, String> device_channel_ident = new HashMap<>();
        device_channel_ident.put("device_id", idMap.getDeviceId());
        device_channel_ident.put("channel_id", idMap.getChannelId());
        Map<String, Object> map = new HashMap<>();
        map.put("device_channel_ident", device_channel_ident);
        map.put("count", 1);
        GmvSessionResult<String> result = GmvHttpUtil.post(url, map, String.class);
        if (result == null) {
            throw new RuntimeException("采集画面快照失败");
        }
        if (result.getCode() != 200) {
            GmvDeviceChannel gmvDeviceChannel = new GmvDeviceChannel();
            gmvDeviceChannel.setDeviceId(idMap.getDeviceId());
            gmvDeviceChannel.setChannelId(idMap.getChannelId());
            gmvDeviceChannel.setSnapshot(2);
            logger.warn(result.getMsg());
            gmvDeviceChannelMapper.updateByPrimaryKeySelective(gmvDeviceChannel);
            return null;
        }
        return result.getData();
    }

    @Override
    public void overviewImage(OverImageID imageID) {
        GmvDeviceChannel gmvDeviceChannel = new GmvDeviceChannel();
        gmvDeviceChannel.setDeviceId(imageID.getDeviceId());
        gmvDeviceChannel.setChannelId(imageID.getChannelId());
        gmvDeviceChannel.setOverPicId(imageID.getImageId());
        gmvDeviceChannelMapper.updateByPrimaryKeySelective(gmvDeviceChannel);
    }

    @Override
    public boolean rmFile(PairParamModel<String,Long> req) {
        String url = String.format("%s%s", getSessionSourceUrl(req.getParam1()), ApiPathConstants.RM_FILE);
        Map<String, Object> map = new HashMap<>();
        map.put("param", req.getParam2());
        GmvSessionResult<Boolean> result = GmvHttpUtil.post(url, map, Boolean.class);
        if (result == null) {
            throw new RuntimeException("删除失败");
        }
        if (result.getCode() != 200) {
            throw new RuntimeException(result.getMsg());
        }
        return true;
    }

    @Override
    public ResponseEntity<Resource> downloadFile(Long fileId) throws UnsupportedEncodingException {
        String filePath = deviceInfoMapper.getFilePath(fileId);
        if (StringUtils.isEmpty(filePath)) {
            return ResponseEntity.notFound().build();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(file);
        String encodedFileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    private static String buildFileSize(double size) {
        if (size >= 1024 * 1024 * 1024) {
            double t = size / (1024 * 1024 * 1024);
            return String.format("%.2f", t) + "|GB";
        } else if (size >= 1024 * 1024) {
            double t = size / (1024 * 1024);
            return String.format("%.2f", t) + "|MB";
        } else if (size >= 1024) {
            return String.format("%.2f", size / 1024) + "|KB";
        } else {
            return size + "|B";
        }
    }

}
