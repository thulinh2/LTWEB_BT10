package vn.iotstar.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null) return false;
        return email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$");
    }
}