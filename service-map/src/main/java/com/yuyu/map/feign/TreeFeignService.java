package com.yuyu.map.feign;

import com.alibaba.fastjson.JSONObject;
import com.yuyu.common.utils.R;
import com.yuyu.map.vo.TreesSearchVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("tree")
public interface TreeFeignService {

    @GetMapping("/list/base")
    R getTreeBaseList(@RequestParam JSONObject object);

}
