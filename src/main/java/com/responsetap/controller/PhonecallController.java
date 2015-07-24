package com.responsetap.controller;

import com.responsetap.model.PhonecallForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


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



            try {

                String urlString = "http://pbx.responsetap.com/cgi-bin/geek.pl?text={text}&number={number}";
                urlString = urlString.replace("{text}", URLEncoder.encode(phonecallForm.getText(), "UTF-8"));
                urlString = urlString.replace("{number}", URLEncoder.encode(phonecallForm.getNumber(), "UTF-8"));

                InputStream response = new URL(urlString).openStream();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(response))) {
                    for (String line; (line = reader.readLine()) != null;) {
                        System.out.println(line);
                    }
                }
            }
            catch (MalformedURLException e) {
                System.err.println(e.getMessage());
                System.err.println(e.fillInStackTrace());
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
                System.err.println(e.fillInStackTrace());
            }

            return "result";
        }
    }
}
