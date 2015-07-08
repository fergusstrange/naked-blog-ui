package com.nakedgardener.application.contact;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.nakedgardener.Application;
import com.nakedgardener.web.contact.ContactForm;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ContactMailServiceTest {

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.ALL);

    @Autowired
    private ContactMailService contactMailService;

    @Test
    public void shouldConnectToMailServer() throws Exception {
        contactMailService.sendEmail(contactForm());
        contactMailService.sendEmail(contactForm());
        contactMailService.sendEmail(contactForm());

        assertThat(greenMail.getReceivedMessages().length).isEqualTo(3);
        assertThat((String) greenMail.getReceivedMessages()[0].getContent())
                .contains("Mr. Mistoffelees")
                .contains("mr.mistoffelees@thenakedgardener.co.uk")
                .contains("+0123456789")
                .contains("Hello.");
    }

    private ContactForm contactForm() {
        ContactForm contactForm = new ContactForm();
        contactForm.setName("Mr. Mistoffelees");
        contactForm.setEmail("mr.mistoffelees@thenakedgardener.co.uk");
        contactForm.setTelephone("+0123456789");
        contactForm.setContactMessage("Hello.");
        return contactForm;
    }
}