package com.ai.paas.ipaas.base.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.paas.ipaas.ServiceUtil;
import com.ai.paas.ipaas.base.dao.interfaces.SequenceMapper;
import com.ai.paas.ipaas.base.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.base.dao.mapper.bo.SequenceCriteria;
import com.ai.paas.ipaas.base.service.ISequenceSv;

@Service
@Transactional
public class SequenceSvImpl implements  ISequenceSv{
	
	@Override
	public Sequence findByPkey(String sequenceName) {
		
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria=new SequenceCriteria();
		SequenceCriteria.Criteria criteria=sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequenceName);
		List<Sequence> list=mapper.selectByExample(sequenceCriteria);
		if(list == null  ||  list.size() == 0) {
			return null;
		}
		return list.get(0);
		
	}
	@Override
	public List<Sequence> getModels(Sequence sequence) {
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria=SequenceSvImpl.toCriteria(sequence);
		List<Sequence> list=mapper.selectByExample(sequenceCriteria);
		return list;
	}

	@Override
	public void addModel(Sequence sequence) {
		SequenceMapper mapper=ServiceUtil.getMapper(SequenceMapper.class);
		mapper.insert(sequence);
		
	}

	@Override
	public void modModel(Sequence sequence) {
		SequenceMapper mapper=ServiceUtil.getMapper(SequenceMapper.class);
		mapper.updateByPrimaryKey(sequence);
	}

	@Override
	public void delModel(String  sequenceName) {
		SequenceMapper mapper=ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria=new SequenceCriteria();
		SequenceCriteria.Criteria criteria=sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequenceName);
		mapper.deleteByExample(sequenceCriteria);
	}

	public static SequenceCriteria toCriteria(Sequence sequence){
		SequenceCriteria sequenceCriteria=new SequenceCriteria();
		SequenceCriteria.Criteria criteria=sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequence.getSequenceName());
		criteria.andTableNameEqualTo(sequence.getSequenceName());
		return sequenceCriteria;
	}
	
	
	

	
}
