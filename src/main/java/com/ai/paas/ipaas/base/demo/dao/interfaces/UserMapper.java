package com.ai.paas.ipaas.base.demo.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.paas.ipaas.base.demo.dao.mapper.bo.User;
import com.ai.paas.ipaas.base.demo.dao.mapper.bo.UserCriteria;

public interface UserMapper {
	int countByExample(UserCriteria example);

	int deleteByExample(UserCriteria example);

	int deleteByPrimaryKey(String name);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserCriteria example);

	User selectByPrimaryKey(String sequenceName);

	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserCriteria example);

	int updateByExample(@Param("record") User record,
			@Param("example") UserCriteria example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}