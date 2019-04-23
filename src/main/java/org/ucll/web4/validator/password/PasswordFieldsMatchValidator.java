package org.ucll.web4.validator.password;

import org.ucll.web4.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFieldsMatchValidator implements ConstraintValidator<PasswordFieldsMatch, UserRegistrationDto> {

    @Override
    public void initialize(PasswordFieldsMatch constraintAnnotation) {}

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext context) {
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordConfirmation());
    }
}
