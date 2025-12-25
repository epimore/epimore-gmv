package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.service.api.GbServerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.epimore.gmv.vo.GbServerVo;
import cn.epimore.gmv.gb28181.mapper.GbServerMapper;
import org.springframework.stereotype.Service;

/**
* @author MRK
* @description 针对表【GB_SERVER(国标信令服务)】的数据库操作Service实现
* @createDate 2025-12-24 23:33:50
*/
@Service
public class GbServerServiceImpl extends ServiceImpl<GbServerMapper, GbServerVo>
    implements GbServerService {
}




