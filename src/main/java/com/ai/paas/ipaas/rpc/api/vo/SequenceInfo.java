package com.ai.paas.ipaas.rpc.api.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ai.paas.ipaas.rpc.api.seq.ISequenceRPC;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SequenceInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5601241844355400893L;

	@NotNull(message = "序列名称不能为空", groups = { ISequenceRPC.CreateSequence.class })
	private String sequenceName;

    private String tableName;

    private int maxValue;

    private int minValue;

    private int startValue;

    private int increamentValue;

    private int cacheSize;

    private String cycleFlag;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getIncreamentValue() {
        return increamentValue;
    }

    public void setIncreamentValue(int increamentValue) {
        this.increamentValue = increamentValue;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getCycleFlag() {
        return cycleFlag;
    }

    public void setCycleFlag(String cycleFlag) {
        this.cycleFlag = cycleFlag == null ? null : cycleFlag.trim();
    }
}