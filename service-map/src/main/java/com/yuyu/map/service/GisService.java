package com.yuyu.map.service;

import com.alibaba.fastjson.JSONObject;

import com.yuyu.map.vo.TreesSearchVo;

import java.util.List;

public interface GisService {

    List<JSONObject> getAll();

    List<JSONObject> getGisList(TreesSearchVo searchVo);
}
