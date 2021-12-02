package com.motaharinia.crud.utility.custom.customvalidation.mobile;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی تلفن همراه <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mobile {

    String message() default "CUSTOM_VALIDATION.MOBILE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
