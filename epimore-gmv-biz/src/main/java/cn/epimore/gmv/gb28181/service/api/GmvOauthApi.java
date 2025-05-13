package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GmvOauth;

import java.util.List;

public interface GmvOauthApi {
    int deleteByPrimaryKey(String id);

    int insert(GmvOauth record);

    int updateByPrimaryKeySelective(GmvOauth record);

    List<GmvOauth> getGmvOauthList(GmvOauth record);
}
