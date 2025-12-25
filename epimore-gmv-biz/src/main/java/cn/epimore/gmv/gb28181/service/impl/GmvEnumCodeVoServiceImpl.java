package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.cons.EnumCodeConstants;
import cn.epimore.gmv.gb28181.service.api.GmvEnumCodeVoService;
import cn.epimore.gmv.vo.GmvTreeEnumCodeVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.epimore.gmv.vo.GmvEnumCodeVo;
import cn.epimore.gmv.gb28181.mapper.GmvEnumCodeVoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MRK
 * @description 针对表【GMV_ENUM_CODE(枚举编码表（层级ID字符串）)】的数据库操作Service实现
 * @createDate 2025-12-24 23:46:02
 */
@Service
public class GmvEnumCodeVoServiceImpl extends ServiceImpl<GmvEnumCodeVoMapper, GmvEnumCodeVo>
        implements GmvEnumCodeVoService {
    public List<GmvTreeEnumCodeVo> getGmvTreeEnumCodeVo(String parentId) {
        LambdaQueryWrapper<GmvEnumCodeVo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(GmvEnumCodeVo::getId, GmvEnumCodeVo::getParentId, GmvEnumCodeVo::getName,
                        GmvEnumCodeVo::getValueStart, GmvEnumCodeVo::getValueEnd, GmvEnumCodeVo::getSeq)
                .like(GmvEnumCodeVo::getParentId, parentId+"%")
                .eq(GmvEnumCodeVo::getStatus, 1);
        List<GmvEnumCodeVo> vos = super.baseMapper.selectList(queryWrapper);
        return buildToTree(vos, parentId);
    }

    @Override
    public List<GmvEnumCodeVo> getGmvEnumCodeVo(String parentId) {
        LambdaQueryWrapper<GmvEnumCodeVo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(GmvEnumCodeVo::getId, GmvEnumCodeVo::getParentId, GmvEnumCodeVo::getName,
                        GmvEnumCodeVo::getValueStart, GmvEnumCodeVo::getValueEnd, GmvEnumCodeVo::getSeq)
                .eq(GmvEnumCodeVo::getParentId, parentId)
                .eq(GmvEnumCodeVo::getStatus, 1);
        return super.baseMapper.selectList(queryWrapper);
    }


    private static List<GmvTreeEnumCodeVo> buildToTree(List<GmvEnumCodeVo> vos,String rootParentId) {
        if (CollectionUtils.isEmpty(vos)) {
            return Collections.emptyList();
        }
        Map<String, GmvTreeEnumCodeVo> nodeMap = vos.stream().collect(Collectors.toMap(GmvEnumCodeVo::getId, GmvTreeEnumCodeVo::new));
        List<GmvTreeEnumCodeVo> roots = new ArrayList<>();
        for(GmvEnumCodeVo vo:vos){
            GmvTreeEnumCodeVo current = nodeMap.get(vo.getId());
            String parentId = vo.getParentId();
            if (ObjectUtils.equals(parentId, rootParentId)) {
                roots.add(current);
            }else{
                GmvTreeEnumCodeVo parentVo = nodeMap.get(parentId);
                if (ObjectUtils.isNotEmpty(parentVo)) {
                    parentVo.getSubVos().add(current);
                }
            }
        }
        sortTreeBySeq(roots);
        return roots;

    }
    private static void sortTreeBySeq(List<GmvTreeEnumCodeVo> nodes) {
        if (nodes == null || nodes.isEmpty()) return;
        nodes.sort(Comparator.comparing(node -> node.getVo().getSeq()));
        for (GmvTreeEnumCodeVo node : nodes) {
            sortTreeBySeq(node.getSubVos());
        }
    }
}




