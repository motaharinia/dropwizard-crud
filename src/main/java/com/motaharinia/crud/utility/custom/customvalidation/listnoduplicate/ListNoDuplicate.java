package com.motaharinia.crud.utility.custom.customvalidation.listnoduplicate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی عدم وجود عنصر تکراری در لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListNoDuplicateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListNoDuplicate {

    String message() default "CUSTOM_VALIDATION.LIST_NO_DUPLICATE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
