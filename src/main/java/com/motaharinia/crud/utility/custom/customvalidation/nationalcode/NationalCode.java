package com.motaharinia.crud.utility.custom.customvalidation.nationalcode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی کد ملی <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = NationalCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalCode {


    String message() default "CUSTOM_VALIDATION.NATIONAL_CODE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
