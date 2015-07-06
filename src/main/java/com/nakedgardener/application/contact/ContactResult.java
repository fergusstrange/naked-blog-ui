package com.nakedgardener.application.contact;

import java.util.ArrayList;
import java.util.List;

public class ContactResult {

    private final boolean success;
    private final boolean hasErrors;
    private final List<String> bindingErrors;

    private ContactResult(boolean success, boolean hasErrors, List<String> bindingErrors) {
        this.success = success;
        this.hasErrors = hasErrors;
        this.bindingErrors = bindingErrors;
    }

    public static ContactResultBuilder contactResultBuilder() {
        return new ContactResultBuilder();
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public List<String> getBindingErrors() {
        return bindingErrors;
    }

    public static class ContactResultBuilder {

        private boolean success;
        private boolean hasErrors;
        private List<String> bindingErrors = new ArrayList<>();

        public ContactResultBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ContactResultBuilder hasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
            return this;
        }

        public ContactResultBuilder addBindingErrors(List<String> bindingError) {
            this.bindingErrors.addAll(bindingError);
            return this;
        }

        public ContactResult build() {
            return new ContactResult(success, hasErrors, bindingErrors);
        }
    }
}
