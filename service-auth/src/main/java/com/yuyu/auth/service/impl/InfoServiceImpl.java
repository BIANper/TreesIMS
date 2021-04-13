package com.yuyu.auth.service.impl;

import com.yuyu.auth.mapper.AuthMapper;
import com.yuyu.auth.service.InfoService;
import com.yuyu.common.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public User getUser(String username) {
        return authMapper.findUserByUsername(username);
    }
}
