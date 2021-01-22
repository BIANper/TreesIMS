package com.yuyu.auth.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserVo {

    private String username;

    private String nickname;

    private Long cityId;

    private Collection<? extends GrantedAuthority> authorities;

}
