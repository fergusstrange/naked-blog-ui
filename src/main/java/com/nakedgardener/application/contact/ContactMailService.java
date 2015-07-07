package com.nakedgardener.application.contact;

import com.nakedgardener.web.contact.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;

import static javax.mail.Message.RecipientType.TO;

@Component
public class ContactMailService {

    @Value("${email.to.address}")
    private String emailToAddress;

    private final JavaMailSender mailSender;

    @Autowired
    public ContactMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(ContactForm contactForm) {
        mailSender.send(mimeMessage -> {
            mimeMessage.setRecipient(TO, new InternetAddress(emailToAddress));
            mimeMessage.setFrom(contactForm.getEmail());
            mimeMessage.setReplyTo(new InternetAddress[]{new InternetAddress(contactForm.getEmail())});
            mimeMessage.setSubject("Someone has contacted the Naked Gardener");
            mimeMessage.setText(contactForm.getMessage());
        });
    }
}
