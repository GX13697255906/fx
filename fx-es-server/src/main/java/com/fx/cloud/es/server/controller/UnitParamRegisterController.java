package com.fx.cloud.es.server.controller;

import com.fx.cloud.es.server.service.UnitParamRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xun.guo
 */
@RequestMapping(value = "/unitParamRegister")
@RestController
public class UnitParamRegisterController {

    @Autowired
    private UnitParamRegisterService unitParamRegisterService;

    @GetMapping(value = "/createIndex")
    public void createIndex() {
        unitParamRegisterService.createIndex();
    }

}
