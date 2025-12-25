package cn.epimore.gmv.gb28181.mapper;

import cn.epimore.gmv.vo.GbIdSeq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author MRK
* @description 针对表【GB_ID_SEQ(国标设备ID生成器：编码由中心编码(8位)、行业编码(2位)、类型编码(3位)、网络标识(1位)和序号(6位)5个码段共20位十进制数字字符)】的数据库操作Mapper
* @createDate 2025-12-24 23:45:39
* @Entity cn.epimore.gmv.vo.GbIdSeq
*/
public interface GbIdSeqMapper extends BaseMapper<GbIdSeq> {

}




