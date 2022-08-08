package com.ctf.auth.security.core.userdetails.member;

import com.ctf.common.constant.GlobalConstants;
import com.ctf.common.enums.AuthenticationIdentityEnum;
import com.ctf.ums.dto.MemberAuthDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * 用户认证信息
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class MemberUserDetails implements UserDetails {

    private Long memberId;
    private String username;
    private Boolean enabled;

    /**
     * 扩展字段：认证身份标识，枚举值如下：
     *
     * @see AuthenticationIdentityEnum
     */
    private String authenticationIdentity;

    /**
     * 小程序会员用户体系
     *
     * @param member 小程序会员用户认证信息
     */
    public MemberUserDetails(MemberAuthDTO member) {
        this.setMemberId(member.getMemberId());
        this.setUsername(member.getUsername());
        this.setEnabled(GlobalConstants.STATUS_YES.equals(member.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        return collection;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
