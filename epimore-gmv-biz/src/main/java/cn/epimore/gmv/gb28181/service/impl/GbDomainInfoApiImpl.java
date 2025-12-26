package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.cons.EnumCodeConstants;
import cn.epimore.gmv.gb28181.mapper.GbDomainInfoMapper;
import cn.epimore.gmv.gb28181.mapper.GmvOauthMapper;
import cn.epimore.gmv.gb28181.service.api.GbDomainInfoApi;
import cn.epimore.gmv.gb28181.service.api.GbServerService;
import cn.epimore.gmv.gb28181.service.api.GmvEnumCodeVoService;
import cn.epimore.gmv.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class GbDomainInfoApiImpl implements GbDomainInfoApi {
    @Resource
    private GbDomainInfoMapper gbDomainInfoMapper;
    @Resource
    private GmvOauthMapper gmvOauthMapper;
    @Autowired
    private GbServerService gbServerService;
    @Autowired
    private GmvEnumCodeVoService enumCodeVoService;

    @Override
    public PageInfo<GbServerInfo> queryGbServerInfos(GbSessionServerQo info) {
        PageHelper.startPage(info.getPageNum(), info.getPageSize());
        List<GbServerInfo> list = gbDomainInfoMapper.getGbServerInfoList(info);
        return new PageInfo<>(list);
    }

    @Override
    public GbDomainInfo queryGbDomainDevice(String deviceId) {
        GmvOauth gmvOauth = gmvOauthMapper.selectByPrimaryKey(deviceId);
        if (gmvOauth == null) {
            return null;
        }
        GbServerVo serverVo = gbServerService.getById(gmvOauth.getDomainId());
        if (serverVo == null) {
            return null;
        }
        List<GmvEnumCodeVo> codeVos = enumCodeVoService.lambdaQuery()
                .or(wrapper -> wrapper
                        .likeRight(GmvEnumCodeVo::getParentId, EnumCodeConstants.GB28181_TYPE_CODE + "0")
                        .eq(GmvEnumCodeVo::getValueStart, deviceId.substring(10, 13))
                        .eq(GmvEnumCodeVo::getStatus, 1)
                )
                .or(wrapper -> wrapper
                        .eq(GmvEnumCodeVo::getParentId, EnumCodeConstants.GB28181_NETWORK_CODE)
                        .eq(GmvEnumCodeVo::getValueStart, deviceId.substring(13, 14))
                        .eq(GmvEnumCodeVo::getStatus, 1)
                )
                .list();
        GbDomainInfo info = new GbDomainInfo();
        BeanUtils.copyProperties(gmvOauth, info);
        info.setSipIp(serverVo.getSipIp());
        info.setSipPort(serverVo.getSipPort());

//        BeanUtils.copyProperties(serverVo, info);
        if (ObjectUtils.isNotEmpty(codeVos)) {
            for (GmvEnumCodeVo codeVo : codeVos) {
                if (codeVo.getParentId().startsWith(EnumCodeConstants.GB28181_TYPE_CODE)) {
                    info.setDeviceTypeCode(codeVo.getId());
                }
                if (Objects.equals(codeVo.getParentId(), EnumCodeConstants.GB28181_NETWORK_CODE)) {
                    info.setNetworkTypeCode(codeVo.getId());
                }
            }
        }
        return info;
    }

    @Override
    public GbNetworkDeviceTypeInfo getGbNetworkDeviceTypeInfo() {
        List<GmvEnumCodeVo> networkTypes = enumCodeVoService.getGmvEnumCodeVo(EnumCodeConstants.GB28181_NETWORK_CODE);
        List<GmvTreeEnumCodeVo> deviceTypes = enumCodeVoService.getGmvTreeEnumCodeVo(EnumCodeConstants.GB28181_TYPE_CODE);
        return new GbNetworkDeviceTypeInfo(networkTypes, deviceTypes);
    }

}
