package cn.epimore.gmv.gb28181.mapper;

import cn.epimore.gmv.vo.GmvDevice;

/**
* @author MRK
* @description 针对表【GMV_DEVICE(设备主表)】的数据库操作Mapper
* @createDate 2024-08-30 16:30:30
* @Entity cn.epimore.gmv.api.model.GmvDevice
*/
public interface GmvDeviceMapper {

    int deleteByPrimaryKey(String deviceId);

    int insert(GmvDevice record);

    int insertSelective(GmvDevice record);

    GmvDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GmvDevice record);

    int updateByPrimaryKey(GmvDevice record);

}
