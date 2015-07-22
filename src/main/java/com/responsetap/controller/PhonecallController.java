package com.responsetap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PhonecallController {

    @RequestMapping("/phonecall")
    public String showPhoneCallPage() {
        return "phonecall";
    }
}
