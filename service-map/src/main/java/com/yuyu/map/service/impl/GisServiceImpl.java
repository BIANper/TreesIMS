package com.yuyu.map.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.yuyu.common.utils.R;
import com.yuyu.map.dao.GisMapper;
import com.yuyu.map.feign.TreeFeignService;
import com.yuyu.map.po.GIS;
import com.yuyu.map.po.Tree;
import com.yuyu.map.service.GisService;
import com.yuyu.map.vo.TreeBaseVo;
import com.yuyu.map.vo.TreesSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GisServiceImpl implements GisService {

    @Autowired
    private GisMapper gisMapper;

    @Autowired
    private TreeFeignService treeFeignService;

    @Override
    public List<JSONObject> getAll() {
        List<GIS> gisList = gisMapper.getAll();
        List<JSONObject> collect = gisList.stream().map(it -> {
            String gis = it.getGis();
            JSONObject jsonObj = JSON.parseObject(JSONObject.toJSONString(it));
            String sb = new StringBuffer(gis.substring(6)).reverse().substring(1);
            String[] s = new StringBuffer(sb).reverse().toString().split(" ");
            JSONArray array = new JSONArray();
            array.set(0, s[0]);
            array.set(1, s[1]);
            jsonObj.put("position", array);
            return jsonObj;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<JSONObject> getGisList(TreesSearchVo searchVo) {
        String s1 = JSONObject.toJSONString(searchVo, SerializerFeature.WriteMapNullValue);
        Object object = JSON.parseObject(s1);
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(object,SerializerFeature.WriteMapNullValue));
        R r = treeFeignService.getTreeBaseList(jsonObject);
        LinkedHashMap<String,Object> pageInfo = (LinkedHashMap<String,Object>) r.get("data");
        List<LinkedHashMap<String,Object>> baseVoList = (List<LinkedHashMap<String,Object>>) pageInfo.get("list");
        List<Integer> collectIds = baseVoList.stream().map(it-> (Integer) it.get("id")).collect(Collectors.toList());
        if(collectIds.isEmpty()) return null;
        List<GIS> gisList = gisMapper.getByTreeIds(collectIds);
        return gisList.stream().map(it -> {
            String gis = it.getGis();
            JSONObject jsonObj = JSON.parseObject(JSONObject.toJSONString(it));
            String sb = new StringBuffer(gis.substring(6)).reverse().substring(1);
            String[] s = new StringBuffer(sb).reverse().toString().split(" ");
            JSONArray array = new JSONArray();
            array.set(0, s[0]);
            array.set(1, s[1]);
            jsonObj.put("position", array);
            return jsonObj;
        }).collect(Collectors.toList());
    }

}
