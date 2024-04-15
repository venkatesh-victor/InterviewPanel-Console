package com.zsgs.interviewpanel.validate;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNo(String str) {
        return str != null && str.matches("\\d+");
    }

}
