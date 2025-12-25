package cn.epimore.gmv.gb28181.service.api;

import cn.epimore.gmv.vo.GmvEnumCodeVo;
import cn.epimore.gmv.vo.GmvTreeEnumCodeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MRK
* @description 针对表【GMV_ENUM_CODE(枚举编码表（层级ID字符串）)】的数据库操作Service
* @createDate 2025-12-24 23:46:02
*/
public interface GmvEnumCodeVoService extends IService<GmvEnumCodeVo> {

    List<GmvTreeEnumCodeVo> getGmvTreeEnumCodeVo(String parentId);
    List<GmvEnumCodeVo> getGmvEnumCodeVo(String parentId);
}
