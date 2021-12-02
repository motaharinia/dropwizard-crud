package com.motaharinia.crud.utility.custom.customvalidation.postalcode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی کد پستی <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PostalCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostalCode {

    String message() default "CUSTOM_VALIDATION.POSTAL_CODE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
