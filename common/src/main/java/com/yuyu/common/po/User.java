package com.yuyu.common.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class User implements UserDetails {

    private static final long serialVersionUID = 181642330346361455L;

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Long cityId;

    /**
     * 账户是否没有过期
     */
    private boolean accountNonExpired;

    /**
     * 账户是否没有被锁定
     */
    private boolean accountNonLocked;

    /**
     * 密码是否没有过期
     */
    private boolean credentialsNonExpired;

    /**
     * 账户是否可用
     */
    private boolean enabled;

    /**
     * 用户角色
     */
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
