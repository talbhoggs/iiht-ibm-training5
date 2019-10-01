package com.ibm.ph.amperca.iihtibm.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = UserNameUniqueValidator.class)
@Retention(RUNTIME)
public @interface UniqueUserName {
    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}