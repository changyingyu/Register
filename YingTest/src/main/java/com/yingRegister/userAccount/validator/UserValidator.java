package com.yingRegister.userAccount.validator;

import com.yingRegister.userAccount.model.User;
import com.yingRegister.userAccount.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserValidator implements Validator {
    @Autowired
    private RegisterService registerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 5 ) {
            errors.rejectValue("username", "Size.userRegister.username");
        }

        Pattern namePattern = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = namePattern.matcher(user.getUsername());

        if(nameMatcher.find()){
            errors.rejectValue("username", "Char.userRegister.username");
        }
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)");
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());

        if(!passwordMatcher.find() || user.getPassword().length() < 9){

            errors.rejectValue("password", "Size.userRegister.password");
        }

        if (registerService.checkExistUser(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userRegister.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userRegister.passwordConfirm");
        }
    }
}
