package com.motaharinia.crud.utility.custom.customvalidation.daterange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده تاریخ epoch mili long<br>
 * فقط برای فیلدهای از نوع Long میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = EpochMiliDateRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EpochMiliDateRange {

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
