package com.responsetap.controller;

import com.responsetap.model.PhonecallForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;


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

            String urlString = "http://pbx.responsetap.com/cgi-bin/geek.pl?text={text}&token=VYMWwWBDrEAJU6WtWsHQY2Qe&number={number}";
            urlString = urlString.replace("{text}", phonecallForm.getText());
            urlString = urlString.replace("{number}", phonecallForm.getNumber());

            try {
                URL myURL = new URL(urlString);
                URLConnection myURLConnection = myURL.openConnection();
                myURLConnection.setDoOutput(true);
                myURLConnection.connect();
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
