package com.nakedgardener.web.contact;

import com.nakedgardener.application.contact.ContactForm;
import com.nakedgardener.application.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = GET)
    public String contactForm() {
        return "contact";
    }

    @RequestMapping(method = POST)
    public String processContactForm(final ModelMap modelMap, @Valid ContactForm contactForm, BindingResult bindingResult) {
        modelMap.addAttribute("contactResult", contactService.processContactForm(contactForm, bindingResult));
        return "contact";
    }
}
