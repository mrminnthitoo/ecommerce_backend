package com.minthitoo.ecommerce_backend.commons.validations;

import com.minthitoo.ecommerce_backend.commons.validations.implementations.UniqueSlugValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSlugValidator.class)
@Documented
public @interface UniqueSlug {
    String message() default "slug already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
