package com.gx.fm.controller;


import com.gx.fm.entity.Goods;
import com.gx.fm.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@RestController
@RequestMapping("/goods")
@Api(value = "商品管理",tags = "商品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "获取所有商品信息",notes = "获取所有商品信息")
    @RequestMapping(value = "/getAll",method = RequestMethod.POST)
    public List<Goods> getAll(@RequestBody HashMap<String,Object> params){
        return goodsService.getAll();
    }

    @ApiOperation(value = "根据ID删除商品",notes = "根据ID删除商品")
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public boolean delById(@RequestBody Map<String,Object> params) throws Exception{
        if(null == MapUtils.getString(params,"goodsId")){
            throw new SecurityException("商品ID不能为空");
        }
        return goodsService.removeById(MapUtils.getString(params,"goodsId"));
    }



}

