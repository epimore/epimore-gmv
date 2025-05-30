package cn.epimore.gmv.gb28181.mapper;

import cn.epimore.gmv.vo.GmvOauth;

import java.util.List;

/**
 * @author MRK
 * @description 针对表【GMV_OAUTH(认证表)】的数据库操作Mapper
 * @createDate 2024-08-30 16:29:03
 * @Entity cn.epimore.gmv.api.model.GmvOauth
 */
public interface GmvOauthMapper {

    int deleteByPrimaryKey(String deviceId);

    int insert(GmvOauth record);

//    int insertSelective(GmvOauth record);

    GmvOauth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GmvOauth record);

//    int updateByPrimaryKey(GmvOauth record);

    List<GmvOauth> getGmvOauthList(GmvOauth record);

}
