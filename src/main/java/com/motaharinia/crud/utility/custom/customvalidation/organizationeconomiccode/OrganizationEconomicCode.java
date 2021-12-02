package com.motaharinia.crud.utility.custom.customvalidation.organizationeconomiccode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی کد اقتصادی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = OrganizationEconomicCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganizationEconomicCode {
    String message() default "CUSTOM_VALIDATION.ORGANIZATION_ECONOMIC_CODE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
