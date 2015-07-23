package com.nakedgardener.application.contact;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactForm {

    @NotEmpty(message = "Please provide a name.")
    private String name;

    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Please provide an email address.")
    private String email;

    @NotEmpty(message = "Please provide a telephone number.")
    private String telephone;

    @NotEmpty(message = "Please provide a message.")
    private String contactMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }
}