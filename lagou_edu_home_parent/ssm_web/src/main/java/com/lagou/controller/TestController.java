package com.lagou.controller;

import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //@ResponseBody+@Controller ，注解生效，条件是要在spirngmvc.xml 配置注解增强 ，接口返回List, 会议json形式返回给对方
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/list")
    public List<Test> finaAllTest(){
        List<Test> all = testService.findAll();
        return all;
    }

}
