package com.responsetap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PhonecallController {

    @RequestMapping(value="/phonecall", method = RequestMethod.GET)
    public String showPhoneCallPage(ModelMap modelMap) {


        int counterValue = 0;

        for( int i = 0; i < 100; i++) {
            counterValue = counterValue + 1; //counterValue++
        }

        String message = String.format("You looped %d times", counterValue);
        modelMap.addAttribute("loopCounterValue", message);

        return "phonecall";
    }
}
