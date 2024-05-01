package com.itgnostic.test4sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index2";
    }

}
