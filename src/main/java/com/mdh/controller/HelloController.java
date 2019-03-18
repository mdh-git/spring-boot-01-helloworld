package com.mdh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: madonghao
 * @Date: 2019/1/15 11:57
 */
@Controller
public class HelloController {


    @ResponseBody
    @RequestMapping(value = "hello")
    public String hello(){
        return "hello word!";
    }
}
