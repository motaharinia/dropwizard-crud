package com.motaharinia.crud.utility.custom.customvalidation.decimalcount;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده و تعداد دقیق تعداد رقم اعشار<br>
 * فقط برای فیلدهای از نوع Double میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = DecimalCountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalCount {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * حداقل تعداد رقم اعشار
     *
     * @return خروجی:
     */
    int min() default 0;

    /**
     * حداکثر تعداد رقم اعشار
     *
     * @return خروجی:
     */
    int max() default 0;

    /**
     * تعداد رقم اعشار دقیق
     *
     * @return خروجی:
     */
    int exact() default 0;

}
