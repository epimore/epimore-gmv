package cn.epimore.gmv.common.seq.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommFuncMapper {
    String getSeqCode(String seqName);
}
