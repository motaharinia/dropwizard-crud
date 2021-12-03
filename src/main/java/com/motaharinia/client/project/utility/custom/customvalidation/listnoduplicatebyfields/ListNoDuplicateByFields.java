package com.motaharinia.client.project.utility.custom.customvalidation.listnoduplicatebyfields;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی عدم تکرار در لیست توسط فیلدهای مورد نظر<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListNoDuplicateByFieldsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListNoDuplicateByFields {

    String message() default "CUSTOM_VALIDATION.LIST_NO_DUPLICATE_BY_FIELDS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields() default {};

}
