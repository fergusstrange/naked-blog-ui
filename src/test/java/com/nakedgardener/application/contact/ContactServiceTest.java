package com.nakedgardener.application.contact;

import com.nakedgardener.web.contact.ContactForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    private BindingResult bindingResult;

    private ContactService contactService = new ContactService();

    @Test
    public void shouldReturnSuccessfulResult() throws Exception {
        given(bindingResult.hasFieldErrors()).willReturn(false);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isSuccess()).isTrue();
    }

    @Test
    public void shouldReturnFailureResult() throws Exception {
        given(bindingResult.hasFieldErrors()).willReturn(true);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isSuccess()).isFalse();
    }

    @Test
    public void shouldHaveNoErrorsWhenBindingResultHasNoFieldErrors() throws Exception {
        given(bindingResult.hasFieldErrors()).willReturn(false);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isHasErrors()).isFalse();
    }

    @Test
    public void shouldHaveErrorsWhenBindingResultHasFieldErrors() throws Exception {
        given(bindingResult.hasFieldErrors()).willReturn(true);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isHasErrors()).isTrue();
    }

    @Test
    public void shouldReturnTwoFieldErrors() throws Exception {
        List<FieldError> fieldErrors = fieldErrors();
        given(bindingResult.getFieldErrors()).willReturn(fieldErrors);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.getBindingErrors()).hasSize(2);
    }

    @Test
    public void shouldReturnAppropriateMessagesForValidationErrors() throws Exception {
        List<FieldError> fieldErrors = fieldErrors();
        given(bindingResult.getFieldErrors()).willReturn(fieldErrors);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.getBindingErrors()).contains("In your face.", "You gone did something wrong.");
    }

    private List<FieldError> fieldErrors() {
        return asList(
            mockFieldErrorWithMessage("In your face."),
            mockFieldErrorWithMessage("You gone did something wrong.")
        );
    }

    private FieldError mockFieldErrorWithMessage(String message) {
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getDefaultMessage()).thenReturn(message);
        return fieldError;
    }

    private ContactForm contactForm() {
        return new ContactForm();
    }
}