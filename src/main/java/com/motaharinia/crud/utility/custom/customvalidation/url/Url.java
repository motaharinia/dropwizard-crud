package com.motaharinia.crud.utility.custom.customvalidation.url;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی نشانی وب <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 * نمونه اعتبارسنجی: regex101.com/r/HUNasA/2
 */
@Documented
@Constraint(validatedBy = UrlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {

    String message() default "CUSTOM_VALIDATION.URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
