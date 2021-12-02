package com.motaharinia.crud.utility.custom.customvalidation.integerrange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده اعداد صحیح<br>
 * فقط برای فیلدهای از نوع Integer میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = IntegerRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerRange {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * ابتدای محدوده عددی
     *
     * @return خروجی:
     */
    int min() default Integer.MIN_VALUE;

    /**
     * انتهای محدوده عددی
     *
     * @return خروجی:
     */
    int max() default Integer.MAX_VALUE;

}
