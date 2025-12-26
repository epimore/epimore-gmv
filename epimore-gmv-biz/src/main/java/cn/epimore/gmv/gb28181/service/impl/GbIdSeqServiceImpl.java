package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.GbIdSeqMapper;
import cn.epimore.gmv.gb28181.service.api.GbIdSeqService;
import cn.epimore.gmv.gb28181.service.api.GbServerService;
import cn.epimore.gmv.gb28181.service.api.GmvEnumCodeVoService;
import cn.epimore.gmv.vo.GbIdSeq;
import cn.epimore.gmv.vo.GbServerVo;
import cn.epimore.gmv.vo.GmvEnumCodeVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MRK
 * @description 针对表【GB_ID_SEQ(国标设备ID生成器：编码由中心编码(8位)、行业编码(2位)、类型编码(3位)、网络标识(1位)和序号(6位)5个码段共20位十进制数字字符)】的数据库操作Service实现
 * @createDate 2025-12-24 23:45:39
 */
@Service
public class GbIdSeqServiceImpl extends ServiceImpl<GbIdSeqMapper, GbIdSeq>
        implements GbIdSeqService {
    @Autowired
    private GbServerService gbServerService;
    @Autowired
    private GmvEnumCodeVoService enumCodeVoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String buildGbId(String domainId, String typeCode, String networkCode) {
        GbServerVo serverVo = gbServerService.getById(domainId);
        if (ObjectUtils.isEmpty(serverVo)) {
            return null;
        }
        List<GmvEnumCodeVo> enumCodeVos = enumCodeVoService.lambdaQuery().eq(GmvEnumCodeVo::getId, typeCode).or()
                .eq(GmvEnumCodeVo::getId, networkCode).list();
        if (ObjectUtils.isEmpty(enumCodeVos) || enumCodeVos.size() != 2) {
            throw new RuntimeException("设备类型或网络类型不存在");
        }
        String deviceTypeValue = "";
        String networkTypeValue = "";
        for (GmvEnumCodeVo enumCodeVo : enumCodeVos) {
            if (enumCodeVo.getId().equals(typeCode)) {
                deviceTypeValue = enumCodeVo.getValueStart();
            }
            if (enumCodeVo.getId().equals(networkCode)) {
                networkTypeValue = enumCodeVo.getValueStart();
            }
        }

        if (ObjectUtils.isEmpty(deviceTypeValue) || ObjectUtils.isEmpty(networkTypeValue)) {
            throw new RuntimeException("设备类型或网络类型不存在");
        }

        GbIdSeq result = this.lambdaQuery()
                .eq(GbIdSeq::getDomainId, domainId)
                .eq(GbIdSeq::getTypeCode, deviceTypeValue)
                .eq(GbIdSeq::getNetworkCode, networkTypeValue)
                .one();

        if (ObjectUtils.isEmpty(result)) {
            GbIdSeq idSeq = new GbIdSeq();
            idSeq.setDomainId(domainId);
            idSeq.setCenterCode(domainId.substring(0, 8));
            idSeq.setIndustryCode(domainId.substring(8, 10));
            idSeq.setTypeCode(deviceTypeValue);
            idSeq.setNetworkCode(networkTypeValue);
            idSeq.setSeq(1);
            idSeq.setStatus(1);
            idSeq.setCreateDate(LocalDateTime.now());
            this.save(idSeq);
            return buildDeviceId(idSeq);
        } else {
            if (result.getStatus() == 0) {
                throw new RuntimeException("设备及网络组合分类已禁用，不可创建接入信息");
            } else {
                if (result.getSeq() == null) {
                    result.setSeq(1);
                } else {
                    int i = result.getSeq() + 1;
                    if (i > 999999) {
                        throw new RuntimeException("设备及网络组合分类接入已达上限，不可创建接入信息");
                    }
                    result.setSeq(i);
                }
                this.updateById(result);
            }
            return buildDeviceId(result);
        }
    }

    private static String buildDeviceId(GbIdSeq idSeq) {
        return idSeq.getCenterCode() +
                idSeq.getIndustryCode() +
                idSeq.getTypeCode() +
                idSeq.getNetworkCode() +
                String.format("%06d", idSeq.getSeq());
    }
}




