package com.ecommerce.ecommerce.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidation {
    public  boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
