package com.weixin.controller;

import com.weixin.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guishuangfeng on 2016/5/12.
 */
@Controller
public class TestController {

    @Autowired
    private TbUserMapper mapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {

        return "index";
    }
}
