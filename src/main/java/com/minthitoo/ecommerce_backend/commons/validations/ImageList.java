package com.minthitoo.ecommerce_backend.commons.validations;

import com.minthitoo.ecommerce_backend.commons.validations.implementations.ImageListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ImageListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageList {

    String message() default "{required.images.list}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
