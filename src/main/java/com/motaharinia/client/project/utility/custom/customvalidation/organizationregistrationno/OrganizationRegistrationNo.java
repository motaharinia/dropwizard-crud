package com.motaharinia.client.project.utility.custom.customvalidation.organizationregistrationno;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author https://github.com/motaharinia<br>
 * انوتیشن اعتبارسنجی شماره ثبت سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = OrganizationRegistrationNoValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganizationRegistrationNo {
    String message() default "CUSTOM_VALIDATION.ORGANIZATION_REGISTRATION_NO";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
