package org.ucll.web4.validator.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordFieldsMatchValidator.class)
@Documented
public @interface PasswordFieldsMatch {
    String message () default "Passwords do not match!";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
