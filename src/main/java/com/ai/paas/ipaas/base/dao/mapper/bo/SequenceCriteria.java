package com.ai.paas.ipaas.base.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class SequenceCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SequenceCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSequenceNameIsNull() {
            addCriterion("sequence_name is null");
            return (Criteria) this;
        }

        public Criteria andSequenceNameIsNotNull() {
            addCriterion("sequence_name is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceNameEqualTo(String value) {
            addCriterion("sequence_name =", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotEqualTo(String value) {
            addCriterion("sequence_name <>", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameGreaterThan(String value) {
            addCriterion("sequence_name >", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameGreaterThanOrEqualTo(String value) {
            addCriterion("sequence_name >=", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLessThan(String value) {
            addCriterion("sequence_name <", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLessThanOrEqualTo(String value) {
            addCriterion("sequence_name <=", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLike(String value) {
            addCriterion("sequence_name like", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotLike(String value) {
            addCriterion("sequence_name not like", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameIn(List<String> values) {
            addCriterion("sequence_name in", values, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotIn(List<String> values) {
            addCriterion("sequence_name not in", values, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameBetween(String value1, String value2) {
            addCriterion("sequence_name between", value1, value2, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotBetween(String value1, String value2) {
            addCriterion("sequence_name not between", value1, value2, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNull() {
            addCriterion("max_value is null");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNotNull() {
            addCriterion("max_value is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValueEqualTo(int value) {
            addCriterion("max_value =", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotEqualTo(int value) {
            addCriterion("max_value <>", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThan(int value) {
            addCriterion("max_value >", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThanOrEqualTo(int value) {
            addCriterion("max_value >=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThan(int value) {
            addCriterion("max_value <", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThanOrEqualTo(int value) {
            addCriterion("max_value <=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIn(List<Integer> values) {
            addCriterion("max_value in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotIn(List<Integer> values) {
            addCriterion("max_value not in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueBetween(int value1, int value2) {
            addCriterion("max_value between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotBetween(int value1, int value2) {
            addCriterion("max_value not between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNull() {
            addCriterion("min_value is null");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNotNull() {
            addCriterion("min_value is not null");
            return (Criteria) this;
        }

        public Criteria andMinValueEqualTo(int value) {
            addCriterion("min_value =", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotEqualTo(int value) {
            addCriterion("min_value <>", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThan(int value) {
            addCriterion("min_value >", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThanOrEqualTo(int value) {
            addCriterion("min_value >=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThan(int value) {
            addCriterion("min_value <", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThanOrEqualTo(int value) {
            addCriterion("min_value <=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIn(List<Integer> values) {
            addCriterion("min_value in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotIn(List<Integer> values) {
            addCriterion("min_value not in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueBetween(int value1, int value2) {
            addCriterion("min_value between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotBetween(int value1, int value2) {
            addCriterion("min_value not between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andStartValueIsNull() {
            addCriterion("start_value is null");
            return (Criteria) this;
        }

        public Criteria andStartValueIsNotNull() {
            addCriterion("start_value is not null");
            return (Criteria) this;
        }

        public Criteria andStartValueEqualTo(int value) {
            addCriterion("start_value =", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueNotEqualTo(int value) {
            addCriterion("start_value <>", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueGreaterThan(int value) {
            addCriterion("start_value >", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueGreaterThanOrEqualTo(int value) {
            addCriterion("start_value >=", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueLessThan(int value) {
            addCriterion("start_value <", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueLessThanOrEqualTo(int value) {
            addCriterion("start_value <=", value, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueIn(List<Integer> values) {
            addCriterion("start_value in", values, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueNotIn(List<Integer> values) {
            addCriterion("start_value not in", values, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueBetween(int value1, int value2) {
            addCriterion("start_value between", value1, value2, "startValue");
            return (Criteria) this;
        }

        public Criteria andStartValueNotBetween(int value1, int value2) {
            addCriterion("start_value not between", value1, value2, "startValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueIsNull() {
            addCriterion("increament_value is null");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueIsNotNull() {
            addCriterion("increament_value is not null");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueEqualTo(int value) {
            addCriterion("increament_value =", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueNotEqualTo(int value) {
            addCriterion("increament_value <>", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueGreaterThan(int value) {
            addCriterion("increament_value >", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueGreaterThanOrEqualTo(int value) {
            addCriterion("increament_value >=", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueLessThan(int value) {
            addCriterion("increament_value <", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueLessThanOrEqualTo(int value) {
            addCriterion("increament_value <=", value, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueIn(List<Integer> values) {
            addCriterion("increament_value in", values, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueNotIn(List<Integer> values) {
            addCriterion("increament_value not in", values, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueBetween(int value1, int value2) {
            addCriterion("increament_value between", value1, value2, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andIncreamentValueNotBetween(int value1, int value2) {
            addCriterion("increament_value not between", value1, value2, "increamentValue");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIsNull() {
            addCriterion("cache_size is null");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIsNotNull() {
            addCriterion("cache_size is not null");
            return (Criteria) this;
        }

        public Criteria andCacheSizeEqualTo(int value) {
            addCriterion("cache_size =", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotEqualTo(int value) {
            addCriterion("cache_size <>", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeGreaterThan(int value) {
            addCriterion("cache_size >", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeGreaterThanOrEqualTo(int value) {
            addCriterion("cache_size >=", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeLessThan(int value) {
            addCriterion("cache_size <", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeLessThanOrEqualTo(int value) {
            addCriterion("cache_size <=", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIn(List<Integer> values) {
            addCriterion("cache_size in", values, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotIn(List<Integer> values) {
            addCriterion("cache_size not in", values, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeBetween(int value1, int value2) {
            addCriterion("cache_size between", value1, value2, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotBetween(int value1, int value2) {
            addCriterion("cache_size not between", value1, value2, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCycleFlagIsNull() {
            addCriterion("cycle_flag is null");
            return (Criteria) this;
        }

        public Criteria andCycleFlagIsNotNull() {
            addCriterion("cycle_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCycleFlagEqualTo(String value) {
            addCriterion("cycle_flag =", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagNotEqualTo(String value) {
            addCriterion("cycle_flag <>", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagGreaterThan(String value) {
            addCriterion("cycle_flag >", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_flag >=", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagLessThan(String value) {
            addCriterion("cycle_flag <", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagLessThanOrEqualTo(String value) {
            addCriterion("cycle_flag <=", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagLike(String value) {
            addCriterion("cycle_flag like", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagNotLike(String value) {
            addCriterion("cycle_flag not like", value, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagIn(List<String> values) {
            addCriterion("cycle_flag in", values, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagNotIn(List<String> values) {
            addCriterion("cycle_flag not in", values, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagBetween(String value1, String value2) {
            addCriterion("cycle_flag between", value1, value2, "cycleFlag");
            return (Criteria) this;
        }

        public Criteria andCycleFlagNotBetween(String value1, String value2) {
            addCriterion("cycle_flag not between", value1, value2, "cycleFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}