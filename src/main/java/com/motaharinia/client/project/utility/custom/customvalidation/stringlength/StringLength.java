package com.motaharinia.client.project.utility.custom.customvalidation.stringlength;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی محدوده و تعداد دقیق طول رشته ها<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = StringLengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringLength {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * حداقل طول رشته. پیش فرض غیر فعال است
     *
     * @return خروجی:
     */
    int min() default 0;

    /**
     * حداکثر طول رشته. پیش فرض غیر فعال است
     *
     * @return خروجی:
     */
    int max() default Integer.MAX_VALUE;

    /**
     * طول دقیق رشته. پیش فرض غیر فعال است
     *
     * @return خروجی:
     */
    int exact() default 0;

}
