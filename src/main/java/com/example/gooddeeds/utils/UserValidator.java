package com.example.gooddeeds.utils;

import com.example.gooddeeds.exceptions.InvalidFieldException;
import com.example.gooddeeds.exceptions.PasswordInvalidException;
import com.example.gooddeeds.model.ApplicationUser;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String PASSWORD_REGEX = "^(?=.{8,}$)(?=.*[A-Z])(?=.*[0-9]).*$";


    public static void validatePassword(String password) throws PasswordInvalidException{

        if (StringUtils.isBlank(password))
            throw new PasswordInvalidException("Password must be entered");

        pattern = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordInvalidException("Password must be valid");
        }

    }

    public static void validateUser(ApplicationUser user) throws InvalidFieldException {
        DeedValidator.validateEmail(user.getEmail());
        // TODO: change ad validate users name method so it would display correct error message
        DeedValidator.validateContactPerson(user.getName());
        DeedValidator.validatePhoneNumber(user.getPhone());
        UserValidator.validatePassword(user.getPassword());
    }


}
