package com.nakedgardener.application.contact;

import java.util.ArrayList;
import java.util.List;

public class ContactResult {

    private final boolean success;
    private final List<String> errors;

    private ContactResult(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public static ContactResultBuilder contactResultBuilder() {
        return new ContactResultBuilder();
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean isHasErrors() {
        return !errors.isEmpty();
    }

    public static class ContactResultBuilder {

        private boolean success;
        private List<String> errors = new ArrayList<>();

        public ContactResultBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ContactResultBuilder addErrors(List<String> bindingError) {
            this.errors.addAll(bindingError);
            return this;
        }

        public ContactResult build() {
            return new ContactResult(success, errors);
        }
    }
}
