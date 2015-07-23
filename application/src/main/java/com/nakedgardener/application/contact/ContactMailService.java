package com.nakedgardener.application.contact;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.io.IOException;

import static javax.mail.Message.RecipientType.TO;

@Component
public class ContactMailService {

    @Value("${email.to.address}")
    private String emailToAddress;

    private final JavaMailSender mailSender;
    private final HandlebarsViewResolver handlebarsViewResolver;

    @Autowired
    public ContactMailService(JavaMailSender mailSender, HandlebarsViewResolver handlebarsViewResolver) {
        this.mailSender = mailSender;
        this.handlebarsViewResolver = handlebarsViewResolver;
    }

    public void sendEmail(final ContactForm contactForm) {
        mailSender.send(mimeMessage -> {
            mimeMessage.setRecipient(TO, new InternetAddress(emailToAddress));
            mimeMessage.setFrom(new InternetAddress("contact@thenakedgardener.co.uk", "The Naked Gardener"));
            mimeMessage.setReplyTo(new InternetAddress[]{new InternetAddress(contactForm.getEmail())});
            mimeMessage.setSubject("Someone has contacted The Naked Gardener");
            mimeMessage.setText(contactFormEmailMessage(contactForm));
        });
    }

    private String contactFormEmailMessage(ContactForm contactForm) throws IOException {
        return handlebarsViewResolver
                .getHandlebars()
                .compile("contactFormEmailMessage")
                .apply(contactForm);
    }
}
