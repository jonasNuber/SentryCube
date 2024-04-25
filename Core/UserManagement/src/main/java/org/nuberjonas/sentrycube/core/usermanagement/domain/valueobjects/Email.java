package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;
import org.nuberjonas.sentrycube.core.usermanagement.domain.exceptions.EmailValidationException;

import java.util.regex.Pattern;

public final class Email {
    private final String emailAddress;

    public Email(String emailAddress) {
        if(StringUtils.isEmpty(emailAddress)){
            throw new IllegalArgumentException("Email address must not be empty");
        }

        var emailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        var matcher = emailPattern.matcher(emailAddress);

        if(matcher.matches()){
            this.emailAddress = emailAddress;
        } else {
            throw new EmailValidationException("Email address is malformed.");
        }
    }

    public Email change(String newEmailAddress){
        return new Email(newEmailAddress);
    }

    public String get(){
        return emailAddress;
    }
}
