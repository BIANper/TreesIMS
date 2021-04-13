package com.yuyu.auth.controller;

import com.yuyu.auth.service.InfoService;
import com.yuyu.common.po.User;
import com.yuyu.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"认证鉴权信息接口"})
@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @ApiOperation(httpMethod = "GET",value = "获取用户信息")
    @GetMapping("/info/{username}")
    public R getUserInfo(@PathVariable("username") String username) {
        User user = infoService.getUser(username);
        return R.ok().put("data", user);
    }

}
