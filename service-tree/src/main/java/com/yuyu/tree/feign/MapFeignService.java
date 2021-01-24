package com.yuyu.tree.feign;

import com.yuyu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("map")
public interface MapFeignService {

    @GetMapping("/area/{id}")
    R getArea(@PathVariable("id") Integer id);

}
