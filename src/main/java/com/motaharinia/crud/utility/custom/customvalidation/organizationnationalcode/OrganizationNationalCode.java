package com.motaharinia.crud.utility.custom.customvalidation.organizationnationalcode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی کد ملی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = OrganizationNationalCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganizationNationalCode {
    String message() default "CUSTOM_VALIDATION.ORGANIZATION_NATIONAL_CODE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
