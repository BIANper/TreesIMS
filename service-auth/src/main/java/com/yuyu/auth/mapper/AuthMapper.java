package com.yuyu.auth.mapper;

import com.yuyu.common.po.Role;
import com.yuyu.common.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthMapper {

    User findUserByUsername(String username);

    List<Role> findRoleByUserId(Long userId);

}
