package com.ai.paas.ipaas.base.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.paas.ipaas.base.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.base.manager.ISequenceManageSv;
import com.ai.paas.ipaas.base.service.ISequenceSv;
import com.ai.paas.ipaas.util.StringUtil;

@Service
@Transactional
public class SequenceManageSvImpl implements  ISequenceManageSv{
	

	public static Map<String,List<Integer>>  sequenceMap = new ConcurrentHashMap<String,List<Integer>>();

	@Autowired
	private ISequenceSv  sequeceSv;

	@Override
	public int nextVal(String sequenceName) {
		int nextval = 0;
		List<Integer>  list = sequenceMap.get(sequenceName);
		if(list == null || list.size() == 0) {
			synchronized(sequenceMap){
				list = sequenceMap.get(sequenceName);
				if(list == null || list.size() == 0) {
					Sequence sequence = sequeceSv.findByPkey(sequenceName);
					if(sequence == null) {
						throw new RuntimeException("not exists sequence name:" + sequenceName + "!");
					}
					nextval = getNextValfromDb(sequenceName, sequence);
				}else {
					nextval = list.remove(0);
				}
			}
		}else {
			nextval = list.remove(0);
		}
		
		return nextval;
	}

	public int getNextValfromDb(String sequenceName, Sequence sequence) {
		 int nextval = 0;
		 List<Integer> list = new ArrayList<Integer>();
		sequenceMap.put(sequenceName, list);
		int minValue = sequence.getMinValue();
		if(minValue <= 0) {
			minValue = 1;
		}
		int maxValue = sequence.getMaxValue() == 0 ? Integer.MAX_VALUE:sequence.getMaxValue();
		if(maxValue <= 0) {
			maxValue = Integer.MAX_VALUE;
		}
		int currVal = sequence.getStartValue();
		if(currVal < minValue) {
			currVal = minValue;
		}
		int cacheSize = sequence.getCacheSize();
		if(cacheSize <= 0) {
			cacheSize = 50;
		}
		int growPoint = sequence.getIncreamentValue();
		if(growPoint <= 0) {
			growPoint = 1;
		}
		int nextVal = 0;
		for(int i = 0 ;i < cacheSize ; i++) {
			nextVal = currVal + i * growPoint;
			if(nextVal > maxValue) {
				 if(this.isCycle(sequence)) {
					  nextVal =  minValue;
				 }else {
					 if(i == 0) {
						 throw new RuntimeException("sequence name:" + sequenceName + "is not exists!");
					 }else {
						 nextval = list.remove(0);
						 return nextval;
					 }
				 }
			}
			list.add(nextVal);
			sequence.setStartValue(nextVal +  growPoint);
		}
		sequeceSv.modModel(sequence);
		nextval = list.remove(0);
		
		return nextval;
	}
	
	private boolean isCycle(Sequence sequence) {
		boolean isCycle = false;
		if(StringUtil.isBlank(sequence.getCycleFlag())){
			return isCycle;
		}
		if("1".equals(sequence.getCycleFlag())) {
			isCycle = true;
		}else {
			isCycle = false;
		}
		return isCycle;
	}
	
}
