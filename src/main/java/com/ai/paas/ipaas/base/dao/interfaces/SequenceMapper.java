package com.ai.paas.ipaas.base.dao.interfaces;

import com.ai.paas.ipaas.base.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.base.dao.mapper.bo.SequenceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SequenceMapper {
    int countByExample(SequenceCriteria example);

    int deleteByExample(SequenceCriteria example);

    int deleteByPrimaryKey(String sequenceName);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    List<Sequence> selectByExample(SequenceCriteria example);

    Sequence selectByPrimaryKey(String sequenceName);

    int updateByExampleSelective(@Param("record") Sequence record, @Param("example") SequenceCriteria example);

    int updateByExample(@Param("record") Sequence record, @Param("example") SequenceCriteria example);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
}