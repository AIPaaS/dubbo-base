package com.ai.paas.ipaas.rpc.seq.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.exception.BusinessException;
import com.ai.paas.ipaas.rpc.api.seq.ISequenceRPC;
import com.ai.paas.ipaas.rpc.api.vo.ApplyInfo;
import com.ai.paas.ipaas.rpc.api.vo.ApplyResult;
import com.ai.paas.ipaas.rpc.api.vo.SequenceInfo;
import com.ai.paas.ipaas.seq.dao.mapper.bo.Sequence;
import com.ai.paas.ipaas.seq.service.ISequenceSv;
import com.ai.paas.ipaas.util.CloneTool;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
public class SequenceRPCImpl implements ISequenceRPC {
	private static final Logger log = LogManager
			.getLogger(SequenceRPCImpl.class.getName());
	@Autowired
	private ISequenceSv sequeceSV;

	@Override
	public Long nextVal(String sequenceName) {
		log.info(sequenceName);
		return sequeceSV.nextVal(sequenceName);
	}

	@Override
	public void createSequence(SequenceInfo seq) {
		// 利用gson进行对象转换

		sequeceSV.addModel(CloneTool
				.<com.ai.paas.ipaas.seq.dao.mapper.bo.Sequence> clone(seq,
						Sequence.class));
	}

	@Override
	public ApplyResult getSeq(ApplyInfo info) {
		throw new BusinessException("999999", "这是一个测试");
	}

}
