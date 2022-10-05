package com.ssl.notebase.kafak.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ssl.notebase.kafak.wechat.common.BaseResponseVO;
import com.ssl.notebase.kafak.wechat.conf.WechatTemplateProperties;
import com.ssl.notebase.kafak.wechat.service.WeChatTemplateService;
import com.ssl.notebase.kafak.wechat.uitls.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/10/5 14:57
 * @description
 */
@RestController
@RequestMapping(value = "/v1")
public class WeChatTemplateController {

    @Autowired
    private WechatTemplateProperties properties;

    @Autowired
    private WeChatTemplateService wechatTemplateService;

    /**
     * 获取问卷模版
     */
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public BaseResponseVO getTemplate() {

        WechatTemplateProperties.WechatTemplate wechatTemplate = wechatTemplateService.getWechatTemplate();

        Map<String, Object> result = Maps.newHashMap();
        result.put("templateId", wechatTemplate.getTemplateId());
        result.put("template", FileUtils.readFile2JsonArray(wechatTemplate.getTemplateFilePath()));

        return BaseResponseVO.success(result);
    }

    /**
     * 获取问卷结果
     */
    @RequestMapping(value = "/template/result", method = RequestMethod.GET)
    public BaseResponseVO templateStatistics(
            @RequestParam(value = "templateId", required = false) String templateId) {

        JSONObject statistics = wechatTemplateService.templateStatistics(templateId);

        return BaseResponseVO.success(statistics);
    }

    /**
     * 上传问卷结果
     */
    @RequestMapping(value = "/template/report", method = RequestMethod.POST)
    public BaseResponseVO dataReported(
            @RequestBody String reportData) {

        wechatTemplateService.templateReported(JSON.parseObject(reportData));

        return BaseResponseVO.success();
    }

}

