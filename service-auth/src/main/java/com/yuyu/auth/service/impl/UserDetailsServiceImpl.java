package com.yuyu.auth.service.impl;

import com.yuyu.auth.mapper.AuthMapper;
import com.yuyu.common.po.Role;
import com.yuyu.common.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> rolesList = authMapper.findRoleByUserId(user.getId());
        user.setRoles(rolesList);
        return user;
    }
}
