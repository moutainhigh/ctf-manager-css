package com.ctf.auth.security.core.userdetails.user;

import com.ctf.admin.api.UserFeignClient;
import com.ctf.admin.dto.UserAuthDTO;
import com.ctf.common.result.Result;
import com.ctf.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 系统用户体系业务类
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Service("sysUserDetailsService")
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDetails userDetails = null;
        Result<UserAuthDTO> result = userFeignClient.getUserByUsername(username);
        if (Result.isSuccess(result)) {
            UserAuthDTO user = result.getData();
            if (null != user) {
                userDetails = new SysUserDetails(user);
            }
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

}
