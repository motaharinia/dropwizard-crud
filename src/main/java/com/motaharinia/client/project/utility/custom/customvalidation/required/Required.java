package com.motaharinia.client.project.utility.custom.customvalidation.required;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی الزامی بودن فیلد <br>
 * برای همه فیلدها میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = RequiredValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

    String message() default "CUSTOM_VALIDATION.REQUIRED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
