package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.common.seq.mapper.CommFuncMapper;
import cn.epimore.gmv.gb28181.mapper.GmvDeviceMapper;
import cn.epimore.gmv.gb28181.mapper.GmvOauthMapper;
import cn.epimore.gmv.gb28181.service.api.GbIdSeqService;
import cn.epimore.gmv.gb28181.service.api.GmvOauthApi;
import cn.epimore.gmv.gb28181.utils.CurrentUserHelper;
import cn.epimore.gmv.vo.GmvDevice;
import cn.epimore.gmv.vo.GmvOauth;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GmvOauthApiImpl implements GmvOauthApi {
    private final static Logger logger = LoggerFactory.getLogger(GmvOauthApiImpl.class);
    @Resource
    private GmvOauthMapper gmvOauthMapper;
    @Resource
    private GmvDeviceMapper gmvDeviceMapper;
    @Resource
    private CommFuncMapper commFuncMapper;
    @Value("${gmv.seqName:34020000001117}")
    private String seqName;
    @Autowired
    private GbIdSeqService gbIdSeqService;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gmvOauthMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(GmvOauth record) {
        LoginUser systemUser = CurrentUserHelper.getSystemUser();
        logger.info("当前系统用户信息:{}", systemUser);
        GmvDevice gmvDevice = new GmvDevice();
        String deviceId = gbIdSeqService.buildGbId(record.getDomainId(), record.getTypeCode(), record.getNetworkCode());
        record.setDomain(record.getDomain());
        record.setDomainId(record.getDomainId());
        record.setStatus("1");
        gmvDevice.setDeviceId(deviceId);
        record.setDeviceId(deviceId);
        gmvDeviceMapper.insert(gmvDevice);
        return gmvOauthMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(GmvOauth record) {
        return gmvOauthMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<GmvOauth> getGmvOauthList(GmvOauth record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<GmvOauth> list = gmvOauthMapper.getGmvOauthList(record);
        return new PageInfo<>(list);
    }
}
