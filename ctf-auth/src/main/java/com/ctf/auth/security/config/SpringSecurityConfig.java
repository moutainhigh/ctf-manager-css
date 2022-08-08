//package com.ctf.auth.security.config;
//
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import com.ctf.auth.security.extension.mobile.SmsCodeAuthenticationProvider;
//import com.ctf.auth.security.extension.wechat.WechatAuthenticationProvider;
//import com.ctf.ums.api.MemberFeignClient;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @ClassName SpringSecurityConfig
// * @Description TODO
// * @Author H.m
// * @date 2022/8/5 10:30
// * @Version 1.0
// **/
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSecurity    // 添加 security 过滤器
//@EnableGlobalMethodSecurity(prePostEnabled = true)    // 启用方法级别的权限认证
//public class SpringSecurityConfig {
//    private final UserDetailsService sysUserDetailsService;
//    private final UserDetailsService memberUserDetailsService;
//    private final WxMaService wxMaService;
//    private final MemberFeignClient memberFeignClient;
//    private final StringRedisTemplate redisTemplate;
//
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                // 基于 token，不需要 csrf
//                .csrf().disable()
////                // 基于 token，不需要 session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                // 设置 jwtAuthError 处理认证失败、鉴权失败
////                .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
//                // 下面开始设置权限
////                .authorizeRequests(authorize -> authorize
////                        // 请求放开
////                        .antMatchers("/oauth/**", "/sms-code").permitAll()
////                        .antMatchers("/webjars/**", "/doc.html", "/swagger-resources/**", "/v2/api-docs").permitAll()
////                        // 其他地址的访问均需验证权限
////                        .anyRequest().authenticated()
////                )
////                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
////                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
////                // 认证用户时用户信息加载配置，注入springAuthUserService
////                .userDetailsService(xxxAuthUserService)
//                .authenticationProvider(wechatAuthenticationProvider())
//                .authenticationProvider(daoAuthenticationProvider())
//                .authenticationProvider(smsCodeAuthenticationProvider())
//                .build();
//    }
//
//
//
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers(
//                "/oauth/**", "/sms-code",
//                "/webjars/**", "/doc.html", "/swagger-resources/**", "/v2/api-docs");
//    }
//
//
//
//
//    /**
//     * 认证管理对象
//     *
//     * @return
//     * @throws Exception
//     */
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return
////    }
//
//
//    /**
//     * 获取AuthenticationManager（认证管理器），登录时认证使用
//     *
//     * @param authenticationConfiguration
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
////    @Override
////    public void configure(AuthenticationManagerBuilder auth) {
////        auth.authenticationProvider(wechatAuthenticationProvider()).
////                authenticationProvider(daoAuthenticationProvider()).
////                authenticationProvider(smsCodeAuthenticationProvider());
////    }
//
//    /**
//     * 手机验证码认证授权提供者
//     *
//     * @return
//     */
//    @Bean
//    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
//        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
//        provider.setUserDetailsService(memberUserDetailsService);
//        provider.setRedisTemplate(redisTemplate);
//        return provider;
//    }
//
//    /**
//     * 微信认证授权提供者
//     *
//     * @return
//     */
//    @Bean
//    public WechatAuthenticationProvider wechatAuthenticationProvider() {
//        WechatAuthenticationProvider provider = new WechatAuthenticationProvider();
//        provider.setUserDetailsService(memberUserDetailsService);
//        provider.setWxMaService(wxMaService);
//        provider.setMemberFeignClient(memberFeignClient);
//        return provider;
//    }
//
//
//    /**
//     * 用户名密码认证授权提供者
//     *
//     * @return
//     */
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(sysUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setHideUserNotFoundExceptions(false); // 是否隐藏用户不存在异常，默认:true-隐藏；false-抛出异常；
//        return provider;
//    }
//
//
//    /**
//     * 密码编码器
//     * <p>
//     * 委托方式，根据密码的前缀选择对应的encoder，例如：{bcypt}前缀->标识BCYPT算法加密；{noop}->标识不使用任何加密即明文的方式
//     * 密码判读 DaoAuthenticationProvider#additionalAuthenticationChecks
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//}
