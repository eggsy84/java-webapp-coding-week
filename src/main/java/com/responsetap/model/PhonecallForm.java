package com.responsetap.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class PhonecallForm {

    @Size(min=11, max=11, message = "Please make sure your number is 11 digits")
    private String number;

    @NotBlank(message = "Please give me a message")
    private String text;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
