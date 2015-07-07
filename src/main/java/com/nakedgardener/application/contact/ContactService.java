package com.nakedgardener.application.contact;

import com.nakedgardener.web.contact.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static com.nakedgardener.application.contact.ContactResult.ContactResultBuilder;
import static com.nakedgardener.application.contact.ContactResult.contactResultBuilder;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Component
public class ContactService {

    public static final String MAIL_ERROR_MESSAGE = "Uh oh! Something went wrong, please try again.";
    private final ContactMailService contactMailService;

    @Autowired
    public ContactService(ContactMailService contactMailService) {
        this.contactMailService = contactMailService;
    }

    public ContactResult processContactForm(ContactForm contactForm, BindingResult bindingResult) {
        return bindingResult.hasFieldErrors() ?
                fieldErrorContactResult(bindingResult) :
                sendMail(contactForm);
    }

    private ContactResult sendMail(ContactForm contactForm) {
        ContactResultBuilder result = contactResultBuilder().success(true);
        try {
            contactMailService.sendEmail(contactForm);
        }
        catch(Exception e) {
            result.success(false).addErrors(singletonList(MAIL_ERROR_MESSAGE));
        }
        return result.build();
    }

    private ContactResult fieldErrorContactResult(BindingResult bindingResult) {
        return contactResultBuilder()
                .success(false)
                .addErrors(bindingErrors(bindingResult))
                .build();
    }

    private List<String> bindingErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(toList());
    }

}
