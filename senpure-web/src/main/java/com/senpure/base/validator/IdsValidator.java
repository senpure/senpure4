package com.senpure.base.validator;

import com.senpure.base.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IdsValidator implements ConstraintValidator<Ids, String> {

	@Override
	public void initialize(Ids ids) {

	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}
		value = value.trim();
		if (value.length() == 0) {
			return true;
		}

		value = value.replaceAll("\\s", ",");
		value = value.replaceAll("\n", "");
		value = value.replaceAll("\r", ",");
		String[] ids = value.split(",");
		for (String id : ids) {
			if (!StringUtil.isNum(id)&&id.length()!=0) {
				//logger.debug("id:"+id+",l:"+id.length());
				return false;
			}
		}

		return true;
	}

}
