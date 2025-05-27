package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GmvOauth;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GmvOauthApi {
    int deleteByPrimaryKey(String id);

    int insert(GmvOauth record);

    int updateByPrimaryKeySelective(GmvOauth record);

    PageInfo<GmvOauth> getGmvOauthList(GmvOauth record);
}
