package com.responsetap.controller;

import com.responsetap.model.PhonecallForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class PhonecallController {

    @RequestMapping(value="/phonecall", method = RequestMethod.GET)
    public String showPhoneCallPage(Model model) {

        model.addAttribute("phonecallForm", new PhonecallForm());

        return "phonecall";
    }

    @RequestMapping(value="/phonecall", method = RequestMethod.POST)
    public String handleSubmitPhonecallPage(Model model, @Valid PhonecallForm phonecallForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "phonecall";
        }
        else {
            model.addAttribute("phonecallForm", phonecallForm);
            return "result";
        }
    }
}
