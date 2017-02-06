package com.ai.paas.ipaas.seq.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.paas.ipaas.ServiceUtil;
import com.ai.paas.ipaas.seq.dao.interfaces.SequenceMapper;
import com.ai.paas.ipaas.seq.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.seq.dao.mapper.bo.SequenceCriteria;
import com.ai.paas.ipaas.seq.service.ISequenceSv;
import com.ai.paas.ipaas.util.StringUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class SequenceSvImpl implements ISequenceSv {
	public static Map<String, List<Integer>> sequenceMap = new ConcurrentHashMap<String, List<Integer>>();

	private final String CYCLE_FLAG_YES = "1";

	@Override
	public Sequence findByPkey(String sequenceName) {

		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria = new SequenceCriteria();
		SequenceCriteria.Criteria criteria = sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequenceName);
		List<Sequence> list = mapper.selectByExample(sequenceCriteria);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);

	}

	@Override
	public List<Sequence> getModels(Sequence sequence) {
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria = SequenceSvImpl.toCriteria(sequence);
		List<Sequence> list = mapper.selectByExample(sequenceCriteria);
		return list;
	}

	@Override
	public void addModel(Sequence sequence) {
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		mapper.insert(sequence);

	}

	@Override
	public void modModel(Sequence sequence) {
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		mapper.updateByPrimaryKey(sequence);
	}

	@Override
	public void delModel(String sequenceName) {
		SequenceMapper mapper = ServiceUtil.getMapper(SequenceMapper.class);
		SequenceCriteria sequenceCriteria = new SequenceCriteria();
		SequenceCriteria.Criteria criteria = sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequenceName);
		mapper.deleteByExample(sequenceCriteria);
	}

	public static SequenceCriteria toCriteria(Sequence sequence) {
		SequenceCriteria sequenceCriteria = new SequenceCriteria();
		SequenceCriteria.Criteria criteria = sequenceCriteria.createCriteria();
		criteria.andSequenceNameEqualTo(sequence.getSequenceName());
		criteria.andTableNameEqualTo(sequence.getSequenceName());
		return sequenceCriteria;
	}

	@Override
	public Long nextVal(String sequenceName) {
		//每次获取到序列的当前值，步长，声明一个Map，里面存储一个原子整数
		//存储此次的最大值，如果没有超过最大值，则原子加
		//怎么判断超过了？   int i = count.get();
//        while (i < 10 && !count.compareAndSet(i, i + 1)) {
//            i = count.get();
//        }
		long nextval = 0;
		List<Integer> list = sequenceMap.get(sequenceName);
		if (list == null || list.size() == 0) {
			synchronized (sequenceMap) {
				list = sequenceMap.get(sequenceName);
				if (list == null || list.size() == 0) {
					Sequence sequence = findByPkey(sequenceName);
					if (sequence == null) {
						throw new RuntimeException("not exists sequence name:"
								+ sequenceName + "!");
					}
					nextval = getNextValfromDb(sequenceName, sequence);
				} else {
					nextval = list.remove(0);
				}
			}
		} else {
			nextval = list.remove(0);
		}

		return nextval;
	}

	public long getNextValfromDb(String sequenceName, Sequence sequence) {
		long nextval = 0;
		List<Integer> list = new ArrayList<Integer>();
		sequenceMap.put(sequenceName, list);
		int minValue = sequence.getMinValue();
		if (minValue <= 0) {
			minValue = 1;
		}
		int maxValue = sequence.getMaxValue() == 0 ? Integer.MAX_VALUE
				: sequence.getMaxValue();
		if (maxValue <= 0) {
			maxValue = Integer.MAX_VALUE;
		}
		int currVal = sequence.getStartValue();
		if (currVal < minValue) {
			currVal = minValue;
		}
		int cacheSize = sequence.getCacheSize();
		if (cacheSize <= 0) {
			cacheSize = 50;
		}
		int growPoint = sequence.getIncreamentValue();
		if (growPoint <= 0) {
			growPoint = 1;
		}
		int nextVal = 0;
		for (int i = 0; i < cacheSize; i++) {
			nextVal = currVal + i * growPoint;
			if (nextVal > maxValue) {
				if (this.isCycle(sequence)) {
					nextVal = minValue;
				} else {
					if (i == 0) {
						throw new RuntimeException("sequence name:"
								+ sequenceName + "is not exists!");
					} else {
						nextval = list.remove(0);
						return nextval;
					}
				}
			}
			list.add(nextVal);
			sequence.setStartValue(nextVal + growPoint);
		}
		modModel(sequence);
		nextval = list.remove(0);

		return nextval;
	}

	private boolean isCycle(Sequence sequence) {
		boolean isCycle = false;
		if (StringUtil.isBlank(sequence.getCycleFlag())) {
			return isCycle;
		}
		if (CYCLE_FLAG_YES.equals(sequence.getCycleFlag())) {
			isCycle = true;
		} else {
			isCycle = false;
		}
		return isCycle;
	}

}
