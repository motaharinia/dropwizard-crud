package com.motaharinia.crud.utility.custom.customvalidation.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی نشانی پست الکترونیکی <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "CUSTOM_VALIDATION.EMAIL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
