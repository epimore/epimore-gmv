package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.service.api.GbIdSeqService;
import cn.epimore.gmv.gb28181.service.api.GbServerService;
import cn.epimore.gmv.vo.GbServerVo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.epimore.gmv.vo.GbIdSeq;
import cn.epimore.gmv.gb28181.mapper.GbIdSeqMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String buildGbId(String domainId, String typeCode, String networkCode) {
        GbServerVo serverVo = gbServerService.getById(domainId);
        if (ObjectUtils.isEmpty(serverVo)) {
            return null;
        }
        GbIdSeq result = this.lambdaQuery()
                .eq(GbIdSeq::getDomainId, domainId)
                .eq(GbIdSeq::getTypeCode, typeCode)
                .eq(GbIdSeq::getNetworkCode, networkCode)
                .one();

        if (ObjectUtils.isEmpty(result)) {
            GbIdSeq idSeq = new GbIdSeq();
            idSeq.setDomainId(domainId);
            idSeq.setCenterCode(domainId.substring(0, 8));
            idSeq.setIndustryCode(domainId.substring(8, 10));
            idSeq.setTypeCode(typeCode);
            idSeq.setNetworkCode(networkCode);
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




