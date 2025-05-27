package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.mapper.GmvOauthMapper;
import cn.epimore.gmv.gb28181.service.api.GmvOauthApi;
import cn.epimore.gmv.vo.GmvOauth;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GmvOauthApiImpl implements GmvOauthApi {
    @Resource
    private GmvOauthMapper gmvOauthMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gmvOauthMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GmvOauth record) {
        record.setCreateTime(LocalDateTime.now());
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
