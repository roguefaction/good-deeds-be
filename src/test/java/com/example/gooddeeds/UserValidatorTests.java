package com.example.gooddeeds;

import com.example.gooddeeds.exceptions.*;
import com.example.gooddeeds.utils.UserValidator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserValidatorTests {

    @Test(expected = PasswordInvalidException.class)
    public void check_if_password_is_valid_should_fail_0() throws PasswordInvalidException{
        String password = "777";
        UserValidator.validatePassword(password);
    }

    @Test(expected = PasswordInvalidException.class)
    public void check_if_password_is_valid_should_fail_1() throws PasswordInvalidException{
        String password = "dsfd";
        UserValidator.validatePassword(password);
    }

    @Test(expected = PasswordInvalidException.class)
    public void check_if_password_is_valid_should_fail_2() throws PasswordInvalidException{
        String password = "12345678";
        UserValidator.validatePassword(password);
    }

    @Test
    public void check_if_password_is_valid_should_pass_0() throws PasswordInvalidException{
        String password = "Password123";
        UserValidator.validatePassword(password);
    }
}
