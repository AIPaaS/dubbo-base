package com.ai.paas.ipaas.rpc.seq.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.paas.ipaas.base.api.seq.ISequenceRPCService;
import com.ai.paas.ipaas.base.api.vo.SequenceInfo;
import com.ai.paas.ipaas.base.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.base.service.ISequenceSv;
import com.ai.paas.ipaas.util.CloneTool;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
public class SequenceRPCServiceImpl implements ISequenceRPCService {

	@Autowired
	private ISequenceSv sequeceSV;

	@Override
	public Long nextVal(String sequenceName) {

		return sequeceSV.nextVal(sequenceName);
	}

	@Override
	public void createSequence(SequenceInfo seq) {
		// 利用gson进行对象转换

		sequeceSV.addModel(CloneTool
				.<com.ai.paas.ipaas.base.dao.mapper.bo.Sequence> clone(seq,
						Sequence.class));
	}

}
