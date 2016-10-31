package com.ai.paas.ipaas.rpc.seq.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ai.paas.ipaas.i18n.ResBundle;
import com.ai.paas.ipaas.i18n.TimeZoneEnum;
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

	@Autowired
	ResBundle rb;

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(LocaleContextHolder.getTimeZone());
		String sd = sdf.format(new Date());
		sdf.setTimeZone(TimeZone.getTimeZone(TimeZoneEnum.GMT.getZone()));
		System.out.println(new Date());
		System.out.println(sd);
		return new ApplyResult("000000", rb.getMessage("ipaas.apply.sucess"));
	}

}
