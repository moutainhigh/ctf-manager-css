package com.ctf.lab.spring.beanDefinition;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 *
 * 注册user开头的标签解析器
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
	}
}
