package com.ctf.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.ctf.component.commons.config.CommonResourceServerConfig;

/**
 * 系统管理OAuth2资源服务器
 *
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends CommonResourceServerConfig {

	@Autowired
	public ResourceServerConfig(ResourceServerProperties sso) {
		super(sso);
	}

	/**
	 * 配置拦截路径的安全规则
	 */
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/actuator/**", "/swagger-ui/**", "/swagger-ui.html/**", "/webjars/springfox-swagger-ui/**",
				"/swagger-resources/**", "/v2/api-docs/**").permitAll().anyRequest().authenticated();
	}

}
