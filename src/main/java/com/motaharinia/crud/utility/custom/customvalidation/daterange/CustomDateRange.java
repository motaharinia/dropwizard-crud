package com.motaharinia.crud.utility.custom.customvalidation.daterange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده تارریخ customdate<br>
 * فقط برای فیلدهای از نوع CustomDate میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = CustomDateRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDateRange {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * تاریخ جلالی ابتدای محدوده زمانی<br>
     * فرمتها<br>
     * YYYY-MM-DD <br>
     * unlimited <br>
     * today <br>
     *
     * @return خروجی: ابتدای محدوده زمانی
     */
    String from() default "unlimited";

    /**
     * تاریخ جلالی انتهای محدوده زمانی<br>
     * فرمتها<br>
     * YYYY-MM-DD <br>
     * unlimited <br>
     * today <br>
     *
     * @return خروجی: انتهای محدوده زمانی
     */
    String to() default "unlimited";

}
