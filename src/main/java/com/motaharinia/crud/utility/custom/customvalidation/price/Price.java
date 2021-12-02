package com.motaharinia.crud.utility.custom.customvalidation.price;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی قیمت (عدد بالای صفر و بدون مقدار اعشار) <br>
 * فقط برای فیلدهای از نوع BigDecimal میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {

    String message() default "CUSTOM_VALIDATION.PRICE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
