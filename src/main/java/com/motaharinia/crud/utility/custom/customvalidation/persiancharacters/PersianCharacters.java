package com.motaharinia.crud.utility.custom.customvalidation.persiancharacters;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی رشته با کارکترهای پارسی و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PersianCharactersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersianCharacters {

    String message() default "CUSTOM_VALIDATION.PERSIAN_CHARACTERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
