package com.ai.paas.ipaas.validator.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ai.paas.ipaas.validator.StringEnum;

public class StringEnumerationValidator implements
		ConstraintValidator<StringEnum, String> {

	List<String> valueList = null;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!valueList.contains(value.toUpperCase())) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(StringEnum stringEnum) {
		valueList = new ArrayList<String>();
		Class<? extends Enum<?>> enumClass = stringEnum.enumClazz();

		@SuppressWarnings("rawtypes")
		Enum[] enumValArr = enumClass.getEnumConstants();

		for (@SuppressWarnings("rawtypes")
		Enum enumVal : enumValArr) {
			valueList.add(enumVal.toString().toUpperCase());
		}

	}

}
