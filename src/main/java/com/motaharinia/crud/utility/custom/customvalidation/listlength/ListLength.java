package com.motaharinia.crud.utility.custom.customvalidation.listlength;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده اندازه لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListLengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListLength {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    int exact() default 0;

}
