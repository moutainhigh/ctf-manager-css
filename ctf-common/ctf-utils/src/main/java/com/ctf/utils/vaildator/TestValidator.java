package com.ctf.utils.vaildator;

import cn.hutool.json.JSONUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author william
 * @description
 * @Date: 2020-07-30 09:18
 */
public class TestValidator implements ConstraintValidator<TestValidateList, String> {
    @Override
    public void initialize(TestValidateList constraint) {
    }

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext context) {
        System.out.println(JSONUtil.toJsonStr(obj));
        System.out.println(JSONUtil.toJsonStr(context));
        return true;
    }

}
