package cn.epimore.gmv.gb28181.mapper;


import cn.epimore.gmv.vo.*;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;

import java.util.List;

public interface GbDomainInfoMapper {

    List<GbServerInfo> getGbServerInfoList(GbSessionServerQo info);
    SessionSourceVo getSessionSourceVo(String deviceId);
}
