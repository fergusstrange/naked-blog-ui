package com.nakedgardener.application.contact;

import com.nakedgardener.web.contact.ContactForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static com.nakedgardener.application.contact.ContactResult.contactResultBuilder;
import static java.util.stream.Collectors.toList;

@Component
public class ContactService {

    public ContactResult processContactForm(ContactForm contactForm, BindingResult bindingResult) {
        return contactResultBuilder()
                .success(!hasErrors(bindingResult))
                .hasErrors(hasErrors(bindingResult))
                .addBindingErrors(bindingErrors(bindingResult))
                .build();
    }

    private boolean hasErrors(BindingResult bindingResult) {
        return bindingResult.hasFieldErrors();
    }

    private List<String> bindingErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(toList());
    }

}
