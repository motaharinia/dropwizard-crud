package com.motaharinia.client.project.utility.custom.customvalidation.latincharacters;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی رشته با کارکترهای  لاتین و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = LatinCharactersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LatinCharacters {

    String message() default "CUSTOM_VALIDATION.LATIN_CHARACTERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
