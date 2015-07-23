package com.nakedgardener.application.contact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    private BindingResult bindingResult;

    @Mock
    private ContactMailService contactMailService;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void shouldReturnSuccessfulResult() throws Exception {
        given(bindingResult.hasFieldErrors()).willReturn(false);

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isSuccess()).isTrue();
    }

    @Test
    public void shouldReturnFailureResult() throws Exception {
        givenThatFieldErrorsArePresent();

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
        givenThatFieldErrorsArePresent();

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isHasErrors()).isTrue();
    }

    @Test
    public void shouldReturnTwoFieldErrors() throws Exception {
        givenThatFieldErrorsArePresent();

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.getErrors()).hasSize(2);
    }

    @Test
    public void shouldReturnAppropriateMessagesForValidationErrors() throws Exception {
        givenThatFieldErrorsArePresent();

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.getErrors()).contains("In your face.", "You gone did something wrong.");
    }

    @Test
    public void shouldPassContactFormToEmailServiceWhenNoErrors() throws Exception {
        ContactForm contactForm = contactForm();

        contactService.processContactForm(contactForm, bindingResult);

        verify(contactMailService).sendEmail(contactForm);
    }

    @Test
    public void shouldNotPassContactFormToEmailServiceWhenErrors() throws Exception {
        ContactForm contactForm = contactForm();
        givenThatFieldErrorsArePresent();

        contactService.processContactForm(contactForm, bindingResult);

        verify(contactMailService, never()).sendEmail(contactForm);
    }

    @Test
    public void shouldHandleMailServerExceptionAndReturnUnsuccessfulWithErrorMessage() throws Exception {
        doThrow(new RuntimeException()).when(contactMailService).sendEmail(any(ContactForm.class));

        ContactResult contactResult = contactService.processContactForm(contactForm(), bindingResult);

        assertThat(contactResult.isHasErrors()).isTrue();
        assertThat(contactResult.isSuccess()).isFalse();
        assertThat(contactResult.getErrors()).containsOnly("Uh oh! Something went wrong, please try again.");
    }

    private void givenThatFieldErrorsArePresent() {
        List<FieldError> fieldErrors = fieldErrors();
        given(bindingResult.hasFieldErrors()).willReturn(true);
        given(bindingResult.getFieldErrors()).willReturn(fieldErrors);
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