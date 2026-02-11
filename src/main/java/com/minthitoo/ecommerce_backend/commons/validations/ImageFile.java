package com.minthitoo.ecommerce_backend.commons.validations;

import com.minthitoo.ecommerce_backend.commons.validations.implementations.ImageFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFileValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageFile {
    String message() default "{mime.post.image}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
