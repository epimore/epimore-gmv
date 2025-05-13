package cn.epimore.gmv.gb28181.mapper;


import cn.epimore.gmv.vo.GmvDeviceChannel;
import cn.epimore.gmv.vo.IdMap;

/**
 * @author MRK
 * @description 针对表【GMV_DEVICE_CHANNEL(摄像机通道信息)】的数据库操作Mapper
 * @createDate 2024-08-30 16:30:48
 * @Entity cn.epimore.gmv.api.model.GmvDeviceChannel
 */
public interface GmvDeviceChannelMapper {

    int deleteByPrimaryKey(IdMap idMap);

    int insert(GmvDeviceChannel record);

    int insertSelective(GmvDeviceChannel record);

    GmvDeviceChannel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GmvDeviceChannel record);

    int updateByPrimaryKey(GmvDeviceChannel record);

}
