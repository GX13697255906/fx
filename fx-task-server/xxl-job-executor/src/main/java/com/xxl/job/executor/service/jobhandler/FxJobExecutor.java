package com.xxl.job.executor.service.jobhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author Administrator
 */
@Slf4j
@Component
public class FxJobExecutor {


    @XxlJob(value = "fxTaskHandler")
    public void fxTaskHandler() {
        String param = XxlJobHelper.getJobParam();
        XxlJobContext context = XxlJobHelper.getContext();
        System.out.println(JSONObject.toJSONString(context));
        Map json = (Map) JSON.parse(param);
        System.out.println(JSONObject.toJSONString(json));
    }

}
